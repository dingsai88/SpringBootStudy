package designpatterns.abstratfactory;

/**
 * ����������ӿ�
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 ����10:20:32
 */
public interface DepartmentService {

	/*
	 * ���沿����Ϣ
	 */
	public void insertDepartment(Department department);

	/*
	 * ��ò�����Ϣ
	 */
	public Department getDepartment(Integer i);
}
