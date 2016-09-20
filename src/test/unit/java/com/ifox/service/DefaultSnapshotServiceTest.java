package com.ifox.service;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
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
import com.ifox.exception.InvalidFomatException;
import com.ifox.parser.IParser;

public class DefaultSnapshotServiceTest {


    @InjectMocks
    private DefaultSnapshotService defaultSnapshotService;

    @Mock
    private IParser dataParser;

    private List<Data> datas = new LinkedList<>();


    @Before
    public void setUp() throws Exception {
        initMocks(this);

        Data data0 = new Data();
        data0.setId("e4e87cb2-2222-33ed-44qw-11111999ed11");
        data0.setTime("2016/09/20 08:30:45");
        data0.setAnimalLocations(asList(generateAnimalLocation("cat1", 3, 4, 0 , 0)));
        Data data1 = new Data();
        data1.setId("e4e87cb2-2222-33ed-44qw-11111999ed13");
        data1.setTime("2016/09/20 08:30:50");
        data1.setAnimalLocations(asList(generateAnimalLocation("cat1", 3, 4, 4 , -1),
                generateAnimalLocation("cat2", 5, 9, 0, 0)));
        datas.add(data0);
        datas.add(data1);
    }

    @Test
    public void should_return_right_snapshot_given_historyData_and_id() throws Exception {
        assertThat(defaultSnapshotService.getSnapshot(datas, "e4e87cb2-2222-33ed-44qw-11111999ed13"),
                Is.is("cat1 7 3" + "\n" + "cat2 5 9" + "\n"));

    }

    @Test(expected = InvalidFomatException.class)
    public void should_throws_right_exception() {
        defaultSnapshotService.getSnapshot(datas, "1111");

    }

    private AnimalLocation generateAnimalLocation(String animalId, int x, int y, int xChange, int yChange) {
        AnimalLocation animalLocation = new AnimalLocation();
        animalLocation.setAnimalId(animalId);
        animalLocation.setxPrevious(x);
        animalLocation.setyPrevious(y);
        animalLocation.setxChange(xChange);
        animalLocation.setyChange(yChange);
        return animalLocation;
    }
}
