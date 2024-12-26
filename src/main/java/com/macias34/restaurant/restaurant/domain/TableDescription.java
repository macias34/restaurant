package com.macias34.restaurant.restaurant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class TableDescription {
    @Id
    private TableId tableId;

    private String description;
}
