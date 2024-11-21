package com.unitTesting.exercise.tyrePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlarmTest {

    private static final double LOW_PRESSURE_THRESHOLD = 17;
    private static final double HIGH_PRESSURE_THRESHOLD = 21;

    private Alarm alarm;
    private Sensor sensor;
    @Before
    public void init() {
        sensor = Mockito.mock(Sensor.class);
        alarm = new Alarm(sensor);
    }

    @Test
    public void testGetAlarmOnReturnsFalseOnNewInstance() {
        assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void testCheckSetsAlarmOnTrueWhenSensorValueBelowThreshold() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_THRESHOLD - 1);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckSetsAlarmOnTrueWhenSensorValueAboveThreshold() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_THRESHOLD + 1);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }
    @Test
    public void testCheckSetsAlarmOnFalseWhenSensorValueAtHighPressureThreshold() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_THRESHOLD);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void testCheckSetsAlarmOnFalseWhenSensorValueAtLowPressureThreshold() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_THRESHOLD);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }

}
