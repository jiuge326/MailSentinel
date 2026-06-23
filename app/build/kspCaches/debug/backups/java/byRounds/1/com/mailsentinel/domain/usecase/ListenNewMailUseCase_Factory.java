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
public final class ListenNewMailUseCase_Factory implements Factory<ListenNewMailUseCase> {
  private final Provider<MailRepository> mailRepositoryProvider;

  public ListenNewMailUseCase_Factory(Provider<MailRepository> mailRepositoryProvider) {
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public ListenNewMailUseCase get() {
    return newInstance(mailRepositoryProvider.get());
  }

  public static ListenNewMailUseCase_Factory create(
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new ListenNewMailUseCase_Factory(Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static ListenNewMailUseCase_Factory create(
      Provider<MailRepository> mailRepositoryProvider) {
    return new ListenNewMailUseCase_Factory(mailRepositoryProvider);
  }

  public static ListenNewMailUseCase newInstance(MailRepository mailRepository) {
    return new ListenNewMailUseCase(mailRepository);
  }
}
