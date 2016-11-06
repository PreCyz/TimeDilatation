package timedilataion.view.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import timedilataion.bean.DilatationBean;
import timedilataion.calculation.Calculation;
import timedilataion.calculation.unit.TimeUnit;
import timedilataion.calculation.unit.UnitScale;
import timedilataion.converter.Converter;

/**
 *
 * @author premik
 */
public class StartController implements Initializable {

    @FXML private Slider timeSlider;
    @FXML private Slider time0Slider;
    @FXML private Slider speedSlider;
    @FXML private Slider massSlider;
    @FXML private Slider radiusSlider;
    @FXML private LineChart timeDylatationChart;
    @FXML private ChoiceBox timeBox;
    @FXML private ChoiceBox massBox;
    @FXML private ChoiceBox distanceBox;
    @FXML private Label timeValueLabel;
    @FXML private Label time0ValueLabel;
    @FXML private Label speedValueLabel;
    @FXML private Label massValueLabel;
    @FXML private Label radiusValueLabel;

    private final String GRAMS = "grams";
    private final String METERS = "meters";
    private final short DEFAULT_SECONDS_IDX = 3;
    private final short DEFAULT_GRAMS_IDX = 10;
    private final short DEFAULT_METERS_IDX = DEFAULT_GRAMS_IDX;
    private final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.days;
    private final UnitScale DEFAULT_UNIT_SCALE = UnitScale.kilo;
    private Calculation calc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DilatationBean dilatation = createDilatationBean();
        initCalculation(dilatation);
        
        setUpTimeBox();
        setUpMassAndDistanceBox();

        setValuesAfterInitCalculations(dilatation);

        addValueChangeListeners();
    }
    
    private DilatationBean createDilatationBean() {
        BigDecimal t = new BigDecimal(8);
        BigDecimal t0 = new BigDecimal(3);
        return new DilatationBean.DilatationBuilder()
                .T(t)
                .T0(t0)
                .timeUnit(DEFAULT_TIME_UNIT)
                .massScale(DEFAULT_UNIT_SCALE)
                .radiusScale(DEFAULT_UNIT_SCALE)
                .build();
    }
    
    private void initCalculation(DilatationBean dilatation) {
        calc = new Calculation(dilatation);
        calc.initCalculation();
    }
    
    private void setUpTimeBox() {
        for(TimeUnit unit : TimeUnit.values()){
            timeBox.getItems().add(unit.name());
        }
        timeBox.getSelectionModel().select(DEFAULT_SECONDS_IDX);
    }
    
    private void setUpMassAndDistanceBox() {
        for(UnitScale unit : UnitScale.values()){
            massBox.getItems().add(unit.fixedName() + GRAMS);
            distanceBox.getItems().add(unit.fixedName() + METERS);
        }
        massBox.getSelectionModel().select(DEFAULT_GRAMS_IDX);
        distanceBox.getSelectionModel().select(DEFAULT_METERS_IDX);
    }
    
    private void setValuesAfterInitCalculations(DilatationBean dilatation) {
        timeSlider.setValue(Converter.convertTime(dilatation.getT(), DEFAULT_TIME_UNIT).doubleValue());
        timeValueLabel.setText(timeSlider.getValue() + " [" + timeBox.getValue().toString() + "]");
        time0Slider.setValue(Converter.convertTime(dilatation.getT0(), DEFAULT_TIME_UNIT).doubleValue());
        time0ValueLabel.setText(time0Slider.getValue() + " [" + timeBox.getValue().toString() + "]");
        speedSlider.setValue(Converter.convertSpeed(dilatation.getV(), UnitScale.base, DEFAULT_TIME_UNIT ).doubleValue());
        speedValueLabel.setText(
            speedSlider.getValue() + " [" + distanceBox.getValue().toString() + "/" + timeBox.getValue().toString() + "]"
        );
        massValueLabel.setText(massSlider.getValue() + " [" + massBox.getValue().toString() + "]");
        radiusValueLabel.setText(radiusSlider.getValue() + " [" + distanceBox.getValue().toString() + "]");
    }

    private void addValueChangeListeners() {
        addValueChangeListenerForTimeBox();
        addValueChangeListenerForMassBox();
        addValueChangeListenerForDistanceBox();

        addValueChangeListenerForTimeSlider();
        addValueChangeListenerForTie0Slider();
        addValueChangeListenerForSpeedSlider();
        addValueChangeListenerForMassSlider();
        addValueChangeListenerForRadiusSlider();
    }

    private void addValueChangeListenerForTimeBox() {
        timeBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeValueLabel.setText(Math.round(timeSlider.getValue()) + " [" + timeBox.getItems().get(newValue.intValue()) + "]");
                time0ValueLabel.setText(Math.round(time0Slider.getValue()) + " [" + timeBox.getItems().get(newValue.intValue()) + "]");
                speedValueLabel.setText(Math.round(speedSlider.getValue()) + " [" + distanceBox.getValue().toString() + "/" + timeBox.getItems().get(newValue.intValue()) + "]");
                changeSlider(timeSlider, newValue.intValue());
                changeSlider(time0Slider, newValue.intValue());
            }
        });
    }
    
    private void changeSlider(Slider tSlider, int option) {
        switch (option) {
            case 2:
                tSlider.setMax(23);
                tSlider.setBlockIncrement(1);
                tSlider.setMajorTickUnit(23);
                tSlider.setMinorTickCount(22);
                break;
            case 3:
                tSlider.setMax(364);
                tSlider.setBlockIncrement(1);
                tSlider.setMajorTickUnit(50);
                tSlider.setMinorTickCount(4);
                break;
            case 4:
                tSlider.setMax(1000);
                tSlider.setBlockIncrement(1);
                tSlider.setMajorTickUnit(200);
                tSlider.setMinorTickCount(4);
                break;
            default:
                tSlider.setMax(60);
                tSlider.setBlockIncrement(1);
                tSlider.setMajorTickUnit(10);
                tSlider.setMinorTickCount(4);
        }
    }
    
    private void addValueChangeListenerForDistanceBox() {
        distanceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radiusValueLabel.setText(Math.round(radiusSlider.getValue()) + " [" + distanceBox.getItems().get(newValue.intValue()) + "]");
                speedValueLabel.setText(Math.round(speedSlider.getValue()) + " [" + distanceBox.getItems().get(newValue.intValue()) + "/" + timeBox.getValue().toString() + "]");
            }
        });
    }
    
    private void addValueChangeListenerForMassBox() {
        massBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                massValueLabel.setText(Math.round(massSlider.getValue()) + " [" + massBox.getItems().get(newValue.intValue()) + "]");
            }
        });
    }
    private void addValueChangeListenerForRadiusSlider() {
        radiusSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radiusValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + distanceBox.getValue().toString() + "]");
            }
        });
    }

    private void addValueChangeListenerForTimeSlider() {
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + timeBox.getValue() + "]");
                speedSlider.setValueChanging(true);
                DilatationBean dilatataion = new DilatationBean.DilatationBuilder()
                        .T(new BigDecimal(newValue.doubleValue()))
                        .T0(new BigDecimal(time0Slider.getValue()))
                        .timeUnit(TimeUnit.valueOf(timeBox.getValue().toString()))
                        .build();
                initCalculation(dilatataion);
                speedSlider.setValue(
                        Converter.convertSpeed(dilatataion.getV(), UnitScale.base, DEFAULT_TIME_UNIT).doubleValue()
                );
            }
        });
    }
    
    private void addValueChangeListenerForTie0Slider() {
        time0Slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String txt = String.format("%d [%s]", Math.round(newValue.doubleValue()), timeBox.getValue());
                time0ValueLabel.setText(txt);
            }
        });
    }
    
    private void addValueChangeListenerForSpeedSlider() {
        speedSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                speedValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + distanceBox.getValue().toString() + "/" + timeBox.getValue().toString() + "]");
            }
        });
    }
    
    private void addValueChangeListenerForMassSlider() {
        massSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                massValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + massBox.getValue().toString() + "]");
            }
        });
    }
}
