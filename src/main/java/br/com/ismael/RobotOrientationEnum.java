package br.com.ismael;

/**
 * Enum que define as orientações que o robô pode assumir: Norte (N), Sul (S), Leste (E) ou Oeste (W).
 * 
 * @author Ismael Júnior
 *
 */
public enum RobotOrientationEnum {

    /**
     * Indica que o robô está apontado para o Norte. Caso seja girado para a esquerda deve ir para Oeste; caso seja
     * girado para a direita deve ir para Leste.
     */
    N {

        @Override
        public RobotOrientationEnum toLeft() {
            return W;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return E;
        }

    },
    /**
     * Indica que o robô está apontado para o Sul. Caso seja girado para a esquerda deve ir para Leste; caso seja girado
     * para a direita deve ir para Oeste.
     */
    S {

        @Override
        public RobotOrientationEnum toLeft() {
            return E;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return W;
        }

    },
    /**
     * Indica que o robô está apontado para Leste. Caso seja girado para a esquerda deve ir para o Norte; caso seja
     * girado para a direita deve ir para o Sul.
     */
    E {

        @Override
        public RobotOrientationEnum toLeft() {
            return N;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return S;
        }

    },
    /**
     * Indica que o robô está apontado para Oeste. Caso seja girado para a esquerda deve ir para o Sul; caso seja girado
     * para a direita deve ir para o Norte.
     */
    W {

        @Override
        public RobotOrientationEnum toLeft() {
            return S;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return N;
        }

    };

    /**
     * Deve definir qual orientação um robô deve assumir caso seja girado para a esquerda.
     * 
     * @return
     */
    public abstract RobotOrientationEnum toLeft();

    /**
     * Deve definir qual orientação um robô deve assumir caso seja girado para a direita.
     * 
     * @return
     */
    public abstract RobotOrientationEnum toRight();

}
