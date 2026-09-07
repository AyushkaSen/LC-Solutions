class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] last = new int[26];
        int all = 1; // Starts at 1 to account for the empty subsequence ""

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            // Count of strictly new distinct subsequences ending in 'ch'
            int diff = (all - last[c] + MOD) % MOD;
            
            all = (all + diff) % MOD;
            last[c] = (last[c] + diff) % MOD;
        }

        // Subtract 1 to exclude the empty subsequence
        return (all - 1 + MOD) % MOD;
    }
}