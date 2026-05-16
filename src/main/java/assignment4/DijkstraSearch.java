package assignment4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Double> distTo;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        this.distTo = new HashMap<>();
        dijkstra(source);
    }

    private void dijkstra(Vertex<V> source) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
                Comparator.comparingDouble(v -> distTo.getOrDefault(v, Double.MAX_VALUE))
        );

        distTo.put(source, 0.0);
        pq.add(source);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            if (visited.contains(current)) continue;
            visited.add(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double newDist = distTo.get(current) + entry.getValue();

                if (newDist < distTo.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    public double distanceTo(Vertex<V> dest) {
        return distTo.getOrDefault(dest, Double.MAX_VALUE);
    }
}
