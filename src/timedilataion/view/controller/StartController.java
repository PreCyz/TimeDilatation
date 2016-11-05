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
import timedilataion.calculations.Calculation;
import timedilataion.calculations.units.TimeUnit;
import timedilataion.calculations.units.UnitScale_old;

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

    private final String G = "grams";
    private final String M = "meters";
    private final short DEFAULT_SECONDS_IDX = 3;
    private final short DEFAULT_GRAMS_IDX = 10;
    private final short DEFAULT_METERS_IDX = DEFAULT_GRAMS_IDX;
    private Calculation calc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calc = new Calculation(new BigDecimal(8), new BigDecimal(3), TimeUnit.days.name());
        calc.calculate();
        for(TimeUnit unit : TimeUnit.values()){
            timeBox.getItems().add(unit.name());
        }
        for(UnitScale_old unit : UnitScale_old.values()){
            massBox.getItems().add(unit.fixedName() + G);
            distanceBox.getItems().add(unit.fixedName() + M);
        }
        timeBox.getSelectionModel().select(DEFAULT_SECONDS_IDX);
        massBox.getSelectionModel().select(DEFAULT_GRAMS_IDX);
        distanceBox.getSelectionModel().select(DEFAULT_METERS_IDX);

        timeSlider.setValue(calc.getT().doubleValue());
        timeValueLabel.setText(timeSlider.getValue() + " [" + timeBox.getValue().toString() + "]");
        time0Slider.setValue(calc.getT0().doubleValue());
        time0ValueLabel.setText(time0Slider.getValue() + " [" + timeBox.getValue().toString() + "]");
        speedSlider.setValue(calc.getV().doubleValue());
        speedValueLabel.setText(speedSlider.getValue() + " [" + distanceBox.getValue().toString() + "/" + timeBox.getValue().toString() + "]");
        massValueLabel.setText(massSlider.getValue() + " [" + massBox.getValue().toString() + "]");
        radiusValueLabel.setText(radiusSlider.getValue() + " [" + distanceBox.getValue().toString() + "]");

        //choiceBox value change listeners
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
        massBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                massValueLabel.setText(Math.round(massSlider.getValue()) + " [" + massBox.getItems().get(newValue.intValue()) + "]");
            }
        });
        distanceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radiusValueLabel.setText(Math.round(radiusSlider.getValue()) + " [" + distanceBox.getItems().get(newValue.intValue()) + "]");
                speedValueLabel.setText(Math.round(speedSlider.getValue()) + " [" + distanceBox.getItems().get(newValue.intValue()) + "/" + timeBox.getValue().toString() + "]");
            }
        });
        //sliders value change listeners

        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + timeBox.getValue() + "]");
                speedSlider.setValueChanging(true);
                calc = new Calculation(new BigDecimal(newValue.doubleValue()), new BigDecimal(time0Slider.getValue()), timeBox.getValue().toString());
                calc.calculate();
                speedSlider.setValue(calc.getV().doubleValue());
            }
        });
        
        time0Slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String txt = String.format("%d [%s]", Math.round(newValue.doubleValue()), timeBox.getValue());
                time0ValueLabel.setText(txt);
            }
        });
        
        speedSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                speedValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + distanceBox.getValue().toString() + "/" + timeBox.getValue().toString() + "]");
            }
        });
        
        massSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                massValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + massBox.getValue().toString() + "]");
            }
        });
        
        radiusSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radiusValueLabel.setText(Math.round(newValue.doubleValue()) + " [" + distanceBox.getValue().toString() + "]");
            }
        });
    }
    
    private String text(double[] doubles, String[] strings) {
        StringBuilder result = new StringBuilder();
        Arrays.asList(doubles).stream().forEach(value -> result.append(value).append(" "));
        result.append("[");
        Arrays.asList(strings).stream().forEach(value -> result.append(value).append("/"));
        String msg = result.toString();
        return msg.substring(0, msg.length()-1);
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

}
