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
public class AbstrTable<K extends Comparable<K>, V> implements ITable<K, V> {

    private int velikost;
    private Uzel root;

    public AbstrTable() {
        this.velikost = 0;
        this.root = null;
    }

    private class Uzel {

        private K key;
        private V data;
        private Uzel left;
        private Uzel right;

        public Uzel(K key, V data, Uzel left, Uzel right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Uzel(K key, V data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Uzel{" + "key=" + key + ", data=" + data + '}';
        }
    }

    @Override
    public void zrus() {
        velikost = 0;
        root = null;
    }

    @Override
    public boolean jePrazdny() {
        return velikost == 0;
    }

    @Override
    public int mohutnost() {
        return velikost;
    }

    @Override
    public V najdi(K key) {
        if (root == null) {
            System.out.println("V kolekci neni zadny prvek");
            return null;
        } else {
            Uzel aktualniUzel = root;
            while (aktualniUzel != null) {
                if (key.equals(aktualniUzel.key)) {
                    return aktualniUzel.data;
                } else if (key.compareTo(aktualniUzel.key) < 0) {
                    aktualniUzel = aktualniUzel.left;

                } else {
                    aktualniUzel = aktualniUzel.right;
                }
            }
            System.out.println("Tato hodnota neni v kolekci");
            return null;
        }
    }

    @Override
    public void vloz(K key, V value) throws KolekceException {
        try {
            Uzel novyUzel = new Uzel(key, value);
            if (root == null) {
                root = novyUzel;
                velikost++;
                return;
            }
            Uzel aktualniUzel = root;
            Uzel parent = null;
            while (true) {
                parent = aktualniUzel;
                if (key.compareTo(aktualniUzel.key) < 0) {
                    aktualniUzel = aktualniUzel.left;
                    if (aktualniUzel == null) {
                        parent.left = novyUzel;
                        velikost++;
                        return;
                    }
                } else {
                    aktualniUzel = aktualniUzel.right;
                    if (aktualniUzel == null) {
                        parent.right = novyUzel;
                        velikost++;
                        return;
                    }
                }
            }
        } catch (Exception e) {
            throw new KolekceException(e.getMessage());
        }

    }

    @Override
    public V odeber(K key) throws IllegalAccessException {

        try {
            Uzel parent = root;
            Uzel aktualniUzel = root;
            boolean maLevehoPotomka = false;
            while (!aktualniUzel.key.equals(key)) {
                parent = aktualniUzel;
                if (key.compareTo(aktualniUzel.key) < 0) {
                    maLevehoPotomka = true;
                    aktualniUzel = aktualniUzel.left;
                } else {
                    maLevehoPotomka = false;
                    aktualniUzel = aktualniUzel.right;
                }
                if (aktualniUzel == null) {
                    return null;
                }
            }

            if (aktualniUzel.left == null && aktualniUzel.right == null) {
                if (aktualniUzel == root) {
                    root = null;
                }
                if (maLevehoPotomka == true) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (aktualniUzel.right == null) {
                if (aktualniUzel == root) {
                    root = aktualniUzel.left;
                } else if (maLevehoPotomka) {
                    parent.left = aktualniUzel.left;
                } else {
                    parent.right = aktualniUzel.left;
                }
            } else if (aktualniUzel.left == null) {
                if (aktualniUzel == root) {
                    root = aktualniUzel.right;
                } else if (maLevehoPotomka) {
                    parent.left = aktualniUzel.right;
                } else {
                    parent.right = aktualniUzel.right;
                }
            } else if (aktualniUzel.left != null && aktualniUzel.right != null) {

                Uzel naslednik = getNaslednik(aktualniUzel);
                if (aktualniUzel == root) {
                    root = naslednik;
                } else if (maLevehoPotomka) {
                    parent.left = naslednik;
                } else {
                    parent.right = naslednik;
                }
                naslednik.left = aktualniUzel.left;
            }
            return aktualniUzel.data;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    public Uzel getNaslednik(Uzel uzel) {
        Uzel naslednik = null;
        Uzel naslednikuvRodic = null;
        Uzel aktualni = uzel.right;
        while (aktualni != null) {
            naslednikuvRodic = naslednik;
            naslednik = aktualni;
            aktualni = aktualni.left;
        }

        if (naslednik != uzel.right) {
            naslednikuvRodic.left = naslednik.right;
            naslednik.right = uzel.right;
        }
        return naslednik;
    }

    @Override
    public Iterator<V> vytvorIterator(eTypProhl typ) {

        if(root == null){
            return null;
        }
                
        if (typ == eTypProhl.HLOUBKA) {

            return new Iterator<V>() {

                AbstrLIFO<Uzel> zasobnik = new AbstrLIFO<>();

                private void vlozVlevo(Uzel x) {
                    while (x != null) {
                        zasobnik.vloz(x);
                        x = x.left;
                    }
                }

                {
                    vlozVlevo(root);
                }

                @Override
                public boolean hasNext() {
                    return !zasobnik.jePrazdny();
                }

                @Override
                public V next() {

                    Uzel x = zasobnik.odeber();
                    vlozVlevo(x.right);
                    System.out.println(x);
                    return x.data;

                }
            };

        } else if (typ == eTypProhl.SIRKA) {
            return iterator();
        }
        return null;
    }

    @Override
    public Iterator<V> iterator() {
        
        return new Iterator<V>() {

                AbstrFIFO<Uzel> fronta = new AbstrFIFO<>();
                {
                fronta.vloz(root);
                }
                
                @Override
                public boolean hasNext() {
                    return !fronta.jePrazdny();
                }

                @Override
                public V next() {
                    Uzel x = fronta.odeber();
                    if(x.left!=null){
                        fronta.vloz(x.left);
                    }
                    if(x.right!=null){
                        fronta.vloz(x.right);
                    }
                    System.out.println(x);
                    return x.data;
                }
            };
    }

}
