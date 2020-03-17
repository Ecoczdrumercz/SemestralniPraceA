/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

/**
 *
 * @author DanielP
 */
public interface ISpravaTerminu {

    void vlozTermin(Termin termin, enumPozice pozice);

    void vlozTermin(Termin termin);

    Termin zpristupniTermin(enumPozice pozice);

    Termin odeberTermin(enumPozice pozice);

    Boolean jeTerminVolny(LocalDate dStart, LocalDate dEnd,
            LocalTime tStart, LocalTime tEnd);

    Iterator<Termin> iterator();

    Boolean[][] volneCasy(LocalDate dStart, LocalDate dEnd);

    Termin generujTermin(LocalDate dStart, LocalDate dEnd);

    void generuj(int pocet);

    void uloz();

    void nacti();

    void zrus();
}
