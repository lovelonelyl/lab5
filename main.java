import java.util.HashMap; import java.util.Map; 
 
public class LocalDataImpl implements Data  {     public static final int MAX_ELEMENT; 
    private static LocalDataImpl ourInstance = new LocalDataImpl();     public static LocalDataImpl getInstance() {         return ourInstance; 
    } 
    private LocalDataImpl() { 
    } 
 
   
    public Map getRoot() {                 Map result = new HashMap();         for (int i = 1; i < MAX_ELEMENT; i++) { 
            Node node = new Node(Integer.toString(i),"#","Node "+i);                   result.put(node.getId(),node); 
        }         return result; 
    } 
    public Map getById(String parentId) {              Map result = new HashMap();         if (Integer.parseInt(parentId)%2==0)             for (int i = 1; i < MAX_ELEMENT; i++) {                 String newId = parentId+Integer.toString(i); 
                Node node = new Node(newId, parentId,"Node "+newId);                 result.put(node.getId(),node); 
            }         return result; 
    } 
} 
 
public class WorkQueue 
{     private final int nThreads;     private final PoolWorker[] threads;     private final LinkedList queue; 
  
    public WorkQueue(int nThreads) 
    { 
        this.nThreads = nThreads;         queue = new LinkedList();         threads = new PoolWorker[nThreads]; 
  
        for (int i=0; i<nThreads; i++) {             threads[i] = new PoolWorker();             threads[i].start(); 
        } 
    } 
    public void execute(Runnable r) {         synchronized(queue) {             queue.addLast(r);             queue.notify(); 
        } 
    } 
    private class PoolWorker extends Thread {         public void run() { 
            Runnable r; 
 
            while (true) {                 synchronized(queue) {                     while (queue.isEmpty()) { 
                        try                         {                             queue.wait(); 
                        } 
                        catch (InterruptedException ignored) 
                        { 
                        } 
                    } 
                    r = (Runnable) queue.removeFirst(); 
                } 
  
                // If we don't catch RuntimeException,                  // the pool could leak threads                 try { 
                    r.run(); 
                } 
                catch (RuntimeException e) {                     // You might want to log something here 
                } 
            } 
        } 
    } 
} class TreeSort {     public static void main(String args[]) { 
        Scanner scanner = new Scanner(System.in);         Scanner string = new Scanner(System.in);         boolean flags = true;         Tree myTree;         int str = scanner.nextInt();         myTree = new Tree(str);         while(flags){             str = scanner.nextInt();             switch(str){                 case 1:{                     boolean flag = false;                     while(!flag){                         str = scanner.nextInt();                         myTree.add(new Tree(str)); 
                        System.out.print("Продовжити(y/n): ");                         String strig = string.nextLine();                         if("y".equals(strig))flag = false;                         else flag = true; 
                    }break;                 }                 case 2:{                     myTree.traverse();                     break;                 }                 case 0:{                      flags = false;                     break; 
                } 
            } 
        } 
    } 
} private Node connectNodes(Node parent, Node node) { 
        if (node == null) {             return parent; 
        }         if (parent == null) {             return node;         } else {             if (compare(node, parent) < 0) {                 return connectNodes(node, parent); 
            } 
            Node cur = parent;             Node n = node;             while (cur != null) {                 if (cur.left == null) {                     cur.left = n; 
                    n.parent = cur;                     cur.k++;                     break;                 }                 if (cur.right == null) {                     if (compare(n, cur.left) <= 0) {                         cur.right = cur.left;                         cur.left = n; 
                        n.parent = cur;                         cur.k++;                         break;                     } else {                         cur.right = n; 
                        n.parent = cur;                         cur.k++;                         break; 
                    } 
                } 
                if (compare(n, cur.left) <= 0) {                     Node tmp = cur.left;                     cur.left = n; 
                    n.parent = cur;                     cur.k++;                     cur = n;                     n = tmp;                     continue; 
                } 
                if (compare(n, cur.right) < 0                         && compare(n, cur.left) > 0) {                     cur.k++; 
                    if (cur.right.k < cur.left.k) {                         Node tmp = cur.right;                         cur.right = n; 
                        n.parent = cur;                         cur = n;                         n = tmp;                     } else {                         cur = cur.left; 
                    }                     continue; 
                }             } 
            return parent; 
        } 
