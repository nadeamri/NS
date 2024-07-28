package com.example.demo1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.font.ShapeGraphicAttribute;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button addemploye_addbtn;
    @FXML
    private Button addemploye_clearbtn;
    @FXML
    private TableColumn<employeeData, Integer> addemploye_col_employeeID;
    @FXML
    private TableColumn<employeeData, String> addemploye_col_firstName;
    @FXML
    private TableColumn<employeeData, String> addemploye_col_lastname;
    @FXML
    private TableColumn<employeeData, String> addemploye_col_gender;
    @FXML
    private TableColumn<employeeData, String> addemploye_col_phoneNum;
    @FXML
    private TableColumn<employeeData, String> addemploye_col_position;
    @FXML
    private TableColumn<employeeData, java.sql.Date> addemploye_col_date;
    @FXML
    private Button addemploye_deletebtn;
    @FXML
    private AnchorPane addemploye_form;
    @FXML
    private TextField addemploye_id;
    @FXML
    private ImageView addemploye_image;
    @FXML
    private Button addemploye_importbtn;
    @FXML
    private TextField addemploye_lastname;
    @FXML
    private TextField addemploye_name;
    @FXML
    private TextField addemploye_phone;
    @FXML
    private ComboBox<String> addemploye_position;
    @FXML
    private ComboBox<String> addemploye_gender;
    @FXML
    private TableView<employeeData> addemploye_table;
    @FXML
    private Button addemploye_updatebtn;
    @FXML
    private Button addemployee_btn;
    @FXML
    private Button clear;
    @FXML
    private Button close;
    @FXML
    private Button home_btn;
    @FXML
    private BarChart<?, ?> home_chart;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Label home_inactiveemployye;
    @FXML
    private Label home_totalemployye;
    @FXML
    private Label home_totalpresents;
    @FXML
    private Button logout;
    @FXML
    private FontAwesomeIcon minimizeIcon;
    @FXML
    private TextField salary_Lastname;
    @FXML
    private TextField salary_Name;
    @FXML
    private Button salary_btn;
    @FXML
    private TableColumn<employeeData, String> salary_col_Lastname;
    @FXML
    private TableColumn<employeeData, String> salary_col_Name;
    @FXML
    private TableColumn<employeeData, String> salary_col_Position;
    @FXML
    private TableColumn<employeeData, String> salary_col_employeID;
    @FXML
    private TableColumn<employeeData, String> salary_col_salary;
    @FXML
    private TextField salary_employee;
    @FXML
    private TextField salary_employeeID;
    @FXML
    private AnchorPane salary_form;
    @FXML
    private TableView<employeeData> salary_table;
    @FXML
    private ComboBox<String> salry_Postion;
    @FXML
    private TextField search;
    @FXML
    private Button update;

    private Image image;
    private String imagePath;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<employeeData> addEmployeeList;

    private String[] positionList = {"Marketer Coordinator", "Web Developer (Back End)", "Web Developer (Front End)", "App Developer"};

    // Remplit la liste des positions dans le ComboBox
    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listP);
        addemploye_position.setItems(listData);
    }

    private String[] listGender = {"Male", "Female", "Others"};

    // Remplit la liste des genres dans le ComboBox
    public void addEmployeeGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listG);
        addemploye_gender.setItems(listData);
    }

    // Récupère la liste des employés depuis la base de données
    public ObservableList<employeeData> addEmployeeListData() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee ORDER BY employee_id";

        Connection connect = database.connectDb();

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            employeeData employeeE;

            while (result.next()) {
                employeeE = new employeeData(
                        result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("phoneNum"),
                        result.getString("position"),
                        result.getString("image"),
                        result.getDate("date")
                );
                listData.add(employeeE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    // Affiche les données des employés dans le tableau
    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        addemploye_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addemploye_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addemploye_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addemploye_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addemploye_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addemploye_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addemploye_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addemploye_table.setItems(addEmployeeList);
    }

    // Sélectionne un employé dans le tableau et affiche les détails
    public void addEmployeeSelect() {
        employeeData employeeD = addemploye_table.getSelectionModel().getSelectedItem();
        int num = addemploye_table.getSelectionModel().getSelectedIndex();

        if (employeeD == null) {
            return;
        }

        addemploye_id.setText(String.valueOf(employeeD.getEmployeeId()));
        addemploye_name.setText(employeeD.getFirstName());
        addemploye_lastname.setText(employeeD.getLastName());
        addemploye_phone.setText(employeeD.getPhoneNum());
        imagePath = employeeD.getImage();

        String uri = "file:" + employeeD.getImage();
        Image image = new Image(uri, 101, 127, false, true);
        addemploye_image.setImage(image);
    }

    // Met à jour les informations d'un employé dans la base de données
    public void addEmployeeUpdate() {
        String uri = imagePath;
        if (uri != null) {
            uri = uri.replace("\\", "\\\\");
        } else {
            uri = "default_image_path";
        }

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '" + addemploye_name.getText()
                + "', lastName = '" + addemploye_lastname.getText()
                + "', gender = '" + addemploye_gender.getSelectionModel().getSelectedItem()
                + "', position = '" + addemploye_position.getSelectionModel().getSelectedItem()
                + "', phoneNum = '" + addemploye_phone.getText()
                + "', image = '" + uri
                + "', date = '" + sqlDate
                + "' WHERE employee_id = '" + addemploye_id.getText() + "'";

        Connection connect = database.connectDb();

        try {
            Alert alert;

            if (addemploye_id.getText().isEmpty() || addemploye_name.getText().isEmpty() ||
                    addemploye_lastname.getText().isEmpty() || addemploye_gender.getSelectionModel().getSelectedItem() == null ||
                    addemploye_phone.getText().isEmpty() || addemploye_position.getSelectionModel().getSelectedItem() == null ||
                    uri.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + addemploye_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    double salary = 0;
                    String checkData = "SELECT * FROM employee_info WHERE employee_id = '"
                            + addemploye_id.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();

                    while (result.next()) {
                        salary = result.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName = '"
                            + addemploye_name.getText() + "', lastName = '"
                            + addemploye_lastname.getText() + "', position = '"
                            + addemploye_position.getSelectionModel().getSelectedItem()
                            + "' WHERE employee_id = '"
                            + addemploye_id.getText() + "'";
                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajoute un nouvel employé dans la base de données
    public void addEmployeeAdd() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee (employee_id, firstName, lastName, gender, phoneNum, position, image, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connect = database.connectDb();

        try {
            Alert alert;

            if (addemploye_id.getText().isEmpty() || addemploye_name.getText().isEmpty() ||
                    addemploye_lastname.getText().isEmpty() || addemploye_gender.getSelectionModel().getSelectedItem() == null ||
                    addemploye_phone.getText().isEmpty() || addemploye_position.getSelectionModel().getSelectedItem() == null ||
                    imagePath == null || imagePath.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String check = "SELECT employee_id FROM employee WHERE employee_id = ?";
                PreparedStatement prepareCheck = connect.prepareStatement(check);
                prepareCheck.setString(1, addemploye_id.getText());
                ResultSet resultCheck = prepareCheck.executeQuery();

                if (resultCheck.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID already exists");
                    alert.showAndWait();
                } else {
                    PreparedStatement prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addemploye_id.getText());
                    prepare.setString(2, addemploye_name.getText());
                    prepare.setString(3, addemploye_lastname.getText());
                    prepare.setString(4, addemploye_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addemploye_phone.getText());
                    prepare.setString(6, addemploye_position.getSelectionModel().getSelectedItem());

                    String uri = imagePath;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);
                    prepare.setDate(8, sqlDate);

                    prepare.executeUpdate();

                    String insertInfo = "INSERT INTO employee_info (employee_id, firstName, lastName, position, salary, date) VALUES (?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addemploye_id.getText());
                    prepare.setString(2, addemploye_name.getText());
                    prepare.setString(3, addemploye_lastname.getText());
                    prepare.setString(4, addemploye_position.getSelectionModel().getSelectedItem());
                    prepare.setString(5, "0.0");
                    prepare.setDate(6, sqlDate);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    addEmployeeReset();
                    addEmployeeShowListData();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprime un employé de la base de données
    public void addEmployeeDelete() {
        String sql = "DELETE FROM employee WHERE employee_id = '" + addemploye_id.getText() + "'";

        Connection connect = database.connectDb();

        try {
            Alert alert;

            if (addemploye_id.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the Employee ID");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + addemploye_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    String deleteInfo = "DELETE FROM employee_info WHERE employee_id = '"
                            + addemploye_id.getText() + "'";

                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Réinitialise les champs du formulaire d'ajout/modification d'employé
    public void addEmployeeReset() {
        addemploye_id.setText("");
        addemploye_name.setText("");
        addemploye_lastname.setText("");
        addemploye_gender.getSelectionModel().clearSelection();
        addemploye_position.getSelectionModel().clearSelection();
        addemploye_phone.setText("");
        addemploye_image.setImage(null);
        imagePath = null;
    }

    // Insère une image pour l'employé
    public void addEmployeeInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            imagePath = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 101, 127, false, true);
            addemploye_image.setImage(image);
        }
    }

    // Ferme l'application
    public void close() {
        System.exit(0);
    }

    // Minimise la fenêtre de l'application
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // Déconnecte l'utilisateur et retourne à l'écran de connexion
    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Change le formulaire affiché en fonction du bouton cliqué
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addemploye_form.setVisible(false);
            salary_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #2b5876);");
            addemployee_btn.setStyle("-fx-background-color:transparent;");
            salary_btn.setStyle("-fx-background-color:transparent;");

            homeTotalInactive();
            homeTotalEmployees();
            homedEmployeeTotalPresent();
            homeChart();

        } else if (event.getSource() == addemployee_btn) {
            home_form.setVisible(false);
            addemploye_form.setVisible(true);
            salary_form.setVisible(false);

            addemployee_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #2b5876);");
            home_btn.setStyle("-fx-background-color:transparent;");
            salary_btn.setStyle("-fx-background-color:transparent;");
            addEmployeeGenderList();
            addEmployeePositionList();

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addemploye_form.setVisible(false);
            salary_form.setVisible(true);

            salary_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #2b5876);");
            home_btn.setStyle("-fx-background-color:transparent;");
            addemployee_btn.setStyle("-fx-background-color:transparent;");
            salaryShowListData();
        }
    }

    // Met à jour le salaire d'un employé dans la base de données
    public void salaryUpdate() {
        String sql = "UPDATE employee_info SET salary = '" + salary_employee.getText()
                + "' WHERE employee_id = '" + salary_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (salary_employeeID.getText().isEmpty()
                    || salary_Name.getText().isEmpty()
                    || salary_Lastname.getText().isEmpty()
                    || salry_Postion.getSelectionModel().getSelectedItem().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated");
                alert.showAndWait();
                salaryShowListData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Réinitialise les champs du formulaire de salaire
    public void salaryReset() {
        salary_employeeID.setText("");
        salary_Name.setText("");
        salary_Lastname.setText("");
        salry_Postion.setValue(null);
        salary_employee.setText("");
    }

    // Récupère la liste des employés avec leur salaire depuis la base de données

    public ObservableList<employeeData> salaryListdata() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee_info";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            employeeData employeeD;

            while (result.next()) {
                employeeD = new employeeData(
                        result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("position"),
                        result.getDouble("salary")
                );
                listData.add(employeeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<employeeData> salaryList;

    // Affiche la liste des employés avec leur salaire dans le tableau

    public void salaryShowListData() {
        salaryList = salaryListdata();

        salary_col_employeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_Name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_Lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_col_Position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_table.setItems(salaryList);
    }

    // Sélectionne un employé dans le tableau des salaires et affiche les détails
    public void salarySelect() {
        employeeData employeeD = salary_table.getSelectionModel().getSelectedItem();
        int num = salary_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        salary_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        salary_Name.setText(employeeD.getFirstName());
        salary_Lastname.setText(employeeD.getLastName());
        salry_Postion.setValue(employeeD.getPosition());
        salary_employee.setText(String.valueOf(employeeD.getSalary()));
    }

    // Affiche le nombre total d'employés
    public void homeTotalEmployees() {
        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();
        int countData = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalemployye.setText(String.valueOf(countData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Affiche le nombre total d'employés présents
    public void homedEmployeeTotalPresent() {
        String sql = "SELECT COUNT(id) FROM employee_info";

        connect = database.connectDb();
        int countData = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalpresents.setText(String.valueOf(countData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Affiche le nombre total d'employés inactifs (salaire à 0)
    public void homeTotalInactive() {
        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";

        connect = database.connectDb();
        int countData = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_inactiveemployye.setText(String.valueOf(countData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Affiche un graphique des employés par date
    public void homeChart() {
        home_chart.getData().clear();
        String sql = "SELECT date, COUNT(id) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC";
        connect = database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_chart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Définit le style par défaut du bouton de navigation
    public void defaultNav() {
        home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #3a4368, #28966c);");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEmployeeShowListData();
        addEmployeeGenderList();
        addEmployeePositionList();
        salaryShowListData();
        homeTotalInactive();
        homeTotalEmployees();
        homedEmployeeTotalPresent();
        homeChart();
        defaultNav();
        addemploye_table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                addEmployeeSelect();
            }
        });

        // Définir la visibilité initiale des formulaires
        home_form.setVisible(true);
        addemploye_form.setVisible(false);
        salary_form.setVisible(false);
    }
}
