package com.codegym.model.orderAndTransaction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column()
    @NotEmpty
    private int status=0;

    @NotEmpty
    private String customer_id;

    @NotEmpty
    private String customer_name;

    @NotEmpty
    private String customer_email;

    @NotEmpty
    private String customer_phone;

    @NotEmpty
    private String customer_address;

    @NotEmpty
    private String total_payment;

    @NotEmpty
    private String customer_of_payment;

    @NotEmpty
    private String customer_created;

    public Transaction() {
    }

    public Transaction(int status, String customer_id,
                       String customer_name, String customer_email,
                       String customer_phone,String customer_address,
                       String total_payment, String customer_of_payment,
                       String customer_created) {
        this.status = status;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.total_payment = total_payment;
        this.customer_of_payment = customer_of_payment;
        this.customer_created = customer_created;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }

    public String getCustomer_of_payment() {
        return customer_of_payment;
    }

    public void setCustomer_of_payment(String customer_of_payment) {
        this.customer_of_payment = customer_of_payment;
    }

    public String getCustomer_created() {
        return customer_created;
    }

    public void setCustomer_created(String customer_created) {
        this.customer_created = customer_created;
    }
}
