package ass03CSC645;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private static final String FILENAME = ".\\out.txt";
    private static final StringBuilder stringBuilder = new StringBuilder();
    private static final StringBuilder stringBuilderAccepted = new StringBuilder();

    private double radius = 4;
    private static BufferedWriter writer;
    private GraphicsContext gc;
    private GraphicsContext maskCircle;
    private Canvas canvas;
    private Canvas mask;
    private ColorPicker colorPickerPoints;
    private ColorPicker colorPickerLines;
    private List<Circle> circles = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        writer = new BufferedWriter(new FileWriter(FILENAME, false));
        canvas = new Canvas(500, 400);
        mask = new Canvas(canvas.getWidth(), canvas.getHeight());
        gc = canvas.getGraphicsContext2D();
        maskCircle = mask.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.setLineWidth(2);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        maskCircle.setFill(Color.TRANSPARENT);
        maskCircle.setLineWidth(2);
        maskCircle.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        colorPickerLines = new ColorPicker();
        colorPickerLines.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gc.setStroke(colorPickerLines.getValue());
            }
        });

        colorPickerPoints = new ColorPicker();
        colorPickerPoints.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maskCircle.setStroke(colorPickerPoints.getValue());
            }
        });


        stringBuilder.append("startSymbol");

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        gc.beginPath();
                        stringBuilder.append(String.format("\nPRESSED: (%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        maskCircle.strokeOval(mouse.getX() - radius * 2, mouse.getY() - radius * 2, radius * 4, radius * 4);
                        gc.moveTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        stringBuilder.append(String.format("\n(%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        if (mouse.getX() % 15 == 0 || mouse.getY() % 15 == 0) {
                            maskCircle.strokeOval(mouse.getX() - radius, mouse.getY() - radius, radius * 2, radius * 2);
                        }
                        gc.lineTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        stringBuilder.append(String.format("\nRELEASED: (%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        maskCircle.strokeOval(mouse.getX() - radius * 2, mouse.getY() - radius * 2, radius * 4, radius * 4);
                    }
                });


        mask.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        gc.beginPath();
                        stringBuilder.append(String.format("\nPRESSED: (%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        maskCircle.strokeOval(mouse.getX() - radius * 2, mouse.getY() - radius * 2, radius * 4, radius * 4);
                        gc.moveTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        mask.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        stringBuilder.append(String.format("\n(%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        if (mouse.getX() % 15 == 0 || mouse.getY() % 15 == 0) {
                            maskCircle.strokeOval(mouse.getX() - radius, mouse.getY() - radius, radius * 2, radius * 2);
                        }
                        gc.lineTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        mask.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouse) {
                        stringBuilder.append(String.format("\nRELEASED: (%d, %d)", (int) mouse.getX(), (int) mouse.getY()));
                        maskCircle.strokeOval(mouse.getX() - radius * 2, mouse.getY() - radius * 2, radius * 4, radius * 4);
                    }
                });

        MenuBar menuBar = createMenu();
        CheckBox showPointsCheckBox = createShowPointsCheckBox();
        CheckBox showLinesCheckBox = createShowLinesCheckBox();
        showLinesCheckBox.setSelected(true);
        showPointsCheckBox.setSelected(true);
        Button acceptButton = createAcceptSampleButton();
        Button rejectButton = createRejectSampleButton();
        Button quitButton = createQuitButton();

        GridPane gridPane = new GridPane();

        GridPane.setHalignment(quitButton,HPos.CENTER);
        GridPane.setHalignment(rejectButton,HPos.CENTER);
        GridPane.setHalignment(acceptButton,HPos.CENTER);
        GridPane.setHalignment(showLinesCheckBox,HPos.CENTER);
        GridPane.setHalignment(showPointsCheckBox,HPos.CENTER);

        for (int i = 0; i < 5; ++i) {

            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 5);
            gridPane.getColumnConstraints().add(cc);
        }


        gridPane.getChildren().add(colorPickerLines);
        gridPane.getChildren().add(colorPickerPoints);
        gridPane.add(menuBar, 0, 0, 5, 1);
        gridPane.add(canvas, 0, 1, 5, 1);
        gridPane.add(mask, 0, 1, 5, 1);
        gridPane.add(showPointsCheckBox, 1, 2);
        gridPane.add(showLinesCheckBox, 3, 2);
        gridPane.add(acceptButton, 0, 3);
        gridPane.add(rejectButton, 2, 3);
        gridPane.add(quitButton, 4, 3);

        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setTitle("Program 03 -- Liam Tiemon");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                closeBufferWriter();
            }
        });
    }

    public MenuBar createMenu() {
        Menu menu = new Menu("Colors");
        MenuItem menuPointColor = new MenuItem("POINT Color");
        MenuItem menuLineColor = new MenuItem("LINE  Color");
        menu.getItems().add(menuPointColor);
        menuLineColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                colorPickerLines.show();
            }
        });
        menuPointColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                colorPickerPoints.show();
            }
        });
        menu.getItems().add(menuLineColor);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }


    public Button createAcceptSampleButton() {
        Button button = new Button("Accept");
        setButtonSize(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stringBuilder.append("\nendSymbol\n");
                try {
                    writer.write(stringBuilder.toString());
                    closeDrawing();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return button;
    }

    public Button createRejectSampleButton() {
        Button button = new Button("Reject");
        setButtonSize(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeDrawing();

            }
        });
        return button;
    }

    public Button createQuitButton() {
        Button button = new Button("Quit");
        setButtonSize(button);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeBufferWriter();
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();
            }
        });
        return button;
    }

    public CheckBox createShowPointsCheckBox() {
        CheckBox checkBox = new CheckBox("Points");
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    mask.setVisible(true);
                } else {
                    mask.setVisible(false);
                }
            }
        });
        return checkBox;
    }

    public CheckBox createShowLinesCheckBox() {
        CheckBox checkBox = new CheckBox("Lines");
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    canvas.setVisible(true);
                } else {
                    canvas.setVisible(false);
                }
            }
        });
        return checkBox;
    }


    public void setButtonSize(Button btn) {
        btn.setMinSize(75, 30);
    }

    private void closeBufferWriter() {
        try {
            System.out.println("closing");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeDrawing() {
        stringBuilder.setLength(0);
        stringBuilder.append("startSymbol");
        gc.setFill(Color.TRANSPARENT);
        gc.setLineWidth(2);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        maskCircle.setFill(Color.LIGHTGRAY);
        maskCircle.setLineWidth(2);
        maskCircle.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
