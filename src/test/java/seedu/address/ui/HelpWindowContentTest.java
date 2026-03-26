package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.ui.HelpWindowContent.HelpSection;

public class HelpWindowContentTest {

    @Test
    public void getHelpSections_returnsExpectedCommandSections() {
        List<HelpSection> helpSections = HelpWindowContent.getHelpSections();

        assertEquals(11, helpSections.size());
        assertEquals("help", helpSections.get(0).commandWord());
        assertEquals("Shows this in-app help window.", helpSections.get(0).description());
        assertEquals("No additional parameters.", helpSections.get(0).allowedInput());
        assertIterableEquals(List.of("help"), helpSections.get(0).examples());

        assertEquals("delete", helpSections.get(2).commandWord());
        assertIterableEquals(List.of("delete John Doe", "delete 2"), helpSections.get(2).examples());

        assertEquals("exit", helpSections.get(helpSections.size() - 1).commandWord());
        assertEquals("No additional parameters.", helpSections.get(helpSections.size() - 1).allowedInput());
    }
}
