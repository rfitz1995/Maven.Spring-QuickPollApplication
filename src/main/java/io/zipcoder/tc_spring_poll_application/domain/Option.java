package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPTION_ID")
    private Long id;

    String value;

    public Option() {
    }

    public Option(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
