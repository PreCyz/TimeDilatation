package timedilataion.calculation.unit;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author premik
 */
public enum TimeUnit {
    years(1, 1) 
    ,days(365, 2)
    ,hours(24, 3)
    ,minutes(60, 4)
    ,seconds(60, 5);
    
    private final int conversion;
    private final int order;
    
    private static final MathContext DECIMAL128 = MathContext.DECIMAL128;

    private TimeUnit(int conversion, int order) {
        this.conversion = conversion;
        this.order = order;
    }

    public BigDecimal getConversion() {
        return new BigDecimal(conversion);
    }
    
    public static TimeUnit getUnit(String value){
        if(isValueProper(value)){
            for(TimeUnit unit : TimeUnit.values()){
                if(value.equals(unit.name())) return unit;
            }
        } 
        return null;
    }
    
    private static boolean isValueProper(String value){
        if (value == null) return false;
        if ("".equals(value)) return false;
        for (TimeUnit unit : TimeUnit.values()){
            if (value.equals(unit.name())) return true;
        }
        return false;
    }

    
    public BigDecimal convertViaOrder(TimeUnit timeUnit) {
        BigDecimal result = new BigDecimal(1);
        if (this.order < timeUnit.order) {
            for (int i = this.order + 1; i <= timeUnit.order; i++) {
                result = result.multiply(getTimeUnitByOrder(i).getConversion());
            }
        } else if (this.order > timeUnit.order) {
            for (int i = this.order; i > timeUnit.order; i--) {
                result = result.divide(getTimeUnitByOrder(i).getConversion(), DECIMAL128);
            }
        }
        return result;
    }
    
    private TimeUnit getTimeUnitByOrder(int order) {
        TimeUnit result = null;
        for (TimeUnit unit : TimeUnit.values()) {
            if (unit.order == order) {
                result = unit;
                break;
            }
        }
        return result;
    }
    
    /** This solution is not elegant. It is full of ifology. Bleee!! Use convertViaOrder insted.*/
    @Deprecated
    public BigDecimal convertTo(TimeUnit timeUnit) {
        switch (this) {
            case years : 
                switch (timeUnit) {
                    case days :
                        return days.getConversion();
                    case hours : 
                        return days.getConversion().multiply(hours.getConversion());
                    case minutes:
                        return days.getConversion().multiply(hours.getConversion()).multiply(minutes.getConversion());
                    case seconds:
                        return days.getConversion().multiply(hours.getConversion()).multiply(minutes.getConversion()).multiply(seconds.getConversion());
                    default :
                        return this.getConversion();
                }
                
            case days :
                switch (timeUnit) {
                    case years:
                        return new BigDecimal(1).divide(days.getConversion(), DECIMAL128);
                    case hours:
                        return hours.getConversion();
                    case minutes:
                        return hours.getConversion().multiply(minutes.getConversion());
                    case seconds:
                        return hours.getConversion().multiply(minutes.getConversion()).multiply(seconds.getConversion());
                    default:
                        return this.getConversion();
                }
                
            case hours :
                switch (timeUnit) {
                    case years:
                        return new BigDecimal(1).divide(days.getConversion(), DECIMAL128)
                                .divide(hours.getConversion(), DECIMAL128);
                    case days:
                        return new BigDecimal(1).divide(hours.getConversion(), DECIMAL128);
                    case minutes:
                        return minutes.getConversion();
                    case seconds:
                        return minutes.getConversion().multiply(seconds.getConversion());
                    default:
                        return this.getConversion();
                }
                
            case minutes :
                switch (timeUnit) {
                    case years:
                        return new BigDecimal(1).divide(days.getConversion(), DECIMAL128)
                                .divide(hours.getConversion(), DECIMAL128)
                                .divide(minutes.getConversion(), DECIMAL128);
                    case days:
                        return new BigDecimal(1).divide(hours.getConversion(), DECIMAL128)
                                .divide(minutes.getConversion(), DECIMAL128);
                    case hours:
                        return new BigDecimal(1).divide(minutes.getConversion(), DECIMAL128);
                    case seconds:
                        return seconds.getConversion();
                    default:
                        return this.getConversion();
                }
                
            case seconds :
                switch (timeUnit) {
                    case years:
                        return new BigDecimal(1).divide(days.getConversion(), DECIMAL128)
                                .divide(hours.getConversion(), DECIMAL128)
                                .divide(minutes.getConversion(), DECIMAL128)
                                .divide(seconds.getConversion(), DECIMAL128);
                    case days:
                        return new BigDecimal(1).divide(hours.getConversion(), DECIMAL128)
                                .divide(minutes.getConversion(), DECIMAL128)
                                .divide(seconds.getConversion(), DECIMAL128);
                    case hours:
                        return new BigDecimal(1).divide(minutes.getConversion(), DECIMAL128)
                                .divide(seconds.getConversion(), DECIMAL128);
                    case minutes:
                        return new BigDecimal(1).divide(seconds.getConversion(), DECIMAL128);
                    default:
                        return this.getConversion();
                }
        }
        return new BigDecimal(1);
    }
}
