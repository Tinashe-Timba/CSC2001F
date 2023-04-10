// a class to access the binary tree easier and make main code shorter
import java.util.ArrayList;
import java.util.List;

//creates a new class to access accounts in binary tree
public class Database {
    private ArrayList<Account> Accounts;
    public BT accounts;

    //
    public Database() {
        // creates a binary for the set of users
        accounts = new BT();
        Accounts = new ArrayList<Account>();
    }

    // create , delete a an acc
    public void createAcc(Account acc) {
        accounts.insert(acc);
    }

    public void deleteAcc(Account acc) {
        accounts.delete(acc);
    }

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

    // add a new post an existing account
    public void makePost(String username, String title, String video, String likes) {
        Account acc = new Account(username, "");
        BT.BTN node = accounts.find(acc);
        if (node != null && node.acc!=null) {
            //node.acc.setUsername(username);
            node.acc.addPost(title, video, likes);}
    }

    // dispalys all posts asscoiated with that acc
    public List<Account.Post> AllPosts(String username) {
        Account acc = new Account(username, "");
        BT.BTN node = accounts.find(acc);
        if (node != null) {
            return node.acc.getPosts();
        } else
            return null;

    }

    public String AllAccs() {
        accounts.inOrder();
        return null;
    }

}
