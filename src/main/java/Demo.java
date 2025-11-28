import structures.SimpleList;
import util.ArrayUtils;

import java.util.NoSuchElementException;

public class Demo {
    public static void main(String[] args) {
        try{
            Integer value = 5;
            //Integer[] array = new Integer[]{1, 2, 3, 4, 5};
//            System.out.println("Valor a buscar: " + value);
//            Integer found = ArrayUtils.firstCoincidence(array, value);
//            System.out.println("Valor encontrado: " + found);
            SimpleList<Integer> list = new SimpleList<Integer>();
            list.pushBack(1);
            list.pushBack(2);
            list.pushBack(3);
            list.pushBack(4);
            list.pushBack(5);
//            Integer foundInList = list.firstCoincidence();
        } catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
