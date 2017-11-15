package br.com.ismael;

import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller central para processamento de comandos de deslocamento dos robôs.
 * 
 * @author Ismael Júnior
 *
 */
@Controller
public class MoveRobotsController {

    /**
     * Caracteres válidos para um comando.
     */
    private static final String VALIDS_CHARS = "LRM";

    @RequestMapping(value = "{position}", method = RequestMethod.POST)
    public ResponseEntity<String> post(@PathVariable("position") String position) {
        ResponseEntity<String> response = null;
        boolean isValidCommand = true;
        ResponseEntity<String> badRequestResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(getBadRequestResponse());
        IntStream positionStream = position.chars();

        StringBuilder filterPositionSb = new StringBuilder();
        /*
         * Filtra o comando informado para conter apenas os caracteres válidos.
         */
        positionStream.filter(s -> VALIDS_CHARS.contains(Character.toString((char) s)))
                        .forEach(s -> filterPositionSb.append(Character.toString((char) s)));

        String filterPosition = filterPositionSb.toString();
        /*
         * Caso não tenha sobrado nenhum caracter, assume que o comando é inválido. Caso contrário, apenas ignora
         * comandos inválidos e lida apenas com os válidos.
         */
        if (filterPosition.isEmpty()) {
            response = badRequestResponse;
            isValidCommand = false;
        }

        if (isValidCommand) {
            RobotCommandBuild robotBuild = RobotCommandBuild.newInstance();
            IntStream streamProcessing = filterPosition.chars();
            try {
                /*
                 * Para cada caracter, efetua o comando de movimentação do robô.
                 */
                streamProcessing.forEach(s -> robotBuild.move((char) s));
                /*
                 * Após processado prepara o Response com a posição final do robô após o processamento.
                 */
                RobotCommand robotOrientation = robotBuild.build();
                response = ResponseEntity.ok(getFormattedAcceptResponse(robotOrientation));
            } catch (RobotOrientationException e) {
                /*
                 * Caso ocorra alguma exceção retorna a mensagem padrão de comando inválido juntamente com o motivo.
                 */
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(getBadRequestResponse() + " - " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Monta a mensagem padrão para o {@link Status#} BadRequest.
     * 
     * @return
     */
    private String getBadRequestResponse() {
        return "Posição inválida";
    }

    /**
     * Monta a mensagem que informa a posição final do Robô após o processamento do comando.
     * 
     * @param robotCommand
     * @return
     */
    private String getFormattedAcceptResponse(RobotCommand robotCommand) {
        return "(" + robotCommand.getCurrentX() + ", " + robotCommand.getCurrentY() + ", "
                        + robotCommand.getOrientation() + ")";
    }

}
