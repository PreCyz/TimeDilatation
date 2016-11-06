package timedilataion.calculation.unit;

import timedilataion.calculation.unit.TimeUnit;
import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static timedilataion.calculation.unit.TimeUnit.*;

/**
 * @author Gawa [Paweł Gawędzki]
 * @date 2016-10-30 20:39:42
 */
public class TimeConversionTest {

    private TimeUnit timeUnit;
    private TimeUnit newTime;

    @After
    public void tearDown() {
        timeUnit = null;
    }
    
    @Test
    public void convertYearsToDays() throws Exception {
        timeUnit = years;
        newTime = days;
        
        BigDecimal expected = days.getConversion();
        BigDecimal actual = timeUnit.convertTo(newTime);
        
        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name()); 
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
        
        actual = timeUnit.convertViaOrder(newTime);
        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
    }
    
    @Test
    public void convertYearsToHours() throws Exception {
        timeUnit = years;
        newTime = hours;

        BigDecimal expected = days.getConversion().multiply(hours.getConversion());
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
        
        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
    }
    
    @Test
    public void convertYearsToMinutes() throws Exception {
        timeUnit = years;
        newTime = minutes;

        BigDecimal expected = days.getConversion().multiply(hours.getConversion()).multiply(minutes.getConversion());
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
        
        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
    }
    
    @Test
    public void convertYearsToSeconds() throws Exception {
        timeUnit = years;
        newTime = seconds;

        BigDecimal expected = days.getConversion()
                .multiply(hours.getConversion())
                .multiply(minutes.getConversion())
                .multiply(seconds.getConversion());
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
        
        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
    }
    
    @Test
    public void convertSecondsToMinutes() throws Exception {
        timeUnit = seconds;
        newTime = minutes;

        BigDecimal expected = new BigDecimal(1).divide(seconds.getConversion(), MathContext.DECIMAL128);
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);

        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.1d);
    }
    
    @Test
    public void convertSecondsToYears() throws Exception {
        timeUnit = seconds;
        newTime = years;

        BigDecimal expected = new BigDecimal(1).divide(seconds.getConversion(), MathContext.DECIMAL128)
                .divide(minutes.getConversion(), MathContext.DECIMAL128)
                .divide(hours.getConversion(), MathContext.DECIMAL128)
                .divide(days.getConversion(), MathContext.DECIMAL128);
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);

        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);
    }
    
    @Test
    public void convertMinutesToDays() throws Exception {
        timeUnit = minutes;
        newTime = days;

        BigDecimal expected = new BigDecimal(1)
                .divide(minutes.getConversion(), MathContext.DECIMAL128)
                .divide(hours.getConversion(), MathContext.DECIMAL128);
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);

        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals("Order: " + msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);
    }
    
    @Test
    public void convertHoursToDays() throws Exception {
        timeUnit = hours;
        newTime = days;

        BigDecimal expected = new BigDecimal(1).divide(hours.getConversion(), MathContext.DECIMAL128);
        BigDecimal actual = timeUnit.convertTo(newTime);

        assertNotNull(actual);
        String msg = String.format("1 %s is equal to %f %s.", timeUnit.name(), expected.doubleValue(), newTime.name());
        assertEquals(msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);

        actual = timeUnit.convertViaOrder(newTime);

        assertNotNull(actual);
        assertEquals("Order: " + msg, expected.doubleValue(), actual.doubleValue(), 0.000000000001d);
    }

}