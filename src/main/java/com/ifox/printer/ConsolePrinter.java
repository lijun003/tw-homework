package com.ifox.printer;

import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter implements IPrinter {
    @Override
    public void print(String input) {
        System.out.print(input);
    }
}
