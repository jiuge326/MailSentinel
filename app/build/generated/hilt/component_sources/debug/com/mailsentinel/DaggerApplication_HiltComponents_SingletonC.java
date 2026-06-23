package com.mailsentinel;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.mailsentinel.core.database.MailSentinelDatabase;
import com.mailsentinel.core.database.dao.AccountDao;
import com.mailsentinel.core.database.dao.AttachmentDao;
import com.mailsentinel.core.database.dao.FolderDao;
import com.mailsentinel.core.database.dao.ForwardRuleDao;
import com.mailsentinel.core.database.dao.MessageDao;
import com.mailsentinel.core.database.dao.OcrResultDao;
import com.mailsentinel.core.di.AppModule_ProvideDatabaseFactory;
import com.mailsentinel.core.di.AppModule_ProvideOcrManagerFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideAccountDaoFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideAttachmentDaoFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideFolderDaoFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideForwardRuleDaoFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideMessageDaoFactory;
import com.mailsentinel.core.di.DatabaseModule_ProvideOcrResultDaoFactory;
import com.mailsentinel.core.di.NetworkModule_ProvideConnectionStateManagerFactory;
import com.mailsentinel.core.di.NetworkModule_ProvideImapClientFactory;
import com.mailsentinel.core.di.RepositoryModule_Companion_ProvideMimeParserFactory;
import com.mailsentinel.core.di.RepositoryModule_Companion_ProvideRegexMatcherFactory;
import com.mailsentinel.core.network.ConnectionStateManager;
import com.mailsentinel.core.network.ImapClient;
import com.mailsentinel.core.ocr.OcrManager;
import com.mailsentinel.core.parser.MimeParser;
import com.mailsentinel.core.regex.RegexMatcher;
import com.mailsentinel.data.repository.ForwardRepositoryImpl;
import com.mailsentinel.data.repository.MailRepositoryImpl;
import com.mailsentinel.data.repository.OcrRepositoryImpl;
import com.mailsentinel.domain.repository.ForwardRepository;
import com.mailsentinel.domain.repository.MailRepository;
import com.mailsentinel.domain.repository.OcrRepository;
import com.mailsentinel.domain.usecase.SyncMailUseCase;
import com.mailsentinel.ui.screen.account.AddAccountViewModel;
import com.mailsentinel.ui.screen.account.AddAccountViewModel_HiltModules;
import com.mailsentinel.ui.screen.account.AddAccountViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.mailsentinel.ui.screen.account.AddAccountViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.mailsentinel.ui.screen.inbox.InboxViewModel;
import com.mailsentinel.ui.screen.inbox.InboxViewModel_HiltModules;
import com.mailsentinel.ui.screen.inbox.InboxViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.mailsentinel.ui.screen.inbox.InboxViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.mailsentinel.ui.screen.rules.RulesViewModel;
import com.mailsentinel.ui.screen.rules.RulesViewModel_HiltModules;
import com.mailsentinel.ui.screen.rules.RulesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.mailsentinel.ui.screen.rules.RulesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.mailsentinel.ui.screen.settings.SettingsViewModel;
import com.mailsentinel.ui.screen.settings.SettingsViewModel_HiltModules;
import com.mailsentinel.ui.screen.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.mailsentinel.ui.screen.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.mailsentinel.ui.screen.status.AccountStatusViewModel;
import com.mailsentinel.ui.screen.status.AccountStatusViewModel_HiltModules;
import com.mailsentinel.ui.screen.status.AccountStatusViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.mailsentinel.ui.screen.status.AccountStatusViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerApplication_HiltComponents_SingletonC {
  private DaggerApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public Application_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements Application_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public Application_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements Application_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public Application_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements Application_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public Application_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements Application_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements Application_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements Application_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements Application_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public Application_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends Application_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends Application_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends Application_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends Application_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(AccountStatusViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AccountStatusViewModel_HiltModules.KeyModule.provide()).put(AddAccountViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AddAccountViewModel_HiltModules.KeyModule.provide()).put(InboxViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, InboxViewModel_HiltModules.KeyModule.provide()).put(RulesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, RulesViewModel_HiltModules.KeyModule.provide()).put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends Application_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AccountStatusViewModel> accountStatusViewModelProvider;

    private Provider<AddAccountViewModel> addAccountViewModelProvider;

    private Provider<InboxViewModel> inboxViewModelProvider;

    private Provider<RulesViewModel> rulesViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private SyncMailUseCase syncMailUseCase() {
      return new SyncMailUseCase(singletonCImpl.bindMailRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.accountStatusViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.addAccountViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.inboxViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.rulesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(AccountStatusViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) accountStatusViewModelProvider)).put(AddAccountViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) addAccountViewModelProvider)).put(InboxViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) inboxViewModelProvider)).put(RulesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) rulesViewModelProvider)).put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) settingsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.mailsentinel.ui.screen.status.AccountStatusViewModel 
          return (T) new AccountStatusViewModel(singletonCImpl.bindMailRepositoryProvider.get());

          case 1: // com.mailsentinel.ui.screen.account.AddAccountViewModel 
          return (T) new AddAccountViewModel(singletonCImpl.bindMailRepositoryProvider.get());

          case 2: // com.mailsentinel.ui.screen.inbox.InboxViewModel 
          return (T) new InboxViewModel(viewModelCImpl.syncMailUseCase());

          case 3: // com.mailsentinel.ui.screen.rules.RulesViewModel 
          return (T) new RulesViewModel(singletonCImpl.bindForwardRepositoryProvider.get());

          case 4: // com.mailsentinel.ui.screen.settings.SettingsViewModel 
          return (T) new SettingsViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bindOcrRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends Application_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends Application_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends Application_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<MailSentinelDatabase> provideDatabaseProvider;

    private Provider<AccountDao> provideAccountDaoProvider;

    private Provider<FolderDao> provideFolderDaoProvider;

    private Provider<MessageDao> provideMessageDaoProvider;

    private Provider<AttachmentDao> provideAttachmentDaoProvider;

    private Provider<ImapClient> provideImapClientProvider;

    private Provider<MimeParser> provideMimeParserProvider;

    private Provider<ConnectionStateManager> provideConnectionStateManagerProvider;

    private Provider<MailRepositoryImpl> mailRepositoryImplProvider;

    private Provider<MailRepository> bindMailRepositoryProvider;

    private Provider<ForwardRuleDao> provideForwardRuleDaoProvider;

    private Provider<RegexMatcher> provideRegexMatcherProvider;

    private Provider<ForwardRepositoryImpl> forwardRepositoryImplProvider;

    private Provider<ForwardRepository> bindForwardRepositoryProvider;

    private Provider<OcrManager> provideOcrManagerProvider;

    private Provider<OcrResultDao> provideOcrResultDaoProvider;

    private Provider<OcrRepositoryImpl> ocrRepositoryImplProvider;

    private Provider<OcrRepository> bindOcrRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<MailSentinelDatabase>(singletonCImpl, 2));
      this.provideAccountDaoProvider = DoubleCheck.provider(new SwitchingProvider<AccountDao>(singletonCImpl, 1));
      this.provideFolderDaoProvider = DoubleCheck.provider(new SwitchingProvider<FolderDao>(singletonCImpl, 3));
      this.provideMessageDaoProvider = DoubleCheck.provider(new SwitchingProvider<MessageDao>(singletonCImpl, 4));
      this.provideAttachmentDaoProvider = DoubleCheck.provider(new SwitchingProvider<AttachmentDao>(singletonCImpl, 5));
      this.provideImapClientProvider = DoubleCheck.provider(new SwitchingProvider<ImapClient>(singletonCImpl, 6));
      this.provideMimeParserProvider = DoubleCheck.provider(new SwitchingProvider<MimeParser>(singletonCImpl, 7));
      this.provideConnectionStateManagerProvider = DoubleCheck.provider(new SwitchingProvider<ConnectionStateManager>(singletonCImpl, 8));
      this.mailRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindMailRepositoryProvider = DoubleCheck.provider((Provider) mailRepositoryImplProvider);
      this.provideForwardRuleDaoProvider = DoubleCheck.provider(new SwitchingProvider<ForwardRuleDao>(singletonCImpl, 10));
      this.provideRegexMatcherProvider = DoubleCheck.provider(new SwitchingProvider<RegexMatcher>(singletonCImpl, 11));
      this.forwardRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 9);
      this.bindForwardRepositoryProvider = DoubleCheck.provider((Provider) forwardRepositoryImplProvider);
      this.provideOcrManagerProvider = DoubleCheck.provider(new SwitchingProvider<OcrManager>(singletonCImpl, 13));
      this.provideOcrResultDaoProvider = DoubleCheck.provider(new SwitchingProvider<OcrResultDao>(singletonCImpl, 14));
      this.ocrRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 12);
      this.bindOcrRepositoryProvider = DoubleCheck.provider((Provider) ocrRepositoryImplProvider);
    }

    @Override
    public void injectApplication(Application application) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.mailsentinel.data.repository.MailRepositoryImpl 
          return (T) new MailRepositoryImpl(singletonCImpl.provideAccountDaoProvider.get(), singletonCImpl.provideFolderDaoProvider.get(), singletonCImpl.provideMessageDaoProvider.get(), singletonCImpl.provideAttachmentDaoProvider.get(), singletonCImpl.provideImapClientProvider.get(), singletonCImpl.provideMimeParserProvider.get(), singletonCImpl.provideConnectionStateManagerProvider.get());

          case 1: // com.mailsentinel.core.database.dao.AccountDao 
          return (T) DatabaseModule_ProvideAccountDaoFactory.provideAccountDao(singletonCImpl.provideDatabaseProvider.get());

          case 2: // com.mailsentinel.core.database.MailSentinelDatabase 
          return (T) AppModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.mailsentinel.core.database.dao.FolderDao 
          return (T) DatabaseModule_ProvideFolderDaoFactory.provideFolderDao(singletonCImpl.provideDatabaseProvider.get());

          case 4: // com.mailsentinel.core.database.dao.MessageDao 
          return (T) DatabaseModule_ProvideMessageDaoFactory.provideMessageDao(singletonCImpl.provideDatabaseProvider.get());

          case 5: // com.mailsentinel.core.database.dao.AttachmentDao 
          return (T) DatabaseModule_ProvideAttachmentDaoFactory.provideAttachmentDao(singletonCImpl.provideDatabaseProvider.get());

          case 6: // com.mailsentinel.core.network.ImapClient 
          return (T) NetworkModule_ProvideImapClientFactory.provideImapClient();

          case 7: // com.mailsentinel.core.parser.MimeParser 
          return (T) RepositoryModule_Companion_ProvideMimeParserFactory.provideMimeParser();

          case 8: // com.mailsentinel.core.network.ConnectionStateManager 
          return (T) NetworkModule_ProvideConnectionStateManagerFactory.provideConnectionStateManager();

          case 9: // com.mailsentinel.data.repository.ForwardRepositoryImpl 
          return (T) new ForwardRepositoryImpl(singletonCImpl.provideForwardRuleDaoProvider.get(), singletonCImpl.provideRegexMatcherProvider.get());

          case 10: // com.mailsentinel.core.database.dao.ForwardRuleDao 
          return (T) DatabaseModule_ProvideForwardRuleDaoFactory.provideForwardRuleDao(singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.mailsentinel.core.regex.RegexMatcher 
          return (T) RepositoryModule_Companion_ProvideRegexMatcherFactory.provideRegexMatcher();

          case 12: // com.mailsentinel.data.repository.OcrRepositoryImpl 
          return (T) new OcrRepositoryImpl(singletonCImpl.provideOcrManagerProvider.get(), singletonCImpl.provideOcrResultDaoProvider.get());

          case 13: // com.mailsentinel.core.ocr.OcrManager 
          return (T) AppModule_ProvideOcrManagerFactory.provideOcrManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 14: // com.mailsentinel.core.database.dao.OcrResultDao 
          return (T) DatabaseModule_ProvideOcrResultDaoFactory.provideOcrResultDao(singletonCImpl.provideDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
