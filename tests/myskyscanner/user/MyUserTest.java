package myskyscanner.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
