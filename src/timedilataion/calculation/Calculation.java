package timedilataion.calculations;

import timedilataion.calculations.units.TimeUnit;
import timedilataion.calculations.units.UnitScale;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author premik
 */
public class Calculation {

    private BigDecimal t; //default [s]
    private BigDecimal t0;//default [s]
    private BigDecimal V;//default [m/s]
    private BigDecimal m;//default [kg]
    private BigDecimal r;//default [m]

    private TimeUnit tUnit;//L-lata,D-dni,H-godziny,M-minuty,S-sekundy
    private UnitScale mUnit;
    private UnitScale rUnit;

    private final MathContext NUMBER_LENGTH = MathContext.DECIMAL128;
    private final BigDecimal SPEED_OF_LIGHT = new BigDecimal(299792458);//[m/s]
    private final BigDecimal GRAVITY_CONST = new BigDecimal(6.67).multiply(new BigDecimal(1).scaleByPowerOfTen(-11), NUMBER_LENGTH);
    private final BigDecimal SUN_MASS = new BigDecimal(1.9891, NUMBER_LENGTH).scaleByPowerOfTen(30);

    public Calculation(BigDecimal t, BigDecimal t0, String timeUnit) {
        if(timeUnit != null){
            this.tUnit = TimeUnit.valueOf(timeUnit);
        } else{
            this.tUnit = TimeUnit.years;
        }
        this.t = time2Seconds(t, this.tUnit);
        this.t0 = time2Seconds(t0, this.tUnit);
        this.mUnit = UnitScale.kilo;
        this.m = convertValue(SUN_MASS, this.mUnit);
        this.rUnit = UnitScale.kilo;
    }
    
    public void calculate(){
        V = calculateSpeed();
        r = calculateRadius();
    }

    public BigDecimal calculateSpeed() {
        BigDecimal result = t.pow(2, NUMBER_LENGTH);
        result = result.add(t0.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(SPEED_OF_LIGHT, NUMBER_LENGTH);
        return V = result.divide(t, NUMBER_LENGTH);
    }

    public BigDecimal calculateMass() {
        BigDecimal result = t.pow(2, NUMBER_LENGTH);
        result = result.add(t0.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(SPEED_OF_LIGHT.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(r, NUMBER_LENGTH);
        result = result.divide(GRAVITY_CONST, NUMBER_LENGTH);
        return m = result.divide(t.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
    }

    public BigDecimal calculateRadius() {
        BigDecimal result = t.pow(2, NUMBER_LENGTH).multiply(GRAVITY_CONST, NUMBER_LENGTH).multiply(m, NUMBER_LENGTH);
        result = result.divide(SPEED_OF_LIGHT.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        BigDecimal dif = t.pow(2, NUMBER_LENGTH).subtract(t0.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        return r = result.divide(dif, NUMBER_LENGTH);
    }
    
    public BigDecimal calculateTime(){
        BigDecimal result = V.pow(2).divide(SPEED_OF_LIGHT.pow(2), NUMBER_LENGTH);
        result = new BigDecimal(1).add(result.multiply(new BigDecimal(-1), NUMBER_LENGTH), NUMBER_LENGTH);
        result = new BigDecimal(Math.sqrt(result.doubleValue()));
        return t = t0.divide(result, NUMBER_LENGTH);
    }
    
    public BigDecimal calculateTime0(){
        BigDecimal result = V.pow(2).divide(SPEED_OF_LIGHT.pow(2), NUMBER_LENGTH);
        result = new BigDecimal(1).add(result.multiply(new BigDecimal(-1), NUMBER_LENGTH), NUMBER_LENGTH);
        result = new BigDecimal(Math.sqrt(result.doubleValue()));
        return t0 = t.multiply(result, NUMBER_LENGTH);
    }

    private BigDecimal time2Seconds(BigDecimal time, TimeUnit unit) {
        switch (unit.name()) {
            case "years":
                return time.multiply(TimeUnit.days.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case "days":
                return time.multiply(TimeUnit.hours.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case "hours":
                return time.multiply(TimeUnit.minutes.getConversion(), NUMBER_LENGTH)
                        .multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            case "minutes":
                return time.multiply(TimeUnit.seconds.getConversion(), NUMBER_LENGTH);
            default:
                return time;
        }
    }

    private BigDecimal convertValue(BigDecimal value, UnitScale unit) {
        return value.multiply(unit.getUnit(), NUMBER_LENGTH);
    }
    
    private BigDecimal convertTime(BigDecimal value){
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
    
    private BigDecimal convertSpeed(BigDecimal value){
        BigDecimal counter = new BigDecimal(1, NUMBER_LENGTH), denominator = new BigDecimal(1, NUMBER_LENGTH);
        counter = convertValue(counter, rUnit);
        denominator = convertTime(denominator);
        return value.multiply(counter, NUMBER_LENGTH).divide(denominator, NUMBER_LENGTH);
    }
    

    public BigDecimal getT() {
        return convertTime(t);
    }

    public BigDecimal getT0() {
        return convertTime(t0);
    }

    public BigDecimal getV() {
        return convertSpeed(V);
    }

    public BigDecimal getM() {
        return convertValue(m, mUnit);
    }

    public BigDecimal getR() {
        return convertValue(r, rUnit);
    }

    public TimeUnit gettUnit() {
        return tUnit;
    }

    public UnitScale getmUnit() {
        return mUnit;
    }

    public UnitScale getrUnit() {
        return rUnit;
    }

    public BigDecimal getSPEED_OF_LIGHT() {
        return SPEED_OF_LIGHT;
    }

    public BigDecimal getGRAVITY_CONST() {
        return GRAVITY_CONST;
    }

    public BigDecimal getSUN_MASS() {
        return SUN_MASS;
    }

    @Override
    public String toString() {
        return "Calculation{" + "t=" + t + ", t0=" + t0 + ", V=" + V + ", m=" + m + ", r=" + r 
                + ", tUnit=" + tUnit + ", mUnit=" + mUnit + ", rUnit=" + rUnit 
                + ", SPEED_OF_LIGHT=" + SPEED_OF_LIGHT + ", GRAVITY_CONST=" + GRAVITY_CONST + ", SUN_MASS=" + SUN_MASS + '}';
    }
}