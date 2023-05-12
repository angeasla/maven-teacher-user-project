package gr.aueb.cf.schoolapp.model;

/**
 * A {@link Teacher}  java Bean class.
 * @author petro
 * @version 0.1
 */

public class Teacher {

    private int id;
    private String firstname;
    private String lastname;

    public Teacher() {}

    public Teacher(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return id + ", " + firstname + ", " + lastname;
    }
}
