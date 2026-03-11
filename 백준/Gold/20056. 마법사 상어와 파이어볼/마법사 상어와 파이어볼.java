import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;

    static List<Fireball>[][] map;
    static int[] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] deltaCol = {0, 1, 1, 1, 0, -1, -1, -1};

    static void move() {
        List<Fireball>[][] nextMap = new ArrayList[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                nextMap[row][col] = new ArrayList<>();
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                for (Fireball fireball : map[row][col]) {
                    int nextRow = (row + deltaRow[fireball.direction] * (fireball.speed % N) + N) % N;
                    int nextCol = (col + deltaCol[fireball.direction] * (fireball.speed % N) + N) % N;

                    nextMap[nextRow][nextCol].add(fireball);
                }
            }
        }

        map = nextMap;
    }

    static void mergeAndSplit() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                List<Fireball> list = map[row][col];

                if(list.size() < 2)
                    continue;

                int massSum = 0;
                int speedSum = 0;
                boolean allOdd = true;
                boolean allEven = true;
                for (Fireball fireball : list) {
                    massSum += fireball.mass;
                    speedSum += fireball.speed;

                    if (fireball.direction % 2 == 0) {
                        allOdd = false;
                    } else {
                        allEven = false;
                    }
                }


                int newMass = massSum / 5;
                int newSpeed = speedSum / list.size();
                int newDirection = (allOdd || allEven) ? 0 : 1;

                list.clear();
                if(newMass == 0) {
                    continue;
                }

                for (int direction = newDirection; direction < 8; direction += 2) {
                    list.add(new Fireball(newMass, newSpeed, direction));
                }
            }
        }
    }

    static int getMassSum() {
        int sum = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                for (Fireball fireball : map[row][col]) {
                    sum += fireball.mass;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] = new ArrayList<>();
            }
        }

        for (int input = 1; input <= M; input++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            map[row][col].add(new Fireball(mass, speed, direction));
        }

        while (K-- > 0) {
            move();
            mergeAndSplit();
        }

        System.out.println(getMassSum());
    }
}

class Fireball {
    int mass, speed, direction;

    public Fireball(int mass, int speed, int direction) {
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
    }
}
