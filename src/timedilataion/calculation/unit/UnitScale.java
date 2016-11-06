package timedilataion.calculation.unit;

import java.math.BigDecimal;

/**
 *
 * @author premik
 */
public enum UnitScale {
      jokto(-24)
    , zepto(-21)
    , atto(-18)
    , femto(-15)
    , piko(-12)
    , nano(-9)
    , mikro(-6)
    , mili(-3)
    , centy(-2)
    , decy(-1)
    , base(0)
    , deka(1)
    , hekto(2)
    , kilo(3)
    , mega(6)
    , giga(9)
    , tera(12)
    , peta(15)
    , eksa(18)
    , zetta(21)
    , jotta(24);
    
    private final int exponent;

    private UnitScale(int exponent) {
        this.exponent = exponent;
    }
    
    public BigDecimal getUnit() {
        return new BigDecimal(1).scaleByPowerOfTen(exponent);
    }
    
    public String fixedName() {
        if (base.name().equals(this.name())) {
            return "";
        }
        return this.name();
    }
    
    public static UnitScale getUnit(String value){
        if(isValueProper(value)){
            for(UnitScale unit : UnitScale.values()){
                if(value.equals(unit.name())) return unit;
            }
        } 
        return null;
    }
    
    private static boolean isValueProper(String value){
        if(value==null) return false;
        if("".equals(value)) return false;
        for(UnitScale unit : UnitScale.values()){
            if(value.equals(unit.name())) return true;
        }
        return false;
    }

    public BigDecimal convertTo(UnitScale unitScale2) {
        if (this.exponent * unitScale2.exponent > 0) {//obie ujemne i obie dodatnie
            int calculatedExponent = this.exponent - unitScale2.exponent;
            return new BigDecimal(1).scaleByPowerOfTen(calculatedExponent);
        }
        //jedna ujemna druga dodatnia
        if (this.exponent > unitScale2.exponent) {
            int calculatedExponent = Math.abs(this.exponent) + Math.abs(unitScale2.exponent);
            return new BigDecimal(1).scaleByPowerOfTen(calculatedExponent);
        }
        int calculatedExponent = this.exponent - unitScale2.exponent;
        return new BigDecimal(1).scaleByPowerOfTen(calculatedExponent);
    }
}
