class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // If t is longer than s, it's impossible for t to be a subsequence of s
        if (m < n) {
            return 0;
        }

        // dp[i][j] stores distinct subsequences of s[0..i-1] matching t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // An empty target t can always be formed in 1 way (delete everything)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Match current char (dp[i-1][j-1]) + Skip current char (dp[i-1][j])
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Can only skip current char in s
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}