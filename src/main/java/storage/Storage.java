package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import exception.GeePeeTeeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Represents a storage class that handles the loading and saving of task lists
 * to
 * the hard disk.
 * <p>
 * This class is responsible for loading and saving task lists to the hard disk,
 * allowing the task list to be persisted across multiple sessions of the
 * application.
 * </p>
 */
public class Storage {

    public static final String BASE_PATH = System.getProperty("user.dir");
    private String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file name.
     * @param fileName The name of the file to be associated with the storage.
     * @throws FileNotFoundException If the file specified by the file name does not
     *                               exist and cannot be created.
     * @throws IOException           If an I/O error occurs while creating the file.
     */
    public Storage(String fileName) throws FileNotFoundException, IOException {
        try {
            this.filePath = BASE_PATH + "/" + fileName;
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Loads the task list from the hard disk.
     * @return The task list loaded from the hard disk.
     * @throws FileNotFoundException If the file specified by the file name does not
     *                               exist and cannot be created.
     * @throws GeePeeTeeException    If the file contains an invalid task type.
     * @throws IOException           If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> loadTaskList() throws FileNotFoundException, GeePeeTeeException, IOException {
        ArrayList<Task> result = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;
            switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, LocalDate.parse(parts[3]));
                break;
            case "E":
                task = new Event(description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                break;
            default:
                throw new GeePeeTeeException("File contains invalid task type.");
            }
            if (isDone) {
                task.markAsDone();
            }
            result.add(task);
        }
        s.close();
        return result;
    }

    /**
     * Saves the task list to the hard disk.
     *
     * @param inputList The task list to be saved to the hard disk.
     * @throws GeePeeTeeException If an error occurs while saving the task list to
     *                            the
     *                            hard disk.
     */
    public void saveTaskList(ArrayList<Task> inputList) throws GeePeeTeeException {
        File file = new File(filePath);

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : inputList) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new GeePeeTeeException("Error saving the task list file.");
        }
    }
}
