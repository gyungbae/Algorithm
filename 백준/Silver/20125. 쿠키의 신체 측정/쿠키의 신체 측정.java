import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        int headRow = -1;
        int headCol = -1;
        for (int row = 0; row < N; row++) {
            String str = br.readLine();
            for (int col = 0; col < N; col++) {
                char ch = str.charAt(col);
                map[row][col] = ch;

                if (headRow == -1 && ch == '*') {
                    headRow = row;
                    headCol = col;
                }
            }
        }

        int heartRow = headRow + 1;
        int heartCol = headCol;

        char[] armArr = map[heartRow];
        int leftArm = 0;
        for(int col = heartCol - 1; col >= 0; col--) {
            if(armArr[col] != '*')
                break;

            leftArm++;
        }

        int rightArm = 0;
        for(int col = heartCol + 1; col < N; col++) {
            if(armArr[col] != '*')
                break;

            rightArm++;
        }

        int waist = 0;
        int lastWaistRow = heartRow;
        for(int row = heartRow + 1; row < N; row++) {
            if(map[row][heartCol] != '*')
                break;

            waist++;
            lastWaistRow = row;
        }

        int leftLeg = 0;
        for (int row = lastWaistRow + 1; row < N; row++) {
            if(map[row][heartCol - 1] != '*')
                break;

            leftLeg++;
        }

        int rightLeg = 0;
        for (int row = lastWaistRow + 1; row < N; row++) {
            if(map[row][heartCol + 1] != '*')
                break;

            rightLeg++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(heartRow + 1).append(" ").append(heartCol + 1).append("\n");
        sb.append(leftArm).append(" ").append(rightArm).append(" ").append(waist)
                .append(" ").append(leftLeg).append(" ").append(rightLeg);

        System.out.println(sb);
    }
}
