import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] colorMap; //0(흰) 1(빨) 2(파)

    static List<Piece> pieceList = new ArrayList<>();
    static List<Piece>[][] pieceMap;
    static int[] deltaRow = {0, 0, 0, -1, 1};
    static int[] deltaCol = {0, 1, -1, 0, 0};
    static int answer;

    static int move(Piece piece, int color, int nextRow, int nextCol) {
        return switch (color) {
            case 0 -> moveWhite(piece, nextRow, nextCol).size();
            default -> moveRed(piece, nextRow, nextCol).size();
        };
    }

    static List<Piece> moveWhite(Piece piece, int nextRow, int nextCol) {
        List<Piece> moveList = new ArrayList<>();

        int prevRow = piece.row;
        int prevCol = piece.col;
        List<Piece> prevList = pieceMap[prevRow][prevCol];

        int stdIdx = 0;
        for (int idx = 0; idx < prevList.size(); idx++) {
            if (prevList.get(idx).equals(piece)) {
                stdIdx = idx;
                break;
            }
        }

        pieceMap[prevRow][prevCol] = new ArrayList<>(prevList.subList(0, stdIdx));

        for(int idx = stdIdx; idx < prevList.size(); idx++) {
            moveList.add(prevList.get(idx));
        }

        List<Piece> nextList = pieceMap[nextRow][nextCol];
        for (Piece movePiece : moveList) {
            movePiece.row = nextRow;
            movePiece.col = nextCol;
            nextList.add(movePiece);
        }

        pieceMap[nextRow][nextCol] = nextList;

        return nextList;
    }

    static List<Piece> moveRed(Piece piece, int nextRow, int nextCol) {
        List<Piece> moveList = new ArrayList<>();

        int prevRow = piece.row;
        int prevCol = piece.col;
        List<Piece> prevList = pieceMap[prevRow][prevCol];

        int stdIdx = 0;
        for (int idx = 0; idx < prevList.size(); idx++) {
            if (prevList.get(idx).equals(piece)) {
                stdIdx = idx;
                break;
            }
        }

        pieceMap[prevRow][prevCol] = new ArrayList<>(prevList.subList(0, stdIdx));

        for(int idx = prevList.size() - 1; idx >= stdIdx; idx--) {
            moveList.add(prevList.get(idx));
        }

        List<Piece> nextList = pieceMap[nextRow][nextCol];
        for (Piece movePiece : moveList) {
            movePiece.row = nextRow;
            movePiece.col = nextCol;
            nextList.add(movePiece);
        }

        pieceMap[nextRow][nextCol] = nextList;

        return nextList;
    }

    static void reverseDirection(Piece piece) {
        int direction = piece.direction;
        if(direction == 1 || direction == 3) {
            direction++;
        } else {
            direction--;
        }

        piece.direction = direction;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        colorMap = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                colorMap[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        pieceMap = new ArrayList[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                pieceMap[row][col] = new ArrayList<>();
            }
        }

        for (int input = 1; input <= K; input++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            Piece piece = new Piece(row, col, direction);
            pieceMap[row][col].add(piece);
            pieceList.add(piece);
        }

        loop : while (true) {
            answer++;
            for (Piece piece : pieceList) {
                int nextRow = piece.row + deltaRow[piece.direction];
                int nextCol = piece.col + deltaCol[piece.direction];

                if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > N || colorMap[nextRow][nextCol] == 2) {
                    reverseDirection(piece);

                    nextRow = piece.row + deltaRow[piece.direction];
                    nextCol = piece.col + deltaCol[piece.direction];

                    if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > N || colorMap[nextRow][nextCol] == 2)
                        continue;
                }

                int result = move(piece, colorMap[nextRow][nextCol], nextRow, nextCol);
                if (result >= 4 || answer > 1000)
                    break loop;
            }
        }

        System.out.println(answer > 1000 ? -1 : answer);
    }
}

class Piece {
    int row, col, direction;

    public Piece(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
}
