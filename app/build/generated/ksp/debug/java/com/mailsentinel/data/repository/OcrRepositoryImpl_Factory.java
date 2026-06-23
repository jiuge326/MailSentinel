package com.mailsentinel.data.repository;

import com.mailsentinel.core.database.dao.OcrResultDao;
import com.mailsentinel.core.ocr.OcrManager;
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
public final class OcrRepositoryImpl_Factory implements Factory<OcrRepositoryImpl> {
  private final Provider<OcrManager> ocrManagerProvider;

  private final Provider<OcrResultDao> ocrResultDaoProvider;

  public OcrRepositoryImpl_Factory(Provider<OcrManager> ocrManagerProvider,
      Provider<OcrResultDao> ocrResultDaoProvider) {
    this.ocrManagerProvider = ocrManagerProvider;
    this.ocrResultDaoProvider = ocrResultDaoProvider;
  }

  @Override
  public OcrRepositoryImpl get() {
    return newInstance(ocrManagerProvider.get(), ocrResultDaoProvider.get());
  }

  public static OcrRepositoryImpl_Factory create(
      javax.inject.Provider<OcrManager> ocrManagerProvider,
      javax.inject.Provider<OcrResultDao> ocrResultDaoProvider) {
    return new OcrRepositoryImpl_Factory(Providers.asDaggerProvider(ocrManagerProvider), Providers.asDaggerProvider(ocrResultDaoProvider));
  }

  public static OcrRepositoryImpl_Factory create(Provider<OcrManager> ocrManagerProvider,
      Provider<OcrResultDao> ocrResultDaoProvider) {
    return new OcrRepositoryImpl_Factory(ocrManagerProvider, ocrResultDaoProvider);
  }

  public static OcrRepositoryImpl newInstance(OcrManager ocrManager, OcrResultDao ocrResultDao) {
    return new OcrRepositoryImpl(ocrManager, ocrResultDao);
  }
}
