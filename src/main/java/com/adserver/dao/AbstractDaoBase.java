package com.adserver.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract helper for DAO
 */
public class AbstractDaoBase {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns entity manager.
     * 
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Setter injection for entity manager.
     * 
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
