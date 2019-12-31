/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.lib.model;

import java.io.Serializable;

/**
 *
 * @author student
 */
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String securityName;
    private int quantity;
    private int price;
    private int brokerId;
    private int categoryId;

    public Sale() {
    }

    public Sale(Integer id) {
        this.id = id;
    }

    public Sale(Integer id, String securityName, int quantity, int price) {
        this.id = id;
        this.securityName = securityName;
        this.quantity = quantity;
        this.price = price;
    }

    public Sale(Integer id, String securityName, Integer quantity, Integer price, Integer brokerId, Integer categoryId) {
        this.id = id;
        this.securityName = securityName;
        this.quantity = quantity;
        this.price = price;
        this.brokerId = brokerId;
        this.categoryId = categoryId;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /*@Override
    public String toString() {
        return "org.tyaa.java.ejb.tradingactivity.Sale[ id=" + id + " ]";
    }*/

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", securityName=" + securityName + ", quantity=" + quantity + ", price=" + price + ", brokerId=" + brokerId + ", categoryId=" + categoryId + '}';
    }
}
