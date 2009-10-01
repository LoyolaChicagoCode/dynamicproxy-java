package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Example {
  public static void main(String[] args) {

    InvocationHandler handler = new InvocationHandler() {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method + " invoked on object " + this);
        if (method.getName().equals("blah")) {
          return "hello world " + args[0];
        } else if (method.getName().equals("kuck")) {
          return 77; 
        }
        return null;
      }
    };

    Object x = Proxy.newProxyInstance(
        Example.class.getClassLoader(), new Class[] { Blah.class, Kuck.class }, handler);

    System.out.println(x instanceof Blah);
    System.out.println(x instanceof Kuck);
    System.out.println(((Blah) x).blah(88));
    System.out.println(((Kuck) x).kuck());
    System.out.println(x.getClass().getName());
  }
}

interface Blah {
  String blah(int arg);
}

interface Kuck {
  int kuck();
}