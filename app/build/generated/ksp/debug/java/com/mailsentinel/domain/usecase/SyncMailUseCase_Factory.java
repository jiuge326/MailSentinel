package com.mailsentinel.domain.usecase;

import com.mailsentinel.domain.repository.MailRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SyncMailUseCase_Factory implements Factory<SyncMailUseCase> {
  private final Provider<MailRepository> mailRepositoryProvider;

  public SyncMailUseCase_Factory(Provider<MailRepository> mailRepositoryProvider) {
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public SyncMailUseCase get() {
    return newInstance(mailRepositoryProvider.get());
  }

  public static SyncMailUseCase_Factory create(
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new SyncMailUseCase_Factory(Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static SyncMailUseCase_Factory create(Provider<MailRepository> mailRepositoryProvider) {
    return new SyncMailUseCase_Factory(mailRepositoryProvider);
  }

  public static SyncMailUseCase newInstance(MailRepository mailRepository) {
    return new SyncMailUseCase(mailRepository);
  }
}
