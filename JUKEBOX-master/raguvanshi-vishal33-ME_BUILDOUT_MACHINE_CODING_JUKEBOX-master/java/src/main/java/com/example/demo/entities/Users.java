package com.example.demo.entities;

import java.util.List;
import java.util.Comparator;

public class Users extends BaseEntity {
   
    private final String userName;

    public Users(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    public String getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public boolean checkIfContestExists(List<Users> users){

        for(Users userExist : users){
            if(userExist.equals(users)){
                return true;
            }
        }
            return false;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        Users other = (Users) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Users [id=" + id + ", userName=" + userName + "]";
    }

    public static final Comparator<Users> closingComparator = new Comparator<Users>() {
        public int compare(Users t1, Users t2) {
          return (t1.getId().compareTo(t2.getId()));
        }
      };

     

    public static final Comparator<Users> closingComparatorReverse = new Comparator<Users>() {
        public int compare(Users t1, Users t2) {
          return (t2.getId().compareTo(t1.getId()));
        }
      };
    
}

