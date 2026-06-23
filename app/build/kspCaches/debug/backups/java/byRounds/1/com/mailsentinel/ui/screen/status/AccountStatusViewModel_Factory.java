package com.mailsentinel.ui.screen.status;

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
public final class AccountStatusViewModel_Factory implements Factory<AccountStatusViewModel> {
  private final Provider<MailRepository> mailRepositoryProvider;

  public AccountStatusViewModel_Factory(Provider<MailRepository> mailRepositoryProvider) {
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public AccountStatusViewModel get() {
    return newInstance(mailRepositoryProvider.get());
  }

  public static AccountStatusViewModel_Factory create(
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new AccountStatusViewModel_Factory(Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static AccountStatusViewModel_Factory create(
      Provider<MailRepository> mailRepositoryProvider) {
    return new AccountStatusViewModel_Factory(mailRepositoryProvider);
  }

  public static AccountStatusViewModel newInstance(MailRepository mailRepository) {
    return new AccountStatusViewModel(mailRepository);
  }
}
