package model.exception;

public class InvalidArgumentException extends Exception {
    private final Object arg;

    public InvalidArgumentException(Object arg, String msg) {
        super(String.valueOf(arg) + " " + msg);
        this.arg = arg;
    }

    public Object getArg() {
        return arg;
    }
}