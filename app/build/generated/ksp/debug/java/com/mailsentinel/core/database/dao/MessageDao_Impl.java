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
import com.mailsentinel.core.database.entity.MessageEntity;
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
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MessageEntity> __insertionAdapterOfMessageEntity;

  private final EntityDeletionOrUpdateAdapter<MessageEntity> __updateAdapterOfMessageEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsRead;

  private final SharedSQLiteStatement __preparedStmtOfSetStarred;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByUid;

  private final SharedSQLiteStatement __preparedStmtOfPurgeOldRead;

  public MessageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessageEntity = new EntityInsertionAdapter<MessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `messages` (`id`,`account_id`,`folder_id`,`uid`,`message_id_header`,`subject`,`from_address`,`from_name`,`to_addresses`,`cc_addresses`,`received_date`,`sent_date`,`is_read`,`is_starred`,`has_attachments`,`preview_text`,`body_html`,`body_text`,`size`,`flags`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MessageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        statement.bindLong(3, entity.getFolderId());
        statement.bindLong(4, entity.getUid());
        if (entity.getMessageIdHeader() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMessageIdHeader());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSubject());
        }
        statement.bindString(7, entity.getFromAddress());
        if (entity.getFromName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getFromName());
        }
        statement.bindString(9, entity.getToAddresses());
        if (entity.getCcAddresses() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCcAddresses());
        }
        statement.bindLong(11, entity.getReceivedDate());
        if (entity.getSentDate() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getSentDate());
        }
        final int _tmp = entity.isRead() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isStarred() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        final int _tmp_2 = entity.getHasAttachments() ? 1 : 0;
        statement.bindLong(15, _tmp_2);
        if (entity.getPreviewText() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getPreviewText());
        }
        if (entity.getBodyHtml() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getBodyHtml());
        }
        if (entity.getBodyText() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getBodyText());
        }
        statement.bindLong(19, entity.getSize());
        if (entity.getFlags() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getFlags());
        }
      }
    };
    this.__updateAdapterOfMessageEntity = new EntityDeletionOrUpdateAdapter<MessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `messages` SET `id` = ?,`account_id` = ?,`folder_id` = ?,`uid` = ?,`message_id_header` = ?,`subject` = ?,`from_address` = ?,`from_name` = ?,`to_addresses` = ?,`cc_addresses` = ?,`received_date` = ?,`sent_date` = ?,`is_read` = ?,`is_starred` = ?,`has_attachments` = ?,`preview_text` = ?,`body_html` = ?,`body_text` = ?,`size` = ?,`flags` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MessageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        statement.bindLong(3, entity.getFolderId());
        statement.bindLong(4, entity.getUid());
        if (entity.getMessageIdHeader() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMessageIdHeader());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSubject());
        }
        statement.bindString(7, entity.getFromAddress());
        if (entity.getFromName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getFromName());
        }
        statement.bindString(9, entity.getToAddresses());
        if (entity.getCcAddresses() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCcAddresses());
        }
        statement.bindLong(11, entity.getReceivedDate());
        if (entity.getSentDate() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getSentDate());
        }
        final int _tmp = entity.isRead() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isStarred() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        final int _tmp_2 = entity.getHasAttachments() ? 1 : 0;
        statement.bindLong(15, _tmp_2);
        if (entity.getPreviewText() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getPreviewText());
        }
        if (entity.getBodyHtml() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getBodyHtml());
        }
        if (entity.getBodyText() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getBodyText());
        }
        statement.bindLong(19, entity.getSize());
        if (entity.getFlags() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getFlags());
        }
        statement.bindLong(21, entity.getId());
      }
    };
    this.__preparedStmtOfMarkAsRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET is_read = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetStarred = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET is_starred = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByUid = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE account_id = ? AND folder_id = ? AND uid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfPurgeOldRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE received_date < ? AND is_read = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<MessageEntity> messages,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMessageEntity.insert(messages);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final MessageEntity message, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMessageEntity.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsRead(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsRead.acquire();
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
          __preparedStmtOfMarkAsRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setStarred(final long id, final boolean starred,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetStarred.acquire();
        int _argIndex = 1;
        final int _tmp = starred ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfSetStarred.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByUid(final long accountId, final long folderId, final long uid,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByUid.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, accountId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, folderId);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, uid);
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
          __preparedStmtOfDeleteByUid.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object purgeOldRead(final long beforeTimestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfPurgeOldRead.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, beforeTimestamp);
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
          __preparedStmtOfPurgeOldRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getMessages(final long accountId, final long folderId, final int limit,
      final int offset, final Continuation<? super List<MessageEntity>> $completion) {
    final String _sql = "SELECT * FROM messages WHERE account_id = ? AND folder_id = ? ORDER BY received_date DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, folderId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 4;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MessageEntity>>() {
      @Override
      @NonNull
      public List<MessageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfMessageIdHeader = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id_header");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfFromAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "from_address");
          final int _cursorIndexOfFromName = CursorUtil.getColumnIndexOrThrow(_cursor, "from_name");
          final int _cursorIndexOfToAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "to_addresses");
          final int _cursorIndexOfCcAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "cc_addresses");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "received_date");
          final int _cursorIndexOfSentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sent_date");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "is_read");
          final int _cursorIndexOfIsStarred = CursorUtil.getColumnIndexOrThrow(_cursor, "is_starred");
          final int _cursorIndexOfHasAttachments = CursorUtil.getColumnIndexOrThrow(_cursor, "has_attachments");
          final int _cursorIndexOfPreviewText = CursorUtil.getColumnIndexOrThrow(_cursor, "preview_text");
          final int _cursorIndexOfBodyHtml = CursorUtil.getColumnIndexOrThrow(_cursor, "body_html");
          final int _cursorIndexOfBodyText = CursorUtil.getColumnIndexOrThrow(_cursor, "body_text");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "flags");
          final List<MessageEntity> _result = new ArrayList<MessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MessageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final long _tmpFolderId;
            _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            final String _tmpMessageIdHeader;
            if (_cursor.isNull(_cursorIndexOfMessageIdHeader)) {
              _tmpMessageIdHeader = null;
            } else {
              _tmpMessageIdHeader = _cursor.getString(_cursorIndexOfMessageIdHeader);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpFromAddress;
            _tmpFromAddress = _cursor.getString(_cursorIndexOfFromAddress);
            final String _tmpFromName;
            if (_cursor.isNull(_cursorIndexOfFromName)) {
              _tmpFromName = null;
            } else {
              _tmpFromName = _cursor.getString(_cursorIndexOfFromName);
            }
            final String _tmpToAddresses;
            _tmpToAddresses = _cursor.getString(_cursorIndexOfToAddresses);
            final String _tmpCcAddresses;
            if (_cursor.isNull(_cursorIndexOfCcAddresses)) {
              _tmpCcAddresses = null;
            } else {
              _tmpCcAddresses = _cursor.getString(_cursorIndexOfCcAddresses);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpSentDate;
            if (_cursor.isNull(_cursorIndexOfSentDate)) {
              _tmpSentDate = null;
            } else {
              _tmpSentDate = _cursor.getLong(_cursorIndexOfSentDate);
            }
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsStarred;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsStarred);
            _tmpIsStarred = _tmp_1 != 0;
            final boolean _tmpHasAttachments;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAttachments);
            _tmpHasAttachments = _tmp_2 != 0;
            final String _tmpPreviewText;
            if (_cursor.isNull(_cursorIndexOfPreviewText)) {
              _tmpPreviewText = null;
            } else {
              _tmpPreviewText = _cursor.getString(_cursorIndexOfPreviewText);
            }
            final String _tmpBodyHtml;
            if (_cursor.isNull(_cursorIndexOfBodyHtml)) {
              _tmpBodyHtml = null;
            } else {
              _tmpBodyHtml = _cursor.getString(_cursorIndexOfBodyHtml);
            }
            final String _tmpBodyText;
            if (_cursor.isNull(_cursorIndexOfBodyText)) {
              _tmpBodyText = null;
            } else {
              _tmpBodyText = _cursor.getString(_cursorIndexOfBodyText);
            }
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpFlags;
            if (_cursor.isNull(_cursorIndexOfFlags)) {
              _tmpFlags = null;
            } else {
              _tmpFlags = _cursor.getString(_cursorIndexOfFlags);
            }
            _item = new MessageEntity(_tmpId,_tmpAccountId,_tmpFolderId,_tmpUid,_tmpMessageIdHeader,_tmpSubject,_tmpFromAddress,_tmpFromName,_tmpToAddresses,_tmpCcAddresses,_tmpReceivedDate,_tmpSentDate,_tmpIsRead,_tmpIsStarred,_tmpHasAttachments,_tmpPreviewText,_tmpBodyHtml,_tmpBodyText,_tmpSize,_tmpFlags);
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
  public Flow<List<MessageEntity>> getMessagesFlow(final long accountId, final long folderId) {
    final String _sql = "SELECT * FROM messages WHERE account_id = ? AND folder_id = ? ORDER BY received_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, folderId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"messages"}, new Callable<List<MessageEntity>>() {
      @Override
      @NonNull
      public List<MessageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfMessageIdHeader = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id_header");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfFromAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "from_address");
          final int _cursorIndexOfFromName = CursorUtil.getColumnIndexOrThrow(_cursor, "from_name");
          final int _cursorIndexOfToAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "to_addresses");
          final int _cursorIndexOfCcAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "cc_addresses");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "received_date");
          final int _cursorIndexOfSentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sent_date");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "is_read");
          final int _cursorIndexOfIsStarred = CursorUtil.getColumnIndexOrThrow(_cursor, "is_starred");
          final int _cursorIndexOfHasAttachments = CursorUtil.getColumnIndexOrThrow(_cursor, "has_attachments");
          final int _cursorIndexOfPreviewText = CursorUtil.getColumnIndexOrThrow(_cursor, "preview_text");
          final int _cursorIndexOfBodyHtml = CursorUtil.getColumnIndexOrThrow(_cursor, "body_html");
          final int _cursorIndexOfBodyText = CursorUtil.getColumnIndexOrThrow(_cursor, "body_text");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "flags");
          final List<MessageEntity> _result = new ArrayList<MessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MessageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final long _tmpFolderId;
            _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            final String _tmpMessageIdHeader;
            if (_cursor.isNull(_cursorIndexOfMessageIdHeader)) {
              _tmpMessageIdHeader = null;
            } else {
              _tmpMessageIdHeader = _cursor.getString(_cursorIndexOfMessageIdHeader);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpFromAddress;
            _tmpFromAddress = _cursor.getString(_cursorIndexOfFromAddress);
            final String _tmpFromName;
            if (_cursor.isNull(_cursorIndexOfFromName)) {
              _tmpFromName = null;
            } else {
              _tmpFromName = _cursor.getString(_cursorIndexOfFromName);
            }
            final String _tmpToAddresses;
            _tmpToAddresses = _cursor.getString(_cursorIndexOfToAddresses);
            final String _tmpCcAddresses;
            if (_cursor.isNull(_cursorIndexOfCcAddresses)) {
              _tmpCcAddresses = null;
            } else {
              _tmpCcAddresses = _cursor.getString(_cursorIndexOfCcAddresses);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpSentDate;
            if (_cursor.isNull(_cursorIndexOfSentDate)) {
              _tmpSentDate = null;
            } else {
              _tmpSentDate = _cursor.getLong(_cursorIndexOfSentDate);
            }
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsStarred;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsStarred);
            _tmpIsStarred = _tmp_1 != 0;
            final boolean _tmpHasAttachments;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAttachments);
            _tmpHasAttachments = _tmp_2 != 0;
            final String _tmpPreviewText;
            if (_cursor.isNull(_cursorIndexOfPreviewText)) {
              _tmpPreviewText = null;
            } else {
              _tmpPreviewText = _cursor.getString(_cursorIndexOfPreviewText);
            }
            final String _tmpBodyHtml;
            if (_cursor.isNull(_cursorIndexOfBodyHtml)) {
              _tmpBodyHtml = null;
            } else {
              _tmpBodyHtml = _cursor.getString(_cursorIndexOfBodyHtml);
            }
            final String _tmpBodyText;
            if (_cursor.isNull(_cursorIndexOfBodyText)) {
              _tmpBodyText = null;
            } else {
              _tmpBodyText = _cursor.getString(_cursorIndexOfBodyText);
            }
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpFlags;
            if (_cursor.isNull(_cursorIndexOfFlags)) {
              _tmpFlags = null;
            } else {
              _tmpFlags = _cursor.getString(_cursorIndexOfFlags);
            }
            _item = new MessageEntity(_tmpId,_tmpAccountId,_tmpFolderId,_tmpUid,_tmpMessageIdHeader,_tmpSubject,_tmpFromAddress,_tmpFromName,_tmpToAddresses,_tmpCcAddresses,_tmpReceivedDate,_tmpSentDate,_tmpIsRead,_tmpIsStarred,_tmpHasAttachments,_tmpPreviewText,_tmpBodyHtml,_tmpBodyText,_tmpSize,_tmpFlags);
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
  public Object getByUid(final long uid, final long folderId, final long accountId,
      final Continuation<? super MessageEntity> $completion) {
    final String _sql = "SELECT * FROM messages WHERE uid = ? AND folder_id = ? AND account_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, uid);
    _argIndex = 2;
    _statement.bindLong(_argIndex, folderId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, accountId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MessageEntity>() {
      @Override
      @Nullable
      public MessageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfMessageIdHeader = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id_header");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfFromAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "from_address");
          final int _cursorIndexOfFromName = CursorUtil.getColumnIndexOrThrow(_cursor, "from_name");
          final int _cursorIndexOfToAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "to_addresses");
          final int _cursorIndexOfCcAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "cc_addresses");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "received_date");
          final int _cursorIndexOfSentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sent_date");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "is_read");
          final int _cursorIndexOfIsStarred = CursorUtil.getColumnIndexOrThrow(_cursor, "is_starred");
          final int _cursorIndexOfHasAttachments = CursorUtil.getColumnIndexOrThrow(_cursor, "has_attachments");
          final int _cursorIndexOfPreviewText = CursorUtil.getColumnIndexOrThrow(_cursor, "preview_text");
          final int _cursorIndexOfBodyHtml = CursorUtil.getColumnIndexOrThrow(_cursor, "body_html");
          final int _cursorIndexOfBodyText = CursorUtil.getColumnIndexOrThrow(_cursor, "body_text");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "flags");
          final MessageEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final long _tmpFolderId;
            _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            final String _tmpMessageIdHeader;
            if (_cursor.isNull(_cursorIndexOfMessageIdHeader)) {
              _tmpMessageIdHeader = null;
            } else {
              _tmpMessageIdHeader = _cursor.getString(_cursorIndexOfMessageIdHeader);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpFromAddress;
            _tmpFromAddress = _cursor.getString(_cursorIndexOfFromAddress);
            final String _tmpFromName;
            if (_cursor.isNull(_cursorIndexOfFromName)) {
              _tmpFromName = null;
            } else {
              _tmpFromName = _cursor.getString(_cursorIndexOfFromName);
            }
            final String _tmpToAddresses;
            _tmpToAddresses = _cursor.getString(_cursorIndexOfToAddresses);
            final String _tmpCcAddresses;
            if (_cursor.isNull(_cursorIndexOfCcAddresses)) {
              _tmpCcAddresses = null;
            } else {
              _tmpCcAddresses = _cursor.getString(_cursorIndexOfCcAddresses);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpSentDate;
            if (_cursor.isNull(_cursorIndexOfSentDate)) {
              _tmpSentDate = null;
            } else {
              _tmpSentDate = _cursor.getLong(_cursorIndexOfSentDate);
            }
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsStarred;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsStarred);
            _tmpIsStarred = _tmp_1 != 0;
            final boolean _tmpHasAttachments;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAttachments);
            _tmpHasAttachments = _tmp_2 != 0;
            final String _tmpPreviewText;
            if (_cursor.isNull(_cursorIndexOfPreviewText)) {
              _tmpPreviewText = null;
            } else {
              _tmpPreviewText = _cursor.getString(_cursorIndexOfPreviewText);
            }
            final String _tmpBodyHtml;
            if (_cursor.isNull(_cursorIndexOfBodyHtml)) {
              _tmpBodyHtml = null;
            } else {
              _tmpBodyHtml = _cursor.getString(_cursorIndexOfBodyHtml);
            }
            final String _tmpBodyText;
            if (_cursor.isNull(_cursorIndexOfBodyText)) {
              _tmpBodyText = null;
            } else {
              _tmpBodyText = _cursor.getString(_cursorIndexOfBodyText);
            }
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpFlags;
            if (_cursor.isNull(_cursorIndexOfFlags)) {
              _tmpFlags = null;
            } else {
              _tmpFlags = _cursor.getString(_cursorIndexOfFlags);
            }
            _result = new MessageEntity(_tmpId,_tmpAccountId,_tmpFolderId,_tmpUid,_tmpMessageIdHeader,_tmpSubject,_tmpFromAddress,_tmpFromName,_tmpToAddresses,_tmpCcAddresses,_tmpReceivedDate,_tmpSentDate,_tmpIsRead,_tmpIsStarred,_tmpHasAttachments,_tmpPreviewText,_tmpBodyHtml,_tmpBodyText,_tmpSize,_tmpFlags);
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
  public Object getById(final long id, final Continuation<? super MessageEntity> $completion) {
    final String _sql = "SELECT * FROM messages WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MessageEntity>() {
      @Override
      @Nullable
      public MessageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfMessageIdHeader = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id_header");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfFromAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "from_address");
          final int _cursorIndexOfFromName = CursorUtil.getColumnIndexOrThrow(_cursor, "from_name");
          final int _cursorIndexOfToAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "to_addresses");
          final int _cursorIndexOfCcAddresses = CursorUtil.getColumnIndexOrThrow(_cursor, "cc_addresses");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "received_date");
          final int _cursorIndexOfSentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sent_date");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "is_read");
          final int _cursorIndexOfIsStarred = CursorUtil.getColumnIndexOrThrow(_cursor, "is_starred");
          final int _cursorIndexOfHasAttachments = CursorUtil.getColumnIndexOrThrow(_cursor, "has_attachments");
          final int _cursorIndexOfPreviewText = CursorUtil.getColumnIndexOrThrow(_cursor, "preview_text");
          final int _cursorIndexOfBodyHtml = CursorUtil.getColumnIndexOrThrow(_cursor, "body_html");
          final int _cursorIndexOfBodyText = CursorUtil.getColumnIndexOrThrow(_cursor, "body_text");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "flags");
          final MessageEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final long _tmpFolderId;
            _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            final String _tmpMessageIdHeader;
            if (_cursor.isNull(_cursorIndexOfMessageIdHeader)) {
              _tmpMessageIdHeader = null;
            } else {
              _tmpMessageIdHeader = _cursor.getString(_cursorIndexOfMessageIdHeader);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpFromAddress;
            _tmpFromAddress = _cursor.getString(_cursorIndexOfFromAddress);
            final String _tmpFromName;
            if (_cursor.isNull(_cursorIndexOfFromName)) {
              _tmpFromName = null;
            } else {
              _tmpFromName = _cursor.getString(_cursorIndexOfFromName);
            }
            final String _tmpToAddresses;
            _tmpToAddresses = _cursor.getString(_cursorIndexOfToAddresses);
            final String _tmpCcAddresses;
            if (_cursor.isNull(_cursorIndexOfCcAddresses)) {
              _tmpCcAddresses = null;
            } else {
              _tmpCcAddresses = _cursor.getString(_cursorIndexOfCcAddresses);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpSentDate;
            if (_cursor.isNull(_cursorIndexOfSentDate)) {
              _tmpSentDate = null;
            } else {
              _tmpSentDate = _cursor.getLong(_cursorIndexOfSentDate);
            }
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsStarred;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsStarred);
            _tmpIsStarred = _tmp_1 != 0;
            final boolean _tmpHasAttachments;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAttachments);
            _tmpHasAttachments = _tmp_2 != 0;
            final String _tmpPreviewText;
            if (_cursor.isNull(_cursorIndexOfPreviewText)) {
              _tmpPreviewText = null;
            } else {
              _tmpPreviewText = _cursor.getString(_cursorIndexOfPreviewText);
            }
            final String _tmpBodyHtml;
            if (_cursor.isNull(_cursorIndexOfBodyHtml)) {
              _tmpBodyHtml = null;
            } else {
              _tmpBodyHtml = _cursor.getString(_cursorIndexOfBodyHtml);
            }
            final String _tmpBodyText;
            if (_cursor.isNull(_cursorIndexOfBodyText)) {
              _tmpBodyText = null;
            } else {
              _tmpBodyText = _cursor.getString(_cursorIndexOfBodyText);
            }
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            final String _tmpFlags;
            if (_cursor.isNull(_cursorIndexOfFlags)) {
              _tmpFlags = null;
            } else {
              _tmpFlags = _cursor.getString(_cursorIndexOfFlags);
            }
            _result = new MessageEntity(_tmpId,_tmpAccountId,_tmpFolderId,_tmpUid,_tmpMessageIdHeader,_tmpSubject,_tmpFromAddress,_tmpFromName,_tmpToAddresses,_tmpCcAddresses,_tmpReceivedDate,_tmpSentDate,_tmpIsRead,_tmpIsStarred,_tmpHasAttachments,_tmpPreviewText,_tmpBodyHtml,_tmpBodyText,_tmpSize,_tmpFlags);
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
  public Object getUnreadCount(final long accountId, final long folderId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM messages WHERE account_id = ? AND folder_id = ? AND is_read = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, folderId);
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
