package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;

/**
 * Activity for patients to view their medical record details.
 * This is a read-only view of a record.
 */
public class ViewRecordActivity extends AppCompatActivity {

    private TextView tvPatientName, tvHospitalName, tvDiagnosis, tvPrescription, tvNotes, tvDate;
    private ProgressBar progressBar;
    private AppDatabase database;
    private String recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        // Set up action bar
        setTitle("Record Details");
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
        tvDiagnosis = findViewById(R.id.tvDiagnosis);
        tvPrescription = findViewById(R.id.tvPrescription);
        tvNotes = findViewById(R.id.tvNotes);
        tvDate = findViewById(R.id.tvDate);
        progressBar = findViewById(R.id.progressBar);

        // Load record data
        loadRecordData();
    }

    /**
     * Loads the details of the selected record from the database
     */
    private void loadRecordData() {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            final PatientRecord record = database.patientRecordDao().getRecordById(recordId);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                
                if (record != null) {
                    // Display record data
                    tvPatientName.setText(record.getPatientName());
                    tvHospitalName.setText(record.getHospitalName());
                    tvDiagnosis.setText(record.getDiagnosis());
                    tvPrescription.setText(record.getPrescription().isEmpty() ? "None" : record.getPrescription());
                    tvNotes.setText(record.getNotes().isEmpty() ? "None" : record.getNotes());
                    tvDate.setText(record.getDate());
                } else {
                    Toast.makeText(ViewRecordActivity.this, "Error: Record not found", Toast.LENGTH_SHORT).show();
                    finish();
                }
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
