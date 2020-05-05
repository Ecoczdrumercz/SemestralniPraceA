/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracec;

import java.util.Iterator;

/**
 *
 * @author DanielP
 */
public interface IAbstrPriorQueue<K extends Comparable<K>, V> extends Iterable<V> {
    
    boolean jePrazdny();
    Boolean vloz(K key, V value);
    void zrus();
    V odeberMax();
    V zpristupni();
    Iterator vytvorIterator();
    void vybuduj(V[] data, K[] keys);
}
