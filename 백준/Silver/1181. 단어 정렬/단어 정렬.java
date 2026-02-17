import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();
        for (int input = 0; input < N; input++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>(set);
        Collections.sort(list, (o1, o2) -> {
            if (o1.length() != o2.length()) 
                return o1.length() - o2.length();
            
            return o1.compareTo(o2);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : list) 
            sb.append(s).append('\n');
        
        System.out.print(sb);
    }
}
