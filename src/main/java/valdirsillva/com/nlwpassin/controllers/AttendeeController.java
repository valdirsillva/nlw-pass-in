package valdirsillva.com.nlwpassin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {
    @GetMapping
    public ResponseEntity<String> getTeste() {
        return ResponseEntity.ok("ok");
    }
}
