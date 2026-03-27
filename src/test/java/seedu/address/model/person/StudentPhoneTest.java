package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone
        assertThrows(NullPointerException.class, () -> StudentPhone.isValidPhone(null));

        // invalid phone
        assertFalse(StudentPhone.isValidPhone("")); // empty string
        assertFalse(StudentPhone.isValidPhone(" ")); // spaces only
        assertFalse(StudentPhone.isValidPhone("abc")); // letters only
        assertFalse(StudentPhone.isValidPhone("123")); // less than 4 digits
        assertFalse(StudentPhone.isValidPhone("1234567890123456")); // more than 15 digits
        assertFalse(StudentPhone.isValidPhone("1234 5678")); // contains spaces
        assertFalse(StudentPhone.isValidPhone("1234-5678")); // contains hyphen
        assertFalse(StudentPhone.isValidPhone("+65 1234567")); // contains plus sign

        // valid phone
        assertTrue(StudentPhone.isValidPhone("1234")); // exactly 4 digits
        assertTrue(StudentPhone.isValidPhone("12345")); // 5 digits
        assertTrue(StudentPhone.isValidPhone("12345678")); // 8 digits
        assertTrue(StudentPhone.isValidPhone("123456789012345")); // exactly 15 digits
    }

    @Test
    public void equals() {
        StudentPhone phone = new StudentPhone("12345678");

        // same values -> returns true
        assertTrue(phone.equals(new StudentPhone("12345678")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new StudentPhone("87654321")));
    }

    @Test
    public void toString_method() {
        StudentPhone phone = new StudentPhone("12345678");
        String expected = "12345678";
        assertTrue(phone.toString().equals(expected));
    }

    @Test
    public void hashCode_method() {
        StudentPhone phone = new StudentPhone("12345678");
        assertTrue(phone.hashCode() == phone.value.hashCode());
    }
}
