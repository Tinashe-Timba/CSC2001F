public class BT{
    BTN root;
    public BT(){
        root=null;
    }
    private  class BTN{
        private Account acc;
        private String username;   // key
        private  BTN left;
        private BTN right;

        public BTN(Account acc) {
            this.acc = acc;
            this.username =acc.getUsername();
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
    }
    //insert a new account to data structure
    public void insert (Account acc){
        if (root == null)
            root = new BTN(acc);
        else
            insert(acc, root);

    }
    public void insert(Account acc,BTN node){
        String user =acc.getUsername(); // extract the username that is about to be added
        String nodeUser= node.getUsername();// extracts existing username
        int c = user.compareTo(nodeUser);// stores comparison result
        if (c<=0){
            if (node.left == null)
                node.left = new BTN(acc);
            else
                insert (acc,node.left);
        }
        else {
            if (node.right == null)
                node.right = new BTN(acc);
            else
                insert(acc, node.right);
        }
    }

    // find an account using username as comparison
    public BTN find(Account acc){
        if (root== null)
            return null;
        else
            return find(acc,root);
    }

    public BTN find(Account acc, BTN node){
        String user =acc.getUsername(); // extract the username that is about to be added
        String nodeUser= node.getUsername();// extracts existing username
        int c = user.compareTo(nodeUser);
        if (c==0)
            return node;
        else if (c<0)
            return (node.left == null) ? null : find(acc,node.left);
        else
            return (node.right == null) ? null : find(acc,node.right);
        }

        // delete account
    }









