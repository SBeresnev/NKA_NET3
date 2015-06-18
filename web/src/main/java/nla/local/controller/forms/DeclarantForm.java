package nla.local.controller.forms;

/**
 * Created by belonovich on 27.04.2015.
 */
public class DeclarantForm {
    private  Integer idDecl, idSubject, type;

    private Integer[] clients;

    public Integer getIdDecl() {
        return idDecl;
    }

    public void setIdDecl(Integer idDecl) {
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

    public Integer[] getClients() {
        return clients;
    }

    public void setClients(Integer[] clients) {
        this.clients = clients;
    }
}
