class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String tree : skill_trees) {
            int pointer = 0;
            
            boolean possible = true;
            for(int idx = 0; idx < tree.length(); idx++) {
                char ch = tree.charAt(idx);
                
                if(skill.indexOf(ch) == -1)
                    continue;
                
                if(skill.indexOf(ch) > pointer) {
                    possible = false;
                    break;
                }
                
                if(skill.indexOf(ch) == pointer)
                    pointer++;
            }
            
            if(possible)
                answer++;
        }
        
        return answer;
    }
}