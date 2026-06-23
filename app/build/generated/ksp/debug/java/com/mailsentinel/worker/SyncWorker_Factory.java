package com.mailsentinel.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.mailsentinel.domain.repository.MailRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SyncWorker_Factory implements Factory<SyncWorker> {
  private final Provider<Context> contextProvider;

  private final Provider<WorkerParameters> workerParamsProvider;

  private final Provider<MailRepository> mailRepositoryProvider;

  public SyncWorker_Factory(Provider<Context> contextProvider,
      Provider<WorkerParameters> workerParamsProvider,
      Provider<MailRepository> mailRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.workerParamsProvider = workerParamsProvider;
    this.mailRepositoryProvider = mailRepositoryProvider;
  }

  @Override
  public SyncWorker get() {
    return newInstance(contextProvider.get(), workerParamsProvider.get(), mailRepositoryProvider.get());
  }

  public static SyncWorker_Factory create(javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<WorkerParameters> workerParamsProvider,
      javax.inject.Provider<MailRepository> mailRepositoryProvider) {
    return new SyncWorker_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(workerParamsProvider), Providers.asDaggerProvider(mailRepositoryProvider));
  }

  public static SyncWorker_Factory create(Provider<Context> contextProvider,
      Provider<WorkerParameters> workerParamsProvider,
      Provider<MailRepository> mailRepositoryProvider) {
    return new SyncWorker_Factory(contextProvider, workerParamsProvider, mailRepositoryProvider);
  }

  public static SyncWorker newInstance(Context context, WorkerParameters workerParams,
      MailRepository mailRepository) {
    return new SyncWorker(context, workerParams, mailRepository);
  }
}
