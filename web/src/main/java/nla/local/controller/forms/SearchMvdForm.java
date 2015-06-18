package nla.local.controller.forms;

import nla.local.pojos.subjects.PassportNCA;

/**
 * Created by belonovich on 03.03.2015.
 */

public class SearchMvdForm {
    private String seriesAndNumber;
    private String idNumber;
    private PassportNCA passportNCA;

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        seriesAndNumber.toUpperCase();
        this.seriesAndNumber = seriesAndNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        idNumber.toUpperCase();
        this.idNumber = idNumber;
    }

    public PassportNCA createOrGetPassportNCAObj() {
        if (this.passportNCA == null) {
            PassportNCA passportNCA = new PassportNCA();
            passportNCA.setIdentif(this.idNumber);
            passportNCA.setSer(this.seriesAndNumber.substring(0, 2));
            passportNCA.setNum(this.seriesAndNumber.substring(2));
            this.passportNCA = passportNCA;
        }
        return passportNCA;
    }
}
