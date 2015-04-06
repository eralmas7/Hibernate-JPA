package com.adserver.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCampaign;
import com.adserver.entities.AdDetails;

public class AdRequestDaoImpl extends AbstractDaoBase implements AdDao {

    @Override
    public List<AdDBResponse> fetchAds(String adSpace) {
        // final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        // final CriteriaQuery<AdDBResponse> criteriaQuery =
        // criteriaBuilder.createQuery(AdDBResponse.class);
        // Metamodel m = getEntityManager().getMetamodel();
        // EntityType<AdMapping> mapping_ = m.entity(AdMapping.class);
        // EntityType<AdCampaign> mappingc_ = m.entity(AdCampaign.class);
        // final Root<AdMapping> adMappingRoot = criteriaQuery.from(AdMapping.class);
        // final Root<AdCampaign> adCampaignRoot = criteriaQuery.from(AdCampaign.class);
        // final Root<AdDetails> adDetailsRoot = criteriaQuery.from(AdDetails.class);
        // final Root<AdCustomer> adCustomerRoot = criteriaQuery.from(AdCustomer.class);
        // Join<AdMapping, AdCampaign> productJoin = adMappingRoot.join("AdCampaign");
        // // final List<Predicate> criteriaList = new ArrayList<Predicate>();
        // // criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("campaignId"),
        // // adCampaignRoot.get("campaignId")));
        // // criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("adId"),
        // // adDetailsRoot.get("adId")));
        // // criteriaList.add(criteriaBuilder.equal(adMappingRoot.get("customerId"),
        // // adCustomerRoot.get("customerId")));
        // criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(adCustomerRoot.get("isCustomerActive"),
        // 1), criteriaBuilder.equal(adCampaignRoot.get("isCampaignActive"), 1),
        // criteriaBuilder.equal(adMappingRoot.get("adSpaceId"), Integer.parseInt(adSpace))));
        // criteriaQuery.select(criteriaBuilder.construct(AdDBResponse.class,
        // adDetailsRoot.get("location"), adDetailsRoot.get("targetUrl"),
        // adMappingRoot.get("categoryId"), adMappingRoot.get("campaignId"),
        // adDetailsRoot.get("title")));
        // final TypedQuery<AdDBResponse> query = getEntityManager().createQuery(criteriaQuery);
        // System.out.println(query.toString());
        // return query.getResultList();
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<AdDBResponse> criteriaQuery = criteriaBuilder.createQuery(AdDBResponse.class);
        CriteriaQuery<AdDetails> query = criteriaBuilder.createQuery(AdDetails.class);
        Root<AdDetails> adDetailsRoot = query.from(AdDetails.class);
        Join<AdDetails, AdCampaign> warehouseProductInfoJoin = adDetailsRoot.join("AdDetails_.campaignId");
        criteriaQuery.select(criteriaBuilder.construct(AdDBResponse.class, adDetailsRoot.get("location"), adDetailsRoot.get("targetUrl"), adDetailsRoot.get("title")));
        final TypedQuery<AdDBResponse> queryData = getEntityManager().createQuery(criteriaQuery);
        return queryData.getResultList();
    }
}
