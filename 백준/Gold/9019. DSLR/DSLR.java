import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int A, B;

    static StringBuilder sb = new StringBuilder();

    static int D(int num) {
        return (num * 2) % 10000;
    }

    static int S(int num) {
        return num == 0 ? 9999 : num - 1;
    }

    static int L(int num) {
        return (num % 1000) * 10 + (num / 1000);
    }

    static int R(int num) {
        return (num % 10) * 1000 + (num / 10);
    }

    static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[10000];
        int[] previousArr = new int[10000];
        char[] commandArr = new char[10000];
        Arrays.fill(previousArr, -1);

        queue.add(A);
        visited[A] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == B) {
                break;
            }

            int next = D(current);
            if (!visited[next]) {
                visited[next] = true;
                previousArr[next] = current;
                commandArr[next] = 'D';
                queue.add(next);
            }

            next = S(current);
            if (!visited[next]) {
                visited[next] = true;
                previousArr[next] = current;
                commandArr[next] = 'S';
                queue.add(next);
            }

            next = L(current);
            if (!visited[next]) {
                visited[next] = true;
                previousArr[next] = current;
                commandArr[next] = 'L';
                queue.add(next);
            }

            next = R(current);
            if (!visited[next]) {
                visited[next] = true;
                previousArr[next] = current;
                commandArr[next] = 'R';
                queue.add(next);
            }
        }

        int current = B;
        StringBuilder answer = new StringBuilder();
        while (current != A) {
            answer.append(commandArr[current]);
            current = previousArr[current];
        }

        sb.append(answer.reverse()).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            BFS();
        }

        System.out.println(sb);
    }
}
