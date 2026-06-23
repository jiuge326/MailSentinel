package com.mailsentinel.ui.screen.inbox;

import com.mailsentinel.domain.usecase.SyncMailUseCase;
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
public final class InboxViewModel_Factory implements Factory<InboxViewModel> {
  private final Provider<SyncMailUseCase> syncMailUseCaseProvider;

  public InboxViewModel_Factory(Provider<SyncMailUseCase> syncMailUseCaseProvider) {
    this.syncMailUseCaseProvider = syncMailUseCaseProvider;
  }

  @Override
  public InboxViewModel get() {
    return newInstance(syncMailUseCaseProvider.get());
  }

  public static InboxViewModel_Factory create(
      javax.inject.Provider<SyncMailUseCase> syncMailUseCaseProvider) {
    return new InboxViewModel_Factory(Providers.asDaggerProvider(syncMailUseCaseProvider));
  }

  public static InboxViewModel_Factory create(Provider<SyncMailUseCase> syncMailUseCaseProvider) {
    return new InboxViewModel_Factory(syncMailUseCaseProvider);
  }

  public static InboxViewModel newInstance(SyncMailUseCase syncMailUseCase) {
    return new InboxViewModel(syncMailUseCase);
  }
}
