package com.healthcare.records.util;

/**
 * Utility class for validating Aadhar numbers.
 */
public class AadharValidator {

    /**
     * Validates whether the given string is a valid Aadhar number.
     * An Aadhar number is valid if:
     * - It contains exactly 12 digits
     * - All characters are digits
     *
     * @param aadhar The Aadhar number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAadhar(String aadhar) {
        // Check if null or empty
        if (aadhar == null || aadhar.trim().isEmpty()) {
            return false;
        }
        
        // Remove spaces if any
        aadhar = aadhar.replaceAll("\\s+", "");
        
        // Check if length is 12
        if (aadhar.length() != 12) {
            return false;
        }
        
        // Check if all characters are digits
        for (int i = 0; i < aadhar.length(); i++) {
            if (!Character.isDigit(aadhar.charAt(i))) {
                return false;
            }
        }
        
        // Additional Verhoeff algorithm check could be implemented here
        // for more sophisticated validation
        
        return true;
    }
}
