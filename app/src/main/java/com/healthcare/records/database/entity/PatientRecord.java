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
    private String doctorContactNumber; // Doctor's phone number
    private String imagePath; // Path to stored medical image
    private int severityScore; // Severity score (1-10)
    private String doctorName; // Doctor's name
    private String doctorAvailability; // Doctor's available timings
    private int imageRotation; // Image rotation angle in degrees
    private String appointmentDate; // Appointment date and time (if scheduled)

    /**
     * Constructor for creating a new patient record with all fields
     */
    public PatientRecord(@NonNull String recordId, String patientId, String patientName,
                         String hospitalId, String hospitalName, String diagnosis,
                         String prescription, String notes, String date,
                         String doctorContactNumber, String imagePath, int severityScore,
                         String doctorName, String doctorAvailability, int imageRotation) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.date = date;
        this.doctorContactNumber = doctorContactNumber;
        this.imagePath = imagePath;
        this.severityScore = severityScore;
        this.doctorName = doctorName;
        this.doctorAvailability = doctorAvailability;
        this.imageRotation = imageRotation;
        this.appointmentDate = "";
    }

    /**
     * Constructor for creating a new patient record with doctor's contact and image
     */
    @androidx.room.Ignore
    public PatientRecord(@NonNull String recordId, String patientId, String patientName,
                         String hospitalId, String hospitalName, String diagnosis,
                         String prescription, String notes, String date,
                         String doctorContactNumber, String imagePath) {
        this(recordId, patientId, patientName, hospitalId, hospitalName, diagnosis,
             prescription, notes, date, doctorContactNumber, imagePath, 1, "", "", 0);
    }

    /**
     * Constructor for creating a new patient record (backward compatibility)
     */
    @androidx.room.Ignore
    public PatientRecord(@NonNull String recordId, String patientId, String patientName,
                         String hospitalId, String hospitalName, String diagnosis,
                         String prescription, String notes, String date) {
        this(recordId, patientId, patientName, hospitalId, hospitalName, diagnosis,
             prescription, notes, date, "", "", 1, "", "", 0);
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
    
    /**
     * Get the doctor's contact number
     */
    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    /**
     * Set the doctor's contact number
     */
    public void setDoctorContactNumber(String doctorContactNumber) {
        this.doctorContactNumber = doctorContactNumber;
    }

    /**
     * Get the path to the stored medical image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Set the path to the stored medical image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    /**
     * Get the severity score (1-10)
     */
    public int getSeverityScore() {
        return severityScore;
    }
    
    /**
     * Set the severity score (1-10)
     */
    public void setSeverityScore(int severityScore) {
        // Ensure score is between 1-10
        if (severityScore < 1) {
            this.severityScore = 1;
        } else if (severityScore > 10) {
            this.severityScore = 10;
        } else {
            this.severityScore = severityScore;
        }
    }
    
    /**
     * Get the doctor's name
     */
    public String getDoctorName() {
        return doctorName;
    }
    
    /**
     * Set the doctor's name
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    /**
     * Get the doctor's available timings
     */
    public String getDoctorAvailability() {
        return doctorAvailability;
    }
    
    /**
     * Set the doctor's available timings
     */
    public void setDoctorAvailability(String doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }
    
    /**
     * Get the image rotation angle
     */
    public int getImageRotation() {
        return imageRotation;
    }
    
    /**
     * Set the image rotation angle
     */
    public void setImageRotation(int imageRotation) {
        // Normalize the rotation angle to be between 0-359
        this.imageRotation = imageRotation % 360;
        if (this.imageRotation < 0) {
            this.imageRotation += 360;
        }
    }
    
    /**
     * Get the appointment date
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }
    
    /**
     * Set the appointment date
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    /**
     * Check if record has an appointment scheduled
     */
    public boolean hasAppointment() {
        return appointmentDate != null && !appointmentDate.isEmpty();
    }
}
