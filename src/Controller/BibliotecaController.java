package Controller;

import Main.DBaseConnector;
import Main.Main;
import Main.TableItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.converter.IntegerStringConverter;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BibliotecaController implements Initializable {

        Connection conn=DBaseConnector.getConnection();
        PreparedStatement statement;

    public Button FXDeleteButton;
    public Button FXSearch;
    public Button FXLend;
    public Button FXAddButton;
    public TextField FXTitleField;
    public TextField FXAuthorField;
    public TextField FXYearField;
    public TextField FXQuantityField;
    public TextField FXSearchField;

    public TableView<TableItems> FXTable;
    public TableColumn<TableItems, String> FXTableTitle;
    public TableColumn<TableItems, String> FXTableAuthor;
    public TableColumn<TableItems, Integer > FXTableYear;
    public TableColumn<TableItems, Integer> FXTableQuantity;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DBaseConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from  librarydatabase");

            while (rs.next()){
                observableList.add(new TableItems(
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getInt("Year"),
                        rs.getInt("Quantity")));
            }

        }catch (SQLException ex){
            Logger.getLogger(TableItems.class.getName()).log(Level.SEVERE,null,ex);
        }

        FXTableTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        FXTableAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        FXTableYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
        FXTableQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        FXTable.setItems(observableList);
        edit();
    }

     ObservableList<TableItems> observableList= FXCollections.observableArrayList();
     FilteredList<TableItems> filteredData=new FilteredList<>(observableList, e->true);

    public void edit(){
        FXTableTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        FXTableTitle.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(e.getNewValue());

        });
        FXTableAuthor.setCellFactory(TextFieldTableCell.forTableColumn());
        FXTableAuthor.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(e.getNewValue());
        });
        FXTableYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        FXTableYear.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(String.valueOf(e.getNewValue()));
        });
        FXTableQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        FXTableQuantity.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(String.valueOf(e.getNewValue()));
        });
        FXTable.setEditable(true);
    }

    public void doTheSearch() {

        FXSearchField.textProperty().addListener((value, oldValue, newValue) -> {
            filteredData.setPredicate((TableItems s)->{
                String list=newValue.toLowerCase();

                return s.getTitle().toLowerCase().contains(list)
                        || s.getAuthor().toLowerCase().contains(list)
                        ||String.valueOf(s.getYear()).toLowerCase().contains(list)
                        ||String.valueOf(s.getQuantity()).toLowerCase().contains(list);

            });

        });
        if(FXSearchField.getText().isEmpty()){
            FXTable.setItems(observableList);
        }
        else{
            FXTable.setItems(filteredData);
        }
    }

    public void buttonAddAction() {
        if (FXTitleField.getText().isEmpty() || FXAuthorField.getText().isEmpty() || FXYearField.getText().isEmpty() || FXQuantityField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all required fields!");
            alert.showAndWait();
        } else {
            try{
                String sql= "INSERT INTO librarydatabase (Title,Author,Year,Quantity) VALUES (?,?,?,?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1,FXTitleField.getText());
                statement.setString(2,FXAuthorField.getText());
                statement.setString(3, String.valueOf(Integer.parseInt(FXYearField.getText())));
                statement.setString(4, String.valueOf(Integer.parseInt(FXQuantityField.getText())));
                statement.execute();
                statement.close();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.showAndWait();

            }
            TableItems newRow = new TableItems(
                    FXTitleField.getText(),
                    FXAuthorField.getText(),
                    Integer.parseInt(FXYearField.getText()),
                    Integer.parseInt(FXQuantityField.getText()));

            FXTable.getItems().add(newRow);
        }
    }

    public void buttonDeleteAction() throws SQLException{
        int selectedIndex = FXTable.getSelectionModel().getSelectedIndex();
        String selectedItem = FXTableTitle.getCellData(selectedIndex);
        if(selectedIndex >= 0){
            Connection conn = DBaseConnector.getConnection();
            String delete = "DELETE FROM librarydatabase WHERE Title = ?";
            statement = conn.prepareStatement(delete);
            statement.setString(1, selectedItem);
            statement.execute();
            FXTable.getItems().remove(selectedIndex);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You have not selected an item to delete.");
            alert.showAndWait();
        }
    }


        public final String update = "UPDATE librarydatabase SET Title = ? WHERE Title = ?";
}
