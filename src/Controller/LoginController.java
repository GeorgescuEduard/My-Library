	package Controller;

    import Main.Main;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.Button;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.scene.image.ImageView;
    import javafx.scene.text.Text;

    import java.awt.*;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.List;

    public class LoginController extends Component {

        public Button FXLoginButton;
        public TextField FXUsernameField;
        public PasswordField FXPasswordField;
        public Text FXQuestion;
        public ImageView FXBookshelf;
        public ImageView FXBackground;
        public ImageView FXID;


        public List<String[]> LIST;

        public void buttonLoginAction() throws IOException {
            BufferedReader buff = null;
            Boolean found = false;
            String str, splitLogin[];
            buff = new BufferedReader(new FileReader("Login.txt"));

                while ((str = buff.readLine()) != null) {
                    splitLogin = str.split(" ");
                    if (splitLogin[0].equals(FXUsernameField.getText()) == true && splitLogin[1].equals(String.valueOf(FXPasswordField.getText())) == true) {
                       found =true;
                        Main.setLayout("Biblioteca");
                    }
                }
                if(FXUsernameField.getText().isEmpty() || FXPasswordField.getText().isEmpty()) {
                    Alert alert= new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all required fields!");
                    alert.showAndWait();
                }
                    else  if (found == false) {
                        Alert alert= new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Username or password are incorect!");
                        alert.showAndWait();
                    }


        }

        public void buttonNewAction() throws IOException {
            Main.setLayout("RegisterWindow");
        }



        private void readData() throws IOException {
            String s;
            BufferedReader buff = new BufferedReader(new FileReader("Login.txt"));
            while ((s = buff.readLine()) != null) {
                String data[] = s.split(" ");
                LIST.add(data);
            }
        }

    }