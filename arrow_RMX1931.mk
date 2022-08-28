#
# Copyright (C) 2020 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

$(call inherit-product, device/realme/RMX1931/device.mk)

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/product_launched_with_p.mk)

# Inherit some common stuff.
$(call inherit-product, vendor/arrow/config/common.mk)

TARGET_BOOT_ANIMATION_RES := 1080
TARGET_SUPPORTS_QUICK_TAP := true
TARGET_FACE_UNLOCK_SUPPORTED := true
TARGET_USES_BLUR := true

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := arrow_RMX1931
PRODUCT_DEVICE := RMX1931
PRODUCT_BRAND := realme
PRODUCT_MANUFACTURER := realme

PRODUCT_GMS_CLIENTID_BASE := android-oppo
