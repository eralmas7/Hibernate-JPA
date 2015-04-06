package com.adserver.dao;

import java.util.List;
import javax.persistence.Tuple;

public interface TupleDao {

    public List<Tuple> fetchAllRecords();
}
