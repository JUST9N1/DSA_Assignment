/*
 * Task 5 
 * a) Implement hill climbing algorithm
 */

package Q5;

import java.util.Random;

public class HillClimbingAlgorithm {

    // Hypothetical function to optimize (you can replace this with your function)
    private static double evaluate(double x) {
        return -(x - 5) * (x - 5); // Minimize (maximize if you change the sign)
    }

    // Generates a random neighbor around 'x' within a specified 'stepSize'
    private static double getRandomNeighbor(double x, double stepSize) {
        Random random = new Random();
        double offset = random.nextDouble() * stepSize;
        if (random.nextBoolean()) {
            return x + offset;
        } else {
            return x - offset;
        }
    }

    // Hill climbing algorithm to find the optimal solution
    public static double hillClimbing(double initialX, double stepSize, int maxIterations) {
        double currentX = initialX;
        double currentValue = evaluate(currentX);

        // Iterate for the specified maximum number of iterations
        for (int i = 0; i < maxIterations; i++) {
            double neighborX = getRandomNeighbor(currentX, stepSize);
            double neighborValue = evaluate(neighborX);

            // If the neighbor's value is better, update the current solution
            if (neighborValue > currentValue) {
                currentX = neighborX;
                currentValue = neighborValue;
            }
        }

        return currentX; // Return the best solution found
    }

    public static void main(String[] args) {
        double initialX = 0; // Initial solution
        double stepSize = 0.1; // Step size for generating random neighbors
        int maxIterations = 100; // Maximum number of iterations

        // Find the optimal solution using hill climbing
        double solution = hillClimbing(initialX, stepSize, maxIterations);
        double optimumValue = evaluate(solution);

        // Print the optimal solution and its corresponding value
        System.out.println("Optimal Solution: " + solution);
        System.out.println("Optimal Value: " + optimumValue);
    }
}
