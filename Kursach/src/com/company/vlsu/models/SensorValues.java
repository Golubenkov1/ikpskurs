package com.company.vlsu.models;

import java.sql.Date;
import java.sql.Time;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;
@Data
@NoArgsConstructor
@Entity
public class SensorValues {

    @Id @GeneratedValue private Long id;
    private String sensorValue;
    private Sensor sensor;
    private Date date;
    private Time time;

    public SensorValues(String sensorValue, Sensor sensor, Date date, Time time) {
        this.sensorValue = sensorValue;
        this.sensor = sensor;
        this.date = date;
        this.time = time;
    }

    public SensorValues(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public Long getId() {
        return id;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
