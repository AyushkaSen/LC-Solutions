import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            int low = 0;
            int high = arr2.length - 1;
            boolean isValid = true;

            // Binary search to check if any element in arr2 is within distance d
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (Math.abs(arr2[mid] - x) <= d) {
                    isValid = false;
                    break;
                }
                if (arr2[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (isValid) {
                distanceValue++;
            }
        }

        return distanceValue;
    }
}