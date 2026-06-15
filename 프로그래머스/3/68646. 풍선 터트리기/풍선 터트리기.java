class Solution {
    public int solution(int[] a) {
        int size = a.length;
        
        int[] leftMin = new int[size];
        int[] rightMin = new int[size];
        
        leftMin[0] = a[0];
        for(int idx = 1; idx < size; idx++) {
            leftMin[idx] = Math.min(leftMin[idx - 1], a[idx]);
        }
        
        rightMin[size - 1] = a[size - 1];
        for(int idx = size - 2; idx >= 0; idx--) {
            rightMin[idx] = Math.min(rightMin[idx + 1], a[idx]);
        }
        
        int answer = 0;
        
        for(int idx = 0; idx < size; idx++) {
            if(a[idx] <= leftMin[idx] || a[idx] <= rightMin[idx])
                answer++;
        }
        
        return answer;
    }
}