package ass01CSC645;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//from   w  ww. j  ava  2 s.co  m
public class RGBSliders extends Application {

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.setTitle("RGB sliders");

		// Set the gridpane for labels, sliders, textfields, and rectangle
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(10);
		scene.setRoot(gPane);

		// Set the rectangle to view the current RGB color
		Rectangle rect = new Rectangle(0, 0, 200, 200);
		GridPane.setConstraints(rect, 3, 0, 1, 2);

		// A vertical box to hold the labels for the sliders
		VBox vBoxLabel = new VBox(30);
		GridPane.setConstraints(vBoxLabel, 0, 0);
		Label lblR = new Label("R");
		lblR.setMaxSize(10, 15);
		Label lblG = new Label("G");
		lblG.setMaxSize(10, 15);
		Label lblB = new Label("B");
		lblR.setMaxSize(10, 15);
		vBoxLabel.getChildren().addAll(lblR, lblG, lblB);

		// A label that contains the hex value of the sliders
		Label lblHexCode = new Label();
		GridPane.setConstraints(lblHexCode, 1, 3);
		GridPane.setHalignment(lblHexCode, HPos.CENTER);
		lblHexCode.setStyle("-fx-font: 24 arial;");
		lblHexCode.setText("#000000");

		// Create sliders and place in VBox.
		VBox vBoxSlide = new VBox(30);
		GridPane.setConstraints(vBoxSlide, 1, 0);
		Slider sliderR = new Slider(0, 255, 0);
		Slider sliderG = new Slider(0, 255, 0);
		Slider sliderB = new Slider(0, 255, 0);
		vBoxSlide.getChildren().addAll(sliderR, sliderG, sliderB);

		// Vbox for the textfields that contain the slider value and is editable to
		// change the slider value.
		VBox vBoxValue = new VBox(15);
		GridPane.setConstraints(vBoxValue, 2, 0);

		TextField rValue = new TextField(String.format("%.0f", sliderR.getValue()));
		rValue.setMaxSize(60, 15);

		TextField gValue = new TextField(String.format("%.0f", sliderG.getValue()));
		gValue.setMaxSize(60, 15);

		TextField bValue = new TextField(String.format("%.0f", sliderB.getValue()));
		bValue.setMaxSize(75, 15);

		vBoxValue.getChildren().addAll(rValue, gValue, bValue);

		// bind textfield to slider and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		rValue.textProperty().addListener((observable, oldValue, newValue) -> {
			if (Integer.parseInt(newValue) > 255 || newValue.length() > 3)
				rValue.setText(oldValue);
			sliderR.setValue(Integer.parseInt(newValue));
			Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
					Integer.parseInt(bValue.getText()));
			rect.setFill(color);
			lblHexCode.setText(toRGBCode(color));
		});

		// bind textfield to slider and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		gValue.textProperty().addListener((observable, oldValue, newValue) -> {
			if (Integer.parseInt(newValue) > 255 || newValue.length() > 3)
				gValue.setText(oldValue);
			sliderG.setValue(Integer.parseInt(newValue));
			Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
					Integer.parseInt(bValue.getText()));
			rect.setFill(color);
			lblHexCode.setText(toRGBCode(color));
		});

		// bind textfield to slider and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		bValue.textProperty().addListener((observable, oldValue, newValue) -> {
			if (Integer.parseInt(newValue) > 255 || newValue.length() > 3)
				bValue.setText(oldValue);
			sliderB.setValue(Integer.parseInt(newValue));
			Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
					Integer.parseInt(bValue.getText()));
			rect.setFill(color);
			lblHexCode.setText(toRGBCode(color));
		});

		// bind slider to textfield and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		sliderR.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				rValue.setText(String.format("%.0f", new_val));
				Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
						Integer.parseInt(bValue.getText()));
				rect.setFill(color);
				lblHexCode.setText(toRGBCode(color));
			}
		});

		// bind slider to textfield and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		sliderG.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				gValue.setText(String.format("%.0f", new_val));
				Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
						Integer.parseInt(bValue.getText()));
				rect.setFill(color);
				lblHexCode.setText(toRGBCode(color));
			}
		});

		// bind slider to textfield and change values between slider and textfield
		// updates the color of the rectangle and the hex value label
		sliderB.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				bValue.setText(String.format("%.0f", new_val));
				Color color = Color.rgb(Integer.parseInt(rValue.getText()), Integer.parseInt(gValue.getText()),
						Integer.parseInt(bValue.getText()));
				rect.setFill(color);
				lblHexCode.setText(toRGBCode(color));
			}
		});

		// add everything to gridpane
		gPane.getChildren().addAll(vBoxLabel, vBoxSlide, vBoxValue, rect, lblHexCode);

		// show the full grid.
		stage.show();

	}

	// converts the RGB values to a hexadecimal code.
	public static String toRGBCode(Color color) {
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));
	}

	public static void main(String[] args) {
		launch(args);
	}
}