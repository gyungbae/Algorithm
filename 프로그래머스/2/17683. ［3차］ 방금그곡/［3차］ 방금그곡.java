import java.util.*;

class Solution {
    int getPlayTime(String[] from, String[] to) {
        return Integer.parseInt(to[0]) * 60 + Integer.parseInt(to[1]) - 
            (Integer.parseInt(from[0]) * 60 + Integer.parseInt(from[1]));   
    }
    
    String changeMelody(String melody) {
        melody = melody.replace("C#", "c");
        melody = melody.replace("D#", "d");
        melody = melody.replace("F#", "f");
        melody = melody.replace("G#", "g");
        melody = melody.replace("A#", "a");
        
        return melody;
    }
    
    public String solution(String m, String[] musicinfos) {
        List<Info> list = new ArrayList<>(); 
        
        for(String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            String[] fromTime = info[0].split(":");
            String[] toTime = info[1].split(":");
            String name = info[2];
            String melody = changeMelody(info[3]);
            
            String play = "";
            int playTime = getPlayTime(fromTime, toTime);
            
            if(playTime < melody.length())
                play = melody.substring(0, playTime);
            else {
                int quotient = playTime / melody.length();
                while(quotient > 0) {
                    play += melody;
                    quotient--;
                }
                
                int remain = playTime % melody.length();
                play += melody.substring(0, remain);
            }
            
            if(play.indexOf(changeMelody(m)) != -1)
                list.add(new Info(name, playTime));
        }
        
        if(list.size() == 0)
            return "(None)";
        
        int maxPlayTime = 0;
        String answer = "";
        for(Info info : list) {
            if(info.playTime > maxPlayTime) {
                answer = info.name;
                maxPlayTime = info.playTime;
            }
        }
        
        return answer;
    }
}

class Info {
    String name;
    int playTime;
    
    public Info(String name, int playTime) {
        this.name = name;
        this.playTime = playTime;
    }
}