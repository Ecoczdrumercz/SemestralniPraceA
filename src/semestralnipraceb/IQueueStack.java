/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipraceb;

/**
 *
 * @author DanielP
 */
public interface IQueueStack<T> extends Iterable<T> {

    boolean jePrazdny();

    T odeber();

    void vloz(T data);

    void zrus();
}
