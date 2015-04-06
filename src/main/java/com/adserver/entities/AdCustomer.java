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
@Table(name = "AD_CUSTOMER")
public class AdCustomer {

    @Id
    @Column(name = "cust_id")
    private int customerId;
    @Column(name = "cust_dns")
    private String customerDns;
    @Column(name = "cust_name")
    private String customerName;
    @Column(name = "cust_active")
    private byte isCustomerActive;
    @Column(name = "cust_start_date")
    private Date customerStartDate;
    @Column(name = "cust_end_date")
    private Date customerEndDate;
    @Column(name = "cust_type")
    private String customerType;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private List<AdDetails> adDetailsList = new ArrayList<AdDetails>();

    public AdCustomer() {}

    public AdCustomer(int customerId, String customerDns, String customerName, byte isCustomerActive, Date customerStartDate, Date customerEndDate, String customerType) {
        super();
        this.customerId = customerId;
        this.customerDns = customerDns;
        this.customerName = customerName;
        this.isCustomerActive = isCustomerActive;
        this.customerStartDate = customerStartDate;
        this.customerEndDate = customerEndDate;
        this.customerType = customerType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerDns() {
        return customerDns;
    }

    public void setCustomerDns(String customerDns) {
        this.customerDns = customerDns;
    }

    public byte getIsCustomerActive() {
        return isCustomerActive;
    }

    public void setIsCustomerActive(byte isCustomerActive) {
        this.isCustomerActive = isCustomerActive;
    }

    public Date getCustomerStartDate() {
        return customerStartDate;
    }

    public void setCustomerStartDate(Date customerStartDate) {
        this.customerStartDate = customerStartDate;
    }

    public Date getCustomerEndDate() {
        return customerEndDate;
    }

    public void setCustomerEndDate(Date customerEndDate) {
        this.customerEndDate = customerEndDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public List<AdDetails> getAdDetailsList() {
        return adDetailsList;
    }

    public void setAdDetailsList(List<AdDetails> adDetailsList) {
        this.adDetailsList = adDetailsList;
    }

    @Override
    public String toString() {
        return "AdCustomer [customerId=" + customerId + ", customerDns=" + customerDns + ", customerName=" + customerName + ", isCustomerActive=" + isCustomerActive + ", customerStartDate=" + customerStartDate + ", customerEndDate=" + customerEndDate + ", customerType=" + customerType + "]";
    }
}
