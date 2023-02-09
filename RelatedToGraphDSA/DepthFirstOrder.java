package RelatedToGraphDSA;

import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePostOrder;
    private int checkedComponents;

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        checkedComponents++;
        for(int i : G.adj(v)) {
            if(!marked[i]) {
                dfs(G, i);
            }
        }
        reversePostOrder.push(v);
    }

    // Topological sort bản chất là sắp xếp ngược lại của postorder của đồ thị có hướng.
    public DepthFirstOrder(Digraph G) {
        reversePostOrder = new Stack<Integer>();
        checkedComponents = 0;
        marked = new boolean[G.numberOfVertices()];
        for(int i = 0; i < G.numberOfVertices(); i++) {
            if(!marked[i]) {
                dfs(G, i);
            }
            if(checkedComponents == marked.length) break;
        }
    }

    public Iterable<Integer> getReversePostOrder() {
        return reversePostOrder;
    }
}
