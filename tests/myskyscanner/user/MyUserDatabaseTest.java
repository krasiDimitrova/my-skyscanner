package myskyscanner.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyUserDatabaseTest {

    private MyUserDatabase test = new MyUserDatabase();

    @Test
    public void addUser_addUniqueUserToTheDatabase_true() {
        char[] pass = { 'i', 'p' };
        assertTrue(test.addUser("i", pass, Role.Customer, null, null, null, null));
    }

    @Test
    public void addUser_addUserWithAlreadyExistingUsernameToTheDatabase_false() {
        char[] pass = { 'i', 'p' };
        test.addUser("p", pass, Role.Customer, null, null, null, null);
        assertFalse(test.addUser("p", pass, Role.Customer, null, null, null, null));
    }

    @Test
    public void delete_deleteExistingUserFromTheDatabase_true() {
        char[] pass = { 'i', 'p' };
        test.addUser("k", pass, Role.Customer, null, null, null, null);
        assertTrue(test.delete("k"));
    }

    @Test
    public void delete_tryToDeleteNotExistingUserFromTheDatabase_false() {
        assertFalse(test.delete("m"));
    }

    @Test
    public void hasUser_findExistingUserInTheDatabase_true() {
        char[] pass = { 'i', 'p' };
        test.addUser("e", pass, Role.Customer, null, null, null, null);
        assertTrue(test.hasUser("e"));
    }

    @Test
    public void hasUser_tryToFindNotExistingUserInTheDatabase_false() {
        assertFalse(test.delete("m"));
    }

    @Test
    public void chackPass_validPassword_true() {
        char[] pass = { 'i', 'p' };
        test.addUser("e", pass, Role.Customer, null, null, null, null);
        assertTrue(test.checkPass("e", pass));
    }

    @Test
    public void chackPass_wrongPassword_false() {
        char[] pass = { 'p', 'a', 's', 's', '1', '2' };
        test.addUser("q", pass, Role.Customer, null, null, null, null);
        char[] pas1 = { 's', 's', '1', '2' };
        assertFalse(test.checkPass("q", pas1));
    }
}
