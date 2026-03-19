package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of tasks.
 */
public class TaskList {

    private final List<Task> internalList = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added. Must not be null.
     */
    public void addTask(Task task) {
        requireNonNull(task);
        internalList.add(task);
    }

    /**
     * Removes all tasks that are marked as completed from the list.
     */
    public void removeCompletedTasks() {
        internalList.removeIf(Task::isCompleted);
    }

    /**
     * Marks the task at the specified index as completed.
     * @param index zero-based index of the task.
     */
    public void markTask(int index) {
        internalList.get(index).markAsCompleted();
    }
}
