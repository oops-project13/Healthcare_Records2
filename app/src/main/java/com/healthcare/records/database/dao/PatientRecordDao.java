package com.healthcare.records.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.healthcare.records.database.entity.PatientRecord;

import java.util.List;

/**
 * Data Access Object for the PatientRecord entity.
 * Provides methods to interact with the patient_records table in the database.
 */
@Dao
public interface PatientRecordDao {

    /**
     * Insert a new patient record into the database
     */
    @Insert
    void insert(PatientRecord record);

    /**
     * Update an existing patient record
     */
    @Update
    void update(PatientRecord record);

    /**
     * Get a patient record by its ID
     */
    @Query("SELECT * FROM patient_records WHERE recordId = :recordId")
    PatientRecord getRecordById(String recordId);

    /**
     * Get all records for a specific patient
     */
    @Query("SELECT * FROM patient_records WHERE patientId = :patientId ORDER BY date DESC")
    List<PatientRecord> getRecordsByPatientId(String patientId);

    /**
     * Get all records created by a specific hospital
     */
    @Query("SELECT * FROM patient_records WHERE hospitalId = :hospitalId ORDER BY date DESC")
    List<PatientRecord> getRecordsByHospitalId(String hospitalId);

    /**
     * Get all patient records in the system, ordered by date
     */
    @Query("SELECT * FROM patient_records ORDER BY date DESC")
    List<PatientRecord> getAllRecords();

    /**
     * Delete a record by its ID
     */
    @Query("DELETE FROM patient_records WHERE recordId = :recordId")
    void deleteRecord(String recordId);
}
