package RelatedToGraphDSA;

public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count;
    private int checkedComponents;

    public ConnectedComponents(Graph G) {
        marked = new boolean[G.numberOfVertices()];
        checkedComponents = 0;
        for(int i = 0; i < G.numberOfVertices(); i++) {
            if(!marked[i]) {
                dfs(G, i);
                count++;
            }
            if(checkedComponents >= marked.length) break;
        }
    }

    public int numberOfConnectedGroups() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connnected(int v1, int v2) {
        return id[v1] == id[v2];
    }

    private void dfs(Graph G, int from) {
        marked[from] = true;
        id[from] = count;
        checkedComponents++;
        for(int i : G.adj(from)) {
            if(!marked[i]) {
                dfs(G, i);
            }
        }
    }
}
