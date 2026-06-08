import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Info> map = new HashMap<>();
        
        for(int idx = 0; idx < plays.length; idx++) {
            String genre = genres[idx];
            int play = plays[idx];
            
            map.putIfAbsent(genre, new Info(genre));
            
            Info info = map.get(genre);
            info.addPlay(play);
            info.addList(new int[]{idx, play});
        }
        
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> o2.totalPlay - o1.totalPlay);
        for(Info info : map.values()) {
            queue.offer(info);
        }
        
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            Collections.sort(info.playList, (o1, o2) -> {
               if(o1[1] == o2[1])
                   return o1[0] - o2[0];
                
                return o2[1] - o1[1];
            });
            
            list.add(info.playList.get(0)[0]);
            
            if(info.playList.size() > 1) 
                list.add(info.playList.get(1)[0]);
        }
        
        int[] answer = new int[list.size()];
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
}

class Info {
    String genre;
    int totalPlay;
    List<int[]> playList;
    
    public Info (String genre) {
        this.genre = genre;
        playList = new ArrayList<>();
    }
    
    void addPlay(int play) {
        totalPlay += play;
    }
    
    void addList(int[] arr) {
        playList.add(arr);
    }
}