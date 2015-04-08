package com.adserver.dao;

import java.util.List;
import javax.persistence.Tuple;

/**
 * Generic DAO to fetch any tuple from any tables.
 */
public interface TupleDao {

    /**
     * Fetches records from the backend.
     * 
     * @return
     */
    public List<Tuple> fetchAllRecords();
}
