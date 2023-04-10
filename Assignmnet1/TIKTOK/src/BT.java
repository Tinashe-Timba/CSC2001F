import java.util.ArrayList;

//CREATING A BINARY TREE FOR THE ACCOUNTS
//Author:Tinashe Timba
public class BT {
    ArrayList<String> accs=new ArrayList<>();
    BTN root;

    public BT() {
        root = null;
    }

    public class BTN {
        public Account acc;
        public String username;   // key
        public BTN left;
        private BTN right;

        public BTN(Account acc) {
            this.acc = acc;
            this.username = acc.getUsername();
            left = null;
            right = null;
        }

        public String getUsername() {
            return username;
        }

        public BTN getLeft() {
            return left;
        }

        public BTN getRight() {
            return right;
        }

        public void setAcc(Account acc){
            this.acc=acc;}
    }

    //insert a new account to data structure
    public void insert(Account acc) {
        if (root == null)
            root = new BTN(acc);
        else
            insert(acc, root);

    }


// insert a new account into the BST
    public void insert(Account acc, BTN node) {
        String user = acc.getUsername(); // extract the username that is about to be added
        String nodeUser = node.getUsername();// extracts existing username
        int c = user.compareTo(nodeUser);// stores comparison result
        if (c <= 0) {
            if (node.left == null)
                node.left = new BTN(acc);
            else
                insert(acc, node.left);
        } else {
            if (node.right == null)
                node.right = new BTN(acc);
            else
                insert(acc, node.right);
        }
    }

    // find an account using username as comparison key
    public BTN find(Account acc) {
        if (root == null)
            return null;
        else
            return find(acc, root);
    }

    public BTN find(Account acc, BTN node) {
        String user = acc.getUsername(); // extract the username that is about to be added
        String nodeUser = node.getUsername();// extracts existing username
        int c = user.compareTo(nodeUser);
        if (c == 0)
            return node;
        else if (c < 0)
            return (node.left == null) ? null : find(acc, node.left);
        else
            return (node.right == null) ? null : find(acc, node.right);
    }

    // delete account
    public void delete(Account acc) {
        root = delete(acc, root);
        accs.remove(acc.getUsername());
    }

    public BTN delete(Account acc, BTN node) {
        if (node == null) return null;
        String user = acc.getUsername(); // extract the username that is about to be added
        String nodeUser = node.getUsername();// extracts existing username
        int c = user.compareTo(nodeUser);

        if (c < 0)
            node.left = delete(acc, node.left);
        else if (c > 0)
            node.right = delete(acc, node.right);
        else if (node.left != null && node.right != null) {
            node.acc = findMin(node.right).acc;
            node.right = removeMin(node.right);
        } else if (node.left != null)
            node = node.left;
        else
            node = node.right;
        return node;
    }
    public BTN findMin ( BTN node)
    {
        if (node != null)
            while (node.left != null)
                node = node.left;
        return node;
    }
    public BTN  removeMin ( BTN node )
    {
        if (node == null)
            return null;
        else if (node.left != null)
        {
            node.left = removeMin (node.left);
            return node;
        }
        else
            return node.right;
    }
    // inorder traversal
    public void inOrder(){
        inOrder(root);

    }
    public void inOrder1(){
        inOrder1(root);
    }
    public void inOrder(BTN node){
        if (node!=null) {
            inOrder(node.getLeft());
            System.out.println(node.acc.getUsername());
            inOrder(node.getRight());
        }

        }

    public void inOrder1(BTN node){
        if (node!=null) {
            inOrder1(node.getLeft());
            //System.out.println(node.acc.getUsername());
            this.accs.add(node.acc.getUsername());
            inOrder1(node.getRight());
        }

    }
    public ArrayList<String> list(){

        return  accs;
    }

    }










