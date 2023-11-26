/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ma.projet.beans.Login;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="loginBean")
@SessionScoped
    
public class LoginBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (isValidCredentials(username, password)) {
            return "/index.xhtml"; 
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credentials"));
            return null;
        }
    }
   

    private boolean isValidCredentials(String username, String password) {
        try {
            List<Login> logins;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            logins = session.createQuery("FROM Login WHERE username = :username AND password = :password").setParameter("username", username).setParameter("password", password).list();
            session.getTransaction().commit();
            if (logins.size() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
