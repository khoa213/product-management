import java.util.*;
import java.io.*;
public class CauTrucDuLieuAssignment2 {
    public static void main(String[] args) {
        myProductList list = new myProductList();
        Product p1 = new Product("P03","Sugar",12,25.1);
        Product p2 = new Product("P01","Miliket",10,5.2);
        Product p3 = new Product("P02","Apple",5,4.3);
        Product p4 = new Product("P05","Rose",7,15.4);
        Product p5 = new Product("P07","Beer",11,12.2);
        Product p6 = new Product("P04","Book",9,5.2);
        Product p7 = new Product("3","3",3,3);
        Product p8 = new Product("4","4",4,4);
        list.addTail(p1);
        list.addTail(p2);
        list.addTail(p3);
        list.addTail(p4);
        list.addTail(p5);
        list.addTail(p6);
        list.addTail(p7);
        list.addTail(p8);
        list.save();
        menu();
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        while (choose!=0) {
            if (choose==1) {
                System.out.println("ID | Title   | Quantity | Price");
                list.load();

                menu();
                choose = sc.nextInt();
            } else if (choose==2) {
                System.out.print("Input new ID: ");
                String newID = sc.next();
                System.out.print("Input product name: ");
                String newName = sc.next();
                System.out.print("Input product quantity: ");
                int newQuantity = sc.nextInt();
                System.out.print("Input product price: ");
                double newPrice = sc.nextDouble();
                Product newProduct = new Product(newID,newName,newQuantity,newPrice);
                list.addTail(newProduct);
                menu();
                choose = sc.nextInt();
            } else if (choose==3) {
                System.out.println("ID | Title   | Quantity | Price");
                list.traverse();
                menu();
                choose = sc.nextInt();
            } else if (choose==4) {
                list.save();
                System.out.println("Successfully");
                menu();
                choose = sc.nextInt();
            } else if (choose==5) {
                list.find();
                menu();
                choose = sc.nextInt();
            } else if (choose==6) {
                list.delete();
                System.out.println("Deleted");
                menu();
                choose = sc.nextInt();
            } else if (choose==7) {
                list.sort();
                System.out.println("Successfully");
                menu();
                choose = sc.nextInt();
            } else if (choose==8) {
                list.convertQuantity();
                System.out.println();
                menu();
                choose = sc.nextInt();
            } else if (choose==9) {
                System.out.println("ID | Title   | Quantity | Price");
                list.displayProductStack();
                menu();
                choose = sc.nextInt();
            } else if (choose==10) {
                System.out.println("ID | Title   | Quantity | Price");
                list.displayProductQueue();
                menu();
                choose = sc.nextInt();
            }
        }
    }
    public static void menu() {
        System.out.println("Product list: ");
        System.out.println("1. Load data from file and display");
        System.out.println("2. Input and add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save product list to file");
        System.out.println("5. Search by ID");
        System.out.println("6. Delete by ID");
        System.out.println("7. Sort by ID");
        System.out.println("8. Convert to binary");
        System.out.println("9. Load to stack and display");
        System.out.println("10. Load to queue and display");
        System.out.println("0. Exit");
        System.out.print("Choose one of these options above: ");

    }
}
class Product {
    String ID;
    String title;
    int quantity;
    double price;
    Product(String pID,String pTitle,int pQuantity,double pPrice) {
        ID = pID;
        title = pTitle;
        quantity = pQuantity;
        price = pPrice;
    }
    public String toString() {
        return (ID+" |"+title+"    |"+quantity+"  |"+price);
    }
}
class NodeAs2 {
    Product info;
    NodeAs2 next;
    NodeAs2 (Product x,NodeAs2 p) {
        info = x;
        next = p;
    }
    NodeAs2 (Product x) {
        this (x,null);
    }
}
class myProductList {
    static NodeAs2 head, tail;
    myProductList() {
        head = tail = null;
    }
    void clear() {
        head = tail = null;
    }
    boolean isEmpty() {
        return (head==null);
    }
    void addTail(Product x) {
        NodeAs2 newNode = new NodeAs2(x);
        if (this.head==null) {
            this.head = newNode ;
            this.tail = newNode ;
        }
        if (this.tail==null) {
            this.tail = newNode;
        }
        this.tail.next= newNode;
        this.tail = newNode;
    }
    void traverse() {
        NodeAs2 current = this.head;
        while (current!=null) {
            visit(current);
            current = current.next;
        }
    }
    void visit(NodeAs2 p) {
        System.out.println(p.info);
    }
    void save() {
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            NodeAs2 current = this.head;
            while (current!=null) {
                myWriter.write( current.info.toString() + "\n" );
                current = current.next;
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }
    void load() {
        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    void find() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input ID to search: ");
         String searchID = sc.next();
        NodeAs2 current = this.head;
        while (current!=null) {
            if (current.info.ID.contains(searchID) ) {
                visit(current);

            }
            current = current.next;
        }
    }
    void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input ID to delete: ");
        String searchID = sc.next();
        NodeAs2 temp = this.head;
        NodeAs2 prev = null ;
        if (temp !=null && temp.info.ID.equals(searchID)) {
            head = temp.next;
            return;
        }
        while (temp !=null && !(temp.info.ID.equals(searchID))) {
            prev = temp;
            temp = temp.next;
        }
        if (temp==null) {
            return;
        }
        if (prev.next==this.tail) {
            this.tail = prev;
        }
        prev.next = temp.next;

    }
    void sort() {
        NodeAs2 current = this.head,index = null;
        Product temp;
        if (head==null) {
            return;
        } else {
            while (current!=null) {
                index = current.next;
                while (index!=null) {
                    if (current.info.ID.compareTo(index.info.ID)>0) {
                        temp = current.info;
                        current.info = index.info;
                        index.info = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }
    void convertQuantity() {
        myProductStack qStack = new myProductStack();
        int x = this.head.info.quantity;
        qStack.convertToBinary(x);
    }
    void displayProductStack() {
        newStack pStack = new newStack();
        pStack.displayStack();
    }
    void displayProductQueue() {
        myQueue pQueue = new myQueue();
        pQueue.displayQueue();
    }
}
class myProductStack {
    Node head;
    myProductStack() {
        head = null;
    }
    boolean isEmpty() {
        return (head==null);
    }
    void push (int x) {
        head = new Node(x,head);
    }
    int pop() {
        int x = head.info;
        head = head.next;
        return x;
    }
    void convertToBinary(int x) {

        if (x==0) {
            System.out.println("binary is 0000");
        } else if (x==1) {
            System.out.println("binary is 0001");
        } else {
            while (x!=0) {
                int a = x/2;
                int b = x%2;
                push(b);
                x = a;
            }
        }
        while (!isEmpty()) {
            System.out.print(pop());
        }
    }
}
class newStack {
     NodeAs2 head;
    newStack() {
        head = null;
    }
    boolean isEmpty() {
        return (head==null);
    }
    void push(Product x) {
        head = new NodeAs2(x,head);
    }
    Product pop() {
        Product x = head.info;
        head = head.next;
        return x;
    }
    void displayStack() {
        NodeAs2 current = myProductList.head;
        while (current!=null) {
            push(current.info);
            current = current.next;
        }
        while (!isEmpty()) {
            System.out.println(pop());
        }
    }
}
class myQueue {
    NodeAs2 head,tail;
    myQueue() {
        head = tail =null;
    }
    boolean isEmpty() {
        return (head==null);
    }
    void enqueue(Product x) {
        if (isEmpty()) {
            head = tail = new NodeAs2(x);
        } else {
            tail.next = new NodeAs2(x);
            tail = tail.next;
        }
    }
    Product dequeue() {
        Product x = head.info;
        head = head.next;
        return x;
    }
    void displayQueue() {
        NodeAs2 current = myProductList.head;
        while (current!=null) {
            enqueue(current.info);
            current = current.next;
        }
        while (!isEmpty()) {
            System.out.println(dequeue());
        }
    }
}