package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.HelpWindowContent.HelpSection;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private VBox helpSections;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        populateHelpSections();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    private void populateHelpSections() {
        assert helpSections != null;
        helpSections.getChildren().clear();

        HelpWindowContent.getHelpSections().forEach(this::addSection);
    }

    private void addSection(HelpSection section) {
        VBox sectionCard = new VBox(8);
        sectionCard.getStyleClass().add("help-section-card");

        Label commandTitle = new Label(section.commandWord());
        commandTitle.getStyleClass().add("help-command-title");

        Label description = new Label(section.description());
        description.setWrapText(true);
        description.getStyleClass().add("help-command-description");

        Label allowedInput = new Label("Allowed input: " + section.allowedInput());
        allowedInput.setWrapText(true);
        allowedInput.getStyleClass().add("help-command-allowed-input");

        sectionCard.getChildren().addAll(commandTitle, description, allowedInput);

        for (String example : section.examples()) {
            Label exampleLabel = new Label("Example: " + example);
            exampleLabel.setWrapText(true);
            exampleLabel.getStyleClass().add("help-command-example");
            sectionCard.getChildren().add(exampleLabel);
        }

        helpSections.getChildren().add(sectionCard);
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
