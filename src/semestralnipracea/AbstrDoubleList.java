/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracea;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author asdf
 */
public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private Prvek<T> prvni;
    private Prvek<T> posledni;
    private Prvek<T> aktualni;
    private int pocetPrvku;

    public AbstrDoubleList() {
        this.prvni = null;
        this.posledni = null;
        this.aktualni = null;
        this.pocetPrvku = 0;
    }

    private class Prvek<T> {

        private Prvek predchudce;
        private Prvek naslednik;
        private T zaznam;

        public Prvek(Prvek predchudce, Prvek naslednik, T zaznam) {
            this.predchudce = predchudce;
            this.naslednik = naslednik;
            this.zaznam = zaznam;
        }
    }

    @Override
    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        pocetPrvku = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocetPrvku == 0;
    }

    @Override
    public void vlozPrvni(T data) {
        if (jePrazdny()) {
            Prvek<T> prvek = new Prvek<>(null, null, data);
            prvni = prvek;
            posledni = prvek;
          //  aktualni = prvek;
            pocetPrvku++;
        } else {
            Prvek<T> prvek = new Prvek<>(null, prvni, data);
            prvni.predchudce = prvek;
            prvni = prvek;  // prvni nastavim na prvek
            pocetPrvku++;
        }
    }

    @Override
    public void vlozPosledni(T data) {
        if (jePrazdny()) {
            Prvek<T> prvek = new Prvek<>(null, null, data);
            posledni = prvek;
            prvni = prvek;
          //  aktualni = prvek;
            pocetPrvku++;
        } else {
            Prvek<T> prvek = new Prvek<>(posledni, null, data);
            posledni.naslednik = prvek;
            posledni = prvek;  // prvni nastavim na prvek
            pocetPrvku++;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        if (aktualni == null) {
            throw new NullPointerException(); // 
        } else {
            if (aktualni == posledni) {
                vlozPosledni(data);
            } else {

                Prvek<T> prvek = new Prvek<>(aktualni, aktualni.naslednik, data);
                aktualni.naslednik = prvek;
                aktualni.naslednik.naslednik.predchudce = prvek;
                pocetPrvku++;
            }
        }

    }

    @Override
    public void vlozPredchudce(T data) {
        if (aktualni == null) {
            throw new NullPointerException(); // 
        } else {
            if (aktualni == prvni) {
                vlozPrvni(data);
            } else {

                Prvek<T> prvek = new Prvek<>(aktualni.predchudce, aktualni, data);
                aktualni.predchudce = prvek;
                aktualni.predchudce.predchudce.naslednik = prvek;
                pocetPrvku++;
            }
        }
    }

    @Override
    public T zpristupniAktualni() {
        if (aktualni != null) {
            return aktualni.zaznam;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public T zpristupniPrvni() {
        if (prvni == null) {
            throw new NullPointerException();
        } else {
            aktualni = prvni;
            return aktualni.zaznam;
        }
    }

    @Override
    public T zpristupniPosledni() {
        if (posledni == null) {
            throw new NullPointerException();
        } else {
            aktualni = posledni;
            return aktualni.zaznam;
        }
    }

    @Override
    public T zpristupniNaslednika() {
        if (aktualni == null) {
            throw new NullPointerException();
        } else {
            aktualni = aktualni.naslednik;
            return aktualni.zaznam;
        }
    }

    @Override
    public T zpristupniPredchudce() {
        if (aktualni == null) {
            throw new NullPointerException();
        } else {
            aktualni = aktualni.predchudce;
            return aktualni.zaznam;
        }
    }

    @Override
    public T odeberAktualni() {
        if (aktualni == null) {
            throw new NullPointerException();
        } else {
            if (aktualni == posledni) {
                return odeberPosledni();
            }

            if (aktualni == prvni) {
                return odeberPrvni();
            } else {
                T data = aktualni.zaznam;
                aktualni.predchudce.naslednik = aktualni.naslednik;
                aktualni.naslednik.predchudce = aktualni.predchudce;
                aktualni = null;
                pocetPrvku--;
                return data;
            }
        }
    }

    @Override
    public T odeberPrvni() {
        if (jePrazdny()) {
            throw new NullPointerException();
        } else {
             T data = prvni.zaznam;
        if (prvni == aktualni) {
            if (prvni.naslednik != null) {
                aktualni = prvni.naslednik;
            }
        }
        prvni = prvni.naslednik;
        pocetPrvku--;
        return data;
        }
    }

    @Override
    public T odeberPosledni() {
        if (jePrazdny()) {
            throw new NullPointerException();
        } else {
             T data = posledni.zaznam;

        if (posledni == aktualni) {
            if (posledni.predchudce != null) {
                aktualni = posledni.predchudce;
            }
        }
        posledni = posledni.predchudce;
        pocetPrvku--;
        return data;
    }
        }
    

    @Override
    public T odeberNaslednika() {
        if (aktualni == null || aktualni.naslednik == null) {
            throw new NullPointerException();
        } else {
            if (aktualni.naslednik.naslednik == null) {
                T data = aktualni.zaznam;
                aktualni.naslednik = null;
                posledni = aktualni;
                pocetPrvku--;
                return data;
            } else {
                T data = aktualni.zaznam;
                aktualni.naslednik = aktualni.naslednik.naslednik;
                aktualni.naslednik.naslednik.predchudce = aktualni;
                pocetPrvku--;
                return data;
            }
        }
    }

    @Override
    public T odeberPredchudce() {
        if (aktualni == null || aktualni.predchudce == null) {
            throw new NullPointerException();
        } else {
            if (aktualni.predchudce.predchudce == null) {
                T data = aktualni.zaznam;
                aktualni.predchudce = null; //
                prvni = aktualni; //
                pocetPrvku--;  //
                return data;
            } else {
                T data = aktualni.zaznam;
                aktualni.predchudce = aktualni.predchudce.predchudce;
                aktualni.predchudce.predchudce.naslednik = aktualni;
                pocetPrvku--;
                return data;
            }
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            Prvek<T> i = prvni;
            Prvek<T> predchoziI = null;
            Prvek<T> aktualniI = null;

            @Override
            public boolean hasNext()
            {
                return i != null;
            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    T data = i.zaznam;
                    predchoziI = aktualniI;
                    aktualniI = i;
                    i = i.naslednik;
                    return data;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
