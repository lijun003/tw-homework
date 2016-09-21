package com.ifox.service;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

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


    private static final String TIME = "2016/09/20 08:30:45";
    private static final String ID = "e4e87cb2-2222-33ed-44qw-11111999ed13";
    @InjectMocks
    private DefaultSnapshotService defaultSnapshotService;

    @Mock
    private IParser dataParser;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_return_right_snapshot_given_historyData_and_id() throws Exception {
        assertThat(defaultSnapshotService.getSnapshot(generateDatas(3, 4, TIME), ID),
                Is.is("cat1 7 3" + "\n" + "cat2 5 9" + "\n"));

    }

    @Test(expected = InvalidFomatException.class)
    public void should_throws_right_exception_given_wrong_id_format() {
        defaultSnapshotService.getSnapshot(generateDatas(3, 4, TIME), "1111");
    }

    @Test(expected = InvalidFomatException.class)
    public void should_throws_right_exception_given_wrong_time_format() {
        String wrongTime = "2016/09/2000 08:30:50";
        defaultSnapshotService.getSnapshot(generateDatas(3, 4, wrongTime), ID);
    }

    @Test(expected = InvalidFomatException.class)
    public void should_throws_right_exception_given_conflict_data() {
        defaultSnapshotService.getSnapshot(generateDatas(1, 4, TIME), ID);
        defaultSnapshotService.getSnapshot(generateDatas(3, 1, TIME), ID);
        defaultSnapshotService.getSnapshot(generateDatas(1, 1, TIME), ID);
    }

    private List<Data> generateDatas(int xPrevious, int yPrevious, String time) {
        Data data0 = new Data();
        data0.setId("e4e87cb2-2222-33ed-44qw-11111999ed11");
        data0.setTime("2016/09/20 08:30:45");
        data0.setAnimalLocations(asList(new AnimalLocation("cat1", 3, 4, 0, 0)));
        Data data1 = new Data();
        data1.setId("e4e87cb2-2222-33ed-44qw-11111999ed13");
        data1.setTime(time);
        data1.setAnimalLocations(asList(new AnimalLocation("cat1", xPrevious, yPrevious, 4, -1),
                new AnimalLocation("cat2", 5, 9, 0, 0)));
        return asList(data0, data1);
    }

}
