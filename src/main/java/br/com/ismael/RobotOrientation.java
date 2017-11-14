package br.com.ismael;

public class RobotOrientation {

    private RobotOrientationEnum orientation;
    private int currentX = 0;
    private int currentY = 0;

    public RobotOrientation(RobotOrientationEnum initialOrientation, int initialX, int initialY) {
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
