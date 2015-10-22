package nla.local.pojos.address;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by beresnev on 04.06.2015.
 */
@Entity
@Table(name="V_ADDRESS")
public class Address_src{

    @Id
    @Column(name = "ID_ADR")
    private Long id_adr;

    @Column(name = "ADR_NUM")
    private Long adr_num;

    @Column(name = "PROP_TYPE")
    private Integer propType;

    @Column(name = "ELEMENT_ID")
    private Integer elementId;               //like streetid

    @Column(name = "ELEMENT_NAME")
    private String elementName;             //"STREET_NAME"

    @Column(name = "ELEMENTTYPE_DEPEND")
    private Integer elementtypeDepend;

    @Column(name = "HOUSE_NUM")
    private Integer houseNum;

    @Column(name = "HOUSE_IND")
    private String houseId;

    @Column(name = "CORP_NUM")
    private Integer corpNum;

    @Column(name = "ROOM_NUM")
    private Integer roomNum;

    @Column(name = "ROOM_IND")
    private String roomId;

    @Column(name = "LCOORD", precision = 38)
    private BigDecimal lCoord;

    @Column(name = "BCOORD",  precision = 38)
    private BigDecimal bCoord;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ATE_ID")
    private Integer ateId;

    public Integer getAteId() {
        return ateId;
    }

    public void setAteId(Integer ateId) {
        this.ateId = ateId;
    }

    /*
    @ManyToOne
    @JoinColumn(name = "ATE_ID")
    private Ate ate;

    public Ate getAte() {
        return ate;
    }

    public void setAte(Ate ate) {
        this.ate = ate;
    }*/


    public Long getId_adr() {
        return id_adr;
    }

    public void setId_adr(Long id_adr) {
        this.id_adr = id_adr;
    }

    public Long getAdr_num() {
        return adr_num;
    }

    public void setAdr_num(Long adr_num) {
        this.adr_num = adr_num;
    }

    public BigDecimal getlCoord() {
        return lCoord;
    }

    public void setlCoord(BigDecimal lCoord) {
        this.lCoord = lCoord;
    }

    public BigDecimal getbCoord() {
        return bCoord;
    }

    public void setbCoord(BigDecimal bCoord) {
        this.bCoord = bCoord;
    }

    public Integer getPropType() {
        return propType;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Integer getElementtypeDepend() {
        return elementtypeDepend;
    }

    public void setElementtypeDepend(Integer elementtypeDepend) {
        this.elementtypeDepend = elementtypeDepend;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Integer getCorpNum() {
        return corpNum;
    }

    public void setCorpNum(Integer corpNum) {
        this.corpNum = corpNum;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
