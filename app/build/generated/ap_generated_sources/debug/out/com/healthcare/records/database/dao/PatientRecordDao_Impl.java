package com.healthcare.records.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.healthcare.records.database.entity.PatientRecord;
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
public final class PatientRecordDao_Impl implements PatientRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PatientRecord> __insertionAdapterOfPatientRecord;

  private final EntityDeletionOrUpdateAdapter<PatientRecord> __updateAdapterOfPatientRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRecord;

  public PatientRecordDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPatientRecord = new EntityInsertionAdapter<PatientRecord>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `patient_records` (`recordId`,`patientId`,`patientName`,`hospitalId`,`hospitalName`,`diagnosis`,`prescription`,`notes`,`date`,`doctorContactNumber`,`imagePath`,`severityScore`,`doctorName`,`doctorAvailability`,`imageRotation`,`appointmentDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PatientRecord value) {
        if (value.getRecordId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRecordId());
        }
        if (value.getPatientId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPatientId());
        }
        if (value.getPatientName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPatientName());
        }
        if (value.getHospitalId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getHospitalId());
        }
        if (value.getHospitalName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHospitalName());
        }
        if (value.getDiagnosis() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDiagnosis());
        }
        if (value.getPrescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrescription());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getNotes());
        }
        if (value.getDate() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDate());
        }
        if (value.getDoctorContactNumber() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDoctorContactNumber());
        }
        if (value.getImagePath() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getImagePath());
        }
        stmt.bindLong(12, value.getSeverityScore());
        if (value.getDoctorName() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDoctorName());
        }
        if (value.getDoctorAvailability() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getDoctorAvailability());
        }
        stmt.bindLong(15, value.getImageRotation());
        if (value.getAppointmentDate() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getAppointmentDate());
        }
      }
    };
    this.__updateAdapterOfPatientRecord = new EntityDeletionOrUpdateAdapter<PatientRecord>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `patient_records` SET `recordId` = ?,`patientId` = ?,`patientName` = ?,`hospitalId` = ?,`hospitalName` = ?,`diagnosis` = ?,`prescription` = ?,`notes` = ?,`date` = ?,`doctorContactNumber` = ?,`imagePath` = ?,`severityScore` = ?,`doctorName` = ?,`doctorAvailability` = ?,`imageRotation` = ?,`appointmentDate` = ? WHERE `recordId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PatientRecord value) {
        if (value.getRecordId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRecordId());
        }
        if (value.getPatientId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPatientId());
        }
        if (value.getPatientName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPatientName());
        }
        if (value.getHospitalId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getHospitalId());
        }
        if (value.getHospitalName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHospitalName());
        }
        if (value.getDiagnosis() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDiagnosis());
        }
        if (value.getPrescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrescription());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getNotes());
        }
        if (value.getDate() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDate());
        }
        if (value.getDoctorContactNumber() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDoctorContactNumber());
        }
        if (value.getImagePath() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getImagePath());
        }
        stmt.bindLong(12, value.getSeverityScore());
        if (value.getDoctorName() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDoctorName());
        }
        if (value.getDoctorAvailability() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getDoctorAvailability());
        }
        stmt.bindLong(15, value.getImageRotation());
        if (value.getAppointmentDate() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getAppointmentDate());
        }
        if (value.getRecordId() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getRecordId());
        }
      }
    };
    this.__preparedStmtOfDeleteRecord = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM patient_records WHERE recordId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final PatientRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPatientRecord.insert(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final PatientRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPatientRecord.handle(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteRecord(final String recordId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRecord.acquire();
    int _argIndex = 1;
    if (recordId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, recordId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteRecord.release(_stmt);
    }
  }

  @Override
  public PatientRecord getRecordById(final String recordId) {
    final String _sql = "SELECT * FROM patient_records WHERE recordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (recordId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, recordId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patientName");
      final int _cursorIndexOfHospitalId = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalId");
      final int _cursorIndexOfHospitalName = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalName");
      final int _cursorIndexOfDiagnosis = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosis");
      final int _cursorIndexOfPrescription = CursorUtil.getColumnIndexOrThrow(_cursor, "prescription");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfDoctorContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorContactNumber");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfSeverityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "severityScore");
      final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorName");
      final int _cursorIndexOfDoctorAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorAvailability");
      final int _cursorIndexOfImageRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "imageRotation");
      final int _cursorIndexOfAppointmentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentDate");
      final PatientRecord _result;
      if(_cursor.moveToFirst()) {
        final String _tmpRecordId;
        if (_cursor.isNull(_cursorIndexOfRecordId)) {
          _tmpRecordId = null;
        } else {
          _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
        }
        final String _tmpPatientId;
        if (_cursor.isNull(_cursorIndexOfPatientId)) {
          _tmpPatientId = null;
        } else {
          _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
        }
        final String _tmpPatientName;
        if (_cursor.isNull(_cursorIndexOfPatientName)) {
          _tmpPatientName = null;
        } else {
          _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
        }
        final String _tmpHospitalId;
        if (_cursor.isNull(_cursorIndexOfHospitalId)) {
          _tmpHospitalId = null;
        } else {
          _tmpHospitalId = _cursor.getString(_cursorIndexOfHospitalId);
        }
        final String _tmpHospitalName;
        if (_cursor.isNull(_cursorIndexOfHospitalName)) {
          _tmpHospitalName = null;
        } else {
          _tmpHospitalName = _cursor.getString(_cursorIndexOfHospitalName);
        }
        final String _tmpDiagnosis;
        if (_cursor.isNull(_cursorIndexOfDiagnosis)) {
          _tmpDiagnosis = null;
        } else {
          _tmpDiagnosis = _cursor.getString(_cursorIndexOfDiagnosis);
        }
        final String _tmpPrescription;
        if (_cursor.isNull(_cursorIndexOfPrescription)) {
          _tmpPrescription = null;
        } else {
          _tmpPrescription = _cursor.getString(_cursorIndexOfPrescription);
        }
        final String _tmpNotes;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _tmpNotes = null;
        } else {
          _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpDoctorContactNumber;
        if (_cursor.isNull(_cursorIndexOfDoctorContactNumber)) {
          _tmpDoctorContactNumber = null;
        } else {
          _tmpDoctorContactNumber = _cursor.getString(_cursorIndexOfDoctorContactNumber);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final int _tmpSeverityScore;
        _tmpSeverityScore = _cursor.getInt(_cursorIndexOfSeverityScore);
        final String _tmpDoctorName;
        if (_cursor.isNull(_cursorIndexOfDoctorName)) {
          _tmpDoctorName = null;
        } else {
          _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
        }
        final String _tmpDoctorAvailability;
        if (_cursor.isNull(_cursorIndexOfDoctorAvailability)) {
          _tmpDoctorAvailability = null;
        } else {
          _tmpDoctorAvailability = _cursor.getString(_cursorIndexOfDoctorAvailability);
        }
        final int _tmpImageRotation;
        _tmpImageRotation = _cursor.getInt(_cursorIndexOfImageRotation);
        _result = new PatientRecord(_tmpRecordId,_tmpPatientId,_tmpPatientName,_tmpHospitalId,_tmpHospitalName,_tmpDiagnosis,_tmpPrescription,_tmpNotes,_tmpDate,_tmpDoctorContactNumber,_tmpImagePath,_tmpSeverityScore,_tmpDoctorName,_tmpDoctorAvailability,_tmpImageRotation);
        final String _tmpAppointmentDate;
        if (_cursor.isNull(_cursorIndexOfAppointmentDate)) {
          _tmpAppointmentDate = null;
        } else {
          _tmpAppointmentDate = _cursor.getString(_cursorIndexOfAppointmentDate);
        }
        _result.setAppointmentDate(_tmpAppointmentDate);
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
  public List<PatientRecord> getRecordsByPatientId(final String patientId) {
    final String _sql = "SELECT * FROM patient_records WHERE patientId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patientName");
      final int _cursorIndexOfHospitalId = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalId");
      final int _cursorIndexOfHospitalName = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalName");
      final int _cursorIndexOfDiagnosis = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosis");
      final int _cursorIndexOfPrescription = CursorUtil.getColumnIndexOrThrow(_cursor, "prescription");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfDoctorContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorContactNumber");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfSeverityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "severityScore");
      final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorName");
      final int _cursorIndexOfDoctorAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorAvailability");
      final int _cursorIndexOfImageRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "imageRotation");
      final int _cursorIndexOfAppointmentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentDate");
      final List<PatientRecord> _result = new ArrayList<PatientRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PatientRecord _item;
        final String _tmpRecordId;
        if (_cursor.isNull(_cursorIndexOfRecordId)) {
          _tmpRecordId = null;
        } else {
          _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
        }
        final String _tmpPatientId;
        if (_cursor.isNull(_cursorIndexOfPatientId)) {
          _tmpPatientId = null;
        } else {
          _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
        }
        final String _tmpPatientName;
        if (_cursor.isNull(_cursorIndexOfPatientName)) {
          _tmpPatientName = null;
        } else {
          _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
        }
        final String _tmpHospitalId;
        if (_cursor.isNull(_cursorIndexOfHospitalId)) {
          _tmpHospitalId = null;
        } else {
          _tmpHospitalId = _cursor.getString(_cursorIndexOfHospitalId);
        }
        final String _tmpHospitalName;
        if (_cursor.isNull(_cursorIndexOfHospitalName)) {
          _tmpHospitalName = null;
        } else {
          _tmpHospitalName = _cursor.getString(_cursorIndexOfHospitalName);
        }
        final String _tmpDiagnosis;
        if (_cursor.isNull(_cursorIndexOfDiagnosis)) {
          _tmpDiagnosis = null;
        } else {
          _tmpDiagnosis = _cursor.getString(_cursorIndexOfDiagnosis);
        }
        final String _tmpPrescription;
        if (_cursor.isNull(_cursorIndexOfPrescription)) {
          _tmpPrescription = null;
        } else {
          _tmpPrescription = _cursor.getString(_cursorIndexOfPrescription);
        }
        final String _tmpNotes;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _tmpNotes = null;
        } else {
          _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpDoctorContactNumber;
        if (_cursor.isNull(_cursorIndexOfDoctorContactNumber)) {
          _tmpDoctorContactNumber = null;
        } else {
          _tmpDoctorContactNumber = _cursor.getString(_cursorIndexOfDoctorContactNumber);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final int _tmpSeverityScore;
        _tmpSeverityScore = _cursor.getInt(_cursorIndexOfSeverityScore);
        final String _tmpDoctorName;
        if (_cursor.isNull(_cursorIndexOfDoctorName)) {
          _tmpDoctorName = null;
        } else {
          _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
        }
        final String _tmpDoctorAvailability;
        if (_cursor.isNull(_cursorIndexOfDoctorAvailability)) {
          _tmpDoctorAvailability = null;
        } else {
          _tmpDoctorAvailability = _cursor.getString(_cursorIndexOfDoctorAvailability);
        }
        final int _tmpImageRotation;
        _tmpImageRotation = _cursor.getInt(_cursorIndexOfImageRotation);
        _item = new PatientRecord(_tmpRecordId,_tmpPatientId,_tmpPatientName,_tmpHospitalId,_tmpHospitalName,_tmpDiagnosis,_tmpPrescription,_tmpNotes,_tmpDate,_tmpDoctorContactNumber,_tmpImagePath,_tmpSeverityScore,_tmpDoctorName,_tmpDoctorAvailability,_tmpImageRotation);
        final String _tmpAppointmentDate;
        if (_cursor.isNull(_cursorIndexOfAppointmentDate)) {
          _tmpAppointmentDate = null;
        } else {
          _tmpAppointmentDate = _cursor.getString(_cursorIndexOfAppointmentDate);
        }
        _item.setAppointmentDate(_tmpAppointmentDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<PatientRecord> getRecordsByHospitalId(final String hospitalId) {
    final String _sql = "SELECT * FROM patient_records WHERE hospitalId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hospitalId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hospitalId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patientName");
      final int _cursorIndexOfHospitalId = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalId");
      final int _cursorIndexOfHospitalName = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalName");
      final int _cursorIndexOfDiagnosis = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosis");
      final int _cursorIndexOfPrescription = CursorUtil.getColumnIndexOrThrow(_cursor, "prescription");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfDoctorContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorContactNumber");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfSeverityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "severityScore");
      final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorName");
      final int _cursorIndexOfDoctorAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorAvailability");
      final int _cursorIndexOfImageRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "imageRotation");
      final int _cursorIndexOfAppointmentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentDate");
      final List<PatientRecord> _result = new ArrayList<PatientRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PatientRecord _item;
        final String _tmpRecordId;
        if (_cursor.isNull(_cursorIndexOfRecordId)) {
          _tmpRecordId = null;
        } else {
          _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
        }
        final String _tmpPatientId;
        if (_cursor.isNull(_cursorIndexOfPatientId)) {
          _tmpPatientId = null;
        } else {
          _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
        }
        final String _tmpPatientName;
        if (_cursor.isNull(_cursorIndexOfPatientName)) {
          _tmpPatientName = null;
        } else {
          _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
        }
        final String _tmpHospitalId;
        if (_cursor.isNull(_cursorIndexOfHospitalId)) {
          _tmpHospitalId = null;
        } else {
          _tmpHospitalId = _cursor.getString(_cursorIndexOfHospitalId);
        }
        final String _tmpHospitalName;
        if (_cursor.isNull(_cursorIndexOfHospitalName)) {
          _tmpHospitalName = null;
        } else {
          _tmpHospitalName = _cursor.getString(_cursorIndexOfHospitalName);
        }
        final String _tmpDiagnosis;
        if (_cursor.isNull(_cursorIndexOfDiagnosis)) {
          _tmpDiagnosis = null;
        } else {
          _tmpDiagnosis = _cursor.getString(_cursorIndexOfDiagnosis);
        }
        final String _tmpPrescription;
        if (_cursor.isNull(_cursorIndexOfPrescription)) {
          _tmpPrescription = null;
        } else {
          _tmpPrescription = _cursor.getString(_cursorIndexOfPrescription);
        }
        final String _tmpNotes;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _tmpNotes = null;
        } else {
          _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpDoctorContactNumber;
        if (_cursor.isNull(_cursorIndexOfDoctorContactNumber)) {
          _tmpDoctorContactNumber = null;
        } else {
          _tmpDoctorContactNumber = _cursor.getString(_cursorIndexOfDoctorContactNumber);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final int _tmpSeverityScore;
        _tmpSeverityScore = _cursor.getInt(_cursorIndexOfSeverityScore);
        final String _tmpDoctorName;
        if (_cursor.isNull(_cursorIndexOfDoctorName)) {
          _tmpDoctorName = null;
        } else {
          _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
        }
        final String _tmpDoctorAvailability;
        if (_cursor.isNull(_cursorIndexOfDoctorAvailability)) {
          _tmpDoctorAvailability = null;
        } else {
          _tmpDoctorAvailability = _cursor.getString(_cursorIndexOfDoctorAvailability);
        }
        final int _tmpImageRotation;
        _tmpImageRotation = _cursor.getInt(_cursorIndexOfImageRotation);
        _item = new PatientRecord(_tmpRecordId,_tmpPatientId,_tmpPatientName,_tmpHospitalId,_tmpHospitalName,_tmpDiagnosis,_tmpPrescription,_tmpNotes,_tmpDate,_tmpDoctorContactNumber,_tmpImagePath,_tmpSeverityScore,_tmpDoctorName,_tmpDoctorAvailability,_tmpImageRotation);
        final String _tmpAppointmentDate;
        if (_cursor.isNull(_cursorIndexOfAppointmentDate)) {
          _tmpAppointmentDate = null;
        } else {
          _tmpAppointmentDate = _cursor.getString(_cursorIndexOfAppointmentDate);
        }
        _item.setAppointmentDate(_tmpAppointmentDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<PatientRecord> getAllRecords() {
    final String _sql = "SELECT * FROM patient_records ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patientName");
      final int _cursorIndexOfHospitalId = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalId");
      final int _cursorIndexOfHospitalName = CursorUtil.getColumnIndexOrThrow(_cursor, "hospitalName");
      final int _cursorIndexOfDiagnosis = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosis");
      final int _cursorIndexOfPrescription = CursorUtil.getColumnIndexOrThrow(_cursor, "prescription");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfDoctorContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorContactNumber");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfSeverityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "severityScore");
      final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorName");
      final int _cursorIndexOfDoctorAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorAvailability");
      final int _cursorIndexOfImageRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "imageRotation");
      final int _cursorIndexOfAppointmentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentDate");
      final List<PatientRecord> _result = new ArrayList<PatientRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PatientRecord _item;
        final String _tmpRecordId;
        if (_cursor.isNull(_cursorIndexOfRecordId)) {
          _tmpRecordId = null;
        } else {
          _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
        }
        final String _tmpPatientId;
        if (_cursor.isNull(_cursorIndexOfPatientId)) {
          _tmpPatientId = null;
        } else {
          _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
        }
        final String _tmpPatientName;
        if (_cursor.isNull(_cursorIndexOfPatientName)) {
          _tmpPatientName = null;
        } else {
          _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
        }
        final String _tmpHospitalId;
        if (_cursor.isNull(_cursorIndexOfHospitalId)) {
          _tmpHospitalId = null;
        } else {
          _tmpHospitalId = _cursor.getString(_cursorIndexOfHospitalId);
        }
        final String _tmpHospitalName;
        if (_cursor.isNull(_cursorIndexOfHospitalName)) {
          _tmpHospitalName = null;
        } else {
          _tmpHospitalName = _cursor.getString(_cursorIndexOfHospitalName);
        }
        final String _tmpDiagnosis;
        if (_cursor.isNull(_cursorIndexOfDiagnosis)) {
          _tmpDiagnosis = null;
        } else {
          _tmpDiagnosis = _cursor.getString(_cursorIndexOfDiagnosis);
        }
        final String _tmpPrescription;
        if (_cursor.isNull(_cursorIndexOfPrescription)) {
          _tmpPrescription = null;
        } else {
          _tmpPrescription = _cursor.getString(_cursorIndexOfPrescription);
        }
        final String _tmpNotes;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _tmpNotes = null;
        } else {
          _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpDoctorContactNumber;
        if (_cursor.isNull(_cursorIndexOfDoctorContactNumber)) {
          _tmpDoctorContactNumber = null;
        } else {
          _tmpDoctorContactNumber = _cursor.getString(_cursorIndexOfDoctorContactNumber);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final int _tmpSeverityScore;
        _tmpSeverityScore = _cursor.getInt(_cursorIndexOfSeverityScore);
        final String _tmpDoctorName;
        if (_cursor.isNull(_cursorIndexOfDoctorName)) {
          _tmpDoctorName = null;
        } else {
          _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
        }
        final String _tmpDoctorAvailability;
        if (_cursor.isNull(_cursorIndexOfDoctorAvailability)) {
          _tmpDoctorAvailability = null;
        } else {
          _tmpDoctorAvailability = _cursor.getString(_cursorIndexOfDoctorAvailability);
        }
        final int _tmpImageRotation;
        _tmpImageRotation = _cursor.getInt(_cursorIndexOfImageRotation);
        _item = new PatientRecord(_tmpRecordId,_tmpPatientId,_tmpPatientName,_tmpHospitalId,_tmpHospitalName,_tmpDiagnosis,_tmpPrescription,_tmpNotes,_tmpDate,_tmpDoctorContactNumber,_tmpImagePath,_tmpSeverityScore,_tmpDoctorName,_tmpDoctorAvailability,_tmpImageRotation);
        final String _tmpAppointmentDate;
        if (_cursor.isNull(_cursorIndexOfAppointmentDate)) {
          _tmpAppointmentDate = null;
        } else {
          _tmpAppointmentDate = _cursor.getString(_cursorIndexOfAppointmentDate);
        }
        _item.setAppointmentDate(_tmpAppointmentDate);
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
