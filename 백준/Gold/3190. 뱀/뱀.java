import java.io.*;
import java.util.*;

/**
 * 뱀 - (0, 0)시작, 길이 1, 방향 오른쪽
 * 1. 머리를 다음 칸에 위치
 *  1-1. 벽이나 몸에 부딪히면 종료
 *  1-2. 사과가 있으면 사과를 먹고 길이 증가(꼬리 그대로)
 * 2. 꼬리 다음 칸 위치
 * 방향전환 - X초가 끝난 뒤 C로 전환
 * 몇 초에 끝?
 */
public class Main {
    static int N, K, L;

    static int[][] map;
    static int[] deltaRow = {0, -1, 0, 1};
    static int[] deltaCol = {1, 0, -1, 0};
    static Snake snake;
    static int second;
    static Queue<Order> orderQueue = new ArrayDeque<>();
    static Queue<Integer> directionQueue = new ArrayDeque<>();

    static void move() {
        second++;
        int nextRow = snake.headRow + deltaRow[snake.direction];
        int nextCol = snake.headCol + deltaCol[snake.direction];

        if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
            return;
        }

        if(map[nextRow][nextCol] == 1) {
            return;
        }

        boolean ateApple = false;
        if(map[nextRow][nextCol] == 2)
            ateApple = true;

        snake.headRow = nextRow;
        snake.headCol = nextCol;
        map[nextRow][nextCol] = 1;
        directionQueue.offer(snake.direction);

        if(!ateApple) {
            int tailDirection = directionQueue.poll();
            map[snake.tailRow][snake.tailCol] = 0;
            snake.tailRow += deltaRow[tailDirection];
            snake.tailCol += deltaCol[tailDirection];
        }

        if(!orderQueue.isEmpty() && orderQueue.peek().second == second) {
            turn(orderQueue.poll());
        }

        move();
    }

    static void turn(Order order) {
        if (order.direction == 'L') {
            snake.direction = (snake.direction + 1) % 4;
        } else {
            snake.direction = (snake.direction + 3) % 4;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];    //사과 2, 뱀 1
        for (int apple = 1; apple <= K; apple++) {
            String[] input = br.readLine().split(" ");
            int row = Integer.parseInt(input[0]) - 1;
            int col = Integer.parseInt(input[1]) - 1;

            map[row][col] = 2;
        }

        L = Integer.parseInt(br.readLine());    //L 왼쪽, D 오른쪽
        for (int order = 1; order <= L; order++) {
            String[] input = br.readLine().split(" ");
            int second = Integer.parseInt(input[0]);
            char direction = input[1].charAt(0);

            orderQueue.offer(new Order(second, direction));
        }

        snake = new Snake();
        map[0][0] = 1;
        move();

        System.out.println(second);
    }
}

class Snake {
    int headRow, headCol, tailRow, tailCol;
    int direction;

}

class Order {
    int second;
    char direction;

    public Order(int second, char direction) {
        this.second = second;
        this.direction = direction;
    }
}
