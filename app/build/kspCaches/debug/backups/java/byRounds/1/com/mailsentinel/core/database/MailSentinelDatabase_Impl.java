package com.mailsentinel.core.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.mailsentinel.core.database.dao.AccountDao;
import com.mailsentinel.core.database.dao.AccountDao_Impl;
import com.mailsentinel.core.database.dao.AttachmentDao;
import com.mailsentinel.core.database.dao.AttachmentDao_Impl;
import com.mailsentinel.core.database.dao.FolderDao;
import com.mailsentinel.core.database.dao.FolderDao_Impl;
import com.mailsentinel.core.database.dao.ForwardRuleDao;
import com.mailsentinel.core.database.dao.ForwardRuleDao_Impl;
import com.mailsentinel.core.database.dao.MessageDao;
import com.mailsentinel.core.database.dao.MessageDao_Impl;
import com.mailsentinel.core.database.dao.OcrResultDao;
import com.mailsentinel.core.database.dao.OcrResultDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MailSentinelDatabase_Impl extends MailSentinelDatabase {
  private volatile AccountDao _accountDao;

  private volatile FolderDao _folderDao;

  private volatile MessageDao _messageDao;

  private volatile AttachmentDao _attachmentDao;

  private volatile OcrResultDao _ocrResultDao;

  private volatile ForwardRuleDao _forwardRuleDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `accounts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email_address` TEXT NOT NULL, `display_name` TEXT NOT NULL, `imap_host` TEXT NOT NULL, `imap_port` INTEGER NOT NULL, `smtp_host` TEXT NOT NULL, `smtp_port` INTEGER NOT NULL, `use_ssl` INTEGER NOT NULL, `password` TEXT, `oauth_token` TEXT, `refresh_token` TEXT, `token_type` TEXT, `last_sync_time` INTEGER NOT NULL, `is_active` INTEGER NOT NULL, `connection_state` TEXT NOT NULL, `last_error` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_accounts_email_address` ON `accounts` (`email_address`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `folders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `account_id` INTEGER NOT NULL, `full_name` TEXT NOT NULL, `display_name` TEXT NOT NULL, `message_count` INTEGER NOT NULL, `unread_count` INTEGER NOT NULL, `uid_validity` INTEGER NOT NULL, FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_folders_account_id_full_name` ON `folders` (`account_id`, `full_name`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `messages` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `account_id` INTEGER NOT NULL, `folder_id` INTEGER NOT NULL, `uid` INTEGER NOT NULL, `message_id_header` TEXT, `subject` TEXT, `from_address` TEXT NOT NULL, `from_name` TEXT, `to_addresses` TEXT NOT NULL, `cc_addresses` TEXT, `received_date` INTEGER NOT NULL, `sent_date` INTEGER, `is_read` INTEGER NOT NULL, `is_starred` INTEGER NOT NULL, `has_attachments` INTEGER NOT NULL, `preview_text` TEXT, `body_html` TEXT, `body_text` TEXT, `size` INTEGER NOT NULL, `flags` TEXT, FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`folder_id`) REFERENCES `folders`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_messages_account_id_folder_id_uid` ON `messages` (`account_id`, `folder_id`, `uid`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_received_date` ON `messages` (`received_date`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_subject` ON `messages` (`subject`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_from_address` ON `messages` (`from_address`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` INTEGER NOT NULL, `filename` TEXT NOT NULL, `content_type` TEXT NOT NULL, `size` INTEGER NOT NULL, `local_path` TEXT, `is_downloaded` INTEGER NOT NULL, `ocr_result` TEXT, `ocr_processed` INTEGER NOT NULL, FOREIGN KEY(`message_id`) REFERENCES `messages`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_attachments_message_id` ON `attachments` (`message_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ocr_results` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `attachment_id` INTEGER, `message_id` INTEGER, `recognized_text` TEXT NOT NULL, `language` TEXT NOT NULL, `confidence` REAL NOT NULL, `source_type` TEXT NOT NULL, `processed_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocr_results_attachment_id` ON `ocr_results` (`attachment_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocr_results_message_id` ON `ocr_results` (`message_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `forward_rules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `account_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `regex_pattern` TEXT, `regex_target` TEXT NOT NULL, `js_script` TEXT, `target_address` TEXT NOT NULL, `include_ocr` INTEGER NOT NULL, `is_active` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_forward_rules_account_id` ON `forward_rules` (`account_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '20553958eab487d391f233bf4d5dc1ba')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `accounts`");
        db.execSQL("DROP TABLE IF EXISTS `folders`");
        db.execSQL("DROP TABLE IF EXISTS `messages`");
        db.execSQL("DROP TABLE IF EXISTS `attachments`");
        db.execSQL("DROP TABLE IF EXISTS `ocr_results`");
        db.execSQL("DROP TABLE IF EXISTS `forward_rules`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAccounts = new HashMap<String, TableInfo.Column>(18);
        _columnsAccounts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("email_address", new TableInfo.Column("email_address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("display_name", new TableInfo.Column("display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("imap_host", new TableInfo.Column("imap_host", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("imap_port", new TableInfo.Column("imap_port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("smtp_host", new TableInfo.Column("smtp_host", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("smtp_port", new TableInfo.Column("smtp_port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("use_ssl", new TableInfo.Column("use_ssl", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("oauth_token", new TableInfo.Column("oauth_token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("refresh_token", new TableInfo.Column("refresh_token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("token_type", new TableInfo.Column("token_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("is_active", new TableInfo.Column("is_active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("connection_state", new TableInfo.Column("connection_state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("last_error", new TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccounts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccounts = new HashSet<TableInfo.Index>(1);
        _indicesAccounts.add(new TableInfo.Index("index_accounts_email_address", true, Arrays.asList("email_address"), Arrays.asList("ASC")));
        final TableInfo _infoAccounts = new TableInfo("accounts", _columnsAccounts, _foreignKeysAccounts, _indicesAccounts);
        final TableInfo _existingAccounts = TableInfo.read(db, "accounts");
        if (!_infoAccounts.equals(_existingAccounts)) {
          return new RoomOpenHelper.ValidationResult(false, "accounts(com.mailsentinel.core.database.entity.AccountEntity).\n"
                  + " Expected:\n" + _infoAccounts + "\n"
                  + " Found:\n" + _existingAccounts);
        }
        final HashMap<String, TableInfo.Column> _columnsFolders = new HashMap<String, TableInfo.Column>(7);
        _columnsFolders.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("account_id", new TableInfo.Column("account_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("full_name", new TableInfo.Column("full_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("display_name", new TableInfo.Column("display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("message_count", new TableInfo.Column("message_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("unread_count", new TableInfo.Column("unread_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("uid_validity", new TableInfo.Column("uid_validity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFolders = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysFolders.add(new TableInfo.ForeignKey("accounts", "CASCADE", "NO ACTION", Arrays.asList("account_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesFolders = new HashSet<TableInfo.Index>(1);
        _indicesFolders.add(new TableInfo.Index("index_folders_account_id_full_name", true, Arrays.asList("account_id", "full_name"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoFolders = new TableInfo("folders", _columnsFolders, _foreignKeysFolders, _indicesFolders);
        final TableInfo _existingFolders = TableInfo.read(db, "folders");
        if (!_infoFolders.equals(_existingFolders)) {
          return new RoomOpenHelper.ValidationResult(false, "folders(com.mailsentinel.core.database.entity.FolderEntity).\n"
                  + " Expected:\n" + _infoFolders + "\n"
                  + " Found:\n" + _existingFolders);
        }
        final HashMap<String, TableInfo.Column> _columnsMessages = new HashMap<String, TableInfo.Column>(20);
        _columnsMessages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("account_id", new TableInfo.Column("account_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("folder_id", new TableInfo.Column("folder_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("uid", new TableInfo.Column("uid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("message_id_header", new TableInfo.Column("message_id_header", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("subject", new TableInfo.Column("subject", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("from_address", new TableInfo.Column("from_address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("from_name", new TableInfo.Column("from_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("to_addresses", new TableInfo.Column("to_addresses", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("cc_addresses", new TableInfo.Column("cc_addresses", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("received_date", new TableInfo.Column("received_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("sent_date", new TableInfo.Column("sent_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("is_read", new TableInfo.Column("is_read", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("is_starred", new TableInfo.Column("is_starred", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("has_attachments", new TableInfo.Column("has_attachments", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("preview_text", new TableInfo.Column("preview_text", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("body_html", new TableInfo.Column("body_html", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("body_text", new TableInfo.Column("body_text", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("flags", new TableInfo.Column("flags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessages = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysMessages.add(new TableInfo.ForeignKey("accounts", "CASCADE", "NO ACTION", Arrays.asList("account_id"), Arrays.asList("id")));
        _foreignKeysMessages.add(new TableInfo.ForeignKey("folders", "CASCADE", "NO ACTION", Arrays.asList("folder_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMessages = new HashSet<TableInfo.Index>(4);
        _indicesMessages.add(new TableInfo.Index("index_messages_account_id_folder_id_uid", true, Arrays.asList("account_id", "folder_id", "uid"), Arrays.asList("ASC", "ASC", "ASC")));
        _indicesMessages.add(new TableInfo.Index("index_messages_received_date", false, Arrays.asList("received_date"), Arrays.asList("ASC")));
        _indicesMessages.add(new TableInfo.Index("index_messages_subject", false, Arrays.asList("subject"), Arrays.asList("ASC")));
        _indicesMessages.add(new TableInfo.Index("index_messages_from_address", false, Arrays.asList("from_address"), Arrays.asList("ASC")));
        final TableInfo _infoMessages = new TableInfo("messages", _columnsMessages, _foreignKeysMessages, _indicesMessages);
        final TableInfo _existingMessages = TableInfo.read(db, "messages");
        if (!_infoMessages.equals(_existingMessages)) {
          return new RoomOpenHelper.ValidationResult(false, "messages(com.mailsentinel.core.database.entity.MessageEntity).\n"
                  + " Expected:\n" + _infoMessages + "\n"
                  + " Found:\n" + _existingMessages);
        }
        final HashMap<String, TableInfo.Column> _columnsAttachments = new HashMap<String, TableInfo.Column>(9);
        _columnsAttachments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("message_id", new TableInfo.Column("message_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("filename", new TableInfo.Column("filename", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("content_type", new TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("local_path", new TableInfo.Column("local_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("is_downloaded", new TableInfo.Column("is_downloaded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("ocr_result", new TableInfo.Column("ocr_result", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("ocr_processed", new TableInfo.Column("ocr_processed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttachments = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAttachments.add(new TableInfo.ForeignKey("messages", "CASCADE", "NO ACTION", Arrays.asList("message_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAttachments = new HashSet<TableInfo.Index>(1);
        _indicesAttachments.add(new TableInfo.Index("index_attachments_message_id", false, Arrays.asList("message_id"), Arrays.asList("ASC")));
        final TableInfo _infoAttachments = new TableInfo("attachments", _columnsAttachments, _foreignKeysAttachments, _indicesAttachments);
        final TableInfo _existingAttachments = TableInfo.read(db, "attachments");
        if (!_infoAttachments.equals(_existingAttachments)) {
          return new RoomOpenHelper.ValidationResult(false, "attachments(com.mailsentinel.core.database.entity.AttachmentEntity).\n"
                  + " Expected:\n" + _infoAttachments + "\n"
                  + " Found:\n" + _existingAttachments);
        }
        final HashMap<String, TableInfo.Column> _columnsOcrResults = new HashMap<String, TableInfo.Column>(8);
        _columnsOcrResults.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("attachment_id", new TableInfo.Column("attachment_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("message_id", new TableInfo.Column("message_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("recognized_text", new TableInfo.Column("recognized_text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("confidence", new TableInfo.Column("confidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("source_type", new TableInfo.Column("source_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcrResults.put("processed_at", new TableInfo.Column("processed_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOcrResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOcrResults = new HashSet<TableInfo.Index>(2);
        _indicesOcrResults.add(new TableInfo.Index("index_ocr_results_attachment_id", false, Arrays.asList("attachment_id"), Arrays.asList("ASC")));
        _indicesOcrResults.add(new TableInfo.Index("index_ocr_results_message_id", false, Arrays.asList("message_id"), Arrays.asList("ASC")));
        final TableInfo _infoOcrResults = new TableInfo("ocr_results", _columnsOcrResults, _foreignKeysOcrResults, _indicesOcrResults);
        final TableInfo _existingOcrResults = TableInfo.read(db, "ocr_results");
        if (!_infoOcrResults.equals(_existingOcrResults)) {
          return new RoomOpenHelper.ValidationResult(false, "ocr_results(com.mailsentinel.core.database.entity.OcrResultEntity).\n"
                  + " Expected:\n" + _infoOcrResults + "\n"
                  + " Found:\n" + _existingOcrResults);
        }
        final HashMap<String, TableInfo.Column> _columnsForwardRules = new HashMap<String, TableInfo.Column>(11);
        _columnsForwardRules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("account_id", new TableInfo.Column("account_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("regex_pattern", new TableInfo.Column("regex_pattern", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("regex_target", new TableInfo.Column("regex_target", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("js_script", new TableInfo.Column("js_script", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("target_address", new TableInfo.Column("target_address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("include_ocr", new TableInfo.Column("include_ocr", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("is_active", new TableInfo.Column("is_active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsForwardRules.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysForwardRules = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysForwardRules.add(new TableInfo.ForeignKey("accounts", "CASCADE", "NO ACTION", Arrays.asList("account_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesForwardRules = new HashSet<TableInfo.Index>(1);
        _indicesForwardRules.add(new TableInfo.Index("index_forward_rules_account_id", false, Arrays.asList("account_id"), Arrays.asList("ASC")));
        final TableInfo _infoForwardRules = new TableInfo("forward_rules", _columnsForwardRules, _foreignKeysForwardRules, _indicesForwardRules);
        final TableInfo _existingForwardRules = TableInfo.read(db, "forward_rules");
        if (!_infoForwardRules.equals(_existingForwardRules)) {
          return new RoomOpenHelper.ValidationResult(false, "forward_rules(com.mailsentinel.core.database.entity.ForwardRuleEntity).\n"
                  + " Expected:\n" + _infoForwardRules + "\n"
                  + " Found:\n" + _existingForwardRules);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "20553958eab487d391f233bf4d5dc1ba", "3e98f2459fa8a6c8ffba0748c7e773db");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "accounts","folders","messages","attachments","ocr_results","forward_rules");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `accounts`");
      _db.execSQL("DELETE FROM `folders`");
      _db.execSQL("DELETE FROM `messages`");
      _db.execSQL("DELETE FROM `attachments`");
      _db.execSQL("DELETE FROM `ocr_results`");
      _db.execSQL("DELETE FROM `forward_rules`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AccountDao.class, AccountDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FolderDao.class, FolderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MessageDao.class, MessageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttachmentDao.class, AttachmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OcrResultDao.class, OcrResultDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ForwardRuleDao.class, ForwardRuleDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AccountDao accountDao() {
    if (_accountDao != null) {
      return _accountDao;
    } else {
      synchronized(this) {
        if(_accountDao == null) {
          _accountDao = new AccountDao_Impl(this);
        }
        return _accountDao;
      }
    }
  }

  @Override
  public FolderDao folderDao() {
    if (_folderDao != null) {
      return _folderDao;
    } else {
      synchronized(this) {
        if(_folderDao == null) {
          _folderDao = new FolderDao_Impl(this);
        }
        return _folderDao;
      }
    }
  }

  @Override
  public MessageDao messageDao() {
    if (_messageDao != null) {
      return _messageDao;
    } else {
      synchronized(this) {
        if(_messageDao == null) {
          _messageDao = new MessageDao_Impl(this);
        }
        return _messageDao;
      }
    }
  }

  @Override
  public AttachmentDao attachmentDao() {
    if (_attachmentDao != null) {
      return _attachmentDao;
    } else {
      synchronized(this) {
        if(_attachmentDao == null) {
          _attachmentDao = new AttachmentDao_Impl(this);
        }
        return _attachmentDao;
      }
    }
  }

  @Override
  public OcrResultDao ocrResultDao() {
    if (_ocrResultDao != null) {
      return _ocrResultDao;
    } else {
      synchronized(this) {
        if(_ocrResultDao == null) {
          _ocrResultDao = new OcrResultDao_Impl(this);
        }
        return _ocrResultDao;
      }
    }
  }

  @Override
  public ForwardRuleDao forwardRuleDao() {
    if (_forwardRuleDao != null) {
      return _forwardRuleDao;
    } else {
      synchronized(this) {
        if(_forwardRuleDao == null) {
          _forwardRuleDao = new ForwardRuleDao_Impl(this);
        }
        return _forwardRuleDao;
      }
    }
  }
}
