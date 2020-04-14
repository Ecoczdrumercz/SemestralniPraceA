/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import semestralnipraceb.Terapeut;

public class SemestralniPraceA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpravaTerminu list = new SpravaTerminu();
        Terapeut ter = new Terapeut(0, "asdf", list);
        Termin termin = new Termin(0, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("12:00:00"), LocalTime.parse("14:00:00"),ter);
        Termin termin2 = new Termin(1, LocalDate.parse("2020-03-20"), LocalDate.parse("2020-03-21"), LocalTime.parse("14:00:00"), LocalTime.parse("16:00:00"),ter);
        Termin termin3 = new Termin(1, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"),ter);
        Termin termin4 = new Termin(1, LocalDate.parse("2020-03-15"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"),ter); // neprida se
        Termin termin5 = new Termin(1, LocalDate.parse("2020-03-23"), LocalDate.parse("2020-03-28"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"),ter);
        Termin termin6 = new Termin(1, LocalDate.parse("2020-03-22"), LocalDate.parse("2020-03-22"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"),ter);
        Termin termin7 = new Termin(1, LocalDate.parse("2020-03-22"), LocalDate.parse("2020-03-22"), LocalTime.parse("08:00:00"), LocalTime.parse("10:00:00"),ter);
        Termin termin8 = new Termin(1, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-22"), LocalTime.parse("08:00:00"), LocalTime.parse("10:00:00"),ter);
//        list.vlozTermin(termin); // prida
//        list.vlozTermin(termin); // neprida
//        list.vlozTermin(termin2); // prida
//        list.vlozTermin(termin3); // prida
//        list.vlozTermin(termin4); // neprida
//        list.vlozTermin(termin5); //prida
//        list.vlozTermin(termin8); // prida
//        list.vlozTermin(termin6); // prida
//        list.vlozTermin(termin7); // prida
        //list.uloz();
        
        list.zrus();
//        if(!list.getSeznamTerminu().jePrazdny())
//        for (Termin t : list) {
//            
//        }
        list.nacti();
        
        for (Termin t : list.getSeznamTerminu()) {
            System.out.println(t);
        }

        
        
        StringBuilder s = new StringBuilder();
        Boolean [][] volnecase = list.volneCasy(LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-29"));
        
        for (int i = 0; i < volnecase.length-1; i++) {
            for (int j = 0; j < volnecase[i].length; j++) {
                s.append(volnecase[i][j]).append(" | ");
            }
            s.append(i+1).append("\n");
        }
        System.out.println(s);
    }

}
