package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_REPORT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void isAnyFieldEdited_noFieldsSet_returnsFalse() {
        assertFalse(new EditTaskDescriptor().isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_onlyNameSet_returnsTrue() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_REPORT)
                .build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_onlyDescriptionSet_returnsTrue() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskDescription(VALID_TASK_DESCRIPTION_REPORT)
                .build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_bothFieldsSet_returnsTrue() {
        assertTrue(DESC_PRESENTATION.isAnyFieldEdited());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_PRESENTATION);
        assertTrue(DESC_PRESENTATION.equals(descriptorWithSameValues));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        assertTrue(DESC_PRESENTATION.equals(DESC_PRESENTATION));
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(DESC_PRESENTATION.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertFalse(DESC_PRESENTATION.equals(5));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        assertFalse(DESC_PRESENTATION.equals(DESC_REPORT));
    }

    @Test
    public void equals_differentName_returnsFalse() {
        EditTaskDescriptor edited = new EditTaskDescriptorBuilder(DESC_PRESENTATION)
                .withTaskName(VALID_TASK_NAME_REPORT)
                .build();
        assertFalse(DESC_PRESENTATION.equals(edited));
    }

    @Test
    public void equals_differentDescription_returnsFalse() {
        EditTaskDescriptor edited = new EditTaskDescriptorBuilder(DESC_PRESENTATION)
                .withTaskDescription(VALID_TASK_DESCRIPTION_REPORT)
                .build();
        assertFalse(DESC_PRESENTATION.equals(edited));
    }

    @Test
    public void toStringMethod() {
        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        String expected = EditTaskDescriptor.class.getCanonicalName()
                + "{taskName=" + descriptor.getTaskName().orElse(null)
                + ", taskDescription=" + descriptor.getTaskDescription().orElse(null) + "}";
        assertEquals(expected, descriptor.toString());
    }


}

