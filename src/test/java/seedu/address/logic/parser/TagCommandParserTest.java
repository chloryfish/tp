package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SUPPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SUPPORT;
import static seedu.address.logic.commands.TagCommand.MESSAGE_NOT_TAGGED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


public class TagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_TAGGED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_DESC_REP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_DESC_REP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

    }

    @Test
    public void parse_oneTagSpecified_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_SUPPORT;

        Set<Tag> tags = ParserUtil.parseTags(Collections.singleton(VALID_TAG_SUPPORT));
        TagCommand expectedCommand = new TagCommand(targetIndex, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_SUPPORT + TAG_DESC_REP;

        Set<Tag> tags = ParserUtil.parseTags(Arrays.asList(VALID_TAG_REP, VALID_TAG_SUPPORT));
        TagCommand expectedCommand = new TagCommand(targetIndex, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
