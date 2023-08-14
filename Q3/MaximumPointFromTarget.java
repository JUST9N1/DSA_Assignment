/*
Question 3 
a) Suppose you are provided an array of n targets that are placed in a row from 0 to n-1. Each target is assigned 
with certain integer such that a [0] represent the value associated with target zero. You are asked to shoot all 
the targets. If you shoot I th target then you will get a[i-1]*a[i]*a[i+1] points. 
Note that if i-1 and i+1 position hits index out of bound, then you can assume that two target with value 1 are 
padded before start target and after end target.  
Return maximum point one can gain by hitting each target. 
 
Input: a = [3,1,5,8] 
Output: 167 
Explanation: 
a = [3,1,5,8] 
[3,1,5,8]  points 3*1*5 (“hitting target with value 1”) 
[3,5,8] points 3*5*8 (“hitting target with value 5”) 
[3,8]  points 1*3*8 (“hitting target with value 3”) note that there is padded target with value 1 at beginning and end 
of the provided target list 
,[8]  points 1*8*1 same as above 
points =      3*1*5+   3*5*8   +  1*3*8  + 1*8*1 = 167 
 */

package Q3;

public class MaximumPointFromTarget {
    public static int getMaxPoints(int[] a) {
        int n = a.length;

        // Create a new array paddedTargets with two additional targets with value 1 at
        // the beginning and end
        int[] paddedTargets = new int[n + 2];
        paddedTargets[0] = paddedTargets[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            paddedTargets[i + 1] = a[i]; // Copy the original target values to the paddedTargets array
        }

        // Create a 2D array dp to store maximum points for shooting targets within
        // certain ranges
        int[][] dp = new int[n + 2][n + 2];

        // Loop through different lengths of target ranges
        for (int len = 1; len <= n; len++) {
            // Loop through all possible starting points of the target range
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1; // Calculate the ending point of the target range

                // Loop through all possible intermediate points within the target range
                for (int k = left; k <= right; k++) {
                    // Calculate the points gained by shooting the k-th target in the current range
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][k - 1] + paddedTargets[left - 1] * paddedTargets[k] * paddedTargets[right + 1]
                                    + dp[k + 1][right]);
                }
            }
        }

        // Return the maximum points that can be obtained by shooting all the targets
        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] a = { 3, 1, 5, 8 };
        int output = getMaxPoints(a); // Calculate the maximum points for the given target values
        System.out.println(output); // Print the calculated maximum points (Output: 167)
    }
}
