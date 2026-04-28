package edu.fsadriann.app.hashtable;

public class Hash {

    private int[] table;
    private int prime;

    // @SuppressWarnings("unchecked")
    public Hash(int size) {
        this.table = new int[size];
        this.prime = getPrime();
    }

    private int getPrime() {
        int p = 0;
        for (int i = table.length; i > 1; i--) {
            boolean isPrimo = true;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    isPrimo = false;
                    break;
                }
            }
            if (isPrimo) {
                p = i;
                break;
            }
        }
        return p;
    }

    private int hash(int key) {
        return key % prime;
    }

    public void insert(int key) {
        int index = hash(key);
        System.out.println("Inserting " + key + " at index " + index);
        if (table[index] != 0) {
            System.out.println("Collision detected for key " + key + " at index " + index);
        }
        table[index] = key;
    }

    public int get(int key) {
        int index = hash(key);
        System.out.println("Getting " + key + " at index " + index);
        return table[index];
    }

    public void search() {
    }
}
