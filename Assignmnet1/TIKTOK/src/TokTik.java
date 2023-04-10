import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TokTik extends JFrame {

    private JButton DESCRIPTION;
    private JButton load;
    private JButton ADDp;
    private JButton DisplayPosts;
    private JButton DELETEACC;
    private JButton CREATEACC;
    private JButton LISTACC;
    private JButton Quit;
    private JScrollPane AccountsList;
    private JScrollPane POSTSlist;
    private JScrollPane ALLACCS;

    public TokTik() {
    Database Data = new Database();
    DESCRIPTION = new JButton("1. Find the profile description for a given account");
    load = new JButton("7. Load a file of actions from disk and process this");
    ADDp = new JButton("6. Add a new post for an account");
    DisplayPosts = new JButton("5. Display all posts for a single account");
    DELETEACC = new JButton("4. Delete an account");
    CREATEACC = new JButton("3. Create an account");
    LISTACC = new JButton("2. List all accounts");



    DESCRIPTION.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Enter the account name:");
            String description = Data.Description(name); // retrieves the description if the account is found
            if (description!=null){
                JOptionPane.showMessageDialog(null,description);
            }
            else {JOptionPane.showMessageDialog(null,"Account not found");}

        }
    });
    LISTACC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea textArea = new JTextArea(20,20);
            //JOptionPane.showMessageDialog(null, "The accounts in the TikTok database are: ");
            Data.accounts.inOrder1();
            ArrayList<String> lists = Data.accounts.list();
            if (lists.isEmpty()){
                JOptionPane.showMessageDialog(null, "There are no accounts");
            }
            else{
            for (String element : lists) {
            textArea.append(element+"\n");}
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            JOptionPane.showMessageDialog(null, scrollPane, "List of Accounts", JOptionPane.PLAIN_MESSAGE);}

        }
    });

    CREATEACC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = JOptionPane.showInputDialog("Enter the account name:");

            String Description = JOptionPane.showInputDialog("Enter account description");
            String title = JOptionPane.showInputDialog("Enter post title");
            String video = JOptionPane.showInputDialog("Add video");
            String likes = JOptionPane.showInputDialog("Enter number of likes");
            Account account = new Account(username, Description);
            Data.createAcc(account);
            Data.makePost(username,title,video,likes);


        }
    });
    DELETEACC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = JOptionPane.showInputDialog("Enter the account name:");
            Account accountDelete = new Account(username, ""); // creates an account that will be used to find the account deleted
            Data.deleteAcc(accountDelete);
            JOptionPane.showMessageDialog(null,"Account has been deleted.");
        }
    });
    JPanel panel = new JPanel();
    panel.add(DESCRIPTION);
    panel.add(load);
    panel.add(ADDp);
    panel.add(DisplayPosts);
    panel.add(DELETEACC);
    panel.add(CREATEACC);
    panel.add(LISTACC);

    add(panel);
    setSize(400, 400);
    DisplayPosts.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Enter the account name:");
            JTextArea textArea = new JTextArea(20, 20);
            List<Account.Post> posts = Data.AllPosts(name);
            if (posts != null) {
                textArea.append("These are " + name + "'s posts."+"\n");
                for (Account.Post post : posts) {
                    textArea.append(post.getTitle() + '\n' + post.getVideo() + '\n' + post.getLikes());//concatenates the information for a post
                    textArea.append("\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No posts yet.");
            } // error message if there are no posts for an acc
            if (textArea!=null){
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 400));
                JOptionPane.showMessageDialog(null, scrollPane, "List of Posts", JOptionPane.PLAIN_MESSAGE);}
        }
    });
    ADDp.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = JOptionPane.showInputDialog("Enter the account name:");

            String title = JOptionPane.showInputDialog("Enter post title");
            String video = JOptionPane.showInputDialog("Add video");
            String likes = JOptionPane.showInputDialog("Enter number of likes");
            Data.makePost(username, title, video, likes);
        }
    });
    load.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(Paths.get("\\Users\\3520\\Desktop\\CSC2001F\\Assignmnet1\\TIKTOK\\src\\dataset.txt")); // gets  data from txt file in src
            } catch (IOException error) {
                throw new RuntimeException(error);
            }
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts= line.split(" ");
                if (parts[0].equals("Create")){                        //if line begins with Create then create acc
                    String name= parts[1];
                    String[] D1= Arrays.copyOfRange(parts,2,parts.length);
                    String description= String.join(" ",D1);
                    Account account = new Account(name,description);
                    Data.createAcc(account);

                }
                else if ((parts[0].equals("Add"))){                //if line begins with Add then add a post to an acc
                    String name= parts[1];
                    String video=parts[2];
                    String likes =parts[3];
                    String[] title1= Arrays.copyOfRange(parts,4,parts.length);
                    String title= String.join(" ",title1);
                    Data.makePost(name, title, video, likes);
                }
            }
            JOptionPane.showMessageDialog(null, "The accounts and posts have been loaded ");
        }
    });
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        TokTik tiktoc = new TokTik();

        JPanel panel = new JPanel(new GridLayout(2, 4));
        panel.add(tiktoc.DESCRIPTION);
        panel.add(tiktoc.LISTACC);
        panel.add(tiktoc.CREATEACC);
        panel.add(tiktoc.DELETEACC);
        panel.add(tiktoc.DisplayPosts);
        panel.add(tiktoc.ADDp);
        panel.add(tiktoc.load);
        panel.add(tiktoc.Quit);




        JFrame frame = new JFrame("TIKTOK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); // add the panel to the frame
        frame.pack();
        frame.setVisible(true);
    }
}
