package com.healthcare.records.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.healthcare.records.database.entity.User;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `users` (`userId`,`name`,`identifier`,`password`,`role`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.getUserId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUserId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getIdentifier() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdentifier());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPassword());
        }
        if (value.getRole() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRole());
        }
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `users` SET `userId` = ?,`name` = ?,`identifier` = ?,`password` = ?,`role` = ? WHERE `userId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.getUserId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUserId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getIdentifier() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdentifier());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPassword());
        }
        if (value.getRole() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRole());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserId());
        }
      }
    };
  }

  @Override
  public void insert(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public User getUserById(final String userId) {
    final String _sql = "SELECT * FROM users WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _result = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByIdentifier(final String identifier) {
    final String _sql = "SELECT * FROM users WHERE identifier = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (identifier == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, identifier);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _result = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByAadhar(final String aadharId) {
    final String _sql = "SELECT * FROM users WHERE identifier = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (aadharId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, aadharId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _result = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByIdentifierAndPassword(final String identifier, final String password) {
    final String _sql = "SELECT * FROM users WHERE identifier = ? AND password = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (identifier == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, identifier);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _result = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByAadharAndPassword(final String aadharId, final String password) {
    final String _sql = "SELECT * FROM users WHERE identifier = ? AND password = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (aadharId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, aadharId);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _result = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getUsersByRole(final String role) {
    final String _sql = "SELECT * FROM users WHERE role = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (role == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, role);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _item = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getAllPatients() {
    final String _sql = "SELECT * FROM users WHERE role = 'patient'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _item = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getAllHospitals() {
    final String _sql = "SELECT * FROM users WHERE role = 'hospital'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIdentifier = CursorUtil.getColumnIndexOrThrow(_cursor, "identifier");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpIdentifier;
        if (_cursor.isNull(_cursorIndexOfIdentifier)) {
          _tmpIdentifier = null;
        } else {
          _tmpIdentifier = _cursor.getString(_cursorIndexOfIdentifier);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRole;
        if (_cursor.isNull(_cursorIndexOfRole)) {
          _tmpRole = null;
        } else {
          _tmpRole = _cursor.getString(_cursorIndexOfRole);
        }
        _item = new User(_tmpUserId,_tmpName,_tmpIdentifier,_tmpPassword,_tmpRole);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
