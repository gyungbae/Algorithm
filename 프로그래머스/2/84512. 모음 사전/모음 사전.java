class Solution {
    int answer;
    int count;
    
    char[] words = {'A', 'E', 'I', 'O', 'U'};
    
    void search(String word, String target) {
        if(word.equals(target)) {
            answer = count;
            return;
        }
        
        if(word.length() == 5)
            return;
        
        for(int idx = 0; idx < 5; idx++) {
            count++;
            search(word + words[idx], target);
        }
    }
    
    
    public int solution(String word) {
        answer = 0;
        
        search("", word);
        return answer;
    }
}