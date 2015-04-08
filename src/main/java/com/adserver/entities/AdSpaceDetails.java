package com.adserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AD_SPACE_DETAILS")
public class AdSpaceDetails {

    @Id
    @Column(name = "ad_space_id")
    private int adSpaceId;
    @Column(name = "ad_height")
    private int height;
    @Column(name = "ad_width")
    private int width;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_space_id", nullable = false)
    private AdDetails adDetails = new AdDetails();

    public AdSpaceDetails() {}

    public AdSpaceDetails(int adSpaceId, int height, int width) {
        super();
        this.adSpaceId = adSpaceId;
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getAdSpaceId() {
        return adSpaceId;
    }

    public void setAdSpaceId(int adSpaceId) {
        this.adSpaceId = adSpaceId;
    }

    public AdDetails getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(AdDetails adDetails) {
        this.adDetails = adDetails;
    }

    @Override
    public String toString() {
        return "AdSpaceDetails [adSpaceId=" + adSpaceId + ", height=" + height + ", width=" + width + "]";
    }
}
