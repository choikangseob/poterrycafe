package ks.poterrycafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String refresh;
    private String expiration;
}
