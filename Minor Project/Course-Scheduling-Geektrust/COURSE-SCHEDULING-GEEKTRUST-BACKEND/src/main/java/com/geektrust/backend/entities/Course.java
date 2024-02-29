package com.geektrust.backend.entities;

import java.util.Map;
import java.util.TreeMap;

public class Course implements Comparable<Course>{

    private final String courseID;
    private final String courseName;
    private final String instructor;
    private final String date;
    private final int minCapacity;
    private final int maxCapacity;
    private boolean isAllotted;
    private boolean isCancelled;
   
private final Map<String , Employee> registeredEmployees;

    public Course(String courseID, String courseName, String instructor, String date, int minCapacity, int maxCapacity, boolean isAllotted, boolean isCancelled) { 
        this.courseID=courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.isAllotted = isAllotted;
        this.isCancelled = isCancelled;       
        registeredEmployees = new TreeMap<>();
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDate() {
        return date;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isAllotted() {
        return isAllotted;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setAllotted(boolean allotted) {
        isAllotted = allotted;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
    
    public Map<String, Employee> getRegisteredEmployees() {
        return registeredEmployees;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseID == null) ? 0 : courseID.hashCode());
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
        result = prime * result + (isAllotted ? 1231 : 1237);
        result = prime * result + (isCancelled ? 1231 : 1237);
        result = prime * result + maxCapacity;
        result = prime * result + minCapacity;
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
        Course other = (Course) obj;
        if (courseID == null) {
            if (other.courseID != null)
                return false;
        } else if (!courseID.equals(other.courseID))
            return false;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (instructor == null) {
            if (other.instructor != null)
                return false;
        } else if (!instructor.equals(other.instructor))
            return false;
        if (isAllotted != other.isAllotted)
            return false;
        if (isCancelled != other.isCancelled)
            return false;
        if (maxCapacity != other.maxCapacity)
            return false;
        if (minCapacity != other.minCapacity)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName + ", date=" + date
                + ", instructor=" + instructor + ", isAllotted=" + isAllotted + ", isCancelled="
                + isCancelled + ", maxCapacity=" + maxCapacity + ", minCapacity=" + minCapacity
                + "]";
    }

    @Override
    public int compareTo(Course c) {
        return this.courseName.compareTo(c.courseName);
    }
}