package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\resources\\Profile.txt");
        Profile profile = new FileReader().getDataFromFile(file);
        System.out.println(profile);
    }
}
