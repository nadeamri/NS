package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Button close;

    @FXML
    private TextField login;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;


    public void close() {
        System.exit(0);
    }

    // Connexion à la base de données
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Méthode pour authentifier l'administrateur

 public void loginadmin () {

     // Requête SQL pour authentifier l'utilisateur
     String sql = "SELECT * FROM admin WHERE login = ? AND password = ?";

     connect = database.connectDb(); // Connexion à la base de données

     try {
         // Préparation de la requête avec les paramètres de connexion

         prepare = connect.prepareStatement(sql);
         prepare.setString(1, login.getText());
         prepare.setString(2, password.getText());

         // Exécution de la requête


         result = prepare.executeQuery();

         // Vérification des champs vides


         if (login.getText().isEmpty() || password.getText().isEmpty()) {
             showAlert(Alert.AlertType.ERROR, "Error Message", "Please fill all blank fields");
         } else if (result.next()) {
             showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Logged In");

             // Si les informations de connexion sont correctes
             try {
                 // Charger la nouvelle page FXML nommée "dashboard"

                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/dashboard.fxml"));
                 Parent root = fxmlLoader.load();


                 // Configurer le stage avec la nouvelle scène


                 Stage stage = (Stage) login_btn.getScene().getWindow();
                 Scene scene = new Scene(root);

                 // Obtenir la scène et le stage actuels
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException e) {
                 e.printStackTrace();
                 showAlert(Alert.AlertType.ERROR, "Error Message", "Failed to load the dashboard");
             }
             // Si les informations de connexion sont incorrectes

         } else {
             showAlert(Alert.AlertType.ERROR, "Error Message", "Wrong login/password");
         }
     } catch (SQLException e) {
         e.printStackTrace();
         showAlert(Alert.AlertType.ERROR, "Error Message", "Database error");
     }

 }
    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
