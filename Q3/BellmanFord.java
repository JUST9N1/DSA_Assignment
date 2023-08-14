package Q3;

import java.util.*;

// Class to implement a Max Heap data structure
class MaxHeap {
    private List<Integer> heap;

    // Constructor to initialize an empty heap
    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Method to insert a value into the heap and maintain the Max Heap property
    public void push(int val) {
        heap.add(val); // Add the value at the end
        siftUp(heap.size() - 1); // Restore the Max Heap property
    }

    // Method to remove and return the maximum value from the heap
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        int maxValue = heap.get(0); // Store the maximum value
        int lastValue = heap.remove(heap.size() - 1); // Remove the last value

        if (!isEmpty()) {
            heap.set(0, lastValue); // Replace the root with the last value
            siftDown(0); // Restore the Max Heap property
        }

        return maxValue;
    }

    // Method to check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Helper method to move a value up the heap to maintain Max Heap property
    private void siftUp(int index) {
        int parent = (index - 1) / 2; // Calculate the parent index

        // While the current value is greater than its parent, swap and update indices
        while (index > 0 && heap.get(index) > heap.get(parent)) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Helper method to move a value down the heap to maintain Max Heap property
    private void siftDown(int index) {
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        int largest = index;

        // Check if left child exists and is greater than the current largest
        if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)) {
            largest = leftChild;
        }

        // Check if right child exists and is greater than the current largest
        if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
            largest = rightChild;
        }

        // If the largest value is not the current value, swap and recursively sift down
        if (largest != index) {
            swap(index, largest);
            siftDown(largest);
        }
    }

    // Helper method to swap two values in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

// Class to represent an edge in a graph
class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

// Class to perform the Bellman-Ford algorithm on a graph
public class BellmanFord {
    private static final int INF = Integer.MAX_VALUE;

    // Method to find shortest distances from a source vertex using Bellman-Ford
    public static int[] bellmanFord(List<Edge> graph, int vertices, int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, INF);
        distances[source] = 0;

        // Relax edges repeatedly (vertices - 1 times)
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.src;
                int v = edge.dest;
                int weight = edge.weight;
                if (distances[u] != INF && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : graph) {
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.weight;
            if (distances[u] != INF && distances[u] + weight < distances[v]) {
                throw new IllegalArgumentException("Graph contains a negative weight cycle");
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        // Example graph representation as an edge list
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(4, 0, 4));
        edges.add(new Edge(4, 1, 4));
        edges.add(new Edge(4, 5, 6));

        int vertices = 6;
        int sourceVertex = 0;

        try {
            int[] distances = bellmanFord(edges, vertices, sourceVertex);
            System.out.println("Shortest distances from source vertex " + sourceVertex + ": ");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
