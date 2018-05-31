package ass02CSC645;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
	String[] btnLabels = {"0", ".", "=", "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "x", "AC", "+/-","%","/"};
	TextField textField = new TextField();
	
	Double leftSideValue;
	Double rightSideValue;
	
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
		}
        
        textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        
        for (int j = 0; j < colsInCalc; ++j) {

        	  ColumnConstraints cc = new ColumnConstraints();
        	  cc.setPercentWidth(100.0/colsInCalc);
        	  gPane.getColumnConstraints().add(cc);
        	}
        for (int j = 0; j < rowsInCalc; ++j) {

      	  RowConstraints rc = new RowConstraints();
      	  rc.setPercentHeight(100.0/rowsInCalc);
      	  gPane.getRowConstraints().add(rc);
      	}
        
        gPane.add(textField, 0, 0,4,1);
        gPane.add(btn[0], 0, 5, 2, 1);
        gPane.add(btn[1], 2, 5);
        gPane.add(btn[2], 3, 5);
        
        int index = 3;
        for (int row = 4; row >=1; --row) {
        	for (int col = 0; col < colsInCalc; ++col) {
    			gPane.add(btn[index], col, row);
    			++index;
    		}
		}
        stage.setScene(new Scene(gPane,400,600));
        stage.show();
    }
    
    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(final ActionEvent event) {

        	for (int i = 0; i < btn.length; i++) {
				if(event.getSource() == btn[i]) {
					if (btn[i].getText().equals("+")) {
						leftSideValue = Double.parseDouble(textField.getText());
						textField.setText("");
						addBool = true;
						subBool = false;
						divBool = false;
					    mulBool = false;
					} else if (btn[i].getText().equals("-")) {
						leftSideValue = Double.parseDouble(textField.getText());
						textField.setText("");
						addBool = false;
						subBool = true;
						divBool = false;
					    mulBool = false;
					} else if (btn[i].getText().equals("/")) {
						leftSideValue = Double.parseDouble(textField.getText());
						textField.setText("");
						addBool = false;
						subBool = false;
						divBool = true;
					    mulBool = false;
					} else if (btn[i].getText().equals("*")) {
						leftSideValue = Double.parseDouble(textField.getText());
						textField.setText("");
						addBool = false;
						subBool = false;
						divBool = false;
					    mulBool = true;
					} else if (btn[i].getText().equals("=")) {
						 rightSideValue = Double.parseDouble(textField.getText());
				            if (addBool == true)
				            	rightSideValue = rightSideValue + leftSideValue;
				            else if ( subBool == true)
				            	rightSideValue = rightSideValue - leftSideValue;
				            else if ( mulBool == true)
				            	rightSideValue = rightSideValue * leftSideValue;
				            else if ( divBool == true)
				            	rightSideValue = rightSideValue / leftSideValue;
				            textField.setText(  Double.toString(rightSideValue));

				            addBool = false;
				            subBool = false;
				            mulBool = false;
				            divBool = false;
					} else {
						textField.setText(textField.getText() + btn[i].getText());
					}
						
				}
	                	
			}
        }
    };
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
