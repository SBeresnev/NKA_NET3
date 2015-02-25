package nla.local.services;

import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;

/**
 * Created by beresnev on 24.02.2015.
 */

public interface IPassportService {

   RespNCA findSubject(PassportNCA PassNCA);

}

