package com.mailsentinel.domain.usecase;

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
public final class ManageRulesUseCase_Factory implements Factory<ManageRulesUseCase> {
  private final Provider<ForwardRepository> forwardRepositoryProvider;

  public ManageRulesUseCase_Factory(Provider<ForwardRepository> forwardRepositoryProvider) {
    this.forwardRepositoryProvider = forwardRepositoryProvider;
  }

  @Override
  public ManageRulesUseCase get() {
    return newInstance(forwardRepositoryProvider.get());
  }

  public static ManageRulesUseCase_Factory create(
      javax.inject.Provider<ForwardRepository> forwardRepositoryProvider) {
    return new ManageRulesUseCase_Factory(Providers.asDaggerProvider(forwardRepositoryProvider));
  }

  public static ManageRulesUseCase_Factory create(
      Provider<ForwardRepository> forwardRepositoryProvider) {
    return new ManageRulesUseCase_Factory(forwardRepositoryProvider);
  }

  public static ManageRulesUseCase newInstance(ForwardRepository forwardRepository) {
    return new ManageRulesUseCase(forwardRepository);
  }
}
