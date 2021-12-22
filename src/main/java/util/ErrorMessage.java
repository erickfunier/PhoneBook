package util;

public class ErrorMessage {
    private final boolean error;
    private final String message;

    public ErrorMessage(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
