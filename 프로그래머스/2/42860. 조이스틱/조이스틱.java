class Solution {
    public int solution(String name) {
        int vertical = 0;
        int horizontal = name.length() - 1;
        for(int idx = 0; idx < name.length(); idx++) {
            char ch = name.charAt(idx);
            vertical += Math.min(ch - 'A', 'Z' - ch + 1);
            
            int nextIdx = idx + 1;
            while(nextIdx < name.length() && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }
            
            horizontal = Math.min(horizontal, idx * 2 + name.length() - nextIdx);
            horizontal = Math.min(horizontal, (name.length() - nextIdx) * 2 + idx);
        }
        
        return vertical + horizontal;
    }
}