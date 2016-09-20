package com.ifox.printer;

public final class OutputPrinter {
    private static OutputPrinter outputPrinter = new OutputPrinter();

    private OutputPrinter() {
    }

    public void print(String input, IPrinter printer) {
        printer.print(input);
    }

    public static OutputPrinter getInstance() {
        return outputPrinter;
    }
}
