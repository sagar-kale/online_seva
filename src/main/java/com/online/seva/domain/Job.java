package com.online.seva.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Sagar Kale
 */
@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "title")
    @NotEmpty(message = "*Please Provide Job name")
    @Lob
    private String title;
    private String startDate;
    private String lastDate;
    @Lob
    private String qualification;
    @Lob
    private String sector;
    @Lob
    private String totalPosts;
    @Embedded
    private JobSubDetails jobSubDetails;

}
