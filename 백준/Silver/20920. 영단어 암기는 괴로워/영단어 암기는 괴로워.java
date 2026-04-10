import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            countMap.put(input, countMap.getOrDefault(input, 0) + 1);
        }

        List<String> memorizeList = new ArrayList<>();
        for (String word : countMap.keySet()) {
            if(word.length() < M)
                continue;

            memorizeList.add(word);
        }

        Collections.sort(memorizeList, (o1, o2) -> {
            int count1 = countMap.get(o1);
            int count2 = countMap.get(o2);

            if (count1 == count2) {
                int length1 = o1.length();
                int length2 = o2.length();

                if (length1 == length2) {
                    return o1.compareTo(o2);
                }

                return length2 - length1;
            }

            return count2 - count1;
        });

        StringBuilder sb = new StringBuilder();
        for (String word : memorizeList) {
            sb.append(word).append("\n");
        }

        System.out.println(sb);
    }
}
