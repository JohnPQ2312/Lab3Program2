/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3program2;

import jakarta.persistence.*;
import java.util.List;

public class UserManager {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("user_management");

    public void addUser(Users user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<Users> getUsers() {
        EntityManager em = emf.createEntityManager();
        List<Users> users = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        em.close();
        return users;
    }
    
    public void deleteUser(Long userID){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Users user = em.find(Users.class, userID);
        
        if (user != null){
            em.remove(user);
        }
        
        em.getTransaction().commit();
        em.close();
    }
    
    public void updateUser(Users user){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.merge(user);
        
        em.getTransaction().commit();
        em.close();
    }
// Métodos para actualizar y eliminar usuarios
}
