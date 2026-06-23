package com.mailsentinel.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.mailsentinel.core.database.dao.AttachmentDao;
import com.mailsentinel.domain.repository.OcrRepository;
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
public final class OcrWorker_Factory implements Factory<OcrWorker> {
  private final Provider<Context> contextProvider;

  private final Provider<WorkerParameters> workerParamsProvider;

  private final Provider<OcrRepository> ocrRepositoryProvider;

  private final Provider<AttachmentDao> attachmentDaoProvider;

  public OcrWorker_Factory(Provider<Context> contextProvider,
      Provider<WorkerParameters> workerParamsProvider,
      Provider<OcrRepository> ocrRepositoryProvider,
      Provider<AttachmentDao> attachmentDaoProvider) {
    this.contextProvider = contextProvider;
    this.workerParamsProvider = workerParamsProvider;
    this.ocrRepositoryProvider = ocrRepositoryProvider;
    this.attachmentDaoProvider = attachmentDaoProvider;
  }

  @Override
  public OcrWorker get() {
    return newInstance(contextProvider.get(), workerParamsProvider.get(), ocrRepositoryProvider.get(), attachmentDaoProvider.get());
  }

  public static OcrWorker_Factory create(javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<WorkerParameters> workerParamsProvider,
      javax.inject.Provider<OcrRepository> ocrRepositoryProvider,
      javax.inject.Provider<AttachmentDao> attachmentDaoProvider) {
    return new OcrWorker_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(workerParamsProvider), Providers.asDaggerProvider(ocrRepositoryProvider), Providers.asDaggerProvider(attachmentDaoProvider));
  }

  public static OcrWorker_Factory create(Provider<Context> contextProvider,
      Provider<WorkerParameters> workerParamsProvider,
      Provider<OcrRepository> ocrRepositoryProvider,
      Provider<AttachmentDao> attachmentDaoProvider) {
    return new OcrWorker_Factory(contextProvider, workerParamsProvider, ocrRepositoryProvider, attachmentDaoProvider);
  }

  public static OcrWorker newInstance(Context context, WorkerParameters workerParams,
      OcrRepository ocrRepository, AttachmentDao attachmentDao) {
    return new OcrWorker(context, workerParams, ocrRepository, attachmentDao);
  }
}
