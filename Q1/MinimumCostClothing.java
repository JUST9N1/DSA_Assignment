/*
a)  
A trio of friends planned to purchase clothing from a particular store for an upcoming party, intending to wear 
matching outfits in varying colors - black, blue, and pink. The store had three different clothing sets on display, 
each in a different color. The shopkeeper assisted the three friends by selecting a clothing set in the appropriate 
color for each person based on their body shape and size. Given a 2D array, price[][3], where price[i][0], 
price[i][1], and price[i][2] represent the price of each clothing set for a different colored outfit for person i, your 
objective is to determine the minimum cost required to purchase clothing such that each person wears  have 
different color clothes if they stand in a row. 
 
It should be noted that any two people can wear the same color cloth, but the third person must wear various 
color cloths, and all three can wear different colored garments. 
Input: N = 3, price[][3] = [{14, 4, 11}, {11, 14, 3}, {14, 2, 10}] 
Output: 9 
Explanation:  
Person 1 chooses blue clothing cost=4. Person 2 chooses pink clothing. Cost = 3. Person 3 chooses blue 
clothing. Cost = 2. 
As a result, the total cost = 2 + 5 + 3 = 9. 
Note: algorithm must take 
Time Complexity: O(N) 
Auxiliary Space: O(1) 
*/


package Q1;

public class MinimumCostClothing {

    public static int minCost(int[][] price) {
        int[][] dp = new int[3][3];

        for (int j = 0; j < 3; j++) {
            dp[0][j] = price[0][j];
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = price[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            minCost = Math.min(minCost, dp[2][j]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[][] price = {
                { 14, 4, 11 },
                { 11, 14, 3 },
                { 14, 2, 10 }
        };
        int result = minCost(price);
        System.out.println(result);
    }
}