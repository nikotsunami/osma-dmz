package ch.unibas.medizin.osce.domain;


import javax.validation.constraints.Size;


public class Description {

    @Size(max = 999)
    private String description;
}
