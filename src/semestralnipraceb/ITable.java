/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import java.util.Iterator;

/**
 *
 * @author DanielP
 */
public interface ITable<K extends Comparable<K>, V> extends Iterable<V> {

    void zrus();

    boolean jePrazdny();

    int mohutnost();

    V najdi(K key);

    void vloz(K key, V value) throws KolekceException;

    V odeber(K key) throws IllegalAccessException;

    Iterator<V> vytvorIterator(eTypProhl typ);
}
