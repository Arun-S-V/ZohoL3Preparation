import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum LogicLayer {
    INSTANCE;
    HashMap<Integer,Customer> customerMap = new HashMap<>();
    HashMap<Integer,Item> ItemMap = new HashMap<>();
    HashMap<Integer,ArrayList<Item>> invoiceMap = new HashMap<>();
    public void fillItems(){
        Item item = new Item();
        item.setItemName("HeadSet");
        item.setItemCost(900);
        ItemMap.put(1,item);
        item = new Item();
        item.setItemName("SmartPhone");
        item.setItemCost(7000);
        ItemMap.put(2,item);
        item = new Item();
        item.setItemName("Watch");
        item.setItemCost(750);
        ItemMap.put(3,item);
        item = new Item();
        item.setItemName("TeleVision");
        item.setItemCost(8000);
        ItemMap.put(4,item);
    }
    public void addCustomer(String name,String city,long mobileNo,int cus_id){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(city);
        customer.setMobileNumber(mobileNo);
        customer.setCustomerId(cus_id);
        customerMap.put(cus_id,customer);
        System.out.println(customerMap);
    }
    public HashMap<Integer,Customer> viewAllCustomer(){
        return customerMap;
    }
    public HashMap<Integer,ArrayList<Item>> viewAllInvoices(){
        return invoiceMap;
    }
    public boolean customerCheck(int cus_id){
        if(customerMap.containsKey(cus_id)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean invoiceNoCheck(int invoice_no){
        if(invoiceMap.containsKey(invoice_no)){
            return true;
        }
        else{
            return false;
        }
    }
    public void addItem(int num,int cus_Id,int invoiceNo){
        Item item = ItemMap.get(num);
        item.setCus_Id(cus_Id);
        ArrayList<Item> tempList  = invoiceMap.getOrDefault(invoiceNo,new ArrayList<Item>());
        tempList.add(item);
        invoiceMap.put(invoiceNo,tempList);
        System.out.println(invoiceMap);
    }
    public void addItem(int num,int invoice_no){
        Item item = ItemMap.get(num);
        ArrayList<Item> tempList=  invoiceMap.get(invoice_no);
        Item temp = tempList.get(0);
        int cus_id = temp.getCus_Id();
        item.setCus_Id(cus_id);
        tempList  = invoiceMap.getOrDefault(invoice_no,new ArrayList<Item>());
        tempList.add(item);
        invoiceMap.put(invoice_no,tempList);
        System.out.println(invoiceMap);
    }
    public String  customerInvoices(int cus_id){
        String s ="";
        for(Map.Entry<Integer,ArrayList<Item>> entry:invoiceMap.entrySet()){
            ArrayList<Item> list= entry.getValue();
            int e =1;
            for(int i=0;i<list.size();i++) {
                Item item = list.get(i);
                int id = item.getCus_Id();
                if(cus_id == id){
                    s = s+e+". "+"Item Name : "+item.getItemName()+"\t"+" Item Cost : "+item.getItemCost()+"\n";
                }
                e++;
            }
        }
        return s;
    }
    public ArrayList<Item> getInvoice(int invoice_no){
        ArrayList<Item> list = invoiceMap.get(invoice_no);
        return list;
    }
}
