package com.ifox.parser;

import static java.lang.Integer.parseInt;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;

@Component
public class DataParser implements IParser {
    @Override
    public List<Data> parseData(String historyData) {
        List<Data> result = new LinkedList<>();
        String[] temp = parseString(historyData, "\n\n");
        for (String data : temp) {
            result.add(generateData(data));
        }
        return result;
    }

    private Data generateData(String data) {
        Data dataResult = new Data();
        String[] temp = parseString(data, "\n");
        dataResult.setId(temp[0]);
        dataResult.setTime(temp[1]);
        List<AnimalLocation> animalLocations = new LinkedList<>();
        for (int i = 2; i < temp.length; i++) {
            AnimalLocation animalLocation = new AnimalLocation();
            String[] animalInfo = parseString(temp[i], " ");
            setAnimalLocation(animalLocation, animalInfo);
            animalLocations.add(animalLocation);
            dataResult.setAnimalLocations(animalLocations);

        }
        return dataResult;
    }

    private void setAnimalLocation(AnimalLocation animalLocation, String[] animalInfo) {
        animalLocation.setAnmimalId(animalInfo[0]);
        animalLocation.setXPrevious(parseInt(animalInfo[1]));
        animalLocation.setYPrevious(parseInt(animalInfo[2]));
        animalLocation.setXChange(animalInfo.length == 5 ? parseInt(animalInfo[3]) : 0);
        animalLocation.setYChange(animalInfo.length == 5 ? parseInt(animalInfo[4]) : 0);
    }

    private String[] parseString(String history, String splitter) {
        return history.split(splitter);
    }

}
