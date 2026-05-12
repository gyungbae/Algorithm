class Solution {
    public int[] solution(int[] sequence, int k) {
        int size = sequence.length;
        
        int left = 0; 
        int right = -1;
        int sum = 0;
        
        int answerLeft = 0;
        int answerRight = size - 1;
        
        while(true) {
            if(sum >= k) {
                if(sum == k && right - left < answerRight - answerLeft) {
                    answerLeft = left;
                    answerRight = right;
                }
                
                sum -= sequence[left];
                left++;
            } else {
                if(right == size - 1)
                    break;
                
                right++;
                sum += sequence[right];
            }
        }
        
        return new int[]{answerLeft, answerRight};
    }
}