class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int total = n + k - 1;
        int r = 2 * k;

        if (r > total) {
            return 0;
        }

        long num = 1;
        long den = 1;

        // Compute C(total, r) % MOD
        // Using property: C(N, R) = C(N, N - R)
        if (r > total - r) {
            r = total - r;
        }

        for (int i = 1; i <= r; i++) {
            num = (num * (total - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        // num * modular_inverse(den) % MOD
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long base, int mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}