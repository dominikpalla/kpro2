package cz.uhk.kpro2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String licencePlate;
    @Min(value=1)
    @Max(value=5)
    private double engineVolume;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    private int najetoKm;

    public Car(Long id) {
        this.id = id;
    }

    public Car() {
    }

    public int getNajetoKm() {
        return najetoKm;
    }

    public void setNajetoKm(int najetoKm) {
        this.najetoKm = najetoKm;
    }

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

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int pridejKm(int najezd){
        najetoKm += najezd;
        return najetoKm;
    }

    public float vydelNulou(float delenec, float delitel){
        if(delitel == 0){
            throw new IllegalArgumentException("Nelze delit nulou");
        }
        return delenec / delitel;
    }
}
