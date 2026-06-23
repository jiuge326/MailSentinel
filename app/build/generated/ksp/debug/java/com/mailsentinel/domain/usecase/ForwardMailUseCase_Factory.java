package com.mailsentinel.domain.usecase;

import com.mailsentinel.domain.repository.ForwardRepository;
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
public final class ForwardMailUseCase_Factory implements Factory<ForwardMailUseCase> {
  private final Provider<ForwardRepository> forwardRepositoryProvider;

  private final Provider<MailRepository> mailRepositoryProvider;

  public ForwardMailUseCase_Factory(Provider<ForwardRepository> forwardRepositoryProvider,
      Provider<MailRepository> mailRepositoryProvider) {
    this.forwardRepositoryProvider = forwardRepositoryProvider;
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public ForwardMailUseCase get() {
    return newInstance(forwardRepositoryProvider.get(), mailRepositoryProvider.get());
  }

  public static ForwardMailUseCase_Factory create(
      javax.inject.Provider<ForwardRepository> forwardRepositoryProvider,
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new ForwardMailUseCase_Factory(Providers.asDaggerProvider(forwardRepositoryProvider), Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static ForwardMailUseCase_Factory create(
      Provider<ForwardRepository> forwardRepositoryProvider,
      Provider<MailRepository> mailRepositoryProvider) {
    return new ForwardMailUseCase_Factory(forwardRepositoryProvider, mailRepositoryProvider);
  }

  public static ForwardMailUseCase newInstance(ForwardRepository forwardRepository,
      MailRepository mailRepository) {
    return new ForwardMailUseCase(forwardRepository, mailRepository);
  }
}
