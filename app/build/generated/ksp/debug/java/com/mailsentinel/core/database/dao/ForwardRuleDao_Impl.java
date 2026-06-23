package com.mailsentinel.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mailsentinel.core.database.entity.ForwardRuleEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class ForwardRuleDao_Impl implements ForwardRuleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ForwardRuleEntity> __insertionAdapterOfForwardRuleEntity;

  private final EntityDeletionOrUpdateAdapter<ForwardRuleEntity> __updateAdapterOfForwardRuleEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public ForwardRuleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfForwardRuleEntity = new EntityInsertionAdapter<ForwardRuleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `forward_rules` (`id`,`account_id`,`name`,`regex_pattern`,`regex_target`,`js_script`,`target_address`,`include_ocr`,`is_active`,`priority`,`created_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ForwardRuleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        statement.bindString(3, entity.getName());
        if (entity.getRegexPattern() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRegexPattern());
        }
        statement.bindString(5, entity.getRegexTarget());
        if (entity.getJsScript() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getJsScript());
        }
        statement.bindString(7, entity.getTargetAddress());
        final int _tmp = entity.getIncludeOcr() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getPriority());
        statement.bindLong(11, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfForwardRuleEntity = new EntityDeletionOrUpdateAdapter<ForwardRuleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `forward_rules` SET `id` = ?,`account_id` = ?,`name` = ?,`regex_pattern` = ?,`regex_target` = ?,`js_script` = ?,`target_address` = ?,`include_ocr` = ?,`is_active` = ?,`priority` = ?,`created_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ForwardRuleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        statement.bindString(3, entity.getName());
        if (entity.getRegexPattern() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRegexPattern());
        }
        statement.bindString(5, entity.getRegexTarget());
        if (entity.getJsScript() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getJsScript());
        }
        statement.bindString(7, entity.getTargetAddress());
        final int _tmp = entity.getIncludeOcr() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getPriority());
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM forward_rules WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ForwardRuleEntity rule, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfForwardRuleEntity.insertAndReturnId(rule);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ForwardRuleEntity rule, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfForwardRuleEntity.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
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
  public Object getActiveByAccount(final long accountId,
      final Continuation<? super List<ForwardRuleEntity>> $completion) {
    final String _sql = "SELECT * FROM forward_rules WHERE account_id = ? AND is_active = 1 ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ForwardRuleEntity>>() {
      @Override
      @NonNull
      public List<ForwardRuleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRegexPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "regex_pattern");
          final int _cursorIndexOfRegexTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "regex_target");
          final int _cursorIndexOfJsScript = CursorUtil.getColumnIndexOrThrow(_cursor, "js_script");
          final int _cursorIndexOfTargetAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "target_address");
          final int _cursorIndexOfIncludeOcr = CursorUtil.getColumnIndexOrThrow(_cursor, "include_ocr");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<ForwardRuleEntity> _result = new ArrayList<ForwardRuleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ForwardRuleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRegexPattern;
            if (_cursor.isNull(_cursorIndexOfRegexPattern)) {
              _tmpRegexPattern = null;
            } else {
              _tmpRegexPattern = _cursor.getString(_cursorIndexOfRegexPattern);
            }
            final String _tmpRegexTarget;
            _tmpRegexTarget = _cursor.getString(_cursorIndexOfRegexTarget);
            final String _tmpJsScript;
            if (_cursor.isNull(_cursorIndexOfJsScript)) {
              _tmpJsScript = null;
            } else {
              _tmpJsScript = _cursor.getString(_cursorIndexOfJsScript);
            }
            final String _tmpTargetAddress;
            _tmpTargetAddress = _cursor.getString(_cursorIndexOfTargetAddress);
            final boolean _tmpIncludeOcr;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIncludeOcr);
            _tmpIncludeOcr = _tmp != 0;
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ForwardRuleEntity(_tmpId,_tmpAccountId,_tmpName,_tmpRegexPattern,_tmpRegexTarget,_tmpJsScript,_tmpTargetAddress,_tmpIncludeOcr,_tmpIsActive,_tmpPriority,_tmpCreatedAt);
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
  public Flow<List<ForwardRuleEntity>> getAll() {
    final String _sql = "SELECT * FROM forward_rules ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"forward_rules"}, new Callable<List<ForwardRuleEntity>>() {
      @Override
      @NonNull
      public List<ForwardRuleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRegexPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "regex_pattern");
          final int _cursorIndexOfRegexTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "regex_target");
          final int _cursorIndexOfJsScript = CursorUtil.getColumnIndexOrThrow(_cursor, "js_script");
          final int _cursorIndexOfTargetAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "target_address");
          final int _cursorIndexOfIncludeOcr = CursorUtil.getColumnIndexOrThrow(_cursor, "include_ocr");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<ForwardRuleEntity> _result = new ArrayList<ForwardRuleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ForwardRuleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRegexPattern;
            if (_cursor.isNull(_cursorIndexOfRegexPattern)) {
              _tmpRegexPattern = null;
            } else {
              _tmpRegexPattern = _cursor.getString(_cursorIndexOfRegexPattern);
            }
            final String _tmpRegexTarget;
            _tmpRegexTarget = _cursor.getString(_cursorIndexOfRegexTarget);
            final String _tmpJsScript;
            if (_cursor.isNull(_cursorIndexOfJsScript)) {
              _tmpJsScript = null;
            } else {
              _tmpJsScript = _cursor.getString(_cursorIndexOfJsScript);
            }
            final String _tmpTargetAddress;
            _tmpTargetAddress = _cursor.getString(_cursorIndexOfTargetAddress);
            final boolean _tmpIncludeOcr;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIncludeOcr);
            _tmpIncludeOcr = _tmp != 0;
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ForwardRuleEntity(_tmpId,_tmpAccountId,_tmpName,_tmpRegexPattern,_tmpRegexTarget,_tmpJsScript,_tmpTargetAddress,_tmpIncludeOcr,_tmpIsActive,_tmpPriority,_tmpCreatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
