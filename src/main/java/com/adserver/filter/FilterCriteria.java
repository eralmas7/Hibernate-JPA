package com.adserver.filter;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

public interface FilterCriteria {

    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses);
}
