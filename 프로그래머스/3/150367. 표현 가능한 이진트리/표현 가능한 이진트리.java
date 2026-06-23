class Solution {
    boolean check(String binary, int left, int right) {
        if(left == right)
            return true;

        int mid = (left + right) / 2;

        int leftRoot = (left + mid - 1) / 2;
        int rightRoot = (mid + 1 + right) / 2;

        if(binary.charAt(mid) == '0') {
            if(binary.charAt(leftRoot) == '1')
                return false;

            if(binary.charAt(rightRoot) == '1')
                return false;
        }

        return check(binary, left, mid - 1)
            && check(binary, mid + 1, right);
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int numberIdx = 0; numberIdx < numbers.length; numberIdx++) {

            String binary = Long.toBinaryString(numbers[numberIdx]);

            int treeSize = 1;

            while(treeSize < binary.length()) {
                treeSize = treeSize * 2 + 1;
            }

            while(binary.length() < treeSize) {
                binary = "0" + binary;
            }

            answer[numberIdx] =
                check(binary, 0, treeSize - 1) ? 1 : 0;
        }

        return answer;
    }
}