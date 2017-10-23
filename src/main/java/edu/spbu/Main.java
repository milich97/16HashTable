package edu.spbu;

import java.util.HashMap;

/**
 * Created by Миша on 04.10.2017.
 */
public class Main {


    public static void main(String args[]) throws Exception {
        HashTable hashTable = new HashTable();
        String s = "";
//        for (int i = 0; i < 16; i++) {
//            hashTable.put(s, s.hashCode());
//            s += "hello";
//        }
        hashTable.put("Sergey", 3);
//        hashTable.printf();
        hashTable.put("Anton", 1);
//        hashTable.printf();
        hashTable.put("Mikhail", 2);
//        hashTable.printf();
        hashTable.put("Arsentiy", 3);
//        hashTable.printf();
//        hashTable.printf();
        hashTable.remove("Mikhail");
        hashTable.printf();

    }


}


