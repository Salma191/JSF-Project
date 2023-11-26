/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.faces.bean.ManagedBean;
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
@ManagedBean(name = "serviceBean")
public class ServiceBean {

    private Service service;

    private List<Service> services;
    private ServiceService serviceService;
    private EmployeService employeService;
    private static ChartModel barModel;



    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();

    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

   

    public String onCreateAction() {
        serviceService.create(service);
        service = new Service();
        return null;
    }

    public String onDeleteAction() {

        serviceService.delete(serviceService.getById(service.getId()));
        return null;
    }


    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        Service service = serviceService.getById(this.service.getId());
        serviceService.update(service);
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries services = new ChartSeries();
        services.setLabel("services");
        model.setAnimate(true);
        for (Service m : serviceService.getAll()) {
            services.set(m.getNom(), m.getEmployes().size());
        }
        model.addSeries(services);

        return model;
    }



}