package assignment4;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        bfs(source);
    }

    private void bfs(Vertex<V> root) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        visited.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }
}
