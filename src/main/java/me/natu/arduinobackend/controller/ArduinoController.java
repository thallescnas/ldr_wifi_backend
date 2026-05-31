package me.natu.arduinobackend.controller;

import me.natu.arduinobackend.dto.ArduinoSendDTO;
import me.natu.arduinobackend.service.ArduinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/arduino")
public class ArduinoController {

    private ArduinoService service;

    public ArduinoController(ArduinoService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> postRead(@RequestBody ArduinoSendDTO dto){
        service.addRead(dto.value());

        return ResponseEntity.ok(dto.value());
    }
    @GetMapping("/")
    public ResponseEntity<?> valueRead() {
        return ResponseEntity.ok(service.lastRead());
    }
}
