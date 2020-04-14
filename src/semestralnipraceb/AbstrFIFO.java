/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

import java.util.Iterator;
import semestralnipracea.AbstrDoubleList;


public class AbstrFIFO<T> implements IQueueStack<T>{

    AbstrDoubleList<T> list = new AbstrDoubleList<>();

    @Override
    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    @Override
    public T odeber() {
        return list.odeberPosledni();
    }

    @Override
    public void vloz(T data) {
        list.vlozPrvni(data);
    }

    @Override
    public void zrus() {
        list.zrus();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

}