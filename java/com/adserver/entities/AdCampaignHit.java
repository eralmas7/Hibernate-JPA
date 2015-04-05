package com.adserver.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AD_CAMPAIGN_HIT")
public class AdCampaignHit {

    @Id
    @Column(name = "campaign_id")
    private int campaignId;
    @Column(name = "date_hit")
    private Date dateHit;
    @Column(name = "campaign_hit_count")
    private int campaignHitCount;

    public AdCampaignHit() {}

    public AdCampaignHit(int campaignId, Date dateHit, int campaignHitCount) {
        this.campaignId = campaignId;
        this.dateHit = dateHit;
        this.campaignHitCount = campaignHitCount;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public Date getDateHit() {
        return dateHit;
    }

    public void setDateHit(Date dateHit) {
        this.dateHit = dateHit;
    }

    public int getCampaignHitCount() {
        return campaignHitCount;
    }

    public void setCampaignHitCount(int campaignHitCount) {
        this.campaignHitCount = campaignHitCount;
    }

    @Override
    public String toString() {
        return "AdCampignHit [campaignId=" + campaignId + ", dateHit=" + dateHit + ", campaignHitCount=" + campaignHitCount + "]";
    }
}
