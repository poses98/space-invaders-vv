package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board;
    Alien alien;
    Alien.Bomb bomb;
    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        board = new Board();
        alien = board.getAliens().getFirst();
        bomb = alien.getBomb();
    }

    @Test
    void test_gameInit(){
        assertEquals(24, board.getAliens().size());
        var aliens = board.getAliens();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int expectedX = Commons.ALIEN_INIT_X + 18 * j;
                int expectedY = Commons.ALIEN_INIT_Y + 18 * i;
                Alien alien = aliens.get(i * 6 + j);
                assertEquals(expectedX, alien.getX(), "La posición X del alien en la fila " + i + " y columna " + j + " no es correcta.");
                assertEquals(expectedY, alien.getY(), "La posición Y del alien en la fila " + i + " y columna " + j + " no es correcta.");
            }
        }
        assertNotEquals(null,board.getPlayer());
        assertNotEquals(null, board.getShot());
        assertTrue(board.isInGame());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,0,1,false,1",   // Alien en (0,0), movimiento hacia la derecha, no debe ir hacia abajo
            "1,0,1,false,1",   // Alien en (1,0), movimiento hacia la derecha, no debe ir hacia abajo
            "58,0,1,false,1",  // Alien en (58,0), movimiento hacia la derecha, no debe ir hacia abajo
            "357,0,1,true,-1", // Alien en (357,0), movimiento hacia la derecha, no debe ir hacia abajo
            "358,0,1,true,-1", // Alien en (358,0), movimiento hacia la derecha, debería ir hacia abajo (cambio de dirección)
            "0,1,-1,true,1",  // Alien en (0,1), movimiento hacia la izquierda, debería ir hacia abajo (cambio de dirección)
            "1,1,-1,true,1", // Alien en (1,1), movimiento hacia la izquierda, no debe ir hacia abajo
            "58,1,1,false,1",  // Alien en (58,1), movimiento hacia la derecha, no debe ir hacia abajo
            "357,1,1,true,-1", // Alien en (357,1), movimiento hacia la derecha, no debe ir hacia abajo
            "358,1,1,true,-1", // Alien en (358,1), movimiento hacia la derecha, debería ir hacia abajo (cambio de dirección)
            "0,58,-1,true,1", // Alien en (0,58), movimiento hacia la izquierda, debería ir hacia abajo (cambio de dirección)
            "1,58,-1,true,1", // Alien en (1,58), movimiento hacia la izquierda, no debe ir hacia abajo
            "58,58,-1,false,-1", // Alien en (58,58), movimiento hacia la izquierda, no debe ir hacia abajo
            "357,58,1,true,-1",  // Alien en (357,58), movimiento hacia la derecha, no debe ir hacia abajo
            "358,58,1,true,-1", // Alien en (358,58), movimiento hacia la derecha, debería ir hacia abajo (cambio de dirección)
            "0,289,-1,true,1", // Alien en (0,289), movimiento hacia la izquierda, debería ir hacia abajo (cambio de dirección)
            "1,289,-1,true,1", // Alien en (1,289), movimiento hacia la izquierda, no debe ir hacia abajo
            "58,289,1,false,1",  // Alien en (58,289), movimiento hacia la derecha, no debe ir hacia abajo
            "357,289,1,true,-1", // Alien en (357,289), movimiento hacia la derecha, no debe ir hacia abajo
            "358,289,1,true,-1", // Alien en (358,289), movimiento hacia la derecha, debería ir hacia abajo (cambio de dirección)
            "0,290,-1,true,1",  // Alien en (0,290), movimiento hacia la izquierda, debería ir hacia abajo (cambio de dirección)
            "1,290,-1,true,1", // Alien en (1,290), movimiento hacia la izquierda, no debe ir hacia abajo
            "58,290,1,false,1",  // Alien en (58,290), movimiento hacia la derecha, no debe ir hacia abajo
            "357,290,1,true,-1", // Alien en (357,290), movimiento hacia la derecha, no debe ir hacia abajo
            "358,290,1,true,-1"  // Alien en (358,290), movimiento hacia la derecha, debería ir hacia abajo (cambio de dirección)
    })
    void test_update_aliens(int x, int y, int dx, boolean should_go_down, int expected_dx){
        List<Alien> aliens = new ArrayList<>();
        aliens.add(new Alien(x,y));
        board.setAliens(aliens);
        board.setDirection(dx);
        System.out.println(board.getAliens().getFirst().getX());
        board.update_aliens();
        Alien updatedAlien = board.getAliens().get(0);
        System.out.println(board.getAliens().getFirst().getX());



        if(dx > 0 && !should_go_down){
            assertTrue(x < updatedAlien.getX(), "dx > 0 x coordinate was not expected got:" + updatedAlien.getX() + "\texpected:" + x);
        }else if(dx < 0 && !should_go_down){
            assertTrue(x > updatedAlien.getX(), "dx < 0 x coordinate was not expected got:" + updatedAlien.getX() + "\texpected:" + x);
        }

        if(should_go_down){
            assertTrue(y < updatedAlien.getY(), "should have go down");
        }else{
            assertEquals(y, updatedAlien.getY(),"y coordinate was not expected");
        }
        assertEquals(expected_dx,board.getDirection());
    }

    @Test
    void cp1_update(){
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();
        assertEquals("Game won!", board.getMessage());
        assertFalse(board.isInGame());
    }


    @Test
    void cp2_update(){
        board.setDeaths(10);
        board.update();
        assertTrue(board.isInGame());
    }

    @Test
    void cp3_update(){
        board.setDeaths(Commons.CHANCE);
        board.update();
        assertTrue(board.isInGame());
    }

    @Test
    void cp1_update_bomb(){
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT + 1);
        board.update_bomb();
        assertTrue(bomb.isDestroyed(),"La bomba debería haberse destruido");
    }

    @Test
    void cp3_update_bomb(){
        bomb.setX(board.getPlayer().getX() + Commons.PLAYER_WIDTH/2);
        bomb.setY(board.getPlayer().getY() + Commons.PLAYER_HEIGHT/2);
        bomb.setDestroyed(false);

        board.update_bomb();

        assertTrue(bomb.isDestroyed(),"La bomba debería haber sido destruida al colisionar con el jugador");
        assertTrue(board.getPlayer().isDying(),"El jugador debería estar muriendo al haber colisionado con una bomba");
    }

    @Test
    void cp4_update_bomb(){
        int i = 0;
        while(i<100 && bomb.isDestroyed()){
            board.update_bomb();
            i++;
        }
        System.out.println("Bomb found " + i);
        assertTrue(bomb.isVisible(),"Debería haberse creado después de 100 intentos");
    }

    @Test
    void cp5_update_bomb(){
        bomb.setDestroyed(false);
        bomb.setX(Commons.BOARD_WIDTH/2);
        bomb.setY(Commons.BOMB_HEIGHT/2);
        board.update_bomb();
        assertTrue(bomb.getX() == Commons.BOARD_WIDTH/2 && bomb.getY() == Commons.BOMB_HEIGHT/2 + 1);
    }

    @Test
    void ci_1_update_bomb(){
        List<Alien> aliens;
        aliens = board.getAliens();
        aliens.clear();
        alien.die();
        aliens.add(alien);
        board.update_bomb();
        assertEquals(alien.getX(),alien.getBomb().getX());
        assertEquals(alien.getY(),alien.getBomb().getY());
    }

    @Test
    void ci_2_update_bomb(){
        List<Alien> aliens;
        aliens = board.getAliens();
        aliens.clear();
        alien.getBomb().setDestroyed(true);
        aliens.add(alien);
        board.update_bomb();
        assertEquals(alien.getX(),alien.getBomb().getX());
        assertEquals(alien.getY(),alien.getBomb().getY());
    }

    @Test
    void cp_1_update_shot(){
        Shot shot  = board.getShot();
        shot.setX(alien.getX() + 9);
        shot.setY(alien.getY() + 9);
        int old_death = board.getDeaths();

        board.update_shots();

        assertFalse(shot.isVisible(),"El disparo debería haberse puesto a morir");
        assertTrue(alien.isDying(),"El alien debería estar muriendo");
        assertEquals(old_death-1,board.getDeaths(), "Debería haberse restado uno del contador de aliens");
    }

    @Test
    void cp_2_update_shot(){
        Shot shot  = board.getShot();
        shot.setX(150);
        shot.setY(0);

        board.update_shots();

        assertFalse(shot.isVisible(),"El disparo debería haberse puesto a morir");

    }

    @Test
    void cp_3_update_shot(){
        Shot shot = board.getShot();
        shot.setX(50);
        shot.setY(50);
        board.update_shots();
        assertEquals(46,shot.getY());
    }

    /**
     * Ci camino independiente de caja blanca
     */
    @Test
    void ci_1_update_shot(){
        Shot shot = board.getShot();
        shot.setY(0);
        board.update_shots();
        assertFalse(shot.isVisible());
    }
    @Test
    void ci_2_update_shot(){
        Shot shot = board.getShot();
        shot.setDying(true);
        board.update_shots();
        assertFalse(shot.isVisible());
    }
    @Test
    void ci_3_update_shot(){
        Shot shot = board.getShot();
        shot.setX(50);
        shot.setY(50);
        board.getAliens().clear();
        board.getAliens().add(new Alien(0,0));
        board.getAliens().getFirst().setDying(true);
        board.update_shots();
        assertEquals(46,shot.getY());

    }
}

