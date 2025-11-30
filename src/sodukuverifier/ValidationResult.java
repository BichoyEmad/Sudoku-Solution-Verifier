package sodukuverifier;
import java.util.*;

public class ValidationResult {
    private static ValidationResult instance;

    public boolean valid = true;
    public List<String> errors = new ArrayList<>();

    private ValidationResult() {}

    public static synchronized ValidationResult getInstance() {
        if (instance == null) {
            instance = new ValidationResult();
        }
        return instance;
    }

    public synchronized void addError(String s) {
        valid = false;
        errors.add(s);
    }

    public void reset() {
        valid = true;
        errors.clear();
    }
}
