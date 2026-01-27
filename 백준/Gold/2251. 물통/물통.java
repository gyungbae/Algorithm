import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C;

    static boolean[][] visited;
    static List<Integer> answer = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void DFS(int currentA, int currentB, int currentC) {
        visited[currentA][currentB] = true;

        if (currentA == 0 && !answer.contains(currentC)) {
            answer.add(currentC);
        }

        int pour = 0;
        if (currentA != 0) {
            pour = calculate('A', 'B', currentA, currentB);
            if (!visited[currentA - pour][currentB + pour]) {
                DFS(currentA - pour, currentB + pour, currentC);
            }

            pour = calculate('A', 'C', currentA, currentC);
            if (!visited[currentA - pour][currentB]) {
                DFS(currentA - pour, currentB, currentC + pour);
            }
        }

        if (currentB != 0) {
            pour = calculate('B', 'A', currentB, currentA);
            if (!visited[currentA + pour][currentB - pour]) {
                DFS(currentA + pour, currentB - pour, currentC);
            }

            pour = calculate('B', 'C', currentB, currentC);
            if (!visited[currentA][currentB - pour]) {
                DFS(currentA, currentB - pour, currentC + pour);
            }
        }

        if (currentC != 0) {
            pour = calculate('C', 'A', currentC, currentA);
            if (!visited[currentA + pour][currentB]) {
                DFS(currentA + pour, currentB, currentC - pour);
            }

            pour = calculate('C', 'B', currentC, currentB);
            if (!visited[currentA][currentB + pour]) {
                DFS(currentA, currentB + pour, currentC - pour);
            }
        }
    }

    static int calculate(char from, char to, int fromLiter, int toLiter) {
        int limit = 0;
        switch (to) {
            case 'A' -> limit = A;
            case 'B' -> limit = B;
            default -> limit = C;
        }

        int pour = 0;
        if (fromLiter + toLiter <= limit) {
            pour = fromLiter;
        } else {
            pour = limit - toLiter;
        }

        return  pour;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1];
        DFS(0, 0, C);

        Collections.sort(answer);
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
