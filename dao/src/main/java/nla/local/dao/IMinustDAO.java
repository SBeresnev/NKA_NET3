package nla.local.dao;

import nla.local.pojos.subjects.JurMINUST;

import java.util.List;

/**
 * Created by beresnev on 07.08.2015.
 */
public interface IMinustDAO {

    public List<JurMINUST> getDatabyName(String name);

    public JurMINUST getDatabyUnp(Integer unp);

}
