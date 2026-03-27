package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> StudentAddress.isValidAddress(null));

        // invalid address
        assertFalse(StudentAddress.isValidAddress("")); // empty string
        assertFalse(StudentAddress.isValidAddress(" ")); // spaces only
        assertFalse(StudentAddress.isValidAddress("   ")); // multiple spaces only

        // valid address
        assertTrue(StudentAddress.isValidAddress("Main Street")); // single word
        assertTrue(StudentAddress.isValidAddress("123 Main Street")); // with numbers
        assertTrue(StudentAddress.isValidAddress("Block 123 Street 456 #01-123")); // complex address
        assertTrue(StudentAddress.isValidAddress("a")); // single character
        assertTrue(StudentAddress.isValidAddress("123")); // numbers only
        assertTrue(StudentAddress.isValidAddress("Main Street, City, Country 12345")); // long address
    }

    @Test
    public void equals() {
        StudentAddress address = new StudentAddress("Valid Address");

        // same values -> returns true
        assertTrue(address.equals(new StudentAddress("Valid Address")));

        // same object -> returns true
        assertTrue(address.equals(address));

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        assertFalse(address.equals(new StudentAddress("Other Valid Address")));
    }

    @Test
    public void toString_method() {
        StudentAddress address = new StudentAddress("123 Main Street");
        String expected = "123 Main Street";
        assertTrue(address.toString().equals(expected));
    }

    @Test
    public void hashCode_method() {
        StudentAddress address = new StudentAddress("123 Main Street");
        assertTrue(address.hashCode() == address.value.hashCode());
    }
}
