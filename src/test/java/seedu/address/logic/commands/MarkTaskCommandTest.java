package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Task;

public class MarkTaskCommandTest {

    @Test
    public void execute_markTask_noCrash() {
        Model model = new ModelManager();

        Task task = new Task("Sample Task", "Description");
        model.addTask(task);

        MarkTaskCommand cmd = new MarkTaskCommand(0);

        assertDoesNotThrow(() -> cmd.execute(model));
    }
}
