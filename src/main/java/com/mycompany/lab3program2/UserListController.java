/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.lab3program2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField userID, user_Username, userPassword, userRole;
    
    @FXML
    private void btnUpdate(){
           
    }
    
    @FXML
    private void btnDelete(){
        UserManager userManager = new UserManager();
        List<Users> users = userManager.getUsers();        
        
        Users selectedUser = userList.getSelectionModel().getSelectedItem();
        userManager.deleteUser(selectedUser.getId());
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
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>(){
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                Users user = userList.getSelectionModel().getSelectedItem();
                
                if (newValue != null) {
                    userID.setText(String.valueOf(user.getId()));
                    user_Username.setText(user.getUsername());
                    userPassword.setText(user.getPassword());
                    userRole.setText(user.getRole());
                }
                
                deleteSelected.setDisable(false);
            }          
        });
    }
    
    @FXML
    private void userRegisterScreen() throws IOException{
        App.setRoot("UserRegistration");
    }

}
