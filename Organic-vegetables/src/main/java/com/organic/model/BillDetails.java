package com.organic.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer billId;
    private Integer orderId;
    private String transactionMode;
    private String transactionDate ;
    private String transactionStatus;

    @Embedded
    private Address billingAddress;




}
