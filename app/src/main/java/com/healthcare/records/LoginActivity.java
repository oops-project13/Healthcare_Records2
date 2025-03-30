package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.User;

/**
 * Login screen for both hospital staff and patients.
 * Authenticates users and directs them to the appropriate dashboard based on their role.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etAadharId, etPassword;
    private Button btnLogin;
    private TextView tvSignup;
    private ProgressBar progressBar;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        etAadharId = findViewById(R.id.etAadharId);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);
        progressBar = findViewById(R.id.progressBar);

        // Check if user is already logged in
        checkLoginStatus();

        // Set up login button click listener
        btnLogin.setOnClickListener(view -> attemptLogin());

        // Set up signup text view click listener
        tvSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Checks if user is already logged in and redirects accordingly
     */
    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("healthcare_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);
        String userRole = sharedPreferences.getString("user_role", null);

        if (userId != null && userRole != null) {
            navigateToAppropriateDashboard(userRole);
        }
    }

    /**
     * Attempts to login the user with provided credentials
     */
    private void attemptLogin() {
        String aadharId = etAadharId.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (aadharId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Execute login on a background thread
        new Thread(() -> {
            final User user = database.userDao().getUserByAadharAndPassword(aadharId, password);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                
                if (user != null) {
                    // Save login information
                    saveLoginInfo(user);
                    
                    // Navigate to appropriate dashboard
                    navigateToAppropriateDashboard(user.getRole());
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    /**
     * Saves user login information to SharedPreferences
     */
    private void saveLoginInfo(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("healthcare_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", user.getUserId());
        editor.putString("user_role", user.getRole());
        editor.putString("aadhar_id", user.getAadharId());
        editor.putString("user_name", user.getName());
        editor.apply();
    }

    /**
     * Navigates to the appropriate dashboard based on user role
     */
    private void navigateToAppropriateDashboard(String role) {
        Intent intent;
        if (role.equals("hospital")) {
            intent = new Intent(LoginActivity.this, HospitalDashboardActivity.class);
        } else {
            intent = new Intent(LoginActivity.this, PatientDashboardActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
