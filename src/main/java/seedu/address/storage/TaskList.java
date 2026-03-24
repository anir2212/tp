package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Task;

/**
 * A list of tasks.
 */
public class TaskList {

    private final Map<Task, Employee> internalMap = new HashMap<>();

    /**
     * Adds a task to the list with the assigned employee.
     * @param task the task to be added.
     * @param person the employee to whom the task is assigned.
     */
    public void addTaskOverall(Task task, Employee person) {
        requireNonNull(task);
        requireNonNull(person);
        internalMap.put(task, person);
    }

    /**
     * Returns a string representation of all tasks and their assigned employees.
     * @return a string listing all tasks and their assigned employees.
     */
    public String showFullTaskList() {
        if (internalMap.isEmpty()) {
            return "No tasks assigned.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Full Task List:\n");
        int index = 1;
        for (Map.Entry<Task, Employee> entry : internalMap.entrySet()) {
            Task task = entry.getKey();
            Employee person = entry.getValue();
            sb.append(index)
                    .append(". ")
                    .append(task.getTaskName())
                    .append(" - Assigned to: ")
                    .append(person.getName())
                    .append("\n");

            index++;
        }
        return sb.toString();
    }
}
