import java.util.*; 

class Solution {
    int convert(String time) {
        String[] timeArr = time.split(":");
        
        return Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
    }
    
    int calculate(int[] fees, Info info) {
        int result = fees[1];
        
        int parkTime = info.parkTime;
        
        if(parkTime <= fees[0])
            return result;
        
        parkTime -= fees[0];
        
        int quotient = parkTime / fees[2];  
        
        if(parkTime % fees[2] != 0) {
            quotient++; 
        }
        
        result += fees[3] * quotient;
        
        return result;
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Info> map = new HashMap<>();
        
        for(String record : records) {
            String[] recordArr = record.split(" ");
            String time = recordArr[0];
            String num = recordArr[1];
            String state = recordArr[2];
            
            if(state.equals("IN")) {
                if(map.containsKey(num)) {
                    Info info = map.get(num);
                    info.out = false;
                    info.inTime = convert(time);
                } else {
                    map.put(num, new Info(num, convert(time)));
                }
            } else {
                Info info = map.get(num);
                info.getOut(convert(time));
            }
        }
        
        Info[] infos = map.values().toArray(new Info[0]);
        Arrays.sort(infos, (o1, o2) -> o1.num.compareTo(o2.num));
        
        int[] answer = new int[infos.length];
        for(int idx = 0; idx < infos.length; idx++) {
            Info info = infos[idx];
            
            if(!info.out)
                info.getOut(convert("23:59"));
                
            answer[idx] = calculate(fees, info);
        }
        
        return answer;
    }
}

class Info {
    String num;
    int inTime;
    boolean out;
    int parkTime;
    
    public Info(String num, int inTime) {
        this.num = num;
        this.inTime = inTime;
    }
    
    void getOut(int outTime) {
        this.out = true;
        parkTime += outTime - this.inTime;
    }
}