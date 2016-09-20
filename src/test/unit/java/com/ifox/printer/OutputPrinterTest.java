package com.ifox.printer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class OutputPrinterTest {


    @Test
    public void should_printer_right_content() throws Exception {
        IPrinter consolePrinter = mock(ConsolePrinter.class);

        OutputPrinter.getInstance().print("expected", consolePrinter);

        verify(consolePrinter).print("expected");

    }
}
