package com.example.offline_transfer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

    @Id
    private Long id;

    private double savingsBalance;
    private double ledgerBalance;
    private String pin;
}
