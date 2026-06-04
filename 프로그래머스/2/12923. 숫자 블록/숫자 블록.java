class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];

        int answerIdx = 0;

        for(long number = begin; number <= end; number++) {

            if(number == 1) {
                answer[answerIdx++] = 0;
                continue;
            }

            int blockNumber = 1;

            for(long divisor = 2; divisor * divisor <= number; divisor++) {

                if(number % divisor != 0)
                    continue;

                long pairDivisor = number / divisor;

                if(pairDivisor <= 10000000) {
                    blockNumber = (int)pairDivisor;
                    break;
                }

                blockNumber = (int)divisor;
            }

            answer[answerIdx++] = blockNumber;
        }

        return answer;
    }
}