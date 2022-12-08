git clone https://github.com/kdrag0n/proton-clang.git -b master prebuilts/clang/host/linux-x86/clang-proton --depth=1
rm -rf hardware/qcom-caf/sm8150/audio
rm -rf hardware/qcom-caf/sm8150/display
rm -rf hardware/qcom-caf/sm8150/media
git clone https://github.com/LineageOS/android_hardware_qcom_media.git -b lineage-20.0-caf-sm8150 hardware/qcom-caf/sm8150/media
git clone https://github.com/LineageOS/android_hardware_qcom_audio.git -b lineage-20.0-caf-sm8150 hardware/qcom-caf/sm8150/audio
git clone https://github.com/LineageOS/android_hardware_qcom_display.git -b lineage-20.0-caf-sm8150 hardware/qcom-caf/sm8150/display
