/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Random;
import semestralnipracea.SpravaTerminu;
import semestralnipracea.Termin;

/**
 *
 * @author DanielP
 */
public class SemestralniPraceB {

    static void uloz() {

    }

    static void NactiBTS(String cesta) {

    }

    static void zobraz(Terapeut terapeut) {
        if (terapeut != null) {
            Iterator iter = terapeut.getSprava().iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }

    static Terapeut[] vloz() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Zadej 1 pro: Zadaní z klávesnice \n");
        System.out.println("Zadej 2 pro: Zadaní z generatoru \n");
        Terapeut[] terapeuti = new Terapeut[]{};
        switch (br.readLine()) {
            case "1":
                terapeuti = new Terapeut[]{};
                while (true) {
                    return null;
                }

            case "2":
                Random rand = new Random();
                String[] name = {"Petr", "Daniel", "Josef", "Milan"};
                terapeuti = new Terapeut[10];
                for (int i = 0; i < 10; i++) {
                    SpravaTerminu sprava = new SpravaTerminu();
                    sprava.generuj(10);
                    Terapeut terapeut = new Terapeut(i, name[rand.nextInt((3 - 0) + 1) + 0], sprava);
                    terapeuti[i] = terapeut;
                }
                for (Terapeut terapeut : terapeuti) {
                    System.out.println(terapeut);
                }
                return terapeuti;
            default:
                return null;
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {

        SpravaTerapeutu spravaTer = new SpravaTerapeutu();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Zadej 1 pro: Najdi terapeuta a zobraz");
                System.out.println("Zadej 2 pro: Vloz noveho terapeuta");
                System.out.println("Zadej 3 pro: Nacti BVS");
                System.out.println("Zadej 4 pro: Uloz BVS");
                System.out.println("Zadej 5 pro: Zobraz strom");
                System.out.println("Zadej jakýkoliv znak pro: Konec programu");
                System.out.println("--------------------------------------------------------");
                boolean exit = false;

                switch (br.readLine()) {

                    case "1":
                        System.out.println("Vloz jmeno terapeuta.");
                        Terapeut terapeut = spravaTer.najdi(br.readLine());
                        if (terapeut != null) {
                            System.out.println(terapeut + "\n Pokud chcete zobrazit jeho terminy, stiknete 1");
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                            if (br1.readLine().equals("1")) {
                                zobraz(terapeut);
                            }
                        }
                        break;
                    case "2":
                        for (Terapeut t : vloz()) {
                            spravaTer.vlozTerapeuta(t);
                        }

                        break;
                    case "3":

                        break;
                    case "4":

                        break;

                    case "5":

                        System.out.println("Zadej 1 pro: zobraz do hloubky");
                        System.out.println("Zadej 2 pro: zobraz do sirky");
                        System.out.println("Zadej jakýkoliv znak pro: Konec");
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                        String s = br.readLine();
                        if (s.equals("1")) {
                            System.out.println("--------------------------------------------------------");
                            Iterator iter = spravaTer.VytvorIterator(eTypProhl.HLOUBKA);
                            while (iter.hasNext()) {
                                System.out.println(iter.next());
                            }

                        } else if (s.equals("2")) {
                            System.out.println("--------------------------------------------------------");
                            Iterator iter = spravaTer.VytvorIterator(eTypProhl.SIRKA);
                            while (iter.hasNext()) {

                                System.out.println(iter.next());
                            }

                        } else {
                            break;
                        }
                        break;
                    default:
                        exit = true;
                }
                if (exit) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//        AbstrTable<String, Integer> list = new AbstrTable<>();
//        for (int i = 1; i < 6; i++) {
//            System.out.println("vloz");
//            String key = br.readLine();
//            list.vloz(key, i);
//        }
//
//        System.out.println(list.najdi("d"));
//        Iterator<Integer> a = list.vytvorIterator(eTypProhl.SIRKA);
//
//        while (a.hasNext()) {
//
//            System.out.println(a.next());
//        }
//        System.out.println("rip d");
//        System.out.println(list.odeber("d"));
//        a = list.vytvorIterator(eTypProhl.SIRKA);
//
//        while (a.hasNext()) {
//
//            System.out.println(a.next());
//        }
    }
}
