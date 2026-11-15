package util;

import model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/** Saves and loads the ArrayList of Student objects through serialization. */
public final class FileHandler {
    private FileHandler() {
        // Utility class: prevent objects from being created.
    }

    public static void saveStudents(List<Student> students, String fileName) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(new ArrayList<>(students));
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Student> loadStudents(String fileName)
            throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object savedData = input.readObject();
            if (savedData instanceof ArrayList<?>) {
                return (ArrayList<Student>) savedData;
            }
            throw new IOException("The saved file does not contain student records.");
        }
    }
}
