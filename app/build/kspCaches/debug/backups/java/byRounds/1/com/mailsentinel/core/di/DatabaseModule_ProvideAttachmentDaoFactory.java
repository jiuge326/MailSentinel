package com.mailsentinel.core.di;

import com.mailsentinel.core.database.MailSentinelDatabase;
import com.mailsentinel.core.database.dao.AttachmentDao;
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
public final class DatabaseModule_ProvideAttachmentDaoFactory implements Factory<AttachmentDao> {
  private final Provider<MailSentinelDatabase> dbProvider;

  public DatabaseModule_ProvideAttachmentDaoFactory(Provider<MailSentinelDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AttachmentDao get() {
    return provideAttachmentDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideAttachmentDaoFactory create(
      javax.inject.Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideAttachmentDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideAttachmentDaoFactory create(
      Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideAttachmentDaoFactory(dbProvider);
  }

  public static AttachmentDao provideAttachmentDao(MailSentinelDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAttachmentDao(db));
  }
}
