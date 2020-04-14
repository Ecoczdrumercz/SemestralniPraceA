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
public class SpravaTerapeutu implements ISpravaTerapeutu {

    AbstrTable<String, Terapeut> strom = new AbstrTable<>();

    public SpravaTerapeutu() {
    }

    @Override
    public void vlozTerapeuta(Terapeut terapeut) {
        strom.vloz(terapeut.getName(), terapeut);
    }

    @Override
    public Terapeut najdi(String klic) {
        return strom.najdi(klic);
    }

    @Override
    public Terapeut odeber(String klic) {
        try {
            return strom.odeber(klic);
        } catch (Exception e) {
            throw new Error(e.toString());
        }
        
    }

    @Override
    public void zrus() {
        strom.jePrazdny();
    }

    @Override
    public Iterator<Terapeut> VytvorIterator(eTypProhl typ) {
        return strom.vytvorIterator(typ);
    }

}
