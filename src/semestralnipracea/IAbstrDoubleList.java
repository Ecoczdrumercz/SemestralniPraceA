/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.util.Iterator;

/**
 *
 * @author asdf
 */
public interface IAbstrDoubleList<T> extends Iterable<T>{ 
    void zrus();
    boolean jePrazdny();
    void vlozPrvni(T data);
    void vlozPosledni(T data);
    void vlozNaslednika(T data);
    void vlozPredchudce(T data);
    T zpristupniAktualni();
    T zpristupniPrvni();
    T zpristupniPosledni();
    T zpristupniNaslednika();
    T zpristupniPredchudce();
    
    T odeberAktualni();
    T odeberPrvni();
    T odeberPosledni();
    T odeberNaslednika();
    T odeberPredchudce();

    @Override
    Iterator<T> iterator();
    
    
    
    
}
