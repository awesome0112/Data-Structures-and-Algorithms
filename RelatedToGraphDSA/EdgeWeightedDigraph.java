package RelatedToGraphDSA;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
    private final int V;
    private final List<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (List<DirectedEdge>[]) new List[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedEdge directedEdge) {
        int vFrom = directedEdge.from();
        adj[vFrom].add(directedEdge);
    }

    public int vertices() {
        return V;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
}
