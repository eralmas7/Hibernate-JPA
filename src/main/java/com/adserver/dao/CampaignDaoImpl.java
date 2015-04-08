package com.adserver.dao;

import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.adserver.entities.AdCampaign;
import com.adserver.utils.AdServerConstants;

public class CampaignDaoImpl extends AbstractDaoBase implements TupleDao {

    public final static String AD_CAMPAIGN_LIMIT = "campaignMaxHitLimit";

    @Override
    public List<Tuple> fetchAllRecords() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<AdCampaign> adCampaignRoot = criteriaQuery.from(AdCampaign.class);
        criteriaQuery.multiselect(adCampaignRoot.get(AdServerConstants.AD_CAMPAIGN_ID), adCampaignRoot.get(AD_CAMPAIGN_LIMIT));
        final List<Tuple> tupleResult = getEntityManager().createQuery(criteriaQuery).getResultList();
        return tupleResult;
    }
}
