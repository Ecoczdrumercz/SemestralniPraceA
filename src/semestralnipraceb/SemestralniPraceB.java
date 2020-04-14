/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 *
 * @author DanielP
 */
public class SemestralniPraceB {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    AbstrTable<String, Integer> list = new AbstrTable<>();
        for (int i = 1; i<6; i++) {
            System.out.println("vloz");
            String key = br.readLine();
            list.vloz(key, i);
        }
        
       // System.out.println(list.najdi("d"));
        Iterator<Integer> a = list.vytvorIterator(eTypProhl.SIRKA);
        
        while(a.hasNext()){
            
            System.out.println(a.next());
        }
        System.out.println("rip d");
        System.out.println(list.odeber("d"));
    a=list.vytvorIterator(eTypProhl.SIRKA);
        
    while(a.hasNext()){
            
            System.out.println(a.next());
        }
    }
}
