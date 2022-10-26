package domain;

public class Contact extends Person {

    private User user;

    public Contact(String name, String surname, String email, String phoneNumber) {
        super(name, surname, email, phoneNumber);
        user = null;
    }

    public Contact(String name, String surname, String email, String phoneNumber, User user) {
        this(name, surname, email, phoneNumber);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean isUser() {
        return user != null;
    }

}


