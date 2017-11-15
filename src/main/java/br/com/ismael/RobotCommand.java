package br.com.ismael;

/**
 * Bean responsável por armazenar a posição atual de um robô.
 * 
 * @author Ismael Júnior
 *
 */
public class RobotCommand {

    private RobotOrientationEnum orientation;
    public static final Integer MIN_X = 0;
    public static final Integer MIN_Y = 0;
    public static final Integer MAX_X = 5;
    public static final Integer MAX_Y = 5;
    private int currentX = MIN_X;
    private int currentY = MIN_Y;

    public RobotCommand(RobotOrientationEnum initialOrientation, int initialX, int initialY) {
        this.orientation = initialOrientation;
        this.currentX = initialX;
        this.currentY = initialY;
    }

    public RobotOrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(RobotOrientationEnum orientation) {
        this.orientation = orientation;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

}
