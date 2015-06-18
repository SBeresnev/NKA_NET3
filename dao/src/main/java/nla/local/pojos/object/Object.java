package nla.local.pojos.object;


import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.io.Serializable;



/**
 * Created by beresnev on 03.06.2015.
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public  class Object implements Serializable{


    @Column( name = "CADASTRE_NUMBER")
    private String cadastre_number;

    @Column( name = "ORG_ID")
    private Integer org_id;

    @Column ( name = "INVENTORY_NUMBER")
    private Integer inventory_number;

    @Column ( name = "SQUARE")
    private Integer square;

    @Column ( name = "ROOMSCOUNT")
    private Integer roomscount;

    @Column( name = "READINESS")
    private Integer readiness;

    @Column( name = "OBJECT_NAME")
    private String object_name;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "OBJECT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.OBJECT_TYP,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem objectType;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "USE_PURPOSE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.USE_PURPOSE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem use_purpose;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "LAND_CATEGORY", referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.LAND_CATEGORY,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem land_category;


    public String getCadaster_number() {
        return cadastre_number;
    }

    public void setCadaster_number(String cadaster_number) {
        this.cadastre_number = cadaster_number;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public CatalogItem getObjectType() {
        return objectType;
    }

    public void setObjectType(CatalogItem objectType) {
        this.objectType = objectType;
    }

    public Integer getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(Integer inventory_number) {
        this.inventory_number = inventory_number;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public Integer getRoomscount() {
        return roomscount;
    }

    public void setRoomscount(Integer roomscount) {
        this.roomscount = roomscount;
    }

    public Integer getReadiness() {
        return readiness;
    }

    public void setReadiness(Integer readiness) {
        this.readiness = readiness;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public CatalogItem getUse_purpose() {
        return use_purpose;
    }

    public void setUse_purpose(CatalogItem use_purpose) {
        this.use_purpose = use_purpose;
    }

    public CatalogItem getLand_category() {
        return land_category;
    }

    public void setLand_category(CatalogItem land_category) {
        this.land_category = land_category;
    }

}
