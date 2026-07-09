import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> runnerCountMap = new HashMap<>();

        for (String runnerName : participant) {
            runnerCountMap.put(runnerName, runnerCountMap.getOrDefault(runnerName, 0) + 1);
        }

        for (String runnerName : completion) {
            runnerCountMap.put(runnerName, runnerCountMap.get(runnerName) - 1);
        }

        for (String runnerName : runnerCountMap.keySet()) {
            if (runnerCountMap.get(runnerName) > 0) {
                return runnerName;
            }
        }

        return "";
    }
}