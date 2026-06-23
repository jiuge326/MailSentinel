package com.mailsentinel.core.di;

import com.mailsentinel.core.network.SmtpClient;
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
public final class NetworkModule_ProvideSmtpClientFactory implements Factory<SmtpClient> {
  @Override
  public SmtpClient get() {
    return provideSmtpClient();
  }

  public static NetworkModule_ProvideSmtpClientFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SmtpClient provideSmtpClient() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideSmtpClient());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideSmtpClientFactory INSTANCE = new NetworkModule_ProvideSmtpClientFactory();
  }
}
