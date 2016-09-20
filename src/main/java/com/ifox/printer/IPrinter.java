package com.ifox.printer;

import org.springframework.stereotype.Component;

@Component
public interface IPrinter {
    void print(String input);
}
