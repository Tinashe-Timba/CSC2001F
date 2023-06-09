import java.util.ArrayList;
import java.util.List;

// an outer class to manage the account as a whole
public class Account {
    // an inner class to manage posts
    public class Post {

        public String title;
        public String video;
        public String likes;

        public Post(String title, String video, String likes) {
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

        public void setLikes(String likes) {
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
    public List posts;

    // constructor with basic account details
    public Account(String username,String description){
        this.username=username;
        this.description=description;
        this.posts= new ArrayList<>();
    }
    public Account(){}



    // retrieve and set information
    public String getUsername(){
        return username;
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
    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(String title,String video,String likes) {
        Post post = new Post(title,video,likes);
        this.posts.add(post);}






    // output edit for later
    public String toString(){
        return "The profile description is: "+ description;
    }
}
