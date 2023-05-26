package com.maersk.multiplication.user;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


/**
 * Stores information to identify the user
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Generated
    private Long id;
    private String alias;

    public User(final String alias) {
        this(null, alias);
    }
}
