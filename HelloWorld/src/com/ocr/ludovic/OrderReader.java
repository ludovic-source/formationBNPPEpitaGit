package com.ocr.ludovic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OrderReader {

    public static void lireFichierMenu() {

        Path path = Paths.get("C:/Users/stagiaire/IdeaProjects/HelloWorld/src/com/ocr/ludovic/menu.csv");
        try {
            List<String> menu = Files.readAllLines(path);
            for (String ligne : menu) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
