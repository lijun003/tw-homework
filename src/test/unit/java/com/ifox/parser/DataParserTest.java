package com.ifox.parser;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class DataParserTest {

    private String historyData;

    private IParser dataParser;

    @Before
    public void setUp() throws Exception {
        dataParser = new DataParser();
        historyData = "e4e87cb2" + "\n" + "2016/09/20 08:30:45" + "\n" + "cat1 3 4" + "\n\n" +
                "e4e87cb3" + "\n" + "2016/09/20 08:30:50" + "\n" + "cat1 3 4 4 -1" + "\n" +
                "cat2 5 9";

    }

    @Test
    public void should_return_right_data_given_historyData() throws Exception {
        assertThat(dataParser.parseData(historyData).size(), is(2));
        assertThat(dataParser.parseData(historyData).get(0).getId(), is("e4e87cb2"));
        assertThat(dataParser.parseData(historyData).get(0).getAnimalLocations().size(), is(1));
        assertThat(dataParser.parseData(historyData).get(1).getId(), is("e4e87cb3"));
        assertThat(dataParser.parseData(historyData).get(1).getAnimalLocations().size(), is(2));
        assertThat(dataParser.parseData(historyData).get(1).getAnimalLocations().get(1).getAnmimalId(),
                is("cat2"));
        assertThat(dataParser.parseData(historyData).get(1).getAnimalLocations().get(0).getyChange(),
                is(-1));

    }
}
