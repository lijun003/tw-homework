package com.ifox.service;

import static com.ifox.util.Validator.checkIdFormat;
import static com.ifox.util.Validator.checkTimeFormat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.exception.InvalidFomatException;

@Service
public class DefaultSnapshotService implements SnapshotService {

    private static final String CONFLICT_FOUND = "Conflict found at";
    private Map<String, Data> dataMap = new LinkedHashMap<>();

    private Map<String, AnimalLocation> animalLocationMap = new TreeMap<>();


    @Override
    public String getSnapshot(List<Data> datas, String id) {
        clear();
        checkIdFormat(id);
        initDataMap(datas);
        initAnimalMap(id);
        return getResult();
    }


    private void initAnimalMap(String id) {
        for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
            checkTimeFormat(entry.getValue().getTime());
            checkIdFormat(entry.getKey());
            entry.getValue().getAnimalLocations().forEach(animalLocation -> {
                checkConflict(entry.getKey(), animalLocation);
                animalLocationMap.put(animalLocation.getAnimalId(), animalLocation);
            });
            if (entry.getKey().equals(id)) {
                break;
            }
        }
    }


    private void checkConflict(String id, AnimalLocation animalLocation) {
        if (animalLocationMap.containsKey(animalLocation.getAnimalId())) {
            AnimalLocation preLocation = animalLocationMap.get(animalLocation.getAnimalId());
            int x = preLocation.getxPrevious() + preLocation.getxChange();
            int y = preLocation.getyPrevious() + preLocation.getyChange();
            if (x != animalLocation.getxPrevious() || y != animalLocation.getyPrevious()) {
                throw new InvalidFomatException(CONFLICT_FOUND + " " + id);
            }
        }
    }

    private void initDataMap(List<Data> datas) {
        datas.forEach(data -> dataMap.put(data.getId(), data));
    }

    private String getResult() {
        StringBuilder result = new StringBuilder();
        animalLocationMap.entrySet().forEach(entry -> {
            AnimalLocation animalLocation = entry.getValue();
            result.append(entry.getKey()).append(" ")
                    .append(animalLocation.getxPrevious() + animalLocation.getxChange())
                    .append(" ")
                    .append(animalLocation.getyPrevious() + animalLocation.getyChange())
                    .append("\n");
        });
        return result.toString();
    }

    private void clear() {
        dataMap.clear();
        animalLocationMap.clear();
    }
}
