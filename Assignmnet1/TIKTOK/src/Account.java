import java.util.ArrayList;
// an outer class to manage the account as a whole
public class Account {
    // an inner class to manage posts
    public class Post {
        public String title;
        public String video;
        public int likes;

        public Post(String title, String video, int likes) {
            this.title = title;
            this.video = video;
            this.likes = likes;
        }
        public Post(){}

        public String getTitle() {
            return "Title: " + title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideo() {
            return "Video: " + video;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getLikes() {
            return "Number of Likes: " + likes;
        }

        public String toString(){
            return "Title: " + title+'\n'+"Video: " + video+'\n'+"Number of Likes: " + likes;
        }
    }

    private String username;
    //private String email;
    private String description;
    public Post post;
    public ArrayList<Post> posts;

    // constructor with basic account details
    public Account(String username,String description){
        this.username=username;
        this.description=description;
    }
    public Account(){}



    // retrieve and set information
    public String getUsername(){
        return "The account name is: "+ username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription(){
        return "The profile description is: "+ description;
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(0, post);
    }

    // output edit for later
    public String toString(){
        return "The profile description is: "+ description;
    }
}
