package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;
import com.healthcare.records.database.entity.User;
import com.healthcare.records.util.AadharValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Activity for hospital staff to add a new patient record.
 */
public class AddRecordActivity extends AppCompatActivity {

    private EditText etPatientAadhar, etDiagnosis, etPrescription, etNotes;
    private Button btnSave;
    private ProgressBar progressBar;
    private TextView tvHospitalInfo;
    private AppDatabase database;
    private String hospitalId;
    private String hospitalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        // Set up action bar
        setTitle("Add Patient Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get hospital information from intent
        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        hospitalName = getIntent().getStringExtra("HOSPITAL_NAME");

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        etPatientAadhar = findViewById(R.id.etPatientAadhar);
        etDiagnosis = findViewById(R.id.etDiagnosis);
        etPrescription = findViewById(R.id.etPrescription);
        etNotes = findViewById(R.id.etNotes);
        btnSave = findViewById(R.id.btnSave);
        progressBar = findViewById(R.id.progressBar);
        tvHospitalInfo = findViewById(R.id.tvHospitalInfo);

        // Set hospital info
        tvHospitalInfo.setText("Hospital: " + hospitalName);

        // Set up save button click listener
        btnSave.setOnClickListener(view -> validateAndSaveRecord());
    }

    /**
     * Validates input and saves the patient record if valid
     */
    private void validateAndSaveRecord() {
        String patientAadhar = etPatientAadhar.getText().toString().trim();
        String diagnosis = etDiagnosis.getText().toString().trim();
        String prescription = etPrescription.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();

        // Validate input
        if (patientAadhar.isEmpty() || diagnosis.isEmpty()) {
            Toast.makeText(this, "Patient Aadhar and Diagnosis are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!AadharValidator.isValidAadhar(patientAadhar)) {
            Toast.makeText(this, "Invalid Aadhar number", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Check if patient exists with the given Aadhar
        new Thread(() -> {
            final User patient = database.userDao().getUserByAadhar(patientAadhar);
            
            if (patient == null || !patient.getRole().equals("patient")) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AddRecordActivity.this, "No patient found with this Aadhar number", Toast.LENGTH_SHORT).show();
                });
                return;
            }

            // Get current timestamp
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            
            // Create a new patient record
            String recordId = UUID.randomUUID().toString();
            PatientRecord record = new PatientRecord(
                recordId,
                patient.getUserId(),
                patient.getName(),
                hospitalId,
                hospitalName,
                diagnosis,
                prescription,
                notes,
                currentDate
            );
            
            // Insert the record into the database
            database.patientRecordDao().insert(record);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddRecordActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
