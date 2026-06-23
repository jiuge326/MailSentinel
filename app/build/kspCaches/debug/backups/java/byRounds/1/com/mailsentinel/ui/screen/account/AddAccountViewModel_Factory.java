package com.mailsentinel.ui.screen.account;

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
public final class AddAccountViewModel_Factory implements Factory<AddAccountViewModel> {
  private final Provider<MailRepository> mailRepositoryProvider;

  public AddAccountViewModel_Factory(Provider<MailRepository> mailRepositoryProvider) {
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public AddAccountViewModel get() {
    return newInstance(mailRepositoryProvider.get());
  }

  public static AddAccountViewModel_Factory create(
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new AddAccountViewModel_Factory(Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static AddAccountViewModel_Factory create(
      Provider<MailRepository> mailRepositoryProvider) {
    return new AddAccountViewModel_Factory(mailRepositoryProvider);
  }

  public static AddAccountViewModel newInstance(MailRepository mailRepository) {
    return new AddAccountViewModel(mailRepository);
  }
}
