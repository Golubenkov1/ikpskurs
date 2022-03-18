package com.company.vlsu.models;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;
@Data
@NoArgsConstructor
@Entity
public class Sensor {
    @Id @GeneratedValue private Long id;
    private String type;
    private String location;

    public Sensor(String type, String location) {
        this.type = type;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

