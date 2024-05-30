package de.junkerjoerg12.Exceptions;

public class InvalidIndexException extends Exception{
    public InvalidIndexException(int index) {
        super("" + index);
    }
}
