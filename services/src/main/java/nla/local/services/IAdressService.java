package nla.local.services;

import nla.local.pojos.Addresses;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by beresnev on 29.01.2015.
 */
public interface IAdressService extends IService<Addresses> {

    public List findAddress(DetachedCriteria dc);


}
