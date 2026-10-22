class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        // Check step = 1 (forward increasing)
        if (check(nums, zero, 1, n)) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        // Check step = -1 (backward increasing)
        if (check(nums, zero, -1, n)) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean check(int[] nums, int zero, int step, int n) {
        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;
            if (nums[prev] > nums[curr]) {
                return false;
            }
        }
        return true;
    }
}