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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.adapter.PatientRecordAdapter;
import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Dashboard for hospital staff members.
 * Allows viewing, adding, and editing patient records.
 */
public class HospitalDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvNoRecords;
    private FloatingActionButton fabAddRecord;
    private AppDatabase database;
    private PatientRecordAdapter adapter;
    private String hospitalId;
    private String hospitalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_dashboard);

        // Set up action bar
        setTitle("Hospital Dashboard");

        // Get user information from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("healthcare_prefs", MODE_PRIVATE);
        hospitalId = sharedPreferences.getString("user_id", "");
        hospitalName = sharedPreferences.getString("user_name", "Hospital");

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvNoRecords = findViewById(R.id.tvNoRecords);
        fabAddRecord = findViewById(R.id.fabAddRecord);

        // Set up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PatientRecordAdapter(this, true, record -> {
            // Handle record click - edit record
            Intent intent = new Intent(HospitalDashboardActivity.this, EditRecordActivity.class);
            intent.putExtra("RECORD_ID", record.getRecordId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Set up add record button
        fabAddRecord.setOnClickListener(view -> {
            Intent intent = new Intent(HospitalDashboardActivity.this, AddRecordActivity.class);
            intent.putExtra("HOSPITAL_ID", hospitalId);
            intent.putExtra("HOSPITAL_NAME", hospitalName);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPatientRecords();
    }

    /**
     * Loads all patient records from the database
     */
    private void loadPatientRecords() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoRecords.setVisibility(View.GONE);

        new Thread(() -> {
            final List<PatientRecord> records = database.patientRecordDao().getAllRecords();
            
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
        Intent intent = new Intent(HospitalDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
