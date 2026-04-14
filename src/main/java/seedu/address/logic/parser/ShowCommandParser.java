package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.predicatechecker.DepartmentContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.EmailContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.NameContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.PhoneContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.PositionContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.TagContainsKeywordsPredicate;
import seedu.address.model.employee.predicatechecker.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates
 * a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ShowCommand parse(String args) throws ParseException {
        assert args != null : "Show command arguments should not be null";

        String input = args.trim();

        if (input.isEmpty()) {
            throw createInvalidShowCommandException();
        }

        checkDuplicatePrefixes(input);

        Predicate<Employee> predicate = employee -> true;
        boolean hasFilter = false;

        // Name
        if (containsPrefix(input, "n/")) {
            String value = extract(input, "n/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Name");
            }
            validateNameValue(value);
            predicate = predicate.and(
                    new NameContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Department
        if (containsPrefix(input, "d/")) {
            String value = extract(input, "d/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Department");
            }
            validateDepartmentValue(value);
            predicate = predicate.and(
                    new DepartmentContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Phone
        if (containsPrefix(input, "p/")) {
            String value = extract(input, "p/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Phone");
            }
            validatePhoneValue(value);
            predicate = predicate.and(
                    new PhoneContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Position
        if (containsPrefix(input, "pos/")) {
            String value = extract(input, "pos/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Position");
            }
            validatePositionValue(value);
            predicate = predicate.and(
                    new PositionContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Email
        if (containsPrefix(input, "e/")) {
            String value = extract(input, "e/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Email");
            }
            predicate = predicate.and(
                    new EmailContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Tag
        if (containsPrefix(input, "t/")) {
            String value = extract(input, "t/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Tag");
            }
            predicate = predicate.and(
                    new TagContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        // Task
        if (containsPrefix(input, "task/")) {
            String value = extract(input, "task/");
            if (value.isEmpty()) {
                throw createEmptyFieldException("Task");
            }
            predicate = predicate.and(
                    new TaskContainsKeywordsPredicate(Arrays.asList(value.split("\\s+")))
            );
            hasFilter = true;
        }

        assert predicate != null : "Predicate should always be initialised";

        if (!hasFilter) {
            throw createInvalidShowCommandException();
        }

        return new ShowCommand(predicate);
    }

    /**
     * Checks whether the input contains duplicate prefixes.
     */
    private void checkDuplicatePrefixes(String input) throws ParseException {
        assert input != null : "Input should not be null";

        List<String> duplicatePrefixes = new ArrayList<>();

        if (countPrefixOccurrences(input, "n/") > 1) {
            duplicatePrefixes.add("n/");
        }
        if (countPrefixOccurrences(input, "d/") > 1) {
            duplicatePrefixes.add("d/");
        }
        if (countPrefixOccurrences(input, "p/") > 1) {
            duplicatePrefixes.add("p/");
        }
        if (countPrefixOccurrences(input, "e/") > 1) {
            duplicatePrefixes.add("e/");
        }
        if (countPrefixOccurrences(input, "pos/") > 1) {
            duplicatePrefixes.add("pos/");
        }
        if (countPrefixOccurrences(input, "t/") > 1) {
            duplicatePrefixes.add("t/");
        }
        if (countPrefixOccurrences(input, "task/") > 1) {
            duplicatePrefixes.add("task/");
        }

        if (!duplicatePrefixes.isEmpty()) {
            throw createDuplicatePrefixException(duplicatePrefixes);
        }
    }

    /**
     * Returns true if the prefix appears as a recognised command prefix.
     */
    private boolean containsPrefix(String input, String prefix) {
        assert input != null : "Input should not be null";
        assert prefix != null : "Prefix should not be null";

        return input.startsWith(prefix) || input.contains(" " + prefix);
    }

    /**
     * Counts how many times a prefix appears as a recognised command prefix.
     */
    private int countPrefixOccurrences(String input, String prefix) {
        assert input != null : "Input should not be null";
        assert prefix != null : "Prefix should not be null";

        int count = 0;

        if (input.startsWith(prefix)) {
            count++;
        }

        int fromIndex = 0;
        String spacedPrefix = " " + prefix;

        while ((fromIndex = input.indexOf(spacedPrefix, fromIndex)) != -1) {
            count++;
            fromIndex += spacedPrefix.length();
        }

        return count;
    }

    /**
     * Extracts value after a prefix until the next recognised prefix or end of input.
     */
    private String extract(String input, String prefix) {
        assert input != null : "Input to extract should not be null";
        assert prefix != null : "Prefix should not be null";

        int start = input.indexOf(prefix);
        if (start == -1) {
            return "";
        }

        start += prefix.length();
        int end = input.length();

        String[] prefixes = {"n/", "d/", "p/", "e/", "pos/", "t/", "task/"};

        for (String nextPrefix : prefixes) {
            if (nextPrefix.equals(prefix)) {
                continue;
            }

            int index = input.indexOf(" " + nextPrefix, start);
            if (index != -1 && index < end) {
                end = index;
            }
        }

        assert start <= end : "Extraction start index should not exceed end index";
        return input.substring(start, end).trim();
    }

    /**
     * Validates the name value used in the show command.
     */
    private void validateNameValue(String value) throws ParseException {
        assert value != null : "Name value should not be null";

        if (!Name.isValidName(value)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Validates the department value used in the show command.
     */
    private void validateDepartmentValue(String value) throws ParseException {
        assert value != null : "Department value should not be null";

        if (!Department.isValidDepartment(value)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Validates the phone value used in the show command.
     */
    private void validatePhoneValue(String value) throws ParseException {
        assert value != null : "Phone value should not be null";

        if (!Phone.isValidPhone(value)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Validates the position value used in the show command.
     */
    private void validatePositionValue(String value) throws ParseException {
        assert value != null : "Position value should not be null";

        if (!Position.isValidPosition(value)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Creates the parse exception for invalid show command format.
     */
    private ParseException createInvalidShowCommandException() {
        return new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    /**
     * Creates the parse exception for an empty field value.
     */
    private ParseException createEmptyFieldException(String fieldName) {
        return new ParseException(fieldName + " field should not be empty.\n"
                + ShowCommand.MESSAGE_USAGE);
    }

    /**
     * Creates the parse exception for duplicate prefixes.
     */
    private ParseException createDuplicatePrefixException(List<String> duplicatePrefixes) {
        return new ParseException("Multiple values were provided for these fields, "
                + "but each field accepts only one value: "
                + String.join(" ", duplicatePrefixes));
    }
}
