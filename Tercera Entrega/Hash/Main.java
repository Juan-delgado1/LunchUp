import java.util.LinkedList;
import java.util.Random;

class HashTable<K, V> {
    @SuppressWarnings("hiding")
    private class HashNode<K, V> {
        K key;
        V value;
        @SuppressWarnings("unused")
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M; 
    private int size; 
    private int collisions; 
    private static final double LOAD_FACTOR_THRESHOLD = 0.5; 

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.M = 100; 
        this.chainArray = new LinkedList[M];
        this.size = 0;
        this.collisions = 0;

        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    
    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }

    
    public void put(K key, V value) {
        int index = hash(key);

        
        if (!chainArray[index].isEmpty()) {
            collisions++;
        }

        
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) {
                node.value = value; 
                return;
            }
        }

        
        chainArray[index].add(new HashNode<>(key, value));
        size++;

        
        if (getLoadFactor() > LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
    }

    
    public V get(K key) {
        int index = hash(key);
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null; 
    }

    
    public V remove(K key) {
        int index = hash(key);
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) {
                V value = node.value;
                chainArray[index].remove(node);
                size--;
                return value;
            }
        }
        return null; 
    }

    
    @SuppressWarnings("unchecked")
    private void rehash() {
        System.out.println("Rehashing... Nuevo tamaño: " + M * 2);
        LinkedList<HashNode<K, V>>[] oldChainArray = chainArray;
        M = M * 2; 
        chainArray = new LinkedList[M];
        size = 0;
        collisions = 0; 

        
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }

        
        for (LinkedList<HashNode<K, V>> bucket : oldChainArray) {
            for (HashNode<K, V> node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    
    public int size() {
        return size;
    }

    
    double getLoadFactor() {
        return (double) size / M;
    }

    
    public int getCollisions() {
        return collisions;
    }

    
    public void printHashTable() {
        for (int i = 0; i < M; i++) {
            System.out.print("Bucket " + i + ": ");
            for (HashNode<K, V> node : chainArray[i]) {
                System.out.print("[" + node.key + " : " + node.value + "] -> ");
            }
            System.out.println("null");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        Random random = new Random();

        
        long totalInsertStartTime = System.nanoTime();
        
        for (int i = 1; i <= 100000000; i++) {
            String randomKey = "key" + random.nextInt(10000000); 
            hashTable.put(randomKey, i);
        }
        long totalInsertEndTime = System.nanoTime();
        long totalInsertDuration = totalInsertEndTime - totalInsertStartTime;

        System.out.println("Número de colisiones: " + hashTable.getCollisions());
       // System.out.println("Factor de carga actual: " + hashTable.getLoadFactor());
        //System.out.println("Tamaño actual de la tabla: " + hashTable.size());
        System.out.println("Tiempo total de inserción de 10,000 elementos: " + totalInsertDuration / 1_000_000 + " ms");

        
        String keyToRemove = "key5000"; 
        long removeStartTime = System.nanoTime();
        hashTable.remove(keyToRemove);
        long removeEndTime = System.nanoTime();
        long removeDuration = removeEndTime - removeStartTime;

        System.out.println("Tiempo de eliminación del elemento '" + keyToRemove + "': " + removeDuration + " ms");
        //System.out.println("Tamaño actual de la tabla después de la eliminación: " + hashTable.size());
    }
}

