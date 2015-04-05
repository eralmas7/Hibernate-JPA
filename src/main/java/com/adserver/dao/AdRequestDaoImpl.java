package com.adserver.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCampaign;
import com.adserver.entities.AdCustomer;
import com.adserver.entities.AdDetails;
import com.adserver.entities.AdMapping;

public class AdRequestDaoImpl implements AdDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AdDBResponse> fetchAds(String adSpace) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<AdDBResponse> criteriaQuery = criteriaBuilder.createQuery(AdDBResponse.class);
        final Root<AdMapping> adMappingRoot = criteriaQuery.from(AdMapping.class);
        final Root<AdCampaign> adCampaignRoot = criteriaQuery.from(AdCampaign.class);
        final Root<AdDetails> adDetailsRoot = criteriaQuery.from(AdDetails.class);
        final Root<AdCustomer> adCustomerRoot = criteriaQuery.from(AdCustomer.class);
        // final Root<AdCampaignHit> adCampaignHitRoot = criteriaQuery.from(AdCampaignHit.class);
        final List<Predicate> criteriaList = new ArrayList<Predicate>();
        criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("campaignId"), adCampaignRoot.get("campaignId")));
        criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("adId"), adDetailsRoot.get("adId")));
        criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("customerId"), adCustomerRoot.get("customerId")));
        // criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("campaignId"),
        // adCampaignHitRoot.get("campaignId")));
        final List<Predicate> predicates = new ArrayList<Predicate>(5);
        // final Expression<Integer> campaignMaxHitLimit =
        // adCampaignRoot.get("campaignMaxHitLimit");
        // final Expression<Integer> campaignHitCount = adCampaignHitRoot.get("campaignHitCount");
        // predicates.add(criteriaBuilder.lessThan(campaignHitCount, campaignMaxHitLimit));
        predicates.add(criteriaBuilder.equal(adCustomerRoot.get("isCustomerActive"), 1));
        predicates.add(criteriaBuilder.equal(adCampaignRoot.get("isCampaignActive"), 1));
        predicates.add(criteriaBuilder.equal(adMappingRoot.get("adSpaceId"), Integer.parseInt(adSpace)));
        // long today = System.currentTimeMillis();
        // long nDays = 24 * 60 * 60 * 1000;
        // long nDaysAgo = today - nDays;
        // Date nDaysAgoDate = new Date(nDaysAgo);
        // final Expression<Date> date = adCampaignHitRoot.get("dateHit");
        // predicates.add(criteriaBuilder.greaterThan(date, nDaysAgoDate));
        for (Predicate predicate : predicates) {
            criteriaQuery.where(criteriaBuilder.and(predicate));
        }
        criteriaQuery.select(criteriaBuilder.construct(AdDBResponse.class, adDetailsRoot.get("targetUrl"), adDetailsRoot.get("location"), adMappingRoot.get("categoryId")));
        final TypedQuery<AdDBResponse> query = entityManager.createQuery(criteriaQuery);
        System.out.println(query.toString());
        return query.getResultList();
    }
}
