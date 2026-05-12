import java.util.*;

class Solution {
    Task convert(String[] plan) {
        String time = plan[1];
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3,5));
        
        return new Task(plan[0], hour * 60 + min, Integer.parseInt(plan[2]));
    }
    
    public String[] solution(String[][] plans) {
        int size = plans.length;
        
        Task[] tasks = new Task[size];
        for(int idx = 0; idx < size; idx++) {
            String[] plan = plans[idx];
            tasks[idx] = convert(plan);           
        }
        
        Arrays.sort(tasks, (o1, o2) -> o1.start - o2.start);
        
        Stack<Task> stack = new Stack<>();
        List<Task> finishList = new ArrayList<>();
        
        int nextIdx = 0;
        int currentTime = 0;
        
        while(nextIdx < size) {
            if(stack.isEmpty()) {
                stack.push(tasks[nextIdx++]);
                currentTime = stack.peek().start;
                continue;
            }
            
            Task next = tasks[nextIdx];
            int remainTime = next.start - currentTime;
            
            while(!stack.isEmpty() && remainTime > 0) {
                Task current = stack.peek();
                
                if(current.playtime <= remainTime) {
                    remainTime -= current.playtime;
                    finishList.add(stack.pop());
                } else {
                    current.playtime -= remainTime;
                    remainTime = 0;
                }
            }
            
            currentTime = next.start;
            stack.push(next);
            nextIdx++;
        }
        
        while (!stack.isEmpty()) {
            finishList.add(stack.pop());
        }

        String[] answer = new String[size];
        for (int idx = 0; idx < size; idx++) {
            answer[idx] = finishList.get(idx).name;
        }

        return answer;
    }
}

class Task {
    String name;
    int start, playtime;
    
    public Task(String name, int start, int playtime) {
        this.name = name;
        this.start = start;
        this.playtime = playtime;
    }
}