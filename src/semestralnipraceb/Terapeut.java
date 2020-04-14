/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import semestralnipracea.SpravaTerminu;

/**
 *
 * @author DanielP
 */
public class Terapeut {
    private int id;
    private String name;
    private SpravaTerminu sprava;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpravaTerminu getSprava() {
        return sprava;
    }

    public void setSprava(SpravaTerminu sprava) {
        this.sprava = sprava;
    }

    public Terapeut(int id, String name, SpravaTerminu sprava) {
        this.id = id;
        this.name = name;
        this.sprava = sprava;
    }

    @Override
    public String toString() {
        return "Terapeut{" + "id=" + id + ", name=" + name + ", sprava=" + sprava + '}';
    }
    
    
    
}
