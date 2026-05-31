package me.natu.arduinobackend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArduinoService {
    private final List<Integer> LDRReads;

    public ArduinoService() {
        LDRReads = new ArrayList<>();
    }

    public int lastRead() {
        return LDRReads.get(LDRReads.size() - 1);
    }
    public int addRead(int value) {
        LDRReads.add(value);

        return value;
    }
    public List<Integer> getReads() {
        return LDRReads;
    }
}
