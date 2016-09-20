package com.ifox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.ifox.parser.IParser;
import com.ifox.printer.IPrinter;
import com.ifox.printer.OutputPrinter;
import com.ifox.service.SnapshotService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationSystem {
    @Autowired
    private SnapshotService defaultSnapshotService;

    @Autowired
    private IParser dataParser;

    @Autowired
    private IPrinter consolePrinter;

    @Test
    public void output_result() {
        String historyData = "e4e87cb2-2222-33ed-44qw-11111999ed11"
                + "\n" + "2016/09/20 08:30:45" + "\n" + "cat1 10 9" + "\n\n" +
                "e4e87cb2-2222-33ed-44qw-11111999ed12"
                + "\n" + "2016/09/20 08:30:50" + "\n" + "cat1 10 9 2 -1" + "\n" +
                "cat2 2 3" + "\n\n"
                + "e4e87cb2-2222-33ed-44qw-11111999ed13" + "\n"
                + "2016/09/20 08:31:05" + "\n" + "cat1 12 8 3 4";
        String  output = defaultSnapshotService
                .getSnapshot(dataParser.parseData(historyData), "e4e87cb2-2222-33ed-44qw-11111999ed13");
        OutputPrinter.getInstance().print(output, consolePrinter);
    }
}
