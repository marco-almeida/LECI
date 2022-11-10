import p2utils.HashTable;
import p2utils.Queue;

public class SupermarketOrdering {
    private int numOrders = 0;
    private HashTable<String> table = new HashTable<String>(50);
    private Queue<Order> queue = new Queue<>();

    public int numOrders(){
        return numOrders;
    }

    public void enterOrder(Order order){
        if (table.contains(order.prodName)){
            int x = Integer.parseInt(table.get(order.prodName));
            table.set(order.prodName, String.valueOf(x + order.quantity));
        } else {
            table.set(order.prodName, String.valueOf(order.quantity));
        }
        queue.in(order);
        numOrders++;
    }

    public Order serveOrder(){
        String client = queue.peek().clientName;
        String produto = queue.peek().prodName;
        int quant = queue.peek().quantity;
        Order result = new Order(client, produto, quant);
        String existing = table.get(queue.peek().prodName);
        int newValue = Integer.parseInt(existing)- queue.peek().quantity;

        table.set(queue.peek().prodName, String.valueOf(newValue));
        queue.out();
        numOrders--;
        return result;
    }

    private int query(String product){
        return Integer.parseInt(table.get(product));
    }

    public void displayOrders(){
        String result = "List of orders: [";
        Queue<Order> queueTemp = new Queue<>();

        for (int i = 0; i < numOrders; i++){
            result +=" "+ queue.peek().toString()+" ";
            queueTemp.in(queue.peek());
            queue.out();
        }
        
        for (int i = 0; i < numOrders; i++){
            queue.in(queueTemp.peek());
            queueTemp.out();
        }
        String result2 = "Summary by product: ";
        for (String a : table.keys()){
            if(query(a) == 0){
                continue;
            }
            result2 += a + "(" + String.valueOf(query(a))+")"+" ";
        }
        System.out.println(result+"]");
        System.out.println(result2);
    }
}
