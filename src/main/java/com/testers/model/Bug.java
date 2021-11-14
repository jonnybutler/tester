package com.testers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bug {

    @Id
    @Column
    private Long bugId;
    @Column
    private Long deviceId;
    @Column
    private Long testerId;

}
