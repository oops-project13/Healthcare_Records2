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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Activity for hospital staff to edit an existing patient record.
 */
public class EditRecordActivity extends AppCompatActivity {

    private TextView tvPatientName, tvHospitalName, tvDate;
    private EditText etDiagnosis, etPrescription, etNotes;
    private Button btnUpdate;
    private ProgressBar progressBar;
    private AppDatabase database;
    private String recordId;
    private PatientRecord currentRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        // Set up action bar
        setTitle("Edit Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get record ID from intent
        recordId = getIntent().getStringExtra("RECORD_ID");
        if (recordId == null) {
            Toast.makeText(this, "Error: Record not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        tvPatientName = findViewById(R.id.tvPatientName);
        tvHospitalName = findViewById(R.id.tvHospitalName);
        tvDate = findViewById(R.id.tvDate);
        etDiagnosis = findViewById(R.id.etDiagnosis);
        etPrescription = findViewById(R.id.etPrescription);
        etNotes = findViewById(R.id.etNotes);
        btnUpdate = findViewById(R.id.btnUpdate);
        progressBar = findViewById(R.id.progressBar);

        // Load record data
        loadRecordData();

        // Set up update button click listener
        btnUpdate.setOnClickListener(view -> validateAndUpdateRecord());
    }

    /**
     * Loads the details of the selected record from the database for editing
     */
    private void loadRecordData() {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            currentRecord = database.patientRecordDao().getRecordById(recordId);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                
                if (currentRecord != null) {
                    // Display record data
                    tvPatientName.setText(currentRecord.getPatientName());
                    tvHospitalName.setText(currentRecord.getHospitalName());
                    tvDate.setText(currentRecord.getDate());
                    etDiagnosis.setText(currentRecord.getDiagnosis());
                    etPrescription.setText(currentRecord.getPrescription());
                    etNotes.setText(currentRecord.getNotes());
                } else {
                    Toast.makeText(EditRecordActivity.this, "Error: Record not found", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }).start();
    }

    /**
     * Validates input and updates the patient record if valid
     */
    private void validateAndUpdateRecord() {
        String diagnosis = etDiagnosis.getText().toString().trim();
        String prescription = etPrescription.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();

        // Validate input
        if (diagnosis.isEmpty()) {
            Toast.makeText(this, "Diagnosis is required", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            if (currentRecord != null) {
                // Update record fields
                currentRecord.setDiagnosis(diagnosis);
                currentRecord.setPrescription(prescription);
                currentRecord.setNotes(notes);
                
                // Get current timestamp for last updated
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                currentRecord.setDate(currentDate);
                
                // Update the record in the database
                database.patientRecordDao().update(currentRecord);
                
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EditRecordActivity.this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                });
            } else {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EditRecordActivity.this, "Error: Record not found", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
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
