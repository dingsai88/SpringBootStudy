package designpatterns.mediator;

/**
 * ���ҳ�����
 * 
 * @author daniel
 * 
 */
abstract class Country {
    //���ϻ���
 protected UnitedNations mediator;

 public Country(UnitedNations mediator) {
  this.mediator = mediator;
 }
}