package com.ifox.service;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.parser.IParser;

public class DefaultSnapshotServiceTest {


    @InjectMocks
    private DefaultSnapshotService defaultSnapshotService;

    @Mock
    private IParser dataParser;

    private String historyData;

    @Before
    public void setUp() throws Exception {
        historyData = "e4e87cb2" + "\n" + "2016/09/20 08:30:45" + "\n" + "cat1 3 4" + "\n\n" +
                "e4e87cb3" + "\n" + "2016/09/20 08:30:50" + "\n" + "cat1 3 4 4 -1" + "\n" +
                "cat2 5 9";
        initMocks(this);
    }

    @Test
    public void should_return_right_snapshot_given_historyData_and_id() throws Exception {
        List<Data> datas = new LinkedList<>();
        Data data0 = new Data();
        data0.setId("e4e87cb2");
        data0.setTime("2016/09/20 08:30:45");
        data0.setAnimalLocations(asList(generateAnimalLocation("cat1", 3, 4, 0 , 0)));
        Data data1 = new Data();
        data1.setId("e4e87cb3");
        data1.setTime("2016/09/20 08:30:50");
        data1.setAnimalLocations(asList(generateAnimalLocation("cat1", 3, 4, 4 , -1),
                generateAnimalLocation("cat2", 5, 9, 0, 0)));
        datas.add(data0);
        datas.add(data1);
        when(dataParser.parseData(historyData)).thenReturn(datas);
        assertThat(defaultSnapshotService.getSnapshot(historyData, "e4e87cb3"),
                Is.is("cat1 7 3" + "\n" + "cat2 5 9" + "\n"));

    }

    private AnimalLocation generateAnimalLocation(String animalId, int x, int y, int xChange, int yChange) {
        AnimalLocation animalLocation = new AnimalLocation();
        animalLocation.setAnmimalId(animalId);
        animalLocation.setxPrevious(x);
        animalLocation.setyPrevious(y);
        animalLocation.setxChange(xChange);
        animalLocation.setyChange(yChange);
        return animalLocation;
    }
}
