package tasklist;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a list of tasks.
 * <p>
 * This class is used to represent a list of tasks. It provides methods to add,
 * remove and get tasks from the list.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    /**
     * Constructs a new {@code TaskList} instance with an empty list of tasks.
     */
    public TaskList() {
        this.tasksList = new ArrayList<Task>();
    }

    /**
     * Constructs a new {@code TaskList} instance with the specified list of tasks.
     *
     * @param tasksList The list of tasks.
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /*
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list
     */
    public int getTaskCount() {
        return this.tasksList.size();
    }

    /*
     * Returns the list of tasks.
     * 
     * @return The list of tasks
     */
    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    /*
     * Returns the task at the specified index.
     * 
     * @param index The index of the task
     * 
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return this.tasksList.get(index - 1);
    }

    /*
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasksList.get(i));
        }
    }

    /*
     * Adds a task to the list.
     * 
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasksList.add(task);
    }

    /*
     * Removes a task from the list at the specified index.
     * 
     * @param index The index of the task to be removed
     */
    public void removeTask(int index) {
        this.tasksList.remove(index - 1);
    }
}
