package nla.local.services;

import nla.local.pojos.Address;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by beresnev on 29.01.2015.
 */
public interface IAdressService extends IService<Address> {

    public List findAddress(DetachedCriteria dc);


}
