package com.healthcare.records.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a user in the system.
 * Can be either a patient or hospital staff member.
 */
@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    private String userId;
    private String name;
    private String aadharId;
    private String password;
    private String role; // "patient" or "hospital"

    /**
     * Constructor for creating a new user
     */
    public User(@NonNull String userId, String name, String aadharId, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.aadharId = aadharId;
        this.password = password;
        this.role = role;
    }

    /**
     * Get the unique user ID
     */
    @NonNull
    public String getUserId() {
        return userId;
    }

    /**
     * Set the unique user ID
     */
    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    /**
     * Get the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user's Aadhar ID
     */
    public String getAadharId() {
        return aadharId;
    }

    /**
     * Set the user's Aadhar ID
     */
    public void setAadharId(String aadharId) {
        this.aadharId = aadharId;
    }

    /**
     * Get the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the user's role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
