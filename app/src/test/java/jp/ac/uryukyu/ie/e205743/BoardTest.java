package jp.ac.uryukyu.ie.e205743;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {
    
    @Test
    void judgeTest(){
        Board board = new Board();
        boolean correct = true;
        int firstValue = 0;
        int yValue = 1;
        String col = board.BLACK;
        for(int i = 0; i < 5; i ++){
            board.putGoishi(i, yValue, col);
        }
        assertEquals(correct, board.judge(firstValue, yValue, col));
    }
}
