package com.mailsentinel.core.di;

import com.mailsentinel.core.parser.EmailBodyParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RepositoryModule_Companion_ProvideEmailBodyParserFactory implements Factory<EmailBodyParser> {
  @Override
  public EmailBodyParser get() {
    return provideEmailBodyParser();
  }

  public static RepositoryModule_Companion_ProvideEmailBodyParserFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EmailBodyParser provideEmailBodyParser() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideEmailBodyParser());
  }

  private static final class InstanceHolder {
    static final RepositoryModule_Companion_ProvideEmailBodyParserFactory INSTANCE = new RepositoryModule_Companion_ProvideEmailBodyParserFactory();
  }
}
