package com.ifox.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;
import com.ifox.exception.InvalidFomatException;
import com.ifox.util.Validator;

@Service
public class DefaultSnapshotService implements SnapshotService {

    private static final String INVALID_FORMAT = "Invalid Format";
    private static final String CONFLICT_FOUND = "Conflict found at";
    private Map<String, Data> dataMap = new LinkedHashMap<>();

    private Map<String, AnimalLocation> animalLocationMap = new TreeMap<>();


    @Override
    public String getSnapshot(List<Data> datas, String id) {
        checkIdFormat(id);
        initDataMap(datas);
        initAnimalMap(id);
        return getResult();
    }

    private void checkIdFormat(String id) {
        if (!Validator.idValidate(id)) {
            clear();
            throw  new InvalidFomatException("Invalid Format");

        }
    }

    private void initAnimalMap(String id) {
        for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
            checkTimeFormat(entry);
            checkIdFormat(entry.getKey());
            for (AnimalLocation animalLocation : entry.getValue().getAnimalLocations()) {
                checkConflict(entry, animalLocation);
                animalLocationMap.put(animalLocation.getAnimalId(), animalLocation);
            }
            if (entry.getKey().equals(id)) {
                break;
            }
        }
    }

    private void checkTimeFormat(Map.Entry<String, Data> entry) {
        if (!Validator.timeValidate(entry.getValue().getTime())) {
            clear();
            throw new InvalidFomatException(INVALID_FORMAT);
        }
    }

    private void checkConflict(Map.Entry<String, Data> entry, AnimalLocation animalLocation) {
        if (animalLocationMap.containsKey(animalLocation.getAnimalId())) {
            AnimalLocation preLocation = animalLocationMap.get(animalLocation.getAnimalId());
            int x = preLocation.getxPrevious() + preLocation.getxChange();
            int y = preLocation.getyPrevious() + preLocation.getyChange();
            if (x != animalLocation.getxPrevious() || y != animalLocation.getyPrevious()) {
                clear();
                throw new InvalidFomatException(CONFLICT_FOUND + " " + entry.getKey());
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
        clear();
        return result.toString();
    }

    private void clear() {
        dataMap.clear();
        animalLocationMap.clear();
    }
}
