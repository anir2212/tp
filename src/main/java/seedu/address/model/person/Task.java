package seedu.address.model.person;

/**
 * A class to represent a Task.
 */
public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Constructor for Task.
     * @param taskName the name of the task.
     * @param taskDescription the description of the task.
     */
    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns true if the task is completed.
     *
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format "taskName: taskDescription".
     */
    @Override
    public String toString() {
        return taskName + ": " + taskDescription;
    }

}
