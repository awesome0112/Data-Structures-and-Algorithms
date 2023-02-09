package RelatedToGraphDSA;

import SimpleDataStructures.MinPQ;
import SimpleDataStructures.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> minimumSpanningTree = new LinkedList<>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> sortedEdges = new MinPQ<>(G.numOfEdges());
        for(Edge i : G.edges()) {
            sortedEdges.insert(i);
        }

        UnionFind connectedComponents = new UnionFind(G.vertices());
        while(!sortedEdges.isEmpty() && minimumSpanningTree.size() < G.vertices() - 1) {
            Edge tmp = sortedEdges.delMin();
            int v1 = tmp.either();
            int v2 = tmp.other(v1);
            if(!connectedComponents.isConnected(v1, v2)) {
                connectedComponents.union(v1, v2);
                minimumSpanningTree.add(tmp);
            }
        }
    }

    public Iterable<Edge> edges() {
        return minimumSpanningTree;
    }
}
