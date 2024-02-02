
package duke;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * Constructs an empty task list.
     */
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the list of tasks in the task list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException If the index is invalid.
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws DukeException If the index is invalid.
     */
    public void unmarkTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Deletes a task at the specified index and displays the deletion message using the given UI.
     *
     * @param taskIndex The index of the task to be deleted.
     * @param ui The UI used to display messages.
     */
    public void deleteTask(int taskIndex, Ui ui) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            ui.showTaskDeleted(removedTask, tasks.size());
        } else {
            ui.showInvalidTaskIndex();
        }
    }

    public List<Task> findTasksByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

}
