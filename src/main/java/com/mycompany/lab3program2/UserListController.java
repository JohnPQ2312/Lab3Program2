/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.lab3program2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UserManager userManager = new UserManager();
            List<Users> users = userManager.getUsers();
            ObservableList<Users> userObservableList = FXCollections.observableArrayList(users);

            userList.setItems(userObservableList);

            userList.setCellFactory(listView -> new ListCell<Users>() {
                @Override
                public void updateItem(Users user, boolean empty) {
                    super.updateItem(user, empty);
                    if (empty || user == null) {
                        setText(null);
                    } else {
                        setText(user.getUsername() + " (" + user.getRole() + ")");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar error real en consola
        }
    }

}
