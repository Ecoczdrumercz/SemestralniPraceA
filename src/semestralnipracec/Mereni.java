/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracec;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author DanielP
 */
public class Mereni {
    private int id;
    private int sensor_id;
    private LocalDateTime time;
    private double m3;

    public Mereni(int id, int sensor_id, LocalDateTime time, double m3) {
        this.id = id;
        this.sensor_id = sensor_id;
        this.time = time;
        this.m3 = m3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getM3() {
        return m3;
    }

    public void setM3(double m3) {
        this.m3 = m3;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", sensor_id=" + sensor_id + ", time=" + time + ", m3=" + m3 + '}';
    }
    
    
}
