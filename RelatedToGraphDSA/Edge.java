package RelatedToGraphDSA;

public class Edge implements Comparable<Edge> {
    private final int v1;
    private final int v2;
    private final double weight;

    public Edge(int v1, int v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public int either() {
        return v1;
    }

    public int other(int v) {
        if(v == v1) return v2;
        else return v1;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        if(this.weight > other.weight) return 1;
        else if(this.weight < other.weight) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return v1 + "-" + v2 + ":" + weight;
    }
}
