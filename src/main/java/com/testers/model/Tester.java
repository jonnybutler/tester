package com.testers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tester {

    @Id
    @Column
    @JsonIgnore
    private Long testerId;

    @Column
    @JsonIgnore
    private String firstName;

    @Column
    @JsonIgnore
    private String lastName;

    @Column
    private String country;

    @Column
    private LocalDateTime lastLogin;

    @Transient
    private Long bugCount;

    public String getName(){
        return firstName + " " + lastName;
    }

}
