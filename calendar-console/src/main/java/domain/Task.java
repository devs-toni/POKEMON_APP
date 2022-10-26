package domain;

import java.io.Serializable;

public class Task implements Comparable<Task>, Serializable {

    protected String name;
    private String description;
    private User owner;
    private int duration;

    public Task(String name, String description, int duration, User owner) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Task)) {
            return false;
        }

        Task task = (Task) object;
        return task.getName().equals(name);
    }

    @Override
    public String toString() {
        return name + " [" + description + "]";

    }

    @Override
    public int compareTo(Task task) {
        return (task.duration - duration);
    }
}
