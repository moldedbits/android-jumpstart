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
-dontwarn retrofit.**
-keep class retrofit.** { *; }
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

## Okio
-dontwarn java.nio.file.**
-dontwarn sun.misc.Unsafe
-dontwarn org.codehaus.mojo.**

## Saripaar - validation
-keep class com.mobsandgeeks.saripaar.** {*;}
-keep @com.mobsandgeeks.saripaar.annotation.ValidateUsing class * {*;}

## Models
-keepclassmembers class com.moldedbits.android.model.** { *; }

##Design library
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-dontwarn com.viewpagerindicator.**

## crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**