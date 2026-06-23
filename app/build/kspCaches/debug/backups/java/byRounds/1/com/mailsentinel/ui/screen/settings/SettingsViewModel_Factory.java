package com.mailsentinel.ui.screen.settings;

import android.content.Context;
import com.mailsentinel.domain.repository.OcrRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<OcrRepository> ocrRepositoryProvider;

  public SettingsViewModel_Factory(Provider<Context> contextProvider,
      Provider<OcrRepository> ocrRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.ocrRepositoryProvider = ocrRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(contextProvider.get(), ocrRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<OcrRepository> ocrRepositoryProvider) {
    return new SettingsViewModel_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(ocrRepositoryProvider));
  }

  public static SettingsViewModel_Factory create(Provider<Context> contextProvider,
      Provider<OcrRepository> ocrRepositoryProvider) {
    return new SettingsViewModel_Factory(contextProvider, ocrRepositoryProvider);
  }

  public static SettingsViewModel newInstance(Context context, OcrRepository ocrRepository) {
    return new SettingsViewModel(context, ocrRepository);
  }
}
