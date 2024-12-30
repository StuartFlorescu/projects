//neccessary Java/JavaFX imports in order to run the code
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsApp extends Application {
    // Declare UI components
    private Button btnAdd;
    private Button btnDel;
    private Button btnSave;
    private Button btnAddSecondary;
    private TextField firstName;
    private TextField lastName;
    private TextField address;
    private TextField city;
    private TextField postalCode;
    private TextField number;
    private int maxAdds = 1;
    private String variable;

    // Helper method to read a file and return its contents as an ArrayList of
    // strings. This will make the contents saved and copied onto the csv file
    public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                list.add(line);
            }
        }
        return list;
    }

    //launch the args and start the program
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Tableview logic. This will create a table with all the data to be collected for a contact
        TableView<Contacts> tableView = new TableView<>();
        tableView.setEditable(true);

        // Each code of block will create table columns for each specific attribute (6 of them) and set cell value factories and event handlers for editing
        // Each specific block will also use the classes that use custom FactoryCells that inherit from the super class accordingly
        TableColumn<Contacts, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("first"));
        column1.setCellFactory(param -> new FirstNameCell());
        // edit logic for when the cell is edited and inputs of the data are changed
        column1.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setFirst(event.getNewValue());
        });

        TableColumn<Contacts, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("last"));
        column2.setCellFactory(param -> new LastNameCell());
        column2.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setLast(event.getNewValue());
        });

        TableColumn<Contacts, String> column3 = new TableColumn<>("Address");
        column3.setCellValueFactory(new PropertyValueFactory<>("address"));
        column3.setCellFactory(param -> new AddressCell());
        column3.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setAddress(event.getNewValue());
        });

        TableColumn<Contacts, String> column4 = new TableColumn<>("City");
        column4.setCellValueFactory(new PropertyValueFactory<>("city"));
        column4.setCellFactory(param -> new CityCell());
        column4.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setCity(event.getNewValue());
        });

        TableColumn<Contacts, String> column5 = new TableColumn<>("Postal Code");
        column5.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        column5.setCellFactory(param -> new PostalCodeCell());
        column5.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setPostalCode(event.getNewValue());
        });

        TableColumn<Contacts, String> column6Big = new TableColumn<>("Phone Numbers");
        column6Big.setCellValueFactory(new PropertyValueFactory<>("number"));
        column6Big.setCellFactory(param -> new PhoneNumberCell());
        column6Big.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setNumber(event.getNewValue());
        });

        TableColumn<Contacts, String> column6 = new TableColumn<>("Number");
        column6.setCellValueFactory(new PropertyValueFactory<>("number"));
        column6.setCellFactory(param -> new PhoneNumberCell());
        column6.setOnEditCommit(event -> {
            Contacts contact = event.getRowValue();
            contact.setNumber(event.getNewValue());
        });

        //add each of the newly added columns to the table so that it can be shown
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6Big);
        column6Big.getColumns().add(column6);

        // Read contact information from a file and populate the TableView
        ArrayList<String> rows = readFile("src/info.csv");

        // Variable to keep track of the maximum number of additional columns (phone numbers) in a row. 
        // This is critical in order to create new columns if the csv file already has an additional (ie. 2nd Phone Number) already and a column must be seen in the UI
        int maxColumns = 0;

        // Iterate through each row in the file
        for (String row : rows) {
            // Split the row into fields using comma as the delimiter
            String[] field = row.split(",");

            // Create a new Contacts object with the values from the fields
            Contacts contact = new Contacts(field[0], field[1], field[2], field[3], field[4], field[5]);

            // Update the maxColumns if the current row has more additional columns (phone
            // numbers) that might be saved already in the csv file
            if ((field.length - 5) > maxColumns) {
                maxColumns = field.length - 6;
            }

            // Iterate through the additional columns (phone numbers) starting from index 6. Checks for all the additional phone numbers in the csv
            for (int i = 6; i < field.length; i++) {
                // Add each phone number present in the csv to the Contacts object
                contact.addNumber(field[i]);
            }

            // Add the entire Contacts object to the TableView
            tableView.getItems().add(contact);
        }
        // Iterate through the maximum number of additional columns (phone numbers)
        for (int i = 0; i < maxColumns; i++) {
            // following code is the same for adding a new column. Is neccessary because if there is already an additional 2nd phone number in the csv file a new column must be added to accomodate it
            // this code adds this column and uses the same logic of the button, hence the similarity 
            // Increment maxAdds by 1
            maxAdds += 1;

            // Ternary operator used to determine the variable based on the value of maxAdds, is used for "2nd" and "3rd"
            String variable = maxAdds == 2 ? "nd" : "rd";

            // Create a new TableColumn with a label based on the value of maxAdds and variable (2nd or 3rd)
            TableColumn<Contacts, String> newColumn = new TableColumn<>(maxAdds + variable + " Number");

            // Set the cell value factory for the new column
            newColumn.setCellValueFactory(cellData -> {
                Contacts contact = cellData.getValue();
                String value = "";

                // Retrieve the column index of the new column in the column6Big
                int columnIndex = column6Big.getColumns().indexOf(newColumn);

                // Check if the columnIndex is within the bounds of the contact's phone numbers
                if (contact != null && columnIndex >= 0 && columnIndex < contact.getPhoneNumbers().size()) {
                    // Get the corresponding phone number from the contact's phone numbers
                    value = contact.getPhoneNumbers().get(columnIndex);
                }

                return new SimpleStringProperty(value);
            });

            // Set the cell factory for the new column to a custom PhoneNumberCell
            newColumn.setCellFactory(param -> new PhoneNumberCell());

            // Set the onEditCommit event handler for the new column
            newColumn.setOnEditCommit(e -> {
                Contacts contact = e.getRowValue();
                // columnIndex used so that each column will be unique and that when inputting values, the classes will know which column is being edited and handled accordingly
                int columnIndex = column6Big.getColumns().indexOf(newColumn);
                if (columnIndex >= 0 && columnIndex < contact.getPhoneNumbers().size()) {
                    // Update the existing phone number at the specified column index
                    contact.getPhoneNumbers().set(columnIndex, e.getNewValue());
                } else {
                    // Add a new phone number to the contact's phone numbers
                    contact.getPhoneNumbers().add(e.getNewValue());
                }
            });

            // Set the maximum number of columns to three
            if (maxAdds <= 3) {
                // Add the new column to the column6Big
                column6Big.getColumns().add(newColumn);

                // Set the preferred, minimum, and maximum width of the new column, done for consistency for each column
                newColumn.setPrefWidth(125);
                newColumn.setMinWidth(125);
                newColumn.setMaxWidth(125);

                // Adjust the width of column6Big, tableView, and primaryStage based on the number of columns.
                // Is critical for making sure the screen fits the newly added columns
                column6Big.setMinWidth(125 * maxAdds);
                column6Big.setMaxWidth(125 * maxAdds);
                tableView.setPrefWidth(620 + 130 * (maxAdds - 1));
                primaryStage.setWidth(860 + 130 * (maxAdds - 1));

                // Enable editing in the tableView
                tableView.setEditable(true);
            } else {
                // Display an error message if the maximum number of phone numbers is reached
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Maximum Phone Numbers");
                alert.setHeaderText(null);
                alert.setContentText("You have reached the maximum number of phone numbers per contact.");
                alert.showAndWait();
            }
        }

        // Create a new BorderPane and set the tableView as the center of the layout
        BorderPane root = new BorderPane();
        root.setCenter(tableView);

        // Create TextField instances for capturing input
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField address = new TextField();
        TextField city = new TextField();
        TextField postalCode = new TextField();
        TextField number = new TextField();

        // Create a Button labeled "Add"
        Button btnAdd = new Button("Add");

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Retrieve the values from the input TextFields
                String first = firstName.getText();
                String last = lastName.getText();
                String addr = address.getText();
                String cty = city.getText();
                String postCode = postalCode.getText();
                String num = number.getText();

                // Check if any of the fields are empty
                if (first.isEmpty() || last.isEmpty() || addr.isEmpty() || cty.isEmpty() || postCode.isEmpty()
                        || num.isEmpty()) {
                    // Display an error message if any field is empty
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all the fields.");
                    alert.showAndWait();
                    return;
                }

                // Validate the phone number format
                if (PhoneNumberCell.isValidPhoneNumber(num)) {
                    // If the phone number is valid, create a new Contacts object and add it to the
                    // tableView
                    tableView.getItems().add(new Contacts(first, last, addr, cty, postCode, num));

                    // Clear the input fields
                    firstName.clear();
                    lastName.clear();
                    address.clear();
                    city.clear();
                    postalCode.clear();
                    number.clear();
                } else {
                    // Display an error message if the phone number is invalid
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Phone Number");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid 10-digit phone number.");
                    alert.showAndWait();
                    return;
                }
            }
        });

        // Create the "Delete" button
        btnDel = new Button("Delete");
        btnDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the selected contacts and remove them from the table view
                ObservableList<Contacts> selectedContacts, allContacts;
                allContacts = tableView.getItems();
                selectedContacts = tableView.getSelectionModel().getSelectedItems();
                selectedContacts.forEach(allContacts::remove);
            }
        });

        // Create the "Save" button
        btnSave = new Button("Save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get all contacts from the table view and save them to a CSV file
                ObservableList<Contacts> allContacts;
                allContacts = tableView.getItems();
                String str = "";
                // Enhanced for-loop used in order to get all attributes of types Contacts in the row
                for (Contacts contact : allContacts) {
                    // ArrayList is used as the columns for phone numbers are dynamic and change
                    ArrayList<String> numbers = contact.getPhoneNumbers();
                    String allNumbers = "";
                    // for-loop gets all the numbers in the row (regardless of how many added phone numbers are added) and stores it
                    // uses number.size() to get amount of newly added columns to help as the condition
                    for (int i = 0; i < numbers.size() - 1; i++) {
                        allNumbers += numbers.get(i) + ",";
                    }
                    allNumbers += numbers.get(numbers.size() - 1);
                    // apend the file by seperating by commas and add a new line when done with attributes
                    str += contact.getFirst() + "," + contact.getLast() + "," + contact.getAddress() + ","
                            + contact.getCity() + "," + contact.getPostalCode() + "," + allNumbers;
                    str += "\n";
                }
                try {
                    // Specify the file path and write the contact information to the CSV file
                    Path filePath = Paths.get("src/info.csv");
                    Files.writeString(filePath, str, StandardCharsets.UTF_8);
                // Add an exception/catch statement if the try function does not work, safety net
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Create the "Add Phone Number" button
        btnAddSecondary = new Button("Add Phone Number");
        btnAddSecondary.setOnAction(event -> {
            // Increment the maximum number of phone numbers
            maxAdds += 1;
            variable = maxAdds == 2 ? "nd" : "rd";

            // Create a new column for the additional phone number
            TableColumn<Contacts, String> newColumn = new TableColumn<>(maxAdds + variable + " Number");
            // uses a Callback function that defines how to enter the inputs of the cell value based on the Contact and the current row (cellData)
            newColumn.setCellValueFactory(cellData -> {
                Contacts contact = cellData.getValue();
                // Value initially set to "" as when we add the new phone number column, we want it to be empty
                String value = "";
                // If the contact is not null, it retrieves the column index of the new column using 
                if (contact != null) {
                    int columnIndex = column6Big.getColumns().indexOf(newColumn);
                    // If the columnIndex is within the range of valid phone numbers for the contact, 
                    // the value is set to the corresponding phone number from the contact object
                    if (columnIndex >= 0 && columnIndex < contact.getPhoneNumbers().size()) {
                        value = contact.getPhoneNumbers().get(columnIndex);
                    }
                }
                // return the string property of the cell (in order to edit the already inputted string in the cell)
                return new SimpleStringProperty(value);
            });

            // uses a custom cell factory PhoneNumberCell which defined how the cell should be rendered and behave
            newColumn.setCellFactory(param -> new PhoneNumberCell());
            //  setOnEditCommit method is used to define the behavior when a cell value in the new column is edited and committed
            newColumn.setOnEditCommit(e -> {
                Contacts contact = e.getRowValue();
                int columnIndex = column6Big.getColumns().indexOf(newColumn);
                // contact variable is assigned the Contacts object corresponding to the row where the edit was made
                if (columnIndex >= 0 && columnIndex < contact.getPhoneNumbers().size()) {
                    // If the columnIndex is within the range of valid phone numbers for the contact, 
                    // the edited value is set using contact.getPhoneNumbers().set(columnIndex, e.getNewValue())
                    contact.getPhoneNumbers().set(columnIndex, e.getNewValue());
                } else {
                    // If the columnIndex is out of range, it means a new phone number is being added, so the edited value is 
                    //  added to the contact using contact.getPhoneNumbers().add(e.getNewValue())
                    contact.getPhoneNumbers().add(e.getNewValue());
                }
            });

            // Set the maximum number of columns to three and make condition for UI that max three phone numbers can be in total
            // Done so that newly added columns are not overflown out of screen. This condition can be changed easily for indefinite added columns
            if (maxAdds <= 3) {
                // Set the new column properties accordinly for consistency and visuals
                column6Big.getColumns().add(newColumn);
                newColumn.setPrefWidth(125);
                newColumn.setMinWidth(125);
                newColumn.setMaxWidth(125);
                column6Big.setMinWidth(125 * maxAdds);
                column6Big.setMaxWidth(125 * maxAdds);
                tableView.setPrefWidth(tableView.getWidth() + 130);
                primaryStage.setWidth(primaryStage.getWidth() + 130);
                tableView.setEditable(true);
            } else {
                // Display an error message if the condition is not met and the user has reached the limit of three phone numbers
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Maximum Phone Numbers");
                alert.setHeaderText(null);
                alert.setContentText("You have reached the maximum number of phone numbers per contact.");
                alert.showAndWait();
            }
        });

        // Create a vertical box for the input fields and buttons
        VBox inputBox = new VBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.getChildren().addAll(
                // add all the labels for the input boxes
                new Label("First Name:"), firstName,
                new Label("Last Name:"), lastName,
                new Label("Address:"), address,
                new Label("City:"), city,
                new Label("Postal Code:"), postalCode,
                new Label("Number:"), number,
                btnAdd, btnDel, btnSave, btnAddSecondary);

        // Set the minimum width for each table column for consistency and visuals
        // Set the minimum widths and not the maximum as we want consistency at least for every column (min) but if there is a super long
        // input the user can adjust the max column size to see the input
        column1.setMinWidth(100);
        column2.setMinWidth(100);
        column3.setMinWidth(100);
        column4.setMinWidth(100);
        column5.setMinWidth(100);
        column6.setPrefWidth(125);
        column6.setMinWidth(125);
        column6.setMaxWidth(125);

        // Create a horizontal box to contain the table view and input box
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(root, inputBox);
        hbox.setPrefWidth(820);

        // Create a scene with the horizontal box
        Scene scene = new Scene(hbox);

        // Set the title for the primary stage
        primaryStage.setTitle("Your Contacts:");

        // Set the scene for the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}