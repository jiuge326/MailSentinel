package com.mailsentinel.data.repository;

import com.mailsentinel.core.database.dao.AccountDao;
import com.mailsentinel.core.database.dao.AttachmentDao;
import com.mailsentinel.core.database.dao.FolderDao;
import com.mailsentinel.core.database.dao.MessageDao;
import com.mailsentinel.core.network.ConnectionStateManager;
import com.mailsentinel.core.network.ImapClient;
import com.mailsentinel.core.parser.MimeParser;
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
public final class MailRepositoryImpl_Factory implements Factory<MailRepositoryImpl> {
  private final Provider<AccountDao> accountDaoProvider;

  private final Provider<FolderDao> folderDaoProvider;

  private final Provider<MessageDao> messageDaoProvider;

  private final Provider<AttachmentDao> attachmentDaoProvider;

  private final Provider<ImapClient> imapClientProvider;

  private final Provider<MimeParser> mimeParserProvider;

  private final Provider<ConnectionStateManager> connectionStateManagerProvider;

  public MailRepositoryImpl_Factory(Provider<AccountDao> accountDaoProvider,
      Provider<FolderDao> folderDaoProvider, Provider<MessageDao> messageDaoProvider,
      Provider<AttachmentDao> attachmentDaoProvider, Provider<ImapClient> imapClientProvider,
      Provider<MimeParser> mimeParserProvider,
      Provider<ConnectionStateManager> connectionStateManagerProvider) {
    this.accountDaoProvider = accountDaoProvider;
    this.folderDaoProvider = folderDaoProvider;
    this.messageDaoProvider = messageDaoProvider;
    this.attachmentDaoProvider = attachmentDaoProvider;
    this.imapClientProvider = imapClientProvider;
    this.mimeParserProvider = mimeParserProvider;
    this.connectionStateManagerProvider = connectionStateManagerProvider;
  }

  @Override
  public MailRepositoryImpl get() {
    return newInstance(accountDaoProvider.get(), folderDaoProvider.get(), messageDaoProvider.get(), attachmentDaoProvider.get(), imapClientProvider.get(), mimeParserProvider.get(), connectionStateManagerProvider.get());
  }

  public static MailRepositoryImpl_Factory create(
      javax.inject.Provider<AccountDao> accountDaoProvider,
      javax.inject.Provider<FolderDao> folderDaoProvider,
      javax.inject.Provider<MessageDao> messageDaoProvider,
      javax.inject.Provider<AttachmentDao> attachmentDaoProvider,
      javax.inject.Provider<ImapClient> imapClientProvider,
      javax.inject.Provider<MimeParser> mimeParserProvider,
      javax.inject.Provider<ConnectionStateManager> connectionStateManagerProvider) {
    return new MailRepositoryImpl_Factory(Providers.asDaggerProvider(accountDaoProvider), Providers.asDaggerProvider(folderDaoProvider), Providers.asDaggerProvider(messageDaoProvider), Providers.asDaggerProvider(attachmentDaoProvider), Providers.asDaggerProvider(imapClientProvider), Providers.asDaggerProvider(mimeParserProvider), Providers.asDaggerProvider(connectionStateManagerProvider));
  }

  public static MailRepositoryImpl_Factory create(Provider<AccountDao> accountDaoProvider,
      Provider<FolderDao> folderDaoProvider, Provider<MessageDao> messageDaoProvider,
      Provider<AttachmentDao> attachmentDaoProvider, Provider<ImapClient> imapClientProvider,
      Provider<MimeParser> mimeParserProvider,
      Provider<ConnectionStateManager> connectionStateManagerProvider) {
    return new MailRepositoryImpl_Factory(accountDaoProvider, folderDaoProvider, messageDaoProvider, attachmentDaoProvider, imapClientProvider, mimeParserProvider, connectionStateManagerProvider);
  }

  public static MailRepositoryImpl newInstance(AccountDao accountDao, FolderDao folderDao,
      MessageDao messageDao, AttachmentDao attachmentDao, ImapClient imapClient,
      MimeParser mimeParser, ConnectionStateManager connectionStateManager) {
    return new MailRepositoryImpl(accountDao, folderDao, messageDao, attachmentDao, imapClient, mimeParser, connectionStateManager);
  }
}
