package com.ifox.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.domain.RequestBody;
import com.ifox.service.SnapshotService;

public class SnapshotControllerTest {

    @InjectMocks
    private SnapshotController snapshotController;

    @Mock
    private SnapshotService snapshotService;
    private List<Data> datas = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
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
        initMocks(this);
        when(snapshotService.getSnapshot(datas, "e4e87cb3"))
                .thenReturn("cat1 7 3" + "\n" + "cat2 5 9" + "\n");
    }

    @Test
    public void should_return_right_snapshot() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setId("e4e87cb3");
        requestBody.setDatas(datas);
        ResponseEntity responseEntity = snapshotController.getSnapshot(requestBody);
        assertThat(responseEntity.getStatusCode(), Is.is(OK));
        assertThat(responseEntity.getBody(), Is.is("cat1 7 3" + "\n" + "cat2 5 9" + "\n"));


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
