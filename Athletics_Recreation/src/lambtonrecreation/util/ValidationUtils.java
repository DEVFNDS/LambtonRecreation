package lambtonrecreation.util;

public class ValidationUtils {

	// Check if a string is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    // Check if a string is within the specified length limit
    public static boolean isWithinLengthLimit(String value, int maxLength) {
        return value != null && value.length() <= maxLength;
    }

}
