package space_invaders.sprites;

import main.Commons;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;


public class AlienTest {
    Alien alien = null;

    @org.junit.jupiter.api.Test
    void cp_1_initAlien(){
        int x = 0;
        int y = 0;
        alien = new Alien(x,y);
        assertEquals(0,alien.getX(),"X value is not equal");
        assertEquals(0,alien.getY(), "Y value es not equal");
    }

    @org.junit.jupiter.api.Test
    void cp_2_initAlien(){
        int x = -1;
        int y = 8000;
        alien = new Alien(x,y);
        assertEquals(0, alien.getX(),"X value is not equal");
        assertEquals(350, alien.getY(),"Y value es not equal");
    }

    @org.junit.jupiter.api.Test
    void cp_3_initAlien(){
        int x = 8000;
        int y = -1;
        alien = new Alien(x,y);
        assertEquals(358, alien.getX(),"X value is not equal");
        assertEquals(0, alien.getY(),"Y value es not equal");
    }


    @org.junit.jupiter.api.Test
    void cp_4_initAlien(){
        int x = 8000;
        int y = 8000;
        alien = new Alien(x,y);
        assertEquals(358,alien.getX(), "X value is not equal");
        assertEquals(350,alien.getY(), "Y value es not equal");
    }


    @org.junit.jupiter.api.Test
    void cp_1_act(){
        int direction = 1;
        int initial_x = 0;
        int expected_x = 12;
        alien = new Alien(initial_x,Commons.BOARD_HEIGHT/2);
        alien.act(direction);
        assertEquals(expected_x,alien.getX());
    }

    @org.junit.jupiter.api.Test
    void cp_2_act(){
        int direction = -1;
        int initial_x = 12;
        int expected_x = 0;
        alien = new Alien(initial_x,Commons.BOARD_HEIGHT/2);
        alien.act(direction);
        assertEquals(expected_x,alien.getX());
    }

    @org.junit.jupiter.api.Test
    void cp_3_act(){
        int direction = 1;
        int initial_x = 346;
        int expected_x = 346;
        alien = new Alien(initial_x,Commons.BOARD_HEIGHT/2);
        alien.act(direction);
        assertEquals(expected_x,alien.getX());
    }

    @org.junit.jupiter.api.Test
    void cp_4_act(){
        int direction = -1;
        int initial_x = 0;
        int expected_x = 0;
        alien = new Alien(initial_x,Commons.BOARD_HEIGHT/2);
        alien.act(direction);
        assertEquals(expected_x,alien.getX());
    }
}