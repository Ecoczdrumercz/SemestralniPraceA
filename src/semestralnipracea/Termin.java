/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public Termin(int idTermin, LocalDate dStart, LocalDate dEnd, LocalTime tStart, LocalTime tEnd) {
        this.idTermin = idTermin;
        this.dStart = dStart;
        this.dEnd = dEnd;
        this.tStart = tStart;
        this.tEnd = tEnd;
    }

    @Override
    public String toString() {
        return "Termin{" + "idTermin=" + idTermin + ", dStart=" + dStart + ", dEnd=" + dEnd + ", tStart=" + tStart + ", tEnd=" + tEnd + '}';
    }

}
