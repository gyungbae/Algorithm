class Solution {
    String[] words;
    String target;
    
    boolean[] visited;
    int answer;
    
    void search(int depth, String current) {
        if(depth >= answer)
            return;
        
        if(current.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int idx = 0; idx < words.length; idx++) {
            if(canChange(current, words[idx]) && !visited[idx]) {
                visited[idx] = true;
                search(depth + 1, words[idx]);
                visited[idx] = false;
            }
        }
    }
    
    boolean canChange(String word1, String word2) {
        int count = 0;
        
        for(int idx = 0; idx < word1.length(); idx++) {
            if(word1.charAt(idx) != word2.charAt(idx)) {
                count++;
                
                if(count > 1)
                    return false;
            }
        }
        
        return true;
    }
    
    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.target = target;
        
        visited = new boolean[words.length];
        answer = Integer.MAX_VALUE;
        
        search(0, begin);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}