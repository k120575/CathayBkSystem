package com.example.cathaybksystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String currencyName;

    @Column
    String currencyChName;

    @Column
    Date createdTime;

    @Column
    Date updatedTime;
}
