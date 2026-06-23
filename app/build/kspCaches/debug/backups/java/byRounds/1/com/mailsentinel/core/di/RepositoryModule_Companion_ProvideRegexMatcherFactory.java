package com.mailsentinel.core.di;

import com.mailsentinel.core.regex.RegexMatcher;
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
public final class RepositoryModule_Companion_ProvideRegexMatcherFactory implements Factory<RegexMatcher> {
  @Override
  public RegexMatcher get() {
    return provideRegexMatcher();
  }

  public static RepositoryModule_Companion_ProvideRegexMatcherFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RegexMatcher provideRegexMatcher() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideRegexMatcher());
  }

  private static final class InstanceHolder {
    static final RepositoryModule_Companion_ProvideRegexMatcherFactory INSTANCE = new RepositoryModule_Companion_ProvideRegexMatcherFactory();
  }
}
