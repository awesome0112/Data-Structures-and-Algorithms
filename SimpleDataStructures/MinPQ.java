package SimpleDataStructures;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    public int actualSize;

    public MinPQ(int capacity) {
        keys = (Key[]) new Comparable[capacity + 1]; // Array start at index 1
        actualSize = 0;
    }

    public boolean isEmpty() {
        return actualSize == 0;
    }

    private boolean isLarger(int i, int j) {
        return keys[i].compareTo(keys[j]) > 0;
    }

    private void swap(int i, int j) {
        Key tmp = keys[i];
        keys[i] = keys[j];
        keys[j] = tmp;
    }

    private void swim(int k) {
        while(k/2 > 1) {
            if(isLarger(k/2, k)) {
                swap(k/2, k);
                k = k/2;
            } else break;
        }
    }

    private void sink(int k) {
        while(2 * k <= actualSize) {
            int tmp = 2 * k;
            if(tmp < actualSize) {
                if(isLarger(tmp, tmp + 1)) tmp++;
            }
            if(!isLarger(k, tmp)) break;
            swap(k, tmp);
            k = tmp;
        }
    }

    public void insert(Key key) {
        if(actualSize == keys.length) return;
        keys[++actualSize] = key;
        swim(actualSize);
    }

    public Key delMin() {
        if(actualSize == 0) return keys[0];
        Key min = keys[1];
        swap(1, actualSize--);
        sink(1);
        keys[actualSize + 1] = null;
        return min;
    }
}
