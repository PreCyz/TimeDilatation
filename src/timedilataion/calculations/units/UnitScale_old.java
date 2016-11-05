package timedilataion.calculations.units;

import java.math.BigDecimal;

/**
 *
 * @author premik
 */
public enum UnitScale_old {
      jokto(new BigDecimal(1).scaleByPowerOfTen(-24))
    , zepto(new BigDecimal(1).scaleByPowerOfTen(-21))
    , atto(new BigDecimal(1).scaleByPowerOfTen(-18))
    , femto(new BigDecimal(1).scaleByPowerOfTen(-15))
    , piko(new BigDecimal(1).scaleByPowerOfTen(-12))
    , nano(new BigDecimal(1).scaleByPowerOfTen(-9))
    , mikro(new BigDecimal(1).scaleByPowerOfTen(-6))
    , mili(new BigDecimal(1).scaleByPowerOfTen(-3))
    , centy(new BigDecimal(1).scaleByPowerOfTen(-2))
    , decy(new BigDecimal(1).scaleByPowerOfTen(-1))
    , base(new BigDecimal(1))
    , deka(new BigDecimal(1).scaleByPowerOfTen(1))
    , hekto(new BigDecimal(1).scaleByPowerOfTen(2))
    , kilo(new BigDecimal(1).scaleByPowerOfTen(3))
    , mega(new BigDecimal(1).scaleByPowerOfTen(6))
    , giga(new BigDecimal(1).scaleByPowerOfTen(9))
    , tera(new BigDecimal(1).scaleByPowerOfTen(12))
    , peta(new BigDecimal(1).scaleByPowerOfTen(15))
    , eksa(new BigDecimal(1).scaleByPowerOfTen(18))
    , zetta(new BigDecimal(1).scaleByPowerOfTen(21))
    , jotta(new BigDecimal(1).scaleByPowerOfTen(24));
    
    private final BigDecimal conversion;

    private UnitScale_old(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public BigDecimal getConversion() {
        return conversion;
    }
    
    public String fixedName() {
        if (this.name().equals(base.name())) {
            return "";
        }
        return this.name();
    }
    
    public static UnitScale_old getUnit(String value){
        if(isValueProper(value)){
            for(UnitScale_old unit : UnitScale_old.values()){
                if(value.equals(unit.name())) return unit;
            }
        } 
        return null;
    }
    
    private static boolean isValueProper(String value){
        if(value==null) return false;
        if("".equals(value)) return false;
        for(UnitScale_old unit : UnitScale_old.values()){
            if(value.equals(unit.name())) return true;
        }
        return false;
    }
}
