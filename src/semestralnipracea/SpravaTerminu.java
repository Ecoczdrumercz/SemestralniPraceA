/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author asdf
 */
public class SpravaTerminu implements ISpravaTerminu {

    private AbstrDoubleList<Termin> seznamTerminu;

    public SpravaTerminu() {
        this.seznamTerminu = new AbstrDoubleList<>();
    }

    public AbstrDoubleList<Termin> getSeznamTerminu() {
        return seznamTerminu;
    }

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
        if (jeTerminVolny(termin.dStart, termin.dEnd, termin.tStart, termin.tEnd)) {
            if (getSeznamTerminu().jePrazdny()) {
                seznamTerminu.vlozPrvni(termin);
            } else {
                if (termin.dStart.isBefore(seznamTerminu.zpristupniPrvni().dStart) || (termin.dStart.isEqual(seznamTerminu.zpristupniPrvni().dStart) && termin.tEnd.equals(seznamTerminu.zpristupniPrvni().tStart)
                        || termin.tEnd.isBefore(seznamTerminu.zpristupniPrvni().tStart))) {
                    seznamTerminu.vlozPrvni(termin);
                } else if (termin.dStart.isAfter(seznamTerminu.zpristupniPosledni().dStart)
                        || termin.dStart.isEqual(seznamTerminu.zpristupniPosledni().dStart)
                        && (seznamTerminu.zpristupniPosledni().tEnd.equals(termin.tStart)
                        || seznamTerminu.zpristupniPosledni().tEnd.isBefore(termin.tStart))) {

                    seznamTerminu.vlozPosledni(termin);
                } else {
                    seznamTerminu.zpristupniPosledni();
                    seznamTerminu.vlozPredchudce(termin);               
                }
            }
        } else {
        }
    }

    @Override
    public Termin zpristupniTermin(enumPozice pozice) {
        if (!seznamTerminu.jePrazdny()) {
            switch (pozice) {
                case PRVNI:
                    return seznamTerminu.zpristupniPrvni();
                case POSLEDNI:
                    return seznamTerminu.zpristupniPosledni();
                case PREDCHUDCE:
                    return seznamTerminu.zpristupniPredchudce();
                case NASLEDNIK:
                    return seznamTerminu.zpristupniNaslednika();
                case AKTUALNI:
                    return seznamTerminu.zpristupniAktualni();
                default:
                    throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    @Override
    public Termin odeberTermin(enumPozice pozice) {
        if (!seznamTerminu.jePrazdny()) {
            switch (pozice) {
                case PRVNI:
                    return seznamTerminu.odeberPrvni();
                case POSLEDNI:
                    return seznamTerminu.odeberPosledni();
                case PREDCHUDCE:
                    return seznamTerminu.odeberPredchudce();
                case NASLEDNIK:
                    return seznamTerminu.odeberNaslednika();
                case AKTUALNI:
                    return seznamTerminu.odeberAktualni();
                default:
                    throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    @Override
    public Boolean jeTerminVolny(LocalDate dStart, LocalDate dEnd, LocalTime tStart, LocalTime tEnd) {
        if (seznamTerminu.jePrazdny()) {
            return true;
        } else {
            Iterator<Termin> it = seznamTerminu.iterator();
            while (it.hasNext()) {
                Termin t = it.next();

                if (!t.dStart.isBefore(dEnd) && !t.dStart.equals(dEnd)) {
                    return true;
                }
                if ((t.dEnd.equals(dStart) || t.dEnd.isAfter(dStart)) && !tEnd.isBefore(t.tStart) && !tStart.isAfter(t.tEnd) && !tEnd.equals(t.tStart) && !tStart.equals(t.tEnd)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean[][] volneCasy(LocalDate dStart, LocalDate dEnd) {

        final int POCET_DNU = (int) ChronoUnit.DAYS.between(dStart, dEnd);
        final int POCET_HODIN = 8;  //
        Boolean[][] volny = new Boolean[POCET_DNU][POCET_HODIN];
        for (Boolean[] booleans : volny) {
            Arrays.fill(booleans, Boolean.TRUE);
        }
        
        if (seznamTerminu.jePrazdny()) {
            return volny;
        } else {

            Iterator<Termin> iter = seznamTerminu.iterator();
            while (iter.hasNext()) {
                Termin t = iter.next();
                System.out.println(t);
                if (t.dStart.isAfter(dStart) || dStart.isEqual(t.dStart)) {      // je v rozmezi intervalu nebo na pocatku
                    
                    int startIndex = (int) ChronoUnit.DAYS.between(dStart, t.dStart);
                    int konec = (int) ChronoUnit.DAYS.between(dStart, dEnd);
                    int indexOdHodin = t.tStart.getHour() - POCET_HODIN;
                    int indexDoHodin = t.tEnd.getHour() - POCET_HODIN;
                    
                    if(t.dEnd.isBefore(dEnd)){
                        konec = (int) ChronoUnit.DAYS.between(dStart, t.dEnd); 
                    }
                    
                    for (int x = startIndex; x < konec; x++) {
                        for (int y = indexOdHodin; y < indexDoHodin; y++) {
                            volny[x][y] = false;
                        }
                    }
                }
            }
        return volny;
        }
    }

    @Override
    public Termin generujTermin(LocalDate dStart, LocalDate dEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generuj(int pocet) {
        for (int i = 0; i < pocet - 1; i++) {
            vlozTermin(generujTermin(LocalDate.parse("2020-03-14"), LocalDate.parse("2020-04-14")));
        }
    }

    @Override
    public void uloz() {
        // uloz do souboru
    }

    @Override
    public void nacti() {
        // nacti ze souboru pravdepodobne
    }

    @Override
    public void zrus() {
        seznamTerminu = null;
    }

    @Override
    public Iterator<Termin> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
