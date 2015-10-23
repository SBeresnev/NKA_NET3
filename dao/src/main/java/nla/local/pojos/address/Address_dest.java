package nla.local.pojos.address;

import javax.persistence.*;

/**
 * Created by beresnev on 04.06.2015.
 */
@Entity
@Table(name="V_ADDRESSES_DEST")
public class Address_dest {

    @Id
    @Column(name="ADDRESS_ID", unique=true, nullable=false )
    @SequenceGenerator(name="adr_seq", sequenceName="SEQ_ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="adr_seq")
    private Long address_id;

    @Column(name = "ADR_NUM")
    private Long adr_num;

    @Column(name = "ADR")
    private String adr;

    @Column(name = "SOATO")
    private String soato;


    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public Long getAdr_num() {
        return adr_num;
    }

    public void setAdr_num(Long adr_num) {
        this.adr_num = adr_num;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getSoato() {
        return soato;
    }

    public void setSoato(String soato) {
        this.soato = soato;
    }

}
