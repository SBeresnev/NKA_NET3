package nla.local.controller.forms;

import nla.local.pojos.docs.Document;

/**
 * Created by beresnev on 25.03.2016.
 */
public class DocumentForm extends Document {


    private Byte[] docData;

    public Byte[] getDocData() {
        return docData;
    }

    public void setDocData(Byte[] docData) {
        this.docData = docData;
    }


}
