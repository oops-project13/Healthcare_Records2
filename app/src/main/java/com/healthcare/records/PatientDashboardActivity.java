package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.adapter.PatientRecordAdapter;
import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;
import com.healthcare.records.database.entity.User;

import java.util.List;

/**
 * Dashboard for patients to view their medical records.
 * Patients can only view but not modify records.
 */
public class PatientDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvNoRecords, tvWelcomeMessage, tvPatientId;
    private AppDatabase database;
    private PatientRecordAdapter adapter;
    private String patientId;
    private String patientName;
    private String aadharId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        // Set up action bar
        setTitle("Patient Dashboard");

        // Get user information from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("healthcare_prefs", MODE_PRIVATE);
        patientId = sharedPreferences.getString("user_id", "");
        patientName = sharedPreferences.getString("user_name", "Patient");
        aadharId = sharedPreferences.getString("aadhar_id", "");

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvNoRecords = findViewById(R.id.tvNoRecords);
        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
        tvPatientId = findViewById(R.id.tvPatientId);

        // Set welcome message and patient ID
        tvWelcomeMessage.setText("Welcome, " + patientName);
        tvPatientId.setText("Patient ID: " + patientId + "\nAadhar: " + aadharId);

        // Set up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PatientRecordAdapter(this, false, record -> {
            // Handle record click - view record details
            Intent intent = new Intent(PatientDashboardActivity.this, ViewRecordActivity.class);
            intent.putExtra("RECORD_ID", record.getRecordId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPatientRecords();
    }

    /**
     * Loads records specific to the currently logged-in patient
     */
    private void loadPatientRecords() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoRecords.setVisibility(View.GONE);

        new Thread(() -> {
            final List<PatientRecord> records = database.patientRecordDao().getRecordsByPatientId(patientId);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                
                if (records != null && !records.isEmpty()) {
                    adapter.setRecords(records);
                    tvNoRecords.setVisibility(View.GONE);
                } else {
                    tvNoRecords.setVisibility(View.VISIBLE);
                }
            });
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Logs out the current user and returns to login screen
     */
    private void logout() {
        // Clear shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("healthcare_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to login activity
        Intent intent = new Intent(PatientDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
