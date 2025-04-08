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
     * - First digit is not 0 or 1
     * - Contains no alphabetic characters
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
        
        // Check if first digit is 0 or 1 (not allowed)
        if (aadhar.charAt(0) == '0' || aadhar.charAt(0) == '1') {
            return false;
        }
        
        // Check if all characters are digits (ensures no alphabets)
        for (int i = 0; i < aadhar.length(); i++) {
            if (!Character.isDigit(aadhar.charAt(i))) {
                return false;
            }
        }
        
        // Additional Verhoeff algorithm check could be implemented here
        // for more sophisticated validation
        
        return true;
    }
    
    /**
     * Validates whether the given string is a valid Hospital NIN number.
     * A Hospital NIN is valid if:
     * - It contains exactly 10 digits
     * - All characters are digits
     *
     * @param nin The NIN number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidHospitalNIN(String nin) {
        // Check if null or empty
        if (nin == null || nin.trim().isEmpty()) {
            return false;
        }
        
        // Remove spaces if any
        nin = nin.replaceAll("\\s+", "");
        
        // Check if length is 10
        if (nin.length() != 10) {
            return false;
        }
        
        // Check if all characters are digits
        for (int i = 0; i < nin.length(); i++) {
            if (!Character.isDigit(nin.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }
}
