package com.hotel.activity.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdOn;
    @LastModifiedDate
    private ZonedDateTime updatedOn;
    private ZonedDateTime deletedOn;

}
