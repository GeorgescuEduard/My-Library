package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TableItems {

    private SimpleStringProperty Title;
    private SimpleStringProperty Author;
    private SimpleIntegerProperty Year;
    private SimpleIntegerProperty Quantity;

    public TableItems( String Title, String Author,Integer Year,Integer Quantity) {
        this.Title = new SimpleStringProperty(Title);
        this.Author = new SimpleStringProperty(Author);
        this.Year = new SimpleIntegerProperty(Year);
        this.Quantity=new SimpleIntegerProperty(Quantity);
    }

    public String getTitle() {
        return Title.get();
    }
    public void setTitle(String Title) {
        this.Title = new SimpleStringProperty(Title);
    }

    public String getAuthor(){
        return Author.get();
    }
    public void setAuthor(String Author) {
        this.Author = new SimpleStringProperty(Author);
    }

    public int getYear() {
        return Year.get();
    }
    public void setYear(int Year) {
        this.Year = new SimpleIntegerProperty(Year);
    }

    public int getQuantity(){
        return Quantity.get();
    }
    public void setQuantity(int Quantity){
        this.Quantity =new SimpleIntegerProperty(Quantity);
    }
}