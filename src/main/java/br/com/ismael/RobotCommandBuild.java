package br.com.ismael;

/**
 * Classe responsável por construir objetos RobotCommand e manipula-los em tempo de execução.
 * 
 * @author Ismael Júnior
 *
 */
public class RobotCommandBuild {

    private RobotCommand robotCommand = new RobotCommand(RobotOrientationEnum.N, RobotCommand.MIN_X,
                    RobotCommand.MIN_Y);

    public static RobotCommandBuild newInstance() {
        return new RobotCommandBuild();
    }

    /**
     * Executa a ação de mover um robô no terreno.
     * 
     * @param command
     *            - Comando a ser processado
     * @return
     * @throws RobotOrientationException
     */
    public RobotCommandBuild move(char command) throws RobotOrientationException {
        /*
         * O comando 'M' movimenta o robô de acordo com a direção em que ele se encontra.
         */
        if (command == 'M') {
            /*
             * De acordo com a orientação do robô no momento, o robô pode se deslocar para frente, para baixo, para a
             * esquerda ou para a direita.
             */
            switch (robotCommand.getOrientation()) {
                case N :
                    robotCommand.setCurrentY(robotCommand.getCurrentY() + 1);
                    break;
                case S :
                    robotCommand.setCurrentY(robotCommand.getCurrentY() - 1);
                    break;
                case E :
                    robotCommand.setCurrentX(robotCommand.getCurrentX() + 1);
                    break;
                case W :
                    robotCommand.setCurrentX(robotCommand.getCurrentX() - 1);
                    break;
                default :
                    break;
            }
            /*
             * Caso o comando resulte em uma posição fora dos limites estabelecidos (0 e 5 em X ou Y), lança uma
             * exceção.
             */
            if ((robotCommand.getCurrentX() < RobotCommand.MIN_X || robotCommand.getCurrentX() > RobotCommand.MAX_X)
                            || (robotCommand.getCurrentY() < RobotCommand.MIN_Y
                                            || robotCommand.getCurrentY() > RobotCommand.MAX_Y)) {
                throw new RobotOrientationException(
                                "Enviado comando de deslocamento que ultrapassa os limites permitidos. Mínimo: ("
                                                + RobotCommand.MIN_X + ", " + RobotCommand.MIN_Y + "); Máximo: ("
                                                + RobotCommand.MAX_X + ", " + RobotCommand.MAX_Y + ")");
            }
        } else {
            /*
             * Caso o comando seja 'L' ou 'R' altera a direção do robô para a direita ou para a esquerda sem tira-lo do
             * lugar.
             */
            switch (command) {
                case 'L' :
                    robotCommand.setOrientation(robotCommand.getOrientation().toLeft());
                    break;
                case 'R' :
                    robotCommand.setOrientation(robotCommand.getOrientation().toRight());
                    break;
                default :
                    break;
            }
        }
        setRobotCommand(robotCommand);
        return this;

    }

    /**
     * Finaliza a construção do objeto {@link RobotCommand}.
     * 
     * @return
     */
    public RobotCommand build() {
        return getRobotCommand();

    }

    private void setRobotCommand(RobotCommand robotCommand) {
        this.robotCommand = robotCommand;
    }

    private RobotCommand getRobotCommand() {
        return robotCommand;
    }

}
