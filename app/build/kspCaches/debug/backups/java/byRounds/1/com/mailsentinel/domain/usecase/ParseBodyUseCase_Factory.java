package com.mailsentinel.domain.usecase;

import com.mailsentinel.core.parser.EmailBodyParser;
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
public final class ParseBodyUseCase_Factory implements Factory<ParseBodyUseCase> {
  private final Provider<EmailBodyParser> parserProvider;

  public ParseBodyUseCase_Factory(Provider<EmailBodyParser> parserProvider) {
    this.parserProvider = parserProvider;
  }

  @Override
  public ParseBodyUseCase get() {
    return newInstance(parserProvider.get());
  }

  public static ParseBodyUseCase_Factory create(
      javax.inject.Provider<EmailBodyParser> parserProvider) {
    return new ParseBodyUseCase_Factory(Providers.asDaggerProvider(parserProvider));
  }

  public static ParseBodyUseCase_Factory create(Provider<EmailBodyParser> parserProvider) {
    return new ParseBodyUseCase_Factory(parserProvider);
  }

  public static ParseBodyUseCase newInstance(EmailBodyParser parser) {
    return new ParseBodyUseCase(parser);
  }
}
