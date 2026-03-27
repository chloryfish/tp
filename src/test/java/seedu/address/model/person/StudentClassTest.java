package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentClass(null));
    }

    @Test
    public void constructor_invalidClass_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StudentClass(""));
        assertThrows(IllegalArgumentException.class, () -> new StudentClass("   "));
        assertThrows(IllegalArgumentException.class, () -> new StudentClass("@3A"));
        assertThrows(IllegalArgumentException.class, () -> new StudentClass("3-A"));
    }

    @Test
    public void isValidClass() {
        // null
        assertFalse(StudentClass.isValidClass(null));

        // invalid class names
        assertFalse(StudentClass.isValidClass(""));
        assertFalse(StudentClass.isValidClass("   "));
        assertFalse(StudentClass.isValidClass("@3A"));
        assertFalse(StudentClass.isValidClass("3-A"));
        assertFalse(StudentClass.isValidClass("class 1"));

        // valid class names
        assertTrue(StudentClass.isValidClass("3A"));
        assertTrue(StudentClass.isValidClass("4B"));
        assertTrue(StudentClass.isValidClass("Primary5"));
        assertTrue(StudentClass.isValidClass("P6"));
    }

    @Test
    public void equals() {
        StudentClass studentClass = new StudentClass("3A");

        // same values -> returns true
        assertTrue(studentClass.equals(new StudentClass("3A")));

        // same object -> returns true
        assertTrue(studentClass.equals(studentClass));

        // null -> returns false
        assertFalse(studentClass.equals(null));

        // different types -> returns false
        assertFalse(studentClass.equals(5));

        // different values -> returns false
        assertFalse(studentClass.equals(new StudentClass("4B")));

        // same value, different case -> returns true (case-insensitive)
        assertTrue(studentClass.equals(new StudentClass("3a")));
    }

    @Test
    public void toStringMethod() {
        StudentClass studentClass = new StudentClass("3A");
        assertEquals("3A", studentClass.toString());
    }
}
