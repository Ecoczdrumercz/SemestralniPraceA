/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.converter.LocalDateTimeStringConverter;
import semestralnipraceb.Terapeut;

/**
 *
 * @author asdf
 */
public class SpravaTerminu implements ISpravaTerminu, Serializable {

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
                if (t.dStart.isAfter(dStart) || dStart.isEqual(t.dStart)) {

                    int startIndex = (int) ChronoUnit.DAYS.between(dStart, t.dStart);
                    int konec = (int) ChronoUnit.DAYS.between(dStart, dEnd);
                    int indexOdHodin = t.tStart.getHour() - POCET_HODIN;
                    int indexDoHodin = t.tEnd.getHour() - POCET_HODIN;

                    if (t.dEnd.isBefore(dEnd)) {
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

        Random rand = new Random();
        LocalDateTime localDateTime;

        LocalDate zacatek = between(dStart, dEnd); // 16.4 -30.4      
        LocalDate konec = dEnd;
        LocalTime zacatekCasu;
        LocalTime KonecCasu;

        if ((rand.nextInt((2 - 1) + 1) + 1) == 1) {
            zacatekCasu = LocalTime.of((rand.nextInt((14 - 8) + 1) + 8), 0);
            KonecCasu = zacatekCasu.plusHours(4);
        } else {
            zacatekCasu = LocalTime.of((rand.nextInt((16 - 8) + 1) + 8), 0);
            KonecCasu = zacatekCasu.plusHours(2);
        }

        Termin termin = new Termin(rand.nextInt(Integer.MAX_VALUE), zacatek, konec, zacatekCasu, KonecCasu);
        return termin;
    }

    public LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public void generuj(int pocet) {

        int aktualniPocet = seznamTerminu.getPocetPrvku();
        int pocetKonec = seznamTerminu.getPocetPrvku() + pocet;

        while (aktualniPocet < pocetKonec) {
            LocalDate random = between(LocalDate.now(), LocalDate.now().plusYears(1));
            Termin termin = generujTermin(random, random.plusMonths(3));
            if (jeTerminVolny(termin.dStart, termin.dEnd, termin.tStart, termin.tEnd)) {
                vlozTermin(termin);
                aktualniPocet++;
            }
        }
    }

    @Override
    public void uloz() {
        try {
            File myObj = new File("SpravaTerminu.txt");
            if (myObj.createNewFile()) {
                FileWriter writer = new FileWriter("SpravaTerminu.txt");
                Iterator<Termin> iter = seznamTerminu.iterator();
                while (iter.hasNext()) {
                    Termin t = iter.next();
                    writer.write(t.toString() + "\n");
                }
                writer.close();

            } else {
                File file = new File("SpravaTerminu.txt");
                file.delete();
                FileWriter writer = new FileWriter("SpravaTerminu.txt");
                Iterator<Termin> iter = seznamTerminu.iterator();
                while (iter.hasNext()) {
                    Termin t = iter.next();
                    writer.write(t.toString() + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void nacti() {

        try {
            File myObj = new File("SpravaTerminu.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                if (seznamTerminu != null) {
                    String data = myReader.nextLine();
                    String[] pole = data.split(";");
                    int id = Integer.valueOf(pole[0]);
                    LocalDate dStart = LocalDate.parse(pole[1]);
                    LocalDate dEnd = LocalDate.parse(pole[2]);
                    LocalTime tStart = LocalTime.parse(pole[3]);
                    LocalTime tEnd = LocalTime.parse(pole[4]);
//                    Terapeut ter = new Terapeut(2, "aaa", this);
                    Termin t = new Termin(id, dStart, dEnd, tStart, tEnd);
                    seznamTerminu.vlozPrvni(t);
                } else {
                    String data = myReader.nextLine();
                    String[] pole = data.split(";");
                    int id = Integer.valueOf(pole[0]);
                    LocalDate dStart = LocalDate.parse(pole[1]);
                    LocalDate dEnd = LocalDate.parse(pole[2]);
                    LocalTime tStart = LocalTime.parse(pole[3]);
                    LocalTime tEnd = LocalTime.parse(pole[4]);
//                    Terapeut ter = new Terapeut(2, "aaa", this);
                    Termin t = new Termin(id, dStart, dEnd, tStart, tEnd);
                    seznamTerminu.vlozPrvni(t);
                    seznamTerminu.vlozNaslednika(t);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void zrus() {
        seznamTerminu.zrus();
    }

    @Override
    public Iterator<Termin> iterator() {
        return seznamTerminu.iterator();
    }
}
