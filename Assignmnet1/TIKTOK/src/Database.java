// a class to access the binary tree easier and make main code shorter
import java.util.ArrayList;
import java.util.List;

/**
 * This class makes use of BT class and Account class to create methods to be used in TokTik class and Main class
 */
//creates a new class to access accounts in binary tree
public class Database {
    private ArrayList<Account> Accounts;
    public BT accounts;

    /**
     * Constructor for Datbase class
     * it intinalises the Binary tree for accounts and creates an array to store account names
     */

    public Database() {
        // creates a binary for the set of users
        accounts = new BT();
        Accounts = new ArrayList<Account>();
    }

    /**
     * Creates a new account object and inserts it into the binary tree
     * @param acc the account objected to be created
     */

    // create , delete a an acc
    public void createAcc(Account acc) {
        accounts.insert(acc);
    }

    /**
     * Deletes an existing account from the binary tree
     * @param acc account to be deleted
     */

    public void deleteAcc(Account acc) {
        accounts.delete(acc);
    }

    /**
     * Gives the descrption of an account based on the username
     * @param username username of the acc
     * @return the descrption of the account if it exists
     */

    //prints the description to an acc given the username
    public String Description(String username) {
        Account acc = new Account(username, "");
        BT.BTN node = accounts.find(acc);
        if (node == null) {
            return null;
        } else {
            return node.acc.getDescription();
        }
    }

    /**
     * add a new post to an exisiting account
     * @param username the username of account
     * @param title the title of the post
     * @param video the name of the video posted
     * @param likes number of likes on the post
     */
    // add a new post an existing account
    public void makePost(String username, String title, String video, String likes) {
        Account acc = new Account(username, "");
        BT.BTN node = accounts.find(acc);
        if (node != null && node.acc!=null) {
            //node.acc.setUsername(username);
            node.acc.addPost(title, video, likes);}
    }

    // dispalys all posts asscoiated with that acc

    /**
     * Get all the post associetd with an acc and adds them to a list if account exists
     * @param username username of the account
     * @return a list of posts for the account
     */
    public List<Account.Post> AllPosts(String username) {
        Account acc = new Account(username, "");
        BT.BTN node = accounts.find(acc);
        if (node != null) {
            return node.acc.getPosts();
        } else
            return null;

    }
//  method to return all accounts

    /**
     * Returns a string of the all accounts
     * @return all account in the Binary tree
     */
    public String AllAccs() {
        accounts.inOrder();
        return null;
    }

}
