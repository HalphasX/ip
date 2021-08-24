import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static class Commands {
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

        private static void helloCommand() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm \n" + logo);
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
        }

        private static void byeCommand() {
            System.out.println("____________________________________________________________ \n" +
                    "Bye. Hope to see you again soon! \n" +
                    "____________________________________________________________");
        }

        private static void addCommand(String inputString) throws DukeException {
            String[] word = inputString.split(" ", 2);
            String command = word[0];
            if(!command.equals("todo") && !command.equals("deadline") && !command.equals("event")) {
                throw new DukeException(NO_COMMAND);
            } else {
                if (word.length < 2) {
                    switch (command) {
                        case "todo": {
                            throw new DukeException(EMPTY_COMMAND_1);
                        }
                        case "deadline": {
                            throw new DukeException(EMPTY_COMMAND_2);
                        }
                        case "event": {
                            throw new DukeException(EMPTY_COMMAND_3);
                        }
                    }
                } else {
                    String desc = word[1];
                    switch (command) {
                        case "todo": {
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
                            if (descriptions.length < 2) {
                                throw new DukeException(WRONG_COMMAND_1);
                            } else {
                                String timeline = descriptions[1];
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
                            if (descriptions.length < 2) {
                                throw new DukeException(WRONG_COMMAND_2);
                            } else {
                                String timeline = descriptions[1];
                                Event task = new Event(description, timeline);
                                System.out.println("____________________________________________________________\n" +
                                        "Got it. I've added this task: \n" + task.toString());
                                tasks.add(task);
                                System.out.println("Now you have " + tasks.size() + " task(s) in the list. \n" +
                                        "____________________________________________________________");
                            }
                            break;
                        }
                    }
                }
            }
        }

        private static void listCommand() {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task currTask = tasks.get(i - 1);
                System.out.println(i + "." + currTask.toString());
            }
            System.out.println("____________________________________________________________");
        }

        private static void removeCommand(int i) {
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

        private static void doneCommand(int i) {
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

    private static void printString(Scanner sc) throws DukeException {
        String inputString = sc.nextLine();
        String[] word = inputString.split(" ", 2);
        String command = word[0];
        if (command.equals("done")) {
            int secondWord = Integer.parseInt(word[1]);
            Commands.doneCommand(secondWord);
            printString(sc);
        } else if (command.equals("delete")) {
            int secondWord = Integer.parseInt(word[1]);
            Commands.removeCommand(secondWord);
            printString(sc);
        } else {
            if (inputString.equals("list")) {
                Commands.listCommand();
                printString(sc);
            } else if (inputString.equals("bye")) {
                Commands.byeCommand();
            } else {
                Commands.addCommand(inputString);
                printString(sc);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Commands.helloCommand();
        printString(sc);
    }
}