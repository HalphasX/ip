import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Commands {
    private static TaskList tasks;
    private static final String EMPTY_COMMAND_1 = "\n____________________________________________________________\n" +
            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
            "____________________________________________________________";
    private static final String EMPTY_COMMAND_2 = "\n____________________________________________________________\n" +
            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
            "____________________________________________________________";
    private static final String EMPTY_COMMAND_3 = "\n____________________________________________________________\n" +
            "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
            "____________________________________________________________";
    private static final String WRONG_COMMAND_1 = "\n____________________________________________________________\n" +
            "The command format for deadline is wrong. The command format is supposed to be : \n" +
            "deadline <your task> / <your deadline> ! \n" +
            "____________________________________________________________";
    private static final String WRONG_COMMAND_2 = "\n____________________________________________________________\n" +
            "The command format for event is wrong. The command format is supposed to be : \n" +
            "event <your task> / <your event time> ! \n" +
            "____________________________________________________________";
    private static final String NO_COMMAND = "\n____________________________________________________________\n" +
            "Invalid command! \n" +
            "The commands for setting tasks are : 'todo', 'deadline', and 'event'. \n" +
            "The general commands are : 'list', 'delete', 'done', and 'bye'. \n" +
            "____________________________________________________________";

    public static void byeCommand() {
        System.out.println("____________________________________________________________ \n" +
                "Bye. Hope to see you again soon! \n" +
                "____________________________________________________________");
    }

    public static void addCommand(String inputString) throws DukeException {
        String[] word = inputString.split(" ", 2);
        String command = word[0];
        String desc = word[1];
        try {
            switch (command) {
                case "todo": {
                    if (desc == null) {
                        throw new DukeException(EMPTY_COMMAND_1);
                    }
                    ToDo task = new ToDo(desc);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: \n" + task.toString());
                    tasks.add(task);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list. \n" +
                            "____________________________________________________________");
                    break;
                }
                case "deadline": {
                    String[] descriptions = desc.split("/", 2);
                    String description = descriptions[0];
                    if (descriptions.length == 0) {
                        throw new DukeException(WRONG_COMMAND_1);
                    } else if (descriptions.length == 1) {
                        throw new DukeException(EMPTY_COMMAND_2);
                    } else {
                        LocalDate timeline = LocalDate.parse(descriptions[1]);
                        Deadline task = new Deadline(description, timeline);
                        System.out.println("____________________________________________________________\n" +
                                "Got it. I've added this task: \n" + task.toString());
                        tasks.add(task);
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list. \n" +
                                "____________________________________________________________");
                    }
                    break;
                }
                case "event": {
                    String[] descriptions = desc.split("/", 2);
                    String description = descriptions[0];
                    if (descriptions.length == 0) {
                        throw new DukeException(WRONG_COMMAND_2);
                    } else if (descriptions.length == 1) {
                        throw new DukeException(EMPTY_COMMAND_3);
                    } else {
                        LocalDate timeline = LocalDate.parse(descriptions[1]);
                        Event task = new Event(description, timeline);
                        System.out.println("____________________________________________________________\n" +
                                "Got it. I've added this task: \n" + task.toString());
                        tasks.add(task);
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list. \n" +
                                "____________________________________________________________");
                    }
                }
                default:
                    throw new DukeException(NO_COMMAND);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task currTask;
            currTask = tasks.get(i - 1);
            System.out.println(i + "." + currTask.toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void removeCommand(int i) {
        if (tasks.size() < i) {
            System.out.println("____________________________________________________________\n" +
                    "Sorry! There are no tasks with index number \" + i + \"! :( \n" +
                    "____________________________________________________________");
        } else {
            Task iTask = tasks.get(i - 1);
            int removedSize = tasks.size() - 1;
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(iTask.toString());
            System.out.println("Now you have " + removedSize + " task(s) in the list.");
            System.out.println("____________________________________________________________");
            tasks.remove(i - 1);
        }
    }

    public static void doneCommand(int i) {
        if (tasks.size() < i) {
            System.out.println("____________________________________________________________");
            System.out.println("Sorry! I can't find the tasks you ask for! :(");
            System.out.println("____________________________________________________________");
        } else {
            Task iTask = tasks.get(i - 1);
            iTask.statusDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(i + ". " + iTask.toString());
            System.out.println("____________________________________________________________");
        }
    }

}

