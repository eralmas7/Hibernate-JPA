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
import com.adserver.utils.AdServerConstants;

public class AdRequestDaoImpl extends AbstractDaoBase implements AdDao {

    public static final String AD_CAMPAIGN = "adCampaign";
    public static final String AD_CUSTOMER = "adCustomer";
    public static final String AD_LOCATION = "location";
    public static final String AD_URL = "targetUrl";
    public static final String CUSTOMER_CATEGORY = "customerType";
    public static final String AD_TITLE = "title";
    public static final String AD_SPACE = "adSpace";
    public static final String AD_SPACE_ID = "adSpaceId";
    public static final String IS_CAMPAIGN_ACTIVE = "isCampaignActive";
    public static final String CAMPAIGN_START_DATE = "campaignStartDate";
    public static final String CAMPAIGN_END_DATE = "campaignEndDate";

    @Override
    public List<AdDBResponse> fetchAds(final String adSpace) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<AdDBResponse> criteriaQuery = criteriaBuilder.createQuery(AdDBResponse.class);
        final Root<AdDetails> adDetailsRoot = criteriaQuery.from(AdDetails.class);
        final Join<AdDetails, AdCampaign> joinAdCampaign = adDetailsRoot.join(AD_CAMPAIGN);
        final Join<AdDetails, AdCustomer> joinAdCustomer = adDetailsRoot.join(AD_CUSTOMER);
        final ParameterExpression<Date> todaysDate = criteriaBuilder.parameter(Date.class);
        createWhereClause(criteriaBuilder, criteriaQuery, joinAdCampaign, joinAdCustomer, adSpace, adDetailsRoot, todaysDate);
        criteriaQuery.select(criteriaBuilder.construct(AdDBResponse.class, adDetailsRoot.get(AD_LOCATION), adDetailsRoot.get(AD_URL), joinAdCustomer.get(CUSTOMER_CATEGORY), joinAdCampaign.get(AdServerConstants.AD_CAMPAIGN_ID), adDetailsRoot.get(AD_TITLE)));
        final TypedQuery<AdDBResponse> query = getEntityManager().createQuery(criteriaQuery).setParameter(todaysDate, new Date(), TemporalType.DATE);
        return query.getResultList();
    }

    private void createWhereClause(final CriteriaBuilder criteriaBuilder, final CriteriaQuery<AdDBResponse> criteriaQuery, final Join<AdDetails, AdCampaign> joinAdCampaign, final Join<AdDetails, AdCustomer> joinAdCustomer, final String adSpace, final Root<AdDetails> adDetailsRoot,
                    final ParameterExpression<Date> todaysDate) {
        final Join<AdDetails, AdSpaceDetails> joinAdSpace = adDetailsRoot.join(AD_SPACE);
        final Predicate customerDate =
                        criteriaBuilder.or(criteriaBuilder.between(todaysDate, joinAdCustomer.<Date>get(AdServerConstants.CUSTOMER_START_DATE), joinAdCustomer.<Date>get(AdServerConstants.CUSTOMER_END_DATE)), criteriaBuilder.isNull(joinAdCustomer.<Date>get(AdServerConstants.CUSTOMER_END_DATE)));
        final Predicate campaignDate = criteriaBuilder.or(criteriaBuilder.between(todaysDate, joinAdCampaign.<Date>get(CAMPAIGN_START_DATE), joinAdCampaign.<Date>get(CAMPAIGN_END_DATE)), criteriaBuilder.isNull(joinAdCampaign.<Date>get(CAMPAIGN_END_DATE)));
        criteriaQuery.where(criteriaBuilder.and(customerDate, campaignDate, criteriaBuilder.equal(joinAdCustomer.get(AdServerConstants.IS_CUSTOMER_ACTIVE), 1), criteriaBuilder.equal(joinAdCampaign.get(IS_CAMPAIGN_ACTIVE), 1), criteriaBuilder.equal(joinAdSpace.get(AD_SPACE_ID), Integer
                        .parseInt(adSpace))));
    }
}
