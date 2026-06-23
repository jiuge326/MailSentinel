package com.mailsentinel.core.di;

import com.mailsentinel.core.database.MailSentinelDatabase;
import com.mailsentinel.core.database.dao.ForwardRuleDao;
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
public final class DatabaseModule_ProvideForwardRuleDaoFactory implements Factory<ForwardRuleDao> {
  private final Provider<MailSentinelDatabase> dbProvider;

  public DatabaseModule_ProvideForwardRuleDaoFactory(Provider<MailSentinelDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ForwardRuleDao get() {
    return provideForwardRuleDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideForwardRuleDaoFactory create(
      javax.inject.Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideForwardRuleDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideForwardRuleDaoFactory create(
      Provider<MailSentinelDatabase> dbProvider) {
    return new DatabaseModule_ProvideForwardRuleDaoFactory(dbProvider);
  }

  public static ForwardRuleDao provideForwardRuleDao(MailSentinelDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideForwardRuleDao(db));
  }
}
