package com.epam.mjc.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {

        StringBuilder content = new StringBuilder();
        try (RandomAccessFile aFile = new RandomAccessFile(file.getAbsolutePath(), "r");
             FileChannel inChannel = aFile.getChannel()) {

            long fileSize = inChannel.size();

            //Create buffer of the file size
            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            inChannel.read(buffer);
            buffer.flip();

            // Verify the file content
            for (int i = 0; i < fileSize; i++) {
                content.append((char) buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] elements = String.valueOf(content).split(":|\n");
        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < elements.length; i += 2) {
            values.put(elements[i].trim(), elements[i + 1].trim());
        }
        Profile profile = new Profile();
        profile.setName(values.get("Name"));
        profile.setAge(Integer.valueOf(values.get("Age")));
        profile.setEmail(values.get("Email"));
        profile.setPhone(Long.valueOf(values.get("Phone")));
        return profile;
    }
}
