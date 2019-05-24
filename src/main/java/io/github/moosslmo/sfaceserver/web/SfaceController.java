package io.github.moosslmo.sfaceserver.web;

import io.github.moosslmo.sfaceserver.domain.User;
import io.github.moosslmo.sfaceserver.domain.UserRepository;
import io.github.moosslmo.sfaceserver.exception.UserNotFoundException;
import io.github.moosslmo.sfaceserver.web.dto.Coordinate;
import io.github.moosslmo.sfaceserver.web.dto.DieMessage;
import io.github.moosslmo.sfaceserver.web.dto.FaceMessage;
import io.github.moosslmo.sfaceserver.web.dto.JoinMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SfaceController {

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/join/{username}")
    @SendTo("/topic/join")
    public JoinMessage join(@DestinationVariable String username) {
        int score = 0;
        int positionX = 200;
        int positionY = 100;

        User user = User.builder()
                .name(username)
                .score(score)
                .build();
        userRepository.save(user);

        return JoinMessage.of(username, 0, Coordinate.of(positionX, positionY));
    }

    @MessageMapping("/face/{username}")
    @SendTo("/topic/face")
    public FaceMessage updateFace(@DestinationVariable String username, FaceMessage faceMessage) {
        User user = userRepository.findById(username)
                .orElseThrow(UserNotFoundException::new);
        user.setScore(faceMessage.getScore());
        userRepository.save(user);

        return FaceMessage.of(username, faceMessage.getScore(), faceMessage.getCoords(), faceMessage.getImageBase64());
    }

    @MessageMapping("/die/{username}")
    @SendTo("/topic/die")
    public DieMessage die(@DestinationVariable String username, DieMessage dieMessage) {
        User user = userRepository.findById(username)
                .orElseThrow(UserNotFoundException::new);
        user.setHighScore(dieMessage.getHighScore());

        return DieMessage.of(username, dieMessage.getHighScore());
    }
}
