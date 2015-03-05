package nla.local.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by beresnev on 13.01.2015.
 * adresses
 */
@Entity
@Table(name="ADDRESSES")
public class Addresses {

    @Id
    @Column(name = "ADDRESS_ID" )
    public Integer addressid;

    @Column(name = "ADR_NUM" )
    public Integer adrnum;

    @Column(name = "ADR")
    public String adr;

    @Column(name = "REGION")
    public String region;

    @Column(name = "SUBREGION")
    public String subregion;

    @Column(name = "NP_NAME")
    public String npname;

    @Column(name = "NP_TYPE_NAME")
    public String nptypename;

    @Column(name = "SELSOVET")
    public String selsovet;

    @Column(name = "STREET_NAME")
    public String streetName;

    @Column(name = "STREET_TYPE_NAME")
    public String streettypename;

    @Column(name = "HOUSE_NUM")
    public int houseNum;

    @Column(name = "HOUSE_IND")
    public String houseId;

    @Column(name = "CORP_NUM")
    public int corpNum;

    @Column(name = "ROOM_NUM")
    public int roomNum;

    @Column(name = "ROOM_IND")
    public String roomId;

}
