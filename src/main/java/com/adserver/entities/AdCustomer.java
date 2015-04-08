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
    private int customerType;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private List<AdDetails> adDetailsList = new ArrayList<AdDetails>();

    public AdCustomer() {}

    public AdCustomer(final int customerId, final String customerDns, final String customerName, final byte isCustomerActive, final Date customerStartDate, final Date customerEndDate, final int customerType) {
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

    public void setCustomerId(final int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerDns() {
        return customerDns;
    }

    public void setCustomerDns(final String customerDns) {
        this.customerDns = customerDns;
    }

    public byte getIsCustomerActive() {
        return isCustomerActive;
    }

    public void setIsCustomerActive(final byte isCustomerActive) {
        this.isCustomerActive = isCustomerActive;
    }

    public Date getCustomerStartDate() {
        return customerStartDate;
    }

    public void setCustomerStartDate(final Date customerStartDate) {
        this.customerStartDate = customerStartDate;
    }

    public Date getCustomerEndDate() {
        return customerEndDate;
    }

    public void setCustomerEndDate(final Date customerEndDate) {
        this.customerEndDate = customerEndDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(final int customerType) {
        this.customerType = customerType;
    }

    public List<AdDetails> getAdDetailsList() {
        return adDetailsList;
    }

    public void setAdDetailsList(final List<AdDetails> adDetailsList) {
        this.adDetailsList = adDetailsList;
    }

    @Override
    public String toString() {
        return "AdCustomer [customerId=" + customerId + ", customerDns=" + customerDns + ", customerName=" + customerName + ", isCustomerActive=" + isCustomerActive + ", customerStartDate=" + customerStartDate + ", customerEndDate=" + customerEndDate + ", customerType=" + customerType + "]";
    }
}
