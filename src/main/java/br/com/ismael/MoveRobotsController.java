package br.com.ismael;

import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MoveRobotsController {

    private static final String VALIDS_CHARS = "LRM";

    @RequestMapping(value = "{position}", method = RequestMethod.POST)
    public ResponseEntity<String> post(@PathVariable("position") String position) {
        ResponseEntity<String> response = null;
        boolean isValidCommand = true;
        ResponseEntity<String> badRequestResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(getBadRequestResponse());
        IntStream positionStream = position.chars();

        StringBuilder filterPositionSb = new StringBuilder();
        positionStream.filter(s -> VALIDS_CHARS.contains(Character.toString((char) s)))
                        .forEach(s -> filterPositionSb.append(Character.toString((char) s)));

        String filterPosition = filterPositionSb.toString();
        if (filterPosition.isEmpty()) {
            response = badRequestResponse;
            isValidCommand = false;
        }

        if (isValidCommand) {
            RobotOrientationBuild robotBuild = RobotOrientationBuild.newInstance();
            IntStream streamProcessing = filterPosition.chars();
            try {
                streamProcessing.forEach(s -> robotBuild.move((char) s));
                RobotOrientation robotOrientation = robotBuild.getRobotOrientation();
                if (isValidPosition(robotOrientation)) {
                    robotBuild.build();
                    response = ResponseEntity.ok(getFormattedAcceptResponse(robotOrientation));
                } else {
                    response = badRequestResponse;
                }
            } catch (RobotOrientationException e) {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(getBadRequestResponse() + " - " + e.getMessage());
            }
        }
        return response;
    }

    private String getBadRequestResponse() {
        return "Posição inválida";
    }

    private String getFormattedAcceptResponse(RobotOrientation robotOrientation) {
        return "(" + robotOrientation.getCurrentX() + ", " + robotOrientation.getCurrentY() + ", "
                        + robotOrientation.getOrientation() + ")";
    }

    private boolean isValidPosition(RobotOrientation robotOrientation) {
        return (robotOrientation.getCurrentX() >= RobotOrientation.MIN_X
                        && robotOrientation.getCurrentX() <= RobotOrientation.MAX_X)
                        && (robotOrientation.getCurrentY() >= RobotOrientation.MIN_Y
                                        && robotOrientation.getCurrentY() <= RobotOrientation.MAX_Y);
    }

}
