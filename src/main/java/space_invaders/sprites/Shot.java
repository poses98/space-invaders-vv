package space_invaders.sprites;

import main.Commons;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() {
    }
    /**
     * Inicializa un nuevo objeto disparo en las coordenadas indicadas
     * @param x coordenada X de la posición del nuevo disparo
     * @param y coordenada Y de la posición del nuevo disparo
     * */
    public Shot(int x, int y) {

        initShot(x, y);
    }
    /**
     * Inicializa un nuevo objeto disparo en las coordenadas indicadas y le asigna la imagen correspondiente en la interfaz
     * @param x coordenada X de la posición del nuevo disparo
     * @param y coordenada Y de la posición del nuevo disparo
     * Si alguna de las coordenadas indicadas es mayor al máximo permitido, se le asignará el valor máximo permitido.
     * Si se introducen valores negativos de coordenada, se asignará el mínimo permitido (0).
     * */
    private void initShot(int x, int y) {

        var shotImg = "src/main/resources/images/shot.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        int V_SPACE = 1;
        // Cambiado la atribución de X e Y para comprobar límites y que sea siempre un valor válido
        setX(Math.max(0, Math.min(Commons.BOARD_WIDTH, x + H_SPACE)));
        setY(Math.max(0, Math.min(Commons.BOARD_HEIGHT, y - V_SPACE)));
    }
}

