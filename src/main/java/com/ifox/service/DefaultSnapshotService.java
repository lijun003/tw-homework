package com.ifox.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.parser.IParser;

@Service
public class DefaultSnapshotService implements SnapshotService {

    @Autowired
    private IParser dataParser;

    private Map<String, Data> dataMap = new LinkedHashMap<>();

    private Map<String, AnimalLocation> animalLocationMap = new TreeMap<>();


    @Override
    public String getSnapshot(String historyData, String id) {
        initDataMap(dataParser.parseData(historyData));
        initAnimalMap(id);
        return getResult();
    }

    private void initAnimalMap(String id) {
        for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
            for (AnimalLocation animalLocation : entry.getValue().getAnimalLocations()) {
               animalLocationMap.put(animalLocation.getAnmimalId(), animalLocation);
            }
            if (entry.getKey().equals(id)) {
                break;
            }
        }
    }

    private void initDataMap(List<Data> datas) {
        for (int i = 0; i < datas.size() ; i++) {
            Data data = datas.get(i);
            dataMap.put(data.getId(), data);

        }
    }

    private String getResult() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, AnimalLocation> entry : animalLocationMap.entrySet()) {
            AnimalLocation animalLocation = entry.getValue();
            result.append(entry.getKey()).append(" ")
                    .append(animalLocation.getxPrevious() + animalLocation.getxChange())
                    .append(" ")
                    .append(animalLocation.getyPrevious() + animalLocation.getyChange())
                    .append("\n");

        }
        return result.toString();
    }
}
