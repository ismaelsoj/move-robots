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
    private Integer maxX = 5;
    private Integer maxY = 5;

    @RequestMapping(value = "{position}", method = RequestMethod.POST)
    public ResponseEntity<String> post(@PathVariable("position") String position) {
        ResponseEntity<String> response = null;
        boolean isValidCommand = true;
        ResponseEntity<String> badRequestResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Posição inválida");
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
            streamProcessing.forEach(s -> robotBuild.move((char) s));
            if ((robotBuild.getRobotOrientation().getCurrentX() < 0
                            || robotBuild.getRobotOrientation().getCurrentX() > maxX)
                            || (robotBuild.getRobotOrientation().getCurrentY() < 0
                                            || robotBuild.getRobotOrientation().getCurrentY() > maxY)) {
                response = badRequestResponse;
            }
            if (response == null) {
                robotBuild.build();
                response = ResponseEntity.ok("(" + robotBuild.getRobotOrientation().getCurrentX() + ", "
                                + robotBuild.getRobotOrientation().getCurrentY() + ", "
                                + robotBuild.getRobotOrientation().getOrientation() + ")");
            }
        }
        return response;
    }

}
