/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.Serializable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author lenovo
 */
@ManagedBean(name = "employeBean")
@ViewScoped
public class EmployeBean {

    private Employe employe;
    private Service service;
    
    private EmployeService employeService;
    private ServiceService serviceService;
    private List<Employe> employes;
    private TreeNode root;

    public EmployeBean() {
        service = new Service();
        employe = new Employe();
        employeService = new EmployeService();
        serviceService = new ServiceService();
        root = new DefaultTreeNode("root", null);
        loadTree();

    }
      
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
        

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    
    public List<Employe> salleLoad() {
        for (Employe emp : employeService.getAll()) {
            if (emp.getService().equals(service)) {
                employes.add(emp);
            }
        }
        return employes;

    }
    
    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getEmployes();
    }
    
    public String onCreateAction() {
        employeService.create(employe);
        employe = new Employe();
        return null;
    }
    
     public void onEdit(RowEditEvent event) {
        employe = (Employe) event.getObject();
        Service service = serviceService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }
     
    public String onDeleteAction() {
        employeService.delete(employeService.getById(employe.getId()));
        return null;
    }

    public void onCancel(RowEditEvent event) {
    }
    
     public void loadTree() {
    root.getChildren().clear(); // Clear old nodes
    List<Service> services = serviceService.getAll();

    for (Service service : services) {
        TreeNode serviceNode = new DefaultTreeNode(service, root);

        // Employees nodes
        for (Employe employe : employeService.employessByService(service)) {
            TreeNode employeNode = new DefaultTreeNode(employe, serviceNode);

            // Chief node
            Employe chief = employe.getEmploye();
            if (chief != null) {
                TreeNode chiefNode = new DefaultTreeNode(chief, employeNode);
            }
        }
    }
}


    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    
    
}