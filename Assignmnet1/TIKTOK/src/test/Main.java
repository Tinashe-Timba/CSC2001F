
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;
public class Main {

    public static void main(String[] args) {
        Database Data = new Database();

        Scanner s = new Scanner(System.in);
//        Menu for user
        System.out.println("Choose an action from the menu:" + '\n' +
                "1. Find the profile description for a given account" + '\n' +
                "2. List all accounts" + '\n' +
                "3. Create an account" + '\n' +
                "4. Delete an account" + '\n' +
                "5. Display all posts for a single account" + '\n' +
                "6. Add a new post for an account" + '\n' +
                "7. Load a file of actions from disk and process this" + '\n' +
                "8. Quit");
        String option;
        do {
            option = s.nextLine();              //takes in user input for the menu
            if ("1".equals(option)) {
                System.out.println("Enter the account name: ");
                String name = s.next();
                String description = Data.Description(name); // retrieves the description if the account is found
                if (description!=null){
                    System.out.println(description);
                }
                else {System.out.println("Account not found");}// error message if the account descrption is not found
                s.nextLine();

            } else if ("2".equals(option)) { // prints out all the account names
                System.out.println("The accounts in the TikTok database are: "+'\n');
                Data.AllAccs();
                s.nextLine();

            } else if ("3".equals(option)) {                                      //asks user for input and stores into variables for creation of an acc                                         //works
                System.out.println("Enter the account name");
                String username = s.nextLine();
                System.out.println("Enter account description");
                String Description = s.nextLine();
                System.out.println("Enter post title");                     // an account must have posts
                String title = s.nextLine();
                System.out.println("Enter video");
                String video = s.nextLine();
                System.out.println("Enter number of likes");
                String likes = s.nextLine();
                //int nlikes = Integer.parseInt(likes);
                Account account = new Account(username, Description);
                Data.createAcc(account);
                Data.makePost(username,title,video,likes);

            } else if ("4".equals(option)) {                                                                               //account deletion works
                System.out.println("Enter the account name");
                String name = s.nextLine();
                Account accountDelete = new Account(name, ""); // creates an account that will be used to find the account deleted
                Data.deleteAcc(accountDelete);
                System.out.println("Account has been deleted.");

            } else if ("5".equals(option)) {                                                                                   // displaying posts for an accounts works
                System.out.println("Enter the account name");
                String name = s.nextLine();
                List<Account.Post> posts=Data.AllPosts(name);
                if (posts !=null) {
                    System.out.println("These are "+name+"'s posts.");
                    for (Account.Post post : posts) {
                        System.out.println(post.getTitle() + '\n' + post.getVideo() + '\n' + post.getLikes()); //concatenates the information for a post
                    }
                }
                else {System.out.println("No posts yet.");} // error message if there are no posts for an acc

                }
                else if ("6".equals(option)) {                                                                           // addition of posts works well
                System.out.println("Enter the account name");
                String username = s.nextLine();
                System.out.println("Enter post title");
                String title = s.nextLine();
                System.out.println("Enter video");
                String video = s.nextLine();
                System.out.println("Enter number of likes");
                String likes = s.nextLine();
                //int nlikes = Integer.parseInt(likes);
                Data.makePost(username, title, video, likes);
            }
            else if ("7".equals(option)) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(Paths.get("\\Users\\3520\\Desktop\\CSC2001F\\Assignmnet1\\TIKTOK\\src\\dataset.txt")); // gets  data from txt file in src
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
                System.out.println("Accounts and posts have been loaded");

                }

            if (!option.equalsIgnoreCase("0") && !option.equalsIgnoreCase("8")) {
                System.out.println("Done");
            }
        } while (!option.equalsIgnoreCase("8"));
    }
}
