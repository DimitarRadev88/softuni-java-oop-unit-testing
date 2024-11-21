package com.unitTesting.exercise.tyrePressureMonitoringSystem;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SensorTest {

    private static final double OFFSET = 16;

    @Test
    public void testPopNexPressurePsiValueReturnsRandomValueAboveOffset() {
        Sensor sensor = new Sensor();
        for (int i = 0; i < 1000; i++) {
            assertTrue(sensor.popNextPressurePsiValue() >= OFFSET);
        }
    }

}
