package com.example.sp.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  private int id;
  @Column
  private String firstName;
  @Column
  private String lastName;



}