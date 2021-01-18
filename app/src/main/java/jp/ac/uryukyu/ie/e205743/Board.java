package jp.ac.uryukyu.ie.e205743;

import java.util.Scanner;
/**
 * ボードクラス
 * int length; //横方向の長さ
 * int height; //縦方向の長さ
 * String board; 五目並べのボードの情報
 * String EMPRY; //何もない状態
 * String BLACK; //先手、黒の石
 * String WHITE; //後手、白の石
 * String[] turn; //ゲームの順番
 * boolean judge; //ゲームが終了しているかどうか
 */

public class Board {

    static final int length = 15; 
    static final int height = 15; 
    static String[][] board = new String[length][height]; 
    static final String EMPTY = "　"; 
    static final String BLACK = "⚫️"; 
    static final String WHITE = "⚪️"; 
    private String[] turn = { BLACK, WHITE }; 
    private boolean judge = false;

    public String[] getTurn() { return turn; }

    public boolean getJudge(){ return this.judge; }

    /**
     * 盤上を全部何も置かれていない状態にする
     */
    public void clearBoard() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = EMPTY;
            }
        }
    }

    /**
     * コンストラクタ　インスタンス化した時にはないも置かれていない
     */
    public Board() {
        clearBoard();
    }

    /**
     * ボードを表示する
     * 縦と横にはそれぞれメモリが表示される(0~14)
     */
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

    /**
     * 指定された座標に指定された色の石を置く
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     */
    public void putGoishi(int x, int y, String col) {
        if (col == BLACK || col == WHITE) {
            board[x][y] = col;
        }
    }

    /**
     * 入力した数字が想定されたボードの範囲内かどうかを確認する
     * @param cooedinate 入力した座標
     */
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

    /**
     * 指定された座標に何もないか確認する
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @return result 置いてもいいかどうか
     */
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

    /**
     * 入力した座標に石を置く
     * putGoishi()、checkBoard()、checkCoodinate()を使い正しく石が置かれるまで入力を繰り返す
     * @param col 石の色
     * @param scanner キーボードで入力するため
     */
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

    /**
     * 黒、白の順に石を置いていき、ボードを表示する
     */
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
    }

    /*
    public String nextValue(int firstValue, int yValue){
        String next = board[firstValue][yValue + 1];
        return next;
    }

    public int serchX(){
        int num = 1;
        String next;
        for (int x = 0; x < length; x ++){
            for (int y = 0; y < height; y ++){
                if(board[x][y] != EMPTY){
                    next = nextValue(x, y);
                    if(next == EMPTY){
                        continue;
                    }
                    if(board[x][y] == next){

                    }
                }
            }
        }
        return num;
    }
    */

    
    /**
     * 指定した座標の右隣に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkRight(int x, int y, String col, int i) {
        boolean result = false;
        String right;
        if (x < length) {
            right = board[x + i][y];
            if (right == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の左隣に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkLeft(int x, int y, String col, int i) {
        boolean result = false;
        String left;
        if (x > 0) {
            left = board[x - i][y];
            if (left == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の上に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkUp(int x, int y, String col, int i) {
        boolean result = false;
        String up;
        if (y > 0) {
            up = board[x][y - i];
            if (up == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の下に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkDown(int x, int y, String col, int i) {
        boolean result = false;
        String down;
        if (y < height) {
            down = board[x][y + 1];
            if (down == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の右斜め上に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkUpRCorner(int x, int y, String col, int i) {
        boolean result = false;
        String upRCorner;
        if (y < height) {
            upRCorner = board[x + 1][y - 1];
            if (upRCorner == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の左斜め上に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkUpLCorner(int x, int y, String col, int i) {
        boolean result = false;
        String upLCorner;
        if (y < height) {
            upLCorner = board[x - 1][y - 1];
            if (upLCorner == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 指定した座標の右斜め下に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkLowRCorner(int x, int y, String col, int i) {
        boolean result = false;
        String lowRCorner = board[x + 1][y - 1];
        if (y < height) {
            if (lowRCorner == board[x][y]) {
                result = true;
            }
        }
        return result;
    }
    /**
     * 指定した座標の左斜め下に同じ色の石があるか調べる
     * @param x 横方向の座標
     * @param y　縦方向の座標
     * @param col　石の色
     * @param i 何番目隣なのか
     * @return result 同じ石があるかどうか
     */
    public boolean checkLowLCorner(int x, int y, String col, int i) {
        boolean result = false;
        String lowLCorner = board[x + 1][y - 1];
        if (y < height) {
            if (lowLCorner == board[x][y]) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 横方向に同じ色の石がつながっているかを調べる
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     * @return numX つながっている石の数
     */
    public int countX(int x, int y, String col) {
        int numX = 1;
        int num1 = 1;
        int num2 = 1;
        while (true) {
            if (checkRight(x, y, col, num1)) {
                numX++;
                num1++;
            } else {
                break;
            }
        }
        while (true) {
            if (checkLeft(x, y, col, num2)) {
                numX++;
                num2++;
            } else {
                break;
            }
        }
        return numX;
    }

    /**
     * 縦方向に同じ色石がつながっているかを調べる
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     * @return numX つながっている石の数
     */
    public int countY(int x, int y, String col) {
        int numY = 1;
        int num1 = 1;
        int num2 = 1;
        while (true) {
            if (checkUp(x, y, col, num1)) {
                numY++;
                num1++;
            } else {
                break;
            }
        }
        while (true) {
            if (checkDown(x, y, col, num2)) {
                numY++;
                num2++;
            } else {
                break;
            }
        }
        return numY;
    }

    /**
     * 右斜め上から左下にかけて同じ色の石がつながっているかを調べる
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     * @return numX つながっている石の数
     */
    public int countRUpCorner(int x, int y, String col) {
        int numRUpCorner = 1;
        int num1 = 1;
        int num2 = 1;
        while (true) {
            if (checkUpRCorner(x, y, col, num1)) {
                numRUpCorner++;
                num1++;
            } else {
                break;
            }
        }
        while (true) {
            if (checkLowLCorner(x, y, col, num2)) {
                numRUpCorner++;
                num2++;
            } else {
                break;
            }
        }
        return numRUpCorner;
    }

    /**
     * 左斜め上から右下にかけて同じ色の石がつながっているかを調べる
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     * @return numX つながっている石の数
     */
    public int countRLowCorner(int x, int y, String col) {
        int numRLowCorner = 1;
        int num1 = 1;
        int num2 = 1;
        while (true) {
            if (checkUpLCorner(x, y, col, num1)) {
                numRLowCorner++;
                num1++;
            } else {
                break;
            }
        }
        while (true) {
            if (checkLowRCorner(x, y, col, num2)) {
                numRLowCorner++;
                num2++;
            } else {
                break;
            }
        }
        return numRLowCorner;
    }

    /**
     * 5個以上同じ色のいしがつながっているか調べる
     * @param x 横方向の座標
     * @param y 縦方向の座標
     * @param col 石の色
     * @return judge 5個以上あるかどうか
     */
    public boolean judge(int x, int y, String col) {
        int numX = countX(x, y, col);
        int numY = countY(x, y, col);
        int numRUpCorner = countRUpCorner(x, y, col);
        int numRLowCorner = countRUpCorner(x, y, col);
        System.out.println(numX);
        System.out.println(numY);
        System.out.println(numRUpCorner);
        System.out.println(numRLowCorner);
        if (numX >= 5 || numY >= 5 || numRUpCorner >= 5 || numRLowCorner >= 5) {
            System.out.println(numX + numY + numRUpCorner + numRLowCorner);
            this.judge = true;
        } else {
            this.judge = false;
        }
        return this.judge;
    }
}