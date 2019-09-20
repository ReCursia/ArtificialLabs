package utils;

import model.Node;
import model.Table;

public class OutputUtils {
    public static void outputNodes(Node node){
        if(node != null){
            do{
                System.out.println(node.getData());
            } while ((node = node.getParent()) != null);
        }
    }
}
