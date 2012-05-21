package ch.unibas.medizin.osce.domain;



import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;


public class Bankaccount {

    @Size(max = 40)
    private String bankName;

    @Size(max = 40)
    private String IBAN;

    @Size(max = 40)
    private String BIC;

    @Size(max = 40)
    private String ownerName;

    private Integer postalCode;

    @Size(max = 30)
    private String city;

    @ManyToOne
    private Nationality country;
}
