import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";


    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.createTaskFromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            // File does not exist or other IO error, it's okay, just return an empty list
        }
        return tasks;
    }
    private static void printHorizontalLine() {
        System.out.println(" _____________________________");
    }

    public static void main(String[] args) {
        String chatbotName = "Aether";
        Scanner scanner = new Scanner(System.in);

        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + chatbotName + "!");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");


        ArrayList<Task> tasks = loadTasksFromFile();
        int taskCounter = tasks.size();

        //ArrayList<Task> tasks = new ArrayList<>();

        String input;

        do {
            input = scanner.nextLine();
            printHorizontalLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.size() > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else {
                    System.out.println("No tasks added yet.");
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex).getStatusIcon() + " " +
                            tasks.get(taskIndex).getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("unmark")) {
                // Mark a task as not done
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex).getStatusIcon() + " " + tasks.get(taskIndex).getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("todo")) {
                // Add a ToDo task
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    //tasks[taskCounter] = new ToDo(description);
                    tasks.add(new ToDo(description));
                    //taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (input.startsWith("deadline")) {
                // Add a Deadline task
                String[] deadlineDetails = input.substring(9).split("/by");
                if (deadlineDetails.length == 2) {
                    tasks.add(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Invalid deadline format. Please use: deadline <description> /by <date/time>");
                }
            } else if (input.startsWith("event")) {
                // Add an Event task
                String[] eventDetails = input.substring(6).split("/from|/to");
                if (eventDetails.length == 3) {
                    tasks.add(new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim()));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Invalid event format. Please use: event <description> /from <start> /to <end>");
                }
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    Task removedTask = tasks.remove(taskIndex);
                    // Shift tasks to fill the gap
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Invalid task index.");
                }

            } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                printHorizontalLine();
            }
            while (!input.equalsIgnoreCase("bye")) ;
            scanner.close();
        }
    }

