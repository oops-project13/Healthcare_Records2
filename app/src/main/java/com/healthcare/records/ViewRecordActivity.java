package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;

import java.io.File;

/**
 * Activity for patients to view their medical record details.
 * This is a read-only view of a record.
 */
public class ViewRecordActivity extends AppCompatActivity {

    private TextView tvPatientName, tvHospitalName, tvDiagnosis, tvPrescription, tvNotes, tvDate;
    private TextView tvDoctorContact, tvDoctorName, tvDoctorAvailability, tvSeverityScore;
    private ImageView ivRecordImage;
    private ProgressBar progressBar, pbSeverity;
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
        tvDoctorContact = findViewById(R.id.tvDoctorContact);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvDoctorAvailability = findViewById(R.id.tvDoctorAvailability);
        tvSeverityScore = findViewById(R.id.tvSeverityScore);
        ivRecordImage = findViewById(R.id.ivRecordImage);
        progressBar = findViewById(R.id.progressBar);
        pbSeverity = findViewById(R.id.pbSeverity);

        // Load record data
        loadRecordData();
    }

    /**
     * Loads the details of the selected record from the database
     */
    private void loadRecordData() {
        progressBar.setVisibility(View.VISIBLE);

        // Use a background thread for database operations
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final PatientRecord record = database.patientRecordDao().getRecordById(recordId);
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            
                            if (record != null) {
                                // Display record data
                                tvPatientName.setText(record.getPatientName());
                                tvHospitalName.setText(record.getHospitalName());
                                tvDiagnosis.setText(record.getDiagnosis());
                                tvPrescription.setText(record.getPrescription().isEmpty() ? "None" : record.getPrescription());
                                tvNotes.setText(record.getNotes().isEmpty() ? "None" : record.getNotes());
                                tvDate.setText(record.getDate());
                                
                                // Display doctor's contact information
                                String doctorContact = record.getDoctorContactNumber();
                                tvDoctorContact.setText(doctorContact != null && !doctorContact.isEmpty() ? doctorContact : "Not available");
                                
                                // Display doctor's name if available
                                String doctorName = record.getDoctorName();
                                tvDoctorName.setText(doctorName != null && !doctorName.isEmpty() ? doctorName : "Not available");
                                
                                // Display doctor's availability if available
                                String doctorAvailability = record.getDoctorAvailability();
                                tvDoctorAvailability.setText(doctorAvailability != null && !doctorAvailability.isEmpty() ? 
                                        doctorAvailability : "Not available");
                                
                                // Display severity score
                                int severityScore = record.getSeverityScore();
                                tvSeverityScore.setText(String.valueOf(severityScore));
                                
                                // Set progress and color for severity bar
                                pbSeverity.setProgress(severityScore);
                                
                                // Change color based on severity
                                if (severityScore <= 3) {
                                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50"))); // Green
                                } else if (severityScore <= 7) {
                                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFC107"))); // Yellow
                                } else {
                                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#F44336"))); // Red
                                }
                                
                                // Display image if available
                                String imagePath = record.getImagePath();
                                if (imagePath != null && !imagePath.isEmpty()) {
                                    File imageFile = new File(imagePath);
                                    if (imageFile.exists()) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                                        if (bitmap != null) {
                                            // Apply rotation if needed
                                            int imageRotation = record.getImageRotation();
                                            if (imageRotation != 0) {
                                                Matrix matrix = new Matrix();
                                                matrix.postRotate(imageRotation);
                                                Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, 
                                                        bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                                ivRecordImage.setImageBitmap(rotatedBitmap);
                                            } else {
                                                ivRecordImage.setImageBitmap(bitmap);
                                            }
                                            
                                            ivRecordImage.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(ViewRecordActivity.this, "Error: Record not found", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ViewRecordActivity.this, "Error loading record: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
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
