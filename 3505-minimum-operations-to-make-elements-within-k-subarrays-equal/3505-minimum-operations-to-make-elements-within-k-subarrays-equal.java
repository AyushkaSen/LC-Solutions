import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCosts = new long[n];
        
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        int lowerSize = 0, upperSize = 0;
        long lowerSum = 0, upperSum = 0;

        for (int i = 0; i < n; i++) {
            // Add current element
            if (lowerSize == 0 || nums[i] <= lower.lastKey()) {
                lower.put(nums[i], lower.getOrDefault(nums[i], 0) + 1);
                lowerSize++;
                lowerSum += nums[i];
            } else {
                upper.put(nums[i], upper.getOrDefault(nums[i], 0) + 1);
                upperSize++;
                upperSum += nums[i];
            }

            // Remove element sliding out of window
            if (i >= x) {
                int out = nums[i - x];
                if (lower.containsKey(out)) {
                    lower.put(out, lower.get(out) - 1);
                    if (lower.get(out) == 0) lower.remove(out);
                    lowerSize--;
                    lowerSum -= out;
                } else {
                    upper.put(out, upper.get(out) - 1);
                    if (upper.get(out) == 0) upper.remove(out);
                    upperSize--;
                    upperSum -= out;
                }
            }

            // Balance halves safely
            int targetLowerSize = (x + 1) / 2;
            while (lowerSize < targetLowerSize && !upper.isEmpty()) {
                Map.Entry<Integer, Integer> entry = upper.firstEntry();
                int val = entry.getKey();
                upper.put(val, entry.getValue() - 1);
                if (upper.get(val) == 0) upper.remove(val);
                upperSize--;
                upperSum -= val;

                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSize++;
                lowerSum += val;
            }
            while (lowerSize > targetLowerSize && !lower.isEmpty()) {
                Map.Entry<Integer, Integer> entry = lower.lastEntry();
                int val = entry.getKey();
                lower.put(val, entry.getValue() - 1);
                if (lower.get(val) == 0) lower.remove(val);
                lowerSize--;
                lowerSum -= val;

                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSize++;
                upperSum += val;
            }

            // Calculate cost for full window starting at i - x + 1
            if (i >= x - 1) {
                int median = lower.lastKey();
                long cost = (long) median * lowerSize - lowerSum + upperSum - (long) median * upperSize;
                windowCosts[i - x + 1] = cost;
            }
        }

        // DP to find minimum cost for k non-overlapping windows
        long[][] memo = new long[n - x + 2][k + 1];
        for (long[] row : memo) Arrays.fill(row, -1);
        
        long ans = solve(0, k, x, n, windowCosts, memo);
        return ans >= 1e17 ? -1 : ans;
    }

    private long solve(int idx, int remK, int x, int n, long[] windowCosts, long[][] memo) {
        if (remK == 0) return 0;
        if (idx > n - remK * x) return (long) 1e17;
        if (memo[idx][remK] != -1) return memo[idx][remK];

        long skip = solve(idx + 1, remK, x, n, windowCosts, memo);
        long pick = windowCosts[idx] + solve(idx + x, remK - 1, x, n, windowCosts, memo);

        return memo[idx][remK] = Math.min(skip, pick);
    }
}