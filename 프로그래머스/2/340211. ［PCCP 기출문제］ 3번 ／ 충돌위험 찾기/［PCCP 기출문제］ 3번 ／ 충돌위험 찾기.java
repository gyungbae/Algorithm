import java.util.*;

class Solution {
    int n, m, x;
    int[][] points, routes;

    Robot[] robots;

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        n = points.length;
        x = routes.length;
        m = routes[0].length;
        this.points = points;
        this.routes = routes;

        robots = new Robot[x];

        for (int robot = 0; robot < x; robot++) {
            int[] route = routes[robot];
            int[] startInfo = points[route[0] - 1];

            robots[robot] = new Robot(startInfo[0], startInfo[1], route);
            robots[robot].getDiff(points);
        }

        int completeCount = 0;

        while (completeCount < x) {
            Map<String, Integer> map = new HashMap<>();

            for (Robot robot : robots) {
                if (robot.completed) continue;

                String key = robot.row + "," + robot.col;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for (int count : map.values()) {
                if (count >= 2) answer++;
            }

            for (Robot robot : robots) {
                if (robot.completed) continue;

                if (robot.routeIdx >= m) {
                    robot.completed = true;
                    completeCount++;
                }
            }

            for (Robot robot : robots) {
                if (robot.completed) continue;

                robot.move();

                if (robot.rowDiff == 0 && robot.colDiff == 0) {
                    robot.routeIdx++;

                    if (robot.routeIdx < m) {
                        robot.getDiff(points);
                    }
                }
            }
        }

        return answer;
    }
}

class Robot {
    int row, col, routeIdx;
    int[] route;
    int rowDiff, colDiff;
    boolean completed;

    public Robot(int row, int col, int[] route) {
        this.row = row;
        this.col = col;
        this.route = route;
        routeIdx = 1;
    }

    void getDiff(int[][] points) {
        int[] pointInfo = points[route[routeIdx] - 1];

        rowDiff = pointInfo[0] - row;
        colDiff = pointInfo[1] - col;
    }

    void move() {
        if (rowDiff != 0) {
            if (rowDiff > 0) {
                row++;
                rowDiff--;
            } else {
                row--;
                rowDiff++;
            }
        } else if (colDiff != 0) {
            if (colDiff > 0) {
                col++;
                colDiff--;
            } else {
                col--;
                colDiff++;
            }
        }
    }
}