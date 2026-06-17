class Solution {
    int[][] key;
    int keySize;
    
    void rotate() {
        int[][] result = new int[keySize][keySize];
        for(int row = 0; row < keySize; row++) {
            for(int col = 0; col < keySize; col++) {
                result[row][col] = key[col][keySize - 1 - row];
            }
        }
        
        key = result;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        keySize = key.length;
        
        int lockSize = lock.length;
        int mapSize = lockSize + keySize * 2;
        
        for(int rotate = 0; rotate < 4; rotate++) {
            int[][] map = new int[mapSize][mapSize];
            for(int row = 0; row < lockSize; row++) {
                for(int col = 0; col < lockSize; col++) {
                    map[row + keySize][col + keySize] = lock[row][col];
                }
            }
            
            if(rotate != 0)
                rotate();
            
            for(int fromRow = 1; fromRow < mapSize - keySize; fromRow++) {
                for(int fromCol = 1; fromCol < mapSize - keySize; fromCol++) {
                    for(int row = 0; row < keySize; row++) {
                        for(int col = 0; col < keySize; col++) {
                            map[fromRow + row][fromCol + col] += this.key[row][col];
                        }
                    }
                    
                    boolean solved = true;
                    loop :
                     for(int row = keySize; row < keySize + lockSize; row++) {
                        for(int col = keySize; col < keySize + lockSize; col++) {
                            if(map[row][col] != 1) {
                                solved = false;
                                break loop;
                            }
                        }
                    }
                    
                    if(solved)
                        return true;
                    
                    for(int row = 0; row < keySize; row++) {
                        for(int col = 0; col < keySize; col++) {
                            map[fromRow + row][fromCol + col] -= this.key[row][col];
                        }
                    }
                }
            }
        }
        
        return false;
    }
}