package auto_salon.Adminictrator;

import auto_salon.manager.Manager;

public class Administrator {
    public String firstname;
    public String lastname;
    public String login;
    public String password;

    public Administrator(String firstname, String lastname, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }
}
