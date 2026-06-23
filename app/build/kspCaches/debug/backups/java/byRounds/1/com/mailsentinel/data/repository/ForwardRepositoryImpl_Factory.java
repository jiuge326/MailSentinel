package com.mailsentinel.data.repository;

import com.mailsentinel.core.database.dao.ForwardRuleDao;
import com.mailsentinel.core.regex.RegexMatcher;
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
public final class ForwardRepositoryImpl_Factory implements Factory<ForwardRepositoryImpl> {
  private final Provider<ForwardRuleDao> forwardRuleDaoProvider;

  private final Provider<RegexMatcher> regexMatcherProvider;

  public ForwardRepositoryImpl_Factory(Provider<ForwardRuleDao> forwardRuleDaoProvider,
      Provider<RegexMatcher> regexMatcherProvider) {
    this.forwardRuleDaoProvider = forwardRuleDaoProvider;
    this.regexMatcherProvider = regexMatcherProvider;
  }

  @Override
  public ForwardRepositoryImpl get() {
    return newInstance(forwardRuleDaoProvider.get(), regexMatcherProvider.get());
  }

  public static ForwardRepositoryImpl_Factory create(
      javax.inject.Provider<ForwardRuleDao> forwardRuleDaoProvider,
      javax.inject.Provider<RegexMatcher> regexMatcherProvider) {
    return new ForwardRepositoryImpl_Factory(Providers.asDaggerProvider(forwardRuleDaoProvider), Providers.asDaggerProvider(regexMatcherProvider));
  }

  public static ForwardRepositoryImpl_Factory create(
      Provider<ForwardRuleDao> forwardRuleDaoProvider,
      Provider<RegexMatcher> regexMatcherProvider) {
    return new ForwardRepositoryImpl_Factory(forwardRuleDaoProvider, regexMatcherProvider);
  }

  public static ForwardRepositoryImpl newInstance(ForwardRuleDao forwardRuleDao,
      RegexMatcher regexMatcher) {
    return new ForwardRepositoryImpl(forwardRuleDao, regexMatcher);
  }
}
