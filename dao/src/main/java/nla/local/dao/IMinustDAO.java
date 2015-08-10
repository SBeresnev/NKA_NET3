package nla.local.dao;

import nla.local.pojos.subjects.JurMINJST;

import java.util.List;

/**
 * Created by beresnev on 07.08.2015.
 */
public interface IMinustDAO {

    public List<JurMINJST> getDatabyName(String name);

    public JurMINJST getDatabyNumber(Integer unp);

}
