package com.ta.stock_protfolio.beans;

import com.ta.stock_protfolio.validation.ValidEmail;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ValidEmail
    private String email;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    private List<UserStock> stocks;

}
