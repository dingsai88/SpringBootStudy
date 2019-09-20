package designpatterns.composite;

/**
 * 8. Composite����ϣ�����������ϳ���״��νṹ��ʹ�û��Ե����������϶������һ�µķ����ԡ�
 */
public class ZTestMain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-27 ����12:41:27
     */
    public static void main(String[] args) {
        CompanyImpl zong = new CompanyImpl("�ܹ�˾");
        zong.add(new HRDepartment("�ܹ�˾HR"));
        zong.add(new FinanceDepartment("�ܹ�˾����"));

        CompanyImpl bj = new CompanyImpl("bj��˾");
        bj.add(new HRDepartment("bj��˾HR"));
        bj.add(new FinanceDepartment("bj��˾����"));


        CompanyImpl sh = new CompanyImpl("sh��˾");
        sh.add(new HRDepartment("sh��˾HR"));
        sh.add(new FinanceDepartment("sh��˾����"));

        zong.add(bj);
        zong.add(sh);
        System.out.println("-----------��˾�ṹ-------------");
        zong.display(1);
        System.out.println("-----------����ְ��-------------");
        zong.lineOfDuty();
    }


}
