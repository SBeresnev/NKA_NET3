package nla.local.pojos.rights;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by beresnev on 16.01.2016.
 */
@Entity
@Table( name = "RIGHTS", schema = "NKA_NET3_DEV")
public class RightTest {

    @Id
    @GeneratedValue(generator="seq_id")
    @GenericGenerator(
            name="seq_id",
            strategy = "nla.local.util.CodeGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "seq_name", value = "SEQ_RIGHT_ID"))
    @Column(name="RIGHT_ID", unique=true, nullable=false )
    private Long right_id;

   // @Column(name = "END_DATE")
   // @JsonSerialize(using=DateSerializer.class)
   // private Date end_date;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "rightTest")
    private Set<RightOwnerTest> rightOwnerTest;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<RightOwnerTest> getRightOwners() {
        return rightOwnerTest;
    }

    public void setRightOwners(Set<RightOwnerTest> rightOwnerTest) {
        this.rightOwnerTest = rightOwnerTest;
    }

    public Long getRight_id() {
        return right_id;
    }

    public void setRight_id(Long right_id) {
        this.right_id = right_id;
    }

    /*
    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }*/

}
