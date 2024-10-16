package be.pxl.services.services;

import be.pxl.services.domain.Notification;
import be.pxl.services.domain.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {


    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendMessage(Notification notification) {

        log.info("Receiving notification...");
        log.info("Sending.... {}", notification.getMessage());
        log.info("TO {}" , notification.getSender());


    }
}
