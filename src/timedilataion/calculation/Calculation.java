package timedilataion.calculation;

import java.math.BigDecimal;
import timedilataion.bean.DilatationBean;
import timedilataion.converter.Converter;
import static timedilataion.calculation.Constants.*;

/**
 *
 * @author premik
 */
public class Calculation {
    
    private DilatationBean dilatationBean;

    public Calculation(DilatationBean dilatataionBean) {
        this.dilatationBean = dilatataionBean;
    }
    
    public void initCalculation() {
        dilatationBean.setT(Converter.time2Seconds(dilatationBean.getT(), dilatationBean.gettUnit()));
        dilatationBean.setT0(Converter.time2Seconds(dilatationBean.getT0(), dilatationBean.gettUnit()));
        dilatationBean.setM(Converter.convertValue(SUN_MASS, dilatationBean.getmUnit()));
        calculateSpeed();
        calculateRadius();
    }
    
    private void calculateSpeed() {
        BigDecimal result = dilatationBean.getT().pow(2, NUMBER_LENGTH);
        result = result.add(dilatationBean.getT0().pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(SPEED_OF_LIGHT, NUMBER_LENGTH);
        dilatationBean.setV(result.divide(dilatationBean.getT(), NUMBER_LENGTH));
    }

    private void calculateRadius() {
        BigDecimal result = dilatationBean.getT().pow(2, NUMBER_LENGTH)
                .multiply(GRAVITY_CONST, NUMBER_LENGTH).multiply(dilatationBean.getM(), NUMBER_LENGTH);
        result = result.divide(SPEED_OF_LIGHT.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        BigDecimal dif = dilatationBean.getT().pow(2, NUMBER_LENGTH)
                .subtract(dilatationBean.getT0().pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        dilatationBean.setR(result.divide(dif, NUMBER_LENGTH));
    }
    
    public void calculateMass() {
        BigDecimal result = dilatationBean.getT().pow(2, NUMBER_LENGTH);
        result = result.add(dilatationBean.getT0().pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(SPEED_OF_LIGHT.pow(2, NUMBER_LENGTH), NUMBER_LENGTH);
        result = result.multiply(dilatationBean.getR(), NUMBER_LENGTH);
        result = result.divide(GRAVITY_CONST, NUMBER_LENGTH);
        dilatationBean.setM(result.divide(dilatationBean.getT().pow(2, NUMBER_LENGTH), NUMBER_LENGTH));
    }
    
    public void calculateTime(){
        BigDecimal result = dilatationBean.getV().pow(2).divide(SPEED_OF_LIGHT.pow(2), NUMBER_LENGTH);
        result = new BigDecimal(1).add(result.multiply(new BigDecimal(-1), NUMBER_LENGTH), NUMBER_LENGTH);
        result = new BigDecimal(Math.sqrt(result.doubleValue()));
        dilatationBean.setT(dilatationBean.getT0().divide(result, NUMBER_LENGTH));
    }
    
    public void calculateTime0(){
        BigDecimal result = dilatationBean.getV().pow(2).divide(SPEED_OF_LIGHT.pow(2), NUMBER_LENGTH);
        result = new BigDecimal(1).add(result.multiply(new BigDecimal(-1), NUMBER_LENGTH), NUMBER_LENGTH);
        result = new BigDecimal(Math.sqrt(result.doubleValue()));
        dilatationBean.setT0(dilatationBean.getT().multiply(result, NUMBER_LENGTH));
    }
}