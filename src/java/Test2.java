
import java.util.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Test2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ServiceService ss = new ServiceService();
//        //création des Salles
//        ss.create(new Service("RH"));
//        ss.create(new Service("Finance"));
//        //La liste des salles
//        for (Service s : ss.getAll()) {
//            System.out.println("" + s.getNom());
//        }
//        EmployeService ss = new EmployeService();
//        //création des Salles
//        ss.create(new Employe("Jalaoui","Salma",new Date(),new Service(1L),null));
//        //La liste des salles
//        for (Employe s : ss.getAll()) {
//            System.out.println("" + s.getNom());
//        }
        EmployeService ss = new EmployeService();
        ServiceService s = new ServiceService();
        System.out.println(ss.employessByService(s.getById(1L)));
    }
}
