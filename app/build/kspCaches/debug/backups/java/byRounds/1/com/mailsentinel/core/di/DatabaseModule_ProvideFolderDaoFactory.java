package com.mailsentinel.core.di;

import com.mailsentinel.core.database.MailSentinelDatabase;
import com.mailsentinel.core.database.dao.FolderDao;
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
public final class DatabaseModule_ProvideFolderDaoFactory implements Factory<FolderDao> {
  private final Provider<MailSentinelDatabase> dbProvider;

  public DatabaseModule_ProvideFolderDaoFactory(Provider<MailSentinelDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public FolderDao get() {
    return provideFolderDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideFolderDaoFactory create(
      javax.inject.Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideFolderDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideFolderDaoFactory create(
      Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideFolderDaoFactory(dbProvider);
  }

  public static FolderDao provideFolderDao(MailSentinelDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideFolderDao(db));
  }
}
