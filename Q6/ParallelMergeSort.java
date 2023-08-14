package Q6;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {

    // Inner class representing a task for parallel merge sort
    private static class MergeSortTask extends RecursiveAction {
        private final int[] array;
        private final int start;
        private final int end;

        // Constructor for the task
        public MergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        // The main computation method for the task
        @Override
        protected void compute() {
            // If the subarray is small enough, no need to sort
            if (end - start <= 1) {
                return;
            }

            // Calculate the midpoint of the subarray
            int mid = (start + end) / 2;

            // Create tasks for sorting the left and right halves
            MergeSortTask leftTask = new MergeSortTask(array, start, mid);
            MergeSortTask rightTask = new MergeSortTask(array, mid, end);

            // Invoke both tasks in parallel
            invokeAll(leftTask, rightTask);

            // Merge the sorted left and right halves
            merge(array, start, mid, end);
        }

        // Merge two sorted subarrays into a single sorted subarray
        private void merge(int[] array, int start, int mid, int end) {
            int[] merged = new int[end - start];
            int leftIndex = start;
            int rightIndex = mid;
            int mergedIndex = 0;

            // Compare elements from both halves and merge them in sorted order
            while (leftIndex < mid && rightIndex < end) {
                if (array[leftIndex] < array[rightIndex]) {
                    merged[mergedIndex++] = array[leftIndex++];
                } else {
                    merged[mergedIndex++] = array[rightIndex++];
                }
            }

            // Copy remaining elements from the left half, if any
            while (leftIndex < mid) {
                merged[mergedIndex++] = array[leftIndex++];
            }

            // Copy remaining elements from the right half, if any
            while (rightIndex < end) {
                merged[mergedIndex++] = array[rightIndex++];
            }

            // Copy the merged subarray back to the original array
            System.arraycopy(merged, 0, array, start, merged.length);
        }
    }

    // Method for invoking parallel merge sort
    public static void parallelMergeSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, 0, array.length);
        pool.invoke(task);
    }

    // Main method to demonstrate parallel merge sort
    public static void main(String[] args) {
        int[] inputArray = { 5, 3, 9, 1, 7, 2, 8, 4, 6 };
        parallelMergeSort(inputArray);
        System.out.println("Sorted array: " + Arrays.toString(inputArray));
    }
}
