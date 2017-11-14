package br.com.ismael;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MoveRobotsController {

    private Integer maxX = 5;
    private Integer maxY = 5;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "{position}", method = RequestMethod.POST)
    public ResponseEntity<String> post(@PathVariable("position") String position) {
        ResponseEntity<String> response = null;
        boolean isValidCommand = true;
        ResponseEntity<String> badRequestResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Posição inválida");
        for (Character c : position.toCharArray()) {
            if (!"LRM".contains(String.valueOf(c))) {
                response = badRequestResponse;
                isValidCommand = false;
                break;
            }
        }

        if (isValidCommand) {
            RobotOrientationBuild robotBuild = RobotOrientationBuild.newInstance();
            for (Character c : position.toCharArray()) {
                robotBuild.move(c);
                if ((robotBuild.getRobotOrientation().getCurrentX() < 0
                                || robotBuild.getRobotOrientation().getCurrentX() > maxX)
                                || (robotBuild.getRobotOrientation().getCurrentY() < 0
                                                || robotBuild.getRobotOrientation().getCurrentY() > maxY)) {
                    response = badRequestResponse;
                }
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
