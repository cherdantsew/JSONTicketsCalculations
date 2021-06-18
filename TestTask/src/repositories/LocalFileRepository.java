package repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LocalFileRepository {

    public String getJSONContent() {
        File jsonFile = new File("D:\\программирование\\tickets.json");

        try {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(jsonFile);
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
