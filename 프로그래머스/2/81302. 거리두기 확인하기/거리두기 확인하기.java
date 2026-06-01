class Solution {
    char[][] places;
    
    int[] deltaRow1 = {-1, 1, 0, 0};
    int[] deltaCol1 = {0, 0, -1, 1};
    int[] deltaRow2 = {-1, -1, 1, 1};
    int[] deltaCol2 = {-1, 1, -1, 1};
    
    boolean isInRange(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }
    
    boolean search(int row, int col) {
        boolean[] partition = new boolean[4];
        
        for(int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow1[delta];
            int nextCol = col + deltaCol1[delta];
            
            if(!isInRange(nextRow, nextCol))
                continue;
            
            if(places[nextRow][nextCol] == 'P')
                return false;
            
            if(places[nextRow][nextCol] == 'X')
                partition[delta] = true;
            
            nextRow += deltaRow1[delta];
            nextCol += deltaCol1[delta];
            
            if(!isInRange(nextRow, nextCol))
                continue;
            
            if(places[nextRow][nextCol] == 'P' && !partition[delta])
                return false;
        }
        
        for(int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow2[delta];
            int nextCol = col + deltaCol2[delta];
            
            if(!isInRange(nextRow, nextCol))
                continue;
            
            if(places[nextRow][nextCol] == 'P') {
                switch (delta) {
                    case 0 -> {
                        if(!partition[0] || !partition[2])
                            return false;
                    }
                    case 1 -> {
                        if(!partition[0] || !partition[3])
                            return false;
                    }
                    case 2 -> {
                        if(!partition[1] || !partition[2])
                            return false;
                    }
                    case 3 -> {
                        if(!partition[1] || !partition[3])
                            return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int placeIdx = 0; placeIdx < 5; placeIdx++) {
            this.places = new char[5][5];
            
            for(int row = 0; row < 5; row++) {
                this.places[row] = places[placeIdx][row].toCharArray();
            }
            
            boolean satisfied = true;
            
            for(int row = 0; row < 5; row++) {
                for(int col = 0; col < 5; col++) {
                    if(this.places[row][col] != 'P')
                        continue;
                    
                    if(!search(row, col)) {
                        satisfied = false;
                        break;
                    }
                }
                
                if(!satisfied)
                    break;
            }
            
            answer[placeIdx] = satisfied ? 1 : 0;
        }
        
        return answer;
    }
}