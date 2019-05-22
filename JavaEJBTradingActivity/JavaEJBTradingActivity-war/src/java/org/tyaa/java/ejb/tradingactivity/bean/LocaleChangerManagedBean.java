/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.bean;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author student
 */
@ManagedBean(name="locale_changer", eager = true)
@SessionScoped
public class LocaleChangerManagedBean implements Serializable {
    
    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public LocaleChangerManagedBean() {}
    public void changeLocale(String localeCode) {
        currentLocale = new Locale(localeCode);
    }
    public Locale getCurrentLocale() {
        return currentLocale;
    }

}
