package midtermProb4CSC645;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Prob4 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(400, 400);
        final GraphicsContext gc = canvas.getGraphicsContext2D();


        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent mouse) {
                        gc.beginPath();
                        System.out.println(String.format("PRESSED: (%d, %d)",(int)mouse.getX(),(int)mouse.getY()));
                        gc.moveTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent mouse) {
                        System.out.println(String.format("(%d, %d)",(int)mouse.getX(),(int)mouse.getY()));
                        gc.lineTo(mouse.getX(), mouse.getY());
                        gc.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent mouse) {
                        System.out.println(String.format("RELEASED: (%d, %d)",(int)mouse.getX(),(int)mouse.getY()));
                    }
                });


        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
