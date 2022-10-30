package artemget.example.feature_toggle.exception;

public class FeatureDisabledException extends RuntimeException {
    public FeatureDisabledException() {
    }

    public FeatureDisabledException(String message) {
        super(message);
    }

    public FeatureDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeatureDisabledException(Throwable cause) {
        super(cause);
    }

    public FeatureDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
