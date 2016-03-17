package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by noellemachin on 3/17/16.
 */
@Entity
@Table (name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    Thrill thrill;

    public Favorite(User user, Thrill thrill) {
        this.user = user;
        this.thrill = thrill;
    }

    public Favorite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thrill getThrill() {
        return thrill;
    }

    public void setThrill(Thrill thrill) {
        this.thrill = thrill;
    }
}
