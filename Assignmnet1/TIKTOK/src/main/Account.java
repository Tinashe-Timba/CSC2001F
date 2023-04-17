package main;
//AUTHOR:TINASHE TIMBA
// STUDENT NUMBER:TMBTIN004

//CREATING An ACCOUNTS CLASS FOR THE ACCOUNTS
//Author:Tinashe Timba

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an account  for the TOKTIK app
 * it manages   the account information as a whole such as username description and posts
 *
 */

// an outer class to manage the account as a whole
public class Account {
    // an inner class to manage posts

    /**
     *   an inner class to manage posts
     *   it contains information about the post , such as title, video and likes
     */
    public class Post {

    //making variables for Posts
        public String title;
        public String video;
        public String likes;

        /**
         * Constructor for creating a Post object with title, video and likes
         * @param title title of post
         * @param video video name of post
         * @param likes number of likes
         */
        public Post(String title, String video, String likes) {
            this.title = title;
            this.video = video;
            this.likes = likes;
        }

        /**
         * Default constructor for creating an empty Post object
         */
        public Post(){}
    //get and set the instance  variables

        /**
         * get method to extract post title
         * @return title of post
         */
        public String getTitle() {
            return "Title: " + title;
        }
        /**
         * set title of post
         * @param title  title of post
         */

        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * set the video associated with post
         * @param video video asscoaited with post
         */

        public void setVideo(String video) {
            this.video = video;
        }

        /**
         * Get the video from the post
         * @return video from post
         */
        public String getVideo() {
            return "Video: " + video;
        }

        /**
         * set the likes associated with the post
         * @param likes likes for the post
         */
        public void setLikes(String likes) {
            this.likes = likes;
        }

        /**
         * get number of likes
         * @return number of likes
         */
        public String getLikes() {
            return "Number of Likes: " + likes;
        }

        /**
         * overide the toString() method to provide a specfic output
         * @return a string version of the post
         */

        public String toString(){
            return "Title: " + title+'\n'+"Video: " + video+'\n'+"Number of Likes: " + likes;
        }
    }

    private String username;
    //private String email;
    private String description;
    public Post post;
    public List posts; // creates an array of posts

    // constructor with basic account details

    /**
     * Constructor for creating an account with name and deescrption
     * @param username username associated with account
     * @param description description of the account holder
     */
    public Account(String username,String description){
        this.username=username;
        this.description=description;
        this.posts= new ArrayList<>();
    }

    /**
     * Default constructor for creating an empty account
     */
    public Account(){}


    /**
     * Get the username associated with the account.
     * @return username associated with account
     */
    // retrieve and set information
    public String getUsername(){
        return username;
    }

    /**
     * set the username
     * @param username username of account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set the description of account
     * @param description descrption of the account
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Get the description of the account
     * @return descritption of the account
     */
    public String getDescription(){
        return "The profile description is: "+ description;
    }

    /**
     * get post of the account
     * @return post from the specific account
     */
    public List<Post> getPosts() {
        return posts;
    }

    //Adds a post to an account using the account array list

    /**
     *  method to add a post for an account holder
     * @param title title of post
     * @param video video name
     * @param likes number of like recieved
     */
    public void addPost(String title,String video,String likes) {
        Post post = new Post(title,video,likes);
        this.posts.add(0,post);}


    /**
     * overide the toString method for the account class
     * @return the profile description
     */

    // output edit for later
    public String toString(){
        return "The profile description is: "+ description;
    }
}
