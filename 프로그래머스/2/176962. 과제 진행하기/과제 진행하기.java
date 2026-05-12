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
        
        for(int idx = 0; idx < size - 1; idx++) {
            Task current = tasks[idx];
            Task next = tasks[idx + 1];
            
            stack.push(current);
            
            int remainTime = next.start - current.start;
            
            while(!stack.isEmpty() && remainTime > 0) {
                Task task = stack.peek();
                
                if(task.playtime <= remainTime) {
                    remainTime -= task.playtime;
                    finishList.add(stack.pop());
                } else {
                    task.playtime -= remainTime;
                    remainTime = 0;
                }
            }
        }
        
        stack.push(tasks[size - 1]);

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