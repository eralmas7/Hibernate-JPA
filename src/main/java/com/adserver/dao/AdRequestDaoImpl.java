package com.adserver.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCampaign;
import com.adserver.entities.AdCustomer;
import com.adserver.entities.AdDetails;
import com.adserver.entities.AdSpaceDetails;

public class AdRequestDaoImpl extends AbstractDaoBase implements AdDao {

    @Override
    public List<AdDBResponse> fetchAds(String adSpace) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<AdDBResponse> criteriaQuery = criteriaBuilder.createQuery(AdDBResponse.class);
        final Root<AdDetails> adDetailsRoot = criteriaQuery.from(AdDetails.class);
        Join<AdDetails, AdCampaign> joinAdCampaign = adDetailsRoot.join("adCampaign");
        Join<AdDetails, AdCustomer> joinAdCustomer = adDetailsRoot.join("adCustomer");
        Join<AdDetails, AdSpaceDetails> joinAdSpace = adDetailsRoot.join("adSpace");
        Date currentDate = new Date();
        ParameterExpression<Date> todaysDate = criteriaBuilder.parameter(Date.class);
        Predicate customerDate = criteriaBuilder.or(criteriaBuilder.between(todaysDate, joinAdCustomer.<Date>get("customerStartDate"), joinAdCustomer.<Date>get("customerEndDate")), criteriaBuilder.isNull(joinAdCustomer.<Date>get("customerEndDate")));
        Predicate campaignDate = criteriaBuilder.or(criteriaBuilder.between(todaysDate, joinAdCampaign.<Date>get("campaignStartDate"), joinAdCampaign.<Date>get("campaignEndDate")), criteriaBuilder.isNull(joinAdCampaign.<Date>get("campaignEndDate")));
        criteriaQuery.where(criteriaBuilder.and(customerDate, campaignDate, criteriaBuilder.equal(joinAdCustomer.get("isCustomerActive"), 1), criteriaBuilder.equal(joinAdCampaign.get("isCampaignActive"), 1), criteriaBuilder.equal(joinAdSpace.get("adSpaceId"), Integer.parseInt(adSpace))));
        criteriaQuery.select(criteriaBuilder.construct(AdDBResponse.class, adDetailsRoot.get("location"), adDetailsRoot.get("targetUrl"), joinAdCustomer.get("customerType"), joinAdCampaign.get("campaignId"), adDetailsRoot.get("title")));
        final TypedQuery<AdDBResponse> query = getEntityManager().createQuery(criteriaQuery).setParameter(todaysDate, currentDate, TemporalType.DATE);
        return query.getResultList();
    }
}
