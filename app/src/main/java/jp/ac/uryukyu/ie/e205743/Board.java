package jp.ac.uryukyu.ie.e205743;

import java.util.Scanner;

public class Board {

    static final int length = 15; // 横方向の長さ
    static final int height = 15; // 縦方向の長さ
    static String[][] board = new String[length][height]; // 五目並べのボード
    static final String EMPTY = "　"; // 何も置かれていない時の中身
    static final String BLACK = "⚫️"; // 先手の色
    static final String WHITE = "⚪️"; // 後手の色
    private String[] turn = { BLACK, WHITE }; // 順番

    // 盤上を全部何も置かれていない状態にする
    public void clearBoard() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = EMPTY;
            }
        }
    }

    // インスタンス化した時にはないも置かれていない
    public Board() {
        clearBoard();
    }

    // ボードを表示する
    // 縦と横にはそれぞれメモリが表示される(0~14)
    public void outputBoard() {
        int numLength = 0;
        System.out.println("  |０|１|２|３|４|５|６|７|８|９|10|11|12|13|14|");
        if (numLength >= 14) {
            System.out.println("");
        }
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < height; x++) {
                if (x == 0) {
                    if (y <= 9) {
                        System.out.print(y + " |");
                    }
                    if (y > 9) {
                        System.out.print(y + "|");
                    }
                }
                System.out.printf(board[x][y] + "|");
                numLength++;
            }
            if (numLength >= 14) {
                System.out.println("");
            }
        }

    }

    // 指定された座標に指定された色の石を置く
    public void putGoishi(int x, int y, String col) {
        if (col == BLACK || col == WHITE) {
            board[x][y] = col;
        }
    }

    // 入力した数字が想定されたボードの範囲内かどうかを確認する
    public boolean checkCoordinate(int coordinate) {
        boolean result;
        if (coordinate <= length || coordinate <= height) {
            result = true;
        } else {
            System.out.println("その位置に石はおけません");
            result = false;
        }
        return result;
    }

    // 指定された座標に何もないか確認する
    public boolean checkBoard(int x, int y) {
        boolean result;
        if (board[x][y] == EMPTY) {
            result = true;
        } else {
            System.out.println("その位置にはすでに置かれています");
            result = false;
        }
        return result;
    }

    //入力した座標に石を置く
    //putGoishi()、checkBoard()、checkCoodinate()を使い正しく石が置かれるまで入力を繰り返す
    public void inputCoordinate(String col, Scanner scanner) {
        int xCoordinate;
        int yCoordinate;
        while (true) {
            while (true) {
                System.out.println("x座標を入力してください");
                xCoordinate = scanner.nextInt();
                if (checkCoordinate(xCoordinate)) {
                    break;
                } else {
                    continue;
                }
            }
            while (true) {
                System.out.println("y座標を入力してください");
                yCoordinate = scanner.nextInt();
                if (checkCoordinate(yCoordinate)) {
                    break;
                } else {
                    continue;
                }
            }
            if (checkBoard(xCoordinate, yCoordinate)) {
                putGoishi(xCoordinate, yCoordinate, col);
                break;
            }
        }
    }

    //先手後手の順に石を置いていき、ボードを表示する
    public void turn() {
        Scanner scanner = new Scanner(System.in);
        for (String col : turn) {
            if (col == BLACK) {
                System.out.println("先手の番です");
            }
            if (col == WHITE) {
                System.out.println("後手の番です");
            }
            inputCoordinate(col, scanner);
            outputBoard();
        }
        scanner.close();
    }
}
