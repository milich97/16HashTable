package edu.spbu;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Миша on 04.10.2017.
 */

public class HashTable {
    int size;
    int numberOfElements = 0;
    int p;
    Element[] H;


    public HashTable() {
        p = Integer.parseInt(BigInteger.probablePrime(12, new Random()).toString());
        size = 4;
        H = new Element[size];
    }

    public void put(String key, Integer value) {
        numberOfElements++;
        if (numberOfElements > size) {
            rehash();
            put(key, value);
        } else {
            int index = (Math.abs(key.hashCode()) % p) % size;
            if (H[index] == null || H[index].deleted) {
                H[index] = new Element(key, value);
            } else {
                while (H[index] != null) {
                    index = (index + p) % size;
                }
                H[index] = new Element(key, value);
            }
        }
    }

    private void rehash() {

        Element[] subsidiary = new Element[size];
        for (int i = 0; i < subsidiary.length; i++) {
            subsidiary[i] = H[i];
        }
        size *= 2;
        H = new Element[size];
        numberOfElements = 0;
        for (int i = 0; i < subsidiary.length; i++) {
            put(subsidiary[i].key, subsidiary[i].value);
        }
    }

    public void printf() {
        for (int i = 0; i < H.length; i++) {
            Element elem = H[i];
            if (elem != null && !elem.deleted) {
                System.out.println("key=" + H[i].key + ", value=" + H[i].value);
            } else {
                System.out.println("null");
            }
        }
        System.out.println();
    }

    public int get(String key) throws Exception {
        int index = (Math.abs(key.hashCode()) % p) % size;
        for (int i = 0; i < size; i++) {
            if (H[index] != null && !H[index].deleted) {
                if (H[index].key == key) {
                    return H[index].value;
                } else {
                    index = (index + p) % size;
                }
            }
        }
        throw new Exception("There isn't that key!");
    }

    public void remove(String key) {
        int index = (Math.abs(key.hashCode()) % p) % size;
        for (int i = 0; i < size; i++) {
            if (H[index] != null && !H[index].deleted) {
                if (H[index].key == key) {
                    H[index].deleted = true;
                } else {
                    index = (index + p) % size;
                }
            }
        }
    }

    public class Element {
        String key;
        Integer value;
        boolean deleted;

        public Element(String key, Integer value) {
            this.key = key;
            this.value = value;
            deleted = false;
        }
    }
}
