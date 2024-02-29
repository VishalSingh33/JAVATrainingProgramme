package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long id;
    private final String name;
    private UserLevel level;
    private final List<Review> reviewList;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        level = UserLevel.LEVEL1; // at the beginning every user will have to start with level 1
        reviewList = new ArrayList<>();
    }

    public User(String name) {
        this.id = null;
        this.name = name;
        level = UserLevel.LEVEL1;
        reviewList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNum_ratings() {
        return reviewList.size();
    }

    public UserLevel getLevel() {
        return level;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    // @Override
    // public boolean equals(Object o) {
    // if (this == o) return true;
    // if (!(o instanceof User user)) return false;
    // return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName());
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((reviewList == null) ? 0 : reviewList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (level != other.level)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (reviewList == null) {
            if (other.reviewList != null)
                return false;
        } else if (!reviewList.equals(other.reviewList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [" + "id=" + id + ']';
    }

    public void refreshUserLevel() {
        int count = reviewList.size();
        if (count < 4)
            return;
        if (count >= 4 && count < 8)
            this.level = UserLevel.LEVEL2;
        else if (count >= 8 && count < 16)
            this.level = UserLevel.LEVEL3;
        else
            this.level = UserLevel.LEVEL4;
    }

    public void addToReviewList(Review review) {
        this.reviewList.add(review);
    }

    public void setReviewsList(int index, Review review) {
        this.reviewList.set(index, review);
    }

    public int getIndexOfInReviewList(Review review) {
        return this.reviewList.indexOf(review);
    }
}
