class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int validCount = 0;

        for (int num = 100; num <= 998; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;

            int[] needed = new int[10];
            needed[hundreds]++;
            needed[tens]++;
            needed[units]++;

            if (needed[hundreds] <= count[hundreds] &&
                needed[tens] <= count[tens] &&
                needed[units] <= count[units]) {
                validCount++;
            }
        }

        return validCount;
    }
}