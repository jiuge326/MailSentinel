package com.mailsentinel.core.di;

import android.content.Context;
import com.mailsentinel.core.service.KeepAliveManager;
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
public final class AppModule_ProvideKeepAliveManagerFactory implements Factory<KeepAliveManager> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideKeepAliveManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public KeepAliveManager get() {
    return provideKeepAliveManager(contextProvider.get());
  }

  public static AppModule_ProvideKeepAliveManagerFactory create(
      javax.inject.Provider<Context> contextProvider) {
    return new AppModule_ProvideKeepAliveManagerFactory(Providers.asDaggerProvider(contextProvider));
  }

  public static AppModule_ProvideKeepAliveManagerFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideKeepAliveManagerFactory(contextProvider);
  }

  public static KeepAliveManager provideKeepAliveManager(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideKeepAliveManager(context));
  }
}
