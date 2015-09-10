package nla.local.services.impl.subjects;

import nla.local.dao.IMinjstDAO;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.dict.CatalogPk;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.JurMINJST;
import nla.local.services.IJusticeService;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.log4j.Logger;
import org.minustserv.ArrayOfSubjectData;
import org.minustserv.SubjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.Subjects;

import javax.security.auth.Subject;
import java.util.List;

/**
 * Created by beresnev on 10.08.2015.
 */
@Service
public class JusticeServiceImp extends JSubjectServiceImp implements IJusticeService {

    private static Logger log = Logger.getLogger(PassportServiceImp.class);

    @Autowired
    private IMinjstDAO minustDAO;

    @Autowired
    private Subjects webServiceMinjust;


    @Autowired
    public CodeGenerator scg;

    @Autowired
    public CatalogServiceImp commonDict;


    @Override
    public JurMINJST findSubjectUnp(Integer unp) throws ServiceDaoException {

        ArrayOfSubjectData ss = webServiceMinjust.getSubjectData(unp);

        List<SubjectData> ret_val_list = ss.getSubjectData();

        //JurMINJST ret_val = minustDAO.getDatabyNumber(unp);

        return ret_val_list.size() >0 ? (JurMINJST) ret_val_list.get(0) : null;

    }

    @Override
    public List<JurMINJST> findSubjectName(String name ) throws ServiceDaoException {

        List<JurMINJST> ret_val = minustDAO.getDatabyName(name);

        return ret_val;

    }

    @Override
    public JPerson casttoPerson(JurMINJST resp) throws ServiceException, ServiceDaoException {

        JPerson jp = new JPerson();

        //VOBLAST || ' обл., ' || VRAION || ' р-н., ' || VNTNPK || VNP || ', ' || VNTULK || VULITSA || VDOM || ', '|| VKORP

        jp.unp = String.valueOf(resp.getUNP());

        jp.regNumber = String.valueOf(resp.getUNP());

        jp.bothRegDate = resp.getRegDate();

        jp.fullname = resp.getVNAIM();

        jp.shortname = resp.getVN();


        CatalogPk cp_test = new CatalogPk();

        cp_test.setAnalytic_type(Integer.decode(CatalogConstants.ORG_STRUCTURE));

        cp_test.setCode_id(resp.getNkOpf());

        jp.orgRightForm = commonDict.getCatalogItem(cp_test);

        jp.subjectType =  CollectionUtils.find(commonDict.getCatalogItemsByTyp(Integer.decode(CatalogConstants.SUBJECT_TYP)), new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_id().equals(200); // Юридические лица
            }
        });

        jp.address = resp.getVoblast() != null ?   resp.getVoblast() +" обл; " :"" ;

        jp.address += resp.getVraion() != null ?   resp.getVraion() +" р-н; " :"" ;

        jp.address += resp.getVntnpk() != null ?   resp.getVntnpk() +" " + resp.getVnp() + "; " :"" ;

        jp.address += resp.getVulitsa() != null ?   resp.getVntulk() +" " + resp.getVulitsa() + " " :"" ;

        jp.address += resp.getVdom() != null ?   " д. " + resp.getVdom() + ", " :"" ;

        jp.address += resp.getVkorp() != null ?   " корп. " + resp.getVdom() + ", " :"" ;

        jp.address += resp.getVkorp() != null ?   " кв. " + resp.getVpom() + "" :"" ;

        jp.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());

        return jp;
    }
}
