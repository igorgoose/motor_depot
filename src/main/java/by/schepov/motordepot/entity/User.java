package by.schepov.motordepot.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private Role role;
    private String email;
    private boolean blocked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                blocked == user.blocked &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, email, blocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", isBlocked=" + blocked +
                '}';
    }
}
