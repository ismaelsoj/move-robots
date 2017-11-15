package br.com.ismael;

public class RobotOrientationBuild {

    private RobotOrientation robotOrientation = new RobotOrientation(RobotOrientationEnum.N, RobotOrientation.MIN_X,
                    RobotOrientation.MIN_Y);

    public static RobotOrientationBuild newInstance() {
        return new RobotOrientationBuild();
    }

    public RobotOrientationBuild move(char command) throws RobotOrientationException {
        if (command == 'M') {
            int currentX = robotOrientation.getCurrentX();
            int currentY = robotOrientation.getCurrentY();
            switch (robotOrientation.getOrientation()) {
                case N :
                    robotOrientation.setCurrentY(currentY + 1);
                    break;
                case S :
                    robotOrientation.setCurrentY(currentY - 1);
                    break;
                case E :
                    robotOrientation.setCurrentX(currentX + 1);
                    break;
                case W :
                    robotOrientation.setCurrentX(currentX - 1);
                    break;
                default :
                    break;
            }
            if (currentX > RobotOrientation.MAX_X || currentY > RobotOrientation.MAX_Y) {
                throw new RobotOrientationException(
                                "Enviado comando de deslocamento que ultrapassa os limites permitidos ("
                                                + RobotOrientation.MAX_X + ", " + RobotOrientation.MAX_Y + ")");
            }
        } else {
            switch (command) {
                case 'L' :
                    robotOrientation.setOrientation(robotOrientation.getOrientation().toLeft());
                    break;
                case 'R' :
                    robotOrientation.setOrientation(robotOrientation.getOrientation().toRight());
                    break;
                default :
                    break;
            }
        }
        setRobotOrientation(robotOrientation);
        return this;

    }

    public RobotOrientation build() {
        return getRobotOrientation();

    }

    private void setRobotOrientation(RobotOrientation robotOrientation) {
        this.robotOrientation = robotOrientation;
    }

    public RobotOrientation getRobotOrientation() {
        return robotOrientation;
    }

}
