package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonHasClassPredicateTest {

    @Test
    public void equals() {
        PersonHasClassPredicate firstPredicate = new PersonHasClassPredicate("3A");
        PersonHasClassPredicate secondPredicate = new PersonHasClassPredicate("4B");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasClassPredicate firstPredicateCopy = new PersonHasClassPredicate("3A");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different class -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // same value different case -> returns true
        assertTrue(firstPredicate.equals(new PersonHasClassPredicate("3a")));
    }

    @Test
    public void test_personHasMatchingClass_returnsTrue() {
        PersonHasClassPredicate predicate = new PersonHasClassPredicate("3A");
        assertTrue(predicate.test(new PersonBuilder().withStudentClass("3A").build()));
        assertTrue(predicate.test(new PersonBuilder().withStudentClass("3a").build()));
    }

    @Test
    public void test_personDoesNotHaveMatchingClass_returnsFalse() {
        PersonHasClassPredicate predicate = new PersonHasClassPredicate("3A");

        // null class
        assertFalse(predicate.test(new PersonBuilder().withStudentClass(null).build()));

        // different class
        assertFalse(predicate.test(new PersonBuilder().withStudentClass("4B").build()));

        // no class set (PersonBuilder defaults to null)
        assertFalse(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        PersonHasClassPredicate predicate = new PersonHasClassPredicate("3A");
        String expected = PersonHasClassPredicate.class.getCanonicalName() + "{className=3A}";
        assertEquals(expected, predicate.toString());
    }
}
