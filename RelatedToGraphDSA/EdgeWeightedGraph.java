package RelatedToGraphDSA;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    private final int V;
    private final List<Edge>[] adj;
    private List<Edge> edges;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (List<Edge>[]) new List[V];
        edges = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(Edge edge) {
        int v1 = edge.either();
        int v2 = edge.other(v1);
        edges.add(edge);
        adj[v1].add(edge);
        adj[v2].add(edge);
    }

    public void addEdge(int v1, int v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
        adj[v1].add(edge);
        adj[v2].add(edge);
    }

    public List<Edge> adj(int v) {
        return adj[v];
    }

    public int vertices() {
        return V;
    }

    public Iterable<Edge> edges() {
        return this.edges;
    }

    public int numOfEdges() {
        return edges.size();
    }

    @Override
    public String toString() {
        String res = "[";
        for(int i = 0; i < edges.size(); i++) {
            res += edges.get(i);
            if(i != edges.size() - 1) res += " - ";
        }
        res += "]";
        return res;
    }
}
