package com.yash.bulk_processing.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_items",
       uniqueConstraints = @UniqueConstraint(columnNames = {"group_id_ref", "item_name"}))
public class GroupItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recordId;

    private String itemName;

    private Integer quantity;

    private Double price;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "group_id_ref")
    private Group group;
}
