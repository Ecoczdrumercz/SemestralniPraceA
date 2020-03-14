/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalTime;

public class SemestralniPraceA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpravaTerminu list = new SpravaTerminu();
        Termin termin = new Termin(0, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("12:00:00"), LocalTime.parse("14:00:00"));
        Termin termin2 = new Termin(1, LocalDate.parse("2020-03-20"), LocalDate.parse("2020-03-21"), LocalTime.parse("14:00:00"), LocalTime.parse("16:00:00"));
        Termin termin3 = new Termin(1, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin4 = new Termin(1, LocalDate.parse("2020-03-15"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin5 = new Termin(1, LocalDate.parse("2020-03-23"), LocalDate.parse("2020-03-28"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin6 = new Termin(1, LocalDate.parse("2020-03-22"), LocalDate.parse("2020-03-22"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        list.vlozTermin(termin);
        list.vlozTermin(termin);
        list.vlozTermin(termin2);
        list.vlozTermin(termin3);
        list.vlozTermin(termin4);
        list.vlozTermin(termin5);
        list.vlozTermin(termin6);

        for (Termin t : list.getSeznamTerminu()) {
            System.out.println(t);
        }
    }

}
