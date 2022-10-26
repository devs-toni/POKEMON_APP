package domain;

import java.io.Serializable;

public abstract class Person implements Serializable {

    protected String name;
    protected String surname;
    private String email;
    private String phoneNumber;

    public Person() {

    }

    public Person(String email) {
        this.email = email;
    }

    public Person(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return name.concat(surname).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof Person)) return false;

        Person person = (Person) object;
        return person.getName().concat(person.getSurname()).equals(name.concat(surname));
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public abstract boolean isUser();
}
