package com.mailsentinel.core.di;

import com.mailsentinel.core.parser.MimeParser;
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
public final class RepositoryModule_Companion_ProvideMimeParserFactory implements Factory<MimeParser> {
  @Override
  public MimeParser get() {
    return provideMimeParser();
  }

  public static RepositoryModule_Companion_ProvideMimeParserFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MimeParser provideMimeParser() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideMimeParser());
  }

  private static final class InstanceHolder {
    static final RepositoryModule_Companion_ProvideMimeParserFactory INSTANCE = new RepositoryModule_Companion_ProvideMimeParserFactory();
  }
}
