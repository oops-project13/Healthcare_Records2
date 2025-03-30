package com.healthcare.records.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.healthcare.records.database.entity.User;

import java.util.List;

/**
 * Data Access Object for the User entity.
 * Provides methods to interact with the user table in the database.
 */
@Dao
public interface UserDao {

    /**
     * Insert a new user into the database
     */
    @Insert
    void insert(User user);

    /**
     * Update an existing user in the database
     */
    @Update
    void update(User user);

    /**
     * Get user by ID
     */
    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(String userId);

    /**
     * Get user by Aadhar ID
     */
    @Query("SELECT * FROM users WHERE aadharId = :aadharId")
    User getUserByAadhar(String aadharId);

    /**
     * Get user by Aadhar ID and password for authentication
     */
    @Query("SELECT * FROM users WHERE aadharId = :aadharId AND password = :password")
    User getUserByAadharAndPassword(String aadharId, String password);

    /**
     * Get all users with a specific role
     */
    @Query("SELECT * FROM users WHERE role = :role")
    List<User> getUsersByRole(String role);

    /**
     * Get all patients in the system
     */
    @Query("SELECT * FROM users WHERE role = 'patient'")
    List<User> getAllPatients();

    /**
     * Get all hospital staff in the system
     */
    @Query("SELECT * FROM users WHERE role = 'hospital'")
    List<User> getAllHospitals();
}
