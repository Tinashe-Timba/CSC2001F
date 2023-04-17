package main;
//AUTHOR:TINASHE TIMBA
// STUDENT NUMBER:TMBTIN004



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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;


/**
 * TokTik is a Java Swing application that simulates a social media platform like TikTok.
 * It allows users to create accounts, make posts, view posts, delete accounts, and load actions from a file.
 * It uses a custom database to store account and post information. 
 */

public class TokTik extends JFrame {
    // create buttons to be added to the gui interface
    private JButton DESCRIPTION;
    private JButton load;
    private JButton ADDp;
    private JButton DisplayPosts;
    private JButton DELETEACC;
    private JButton CREATEACC;
    private JButton LISTACC;
    private JButton Quit;
    //scroll panes for displaying accounts and post
    private JScrollPane AccountsList;
    private JScrollPane POSTSlist;
    private JScrollPane ALLACCS;

    /**
     * Constructs a new TokTik object that creates  a window for TokTik
     * It intialises the buttons for the menu and makes the event listeners for  for the button actions
     */
    public TokTik() {
        // create a new toktik database and name buttons
        Database Data = new Database();
        DESCRIPTION = new JButton("1. Find the profile description for a given account");
        load = new JButton("7. Load a file of actions from disk and process this");
        ADDp = new JButton("6. Add a new post for an account");
        DisplayPosts = new JButton("5. Display all posts for a single account");
        DELETEACC = new JButton("4. Delete an account");
        CREATEACC = new JButton("3. Create an account");
        LISTACC = new JButton("2. List all accounts");


// add actions for the buttons
        DESCRIPTION.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the account name:");
                String description = Data.Description(name); // retrieves the description if the account is found
                if (description != null) {
                    JOptionPane.showMessageDialog(null, description);
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found");
                }

            }
        });
        LISTACC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = new JTextArea(20, 20);
                //JOptionPane.showMessageDialog(null, "The accounts in the TikTok database are: ");
                Data.accounts.inOrder1();
                ArrayList<String> lists = Data.accounts.list();
                if (lists.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are no accounts");
                } else {
                    for (String element : lists) {
                        textArea.append(element + "\n");
                    }
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 400));
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Accounts", JOptionPane.PLAIN_MESSAGE);
                }

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
                Data.makePost(username, title, video, likes);
                JOptionPane.showMessageDialog(null,"Account has been created");


            }
        });
        DELETEACC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter the account name:");
                Account accountDelete = new Account(username, ""); // creates an account that will be used to find the account deleted
                Data.deleteAcc(accountDelete);
                JOptionPane.showMessageDialog(null, "Account has been deleted.");
            }
        });

        DisplayPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the account name:");
                JTextArea textArea = new JTextArea(20, 20);
                List<Account.Post> posts = Data.AllPosts(name);
                if (posts != null) {
                    textArea.append("These are " + name + "'s posts." + "\n");
                    for (Account.Post post : posts) {
                        textArea.append(post.getTitle() + '\n' + post.getVideo() + '\n' + post.getLikes());//concatenates the information for a post
                        textArea.append("\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No posts yet.");
                } // error message if there are no posts for an acc
                if (textArea != null) {
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 400));
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Posts", JOptionPane.PLAIN_MESSAGE);
                }
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
                JOptionPane.showMessageDialog(null, "Post has been added.");
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scanner = null;
                String input = JOptionPane.showInputDialog("Enter the file name:");
                try {
                    scanner = new Scanner(Paths.get("src//"+input)); // gets  data from txt file in src
                } catch (IOException error) {
                    //throw new RuntimeException(error);
                    JOptionPane.showMessageDialog(null, "Error loading file: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    if (parts[0].equals("Create")) {                        //if line begins with Create then create acc
                        String name = parts[1];
                        String[] D1 = Arrays.copyOfRange(parts, 2, parts.length);
                        String description = String.join(" ", D1);
                        Account account = new Account(name, description);
                        Data.createAcc(account);

                    } else if ((parts[0].equals("Add"))) {                //if line begins with Add then add a post to an acc
                        String name = parts[1];
                        String video = parts[2];
                        String likes = parts[3];
                        String[] title1 = Arrays.copyOfRange(parts, 4, parts.length);
                        String title = String.join(" ", title1);
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


    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        TokTik tiktoc = new TokTik();

        JPanel panel = new JPanel(new GridLayout(8, 1));
    

      
    
        panel.add(tiktoc.DESCRIPTION,CENTER_ALIGNMENT);
        panel.add(tiktoc.LISTACC);
        panel.add(tiktoc.CREATEACC);
        panel.add(tiktoc.DELETEACC);
        panel.add(tiktoc.DisplayPosts);
        panel.add(tiktoc.ADDp);
        panel.add(tiktoc.load);
        panel.add(tiktoc.Quit);
        tiktoc.DESCRIPTION.setBackground(Color.BLACK);
        tiktoc.DESCRIPTION.setForeground(Color.WHITE);
        
        tiktoc.LISTACC.setBackground(Color.BLACK);
        tiktoc.LISTACC.setForeground(Color.WHITE);
        
        tiktoc.CREATEACC.setBackground(Color.BLACK);
        tiktoc.CREATEACC.setForeground(Color.WHITE);
        
        tiktoc.DELETEACC.setBackground(Color.BLACK);
        tiktoc.DELETEACC.setForeground(Color.WHITE);
        
        tiktoc.DisplayPosts.setBackground(Color.BLACK);
        tiktoc.DisplayPosts.setForeground(Color.WHITE);
        
        tiktoc.ADDp.setBackground(Color.BLACK);
        tiktoc.ADDp.setForeground(Color.WHITE);
        
        tiktoc.load.setBackground(Color.BLACK);
        tiktoc.load.setForeground(Color.WHITE);
        
        tiktoc.Quit.setBackground(Color.BLACK);
        tiktoc.Quit.setForeground(Color.WHITE);

        JFrame frame = new JFrame("TOKTIK");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); // add the panel to the frame
        frame.pack();
        frame.setVisible(true);
    }
   
    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    
    
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        DESCRIPTION = new JButton();
        DESCRIPTION.setText("1. Find the profile description for a given account");
        panel1.add(DESCRIPTION, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        load = new JButton();
        load.setText("7. Load a file of actions from disk and process this");
        panel1.add(load, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ADDp = new JButton();
        ADDp.setText("6. Add a new post for an account");
        panel1.add(ADDp, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DELETEACC = new JButton();
        DELETEACC.setText("4. Delete an account");
        panel1.add(DELETEACC, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CREATEACC = new JButton();
        CREATEACC.setText("3. Create an account");
        panel1.add(CREATEACC, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LISTACC = new JButton();
        LISTACC.setText("2. List all accounts");
        panel1.add(LISTACC, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DisplayPosts = new JButton();
        DisplayPosts.setText("5. Display all posts for a single account");
        panel1.add(DisplayPosts, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Quit = new JButton();
        Quit.setText(" 8.Quit");
        panel1.add(Quit, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        AccountsList = new JScrollPane();
        panel1.add(AccountsList, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ALLACCS = new JScrollPane();
        AccountsList.setViewportView(ALLACCS);
        POSTSlist = new JScrollPane();
        panel1.add(POSTSlist, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }
}
