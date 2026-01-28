import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();
        for (int input = 1; input <= N; input++) {
            trie.insert(br.readLine());
        }

        int answer = 0;
        for (int input = 1; input <= M; input++) {
            if (trie.search(br.readLine())) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}

class TrieNode {
    TrieNode[] childArr = new TrieNode[26];
    boolean isEnd;
}

class Trie {
    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode current = root;
        for (char character : word.toCharArray()) {
            int idx = character - 'a';
            if (current.childArr[idx] == null) {
                current.childArr[idx] = new TrieNode();
            }
            current = current.childArr[idx];
        }

        current.isEnd = true;
    }

    boolean search(String word) {
        TrieNode current = root;
        for (char character : word.toCharArray()) {
            int idx = character - 'a';
            if (current.childArr[idx] == null) {
                return false;
            }
            current = current.childArr[idx];
        }

        return current.isEnd;
    }
}
