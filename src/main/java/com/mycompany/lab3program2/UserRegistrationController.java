/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.lab3program2;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author jp570
 */
public class UserRegistrationController{
    
    @FXML
    private DatePicker userDatePicker;
    
    @FXML
    private TextField user_Username, userPassword, userRole;
    
    @FXML
    private Button register, goToUserList;  
    
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    private void clearFields() {
        user_Username.clear();
        userPassword.clear();
        userRole.clear();
    }
    
    @FXML
    private void btnRegister(){
        UserManager userManager = new UserManager();
        
        String username = user_Username.getText();
        String password = userPassword.getText();
        String role = userRole.getText();
        LocalDate expiration = userDatePicker.getValue();
        
        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || expiration == null) {
            showAlert("Por favor llene todos los campos.");
            return;
        }
        
        Users newUser = new Users(username, password, role, Date.from(expiration.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        userManager.addUser(newUser);
        showAlert("Usuario registrado");
        clearFields();
    }
    
    public void initialize() {
        userDatePicker.setValue(LocalDate.now());
        userDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
    }
    
    @FXML
    private void userListScreen() throws IOException{
        App.setRoot("UserList");
    }
}
