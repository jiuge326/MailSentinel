# MailSentinel ProGuard Rules

# ===== JavaMail (Android Mail) =====
-keep class com.sun.mail.** { *; }
-keep class javax.mail.** { *; }
-keep class javax.activation.** { *; }

# ===== ML Kit =====
-keep class com.google.mlkit.** { *; }
-keep class com.google.android.gms.tasks.** { *; }

# ===== Hilt =====
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
    @javax.inject.Inject <fields>;
    @dagger.hilt.InstallIn class *;
    @dagger.hilt.android.AndroidEntryPoint class *;
}

# ===== Room =====
-keep class * extends androidx.room.RoomDatabase
-keep class com.mailsentinel.core.database.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase {
    public <init>(...);
}
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }

# ===== Kotlin Serialization =====
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ===== Jsoup =====
-keep class org.jsoup.** { *; }

# ===== Compose =====
-keep class androidx.compose.** { *; }

# ===== 保留 Application 类 =====
-keep class com.mailsentinel.Application { *; }

# ===== 保留 Parcelable =====
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
