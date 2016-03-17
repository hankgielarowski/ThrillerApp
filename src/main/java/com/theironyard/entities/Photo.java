package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by noellemachin on 3/17/16.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Thrill thrill;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    LocalDateTime time;

    public Photo(Thrill thrill, String filename, LocalDateTime time) {
        this.thrill = thrill;
        this.filename = filename;
        this.time = time;
    }

    public Photo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thrill getThrill() {
        return thrill;
    }

    public void setThrill(Thrill thrill) {
        this.thrill = thrill;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
