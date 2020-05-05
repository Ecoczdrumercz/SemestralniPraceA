/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracec;


import java.time.LocalDateTime;
import java.util.Iterator;
import semestralnipracec.AbstrPriorQueue;
import semestralnipracec.Mereni;

public class Main {
    public static void main(String[] args) {

        Mereni mereni1 = new Mereni(1, 1, LocalDateTime.MIN, 10);
        Mereni mereni2 = new Mereni(2, 2, LocalDateTime.MIN, 20);
        Mereni mereni3 = new Mereni(3, 3, LocalDateTime.MIN, 7);
        Mereni mereni4 = new Mereni(4, 4, LocalDateTime.MIN, 40);
        Mereni mereni5 = new Mereni(5, 5, LocalDateTime.MIN, 15);
        Mereni mereni6 = new Mereni(6, 6, LocalDateTime.MIN, 8);

        AbstrPriorQueue<Double,Mereni> primarniFronta  = new AbstrPriorQueue();

        primarniFronta.vloz(mereni1.getM3(),mereni1);
        primarniFronta.vloz(mereni2.getM3(),mereni2);
        primarniFronta.vloz(mereni3.getM3(),mereni3);
        primarniFronta.vloz(mereni4.getM3(),mereni4);
        primarniFronta.vloz(mereni5.getM3(),mereni5);
        primarniFronta.vloz(mereni6.getM3(),mereni6);

        Iterator it = primarniFronta.iterator();
        System.out.println("\n Výpis fronty\n");
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
        
        
        System.out.println("zpristupni max prvek: "+primarniFronta.zpristupni());          // zavola se metoda zpristupni, která vrátí Mereni
        System.out.println("\nOdeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        System.out.println("Odeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        System.out.println("Odeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        System.out.println("\n Přebudovaní fronty\n");
        System.out.println("zpristupni max prvek:"+primarniFronta.zpristupni());
        System.out.println("Odeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        System.out.println("zpristupni max prvek:"+primarniFronta.zpristupni());
        System.out.println("Odeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        System.out.println("Odeber a vrat prvni prvek z prioritni fronty: "+primarniFronta.odeberMax());
        
        primarniFronta.odeberMax();
    }
}