package SimpleDataStructures;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int actualSize;

    public MaxPQ(int capacity) {
        keys = (Key[]) new Comparable[capacity + 1];
        actualSize = 0;
    }

    public boolean isEmpty() {
        return actualSize == 0;
    }

    public boolean isLess(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }

    public void swap(int i, int j) {
        Key tmp = keys[i];
        keys[i] = keys[j];
        keys[j] = tmp;
    }

    private void swim(int k) {
        while(k/2 > 1) {
            if(isLess(k/2, k)) {
                swap(k/2, k);
                k = k/2;
            } else break;
        }
    }

    private void sink(int k) {
        int tmp;
        while(2*k >= actualSize) {
            tmp = 2 * k;
            if(tmp < actualSize) {
                if(isLess(tmp, tmp + 1)) tmp++;
            }
            if(!isLess(k, tmp)) break;
            swap(tmp, k);
            k = tmp;
        }
    }

    public void insert(Key key) {
        if(actualSize == keys.length) return;
        keys[++actualSize] = key;
        swim(actualSize);
    }

    private void anotherSink(int k) {
        int keyToSwap;
        while (k * 2 >= actualSize) {
            keyToSwap = k * 2;
            if(keyToSwap < actualSize) { // Nếu keyToSwap bằng actualSize thì sẽ không có phần từ đứng bên cạnh (keyToSwap + 1) để kiểm tra.
                if (isLess(keyToSwap, keyToSwap + 1)) keyToSwap++;
            }
            // Nếu nút k đã lớn hơn 2 nút con thì sẽ dừng chương trình.
            if(!isLess(k, keyToSwap)) return;
            swap(keyToSwap, k);
            k *= 2;
        }
    }

    public Key delMax() {
        if(actualSize == 0) return keys[0];
        Key max = keys[1];
        swap(1, actualSize--);
        sink(1);
        keys[actualSize + 1] = null;
        return max;
    }
}
