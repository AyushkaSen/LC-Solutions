class Solution {
    public boolean canAliceWin(int n) {
        int stonesToTake = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToTake) {
            n -= stonesToTake;
            stonesToTake--;
            isAliceTurn = !isAliceTurn;
        }

        // If the loop terminates on Alice's turn, Alice cannot move and loses (return false).
        // If it terminates on Bob's turn, Bob cannot move, so Alice wins (return true).
        return !isAliceTurn;
    }
}