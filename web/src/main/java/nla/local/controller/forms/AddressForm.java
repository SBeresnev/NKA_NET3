package nla.local.controller.forms;

import nla.local.pojos.address.Address_dest;

/**
 * Created by beresnev on 26.10.2015.
 */
public class AddressForm {

    private Long address_id;

    private Long adr_num;

    private String soato;

    private String adr;


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

    public String getSoato() {
        return soato;
    }

    public void setSoato(String soato) {
        this.soato = soato;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }


}
