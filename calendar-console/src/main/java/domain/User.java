package domain;

import domain.exception.LimitSizeException;
import domain.exception.NoTaskException;

import java.util.*;
import java.util.stream.Collectors;

public class User extends Person implements Comparable<User> {


    public enum UserType {
        ADMINISTRATOR, USER, EDITOR
    }

    private String username;
    private String password;
    private UserType type;

    private HashSet<Contact> contacts;
    private HashMap<String, Task> tasks;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;

        initCollections();
    }

    public User(String email, String username, String password, UserType type) {
        super(email);
        ;
        this.username = username;
        this.password = password;
        this.type = type;

        initCollections();

    }

    private void initCollections() {
        contacts = new HashSet<>();
        tasks = new HashMap<>();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public HashSet<Contact> getContacts() {
        return contacts;
    }


    public void addContact(Contact contact) {

    }

    public void addEvent(Event event) throws LimitSizeException {
        add(event);
    }

    public void addTask(Task task) throws LimitSizeException {
        add(task);
    }

    private void add(Task task) {
        tasks.put(task.getName(), task);
    }

    public Map<String, Task> getTaskMap() {
        return tasks;
    }

    public Collection<Task> getTasks() throws NoTaskException {
        return getOnlyTasks();
    }

    public Set<Task> getTasksSet() throws NoTaskException {
        return getOnlyTasks();
    }

    public Collection<Event> getEvents() {
        return getOnlyEvents();
    }

    public Set<Event> getEventsSet() {
        return getOnlyEvents();
    }

    private Set<Task> getOnlyTasks() throws NoTaskException {
        Set<Task> tasks = this.tasks.values().stream()
                .filter(task -> task instanceof Event)
                .collect(Collectors.toSet());

        if (tasks.isEmpty()) {
            throw new NoTaskException("No hay tareas.");
        }
        return tasks;
    }

    private Set<Event> getOnlyEvents() {
        return tasks.values().stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .collect(Collectors.toSet());

    }

    public void removeContact(Contact contact) {

    }

    public void removeEvent(Event event) {

    }

    public void removeTask(Task task) {

    }

    public void changePassword(String password) {

    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return user.getUsername().concat(user.getPassword()).equals(username.concat(password));
    }

    @Override
    public int hashCode() {
        return username.concat(password).hashCode();
    }

    @Override
    public String toString() {
        return super.toString().concat("[user=" + username + "]");
    }

    @Override
    public int compareTo(User user) {
        return username.compareTo(user.username);
    }

    @Override
    public boolean isUser() {
        return true;
    }
}
