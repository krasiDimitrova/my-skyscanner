package myskyscanner.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyUserTest {

    @Test
    public void validatePass_validPasswordWithOnlyDigitsAndLetters_True() {
        char[] pass = { 'p', 'a', 's', 's', '1', '2' };
        MyUser test = new MyUser("test", pass, Role.Customer, null, null, null, null);
        assertTrue(test.validatePass(pass));
    }

    @Test
    public void validatePass_wrongPasswordWithOnlyDigitsAndLetters_False() {
        char[] pass = { 'p', 'a', 's', 's', '1', '2' };
        MyUser test = new MyUser("test", pass, Role.Customer, null, null, null, null);
        char[] pas1 = { 's', 's', '1', '2' };
        assertFalse(test.validatePass(pas1));
    }

    @Test
    public void validatePass_validPasswordWithSymbolsDigitsAndLetters_True() {
        char[] pass = { 'p', 'a', '!', '?', '1', '+' };
        MyUser test = new MyUser("test", pass, Role.Customer, null, null, null, null);
        assertTrue(test.validatePass(pass));
    }

    @Test
    public void validatePass_wrongPasswordWithSymbolsDigitsAndLetters_False() {
        char[] pass = { 'p', 'a', '!', '?', '1', '+' };
        MyUser test = new MyUser("test", pass, Role.Customer, null, null, null, null);
        char[] pas1 = { 's', '@', '*', '2' };
        assertFalse(test.validatePass(pas1));
    }

    @Test
    public void getLocations_addLocationsForNotify_listContainingThem() {
        char[] pass = { 'p', 'a', '!', '?', '1', '+' };
        MyUser test = new MyUser("test", pass, Role.Customer, null, null, null, null);
        test.addStartEndLoc("Sofia", "London");
        test.addStartEndLoc("London", "Sofia");
        List<String> expected = new ArrayList<String>();
        expected.add("Sofia");
        expected.add("London");
        expected.add("London");
        expected.add("Sofia");
        assertEquals(expected, test.getLocations());
    }
}
