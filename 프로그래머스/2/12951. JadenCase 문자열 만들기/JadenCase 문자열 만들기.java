class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        boolean isStart = true; 
        
        for (char ch : s.toCharArray()) {
            if (isStart) {
                if (Character.isLetter(ch)) {
                    sb.append(Character.toUpperCase(ch));
                } else {
                    sb.append(ch);
                }
                isStart = false;
            } else {

                if (Character.isLetter(ch)) {
                    sb.append(Character.toLowerCase(ch));
                } else {
                    sb.append(ch);
                }
            }

            if (ch == ' ') {
                isStart = true;
            }
        }
        
        return sb.toString();
    }
}