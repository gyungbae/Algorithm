class Solution {
    int[] numbers;
    int target;
    
    int answer;
    
    void search(int depth, int sum) {
        if(depth == numbers.length) {
            if(sum == target)
                answer++;
            
            return;
        }
        
        search(depth + 1, sum + numbers[depth]);
        search(depth + 1, sum - numbers[depth]);
    }
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        search(0, 0);
        
        return answer;
    }
}