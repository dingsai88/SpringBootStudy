package designpatterns.observer;

import java.util.Observable;

/**
 * �����۸���
 * @author daniel
 *
 */
public class AgPrice extends Observable {
  
   private double price;
   public AgPrice(double price){
    this.price=price;    
   }
   
   public void setPrice(double price){
       super.setChanged();
        super.notifyObservers(price);
    this.price=price;
   }   
   
   public String showPrice(){
    return "�����۸�Ϊ:"+this.price;
   }
}