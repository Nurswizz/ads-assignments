package assignment4;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        example();
    }

    private static void example() {
        WeightedGraph<Integer> network = new WeightedGraph<>(true);

        Vertex<Integer> pc1     = new Vertex<>(1);
        Vertex<Integer> pc2     = new Vertex<>(2);
        Vertex<Integer> pc3     = new Vertex<>(3);
        Vertex<Integer> pc4     = new Vertex<>(4);
        Vertex<Integer> pc5     = new Vertex<>(5);
        Vertex<Integer> server  = new Vertex<>(6);

        network.addEdge(pc1,    pc2,    4.0);
        network.addEdge(pc1,    pc3,    1.0);
        network.addEdge(pc3,    pc2,    2.0);
        network.addEdge(pc2,    pc4,    5.0);
        network.addEdge(pc3,    pc5,    8.0);
        network.addEdge(pc4,    server, 2.0);
        network.addEdge(pc5,    server, 3.0);
        network.addEdge(pc2,    server, 10.0);

        System.out.println("=== Example 2: Computer Network (latency in ms) ===");
        System.out.println("--- BFS from PC-1 ---");
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(network, pc1);
        printPath(bfs, pc1, server);
        printPath(bfs, pc1, pc4);
        printPath(bfs, pc1, pc5);

        System.out.println("--- Dijkstra from PC-1 ---");
        DijkstraSearch<Integer> dijkstra = new DijkstraSearch<>(network, pc1);
        printPath(dijkstra, pc1, server);
        printPath(dijkstra, pc1, pc4);
        printPath(dijkstra, pc1, pc5);
        System.out.printf("Lowest latency PC-1 -> Server: %.0f ms%n", dijkstra.distanceTo(server));
        System.out.printf("Lowest latency PC-1 -> PC-4:   %.0f ms%n", dijkstra.distanceTo(pc4));
    }

    private static <V> void printPath(Search<V> search, Vertex<V> source, Vertex<V> dest) {
        System.out.printf("Path from %s to %s: ", source, dest);
        Iterable<Vertex<V>> path = search.pathTo(dest);
        if (path == null) {
            System.out.println("no path found");
            return;
        }
        boolean first = true;
        for (Vertex<V> v : path) {
            if (!first) System.out.print(" -> ");
            System.out.print(v);
            first = false;
        }
        System.out.println();
    }
}
