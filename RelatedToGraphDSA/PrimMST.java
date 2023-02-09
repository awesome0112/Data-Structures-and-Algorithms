package RelatedToGraphDSA;

import SimpleDataStructures.MinPQ;

import java.util.LinkedList;
import java.util.Queue;

public class PrimMST {
    private boolean[] marked; // vertices that are marked
    private Queue<Edge> mst; // result
    private MinPQ<Edge> sortedEdges;

    public PrimMST(EdgeWeightedGraph G) {
        // SimpleDataStructures.MinPQ được dùng để lưu trữ cạnh nhỏ nhất để lấy ra dần cho vào kết quả
        // Bắt đầu từ đỉnh 0
        // Kiểm tra các cạnh kề với 0
        // Kiểm tra 2 đỉnh nối của cạnh đang xét
        // Nếu cả 2 đỉnh đều đã được đánh dấu đi qua thì bỏ qua
        // Ngược lại thì nếu có đỉnh nào chưa được đánh dấu thì visit đỉnh đó
        // Trong hàm visit sẽ đánh dấu đỉnh chuyền vào là đã đi qua
        //  Và kiểm tra các cạnh kề với đỉnh đó
        //  Nếu đỉnh còn lại của cạnh kề đó chưa được đánh dấu thì cho vào SimpleDataStructures.MinPQ để kiểm tra tiếp
        // Lặp lại cho đến khi SimpleDataStructures.MinPQ rỗng hoặc số cạnh trong kết quả đã bằng số đỉnh - 1
        sortedEdges = new MinPQ<>(G.numOfEdges());
        mst = new LinkedList<>();
        marked = new boolean[G.vertices()];

        visit(G, 0); // start at vertice 0

        while(!sortedEdges.isEmpty() && mst.size() < G.vertices() - 1) {
            Edge tmp = sortedEdges.delMin();
            int v1 = tmp.either();
            int v2 = tmp.other(v1);
            if(!(marked[v1] && marked[v2])) {
                mst.add(tmp);
                if(!marked[v1]) visit(G, v1);
                if(!marked[v2]) visit(G, v2);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int vertice) {
        marked[vertice] = true;
        for(Edge i : G.adj(vertice)) {
            if(!marked[i.other(vertice)]) sortedEdges.insert(i);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
