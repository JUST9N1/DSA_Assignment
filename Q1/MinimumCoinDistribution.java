
/*
b) 
A group of n Pathao riders stood in a queue, and each rider was assigned a unique integer rating based on their 
performance over the year. The Pathao company planned to distribute gold coins to each rider in ascending order, 
starting from count 1. The riders with higher ratings should receive more coins than their neighboring riders. The 
objective was to determine the minimum number of coins required by Pathao to distribute coins to the selected 
riders according to their ratings. 
Input: ratings = [1,0,2] 
Output: 5 
Explanation: You can give the first, second, and third rider 2, 1, 2 gold coins, respectively.   

*/



package Q1;

public class MinimumCoinDistribution {
    public static int minCoins(int[] rating) {
        int n = rating.length;
        int[] coins = new int[n]; // This array will store the number of coins assigned to each rider.

        // Initialize coins array with count one for all riders
        for (int i = 0; i < n; i++) {
            coins[i] = 1; // Every rider starts with at least one coin.
        }

        // Assign more coins to rider with higher ratings from left to right
        for (int i = 1; i < n; i++) {
            if (rating[i] > rating[i - 1]) {
                coins[i] = coins[i - 1] + 1; // If the current rider's rating is higher, give them one more coin than the previous rider.
            }
        }

        // Assign more coins to rider with higher ratings from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (rating[i] > rating[i + 1] && coins[i] <= coins[i + 1]) {
                coins[i] = coins[i + 1] + 1; // If the current rider's rating is higher than the next rider's and they have fewer coins, give them one more coin.
            }
        }

        // Calculate minimum number of coins required by summing up the coins array
        int minCoin = 0;
        for (int i = 0; i < n; i++) {
            minCoin += coins[i]; // Sum up all the coins assigned to riders.
        }
        return minCoin; // Return the total minimum number of coins required.

    }

    public static void main(String[] args) {
        int[] ratings = { 1, 0, 2 };
        int result = minCoins(ratings); // Calculate the minimum coins required using the minCoins function.

        System.out.println(result); // Print the minimum number of coins required.
    }

}
