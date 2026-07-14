class Solution {
    public String solution(int n, int t, int m, int p) {
        int maxLength = m * t;
        
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while(sb.length() < maxLength) {
            sb.append(Integer.toString(num++, n).toUpperCase());
        }
        
        String str = sb.toString();
        StringBuilder answer = new StringBuilder();
        int strIdx = p - 1;
        while(t > 0) {
            answer.append(str.charAt(strIdx));
            strIdx += m;
            t--;
        }
        
        return answer.toString();
    }
}