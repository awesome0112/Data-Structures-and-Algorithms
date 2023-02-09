package RelatedToGraphDSA;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int V;  // Vertices
    private List<Integer>[] adj; // Adjacent

    public Graph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new ArrayList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int v1, int v2) {
        adj[v1].add(v2);
        adj[v2].add(v1);
    }

    public int numberOfVertices() {
        return V;
    }

    public List<Integer> adj(int v) {
        return adj[v];
    }

    public List<Integer>[] getAdj() {
        return adj;
    }
}
