package com.adserver.dao;

import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.adserver.entities.AdCampaign;

public class CampaignDaoImpl extends AbstractDaoBase implements TupleDao {

    @Override
    public List<Tuple> fetchAllRecords() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<AdCampaign> adCampaignRoot = criteriaQuery.from(AdCampaign.class);
        criteriaQuery.multiselect(adCampaignRoot.get("campaignId"), adCampaignRoot.get("campaignMaxHitLimit"));
        final List<Tuple> tupleResult = getEntityManager().createQuery(criteriaQuery).getResultList();
        return tupleResult;
    }
}
