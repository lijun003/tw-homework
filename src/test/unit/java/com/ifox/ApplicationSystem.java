package com.ifox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.ifox.service.SnapshotService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationSystem {
    @Autowired
    private SnapshotService defaultSnapshotService;

    @Test
    public void output_result() {
        String historyData = "e4e87cb2" + "\n" + "2016/09/20 08:30:45" + "\n" + "cat1 10 9" + "\n\n" +
                "e4e87cb3" + "\n" + "2016/09/20 08:30:50" + "\n" + "cat1 10 9 2 -1" + "\n" +
                "cat2 2 3" + "\n\n"
                + "e4e87cb4" + "\n" + "2016/09/20 08:31:05" + "\n" + "cat1 12 8 3 4";
        System.out.println(defaultSnapshotService.getSnapshot(historyData, "e4e87cb4"));
    }
}
