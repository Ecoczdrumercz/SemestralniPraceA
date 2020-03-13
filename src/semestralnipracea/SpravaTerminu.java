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
 * @author asdf
 */
public class SpravaTerminu implements ISpravaTerminu {

    AbstrDoubleList<Termin> seznamTerminu;

    @Override
    public void vlozTermin(Termin termin, enumPozice pozice) {
        if (jeTerminVolny(termin.dStart, termin.dEnd, termin.tStart, termin.tEnd)) {
            switch (pozice) {
                case PRVNI:
                    seznamTerminu.vlozPrvni(termin);
                    break;
                case POSLEDNI:
                    seznamTerminu.vlozPosledni(termin);
                    break;
                case PREDCHUDCE:
                    seznamTerminu.vlozPredchudce(termin);
                    break;
                case NASLEDNIK:
                    seznamTerminu.vlozNaslednika(termin);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    @Override
    public void vlozTermin(Termin termin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Termin zpristupniTermin(enumPozice pozice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Termin odeberTermin(enumPozice pozice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean jeTerminVolny(LocalDate dStart, LocalDate dEnd, LocalTime tStart, LocalTime tEnd) {
        if (seznamTerminu.jePrazdny()) {
            return true;
        } else {
            while (seznamTerminu.iterator().hasNext()) {
                if () {
                    seznamTerminu.iterator().next();
                }
            }
        }

    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean[][] volneCasy(LocalDate dStart, LocalDate dEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Termin generujTermin(LocalDate dStart, LocalDate dEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generuj(int pocet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uloz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nacti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zrus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
