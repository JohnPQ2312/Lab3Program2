/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.lab3program2;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
