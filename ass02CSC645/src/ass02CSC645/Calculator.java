package ass02CSC645;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Calculator extends Application {

	Button[] btn = new Button[19];
	String[] btnLabels = { "0", ".", "=", "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "x", "AC", "+/-", "%",
			"/" };
	TextField textField = new TextField();

	Double leftSideValue = null;
	Double rightSideValue = null;

	Boolean addBool = false;
	Boolean subBool = false;
	Boolean divBool = false;
	Boolean mulBool = false;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Calculator");

		GridPane gPane = new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());

		int rowsInCalc = 6, colsInCalc = 4;

		for (int i = 0; i < 19; i++) {
			btn[i] = new Button(btnLabels[i]);
			btn[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			btn[i].setOnAction(myHandler);
			btn[i].setFocusTraversable(false);
		}

		textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		textField.setAlignment(Pos.CENTER_RIGHT);
		textField.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[\\d.]*")) {
					textField.setText(newValue.replaceAll("[^\\d.]", ""));
				}
			}
		});
		
		

		for (int j = 0; j < colsInCalc; ++j) {

			ColumnConstraints cc = new ColumnConstraints();
			cc.setPercentWidth(100.0 / colsInCalc);
			gPane.getColumnConstraints().add(cc);
		}
		for (int j = 0; j < rowsInCalc; ++j) {

			RowConstraints rc = new RowConstraints();
			rc.setPercentHeight(100.0 / rowsInCalc);
			gPane.getRowConstraints().add(rc);
		}

		gPane.add(textField, 0, 0, 4, 1);
		gPane.add(btn[0], 0, 5, 2, 1);
		gPane.add(btn[1], 2, 5);
		gPane.add(btn[2], 3, 5);

		int index = 3;
		for (int row = 4; row >= 1; --row) {
			for (int col = 0; col < colsInCalc; ++col) {
				gPane.add(btn[index], col, row);
				++index;
			}
		}
		stage.setScene(new Scene(gPane, 400, 600));
		stage.show();
	}

	final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(final ActionEvent event) {

			for (Button button : btn) {
				if (event.getSource() == button) {
					if (button.getText().equals("+")) {
						if (!textField.getText().equals("")) {
							leftSideValue = Double.parseDouble(textField.getText());
							textField.setText("");
						}
						addBool = true;
						subBool = false;
						divBool = false;
						mulBool = false;

					} else if (button.getText().equals("-")) {
						if (!textField.getText().equals("")) {
							leftSideValue = Double.parseDouble(textField.getText());
							textField.setText("");
						}
						addBool = false;
						subBool = true;
						divBool = false;
						mulBool = false;

					} else if (button.getText().equals("/")) {
						if (!textField.getText().equals("")) {
							leftSideValue = Double.parseDouble(textField.getText());
							textField.setText("");
						}
						addBool = false;
						subBool = false;
						divBool = true;
						mulBool = false;

					} else if (button.getText().equals("x")) {
						if (!textField.getText().equals("")) {
							leftSideValue = Double.parseDouble(textField.getText());
							textField.setText("");
						}
						addBool = false;
						subBool = false;
						divBool = false;
						mulBool = true;

					} else if (button.getText().equals("AC")) {
						textField.setText("");
						leftSideValue = null;
						addBool = false;
						subBool = false;
						divBool = false;
						mulBool = false;
					} else if (button.getText().equals("+/-")) {
						if (textField.getText().contains("-"))
							textField.setText(textField.getText(1, textField.getLength()));
						else
							textField.setText("-" + textField.getText());

					} else if (button.getText().equals("%")) {
						try {
							if (!textField.getText().equals(null)) {
								leftSideValue = Double.parseDouble(textField.getText());
								leftSideValue = leftSideValue / 100.0;
								textField.setText(leftSideValue.toString());

							}
						} catch (NumberFormatException nfe) {
							showErrorMessage(nfe.getMessage());
						}
					} else if (button.getText().equals(".")){
						if(textField.getText().contains("."))
							break;
						else
							textField.setText(textField.getText() + button.getText());
					}
					else if (button.getText().equals("=")) {
						equalFunction();
					} else {
						textField.setText(textField.getText() + button.getText());
					}

				}

			}
		}
	};

	public void equalFunction() {
		if ((textField.getText().equals("0") || textField.getText().equals("")) && divBool == true) {
			showErrorMessage("Cannot Divide by zero");
			textField.setText("");
			return;
		}
		rightSideValue = Double.parseDouble(textField.getText());
		if (addBool == true)
			rightSideValue = leftSideValue + rightSideValue;
		else if (subBool == true)
			rightSideValue = leftSideValue - rightSideValue;
		else if (mulBool == true)
			rightSideValue = leftSideValue * rightSideValue;
		else if (divBool == true)
			rightSideValue = leftSideValue / rightSideValue;
		textField.setText(Double.toString(rightSideValue));

		addBool = false;
		subBool = false;
		mulBool = false;
		divBool = false;
	}
	
	public static void showErrorMessage(String errorMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(errorMessage);

		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
