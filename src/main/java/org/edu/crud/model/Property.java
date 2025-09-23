package org.edu.crud.model;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
@Data
@Getter
@Setter
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Double price;
    private Double size;
    private String description;
}
