package designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ��˾ʵ����
 *
 * @author daniel
 */
public class CompanyImpl extends Company {
    /**
     * ��˾���߲��ų���
     */
    private List<Company> children = new ArrayList<Company>();

    public CompanyImpl(String name) {
        super(name);
    }

    @Override
    public void add(Company c) {
        children.add(c);

    }

    /**
     * չʾ��˾ ��������  �㼶
     * @param i
     */
    @Override
    public void display(int i) {
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < i; j++) {
            sb.append("-");
        }
        System.out.println(sb.toString() + this.name);

        for (Company company : children) {
            company.display(i + 2);
        }
    }

    @Override
    public void lineOfDuty() {
        for (Company company : children) {
            company.lineOfDuty();
        }

    }

    @Override
    public void remove(Company c) {
        children.remove(c);
    }
}
