package com.mailsentinel.ui.screen.rules;

import com.mailsentinel.domain.repository.ForwardRepository;
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
public final class RulesViewModel_Factory implements Factory<RulesViewModel> {
  private final Provider<ForwardRepository> forwardRepositoryProvider;

  public RulesViewModel_Factory(Provider<ForwardRepository> forwardRepositoryProvider) {
    this.forwardRepositoryProvider = forwardRepositoryProvider;
  }

  @Override
  public RulesViewModel get() {
    return newInstance(forwardRepositoryProvider.get());
  }

  public static RulesViewModel_Factory create(
      javax.inject.Provider<ForwardRepository> forwardRepositoryProvider) {
    return new RulesViewModel_Factory(Providers.asDaggerProvider(forwardRepositoryProvider));
  }

  public static RulesViewModel_Factory create(
      Provider<ForwardRepository> forwardRepositoryProvider) {
    return new RulesViewModel_Factory(forwardRepositoryProvider);
  }

  public static RulesViewModel newInstance(ForwardRepository forwardRepository) {
    return new RulesViewModel(forwardRepository);
  }
}
