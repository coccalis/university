package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controller {

     Connection conn = null;
     Statement st = null;
     ResultSet rs = null;
     PreparedStatement pst = null;

    //main buttons
    @FXML
    private Button connectBtn, disconnectBtn,deleteBtn, searchBtn,showStListBtn, addBtn;
    //buttons for other actions
    @FXML
    private Button submitBtn, insertBtn,deleteStBtn,deleteAllBtn;
    @FXML
    private TextArea showListTextArea;
    @FXML
    private TextField searchTf, addModuleTf,deleteTf;
    @FXML
    private Spinner moduleSp;
    @FXML
    private ChoiceBox searchOptionBox;
    @FXML
    private Label addTitleLabel, searchTitleLabel,deleteTitleLabel;


    //method to connect to database
    public void connectDb(){
        Alert a = new Alert(Alert.AlertType.NONE); //sent a pop up alert message that the connection either is successful or failed
        try {
            Class.forName("org.mariadb.jdbc.Driver");//MariaDB Driver
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/askisi1_db", "kokkalis", "1234"); //MariaDB
//            System.out.println("Connected\n");

            //here prepare to sent pop up the alert window
            EventHandler<ActionEvent> event = new
                    EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Connected");
                            a.show();
                        }
                    };

            connectBtn.setOnAction(event); //sent the pop-up when the connect button is clicked
            turnOnMainButtons();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //if the connection failed sent an error pop up window
            EventHandler<ActionEvent> event = new
                    EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.setContentText("error with connection");
                            a.show();
                        }
                    };
            connectBtn.setOnAction(event);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //if the connection failed sent an error pop up window
            EventHandler<ActionEvent> event = new
                    EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.setContentText("error Dialog");
                            a.show();
                        }
                    };
            connectBtn.setOnAction(event);
        }
    }

    //method to disconnect from database
    public void disconnectDb(){
        try {
            //Close the connection and release the resources
            rs.close();
            assert st != null;
            st.close();
            conn.close();

            // sent a pop up window message when disconnected
            Alert a = new Alert(Alert.AlertType.NONE);
            EventHandler<ActionEvent> event = new
                    EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Disconnected");
                            a.showAndWait();
                            javafx.application.Platform.exit();
                        }
                    };
            disconnectBtn.setOnAction(event);
            turnOffMainButtons();



        } catch (SQLException ex) {
            System.out.println("Close Resources exception " + ex);
        }

    }

    //method to show contents of database
    public void showDb() {
        String sqlCommand = "select * from students"; //enter the sql command
        executeSqlQuery(sqlCommand); //sent the sql command
        printStudents(); //print to the text area the resaults

        turnOffTextEditsOfDelete();
        turnOffTextEditsOfSearch();
        turnOffTextEditsOfAdd();
    }

    //method to search through the database
    @FXML
    private void searchDb(){
        turnOnTextEditsOfSearch();
        //when user presses the submit button is activates a listener
        submitBtn.setOnAction((event) -> {

            //check if text field is empty. If not proceed else sent a pop up error window
            if(!searchTf.getText().isEmpty()){

                String resultSearchTf = searchTf.getText(); //get text from text field
                String optionSearch = (String) searchOptionBox.getValue(); //get option from combobox

                //check if it is the first option
                if(optionSearch.equals("Search by Lastname") ){
                    String sqlCommand = "select * from students where lastName = " +"\"" +resultSearchTf +"\""; //sql command
                    executeSqlQuery(sqlCommand); //call method to execute command
                    printStudents(); //print data
                }else if(optionSearch.equals("Search by Semester")){
                    int semester = Integer.parseInt(resultSearchTf); //if the option is the semester the turn the string option into an integer
//                    System.out.println("The semester is: " +((Object)semester).getClass().getSimpleName());
                    String sqlCommand = "select * from students where semester like'%" + semester + "%'" ; //sql command
                    executeSqlQuery(sqlCommand);
                    printStudents();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                EventHandler<ActionEvent> event2 = new
                        EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e)
                            {
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please fill both text field and combobox");
                                a.show();
                            }
                        };
                submitBtn.setOnAction(event2);
            }
        });
    }

    //method to update the modules to the database
    @FXML
    private void updateDb(){
       turnOnTextEditsOfAdd();

        insertBtn.setOnAction((event) -> {
            int modules = (int) moduleSp.getValue(); //get value from spinner

            //check if text field empty and module is bigger than zero
            if(!addModuleTf.getText().isEmpty() && (modules > 0)){

                String lastName = addModuleTf.getText(); //get text from text field

                String sqlCommand = "update students set Modules = '" +modules+ "' where lastName= " +"\"" + lastName +"\"" ; //sql command
                //System.out.println("SQL ENTOLH: " + sqlCommand);

                try {
                    // Create and execute the SQL statement
                    st = conn.createStatement();
                    st.executeUpdate(sqlCommand);

                    //show list after the update
                    sqlCommand = "select * from students";
                    executeSqlQuery(sqlCommand);
                    printStudents();

                    //clear text edits
                    addModuleTf.clear();
                    moduleSp.getValueFactory().setValue(0);

                } catch (SQLException ex) {
                    System.out.println("SQL Statement exception " + ex);
                }
            }else {
                Alert a = new Alert(Alert.AlertType.NONE);
                EventHandler<ActionEvent> event2 = new
                        EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e)
                            {
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please fill both text field and spinner");
                                a.show();
                            }
                        };
                insertBtn.setOnAction(event2);
            }


        });
    }

    //method to delete student from database
    @FXML
    private void deleteData(){
        turnOnTextEditsOfDelete();

        //show list after the update
        AtomicReference<String> sqlCommand = new AtomicReference<>("select * from students");
        executeSqlQuery(sqlCommand.get());
        printStudents();

        deleteStBtn.setOnAction((actionEvent -> {

            if (!deleteTf.getText().isEmpty()){

                String lastName = deleteTf.getText(); //get text from textfield

                //sent a pop up window to confirm the choice to delete student
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + lastName + " ?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                //if user proceeds and presses yes then
                if (alert.getResult() == ButtonType.YES) {
                    try {
                        pst = conn.prepareStatement("delete from students where lastName=?"); //prepare sql
                        System.out.println("SQL before bind: " + pst.toString());
                        pst.setString(1, lastName);
                        pst.executeUpdate(); //run command

                        //show list after the update
                        sqlCommand.set("select * from students");
                        executeSqlQuery(sqlCommand.get());
                        printStudents();

                        deleteTf.clear();
                    } catch (SQLException ex) {
                        System.out.println("SQL Statement exception " + ex);
                    }
                }
            }else {
                Alert a = new Alert(Alert.AlertType.NONE);
                EventHandler<ActionEvent> event2 = new
                        EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e)
                            {
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please fill text field ");
                                a.show();
                            }
                        };
                deleteStBtn.setOnAction(event2);
            }


        }));


    }

    //method to delete all students from database
    @FXML
    private void deleteAllStudents(){

        deleteAllBtn.setOnAction(actionEvent -> {
            //same as delete method
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete all students ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES){
                try {

                    pst = conn.prepareStatement("delete from students"); //prepare sql
                    pst.executeUpdate(); //run command

                } catch (SQLException ex) {
                    System.out.println("SQL Statement exception " + ex);
                }
            }


        });

    }

    //print the results from mysql database to text area
    private  void printStudents() {
        try {
            showListTextArea.clear(); //before each print clear the text area to avoid double prints
            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                showListTextArea.appendText(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" +
                        rs.getString(4) + "\t\t" + rs.getString(5) + "\t\t" + rs.getString(6) + "\n");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Statement exception " + ex);
        }
    }

    public  void executeSqlQuery(String sqlCommand) {
        try {
            // Create an SQL statement
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(sqlCommand); // execute the command

        } catch (SQLException ex) {
            System.out.println("SQL Statement exception " + ex);
        }
    }

    // methods to turn on and off text edits and buttons for gui purposes
    //----start----
    private void turnOffTextEditsOfSearch(){

        searchTitleLabel.setVisible(false);
        searchTf.setVisible(false);
        searchOptionBox.setVisible(false);
        submitBtn.setVisible(false);
    }

    private void turnOnTextEditsOfSearch(){
        searchTitleLabel.setVisible(true);
        searchTf.setVisible(true);
        searchOptionBox.setVisible(true);
        searchOptionBox.getSelectionModel().selectFirst();
        submitBtn.setVisible(true);


        turnOffTextEditsOfAdd();
        turnOffTextEditsOfDelete();
        showListTextArea.clear();
    }

    private void turnOffTextEditsOfAdd(){
        addTitleLabel.setVisible(false);
        addModuleTf.setVisible(false);
        moduleSp.setVisible(false);
        insertBtn.setVisible(false);
    }

    private void turnOnTextEditsOfAdd(){
        addTitleLabel.setVisible(true);
        addModuleTf.setVisible(true);
        moduleSp.setVisible(true);
        insertBtn.setVisible(true);


        turnOffTextEditsOfSearch();
        turnOffTextEditsOfDelete();
        showListTextArea.clear();
    }

    private void turnOffTextEditsOfDelete(){
        deleteTitleLabel.setVisible(false);
        deleteStBtn.setVisible(false);
        deleteTf.setVisible(false);
        deleteAllBtn.setVisible(false);
    }

    private void turnOnTextEditsOfDelete(){
        deleteTitleLabel.setVisible(true);
        deleteStBtn.setVisible(true);
        deleteTf.setVisible(true);
        deleteAllBtn.setVisible(true);


        turnOffTextEditsOfAdd();
        turnOffTextEditsOfSearch();
        showListTextArea.clear();

    }

    private void turnOnMainButtons(){

        disconnectBtn.setDisable(false);
        showStListBtn.setDisable(false);
        searchBtn.setDisable(false);
        addBtn.setDisable(false);
        deleteBtn.setDisable(false);

        //connectBtn.setDisable(true);
    }

    private void turnOffMainButtons(){
        connectBtn.setDisable(false);

        //disconnectBtn.setDisable(true);
        showStListBtn.setDisable(true);
        searchBtn.setDisable(true);
        addBtn.setDisable(true);
        deleteBtn.setDisable(true);

    }
    //----end---


}
