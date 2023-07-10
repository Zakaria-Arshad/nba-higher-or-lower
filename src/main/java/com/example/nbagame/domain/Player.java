package com.example.nbagame.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

// The Player entity is a representation of the stats table in the MySQL database.
// Each property in the Player entity corresponds to a column in the table.
@Entity
@Table(name="stats")
public class Player {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String team;
    @Column(name = "points_per_game")
    private BigDecimal points;
    @Column(name = "assists_per_game")
    private BigDecimal assists;
    @Column(name = "three_pt_percentage")
    private BigDecimal threePtPercentage;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public BigDecimal getAssists() {
        return assists;
    }

    public BigDecimal getThreePtPercentage() {
        return threePtPercentage;
    }

}
