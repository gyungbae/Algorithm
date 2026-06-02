import java.util.*;

class Solution {
    public int solution(String dirs) {
        int row = 0;
        int col = 0;

        Set<String> set = new HashSet<>();

        for (int idx = 0; idx < dirs.length(); idx++) {

            int prevRow = row;
            int prevCol = col;

            char move = dirs.charAt(idx);

            if (move == 'U')
                row++;
            else if (move == 'D')
                row--;
            else if (move == 'L')
                col--;
            else
                col++;

            if (row < -5 || row > 5 || col < -5 || col > 5) {
                row = prevRow;
                col = prevCol;
                continue;
            }

            String road =
                    prevRow + "," + prevCol + "," + row + "," + col;

            String reverseRoad =
                    row + "," + col + "," + prevRow + "," + prevCol;

            set.add(road);
            set.add(reverseRoad);
        }

        return set.size() / 2;
    }
}