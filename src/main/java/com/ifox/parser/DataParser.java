package com.ifox.parser;

import static java.lang.Integer.parseInt;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.ifox.domain.AnimalLocation;
import com.ifox.domain.Data;

@Component
public class DataParser implements IParser {

    private static final String SPACER_SPLITTER = " ";
    private static final String NEW_LINE_SPLITTER = "\n";
    private static final String SPACE_LINE_SPLITTER = "\n\n";
    private static final int ANIMAL_INFO_LENGTH = 5;

    @Override
    public List<Data> parseData(String historyData) {
        List<Data> result = new LinkedList<>();
        String[] temp = parseString(historyData, SPACE_LINE_SPLITTER);
        for (String data : temp) {
            result.add(generateData(data));
        }
        return result;
    }

    private Data generateData(String input) {
        Data data = new Data();
        String[] temp = parseString(input, NEW_LINE_SPLITTER);
        data.setId(temp[0]);
        data.setTime(temp[1]);
        data.setAnimalLocations(generateAnimalLocations(temp));
        return data;
    }

    private List<AnimalLocation> generateAnimalLocations(String[] dataInfo) {
        List<AnimalLocation> animalLocations = new LinkedList<>();
        for (int i = 2; i < dataInfo.length; i++) {
            AnimalLocation animalLocation = new AnimalLocation();
            String[] animalInfo = parseString(dataInfo[i], SPACER_SPLITTER);
            setAnimalLocation(animalLocation, animalInfo);
            animalLocations.add(animalLocation);
        }
        return animalLocations;
    }

    private void setAnimalLocation(AnimalLocation animalLocation, String[] animalInfo) {
        animalLocation.setAnimalId(animalInfo[0]);
        animalLocation.setxPrevious(parseInt(animalInfo[1]));
        animalLocation.setyPrevious(parseInt(animalInfo[2]));
        animalLocation.setxChange(animalInfo.length == ANIMAL_INFO_LENGTH ? parseInt(animalInfo[3]) : 0);
        animalLocation.setyChange(animalInfo.length == ANIMAL_INFO_LENGTH ? parseInt(animalInfo[4]) : 0);
    }

    private String[] parseString(String history, String splitter) {
        return history.split(splitter);
    }

}
