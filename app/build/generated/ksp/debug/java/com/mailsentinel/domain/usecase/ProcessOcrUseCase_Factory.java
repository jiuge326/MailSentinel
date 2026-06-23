package com.mailsentinel.domain.usecase;

import com.mailsentinel.domain.repository.OcrRepository;
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
public final class ProcessOcrUseCase_Factory implements Factory<ProcessOcrUseCase> {
  private final Provider<OcrRepository> ocrRepositoryProvider;

  public ProcessOcrUseCase_Factory(Provider<OcrRepository> ocrRepositoryProvider) {
    this.ocrRepositoryProvider = ocrRepositoryProvider;
  }

  @Override
  public ProcessOcrUseCase get() {
    return newInstance(ocrRepositoryProvider.get());
  }

  public static ProcessOcrUseCase_Factory create(
      javax.inject.Provider<OcrRepository> ocrRepositoryProvider) {
    return new ProcessOcrUseCase_Factory(Providers.asDaggerProvider(ocrRepositoryProvider));
  }

  public static ProcessOcrUseCase_Factory create(Provider<OcrRepository> ocrRepositoryProvider) {
    return new ProcessOcrUseCase_Factory(ocrRepositoryProvider);
  }

  public static ProcessOcrUseCase newInstance(OcrRepository ocrRepository) {
    return new ProcessOcrUseCase(ocrRepository);
  }
}
