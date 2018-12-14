package main.java.reflect;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/14 16:27
 **/
public class ATest1 {

    public static void main(String[] args){
        //创建A类的实例
        A a = new A();
        //让a实例的a Field值自加
        a.a ++;
        System.out.println(a.a);
    }
}
