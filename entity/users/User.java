package entity.users;

import java.util.Date;

public class User {
    private final String name;
    private final Date createdOn;
    private UserRole role;

    public User(String name) {
        this.name = name;
        this.createdOn = new Date();
        this.role = Viewer.getInstance();
    }

    public String getName() {
        return name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
