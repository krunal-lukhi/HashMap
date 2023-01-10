package org.example;

import java.util.*;
import java.lang.* ;
import java.io.*;

public class Main {
    public static class MyHashMap<K,V>{
        private class Node{ //Node class for creating linked list
            K key;
            V value;
            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
        static final int MAX_SIZE = 10000009; //maximum size of hash table
        private int size;

        private int HashFunction(K key){

            int hashKey = key.hashCode(); //hashCode() function converts String to integer hash value
            return Math.abs(hashKey)%MAX_SIZE; // it can be + or - and module operation keep it in range

        }


        private LinkedList<Node> [] HashTable; // hashing by chaining

        public MyHashMap(){
            initializeBins(MAX_SIZE);
            size = 0;
        }
        private void initializeBins(int size){
            HashTable = new LinkedList[size];

            for(int itr = 0 ; itr < size ; itr++)
            {
                HashTable[itr] = new LinkedList<>();
            }
        }

        private int getIndexInList(K key,int hashKey)
        {
            int indexInList = 0;

            for(Node node : HashTable[hashKey]) // iterating linked list related to given hashKey in hashtable
            {
                if(node.key.equals(key))
                {
                    return indexInList; // index of given key
                }
                indexInList++;
            }
            return -1;
        }
        public V get(K key){
            int hashKey = HashFunction(key);
            int indexInList = getIndexInList(key,hashKey);

            if(indexInList != -1)
            {
                Node node = HashTable[hashKey].get(indexInList);
                return node.value;
            }
            else
            {
                return null;
            }

        }

        public void put(K key, V value){
            int hashKey = HashFunction(key);
            int indexInList = getIndexInList(key,hashKey);

            if(indexInList != -1)
            {
                Node node = HashTable[hashKey].get(indexInList);
                node.value = value;

            }
            else
            {
                Node node = new Node(key,value);
                HashTable[hashKey].add(node);
                this.size++;

            }
        }
        public void removeKey(K key)
        {
            int hashKey = HashFunction(key);
            int indexInList = getIndexInList(key,hashKey);

            if(indexInList != -1)
            {
                HashTable[hashKey].remove(indexInList);
                this.size--;
            }
            else
            {
                System.out.println("Key does not exists");
            }
        }

        public int size(){
            return size;
        }

        public boolean containsKey(K key){
            int hashKey = HashFunction(key);
            int indexInList = getIndexInList(key,hashKey);

            if(indexInList != -1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public static void main(String[] args) {
       MyHashMap<String, Integer> hp  = new MyHashMap<String,Integer>();
        String name = new String("Krunal");
        hp.get("Parin");
//        hp.put(name,20);
//        System.out.println("Size : " + hp.size());
//        System.out.println("Value : " + hp.get(name));

    }
}