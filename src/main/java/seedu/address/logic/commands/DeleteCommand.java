package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes one or more people from the address book.
 * Identified using displayed index/indices (i.e. "2", "1 3 4", "1-5"),
 * or all currently displayed ("all")
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes one or more people identified by index numbers in the displayed person list.\n"
            + "Parameters: INDEX... (space-separated), START_INDEX-END_INDEX (range), or 'all'\n"
            + "Examples: " + COMMAND_WORD + " 1, "
            + COMMAND_WORD + " 1 3 5, "
            + COMMAND_WORD + " 2-5, "
            + COMMAND_WORD + " all";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX_SPECIFIC = "The person index %1$s is invalid";
    public static final String MESSAGE_EMPTY_PERSON_LIST = "The displayed list is already empty.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted %1$s people: %2$s";

    private ArrayList<Index> targets = new ArrayList<>();
    private boolean deleteAllDisplayed = false;

    /**
     * @param targets list of indices of the people to be deleted
     */
    public DeleteCommand(ArrayList<Index> targets) {
        this.targets = targets;
    }

    /**
     * @param deleteAllDisplayed true
     */
    public DeleteCommand(boolean deleteAllDisplayed) {
        this.deleteAllDisplayed = deleteAllDisplayed;
        this.targets = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = new ArrayList<>(model.getFilteredPersonList());

        if (deleteAllDisplayed) {
            if (lastShownList.isEmpty()) {
                throw new CommandException(MESSAGE_EMPTY_PERSON_LIST);
            }
            StringBuilder names = new StringBuilder();
            for (Person p : lastShownList) {
                names.append(p.getName()).append(", ");
                model.deletePerson(p);
            }
            names.setLength(names.length() - 2);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, lastShownList.size(), names));
        } else {

            // Ensure all indices are valid before executing command
            for (Index targetIndex : targets) {
                if (targetIndex.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(String.format(
                            MESSAGE_INVALID_PERSON_DISPLAYED_INDEX_SPECIFIC,
                            targetIndex.getOneBased()));
                }
            }

            StringBuilder names = new StringBuilder();

            for (Index targetIndex : targets) {
                Person p = lastShownList.get(targetIndex.getZeroBased());
                names.append(p.getName()).append(", ");
                model.deletePerson(p);
            }
            names.setLength(names.length() - 2);

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, targets.size(), names));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targets.equals(otherDeleteCommand.targets);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targets)
                .toString();
    }
}
