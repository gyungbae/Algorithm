import java.util.*;

class Solution {
    boolean[][] pillar;
    boolean[][] beam;
    int size;

    boolean canPillar(int col, int row) {
        return row == 0 
            || pillar[col][row - 1] 
            || beam[col][row] 
            || (col > 0 && beam[col - 1][row]);
    }

    boolean canBeam(int col, int row) {
        return pillar[col][row - 1]
            || pillar[col + 1][row - 1]
            || (col > 0 && beam[col - 1][row] && beam[col + 1][row]);
    }

    boolean isValid() {
        for(int col = 0; col <= size; col++) {
            for(int row = 0; row <= size; row++) {
                if(pillar[col][row] && !canPillar(col, row))
                    return false;

                if(beam[col][row] && !canBeam(col, row))
                    return false;
            }
        }

        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        size = n;
        pillar = new boolean[n + 2][n + 2];
        beam = new boolean[n + 2][n + 2];

        for(int[] info : build_frame) {
            int col = info[0];
            int row = info[1];
            int type = info[2];      // 0 기둥, 1 보
            int command = info[3];   // 0 삭제, 1 설치

            if(type == 0) {
                pillar[col][row] = command == 1;
            } else {
                beam[col][row] = command == 1;
            }

            if(!isValid()) {
                if(type == 0) {
                    pillar[col][row] = command == 0;
                } else {
                    beam[col][row] = command == 0;
                }
            }
        }

        List<int[]> result = new ArrayList<>();

        for(int col = 0; col <= n; col++) {
            for(int row = 0; row <= n; row++) {
                if(pillar[col][row]) {
                    result.add(new int[]{col, row, 0});
                }

                if(beam[col][row]) {
                    result.add(new int[]{col, row, 1});
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}