package designpatterns.composite;

/**
 * HR������
 * @author daniel
 *
 */
public class HRDepartment extends Company {
 public HRDepartment (String name){
  super(name);
 }

 @Override
 public void add(Company c) {
  // TODO Auto-generated method stub

 }

 @Override
 public void display(int i) {
  StringBuffer sb=new StringBuffer();
  for(int j=0;j<i;j++){
   sb.append("-");
  }
  System.out.println(sb.toString()+this.name);
 }

 @Override
 public void lineOfDuty() {
  // TODO Auto-generated method stub
  System.out.println(this.name+"   Ա��������ѵ");
 }

 @Override
 public void remove(Company c) {
  // TODO Auto-generated method stub

 }

}