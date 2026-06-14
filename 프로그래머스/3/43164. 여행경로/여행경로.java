import java.util.*;

class Solution {
    String[][] tickets;
    int size;
    
    Map<String, List<Ticket>> map = new HashMap<>();
    boolean[] used;
    String answerRoute;
    int answerIdx;
    
    void search(int depth, String current, StringBuilder route) {
        if(depth == size) {
            String newRoute = route.toString();
            
            if(answerRoute == null || answerRoute.compareTo(newRoute) > 0) 
                answerRoute = newRoute;
            
            return;
        }
        
        if(!map.containsKey(current))
            return;
        
        for(Ticket next : map.get(current)) {
            int ticketIdx = next.ticketIdx;
            String to = next.to;
            
            if(used[ticketIdx])
                continue;
            
            int routeLength = route.length();
            used[ticketIdx] = true;
            search(depth + 1, to, route.append(to).append(","));
            used[ticketIdx] = false;
            route.setLength(routeLength);
        }
    }
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        size = tickets.length;
        
        for(int idx = 0; idx < size; idx++) {
            String[] ticket = tickets[idx];
            String from = ticket[0];
            String to = ticket[1];
            
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(new Ticket(from, to, idx));
        }
        
        used = new boolean[size];
        search(0, "ICN", new StringBuilder());
        
        String[] searchResult = answerRoute.split(",");
        String[] answer = new String[size + 1];
        answer[0] = "ICN";
        
        for(int idx = 1; idx <= size; idx++) {
            answer[idx] = searchResult[idx - 1];
        }
        
        return answer;
    }
}

class Ticket {
    String from, to;
    int ticketIdx;
    
    public Ticket(String from, String to, int ticketIdx) {
        this.from = from;
        this.to = to;
        this.ticketIdx = ticketIdx;
    }
}