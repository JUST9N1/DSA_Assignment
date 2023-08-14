/*
Question 4 
a) 
There are n tasks you need to complete for a game, labelled from 1 to n. 
We are given r[i]=[x,y] representing a prerequisite relationship between task x and task y: task x has to be 
completed before task y. 
In one step you can complete any number of task as long as you have completed all the prerequisites for the tasks 
you are provided while playing game. 
Return the minimum number of steps needed to complete all tasks.  If there is no way to complete all the tasks, 
return -1. 
 
 
Input: N = 3, r= [[1,3],[2,3]] 
Output: 2 
Explanation:  
In the first step, you can complete task 1 and 2. In the second semester, step task 3 can be completed.
 */

package Q4;

// Importing the necessary Java libraries
import java.util.*;

// Defining the class for calculating minimum steps to complete tasks
public class MinimumStepsToCompleteTasks {

    // Method to calculate the minimum steps to complete tasks
    public static int minSteps(int N, int[][] prerequisites) {
        // Creating a list of lists to represent the directed graph
        List<List<Integer>> graph = new ArrayList<>();
        // Array to keep track of the incoming edges for each node
        int[] inDegree = new int[N + 1];

        // Initializing the graph
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // Building the graph and calculating incoming edges
        for (int[] preReq : prerequisites) {
            int x = preReq[0];
            int y = preReq[1];
            graph.get(x).add(y); // Adding y as a neighbor of x (x -> y)
            inDegree[y]++; // Incrementing the incoming edge count for y
        }

        // Creating a queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        // Adding nodes with no incoming edges to the queue
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Initializing steps counter
        int steps = 0;
        // Performing BFS traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Processing nodes at the current level
            for (int i = 0; i < size; i++) {
                int current = queue.poll(); // Removing the node from the queue
                // Decreasing the incoming edge count for neighbors
                for (int neighbor : graph.get(current)) {
                    inDegree[neighbor]--;
                    // Adding neighbors with no incoming edges to the queue
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
            steps++; // Incrementing the steps after processing a level
        }

        // Checking if there are remaining tasks with incoming edges (cycles)
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] > 0) {
                return -1; // There's a cycle, can't complete all tasks
            }
        }

        return steps; // Returning the minimum steps required
    }

    // Main method for testing
    public static void main(String[] args) {
        int n = 3;
        int[][] prerequisites = { { 1, 3 }, { 2, 3 } };
        int result = minSteps(n, prerequisites);
        System.out.println("Minimum number of steps needed to complete all tasks: " + result);
    }
}
