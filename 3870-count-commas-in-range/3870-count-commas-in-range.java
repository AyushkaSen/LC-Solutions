class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        long threshold = 1000L; // 10^3, 10^6, 10^9, etc.

        while (threshold <= n) {
            totalCommas += (n - threshold + 1);
            threshold *= 1000L;
        }

        return totalCommas;
    }
}