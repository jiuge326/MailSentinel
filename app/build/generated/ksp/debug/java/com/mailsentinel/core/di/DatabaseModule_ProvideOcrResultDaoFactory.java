package com.mailsentinel.core.di;

import com.mailsentinel.core.database.MailSentinelDatabase;
import com.mailsentinel.core.database.dao.OcrResultDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideOcrResultDaoFactory implements Factory<OcrResultDao> {
  private final Provider<MailSentinelDatabase> dbProvider;

  public DatabaseModule_ProvideOcrResultDaoFactory(Provider<MailSentinelDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public OcrResultDao get() {
    return provideOcrResultDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideOcrResultDaoFactory create(
      javax.inject.Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideOcrResultDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideOcrResultDaoFactory create(
      Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideOcrResultDaoFactory(dbProvider);
  }

  public static OcrResultDao provideOcrResultDao(MailSentinelDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideOcrResultDao(db));
  }
}
