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
     * Get user by identifier (Aadhar for patients, NIN for hospitals)
     */
    @Query("SELECT * FROM users WHERE identifier = :identifier")
    User getUserByIdentifier(String identifier);

    /**
     * Get user by Aadhar ID (for backward compatibility)
     * @deprecated Use getUserByIdentifier instead
     */
    @Deprecated
    @Query("SELECT * FROM users WHERE identifier = :aadharId")
    User getUserByAadhar(String aadharId);

    /**
     * Get user by identifier and password for authentication
     */
    @Query("SELECT * FROM users WHERE identifier = :identifier AND password = :password")
    User getUserByIdentifierAndPassword(String identifier, String password);

    /**
     * Get user by Aadhar ID and password for authentication (for backward compatibility)
     * @deprecated Use getUserByIdentifierAndPassword instead
     */
    @Deprecated
    @Query("SELECT * FROM users WHERE identifier = :aadharId AND password = :password")
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
