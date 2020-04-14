/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import java.io.BufferedReader;
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
        if(terapeut!=null){
            Iterator iter = terapeut.getSprava().iterator();
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
        }
    }
    
    static Terapeut[] vloz() throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Zadej 1 pro: Zadaní z klávesnice \n");
        System.out.println("Zadej 2 pro: Zadaní z generatoru \n");
        if (br.readLine() == "1") {
            Terapeut[] terapeuti = new Terapeut[]{};
            while (true) {
                
                
                Terapeut terapeut = new Terapeut(0, "a", spravaTer);
                return terapeuti;
            }
        } else if (br.readLine() == "2") {
            Random rand = new Random();
            String[] name = {"Petr", "Daniel", "Josef", "Milan"};
            Terapeut[] terapeuti = new Terapeut[10];
            for (int i = 0; i < 10; i++) {
                SpravaTerminu sprava = new SpravaTerminu();
                sprava.generuj(10);
                Terapeut terapeut = new Terapeut(i, name[rand.nextInt((4 - 1) + 1) + 1], sprava);
                terapeuti[i] = terapeut;
            }
            return terapeuti;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {

        SpravaTerapeutu spravaTer = new SpravaTerapeutu();

        SpravaTerminu sprava = new SpravaTerminu();
        SpravaTerminu sprava2 = new SpravaTerminu();
        SpravaTerminu sprava3 = new SpravaTerminu();
        SpravaTerminu[] spravy = new SpravaTerminu[]{sprava, sprava2, sprava3};

        Termin termin = new Termin(0, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("12:00:00"), LocalTime.parse("14:00:00"));
        Termin termin2 = new Termin(1, LocalDate.parse("2020-03-20"), LocalDate.parse("2020-03-21"), LocalTime.parse("14:00:00"), LocalTime.parse("16:00:00"));
        Termin termin3 = new Termin(1, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin4 = new Termin(1, LocalDate.parse("2020-03-15"), LocalDate.parse("2020-03-21"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00")); // neprida se
        Termin termin5 = new Termin(1, LocalDate.parse("2020-03-23"), LocalDate.parse("2020-03-28"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin6 = new Termin(1, LocalDate.parse("2020-03-22"), LocalDate.parse("2020-03-22"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"));
        Termin termin7 = new Termin(1, LocalDate.parse("2020-03-22"), LocalDate.parse("2020-03-22"), LocalTime.parse("08:00:00"), LocalTime.parse("10:00:00"));
        Termin termin8 = new Termin(1, LocalDate.parse("2020-03-14"), LocalDate.parse("2020-03-22"), LocalTime.parse("08:00:00"), LocalTime.parse("10:00:00"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadej 1 pro: Najdi terapeuta a zobraz");
        System.out.println("Zadej 2 pro: Vloz noveho terapeuta");  // vlozi terapeuta pomoci klavesnice nebo random
        System.out.println("Zadej 3 pro: Nacti BVS");
        System.out.println("Zadej 4 pro: Uloz BVS");

        switch (br.readLine()) {
            case "1": 
            System.out.println("Vloz jmeno terapeuta.");
            Terapeut terapeut = spravaTer.najdi(br.readLine());
            if(terapeut!=null){
                System.out.println(terapeut + "\n Pokud chcete zobrazit jeho terminy, stiknete 1");
            if(br.readLine()=="1"){
                zobraz(terapeut);
            }
                
            }
                break;
                case "2":
                
                break;
                case "3":
                
                break;
                case "4":
                
                break;
            default:
                throw new AssertionError();
        }
        

        AbstrTable<String, Integer> list = new AbstrTable<>();
        for (int i = 1; i < 6; i++) {
            System.out.println("vloz");
            String key = br.readLine();
            list.vloz(key, i);
        }

        System.out.println(list.najdi("d"));
        Iterator<Integer> a = list.vytvorIterator(eTypProhl.SIRKA);

        while (a.hasNext()) {

            System.out.println(a.next());
        }
        System.out.println("rip d");
        System.out.println(list.odeber("d"));
        a = list.vytvorIterator(eTypProhl.SIRKA);

        while (a.hasNext()) {

            System.out.println(a.next());
        }
    }
}
