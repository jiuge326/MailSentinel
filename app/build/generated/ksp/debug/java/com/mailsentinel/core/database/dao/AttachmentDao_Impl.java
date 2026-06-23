package com.mailsentinel.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mailsentinel.core.database.entity.AttachmentEntity;
import java.lang.Class;
import java.lang.Exception;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AttachmentDao_Impl implements AttachmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AttachmentEntity> __insertionAdapterOfAttachmentEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOcrResult;

  public AttachmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttachmentEntity = new EntityInsertionAdapter<AttachmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `attachments` (`id`,`message_id`,`filename`,`content_type`,`size`,`local_path`,`is_downloaded`,`ocr_result`,`ocr_processed`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttachmentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getMessageId());
        statement.bindString(3, entity.getFilename());
        statement.bindString(4, entity.getContentType());
        statement.bindLong(5, entity.getSize());
        if (entity.getLocalPath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLocalPath());
        }
        final int _tmp = entity.isDownloaded() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getOcrResult() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getOcrResult());
        }
        final int _tmp_1 = entity.getOcrProcessed() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
      }
    };
    this.__preparedStmtOfUpdateOcrResult = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE attachments SET ocr_result = ?, ocr_processed = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<AttachmentEntity> attachments,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttachmentEntity.insert(attachments);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOcrResult(final long id, final String result,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOcrResult.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, result);
        _argIndex = 2;
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
          __preparedStmtOfUpdateOcrResult.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getByMessage(final long messageId,
      final Continuation<? super List<AttachmentEntity>> $completion) {
    final String _sql = "SELECT * FROM attachments WHERE message_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, messageId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AttachmentEntity>>() {
      @Override
      @NonNull
      public List<AttachmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfContentType = CursorUtil.getColumnIndexOrThrow(_cursor, "content_type");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "local_path");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "is_downloaded");
          final int _cursorIndexOfOcrResult = CursorUtil.getColumnIndexOrThrow(_cursor, "ocr_result");
          final int _cursorIndexOfOcrProcessed = CursorUtil.getColumnIndexOrThrow(_cursor, "ocr_processed");
          final List<AttachmentEntity> _result = new ArrayList<AttachmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AttachmentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpMessageId;
            _tmpMessageId = _cursor.getLong(_cursorIndexOfMessageId);
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final String _tmpContentType;
            _tmpContentType = _cursor.getString(_cursorIndexOfContentType);
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final boolean _tmpIsDownloaded;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp != 0;
            final String _tmpOcrResult;
            if (_cursor.isNull(_cursorIndexOfOcrResult)) {
              _tmpOcrResult = null;
            } else {
              _tmpOcrResult = _cursor.getString(_cursorIndexOfOcrResult);
            }
            final boolean _tmpOcrProcessed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfOcrProcessed);
            _tmpOcrProcessed = _tmp_1 != 0;
            _item = new AttachmentEntity(_tmpId,_tmpMessageId,_tmpFilename,_tmpContentType,_tmpSize,_tmpLocalPath,_tmpIsDownloaded,_tmpOcrResult,_tmpOcrProcessed);
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
  public Object getPendingOcrImages(
      final Continuation<? super List<AttachmentEntity>> $completion) {
    final String _sql = "SELECT * FROM attachments WHERE ocr_processed = 0 AND content_type LIKE 'image%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AttachmentEntity>>() {
      @Override
      @NonNull
      public List<AttachmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfContentType = CursorUtil.getColumnIndexOrThrow(_cursor, "content_type");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "local_path");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "is_downloaded");
          final int _cursorIndexOfOcrResult = CursorUtil.getColumnIndexOrThrow(_cursor, "ocr_result");
          final int _cursorIndexOfOcrProcessed = CursorUtil.getColumnIndexOrThrow(_cursor, "ocr_processed");
          final List<AttachmentEntity> _result = new ArrayList<AttachmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AttachmentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpMessageId;
            _tmpMessageId = _cursor.getLong(_cursorIndexOfMessageId);
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final String _tmpContentType;
            _tmpContentType = _cursor.getString(_cursorIndexOfContentType);
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final boolean _tmpIsDownloaded;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp != 0;
            final String _tmpOcrResult;
            if (_cursor.isNull(_cursorIndexOfOcrResult)) {
              _tmpOcrResult = null;
            } else {
              _tmpOcrResult = _cursor.getString(_cursorIndexOfOcrResult);
            }
            final boolean _tmpOcrProcessed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfOcrProcessed);
            _tmpOcrProcessed = _tmp_1 != 0;
            _item = new AttachmentEntity(_tmpId,_tmpMessageId,_tmpFilename,_tmpContentType,_tmpSize,_tmpLocalPath,_tmpIsDownloaded,_tmpOcrResult,_tmpOcrProcessed);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
