package br.com.ismael;

public class RobotOrientationBuild {

    private RobotOrientation robotOrientation = new RobotOrientation(RobotOrientationEnum.N, 0, 0);

    public static RobotOrientationBuild newInstance() {
        return new RobotOrientationBuild();
    }

    public RobotOrientationBuild move(char command) {
        if (command == 'M') {
            switch (robotOrientation.getOrientation()) {
                case N :
                    robotOrientation.setCurrentY(robotOrientation.getCurrentY() + 1);
                    break;
                case S :
                    robotOrientation.setCurrentY(robotOrientation.getCurrentY() - 1);
                    break;
                case E :
                    robotOrientation.setCurrentX(robotOrientation.getCurrentX() + 1);
                    break;
                case W :
                    robotOrientation.setCurrentX(robotOrientation.getCurrentX() - 1);
                    break;
                default :
                    break;
            }
        } else {
            if (command == 'L') {
                robotOrientation.setOrientation(robotOrientation.getOrientation().toLeft());
            } else if (command == 'R') {
                robotOrientation.setOrientation(robotOrientation.getOrientation().toRight());
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
