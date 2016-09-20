package com.ifox.parser;


import java.util.List;

import org.springframework.stereotype.Component;
import com.ifox.domain.Data;

@Component
public interface IParser {
    List<Data> parseData(String historyData);
}
