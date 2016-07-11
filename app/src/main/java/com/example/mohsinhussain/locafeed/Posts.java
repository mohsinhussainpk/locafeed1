package com.example.mohsinhussain.locafeed;

/**
 * Created by mohsinhussain on 08/07/2016.
 */
import java.lang.String;
public class Posts {

    private String title, description, votes;

    public Posts(String title, String description, String votes)
    {
        this.setTitle(title);
        this.setDescription(description);
        this.setVotes(votes);

    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

