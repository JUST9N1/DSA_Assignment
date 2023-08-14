/*
 * Task 5
 * b)
 * A network consisting of n servers is connected in a tree structure, where
 * the servers are numbered from 0 to n - 1 and there are n - 1 connections
 * between them that only allow for one-way communication. A 2D array a is used
 * to represent these connections, where a[i] = [ai, bi] represents a directed
 * path from server ai to server bi. However, due to specific requirements, all
 * traffic from each server must route to server 0. The task is to reorient some
 * connections to ensure that each server has a path to server 0. The goal is to
 * minimize the number of edges that need to be changed. It is guaranteed that
 * every server must have a path to server 0 after the connections are reordered.
 * 
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges shown in red such that each node can reach node 0.
 */

package Q5;

import java.util.ArrayList;
import java.util.List;

public class ReorientConnections {

    static List<List<Integer>> graph; // Adjacency list representation of the graph
    static boolean[] visited; // To track visited nodes during DFS traversal
    static int[] reverseCount; // To keep count of reversed edges during traversal

    // Function to calculate the minimum number of edges to reverse
    public static int minReorder(int n, int[][] connections) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>()); // Initialize the adjacency list
        }

        // Build the graph using the given connections
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            graph.get(from).add(to); // Add directed edge
            graph.get(to).add(-from); // Negative value to indicate reversed edge
        }

        visited = new boolean[n]; // Initialize visited array
        reverseCount = new int[n]; // Initialize reverseCount array

        dfs(0); // Start DFS traversal from node 0

        int totalReversals = 0;
        for (int count : reverseCount) {
            if (count > 0) {
                totalReversals++; // Count nodes with reversed edges
            }
        }

        return totalReversals; // Return the minimum number of edges to reverse
    }

    // Depth-First Search traversal
    private static void dfs(int node) {
        visited[node] = true; // Mark the node as visited

        for (int neighbor : graph.get(node)) {
            if (!visited[Math.abs(neighbor)]) { // Check if the neighbor is not visited
                if (neighbor < 0) {
                    reverseCount[node]++; // Increment reverseCount if the edge is reversed
                    neighbor = -neighbor; // Get the original neighbor
                }
                dfs(neighbor); // Recursive DFS traversal on the neighbor
                reverseCount[node] += reverseCount[neighbor]; // Update reverseCount
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };

        int result = minReorder(n, connections);
        System.out.println("Minimum number of edges to reverse: " + result); // Output: 3
    }
}
