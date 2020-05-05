/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracec;

import java.util.Iterator;
import semestralnipracea.AbstrDoubleList;

/**
 *
 * @author DanielP
 */
public class AbstrPriorQueue<K extends Comparable<K>, V> implements IAbstrPriorQueue<K, V> {

    private AbstrDoubleList<Prvek> Useznam = new AbstrDoubleList<>();
    private AbstrDoubleList<Prvek> Nseznam = new AbstrDoubleList<>();
    private final int MAX_POCET = 3;
    private K mez;

    private class Prvek {

        V data;  // Objekt mereni
        K priorita; // m3  data.getM3

        public Prvek(V data, K priorita) {
            this.data = data;
            this.priorita = priorita;
        }

        public V getData() {
            return data;
        }

        public K getPriorita() {
            return priorita;
        }
        
        

        @Override
        public String toString() {
            return "P{" + "data=" + data + '}';
        }

    }

    @Override
    public boolean jePrazdny() {
        return Useznam.jePrazdny();
    }

    @Override
    public Boolean vloz(K key, V value) {
        if (key != null || value != null) {
            Prvek prvek = new Prvek(value, key);
            if (jePrazdny()) {                                                  // operace Vloz a
                Useznam.vlozPrvni(prvek);
                mez = key;
                return true;
            } else {
                if (Useznam.getPocetPrvku() == MAX_POCET) {

                    if (key.compareTo(mez) < 1) {
                        if (Nseznam.jePrazdny()) {
                            Nseznam.vlozPrvni(prvek);
                        } else {
                            Nseznam.vlozNaslednika(prvek);
                        }
                    } else {
                        Iterator it = Useznam.iterator();

                        while (it.hasNext()) {

                            Prvek p = (Prvek) it.next();

                            if (prvek.priorita.compareTo(p.priorita) >= 0) {
                                Useznam.vlozPredchudce(prvek);
                                
                                
                                if (Nseznam.jePrazdny()) {
                                    Nseznam.vlozPrvni(Useznam.odeberPosledni());
                                } else {
                                    Nseznam.vlozNaslednika(Useznam.odeberPosledni());
                                }
                                mez = Useznam.zpristupniPosledni().priorita;
                                return true;
                            }
                        }
                    }

                } else {
                    if (prvek.priorita.compareTo(Useznam.zpristupniPrvni().priorita) == 1) {
                        Useznam.vlozPrvni(prvek);
                        return true;
                    }
                    Iterator itt = Useznam.iterator();

                    while (itt.hasNext()) {

                        Prvek p = (Prvek) itt.next();

                        if (prvek.priorita.compareTo(p.priorita) >= 0) {

                            Useznam.vlozPredchudce(prvek);
                            return true;
                        }
                        if (!itt.hasNext()) {
                            Useznam.vlozPosledni(prvek);
                            mez = key;
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    @Override
    public void zrus() {
        Nseznam = new AbstrDoubleList<>();
        Useznam = new AbstrDoubleList<>();
    }

    public void naplFrontu() {
        
        int pocet = Nseznam.getPocetPrvku();
            for (int i = 0; i < pocet; i++) {
                Prvek p = Nseznam.odeberPrvni();
                vloz(p.priorita, p.data);
            }
        
    }

    @Override
    public V odeberMax() {
        if (!Useznam.jePrazdny()) {
            if (Useznam.getPocetPrvku() > 1) {
                return Useznam.odeberPrvni().data;
            } else {
                V value = Useznam.odeberPrvni().data;
                if (!Nseznam.jePrazdny()) {
                        naplFrontu();
                }
                return value;
            }
        } else {
            return null;
        }
    }

    @Override
    public V zpristupni() {
        if (!Useznam.jePrazdny()) {
            return Useznam.zpristupniPrvni().data;
        } else {
            return null;
        }
    }

    @Override
    public Iterator vytvorIterator() {                                          // vytvoří iterator pro neserazenou frontu
        return Nseznam.iterator();
    }

    @Override
    public void vybuduj(V[] values, K[] keys) {
        V[] datas = values;
        K[] priority = keys;

        for (int i = 0; i < datas.length; i++) {
            vloz(priority[i], datas[i]);
        }
    }

    @Override
    public Iterator iterator() {                                                // vytvoří iterator pro serazenou frontu
        return Useznam.iterator();
    }

}
