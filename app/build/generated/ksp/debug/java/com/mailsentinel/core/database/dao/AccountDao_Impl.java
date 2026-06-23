package com.mailsentinel.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mailsentinel.core.database.entity.AccountEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AccountDao_Impl implements AccountDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AccountEntity> __insertionAdapterOfAccountEntity;

  private final EntityDeletionOrUpdateAdapter<AccountEntity> __updateAdapterOfAccountEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateConnectionState;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public AccountDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccountEntity = new EntityInsertionAdapter<AccountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `accounts` (`id`,`email_address`,`display_name`,`imap_host`,`imap_port`,`smtp_host`,`smtp_port`,`use_ssl`,`password`,`oauth_token`,`refresh_token`,`token_type`,`last_sync_time`,`is_active`,`connection_state`,`last_error`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccountEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getEmailAddress());
        statement.bindString(3, entity.getDisplayName());
        statement.bindString(4, entity.getImapHost());
        statement.bindLong(5, entity.getImapPort());
        statement.bindString(6, entity.getSmtpHost());
        statement.bindLong(7, entity.getSmtpPort());
        final int _tmp = entity.getUseSsl() ? 1 : 0;
        statement.bindLong(8, _tmp);
        if (entity.getPassword() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPassword());
        }
        if (entity.getOauthToken() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOauthToken());
        }
        if (entity.getRefreshToken() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getRefreshToken());
        }
        if (entity.getTokenType() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getTokenType());
        }
        statement.bindLong(13, entity.getLastSyncTime());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindString(15, entity.getConnectionState());
        if (entity.getLastError() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getLastError());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
      }
    };
    this.__updateAdapterOfAccountEntity = new EntityDeletionOrUpdateAdapter<AccountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `accounts` SET `id` = ?,`email_address` = ?,`display_name` = ?,`imap_host` = ?,`imap_port` = ?,`smtp_host` = ?,`smtp_port` = ?,`use_ssl` = ?,`password` = ?,`oauth_token` = ?,`refresh_token` = ?,`token_type` = ?,`last_sync_time` = ?,`is_active` = ?,`connection_state` = ?,`last_error` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccountEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getEmailAddress());
        statement.bindString(3, entity.getDisplayName());
        statement.bindString(4, entity.getImapHost());
        statement.bindLong(5, entity.getImapPort());
        statement.bindString(6, entity.getSmtpHost());
        statement.bindLong(7, entity.getSmtpPort());
        final int _tmp = entity.getUseSsl() ? 1 : 0;
        statement.bindLong(8, _tmp);
        if (entity.getPassword() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPassword());
        }
        if (entity.getOauthToken() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOauthToken());
        }
        if (entity.getRefreshToken() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getRefreshToken());
        }
        if (entity.getTokenType() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getTokenType());
        }
        statement.bindLong(13, entity.getLastSyncTime());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindString(15, entity.getConnectionState());
        if (entity.getLastError() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getLastError());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
        statement.bindLong(19, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateConnectionState = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE accounts SET connection_state = ?, last_error = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM accounts WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AccountEntity account, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAccountEntity.insertAndReturnId(account);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final AccountEntity account, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAccountEntity.handle(account);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateConnectionState(final long id, final String state, final String error,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateConnectionState.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, state);
        _argIndex = 2;
        if (error == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, error);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateConnectionState.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AccountEntity>> getAllActive() {
    final String _sql = "SELECT * FROM accounts WHERE is_active = 1 ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"accounts"}, new Callable<List<AccountEntity>>() {
      @Override
      @NonNull
      public List<AccountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "email_address");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfImapHost = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_host");
          final int _cursorIndexOfImapPort = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_port");
          final int _cursorIndexOfSmtpHost = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_host");
          final int _cursorIndexOfSmtpPort = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_port");
          final int _cursorIndexOfUseSsl = CursorUtil.getColumnIndexOrThrow(_cursor, "use_ssl");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfOauthToken = CursorUtil.getColumnIndexOrThrow(_cursor, "oauth_token");
          final int _cursorIndexOfRefreshToken = CursorUtil.getColumnIndexOrThrow(_cursor, "refresh_token");
          final int _cursorIndexOfTokenType = CursorUtil.getColumnIndexOrThrow(_cursor, "token_type");
          final int _cursorIndexOfLastSyncTime = CursorUtil.getColumnIndexOrThrow(_cursor, "last_sync_time");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfConnectionState = CursorUtil.getColumnIndexOrThrow(_cursor, "connection_state");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AccountEntity> _result = new ArrayList<AccountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccountEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEmailAddress;
            _tmpEmailAddress = _cursor.getString(_cursorIndexOfEmailAddress);
            final String _tmpDisplayName;
            _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            final String _tmpImapHost;
            _tmpImapHost = _cursor.getString(_cursorIndexOfImapHost);
            final int _tmpImapPort;
            _tmpImapPort = _cursor.getInt(_cursorIndexOfImapPort);
            final String _tmpSmtpHost;
            _tmpSmtpHost = _cursor.getString(_cursorIndexOfSmtpHost);
            final int _tmpSmtpPort;
            _tmpSmtpPort = _cursor.getInt(_cursorIndexOfSmtpPort);
            final boolean _tmpUseSsl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseSsl);
            _tmpUseSsl = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpOauthToken;
            if (_cursor.isNull(_cursorIndexOfOauthToken)) {
              _tmpOauthToken = null;
            } else {
              _tmpOauthToken = _cursor.getString(_cursorIndexOfOauthToken);
            }
            final String _tmpRefreshToken;
            if (_cursor.isNull(_cursorIndexOfRefreshToken)) {
              _tmpRefreshToken = null;
            } else {
              _tmpRefreshToken = _cursor.getString(_cursorIndexOfRefreshToken);
            }
            final String _tmpTokenType;
            if (_cursor.isNull(_cursorIndexOfTokenType)) {
              _tmpTokenType = null;
            } else {
              _tmpTokenType = _cursor.getString(_cursorIndexOfTokenType);
            }
            final long _tmpLastSyncTime;
            _tmpLastSyncTime = _cursor.getLong(_cursorIndexOfLastSyncTime);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final String _tmpConnectionState;
            _tmpConnectionState = _cursor.getString(_cursorIndexOfConnectionState);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AccountEntity(_tmpId,_tmpEmailAddress,_tmpDisplayName,_tmpImapHost,_tmpImapPort,_tmpSmtpHost,_tmpSmtpPort,_tmpUseSsl,_tmpPassword,_tmpOauthToken,_tmpRefreshToken,_tmpTokenType,_tmpLastSyncTime,_tmpIsActive,_tmpConnectionState,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAll(final Continuation<? super List<AccountEntity>> $completion) {
    final String _sql = "SELECT * FROM accounts ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AccountEntity>>() {
      @Override
      @NonNull
      public List<AccountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "email_address");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfImapHost = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_host");
          final int _cursorIndexOfImapPort = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_port");
          final int _cursorIndexOfSmtpHost = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_host");
          final int _cursorIndexOfSmtpPort = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_port");
          final int _cursorIndexOfUseSsl = CursorUtil.getColumnIndexOrThrow(_cursor, "use_ssl");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfOauthToken = CursorUtil.getColumnIndexOrThrow(_cursor, "oauth_token");
          final int _cursorIndexOfRefreshToken = CursorUtil.getColumnIndexOrThrow(_cursor, "refresh_token");
          final int _cursorIndexOfTokenType = CursorUtil.getColumnIndexOrThrow(_cursor, "token_type");
          final int _cursorIndexOfLastSyncTime = CursorUtil.getColumnIndexOrThrow(_cursor, "last_sync_time");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfConnectionState = CursorUtil.getColumnIndexOrThrow(_cursor, "connection_state");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AccountEntity> _result = new ArrayList<AccountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccountEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEmailAddress;
            _tmpEmailAddress = _cursor.getString(_cursorIndexOfEmailAddress);
            final String _tmpDisplayName;
            _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            final String _tmpImapHost;
            _tmpImapHost = _cursor.getString(_cursorIndexOfImapHost);
            final int _tmpImapPort;
            _tmpImapPort = _cursor.getInt(_cursorIndexOfImapPort);
            final String _tmpSmtpHost;
            _tmpSmtpHost = _cursor.getString(_cursorIndexOfSmtpHost);
            final int _tmpSmtpPort;
            _tmpSmtpPort = _cursor.getInt(_cursorIndexOfSmtpPort);
            final boolean _tmpUseSsl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseSsl);
            _tmpUseSsl = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpOauthToken;
            if (_cursor.isNull(_cursorIndexOfOauthToken)) {
              _tmpOauthToken = null;
            } else {
              _tmpOauthToken = _cursor.getString(_cursorIndexOfOauthToken);
            }
            final String _tmpRefreshToken;
            if (_cursor.isNull(_cursorIndexOfRefreshToken)) {
              _tmpRefreshToken = null;
            } else {
              _tmpRefreshToken = _cursor.getString(_cursorIndexOfRefreshToken);
            }
            final String _tmpTokenType;
            if (_cursor.isNull(_cursorIndexOfTokenType)) {
              _tmpTokenType = null;
            } else {
              _tmpTokenType = _cursor.getString(_cursorIndexOfTokenType);
            }
            final long _tmpLastSyncTime;
            _tmpLastSyncTime = _cursor.getLong(_cursorIndexOfLastSyncTime);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final String _tmpConnectionState;
            _tmpConnectionState = _cursor.getString(_cursorIndexOfConnectionState);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AccountEntity(_tmpId,_tmpEmailAddress,_tmpDisplayName,_tmpImapHost,_tmpImapPort,_tmpSmtpHost,_tmpSmtpPort,_tmpUseSsl,_tmpPassword,_tmpOauthToken,_tmpRefreshToken,_tmpTokenType,_tmpLastSyncTime,_tmpIsActive,_tmpConnectionState,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super AccountEntity> $completion) {
    final String _sql = "SELECT * FROM accounts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AccountEntity>() {
      @Override
      @Nullable
      public AccountEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "email_address");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfImapHost = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_host");
          final int _cursorIndexOfImapPort = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_port");
          final int _cursorIndexOfSmtpHost = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_host");
          final int _cursorIndexOfSmtpPort = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_port");
          final int _cursorIndexOfUseSsl = CursorUtil.getColumnIndexOrThrow(_cursor, "use_ssl");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfOauthToken = CursorUtil.getColumnIndexOrThrow(_cursor, "oauth_token");
          final int _cursorIndexOfRefreshToken = CursorUtil.getColumnIndexOrThrow(_cursor, "refresh_token");
          final int _cursorIndexOfTokenType = CursorUtil.getColumnIndexOrThrow(_cursor, "token_type");
          final int _cursorIndexOfLastSyncTime = CursorUtil.getColumnIndexOrThrow(_cursor, "last_sync_time");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfConnectionState = CursorUtil.getColumnIndexOrThrow(_cursor, "connection_state");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final AccountEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEmailAddress;
            _tmpEmailAddress = _cursor.getString(_cursorIndexOfEmailAddress);
            final String _tmpDisplayName;
            _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            final String _tmpImapHost;
            _tmpImapHost = _cursor.getString(_cursorIndexOfImapHost);
            final int _tmpImapPort;
            _tmpImapPort = _cursor.getInt(_cursorIndexOfImapPort);
            final String _tmpSmtpHost;
            _tmpSmtpHost = _cursor.getString(_cursorIndexOfSmtpHost);
            final int _tmpSmtpPort;
            _tmpSmtpPort = _cursor.getInt(_cursorIndexOfSmtpPort);
            final boolean _tmpUseSsl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseSsl);
            _tmpUseSsl = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpOauthToken;
            if (_cursor.isNull(_cursorIndexOfOauthToken)) {
              _tmpOauthToken = null;
            } else {
              _tmpOauthToken = _cursor.getString(_cursorIndexOfOauthToken);
            }
            final String _tmpRefreshToken;
            if (_cursor.isNull(_cursorIndexOfRefreshToken)) {
              _tmpRefreshToken = null;
            } else {
              _tmpRefreshToken = _cursor.getString(_cursorIndexOfRefreshToken);
            }
            final String _tmpTokenType;
            if (_cursor.isNull(_cursorIndexOfTokenType)) {
              _tmpTokenType = null;
            } else {
              _tmpTokenType = _cursor.getString(_cursorIndexOfTokenType);
            }
            final long _tmpLastSyncTime;
            _tmpLastSyncTime = _cursor.getLong(_cursorIndexOfLastSyncTime);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final String _tmpConnectionState;
            _tmpConnectionState = _cursor.getString(_cursorIndexOfConnectionState);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new AccountEntity(_tmpId,_tmpEmailAddress,_tmpDisplayName,_tmpImapHost,_tmpImapPort,_tmpSmtpHost,_tmpSmtpPort,_tmpUseSsl,_tmpPassword,_tmpOauthToken,_tmpRefreshToken,_tmpTokenType,_tmpLastSyncTime,_tmpIsActive,_tmpConnectionState,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByEmail(final String email,
      final Continuation<? super AccountEntity> $completion) {
    final String _sql = "SELECT * FROM accounts WHERE email_address = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AccountEntity>() {
      @Override
      @Nullable
      public AccountEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "email_address");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfImapHost = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_host");
          final int _cursorIndexOfImapPort = CursorUtil.getColumnIndexOrThrow(_cursor, "imap_port");
          final int _cursorIndexOfSmtpHost = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_host");
          final int _cursorIndexOfSmtpPort = CursorUtil.getColumnIndexOrThrow(_cursor, "smtp_port");
          final int _cursorIndexOfUseSsl = CursorUtil.getColumnIndexOrThrow(_cursor, "use_ssl");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfOauthToken = CursorUtil.getColumnIndexOrThrow(_cursor, "oauth_token");
          final int _cursorIndexOfRefreshToken = CursorUtil.getColumnIndexOrThrow(_cursor, "refresh_token");
          final int _cursorIndexOfTokenType = CursorUtil.getColumnIndexOrThrow(_cursor, "token_type");
          final int _cursorIndexOfLastSyncTime = CursorUtil.getColumnIndexOrThrow(_cursor, "last_sync_time");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfConnectionState = CursorUtil.getColumnIndexOrThrow(_cursor, "connection_state");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final AccountEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEmailAddress;
            _tmpEmailAddress = _cursor.getString(_cursorIndexOfEmailAddress);
            final String _tmpDisplayName;
            _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            final String _tmpImapHost;
            _tmpImapHost = _cursor.getString(_cursorIndexOfImapHost);
            final int _tmpImapPort;
            _tmpImapPort = _cursor.getInt(_cursorIndexOfImapPort);
            final String _tmpSmtpHost;
            _tmpSmtpHost = _cursor.getString(_cursorIndexOfSmtpHost);
            final int _tmpSmtpPort;
            _tmpSmtpPort = _cursor.getInt(_cursorIndexOfSmtpPort);
            final boolean _tmpUseSsl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseSsl);
            _tmpUseSsl = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpOauthToken;
            if (_cursor.isNull(_cursorIndexOfOauthToken)) {
              _tmpOauthToken = null;
            } else {
              _tmpOauthToken = _cursor.getString(_cursorIndexOfOauthToken);
            }
            final String _tmpRefreshToken;
            if (_cursor.isNull(_cursorIndexOfRefreshToken)) {
              _tmpRefreshToken = null;
            } else {
              _tmpRefreshToken = _cursor.getString(_cursorIndexOfRefreshToken);
            }
            final String _tmpTokenType;
            if (_cursor.isNull(_cursorIndexOfTokenType)) {
              _tmpTokenType = null;
            } else {
              _tmpTokenType = _cursor.getString(_cursorIndexOfTokenType);
            }
            final long _tmpLastSyncTime;
            _tmpLastSyncTime = _cursor.getLong(_cursorIndexOfLastSyncTime);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final String _tmpConnectionState;
            _tmpConnectionState = _cursor.getString(_cursorIndexOfConnectionState);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new AccountEntity(_tmpId,_tmpEmailAddress,_tmpDisplayName,_tmpImapHost,_tmpImapPort,_tmpSmtpHost,_tmpSmtpPort,_tmpUseSsl,_tmpPassword,_tmpOauthToken,_tmpRefreshToken,_tmpTokenType,_tmpLastSyncTime,_tmpIsActive,_tmpConnectionState,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM accounts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
