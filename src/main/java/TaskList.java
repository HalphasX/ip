import java.util.List;
import java.util.ArrayList;


public class TaskList {
    private List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
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
}
