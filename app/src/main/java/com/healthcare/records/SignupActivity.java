package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.User;
import com.healthcare.records.util.AadharValidator;

import java.util.UUID;

/**
 * SignupActivity handles registration of new users (both hospital staff and patients).
 * It validates the Aadhar number and creates a new user in the database.
 */
public class SignupActivity extends AppCompatActivity {

    private EditText etName, etAadharId, etPassword, etConfirmPassword;
    private RadioGroup rgUserType;
    private RadioButton rbPatient, rbHospital;
    private Button btnSignup;
    private TextView tvLogin;
    private ProgressBar progressBar;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        etName = findViewById(R.id.etName);
        etAadharId = findViewById(R.id.etAadharId);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        rgUserType = findViewById(R.id.rgUserType);
        rbPatient = findViewById(R.id.rbPatient);
        rbHospital = findViewById(R.id.rbHospital);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);

        // Set up signup button click listener
        btnSignup.setOnClickListener(view -> attemptSignup());

        // Set up login text view click listener
        tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Attempts to register a new user with the provided information
     */
    private void attemptSignup() {
        String name = etName.getText().toString().trim();
        String aadharId = etAadharId.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        
        // Get selected user type
        int selectedRadioButtonId = rgUserType.getCheckedRadioButtonId();
        String userRole = (selectedRadioButtonId == R.id.rbHospital) ? "hospital" : "patient";

        // Validate input
        if (name.isEmpty() || aadharId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Validate Aadhar number
        if (!AadharValidator.isValidAadhar(aadharId)) {
            Toast.makeText(this, "Invalid Aadhar number", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Check if user with same Aadhar already exists
        new Thread(() -> {
            final User existingUser = database.userDao().getUserByAadhar(aadharId);
            
            if (existingUser != null) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignupActivity.this, "User with this Aadhar already exists", Toast.LENGTH_SHORT).show();
                });
                return;
            }

            // Create a new user
            String userId = UUID.randomUUID().toString();
            User newUser = new User(userId, name, aadharId, password, userRole);
            
            // Insert the new user into the database
            database.userDao().insert(newUser);
            
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                
                // Redirect to login page
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}
