import jp.ac.uryukyu.ie.e205743.*;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        String[] turn = board.getTurn();
        board.outputBoard();
        while(true){
            board.turn();
        }


    }
    
}
