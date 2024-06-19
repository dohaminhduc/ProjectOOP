package duck.manager;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static boolean fileExists(String directoryPath, String fileName) {
        File file = new File(directoryPath, fileName);
        return file.exists() && !file.isDirectory();
    }

    public static void serializeObject(Object obj, String directoryPath, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(directoryPath + "/" + fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String directoryPath, String fileName) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(directoryPath + "/" + fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static List<Flight> deserializeFlights(String directoryPath) {
        List<Flight> flights = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                   Flight flight = (Flight) deserializeObject(file.getParent().toString(), file.getFileName().toString());
                    if (flight != null) {
                        flights.add(flight);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
