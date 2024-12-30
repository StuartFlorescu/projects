//neccessary Java/JavaFX imports in order to run the code
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class FirstNameCell extends TextFieldTableCell<Contacts, String> {

    public FirstNameCell() {
        // Call the constructor of TextFieldTableCell with a StringConverter
        // configures a StringConverter for converting the cell value to and from a string representation
        super(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        });
    }

    @Override
    // startEdit() method is an overridden method within a custom cell class
    // called when the user initiates the editing of a cell, typically by double-clicking or pressing the Enter key
    public void startEdit() {
        // Super call is responsible for setting the cell into the editing state
        // The superclass's implementation handles tasks like displaying the appropriate editing controls and managing the cell's visual state
        super.startEdit();
        TextField textField = getTextField();
        // input validation for the custom cell
        if (textField != null) {
            // Add a listener to the text property of the TextField
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                // if it is empty but is not null, set to red, indicating to user it is invalid input
                if (newValue != null && newValue.isEmpty()) {
                    textField.setStyle("-fx-control-inner-background: red;");
                } else {
                    // if everything checks out, set it to no style
                    textField.setStyle("");
                }
            });
        }
        // code to immediately check if valid input and wait for listener to do its thing and check every time you click
        String currentValue = getItem();
        if (currentValue != null && currentValue.isEmpty()) {
            textField.setStyle("-fx-control-inner-background: red;");
        }
    }
    // sets to red if necessary conditions are not met

    @Override
    // An overridden method within a custom cell class that extends a JavaFX TableCell class, 
    // called when the user has finished editing the cell and attempts to commit the changes
    public void commitEdit(String newValue) {
        // checks if the newValue (the edited value) is null or empty
        // if the user didn't enter any value or cleared the existing value, an alert is performed
        if (newValue.isEmpty()) {
            showAlert();
        } else {
            // invokes the superclass's commitEdit method, which is responsible for committing the edited value and updating the underlying data model with the new value
            super.commitEdit(newValue);
        }
    }

    @Override
    // a call to the superclass's updateItem method.  Purpose is to update the visual representation of the cell based on the data it should display
    // handles the default behavior for updating the cell
    // updates item if cell is not empty
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
        } else {
            setText(item);
        }
    }

    static boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number is valid
        return phoneNumber != null && phoneNumber.length() == 10 && phoneNumber.matches("\\d{10}");
    }

    private TextField getTextField() {
        // Get the TextField from the graphic property in order to set and display the input correctly
        return (TextField) this.getGraphic();
    }

    private void showAlert() {
        // Show an alert for an empty input
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("You have an empty input. Please enter a valid input.");
        alert.showAndWait();
    }
}
