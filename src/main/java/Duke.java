import java.util.Scanner;

public class Duke {
    Scanner sc = new Scanner(System.in);

    private static void printString(Scanner sc) throws DukeException {
        String inputString = sc.nextLine();
        while (inputString != null) {
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
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        printString(sc);
    }
}