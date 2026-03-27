package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.PersonHasClassPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingClassPrefix_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "3A",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyClass_throwsParseException() {
        assertParseFailure(parser, " c/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " c/  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidClass_throwsParseException() {
        assertParseFailure(parser, " c/@3A", "Class names should be alphanumeric and not blank (e.g. 3A, 4B)");
        assertParseFailure(parser, " c/3-A", "Class names should be alphanumeric and not blank (e.g. 3A, 4B)");
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        FilterCommand expectedFilterCommand = new FilterCommand(new PersonHasClassPredicate("3A"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " c/3A", expectedFilterCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " \n c/3A \n", expectedFilterCommand);

        // different valid class
        expectedFilterCommand = new FilterCommand(new PersonHasClassPredicate("4B"));
        assertParseSuccess(parser, " c/4B", expectedFilterCommand);
    }

}
