class RedBlackTree{
  class Node{
    int value;
    Node left,right;
    int color;// 0 red, 1 black;
  }
  Node root;
  final Node nil;

  public RedBlackTree(){
    nil=new Node();
    nil.value=0;
    nil.color=1;
    nil.left=nil;
    nil.right=nil;
    root=nil;
  }

  public void insert(int v){
    root=_insert(root,v);
    root.color=1;
  }


// 和普通二叉树的插入类似，只不过最后加入了一个维持平衡的步骤。
  Node _insert(Node cur,int v){
    if(cur==nil){return getNewNode(v);}
    if(cur.value==v){return cur;}
    if(cur.value>v){cur.left=_insert(cur.left,v);}
    else{cur.right=_insert(cur.right,v);}
    return insertMaintain(cur);
  }

// 插入的都是红色节点，解决红红冲突，调整前后路径上黑色节点数量不变
// 只需考虑cur的儿子节点和孙子节点的红红冲突，可采取的策略是红色上浮或者红色下沉
  Node insertMaintain(Node cur){
    if(!hasRedChild(cur)){return cur;}
    // 左右孩子都是红色，cur必是黑色
    // 因为红色上浮过程中最多造成一处红红冲突，不可能有两处红红冲突
    if(cur.left.color==0&&cur.right.color==0){
      cur.left.color=1;
      cur.right.color=1;
      cur.color=0;
      return cur;
    }
    //冲突发生在左孩子
    if(cur.left.color==0&&hasRedChild(cur.left)){
      if(cur.left.right.color==0){
        cur.left=rotateLeft(cur.left);
      }
      cur=rotateRight(cur);
      cur.color=1;
      cur.right.color=0;
    }
    // 冲突发生在右孩子
    else if(cur.right.color==0&&hasRedChild(cur.right)){
      if(cur.right.left.color==0){
        cur.right=rotateRight(cur.right);
      }
      cur=rotateLeft(cur);
      cur.color=1;
      cur.left.color=0;
    }
    return cur;
  }

  Node rotateLeft(Node cur){
    Node tmp=cur.right;
    cur.right=tmp.left;
    tmp.left=cur;
    return tmp;
  }

  Node rotateRight(Node cur){
    Node tmp=cur.left;
    cur.left=tmp.right;
    tmp.right=cur;
    return tmp;

  }

  boolean hasRedChild(Node cur){
    return cur.left.color==0||cur.right.color==0;
  }

  //新节点默认是红色，因为红色不影响根节点到叶节点路径上黑色节点的数目
  Node getNewNode(int v){
    Node node=new Node();
    node.value=v;
    node.color=0;
    node.left=nil;
    node.right=nil;
    return node;
  }

  void print(){
    _print(root);
    System.out.println("------------------------");
  }
  void _print(Node node){
    if(node==nil){return;}
    System.out.printf("%d|\t%d\t%d\t%d\n",node.color,node.value,node.left.value,node.right.value);
    _print(node.left);
    _print(node.right);
  }
}
