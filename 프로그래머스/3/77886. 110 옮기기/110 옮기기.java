class Solution {
    String convert(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for(int idx = 0; idx < input.length(); idx++) {
            sb.append(input.charAt(idx));
            
            int size = sb.length();
            
            if(size >= 3 && sb.charAt(size - 3) == '1' && sb.charAt(size - 2) == '1' && 
              sb.charAt(size - 1) == '0') {
                sb.delete(size - 3, size);
                count++;
            }
        }
        
        int insertIdx = sb.lastIndexOf("0") + 1;
        
        StringBuilder result = new StringBuilder();
        
        result.append(sb.substring(0, insertIdx));
        
        while(count > 0) {
            result.append("110");
            count--;
        }
        
        result.append(sb.substring(insertIdx));
        
        return result.toString();
    }

    public String[] solution(String[] s) {
        int size = s.length;
        String[] answer = new String[size];

        for(int idx = 0; idx < size; idx++) {
            answer[idx] = convert(s[idx]);
        }

        return answer;
    }
}