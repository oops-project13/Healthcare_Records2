package com.healthcare.records.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a patient medical record in the system.
 */
@Entity(tableName = "patient_records")
public class PatientRecord {

    @PrimaryKey
    @NonNull
    private String recordId;
    private String patientId;
    private String patientName;
    private String hospitalId;
    private String hospitalName;
    private String diagnosis;
    private String prescription;
    private String notes;
    private String date;

    /**
     * Constructor for creating a new patient record
     */
    public PatientRecord(@NonNull String recordId, String patientId, String patientName,
                         String hospitalId, String hospitalName, String diagnosis,
                         String prescription, String notes, String date) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.date = date;
    }

    /**
     * Get the unique record ID
     */
    @NonNull
    public String getRecordId() {
        return recordId;
    }

    /**
     * Set the unique record ID
     */
    public void setRecordId(@NonNull String recordId) {
        this.recordId = recordId;
    }

    /**
     * Get the patient's ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Set the patient's ID
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Get the patient's name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Set the patient's name
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * Get the hospital's ID
     */
    public String getHospitalId() {
        return hospitalId;
    }

    /**
     * Set the hospital's ID
     */
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * Get the hospital's name
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * Set the hospital's name
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * Get the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Set the diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Get the prescription
     */
    public String getPrescription() {
        return prescription;
    }

    /**
     * Set the prescription
     */
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    /**
     * Get additional notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set additional notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Get the record creation/update date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the record creation/update date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
