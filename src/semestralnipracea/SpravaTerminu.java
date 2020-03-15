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
                if (termin.dStart.isBefore(seznamTerminu.zpristupniPrvni().dStart) || termin.dStart.isEqual(seznamTerminu.zpristupniPrvni().dStart)) {
                    if ((termin.tEnd.equals(seznamTerminu.zpristupniPrvni().tStart)
                            || termin.tEnd.isBefore(seznamTerminu.zpristupniPrvni().tStart))) {
                        seznamTerminu.vlozPrvni(termin);
                    }
                } else if (termin.dStart.isAfter(seznamTerminu.zpristupniPosledni().dEnd) // rozdelit podminku na dve 
                        || termin.dStart.isEqual(seznamTerminu.zpristupniPosledni().dEnd)
                        || seznamTerminu.zpristupniPosledni().dStart.isBefore(termin.dStart)) {
                    if (seznamTerminu.zpristupniPosledni().tEnd.equals(termin.tStart)
                            || seznamTerminu.zpristupniPosledni().tEnd.isAfter(termin.tStart)) { // tohle me jebe do prdele
                        seznamTerminu.vlozPosledni(termin);
                    }
                } else {                                                        // iterator, dokud zacatek data nebude rovno stejnemu datu terminu a zaroven konec musi se rovnat startu 
                    Iterator<Termin> it = seznamTerminu.iterator();
                    Termin t = it.next();
                    while (t.dStart.isEqual(termin.dStart) || termin.dStart.isBefore(t.dStart)) {   // tady je chyba na posledni termin + otestovat jine data pro prvni a posledni terminy
                        if (t.tEnd.equals(termin.tStart)) {
                            seznamTerminu.vlozPredchudce(termin);
                            break;
                        }
                        if (t.tStart.equals(termin.tEnd)) {
                            seznamTerminu.vlozNaslednika(termin);
                            break;
                        }
                        it.next();
                    }
                    // (pokud vsechno splneno, vloz predchudce, protoze aktualni je prvek, ktere kontroluji)
                }

            }
        } else {
            System.out.println("Nelze pridat termin");
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
        } else if (dStart.isAfter(seznamTerminu.zpristupniPosledni().dEnd)
                || dStart.isEqual(seznamTerminu.zpristupniPosledni().dEnd)
                && seznamTerminu.zpristupniPosledni().tEnd.equals(tStart)
                || seznamTerminu.zpristupniPosledni().tEnd.isBefore(tStart)) {  // jeste kontrola casu a to same na prvni polozku
            return true;
        } else if (dEnd.isBefore(seznamTerminu.zpristupniPrvni().dStart)
                || dEnd.isEqual(seznamTerminu.zpristupniPrvni().dStart)
                && (tEnd.equals(seznamTerminu.zpristupniPrvni().tStart)
                || tEnd.isBefore(seznamTerminu.zpristupniPrvni().tStart))) {
            return true;

        } else {
            Iterator<Termin> it = seznamTerminu.iterator();
            while (it.hasNext()) { // dokud ma dalsi prvky
                Termin t = it.next();
                // v tento den uz je termin, tak zkontroluj cas a kontroluj terminy dokud konec noveho terminu je pred zacatek terminu
                if (!t.dStart.isBefore(dEnd) && !t.dStart.equals(dEnd)) {       //do doby, kdy termin je mensi nebo rovno datu, jinak return true
                    return true;
                }
                if ((t.dEnd.equals(dStart) || t.dEnd.isAfter(dStart)) && !tEnd.isBefore(t.tStart) && !tStart.isAfter(t.tEnd) && !tEnd.equals(t.tStart) && !tStart.equals(t.tEnd)) {
                    return false;
                }
            }
//            if (!dEnd.isBefore(t.dEnd) && ((!tStart.equals(t.tEnd)) || !tEnd.equals(t.tStart)) // !tStart.isAfter // !tEnd.isBefore
//                    || dStart.isEqual(t.dEnd) && (!tStart.equals(t.tEnd) || !tEnd.equals(t.tStart))) {
//                return false;
//            }
        }
        return true;
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
        for (int i = 0; i < pocet - 1; i++) {
            vlozTermin(generujTermin(LocalDate.MAX, LocalDate.MIN));
        }
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
