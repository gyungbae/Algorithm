class Solution {
    
    int gcd(int firstNumber, int secondNumber) {
        while(secondNumber != 0) {
            int remainder = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = remainder;
        }

        return firstNumber;
    }
    
    int lcm(int firstNumber, int secondNumber) {
        return firstNumber / gcd(firstNumber, secondNumber) * secondNumber;
    }
    
    public int solution(int[] arr) {
        int answer = arr[0];
        
        for(int arrIdx = 1; arrIdx < arr.length; arrIdx++) {
            answer = lcm(answer, arr[arrIdx]);
        }
        
        return answer;
    }
}
