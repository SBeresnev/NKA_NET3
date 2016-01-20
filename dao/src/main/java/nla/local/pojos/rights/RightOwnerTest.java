package nla.local.pojos.rights;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by beresnev on 16.01.2016.
 */
@Entity
@Table( name = "RIGHTOWNERS", schema = "NKA_NET3_DEV")
public class RightOwnerTest implements Serializable {

    @Id
    @Column(name = "RIGHT_OWNER_ID")
    @SequenceGenerator(name="right_seq", sequenceName="SEQ_RIGHT_OWNERS")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="right_seq")
    private Long right_owner_id;


    @Column(name = "STATUS")
    private Integer status;


    @ManyToOne
    @JoinColumn(name = "RIGHT_ID", referencedColumnName = "RIGHT_ID")

    private RightTest rightTest;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRight_owner_id() {
        return right_owner_id;
    }

    public void setRight_owner_id(Long right_owner_id) {
        this.right_owner_id = right_owner_id;
    }

    public RightTest getRightTest() {
        return rightTest;
    }

    public void setRightTest(RightTest rightTest) {
        this.rightTest = rightTest;
    }

}