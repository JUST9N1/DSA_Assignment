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

import java.util.*;

public class MinimumStepsToCompleteTasks {

    public static int minSteps(int N, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] preReq : prerequisites) {
            int x = preReq[0];
            int y = preReq[1];
            graph.get(x).add(y);
            inDegree[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int neighbor : graph.get(current)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
            steps++;
        }

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] > 0) {
                return -1; // There's a cycle, can't complete all tasks
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] prerequisites = { { 1, 3 }, { 2, 3 } };
        int result = minSteps(n, prerequisites);
        System.out.println("Minimum number of steps needed to complete all tasks: " + result);
    }
}
