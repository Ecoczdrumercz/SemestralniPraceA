/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalTime;
import semestralnipraceb.Terapeut;

/**
 *
 * @author DanielP
 */
public class Termin {

     int idTermin;
     LocalDate dStart;
     LocalDate dEnd;
     LocalTime tStart;
     LocalTime tEnd;
//     Terapeut t;

    public Termin(int idTermin, LocalDate dStart, LocalDate dEnd, LocalTime tStart, LocalTime tEnd) {
        this.idTermin = idTermin;
        this.dStart = dStart;
        this.dEnd = dEnd;
        this.tStart = tStart;
        this.tEnd = tEnd;
//        this.t = t;
    }

//    public int getIdTermin() {
//        return idTermin;
//    }
//
//    public void setIdTermin(int idTermin) {
//        this.idTermin = idTermin;
//    }
//
//    public LocalDate getdStart() {
//        return dStart;
//    }
//
//    public void setdStart(LocalDate dStart) {
//        this.dStart = dStart;
//    }
//
//    public LocalDate getdEnd() {
//        return dEnd;
//    }
//
//    public void setdEnd(LocalDate dEnd) {
//        this.dEnd = dEnd;
//    }
//
//    public LocalTime gettStart() {
//        return tStart;
//    }
//
//    public void settStart(LocalTime tStart) {
//        this.tStart = tStart;
//    }
//
//    public LocalTime gettEnd() {
//        return tEnd;
//    }
//
//    public void settEnd(LocalTime tEnd) {
//        this.tEnd = tEnd;
//    }
//
//    public Terapeut getT() {
//        return t;
//    }
//
//    public void setT(Terapeut t) {
//        this.t = t;
//    }
    
    

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(idTermin).append(";").append(dStart).append(";").append(dEnd).append(";").append(tStart).append(";").append(tEnd).append(";");
        return s.toString();
    }

}
