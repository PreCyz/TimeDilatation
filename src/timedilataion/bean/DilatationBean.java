package timedilataion.bean;

import java.math.BigDecimal;
import timedilataion.calculation.unit.TimeUnit;
import timedilataion.calculation.unit.UnitScale;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-11-05 20:57:35
 */
public class DilatationBean {
    
    private BigDecimal t; //default [s]
    private BigDecimal t0;//default [s]
    private BigDecimal V;//default [m/s]
    private BigDecimal m;//default [kg]
    private BigDecimal r;//default [m]
    
    private TimeUnit tUnit;
    private UnitScale mUnit;
    private UnitScale rUnit;

    private DilatationBean() {}
    
    private DilatationBean(BigDecimal t, BigDecimal t0, BigDecimal V, BigDecimal m, BigDecimal r, 
            UnitScale mUnit, UnitScale rUnit, TimeUnit tUnit) {
        this.t = t;
        this.t0 = t0;
        this.V = V;
        this.m = m;
        this.r = r;
        this.mUnit = mUnit;
        this.rUnit = rUnit;
        this.tUnit = tUnit;
    }

    public BigDecimal getT() {
        return t;
    }

    public BigDecimal getT0() {
        return t0;
    }

    public BigDecimal getV() {
        return V;
    }

    public BigDecimal getM() {
        return m;
    }

    public BigDecimal getR() {
        return r;
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

    public void setT(BigDecimal t) {
        this.t = t;
    }

    public void setT0(BigDecimal t0) {
        this.t0 = t0;
    }

    public void setV(BigDecimal V) {
        this.V = V;
    }

    public void setM(BigDecimal m) {
        this.m = m;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public void settUnit(TimeUnit tUnit) {
        this.tUnit = tUnit;
    }

    public void setmUnit(UnitScale mUnit) {
        this.mUnit = mUnit;
    }

    public void setrUnit(UnitScale rUnit) {
        this.rUnit = rUnit;
    }

    @Override
    public String toString() {
        return "DilatationBean{" + "t=" + t + ", t0=" + t0 + ", V=" + V + ", m=" + m + ", r=" + r + 
                ", tUnit=" + tUnit + ", mUnit=" + mUnit + ", rUnit=" + rUnit + '}';
    }
    
    public static class DilatationBuilder {

        private BigDecimal t;
        private BigDecimal t0;
        private BigDecimal V;
        private BigDecimal m;
        private BigDecimal r;
        private UnitScale mUnit;
        private UnitScale rUnit;
        private TimeUnit tUnit;

        public DilatationBuilder() {
        }

        public DilatationBuilder T(BigDecimal t) {
            this.t = t;
            return this;
        }

        public DilatationBuilder T0(BigDecimal t0) {
            this.t0 = t0;
            return this;
        }

        public DilatationBuilder V(BigDecimal V) {
            this.V = V;
            return this;
        }

        public DilatationBuilder M(BigDecimal m) {
            this.m = m;
            return this;
        }

        public DilatationBuilder R(BigDecimal r) {
            this.r = r;
            return this;
        }

        public DilatationBuilder massScale(UnitScale mUnit) {
            this.mUnit = mUnit;
            return this;
        }

        public DilatationBuilder radiusScale(UnitScale rUnit) {
            this.rUnit = rUnit;
            return this;
        }
        
        public DilatationBuilder timeUnit(TimeUnit tUnit) {
            this.tUnit = tUnit;
            return this;
        }

        public DilatationBean build() {
            if (tUnit == null) {
                tUnit = TimeUnit.years;
            }
            return new DilatationBean(t, t0, V, m, r, mUnit, rUnit, tUnit);
        }
    }
}
