package SimpleDataStructures;

public class UnionFind {
    private int[] id;
    private int[] size;

    public UnionFind(int N) {
        id = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int root(int i) {
        // Gắn i = id[i] cho đến khi tìm được trường hợp i == id[i] là tìm được root
        // trong quá trình tìm ta gắn id của i bằng id cha của id của i
        // tức là giảm chiều cao của cây => giảm thời gian truy xuất.
        while(i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean isConnected(int i1, int i2) {
        return root(i1) == root(i2);
    }

    public void union(int i1, int i2) {
        // Lấy 2 gốc của i1 và i2
        // Lấy gốc có kích thước nhỏ hơn gắn vào gốc có kích thước to hơn.
        int r1 = root(i1);
        int r2 = root(i2);
        if(r1 == r2) return;
        if(size[r1] < size[r2]) {
            id[r1] = r2;
            size[r2] += size[r1];
        } else {
            id[r2] = r1;
            size[r1] += size[r2];
        }
    }
}
