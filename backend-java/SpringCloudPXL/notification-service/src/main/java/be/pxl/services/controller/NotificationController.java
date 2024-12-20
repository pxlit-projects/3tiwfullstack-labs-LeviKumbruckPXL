package be.pxl.services.controller;

import be.pxl.services.domain.Notification;
import be.pxl.services.domain.dto.NotificationResponse;
import be.pxl.services.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void sendMessage(@RequestBody Notification notification) {
        notificationService.sendMessage(notification);
    }



}
