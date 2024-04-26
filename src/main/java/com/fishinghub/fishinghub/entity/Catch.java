package com.fishinghub.fishinghub.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "catches")
public class Catch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "fish_species_id")
    private FishSpecies fishSpecies;

    @Column(nullable = false)
    private Integer quantity;

    public Catch() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FishSpecies getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(FishSpecies fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getters and Setters

}
