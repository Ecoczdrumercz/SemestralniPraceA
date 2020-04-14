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

    AbstrTable<String, Terapeut> list = new AbstrTable<>();

    public SpravaTerapeutu() {
    }

    @Override
    public void vlozTerapeuta(Terapeut terapeut) {
        list.vloz(terapeut.getName(), terapeut);
    }

    @Override
    public Terapeut najdi(String klic) {
        return list.najdi(klic);
    }

    @Override
    public Terapeut odeber(String klic) {
        try {
            return list.odeber(klic);
        } catch (Exception e) {
            throw new Error(e.toString());
        }
        
    }

    @Override
    public void zrus() {
        list.jePrazdny();
    }

    @Override
    public Iterator<Terapeut> VytvorIterator(eTypProhl typ) {
        return list.vytvorIterator(typ);
    }

}
