package com.example.demo.entities;

public class Review {
    private final Long id;
    private final int rating;
    private final String dishName;
    private final String description;
    private final User createdByUser;
    private final Restaurant createdForRestaurant;

    public Review(Long id, int rating, String dishName, String description, User user,
            Restaurant restaurant) {
        this.id = id;
        this.rating = rating;
        this.dishName = dishName;
        this.description = description;
        this.createdByUser = user;
        this.createdForRestaurant = restaurant;
    }

    public Review(Long id, int rating, User user, Restaurant restaurant) {
        this.id = id;
        this.rating = rating;
        this.dishName = "";
        this.description = "";
        this.createdByUser = user;
        this.createdForRestaurant = restaurant;
    }

    public Review(int rating) {
        this.id = null;
        this.rating = rating;
        this.dishName = "";
        this.description = "";
        this.createdByUser = null;
        this.createdForRestaurant = null;
    }

    public Review(int rating, String dishName, String description, User user,
            Restaurant restaurant) {
        this.id = null;
        this.rating = rating;
        this.dishName = dishName;
        this.description = description;
        this.createdByUser = user;
        this.createdForRestaurant = restaurant;
    }

    public Review(int rating, String dishName, String description) {
        this.id = null;
        this.rating = rating;
        this.dishName = dishName;
        this.description = description;
        this.createdByUser = null;
        this.createdForRestaurant = null;
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o)
    //         return true;
    //     if (!(o instanceof Review review))
    //         return false;
    //     return Objects.equals(createdByUser, review.createdByUser)
    //             && Objects.equals(createdForRestaurant, review.createdForRestaurant);
    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (createdByUser == null) {
            if (other.createdByUser != null)
                return false;
        } else if (!createdByUser.equals(other.createdByUser))
            return false;
        if (createdForRestaurant == null) {
            if (other.createdForRestaurant != null)
                return false;
        } else if (!createdForRestaurant.equals(other.createdForRestaurant))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rating != other.rating)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdByUser == null) ? 0 : createdByUser.hashCode());
        result = prime * result
                + ((createdForRestaurant == null) ? 0 : createdForRestaurant.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + rating;
        return result;
    }

    @Override
    public String toString() {
        return "Review [" + "id=" + id + ']';
    }

    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getdishName() {
        return dishName;
    }

    public String getDescription() {
        return description;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public Restaurant getCreatedForRestaurant() {
        return createdForRestaurant;
    }
}
