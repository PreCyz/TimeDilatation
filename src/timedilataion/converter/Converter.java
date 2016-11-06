package timedilataion.converter;

import java.math.BigDecimal;
import timedilataion.calculation.unit.TimeUnit;
import timedilataion.calculation.unit.UnitScale;
import static timedilataion.calculation.Constants.NUMBER_LENGTH;
import static timedilataion.calculation.unit.TimeUnit.*;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-11-05 21:41:24
 */
public class Converter {
    
    private Converter(){}
    
    public static BigDecimal time2Seconds(BigDecimal time, TimeUnit unit) {
        switch (unit) {
            case years:
                return time.multiply(TimeUnit.days.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case days:
                return time.multiply(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case hours:
                return time.multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case minutes:
                return time.multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            default:
                return time;
        }
    }

    public static BigDecimal convertValue(BigDecimal value, UnitScale unit) {
        return value.multiply(unit.getUnit(), NUMBER_LENGTH);
    }

    public static BigDecimal convertTime(BigDecimal value, TimeUnit tUnit) {
        switch (tUnit) {
            case years:
                return value.divide(TimeUnit.days.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case days:
                return value.divide(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case hours:
                return value.divide(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .divide(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case minutes:
                return value.divide(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            default:
                return value;
        }
    }

    public static BigDecimal convertSpeed(BigDecimal value, UnitScale rUnit, TimeUnit tUnit) {
        BigDecimal counter = new BigDecimal(1, NUMBER_LENGTH), denominator = new BigDecimal(1, NUMBER_LENGTH);
        counter = convertValue(counter, rUnit);
        denominator = convertTime(denominator, tUnit);
        return value.multiply(counter, NUMBER_LENGTH).divide(denominator, NUMBER_LENGTH);
    }

}
