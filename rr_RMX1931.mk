#
# Copyright (C) 2020 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

$(call inherit-product, device/realme/RMX1931/device.mk)

# Inherit some common RR stuff.
$(call inherit-product, vendor/rr/config/common_full_phone.mk)

#FaceUnlock
TARGET_FACE_UNLOCK_SUPPORTED := true


# Device identifier. This must come after all inclusions.
PRODUCT_NAME := rr_RMX1931
PRODUCT_DEVICE := RMX1931
PRODUCT_BRAND := realme
PRODUCT_MODEL := realme X2 Pro
PRODUCT_MANUFACTURER := realme

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="coral-user 11 RP1A.201105.002 6869500 release-keys"

BUILD_FINGERPRINT := "google/coral/coral:11/RP1A.201105.002/6869500:user/release-keys"

PRODUCT_GMS_CLIENTID_BASE := android-oppo