package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

	 static Parent root;
	 static Scene scene;
	 static Stage stage;
	 static Main self;
	
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        Main.stage =primaryStage;
        Main.self=this;
        setLayout();
    }

    public static void setLayout()throws IOException{
        Main.setLayout("LoginWindow");
    }

    public static void setLayout(String layout) throws IOException{
    	Parent root =FXMLLoader.load(Main.self.getClass().getResource(String.format("/FXML/%s.fxml", layout)));

    	Main.root =root;
    	Main.scene =new Scene(root);
    	Main.stage.setScene(Main.scene);
    	Main.stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
