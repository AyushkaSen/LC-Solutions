class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int lCount = 0;
        int rCount = 0;
        int blankCount = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                lCount++;
            } else if (c == 'R') {
                rCount++;
            } else {
                blankCount++;
            }
        }

        return Math.abs(lCount - rCount) + blankCount;
    }
}