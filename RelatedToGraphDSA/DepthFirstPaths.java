package RelatedToGraphDSA;

import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int from;

    private void depthFirstSearch(Graph G, int from) {
        // Kiểm tra từ đỉnh from
        // Kiểm tra các đỉnh kề của đỉnh đó (vòng for)
        // nếu có đỉnh nào chưa được đánh dấu thì đánh dấu và đệ quy để kiểm tra lại như trên.
        marked[from] = true;
        for(int i : G.adj(from)) {
            if(!marked[i]) {
                depthFirstSearch(G, i);
                edgeTo[i] = from;
            }
        }
    }

    public DepthFirstPaths(Graph G, int from) {
        this.from = from;
        marked = new boolean[G.numberOfVertices()];
        edgeTo = new int[G.numberOfVertices()];
        depthFirstSearch(G, from);
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int i = v; i != from; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(from);
        return path;
    }
}
