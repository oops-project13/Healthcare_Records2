package com.healthcare.records.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.healthcare.records.database.dao.PatientRecordDao;
import com.healthcare.records.database.dao.PatientRecordDao_Impl;
import com.healthcare.records.database.dao.UserDao;
import com.healthcare.records.database.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile PatientRecordDao _patientRecordDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`userId` TEXT NOT NULL, `name` TEXT, `identifier` TEXT, `password` TEXT, `role` TEXT, PRIMARY KEY(`userId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `patient_records` (`recordId` TEXT NOT NULL, `patientId` TEXT, `patientName` TEXT, `hospitalId` TEXT, `hospitalName` TEXT, `diagnosis` TEXT, `prescription` TEXT, `notes` TEXT, `date` TEXT, `doctorContactNumber` TEXT, `imagePath` TEXT, `severityScore` INTEGER NOT NULL, `doctorName` TEXT, `doctorAvailability` TEXT, `imageRotation` INTEGER NOT NULL, `appointmentDate` TEXT, PRIMARY KEY(`recordId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4d603a104b6470505ad6f4bd1809e092')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `users`");
        _db.execSQL("DROP TABLE IF EXISTS `patient_records`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(5);
        _columnsUsers.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("identifier", new TableInfo.Column("identifier", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(_db, "users");
        if (! _infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.healthcare.records.database.entity.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsPatientRecords = new HashMap<String, TableInfo.Column>(16);
        _columnsPatientRecords.put("recordId", new TableInfo.Column("recordId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("patientId", new TableInfo.Column("patientId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("patientName", new TableInfo.Column("patientName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("hospitalId", new TableInfo.Column("hospitalId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("hospitalName", new TableInfo.Column("hospitalName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("diagnosis", new TableInfo.Column("diagnosis", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("prescription", new TableInfo.Column("prescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("doctorContactNumber", new TableInfo.Column("doctorContactNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("imagePath", new TableInfo.Column("imagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("severityScore", new TableInfo.Column("severityScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("doctorName", new TableInfo.Column("doctorName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("doctorAvailability", new TableInfo.Column("doctorAvailability", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("imageRotation", new TableInfo.Column("imageRotation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientRecords.put("appointmentDate", new TableInfo.Column("appointmentDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPatientRecords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPatientRecords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPatientRecords = new TableInfo("patient_records", _columnsPatientRecords, _foreignKeysPatientRecords, _indicesPatientRecords);
        final TableInfo _existingPatientRecords = TableInfo.read(_db, "patient_records");
        if (! _infoPatientRecords.equals(_existingPatientRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "patient_records(com.healthcare.records.database.entity.PatientRecord).\n"
                  + " Expected:\n" + _infoPatientRecords + "\n"
                  + " Found:\n" + _existingPatientRecords);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4d603a104b6470505ad6f4bd1809e092", "91e231cb2b35b93a04a6a67409c2bdf7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","patient_records");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `patient_records`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PatientRecordDao.class, PatientRecordDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public PatientRecordDao patientRecordDao() {
    if (_patientRecordDao != null) {
      return _patientRecordDao;
    } else {
      synchronized(this) {
        if(_patientRecordDao == null) {
          _patientRecordDao = new PatientRecordDao_Impl(this);
        }
        return _patientRecordDao;
      }
    }
  }
}
