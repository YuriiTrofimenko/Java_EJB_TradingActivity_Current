/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.tyaa.java.ejb.tradingactivity.entity.Category;
import org.tyaa.java.ejb.tradingactivity.entity.Sale;
import org.tyaa.java.ejb.tradingactivity.session.CategoryFacade;
import org.tyaa.java.ejb.tradingactivity.session.SaleFacade;

/**
 *
 * @author student
 */
@ManagedBean(name="index_data", eager = true)
@ApplicationScoped
public class IndexJSFManagedBean {
    
    private String mSearchString;
    
    public IndexJSFManagedBean() {
        //salesList = getAllSales();
    }
    
    @EJB
    SaleFacade saleFacade;
    
    @EJB
    CategoryFacade categoryFacade;
    
    public List getSales() throws InterruptedException{
        Map<String, String> params =
                FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequestParameterMap();
        if(params.containsKey("category_id")){
            Category category = categoryFacade.find(Integer.parseInt(params.get("category_id")));
            for (Sale sale : category.getSaleList()) {
                System.out.println(sale.getId());
            }
            return (List)category.getSaleList();
        }else if(params.containsKey("search_string")){
            List<Sale> sales =
                saleFacade.findBySecurityName(mSearchString);
                sales.stream().forEach((sale) -> {
                    System.out.println(sale.getSecurityName());
                });
                return sales;
        }
        return saleFacade.findAll();
    }
    
    public List getAllCategories(){
        return categoryFacade.findAll();
    }
    
    //Перезагрузка страницы каждые 5 секунд
    @Schedule(minute = "*/5", hour = "*", persistent=false)
    public void updateJsfPage() throws IOException {
        System.out.println("Jsf page updating");
        FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("#");
    }
    
    public String getSearchString() {
        return mSearchString;
    }

    public void setSearchString(String _searchString) {
        mSearchString = _searchString;
    }
}
