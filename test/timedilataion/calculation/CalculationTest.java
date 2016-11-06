package timedilataion.calculation;

import java.math.BigDecimal;
import timedilataion.calculation.Calculation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import timedilataion.bean.DilatationBean;
import timedilataion.calculation.unit.TimeUnit;
import timedilataion.calculation.unit.UnitScale;

/**
 * @author Gawa [Paweł Gawędzki]
 * @date 2016-11-06 18:38:00
 */
public class CalculationTest {
    
    private final String GRAMS = "grams";
    private final String METERS = "meters";
    private final short DEFAULT_SECONDS_IDX = 3;
    private final short DEFAULT_GRAMS_IDX = 10;
    private final short DEFAULT_METERS_IDX = DEFAULT_GRAMS_IDX;
    private final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.days;
    private final UnitScale DEFAULT_UNIT_SCALE = UnitScale.kilo;
    
    private DilatationBean bean;
    private Calculation calc;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        bean = null;
        calc = null;
    }

    @Test
    public void givenDilatationBeanWhenInitCalculationThenCalculateAllParameters() {
        bean = new DilatationBean.DilatationBuilder()
                .T(new BigDecimal(8))
                .T0(new BigDecimal(3))
                .timeUnit(DEFAULT_TIME_UNIT)
                .massScale(DEFAULT_UNIT_SCALE)
                .radiusScale(DEFAULT_UNIT_SCALE)
                .build();
        new Calculation(bean).initCalculation();
        
        assertNotNull("Time should not be null.", bean.getT());
        assertNotNull("Time in 0 moment should not be null.", bean.getT0());
        assertNotNull("Speed should not be null.", bean.getM());
        assertNotNull("Speed should not be null.", bean.getV());
        assertNotNull("Radius should not be null.", bean.getR());
    }
    

}