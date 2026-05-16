package assignment4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public abstract class Search<V> {
    protected final Vertex<V> source;
    protected Set<Vertex<V>> visited;
    protected Map<Vertex<V>, Vertex<V>> edgeTo;

    public Search(Vertex<V> source) {
        this.source = source;
        this.visited = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<V> dest) {
        return visited.contains(dest);
    }

    public Iterable<Vertex<V>> pathTo(Vertex<V> dest) {
        if (!hasPathTo(dest)) return null;

        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> v = dest; !v.equals(source); v = edgeTo.get(v)) {
            path.addFirst(v);
        }
        path.addFirst(source);
        return path;
    }
}
