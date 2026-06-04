class Solution {
    public int solution(String s) {
        int answer = s.length();

        for(int size = 1; size <= s.length() / 2; size++) {
            StringBuilder sb = new StringBuilder();

            String prev = s.substring(0, size);
            int count = 1;

            for(int fromIdx = size; fromIdx + size <= s.length(); fromIdx += size) {
                String current = s.substring(fromIdx, fromIdx + size);

                if(prev.equals(current)) {
                    count++;
                } else {
                    if(count == 1)
                        sb.append(prev);
                    else
                        sb.append(count).append(prev);

                    prev = current;
                    count = 1;
                }

                if(sb.length() >= answer)
                    break;
            }

            if(count == 1)
                sb.append(prev);
            else
                sb.append(count).append(prev);

            int remainStartIdx = s.length() - (s.length() % size);

            if(remainStartIdx < s.length())
                sb.append(s.substring(remainStartIdx));

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}