package RelatedToGraphDSA;

import java.util.ArrayList;
import java.util.List;

public class Digraph {
    private final int V;
    private final List<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new List[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v1, int v2) {
        adj[v1].add(v2);
    }

    public int numberOfVertices() {
        return V;
    }

    public List<Integer> adj(int v) {
        return adj[v];
    }
}
