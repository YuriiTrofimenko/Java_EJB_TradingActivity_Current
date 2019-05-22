/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.tyaa.java.ejb.tradingactivity.entity.Sale;

/**
 *
 * @author student
 */
@Stateless
public class SaleFacade extends AbstractFacade<Sale> {

    @PersistenceContext(unitName = "JavaEJBTradingActivity-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaleFacade() {
        super(Sale.class);
    }

    public List<Sale> findBySecurityName(String _searchString) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sale> cq = cb.createQuery(Sale.class);
        Root<Sale> root = cq.from(Sale.class);
        Predicate[] restrictions = { cb.like(root.get("securityName"), "%"+ _searchString +"%") };
        cq.where(restrictions);
        return em.createQuery(cq).getResultList();
    }
}
