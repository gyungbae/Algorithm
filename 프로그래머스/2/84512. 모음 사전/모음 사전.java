import java.util.*;

class Solution {
    char[] arr = {'A', 'E', 'I', 'O', 'U'};
    List<String> dictionary = new ArrayList<>();
    
    void makeWord(String current) {
        dictionary.add(current);
        
        if(current.length() == 5) {
            return;
        }
        
        for(int idx = 0; idx < 5; idx++) {
            makeWord(current + arr[idx]);
        }
    }
    
    
    public int solution(String word) {
        makeWord("");
        
        return dictionary.indexOf(word);
    }
}