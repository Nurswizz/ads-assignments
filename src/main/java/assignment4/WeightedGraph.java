package assignment4;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final List<Vertex<V>> vertices;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Vertex<V> v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        if (!vertices.contains(source)) addVertex(source);
        if (!vertices.contains(dest)) addVertex(dest);

        source.addAdjacentVertex(dest, weight);
        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }

    public boolean isUndirected() {
        return undirected;
    }
}
