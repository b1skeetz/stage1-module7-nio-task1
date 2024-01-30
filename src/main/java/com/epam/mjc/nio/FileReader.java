package com.epam.mjc.nio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Map<String, String> getContext(File file) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        Map<String, String> values = new HashMap<>();
        for(int i = 0; i < strings.size(); i++){
            String[] parts = strings.get(i).split(":|\n");
            values.put(parts[0].trim(), parts[1].trim());
        }
        return values;
    }

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try{
            Map<String, String> values = getContext(file);
            profile.setName(values.get("Name"));
            profile.setAge(Integer.valueOf(values.get("Age")));
            profile.setEmail(values.get("Email"));
            profile.setPhone(Long.valueOf(values.get("Phone")));
        } catch (IOException e){
            e.printStackTrace();
        }
        return profile;
    }
}
