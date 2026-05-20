class Solution {
    int getGcd(int num1, int num2) {
        while(num2 != 0) {
            int remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        
        return num1;
    }
    
    boolean canDivide(int divisor, int[] arr) {
        for(int idx = 0; idx < arr.length; idx++) {
            if(arr[idx] % divisor == 0)
                return false;
        }
        
        return true;
    }
    
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        for(int idx = 1; idx < arrayA.length; idx++) {
            gcdA = getGcd(gcdA, arrayA[idx]);
        }
        
        int gcdB = arrayB[0];
        for(int idx = 1; idx < arrayB.length; idx++) {
            gcdB = getGcd(gcdB, arrayB[idx]);
        }
        
        int answer = 0;
        
        if(canDivide(gcdA, arrayB))
            answer = gcdA;
        
        if(canDivide(gcdB, arrayA))
            answer = Math.max(gcdB, answer);
        
        return answer;
    }
}