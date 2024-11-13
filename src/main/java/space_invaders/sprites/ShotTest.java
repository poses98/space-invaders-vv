package space_invaders.sprites;

import main.Commons;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ShotTest {
    Shot shot;


    @org.junit.jupiter.api.Test
    void cp_1_initShot(){
        int x = 7;
        int y = 5;
        int expected_x=13;
        int expected_y=4;
        shot = new Shot(x,y);
        assertEquals(expected_x,shot.getX());
        assertEquals(expected_y,shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_2_initShot() {
        int x = 0;
        int y = 200;
        int expected_x = 6;
        int expected_y = 199;
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_3_initShot() {
        int x = 400;
        int y = 400;
        int expected_x = 356; // Max BOARD_WIDTH
        int expected_y = 350; // Max BOARD_HEIGHT
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_4_initShot() {
        int x = 355;
        int y = 200;
        int expected_x = 356; // Max BOARD_WIDTH after H_SPACE
        int expected_y = 199;
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_5_initShot() {
        int x = -10;
        int y = -5;
        int expected_x = 0; // Min value
        int expected_y = 0; // Min value
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_6_initShot() {
        int x = -15;
        int y = 100;
        int expected_x = 0; // Min value
        int expected_y = 99; // 100 - V_SPACE
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_7_initShot() {
        int x = 50;
        int y = -20;
        int expected_x = 56; // 50 + H_SPACE
        int expected_y = 0;  // Min value
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_8_initShot() {
        int x = 356;
        int y = 350;
        int expected_x = 356; // Max BOARD_WIDTH
        int expected_y = 349; // 350 - V_SPACE
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_9_initShot() {
        int x = 357;
        int y = 351;
        int expected_x = 356; // Max BOARD_WIDTH
        int expected_y = 350; // Max BOARD_HEIGHT
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }

    @org.junit.jupiter.api.Test
    void cp_10_initShot() {
        int x = -100;
        int y = -100;
        int expected_x = 0; // Min value
        int expected_y = 0; // Min value
        Shot shot = new Shot(x, y);
        assertEquals(expected_x, shot.getX());
        assertEquals(expected_y, shot.getY());
    }
}
