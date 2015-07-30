package nla.local.controller.forms;

/**
 * Created by belonovich on 27.04.2015.
 */
public class DeclarantForm {

    private  Long idDecl;

    private  Integer idSubject;

    private  Integer type;

    private Long[] clients;

    public Long getIdDecl() {
        return idDecl;
    }

    public void setIdDecl(Long idDecl) {
        this.idDecl = idDecl;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long[] getClients() {
        return clients;
    }

    public void setClients(Long[] clients) {
        this.clients = clients;
    }
}
