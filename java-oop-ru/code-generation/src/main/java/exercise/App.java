package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
//import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path filePath, Car car) throws Exception {
        String json = car.serialize();
        Files.writeString(filePath, json);
    }

    public static Car extract(Path filePath) throws Exception {
        String json = Files.readString(filePath);
        return Car.deserialize(json);
    }
}
// END
