package ro.sapientia.furniture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.service.NotificationBodyService;

import java.util.List;

@RestController
@RequestMapping("api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationBodyService notificationBodyService;


    // endpoint - return all notification
    @GetMapping("/all")
    public ResponseEntity<List<NotificationBody>> getAllNotificationBodies(){
        final List<NotificationBody> notificationBodies = notificationBodyService.findAllNotificationBodies();
        return new ResponseEntity<>(notificationBodies, HttpStatus.OK);
    }

    // endpoint - return one notification by id
    @GetMapping("/find/{id}")
    public ResponseEntity<NotificationBody> getNotificationBodyById(@PathVariable("id") Long id){
        final NotificationBody notificationBody = notificationBodyService.findNotificationBodyById(id);
        return new ResponseEntity<>(notificationBody,HttpStatus.OK);
    }

    // endpoint - add one notification
    @PostMapping("/add")
    public ResponseEntity<NotificationBody> addNotificationBody(@RequestBody NotificationBody notificationBody){
        final NotificationBody persistentNotificationBody = notificationBodyService.create(notificationBody);
        return new ResponseEntity<>(persistentNotificationBody,HttpStatus.CREATED);
    }

    // endpoint - update one notification
    @PutMapping("/update")
    public ResponseEntity<NotificationBody> updateNotificationBody(@RequestBody NotificationBody notificationBody){
        final NotificationBody persistentNotificationBody = notificationBodyService.update(notificationBody);
        return new ResponseEntity<>(persistentNotificationBody,HttpStatus.OK);
    }

    // endpoint - delete one notification by id
    @DeleteMapping ("delete/{id}")
    public ResponseEntity<?> deleteNotificationBodyById(@PathVariable("id") Long id){
        notificationBodyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
