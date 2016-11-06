package timedilataion.calculations.units;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static timedilataion.calculations.units.UnitScale.*;

/**
 * @author Gawa [Paweł Gawędzki]
 * @date 2016-10-30 17:27:04
 */
public class UnitConversionTest {
    
    private UnitScale unitScale;
    private UnitScale newUnit;

    @After
    public void tearDown() {
        unitScale = null;
    }
    
    @Test
    public void convertMetersToMilimetersTest() throws Exception {
        unitScale = base;
        newUnit = mili;
        BigDecimal expected = new BigDecimal(1000);
        
        BigDecimal actual = unitScale.convertTo(newUnit);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertKiloMetersToMiliMetersTest() throws Exception {
        unitScale = kilo;
        newUnit = mili;
        BigDecimal expected = new BigDecimal(1).scaleByPowerOfTen(6);
        
        BigDecimal actual = unitScale.convertTo(newUnit);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertMiliMetersToMetersTest() throws Exception {
        unitScale = mili;
        newUnit = base;
        BigDecimal expected = new BigDecimal(1).scaleByPowerOfTen(-3);
        
        BigDecimal actual = unitScale.convertTo(newUnit);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertMiliMetersToDecyMetersTest() throws Exception {
        unitScale = mili;
        newUnit = decy;
        BigDecimal expected = new BigDecimal(1).scaleByPowerOfTen(-2);
        
        BigDecimal actual = unitScale.convertTo(newUnit);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertMetersToMetersTest() throws Exception {
        unitScale = base;
        newUnit = base;
        BigDecimal initMeters = new BigDecimal(100);
        BigDecimal expected = initMeters.scaleByPowerOfTen(0);
        
        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = initMeters.multiply(conversion);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertFewNanoMetersToMegaMetersTest() throws Exception {
        unitScale = nano;
        newUnit = mega;
        BigDecimal initMeters = new BigDecimal(12);
        BigDecimal expected = initMeters.scaleByPowerOfTen(-15);
        
        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = initMeters.multiply(conversion);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertFewNanoMetersToCentyMetersTest() throws Exception {
        unitScale = nano;
        newUnit = centy;
        BigDecimal initMeters = new BigDecimal(12);
        BigDecimal expected = initMeters.scaleByPowerOfTen(-7);
        
        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = initMeters.multiply(conversion);
        
        verifyResult(actual, expected);
    }
    
    @Test
    public void convertFewGigaToHektoTest() throws Exception {
        unitScale = giga;
        newUnit = hekto;
        BigDecimal units = new BigDecimal(12);
        BigDecimal expected = units.scaleByPowerOfTen(7);

        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = units.multiply(conversion);

        verifyResult(actual, expected);
    }
    
    @Test
    public void convertFewMiliToPikoTest() throws Exception {
        unitScale = mili;
        newUnit = piko;
        BigDecimal units = new BigDecimal(12);
        BigDecimal expected = units.scaleByPowerOfTen(9);

        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = units.multiply(conversion);

        verifyResult(actual, expected);
    }
    
    @Test
    public void convertFewJottaToJoktoTest() throws Exception {
        unitScale = jotta;
        newUnit = jokto;
        BigDecimal units = new BigDecimal(12);
        BigDecimal expected = units.scaleByPowerOfTen(48);

        BigDecimal conversion = unitScale.convertTo(newUnit);
        BigDecimal actual = units.multiply(conversion);

        verifyResult(actual, expected);
    }

    private void verifyResult(BigDecimal actual, BigDecimal expected) {
        assertNotNull(actual);
        assertEquals("12 "+unitScale.name()+" equals "+expected+" " + newUnit.name() + ".", 
                expected.doubleValue(), actual.doubleValue(), expected.doubleValue()
        );
    }

}