package nla.local.pojos.object;


import javax.persistence.*;


/**
 * Created by beresnev on 03.06.2015.
 */
@Entity
@Table(name="MV_OBJECT_SRC")
public class Object_src extends Object {

    @Id
    @Column(name="OBJECT_ID", unique=true, nullable=false )
    private Long obj_id;

    @Column( name = "ADR_NUM")
    private Long address_id;

    @Transient
    private String Adr;


    public String getAdr() {
        return Adr;
    }

    public void setAdr(String adr) {
        Adr = adr;
    }

    public Long getObj_id() {
        return obj_id;
    }

    public void setObj_id(Long obj_id) {
        this.obj_id = obj_id;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }


}
