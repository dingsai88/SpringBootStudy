package designpatterns.composite;

/**
 * 8. Composite（组合）：将对象组合成树状层次结构，使用户对单个对象和组合对象具有一致的访问性。
 */
public class ZTestMain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-27 下午12:41:27
     */
    public static void main(String[] args) {
        CompanyImpl zong = new CompanyImpl("总公司");
        zong.add(new HRDepartment("总公司HR"));
        zong.add(new FinanceDepartment("总公司财务"));

        CompanyImpl bj = new CompanyImpl("bj公司");
        bj.add(new HRDepartment("bj公司HR"));
        bj.add(new FinanceDepartment("bj公司财务"));


        CompanyImpl sh = new CompanyImpl("sh公司");
        sh.add(new HRDepartment("sh公司HR"));
        sh.add(new FinanceDepartment("sh公司财务"));

        zong.add(bj);
        zong.add(sh);
        System.out.println("-----------公司结构-------------");
        zong.display(1);
        System.out.println("-----------部门职能-------------");
        zong.lineOfDuty();
    }


}
