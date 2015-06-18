package nla.local.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Использовать аккуратно чистит базу :)
 * Created by beresnev on 24.04.2015.
 */


@Component
@Transactional
public class BaseClean {

    @Autowired
    public CodeGenerator scg;

    private static String cleanDecl = "delete from DECL";
    private static String cleanDeclarants = "delete from DECLARANTS";
    private static String cleanDecluser = "delete from DECLUSER";
    private static String cleanOperation = "delete from OPERATIONS";
    private static String cleanDeclresolution = "delete from DECLRESOLUTIONS";

    private static String cleanSubject = "delete from SUBJECTS";
    private static String cleanSubjectData = "delete from SUBJECTSDATA";
    private static String cleanSubjectDataF = "delete from SUBJECTSDATA_F";
    private static String cleanSubjectDataJ = "delete from SUBJECTSDATA_J";
    private static String cleanOfficialuser = "delete from OFFICIALUSERS";


    public void SubjectClean()
    {
        scg.update(cleanDecluser);
        scg.update(cleanDeclresolution);
        scg.update(cleanDeclarants);
        scg.update(cleanOperation);
        scg.update(cleanDecl);

        scg.update(cleanSubjectData);
        scg.update(cleanSubjectDataF);
        scg.update(cleanSubjectDataJ);
        scg.update(cleanOfficialuser);
        scg.update(cleanSubject);
    }

    public void DeclClean()
    {
        scg.update(cleanDecluser);
        scg.update(cleanDeclresolution);
        scg.update(cleanDeclarants);
        scg.update(cleanOperation);
        scg.update(cleanDecl);
    }

}
