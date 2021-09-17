import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(String fileDir) {
        this.tasks = new ArrayList<>();
        String filePath = fileDir + "/duke.txt";
        try {
            File currDirectory = new File(fileDir);
            if (!currDirectory.exists()) {
                currDirectory.mkdir();
            }
            File currPath = new File(filePath);
            if (!currPath.exists()) {
                currPath.createNewFile();
                saveTasksToFile(filePath, tasks);
            }
            loadTasksFromFile(filePath);
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    private static void saveTasksToFile(String filePath, ArrayList<Task> tasks) {
        try {
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(filePath));
            obj.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private static void loadTasksFromFile(String filePath) {
        try {
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(filePath));
            @SuppressWarnings("unchecked")
            ArrayList<Task> temp = (ArrayList<Task>) obj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
