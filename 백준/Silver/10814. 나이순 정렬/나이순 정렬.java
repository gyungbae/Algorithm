import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<User> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new User(age, name));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (User user : list) {
            sb.append(user.age).append(" ").append(user.name).append("\n");
        }

        System.out.print(sb);
    }
}

class User implements Comparable<User> {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;   
    }
}
