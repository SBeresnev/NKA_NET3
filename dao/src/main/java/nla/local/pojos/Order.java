package nla.local.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serega on 18.09.2014.
 */
@Entity
@Table(name="ORDER_PVT")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ID;

   /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PROJECT_PRID")
    private Project Project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PERSON_PID")
    private Customer Customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SERVICE_SID")
    private Service Service;

    @Column(name = "COST")
    private Integer Cost;

    @Column(name = "COUNT")
    private Integer Count;

    @Column(name = "ORDER_DATE")
    private Date OrderDate;

    public void setOID (Integer id) {ID = id;}
    public void setPID (Project project) {Project = project;}
   // public void setUID (Customer customer) {Customer = customer;}
    public void setSID (Service service) {Service = service;}
    public void setCost (Integer cost) {Cost = cost;}
    public void setCount (Integer count) {Count = count;}
    public void setOrderDate (Date order_date) {OrderDate = order_date;}

    public Integer getOID () {return ID;}
    public Project getPID () {return Project ;}
    //public Customer getUID () {return Customer;}
    public Service getSID () { return Service;}
    public Integer getCost () {return Cost;}
    public Integer getCount () {return Count;}
    public Date getOrderDate () {return OrderDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Order order = (Order) o;

        if (ID != null ? !ID.equals(order.ID) : order.ID != null) return false;
        if (OrderDate != null ? !OrderDate.equals(order.OrderDate) : order.OrderDate != null) return false;
        if (Project != null ? !Project.equals(order.Project) : order.Project != null) return false;
       // if (Customer != null ? !Customer.equals(order.Customer) : order.Customer != null) return false;
        if (Service != null ? !Service.equals(order.Service) : order.Service != null) return false;
        if (Count != null ? !Count.equals(order.Count) : order.Count != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (Project != null ? Project.hashCode() : 0);
    //    result = 31 * result + (Customer != null ? Customer.hashCode() : 0);
        result = 31 * result + (Service != null ? Service.hashCode() : 0);
        result = 31 * result + (Count != null ? Count.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        return "Order : ID: " + ID + "Project : ID: " + Project.getID()  + " Service : ID: " + Service.ID +"Count: " + Count;
    }

 */
}
