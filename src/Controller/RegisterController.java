package Controller;

import Main.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {

    public TextField FXRegisterUsername;
    public PasswordField FXRegisterPassword;
    public PasswordField FXRegisterConfirmPassword;
    public Button FXRegisterButton;
    public ImageView FXImageRegister;

    public void registerActionButton() throws IOException {

        if (FXRegisterUsername.getText().isEmpty() || FXRegisterPassword.getText().isEmpty() || FXRegisterConfirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all required fields!");
            alert.showAndWait();

        }
        else if (!FXRegisterPassword.getText().equals(FXRegisterConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Passwords doesn't match!");
            alert.showAndWait();

        }
         else if(!(FXRegisterUsername.getText().isEmpty() || FXRegisterPassword.getText().isEmpty() || FXRegisterConfirmPassword.getText().isEmpty()) && !(!FXRegisterPassword.getText().equals(FXRegisterConfirmPassword.getText()))) {
            StringBuilder sb = new StringBuilder();
            sb.append(FXRegisterUsername.getText().toString() + " ");
            sb.append(FXRegisterPassword.getText().toString());
            sb.append("\n");

            FileWriter W = new FileWriter("Login.txt", true);
            W.write(sb.toString());
            W.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Account created sucessfully!");
            alert.showAndWait();
            Main.setLayout("LoginWindow");
        }

    }
}

