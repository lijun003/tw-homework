package com.ifox.controller;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alibaba.fastjson.JSON;
import com.ifox.BaseIntegrationTest;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.domain.RequestBody;

public class SnapshotControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private SnapshotController snapshotController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(snapshotController).build();

    }

    @Test
    public void should_return_right_snapshot_given_historyData_and_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/snapshot").contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8).content(generateRequestBody()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("cat1 3 4" + "\n" + "cat2 5 9"))
                .andDo(print());

    }

    private String generateRequestBody() {
        Data data1 = new Data();
        data1.setId("e4e87cb2-2222-33ed-44qw-11111999ed13");
        data1.setTime("2016/09/20 08:30:50");
        data1.setAnimalLocations(asList(new AnimalLocation("cat1", 3, 4, 0 , 0),
                new AnimalLocation("cat2", 5, 9, 0, 0)));
        RequestBody requestBody = new RequestBody();
        requestBody.setId("e4e87cb2-2222-33ed-44qw-11111999ed13");
        requestBody.setDatas(asList(data1));
        return JSON.toJSONString(requestBody);
    }
}
