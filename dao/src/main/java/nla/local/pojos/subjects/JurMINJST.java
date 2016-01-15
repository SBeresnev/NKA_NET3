package nla.local.pojos.subjects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.minustserv.SubjectData;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by beresnev on 07.08.2015.
 */
public class JurMINJST implements Serializable{



    private static final HashMap<Integer,String > opf = new HashMap<Integer, String>();
    static
    {

        opf.put(0, "Не определено");
        opf.put(1000, "Коммерческая организация");
        opf.put(1100, "Хозяйственное товарищество/общество");
        opf.put(1110, "Полное товарищество");
        opf.put(1120, "Коммандитное товарищество");
        opf.put(1130, "Акционерное общество");
        opf.put(1131, "Открытое акционерное общество");
        opf.put(1132, "Закрытое акционерное общество");
        opf.put(1140, "Общество с ограниченной ответственностью");
        opf.put(1150, "Общество с дополнительной ответственностью");
        opf.put(1200, "Производственный кооператив");
        opf.put(1300, "Унитарное предприятие");
        opf.put(1310, "Унитарное предприятие, основанное на праве хозяйственного ведения");
        opf.put(1320, "Унитарное предприятие, основанное на праве оперативного управления");
        opf.put(1400, "Государственное объединение, являющееся коммерческой организацией");
        opf.put(1500, "Арендное предприятие");
        opf.put(1600, "Коллективнок предприятие *");//Старый классификатор
        opf.put(1700, "Крестьянское opf.put(фермерское) хозяйство");
        opf.put(2000, "Некоммерческая организация *");//Старый классификатор
        opf.put(2100, "Потребительский кооператив *");//Старый классификатор
        opf.put(2110, "Потребительский союз *");//Старый классификатор
        opf.put(2120, "Потребительское общество *");//Старый классификатор
        opf.put(2130, "Жилищно-строительный кооператив *");//Старый классификатор
        opf.put(2140, "Садоводческое/садово-огородное товарищество *");//Старый классификатор
        opf.put(2190, "Прочий потребительский кооператив *");//Старый классификатор
        opf.put(2200, "Общественная организация opf.put(объединение) *");//Старый классификатор
        opf.put(2300, "Религиозная организация opf.put(объединение) *");//Старый классификатор
        opf.put(2400, "Фонд *");//Старый классификатор
        opf.put(2500, "Учреждение *");//Старый классификатор
        opf.put(2600, "Объединение юридических лиц *");//Старый классификатор
        opf.put(2610, "Ассоциация *");//Старый классификатор
        opf.put(2620, "Союз *");//Старый классификатор
        opf.put(2700, "Товарищество собственников *");//Старый классификатор
        opf.put(3000, "Обособленное подразделение юридических лиц *");//Старый классификатор
        opf.put(3100, "Представительство *");//Старый классификатор
        opf.put(3200, "Филиал *");//Старый классификатор
        opf.put(4000, "Индивидуальный предприниматель *");//Старый классификатор
        opf.put(5000, "Некоммерческая организация");
        opf.put(5100, "Потребительский кооператив");
        opf.put(5110, "Союз потребительских обществ");
        opf.put(5120, "Потребительское общество");
        opf.put(5130, "Организация застройщиков");
        opf.put(5131, "Жилищно-строительный кооператив");
        opf.put(5132, "Жилищный кооператив");
        opf.put(5140, "Садоводческое товарищество");
        opf.put(5150, "Гаражный кооператив/кооператив, осуществляющий эксплуатацию автомобильных стоянок");
        opf.put(5190, "Прочий потребительский кооператив");
        opf.put(5200, "Общественная, религиозная организация opf.put(объединение)");
        opf.put(5210, "Общественная организация opf.put(объединение)");
        opf.put(5211, "Политическая партия");
        opf.put(5212, "Профессиональный союз");
        opf.put(5219, "Иная общественная организация opf.put(объединение)");
        opf.put(5220, "Религиозная организация opf.put(объединение)");
        opf.put(5300, "Республиканское государственно-общественное объединение");
        opf.put(5400, "Фонд");
        opf.put(5500, "Учреждение");
        opf.put(5600, "Объединение юридических лиц и opf.put(или) индивидуальных предпринимателей");
        opf.put(5610, "Ассоциация");
        opf.put(5620, "Союз");
        opf.put(5800, "Государственное объединение, являющееся некоммерческой организацией");
        opf.put(5900, "Иная некоммерческая организация");
        opf.put(5910, "Адвокатское бюро");
        opf.put(5920, "Коллегия адвокатов");
        opf.put(5930, "Постоянно действующий международный арбитражный opf.put(третейский) суд");
        opf.put(5940, "Постоянно действующий третейский суд, являющийся некоммерческой организацией");
        opf.put(5950, "Товарищество собственников");
        opf.put(5990, "Иная некоммерческая организация, не включенная в другие группировки");
        opf.put(6000, "Субъект, созданный без образования юридического лица");
        opf.put(6100, "Индивидуальный предприниматель");
        opf.put(6200, "Представительство иностранных организаций, действующее на территории РБ");
        opf.put(6500, "Простое товарищество");
        opf.put(6900, "Иной субъект, созданный без образования юридического лица, не включенный в другие группировки");

    }


    private Integer UNP;

    private String VNAIM;

    private String VN;

    private Date RegDate;

    private Date UnRegDate;

    private String RegName;

    private String UnRegName;

    private String Opf;

    private String fullAddress;

    private Integer NkStran;

    @JsonIgnore
    private SubjectData subjectData;

    @JsonIgnore
    private Integer NkOpf;

    @JsonIgnore
    private String voblast;

    @JsonIgnore
    private String vraion;

    @JsonIgnore
    private String vntnpk;

    @JsonIgnore
    private String vnp;

    @JsonIgnore
    private String vntulk;

    @JsonIgnore
    private String vulitsa;

    @JsonIgnore
    private String vdom;

    @JsonIgnore
    private String vkorp;

    @JsonIgnore
    private String vpom;


    public SubjectData getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(SubjectData subjectData) {
        this.subjectData = subjectData;
    }



    public String getFullAddress() {

       /* fullAddress = this.getVoblast() != null ?   this.getVoblast() +" обл; " :"" ;

        fullAddress += this.getVraion() != null ?   this.getVraion() +" р-н; " :"" ;

        fullAddress += this.getVntnpk() != null ?   this.getVntnpk() +" " + this.getVnp() != null ? this.getVnp() : "" + "; " :"" ;

        fullAddress += this.getVulitsa() != null?   this.getVntulk() != null ? this.getVntulk() : "" +" " + this.getVulitsa() + " " :"" ;

        fullAddress += this.getVdom() != null ?   " д. " + this.getVdom() + ", " :"" ;

        fullAddress += this.getVkorp() != null ?   " корп. " + this.getVdom() + ", " :"" ;

        fullAddress += this.getVpom() != null ?   " кв. " + this.getVpom() + "" :"" ; */

        fullAddress = fullAddress != null ? fullAddress : subjectData.getADDRESS().getValue();

        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {

        this.fullAddress = fullAddress;
    }

    public String getOpf() {

        Opf = Opf != null ? Opf : opf.get(subjectData.getNKOPF().intValue());

        return Opf;
    }

    public void setOpf(String opf) {
        Opf = opf;
    }

    public Integer getNkOpf() {

        NkOpf = NkOpf != null ? NkOpf : subjectData.getNKOPF().intValue();

        return NkOpf ;

    }

    public void setNkOpf(Integer nkOpf) {

        this.setOpf(opf.get(nkOpf));

        NkOpf = nkOpf;
    }

    public Integer getNkStran() {

        NkStran = NkStran != null ? NkStran : subjectData.getNKSTRAN().intValue();

        return NkStran;

    }

    public void setNkStran(Integer nkStran) {
        NkStran = nkStran;
    }

    public Integer getUNP() {

        UNP = UNP != null ? UNP : subjectData.getNGRN().intValue();

        return UNP;
    }

    public void setUNP(Integer UNP) {
        this.UNP = UNP;
    }

    public String getVNAIM() {

        VNAIM = VNAIM != null ? VNAIM : subjectData.getVNAIM_().getValue();

        return VNAIM;
    }

    public void setVNAIM(String VNAIM) {
        this.VNAIM = VNAIM;
    }

    public String getVN() {

        VN = VN != null ? VN : subjectData.getVN_().getValue();

        return VN;
    }

    public void setVN(String VN) {

        this.VN = VN;
    }

    public String getVoblast() {

        voblast = voblast != null ? voblast : subjectData.getVOBLAST().getValue();

        return voblast;
    }

    public void setVoblast(String voblast) {

        this.voblast = voblast;
    }

    public String getVraion() {

        vraion = vraion != null ? vraion : subjectData.getVRAION().getValue();

        return vraion;
    }

    public void setVraion(String vraion) {

        this.vraion = vraion;
    }

    public String getVntnpk() {

        vntnpk = vntnpk != null ? vntnpk : subjectData.getVNTNPK().getValue();

        return vntnpk;
    }

    public void setVntnpk(String vntnpk) {
        this.vntnpk = vntnpk;
    }

    public String getVnp() {

        vnp = vnp != null ? vnp : subjectData.getVNP().getValue();

        return vnp;
    }

    public void setVnp(String vnp) {
        this.vnp = vnp;
    }

    public String getVntulk() {

        vntulk = vntulk != null ? vntulk : subjectData.getVNTULK().getValue();

        return vntulk;
    }

    public void setVntulk(String vntulk) {

        this.vntulk = vntulk;

    }

    public String getVulitsa() {

        vulitsa = vulitsa != null ? vulitsa : subjectData.getVULITSA().getValue();

        return vulitsa;
    }

    public void setVulitsa(String vulitsa) {
        this.vulitsa = vulitsa;
    }

    public String getVdom() {

        vdom = vdom != null ? vdom : subjectData.getVDOM().getValue();

        return vdom;
    }

    public void setVdom(String vdom) {

        this.vdom = vdom;

    }

    public String getVkorp() {

        vkorp = vkorp != null ? vkorp : subjectData.getVKORP().getValue();

        return vkorp;

    }

    public void setVkorp(String vkorp) {

        this.vkorp = vkorp;

    }

    public String getVpom() {

        vpom = vpom != null ? vpom : subjectData.getVPOM().getValue();

        return vpom;

    }

    public void setVpom(String vpom) {

        this.vpom = vpom;
    }

    public Date getRegDate() {


        if (RegDate == null && subjectData.getDCRT().toGregorianCalendar().getTimeInMillis() > 0) {

            RegDate = subjectData.getDCRT().toGregorianCalendar().getTime();
        }


        return RegDate;

    }

    public void setRegDate(Date regDate) {
        RegDate = regDate;
    }

    public Date getUnRegDate() {

           if (UnRegDate == null && subjectData.getDISKL().toGregorianCalendar().getTimeInMillis() > 0) {

               UnRegDate = subjectData.getDISKL().toGregorianCalendar().getTime();
           }


        return UnRegDate;
    }

    public void setUnRegDate(Date unRegDate) {
        UnRegDate = unRegDate;
    }

    public String getRegName() {

        RegName = RegName != null ? RegName : subjectData.getVORGCRT().getValue();

        return RegName;
    }

    public void setRegName(String regName) {

        RegName = regName;

    }

    public String getUnRegName() {

        UnRegName = UnRegName != null ? UnRegName : subjectData.getVORGISKL().getValue();

        return UnRegName;
    }

    public void setUnRegName(String unRegName) {
        UnRegName = unRegName;
    }

    @Override
    public int hashCode() {

        int result = getUNP() != null ? UNP.hashCode() : 0;

        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JurMINJST)) return false;
        JurMINJST jur_prs = (JurMINJST) o;
        if (this.getUNP() != null ? !UNP.equals(jur_prs.getUNP()) : jur_prs.getUNP() != null) return false;

        return true;
    }

}
