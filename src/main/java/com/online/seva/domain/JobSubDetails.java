package com.online.seva.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Data
//@Entity
//@Table(name = "job_details")
@Embeddable
public class JobSubDetails {

    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    //private int id;
    @Lob
    private String aboutJob;
    @Lob
    private String postName;
    @Lob
    private String totalVacancies;
    @Lob
    private String salaryScale;
    private String jobLocation;
    @Lob
    private String educationalQualifiction;
    @Lob
    private String selectionProcess;
    private String ageLimit;
    private String applicationFee;
    @Lob
    private String howToApplay;
    private String youTubeLink;
}
