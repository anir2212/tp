package seedu.address.ui;

import java.util.List;

/**
 * Provides the help content shown in the help window.
 */
public final class HelpWindowContent {

    private HelpWindowContent() {}

    /**
     * Returns the help sections shown in the help window.
     */
    public static List<HelpSection> getHelpSections() {
        return List.of(
                new HelpSection("help", "Shows this in-app help window.",
                        "No additional parameters.", List.of("help")),
                new HelpSection("add", "Adds an employee.",
                        "n/NAME p/PHONE e/EMAIL d/DEPARTMENT pos/POSITION [t/TAG]...",
                        List.of("add n/John Doe p/98765432 e/johnd@example.com d/IT pos/Software Engineer "
                                + "t/fulltime")),
                new HelpSection("delete", "Deletes an employee by unique name or list index.",
                        "NAME or INDEX.", List.of("delete John Doe", "delete 2")),
                new HelpSection("edit", "Edits an employee identified by index.",
                        "INDEX with one or more optional fields: [n/NAME] [p/PHONE] [e/EMAIL] "
                                + "[d/DEPARTMENT] [pos/POSITION] [t/TAG]...",
                        List.of("edit 1 p/91234567 e/johndoe@example.com")),
                new HelpSection("find", "Finds employees whose names contain any keyword.",
                        "KEYWORD [MORE_KEYWORDS]...", List.of("find alice bob charlie")),
                new HelpSection("list", "Lists all employees.",
                        "No additional parameters.", List.of("list")),
                new HelpSection("show", "Filters employees by one or more fields.",
                        "At least one of n/ d/ p/ e/ pos/ t/ task/.",
                        List.of("show n/Alex d/IT")),
                new HelpSection("addtask", "Adds a task to an employee by name.",
                        "task/TASK_NAME desc/TASK_DESCRIPTION n/EMPLOYEE_NAME",
                        List.of("addtask task/Prepare Report desc/Submit by Friday n/John Doe")),
                new HelpSection("deletetask", "Deletes a task by task index.",
                        "INDEX.", List.of("deletetask 1")),
                new HelpSection("clear", "Clears all employees from the address book.",
                        "No additional parameters.", List.of("clear")),
                new HelpSection("exit", "Exits ManageUp.",
                        "No additional parameters.", List.of("exit"))
        );
    }

    /**
     * Represents one command section in the help window.
     */
    public record HelpSection(String commandWord, String description, String allowedInput, List<String> examples) { }
}
