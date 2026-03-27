package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_returnsNonEmptyArray() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertNotNull(samplePersons);
        assertTrue(samplePersons.length > 0);
    }

    @Test
    public void getSampleAddressBook_returnsAddressBookWithSamplePersons() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        assertNotNull(sampleAddressBook);
        assertEquals(SampleDataUtil.getSamplePersons().length, sampleAddressBook.getPersonList().size());
    }

    @Test
    public void getTagSet_returnsCorrectSet() {
        var tagSet = SampleDataUtil.getTagSet("friend", "colleague");
        assertEquals(2, tagSet.size());
        assertTrue(tagSet.contains(new Tag("friend")));
        assertTrue(tagSet.contains(new Tag("colleague")));
    }
}
