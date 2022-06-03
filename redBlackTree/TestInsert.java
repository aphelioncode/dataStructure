import java.util.*;
class TestInsert{
  public static void main(String[] args){
    RedBlackTree rbt=new RedBlackTree();
    Random r=new Random(System.currentTimeMillis());
    for(int i=1;i<10;i++){
      int x=r.nextInt(100)+1;
      rbt.insert(x);
      rbt.print();
    }
  }
}
