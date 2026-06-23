package com.mailsentinel.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mailsentinel.core.database.entity.OcrResultEntity;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OcrResultDao_Impl implements OcrResultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OcrResultEntity> __insertionAdapterOfOcrResultEntity;

  public OcrResultDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOcrResultEntity = new EntityInsertionAdapter<OcrResultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ocr_results` (`id`,`attachment_id`,`message_id`,`recognized_text`,`language`,`confidence`,`source_type`,`processed_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OcrResultEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getAttachmentId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getAttachmentId());
        }
        if (entity.getMessageId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getMessageId());
        }
        statement.bindString(4, entity.getRecognizedText());
        statement.bindString(5, entity.getLanguage());
        statement.bindDouble(6, entity.getConfidence());
        statement.bindString(7, entity.getSourceType());
        statement.bindLong(8, entity.getProcessedAt());
      }
    };
  }

  @Override
  public Object insert(final OcrResultEntity result, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOcrResultEntity.insert(result);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByAttachment(final long attachmentId,
      final Continuation<? super OcrResultEntity> $completion) {
    final String _sql = "SELECT * FROM ocr_results WHERE attachment_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, attachmentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<OcrResultEntity>() {
      @Override
      @Nullable
      public OcrResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAttachmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment_id");
          final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfRecognizedText = CursorUtil.getColumnIndexOrThrow(_cursor, "recognized_text");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "source_type");
          final int _cursorIndexOfProcessedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "processed_at");
          final OcrResultEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpAttachmentId;
            if (_cursor.isNull(_cursorIndexOfAttachmentId)) {
              _tmpAttachmentId = null;
            } else {
              _tmpAttachmentId = _cursor.getLong(_cursorIndexOfAttachmentId);
            }
            final Long _tmpMessageId;
            if (_cursor.isNull(_cursorIndexOfMessageId)) {
              _tmpMessageId = null;
            } else {
              _tmpMessageId = _cursor.getLong(_cursorIndexOfMessageId);
            }
            final String _tmpRecognizedText;
            _tmpRecognizedText = _cursor.getString(_cursorIndexOfRecognizedText);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final float _tmpConfidence;
            _tmpConfidence = _cursor.getFloat(_cursorIndexOfConfidence);
            final String _tmpSourceType;
            _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            final long _tmpProcessedAt;
            _tmpProcessedAt = _cursor.getLong(_cursorIndexOfProcessedAt);
            _result = new OcrResultEntity(_tmpId,_tmpAttachmentId,_tmpMessageId,_tmpRecognizedText,_tmpLanguage,_tmpConfidence,_tmpSourceType,_tmpProcessedAt);
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
  public Object getByMessage(final long messageId,
      final Continuation<? super List<OcrResultEntity>> $completion) {
    final String _sql = "SELECT * FROM ocr_results WHERE message_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, messageId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<OcrResultEntity>>() {
      @Override
      @NonNull
      public List<OcrResultEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAttachmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "attachment_id");
          final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfRecognizedText = CursorUtil.getColumnIndexOrThrow(_cursor, "recognized_text");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "source_type");
          final int _cursorIndexOfProcessedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "processed_at");
          final List<OcrResultEntity> _result = new ArrayList<OcrResultEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OcrResultEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpAttachmentId;
            if (_cursor.isNull(_cursorIndexOfAttachmentId)) {
              _tmpAttachmentId = null;
            } else {
              _tmpAttachmentId = _cursor.getLong(_cursorIndexOfAttachmentId);
            }
            final Long _tmpMessageId;
            if (_cursor.isNull(_cursorIndexOfMessageId)) {
              _tmpMessageId = null;
            } else {
              _tmpMessageId = _cursor.getLong(_cursorIndexOfMessageId);
            }
            final String _tmpRecognizedText;
            _tmpRecognizedText = _cursor.getString(_cursorIndexOfRecognizedText);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final float _tmpConfidence;
            _tmpConfidence = _cursor.getFloat(_cursorIndexOfConfidence);
            final String _tmpSourceType;
            _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            final long _tmpProcessedAt;
            _tmpProcessedAt = _cursor.getLong(_cursorIndexOfProcessedAt);
            _item = new OcrResultEntity(_tmpId,_tmpAttachmentId,_tmpMessageId,_tmpRecognizedText,_tmpLanguage,_tmpConfidence,_tmpSourceType,_tmpProcessedAt);
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
