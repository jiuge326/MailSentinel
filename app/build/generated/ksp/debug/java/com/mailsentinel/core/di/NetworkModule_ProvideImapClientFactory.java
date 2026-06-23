package com.mailsentinel.core.di;

import com.mailsentinel.core.network.ImapClient;
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
public final class NetworkModule_ProvideImapClientFactory implements Factory<ImapClient> {
  @Override
  public ImapClient get() {
    return provideImapClient();
  }

  public static NetworkModule_ProvideImapClientFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImapClient provideImapClient() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideImapClient());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideImapClientFactory INSTANCE = new NetworkModule_ProvideImapClientFactory();
  }
}
