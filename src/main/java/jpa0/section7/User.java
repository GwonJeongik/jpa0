package jpa0.section7;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @OneToOne
    private Locker locker;

    public void putLocker(Locker locker) {
        this.locker = locker;
        locker.setUser(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Locker getLocker() {
        return locker;
    }
}
