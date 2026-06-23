package com.mailsentinel.core.di;

import android.content.Context;
import com.mailsentinel.core.ocr.OcrManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideOcrManagerFactory implements Factory<OcrManager> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideOcrManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OcrManager get() {
    return provideOcrManager(contextProvider.get());
  }

  public static AppModule_ProvideOcrManagerFactory create(
      javax.inject.Provider<Context> contextProvider) {
    return new AppModule_ProvideOcrManagerFactory(Providers.asDaggerProvider(contextProvider));
  }

  public static AppModule_ProvideOcrManagerFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideOcrManagerFactory(contextProvider);
  }

  public static OcrManager provideOcrManager(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideOcrManager(context));
  }
}
