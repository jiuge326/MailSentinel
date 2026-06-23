package com.mailsentinel.core.di;

import com.mailsentinel.core.network.ConnectionStateManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class NetworkModule_ProvideConnectionStateManagerFactory implements Factory<ConnectionStateManager> {
  @Override
  public ConnectionStateManager get() {
    return provideConnectionStateManager();
  }

  public static NetworkModule_ProvideConnectionStateManagerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ConnectionStateManager provideConnectionStateManager() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideConnectionStateManager());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideConnectionStateManagerFactory INSTANCE = new NetworkModule_ProvideConnectionStateManagerFactory();
  }
}
