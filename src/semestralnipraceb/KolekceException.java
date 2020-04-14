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
public class KolekceException extends RuntimeException {

    public KolekceException() {
        super("Kolekce Exception");
    }

    public KolekceException(String msg) {
        super("Kolekce Exception: " + msg);
    }

}
