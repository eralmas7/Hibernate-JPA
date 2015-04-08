package com.adserver.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AD_CAMPAIGN")
public class AdCampaign {

    @Id
    @Column(name = "campaign_id")
    private int campaignId;
    @Column(name = "camp_active")
    private byte isCampaignActive;
    @Column(name = "camp_start_date")
    private Date campaignStartDate;
    @Column(name = "camp_end_date")
    private Date campaignEndDate;
    @Column(name = "camp_max_hit_limit")
    private int campaignMaxHitLimit;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private List<AdDetails> adDetails = new ArrayList<AdDetails>();

    public AdCampaign() {}

    public AdCampaign(int campaignId, byte isCampaignActive, Date campaignStartDate, Date campaignEndDate, int campaignMaxHitLimit) {
        this.campaignId = campaignId;
        this.isCampaignActive = isCampaignActive;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
        this.campaignMaxHitLimit = campaignMaxHitLimit;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public byte getIsCampaignActive() {
        return isCampaignActive;
    }

    public void setIsCampaignActive(byte isCampaignActive) {
        this.isCampaignActive = isCampaignActive;
    }

    public Date getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(Date campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public Date getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(Date campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public int getCampaignMaxHitLimit() {
        return campaignMaxHitLimit;
    }

    public void setCampaignMaxHitLimit(int campaignMaxHitLimit) {
        this.campaignMaxHitLimit = campaignMaxHitLimit;
    }

    public List<AdDetails> getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(List<AdDetails> adDetails) {
        this.adDetails = adDetails;
    }

    @Override
    public String toString() {
        return "AdCampaign [campaignId=" + campaignId + ", isCampaignActive=" + isCampaignActive + ", campaignStartDate=" + campaignStartDate + ", campaignEndDate=" + campaignEndDate + ", campaignMaxHitLimit=" + campaignMaxHitLimit + "]";
    }
}
