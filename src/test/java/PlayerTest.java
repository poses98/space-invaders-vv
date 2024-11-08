import main.Commons;
import space_invaders.sprites.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;
    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        player = new Player();
    }

    @org.junit.jupiter.api.AfterEach
    void TearDown(){
        System.out.println(player.toString());
    }

    @org.junit.jupiter.api.Test
    void cp_1_initPlayer(){
        // Posición debe ser Commons.BOARD_WIDTH/2
        int halfScreen = Commons.BOARD_WIDTH/2;
        assertEquals(halfScreen, player.getX());
    }

    /**
     * Movimiento a la izquierda sin estar cerca del borde de la pantalla
     */
    @org.junit.jupiter.api.Test
    void cp_1_act(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED
        );
        int oldX = player.getX();
        player.keyPressed(keyEvent);
        player.act();
        assertEquals(oldX-2,player.getX());
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
        int oldX = player.getX();
        player.keyPressed(keyEvent);
        player.act();
        assertEquals(oldX+2,player.getX());
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
        player.keyPressed(keyEvent);
    }

    /**
     * Ya no se pulsa la tecla VK_LEFT entonces dx = 0
     */
    @org.junit.jupiter.api.Test
    void cp_1_keyReleased(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.keyReleased(keyEvent);
    }
    /**
     * Ya no se pulsa la tecla VK_RIGHT entonces dx = 0
     */
    @org.junit.jupiter.api.Test
    void cp_2_keyReleased(){
        KeyEvent keyEvent = new KeyEvent(
                new Component(){}, // Fuente del evento (no importa mucho para pruebas simples)
                KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(),
                0, // Modificadores (sin shift, alt, etc.)
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        player.keyReleased(keyEvent);
    }
}
