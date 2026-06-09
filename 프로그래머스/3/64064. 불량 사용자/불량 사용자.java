import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();
    List<String>[] possibleList;
    
    void search(int depth, Set<String> banned) {
        if(depth == possibleList.length) {
            List<String> list = new ArrayList<>(banned);
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for(String id : list) {
                sb.append(id).append(",");
            }
            
            set.add(sb.toString());
            return;
        }
        
        for(String user : possibleList[depth]) {
            if(banned.contains(user))
                continue;
            
            banned.add(user);
            search(depth + 1, banned);
            banned.remove(user);
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        int size = banned_id.length;
        
        possibleList = new ArrayList[size];
        for(int idx = 0; idx < size; idx++) {
            possibleList[idx] = new ArrayList<>();
        }
        
        for(int banIdx = 0; banIdx < size; banIdx++) {
            String ban = banned_id[banIdx];
            
            for(int userIdx = 0; userIdx < user_id.length; userIdx++) {
                String user = user_id[userIdx];
                
                if(user.length() != ban.length())
                    continue;
                
                boolean possible = true;
                for(int chIdx = 0; chIdx < ban.length(); chIdx++) {
                    char banCh = ban.charAt(chIdx);
                    char userCh = user.charAt(chIdx);
                    
                    if(banCh != '*' && banCh != userCh) {
                        possible = false;
                        break;
                    }
                }
                
                if(possible) 
                    possibleList[banIdx].add(user);
            }
        }
        
        search(0, new HashSet<>());
        
        return set.size();
    }
}