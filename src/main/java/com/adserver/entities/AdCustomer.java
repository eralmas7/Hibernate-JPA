package com.adserver.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String custName;
    @Column(name = "cust_active")
    private byte isCustomerActive;
    @Column(name = "cust_start_date")
    private Date customerStartDate;
    @Column(name = "cust_end_date")
    private Date customerEndDate;
    @Column(name = "cust_type")
    private String custType;

    public AdCustomer() {}

    public AdCustomer(int customerId, String customerDns, String custName, byte isCustomerActive, Date customerStartDate, Date customerEndDate, String custType) {
        super();
        this.customerId = customerId;
        this.customerDns = customerDns;
        this.custName = custName;
        this.isCustomerActive = isCustomerActive;
        this.customerStartDate = customerStartDate;
        this.customerEndDate = customerEndDate;
        this.custType = custType;
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    @Override
    public String toString() {
        return "AdCustomer [customerId=" + customerId + ", customerDns=" + customerDns + ", isCustomerActive=" + isCustomerActive + ", customerStartDate=" + customerStartDate + ", customerEndDate=" + customerEndDate + "]";
    }
}
