/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.lab3program2;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jp570
 */
public class UserListController implements Initializable {

    @FXML
    private Button deleteSelected, updateUser, saveChanges, backToRegister;

    @FXML
    private ListView<Users> userList;

    @FXML
    private TextField user_Username, userPassword, userRole;

    @FXML
    private DatePicker userDatePicker;

    @FXML
    private void btnSetEditable(){
        user_Username.setDisable(false);
        userPassword.setDisable(false);
        userRole.setDisable(false);
        userDatePicker.setDisable(false);
        
        saveChanges.setDisable(false);
    }
    
    @FXML
    private void btnSaveChanges() {
        UserManager userManager = new UserManager();
        Users selectedUser = userList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            LocalDate changedDate = userDatePicker.getValue();
            
            
            selectedUser.setUsername(user_Username.getText());
            selectedUser.setPassword(userPassword.getText());
            selectedUser.setRole(userRole.getText());
            selectedUser.setExpiredDate(Date.from(changedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            
            user_Username.setDisable(true);
            userPassword.setDisable(true);
            userRole.setDisable(true);
            userDatePicker.setDisable(true);
            
            userManager.updateUser(selectedUser);

            userList.setItems(FXCollections.observableArrayList(userManager.getUsers()));
        }
    }

    @FXML
    private void btnDelete() {
        UserManager userManager = new UserManager();
        List<Users> users = userManager.getUsers();

        Users selectedUser = userList.getSelectionModel().getSelectedItem();
        userManager.deleteUser(selectedUser.getId());

        user_Username.clear();
        userPassword.clear();
        userRole.clear();
        userDatePicker.setValue(null);
        userList.setItems(FXCollections.observableArrayList(userManager.getUsers()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserManager userManager = new UserManager();
        List<Users> users = userManager.getUsers();
        ObservableList<Users> userObservableList = FXCollections.observableArrayList(users);

        userList.setItems(userObservableList);

        //Mostrar en lista
        userList.setCellFactory(listView -> new ListCell<Users>() {
            @Override
            public void updateItem(Users user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getId() + ", " + user.getUsername());
                }
            }
        });

        //Seleccion
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                Users user = userList.getSelectionModel().getSelectedItem();

                if (newValue != null) {
                    user_Username.setText(user.getUsername());
                    userPassword.setText(user.getPassword());
                    userRole.setText(user.getRole());
                    userDatePicker.setValue(user.getExpiredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }

                deleteSelected.setDisable(false);
                updateUser.setDisable(false);
            }
        });
    }

    @FXML
    private void userRegisterScreen() throws IOException {
        App.setRoot("UserRegistration");
    }

}
