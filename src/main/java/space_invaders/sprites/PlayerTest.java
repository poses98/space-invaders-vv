package space_invaders.sprites;

import main.Commons;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;



public class PlayerTest {
    Player player = null;
    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        player = new Player();
    }

    @org.junit.jupiter.api.AfterEach
    void TearDown(){

    }

    @org.junit.jupiter.api.Test
    void cp_1_initPlayer(){
        // Posición debe ser Commons.BOARD_WIDTH/2
        int halfScreen = Commons.BOARD_WIDTH/2;
        assertEquals(halfScreen, player.getX());
    }

    @org.junit.jupiter.api.Test
    void cp_2_initPlayer(){
        assertNotEquals(null,player.getImage());
    }

    @org.junit.jupiter.api.Test
    void cp_3_initPlayer(){
        assertNotEquals(null,player);
    }

    /**
     * Movimiento a la izquierda sin estar cerca del borde de la pantalla
     */
    @org.junit.jupiter.api.Test
    void cp_1_act(){
        /**
         * Se simula pulsación de tecla VK_LEFT
         */
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.setX(179);
        player.keyPressed(keyEvent);
        player.act();
        assertEquals(177,player.getX());
    }

    /**
     * Movimiento a la derecha sin estar cerca del borde derecho
     */
    @org.junit.jupiter.api.Test
    void cp_2_act(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.setX(179);
        player.keyPressed(keyEvent);
        player.act();
       assertEquals(181,player.getX());
    }

    /**
     * Movimiento a la izquierda estando en el borde izquierdo
     */
    @org.junit.jupiter.api.Test
    void cp_3_act(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.setX(0);
        player.keyPressed(keyEvent);
        player.act();
       assertEquals(0,player.getX());
    }

    /**
     * Movimiento a la izquierda estando en el borde izquierdo
     */
    @org.junit.jupiter.api.Test
    void cp_4_act(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.setX(Commons.BOARD_WIDTH-Commons.PLAYER_WIDTH);
        player.keyPressed(keyEvent);
        player.act();
        assertEquals(Commons.BOARD_WIDTH-Commons.PLAYER_WIDTH,player.getX());
    }

    /**
     * Se pulsa la tecla VK_LEFT entonces dx < 0
     */
    @org.junit.jupiter.api.Test
    void cp_1_keyPressed(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.keyPressed(keyEvent);
        assertTrue(player.dx < 0);
    }

    /**
     * Se pulsa la tecla VK_RIGHT entonces dx > 0
     */
    @org.junit.jupiter.api.Test
    void cp_2_keyPressed(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );

        player.keyPressed(keyEvent);
        assertTrue(player.dx > 0);

    }

    /**
     * Se pulsa la tecla ANY entonces dx = dx
     */
    @org.junit.jupiter.api.Test
    void cp_3_keyPressed(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_L,
                KeyEvent.CHAR_UNDEFINED
        );
        int old_dx = player.dx;
        player.keyPressed(keyEvent);
        assertEquals(old_dx, player.dx);

    }

    /**
     * Ya no se pulsa la tecla VK_LEFT entonces dx = 0
     */
    @org.junit.jupiter.api.Test
    void cp_1_keyReleased(){
        player.dx = -2;

        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.keyReleased(keyEvent);
        assertEquals(0,player.dx);
    }
    /**
     * Ya no se pulsa la tecla VK_RIGHT entonces dx = 0
     */
    @org.junit.jupiter.api.Test
    void cp_2_keyReleased(){
        player.dx = 2;
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.keyReleased(keyEvent);
        assertEquals(0,player.dx);
    }

    @org.junit.jupiter.api.Test
    void cp_3_keyReleased(){
        player.dx = 2;

        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_SPACE,
                KeyEvent.CHAR_UNDEFINED
        );
        int old_dx = player.dx;
        player.keyReleased(keyEvent);
        assertEquals(old_dx,player.dx);
    }

    @org.junit.jupiter.api.Test
    void cp_4_keyReleased(){
        player.dx = -2;

        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_SPACE,
                KeyEvent.CHAR_UNDEFINED
        );
        int old_dx = player.dx;
        player.keyReleased(keyEvent);
        assertEquals(old_dx,player.dx);
    }

    @org.junit.jupiter.api.Test
    void cp_5_keyReleased(){
        player.dx = 0;
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_SPACE,
                KeyEvent.CHAR_UNDEFINED
        );
        int old_dx = player.dx;
        player.keyReleased(keyEvent);
        assertEquals(old_dx,player.dx);
    }
}