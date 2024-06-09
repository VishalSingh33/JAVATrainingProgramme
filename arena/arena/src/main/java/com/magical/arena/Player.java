package com.magical.arena;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Player {

    // @Id
    // private int id;
    @Column(name = "health", nullable = false)
    private int health;
    @Column(name = "strength", nullable = false)
    private int strength;
    @Column(name = "attack", nullable = false)
    private int attack;

    public boolean isAlive() {
        return health > 0;
    }

}
