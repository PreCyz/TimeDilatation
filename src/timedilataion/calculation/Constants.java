package timedilataion.calculation;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-11-05 20:52:01
 */
public final class Constants {
    
    private Constants(){}
    
    public static final MathContext NUMBER_LENGTH = MathContext.DECIMAL128;
    public static final BigDecimal SPEED_OF_LIGHT = new BigDecimal(299792458);//[m/s]
    public static final BigDecimal GRAVITY_CONST = new BigDecimal(6.67)
                        .multiply(new BigDecimal(1)
                        .scaleByPowerOfTen(-11), NUMBER_LENGTH);
    public static final BigDecimal SUN_MASS = new BigDecimal(1.9891, NUMBER_LENGTH).scaleByPowerOfTen(30);

}
