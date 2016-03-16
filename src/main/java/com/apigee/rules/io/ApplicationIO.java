package com.apigee.rules.io;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * A wrapper implementation for input and output operations.
 *
 * @author Anish Kurian.
 */
public class ApplicationIO {

    private PrintStream printer;
    private Scanner reader;

    public ApplicationIO() {
        printer = System.out;
        reader = new Scanner(System.in);
    }

    public ApplicationIO(final PrintStream printer, final Scanner reader) {
        this.printer = printer;
        this.reader = reader;
    }

    /**
     * Prints given message using {@link #printer}.
     *
     * @param message the message to be printed.
     */
    public void printMessage(final String message) {
        printer.println(message);
    }

    /**
     * Reads a string value after printing the given message.
     *
     * @param message the message to be printed.
     * @return the value read.
     */
    public String getString(final String message) {
        printer.println(message);
        return reader.next();
    }

    /**
     * Reads a string value using {@link #reader}.
     *
     * @return the value read.
     */
    public String getString() {
        return reader.next();
    }

    /**
     * Reads an integer value after printing given message.
     *
     * @param message the message to be printed.
     * @return the value read.
     */
    public Integer getInteger(final String message) {
        printer.println(message);
        return reader.nextInt();
    }
}
