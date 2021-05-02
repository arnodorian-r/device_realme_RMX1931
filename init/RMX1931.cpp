/*
 * Copyright (C) 2020 LineageOS Project
 * Copyright (C) 2020 Harshit Jain
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <vector>
#include <string>
#include <fstream>

#define _REALLY_INCLUDE_SYS__SYSTEM_PROPERTIES_H_
#include <sys/_system_properties.h>

#include <android-base/properties.h>
#include <android-base/logging.h>

#include "property_service.h"
#include "vendor_init.h"

struct RMX1931_props
{
    std::string device_build;
    std::string product_device;
};

std::vector<std::string> ro_props_default_source_order = {
    "",
    "odm.",
    "product.",
    "system.",
    "vendor.",
};

bool isCN()
{
    // Get project number
    std::ifstream infile("/proc/oppoVersion/prjName");
    std::string prj;
    bool ret = false;
    getline(infile, prj);
    if (prj.compare("19781"))
        ret = true;
    return ret;
}

void property_override(char const prop[], char const value[], bool add = true)
{
    prop_info *pi;
    pi = (prop_info *)__system_property_find(prop);
    if (pi)
        __system_property_update(pi, value, strlen(value));
    else if (add)
        __system_property_add(prop, strlen(prop), value, strlen(value));
}


void setRMX(const unsigned int variant)
{
    RMX1931_props prop[3] = {};

    //RMX1931CN
    prop[0] = {
        "RMX1931",
        "RMX1931CN",
    };

    //RMX1931
    prop[1] = {
        "RMX1931",
        "RMX1931L1",
    };

	const auto set_ro_build_prop = [](const std::string &source,
                                      const std::string &prop, const std::string &value) {
        auto prop_name = "ro." + source + "build." + prop;
        property_override(prop_name.c_str(), value.c_str(), false);
    };

    const auto set_ro_product_prop = [](const std::string &source,
                                        const std::string &prop, const std::string &value) {
        auto prop_name = "ro.product." + source + prop;
        property_override(prop_name.c_str(), value.c_str(), false);
    };

    property_override("ro.build.product", prop[variant].product_device.c_str());
    for (const auto &source : ro_props_default_source_order)
    {
        set_ro_product_prop(source, "device", prop[variant].product_device.c_str());
        set_ro_product_prop(source, "model", prop[variant].device_build.c_str());
        set_ro_product_prop(source, "name", prop[variant].device_build.c_str());
    }

}

void vendor_load_properties()
{
    if (isCN())
    {
        setRMX(0); //RMX1931CN
    }
    else
    {
        setRMX(1); //RMX1931
    }
}
