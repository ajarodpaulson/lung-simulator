package model.exception;

/**
 * Represents an exception that can be thrown when a method is given an invalid
 * argument
 */

public class InvalidArgumentException extends Exception {
    private final Object arg;

    /**
     * Constructs a new InvalidArgumentException for @param arg and provides a message
     */
    public InvalidArgumentException(Object arg, String msg) {
        super(String.valueOf(arg) + " " + msg);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }
}