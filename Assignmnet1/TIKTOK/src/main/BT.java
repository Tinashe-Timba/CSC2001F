package main;
//AUTHOR:TINASHE TIMBA
// STUDENT NUMBER:TMBTIN004

import java.util.ArrayList;

//CREATING A BINARY TREE FOR THE ACCOUNTS
//Author:Tinashe Timba

/**
 * This class represents a BT(Binary tree)
 */
public class BT {

    ArrayList<String> accs=new ArrayList<>();
    BTN root;

    /**
     * The constructor intializes the root to mull
     */
    public BT() {
        root = null;
    }

    /**
     * The inner class BTN represents the nodes of the BT
     */
    public class BTN {
        public Account acc;
        public String username;   // key
        public BTN left;
        private BTN right;

        /**
         * The  constructor intializes the account object ,username as key and the left and right node
         * @param acc the account in the node
         */
        public BTN(Account acc) {
            this.acc = acc;
            this.username = acc.getUsername();
            left = null;
            right = null;
        }

        /**
         * get method to get the usename of the account in the node
         * @return username or key of the node
         */
        public String getUsername() {
            return username;
        }

        /**
         * returns the left node of the current node
         * @return left node
         */
        public BTN getLeft() {
            return left;
        }

        /**
         * return the right node of the current node
         * @return the right node
         */
        public BTN getRight() {
            return right;
        }

        /**
         * sets the account object of the node
         * @param acc the account object to be set
         */
        public void setAcc(Account acc){
            this.acc=acc;}
    }

    //insert a new account to data structure

    /**
     * insert a new account into the binary
     * @param acc account to be added
     */
    public void insert(Account acc) {
        if (root == null)
            root = new BTN(acc);
        else
            insert(acc, root);

    }


// insert a new account into the BST

    /**
     * inserts account into the BT at a specified node
     * @param acc the account object to be added
     * @param node the node which account is being added
     */
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

    /**
     * finds the node containing the specified account using the find method recursivley
     * @param acc the account to be founf
     * @return the node containing account
     */
    public BTN find(Account acc) {
        if (root == null)
            return null;
        else
            return find(acc, root);
    }

    /**
     * find the account
     * @param acc account to be found
     * @param node node
     * @return node
     */
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

    /**
     * deletes the accounf from the binary tree and accs array
     * @param acc account to  be deleted
     */
    public void delete(Account acc) {
        root = delete(acc, root);
        accs.remove(acc.getUsername());
    }

    /**
     * uses a comparision of ussernames to delete the required account
     * @param acc account to be deleted
     * @param node node to start
     * @return the node
     */
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

    /**
     * find the minimmum node
     * @param node
     * @return min node
     */
    public BTN findMin ( BTN node)
    {
        if (node != null)
            while (node.left != null)
                node = node.left;
        return node;
    }

    /**
     * removes min node
     * @param node
     * @return node
     */
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
    /**
     * recursivly does and inorder traversal
     */
    public void inOrder(){
        inOrder(root);

    }
    /**
     * recursivly does and inorder traversal
     */
    public void inOrder1(){
        inOrder1(root);
    }

    /**
     * perfeoms an inorder traveral and print the key
     * @param node node to begin traversal
     */
    public void inOrder(BTN node){
        if (node!=null) {
            inOrder(node.getLeft());
            System.out.println(node.acc.getUsername());
            inOrder(node.getRight());
        }

        }
//inorder1 loads accounts to arraylist that will be used in the TokTik class

    /**
     * performs an inorder traversal whilst storing the usernames of the accounts in an arraylist
     * @param node node to start traversal
     */
    public void inOrder1(BTN node){
        if (node!=null) {
            inOrder1(node.getLeft());
            //System.out.println(node.acc.getUsername());
            this.accs.add(node.acc.getUsername());
            inOrder1(node.getRight());
        }

    }

    /**
     * method to return list of accounts
     * @return list of accounts
     */
    public ArrayList<String> list(){

        return  accs;
    }

    }










