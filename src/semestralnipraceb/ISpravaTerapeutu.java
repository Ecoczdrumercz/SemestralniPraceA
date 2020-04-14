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
public interface ISpravaTerapeutu {

    void vlozTerapeuta(Terapeut terapeut);

    Terapeut najdi(String klic);

    Terapeut odeber(String klic);

    void zrus();

    Iterator<Terapeut> VytvorIterator(eTypProhl typ);
}
