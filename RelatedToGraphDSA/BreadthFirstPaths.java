package RelatedToGraphDSA;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] disTo;
    private int from;

    private void breadFirstSearch(Graph G, int from) {
        // Tạo một Queue chứa các đỉnh chưa được đánh dấu.
        // Cho đỉnh from vào và check các đỉnh kề của nó
        // Nếu có đỉnh nào chưa được đánh dấu thì đánh dấu thêm vào Queue và tiếp tục kiểm tra cho đến khi đã được check hết.
        marked[from] = true;
        edgeTo[from] = from;
        disTo[from] = 0;
        Queue<Integer> check = new LinkedList<>();
        check.add(from);
        while(!check.isEmpty()) {
            int v = check.remove();
            for(int i : G.adj(v)) {
                if(!marked[i]) {
                    check.add(i);
                    marked[i] = true;
                    edgeTo[i] = v;
                    disTo[i] = disTo[v] + 1;
                }
            }
        }
    }

    public BreadthFirstPaths(Graph G, int from) {
        this.from = from;
        marked = new boolean[G.numberOfVertices()];
        edgeTo = new int[G.numberOfVertices()];
        disTo = new int[G.numberOfVertices()];
        breadFirstSearch(G, from);
    }
}
