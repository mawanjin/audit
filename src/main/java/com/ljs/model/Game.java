package com.ljs.model;



import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lala on 16/8/24.
 */
@Entity
@Table(name = "papa_sdk_game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "game_name")
    private String name;

    @Column(name = "app_key")
    private String appKey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
