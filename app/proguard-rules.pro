# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/abhishek/Development/AndroidSDK/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

## Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

## butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

## Models
-keepclassmembers class bdesir.raneh.salon.model.** { *; }

##Design library
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-dontwarn com.viewpagerindicator.**

## crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

## Okio
-dontwarn okio.**

## okhttp3
-dontwarn okhttp3.**

## Rx Internal
-dontwarn rx.internal.util.**
-dontwarn rx.internal.schedulers.**