package com.company.chatterbook.model;

import java.util.List;

public class User {
    private String name;
    private List<ChatterPost> chatterPosts;

    public User(String name, List<ChatterPost> chatterPosts) {
        this.name = name;
        this.chatterPosts = chatterPosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }
}
