package nla.local.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by beresnev on 13.01.2015.
 */

/*
adresses
 */
@Entity
@Table(name="ADDRESSES")
public class Address {

    @Id
    @Column(name = "ADR_NUM" )
    public Integer address_id;

    @Column(name = "REGION")
    public String region;

    @Column(name = "SUBREGION")
    public String subregion;

    @Column(name = "SOVET")
    public String sovet;

    @Column(name = "NP")
    public String np;

    @Column(name = "IMMOVABLETYPENAME")
    public String immovabletypename;

    @Column(name = "ELEMENTTYPENAME")
    public String elementtypename;

    @Column(name = "NUM_HOUSE")
    public int numHouse;

    @Column(name = "IND_HOUSE")
    public String indHouse;

    @Column(name = "NUM_CORP")
    public int numCorp;

    @Column(name = "NUM_ROOM")
    public int numRoom;

    @Column(name = "IND_ROOM")
    public String indRoom;

}
