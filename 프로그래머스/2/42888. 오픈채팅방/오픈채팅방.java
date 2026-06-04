import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        Queue<String[]> queue = new ArrayDeque<>();

        for (String log : record) {
            String[] info = log.split(" ");

            String act = info[0];
            String id = info[1];

            if (!act.equals("Leave")) 
                map.put(id, info[2]);

            if (!act.equals("Change")) 
                queue.offer(new String[]{id, act});
        }

        String[] answer = new String[queue.size()];
        int answerIdx = 0;

        while (!queue.isEmpty()) {
            String[] info = queue.poll();

            String nickName = map.get(info[0]);
            String act = info[1];

            if (act.equals("Enter")) {
                answer[answerIdx++] = nickName + "님이 들어왔습니다.";
            } else {
                answer[answerIdx++] = nickName + "님이 나갔습니다.";
            }
        }

        return answer;
    }
}