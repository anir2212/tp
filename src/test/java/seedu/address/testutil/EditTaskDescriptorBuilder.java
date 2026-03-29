package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private final EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    /**
     * Returns an {@code EditTaskDescriptorBuilder} with the data of {@code toCopy}.
     */
    public EditTaskDescriptorBuilder(EditTaskDescriptor toCopy) {
        descriptor = new EditTaskDescriptor(toCopy);
    }

    /**
     * Sets the task name of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskName(String taskName) {
        descriptor.setTaskName(taskName);
        return this;
    }

    /**
     * Sets the task description of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDescription(String taskDescription) {
        descriptor.setTaskDescription(taskDescription);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
