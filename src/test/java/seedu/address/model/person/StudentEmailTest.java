package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> StudentEmail.isValidEmail(null));

        // invalid email
        assertFalse(StudentEmail.isValidEmail("")); // empty string
        assertFalse(StudentEmail.isValidEmail(" ")); // spaces only
        assertFalse(StudentEmail.isValidEmail("email")); // no @ symbol
        assertFalse(StudentEmail.isValidEmail("@example.com")); // no local part
        assertFalse(StudentEmail.isValidEmail("email@")); // no domain
        assertFalse(StudentEmail.isValidEmail("email@com")); // no TLD
        assertFalse(StudentEmail.isValidEmail("email@example")); // TLD too short
        assertFalse(StudentEmail.isValidEmail("email @example.com")); // contains space
        assertFalse(StudentEmail.isValidEmail("email@exam ple.com")); // space in domain

        // valid email
        assertTrue(StudentEmail.isValidEmail("alex@example.com")); // standard email
        assertTrue(StudentEmail.isValidEmail("alex.tan@example.com")); // with dot in local part
        assertTrue(StudentEmail.isValidEmail("alex123@example.com")); // with numbers
        assertTrue(StudentEmail.isValidEmail("alex@sub.example.com")); // subdomain
        assertTrue(StudentEmail.isValidEmail("a@b.co")); // minimal valid email
    }

    @Test
    public void equals() {
        StudentEmail email = new StudentEmail("test@example.com");

        // same values -> returns true
        assertTrue(email.equals(new StudentEmail("test@example.com")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new StudentEmail("other@example.com")));
    }

    @Test
    public void toString_method() {
        StudentEmail email = new StudentEmail("test@example.com");
        String expected = "test@example.com";
        assertTrue(email.toString().equals(expected));
    }

    @Test
    public void hashCode_method() {
        StudentEmail email = new StudentEmail("test@example.com");
        assertTrue(email.hashCode() == email.value.hashCode());
    }
}
