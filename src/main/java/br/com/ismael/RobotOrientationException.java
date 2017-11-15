package br.com.ismael;

/**
 * Manipula exceções relacionadas às regras de movimentação de um robô no tabuleiro.
 * 
 * @author Ismael Júnior
 *
 */
public class RobotOrientationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RobotOrientationException() {
        super();
    }

    public RobotOrientationException(String s) {
        super(s);
    }

}
