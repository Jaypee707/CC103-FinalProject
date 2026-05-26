
package Game;

// InputValidator
public class InputValidator {

    // Check if it's a valid number
    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check if number is between 1-100
    public static boolean isInRange(int number) {
        return number >= 1 && number <= 100;
    }
}


