import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        LogicLayer.INSTANCE.fillItems();
        int customerId = 0;
        int invoiceNo = 0;
        boolean repeat = true;
        while (repeat) {
            System.out.println("_____________INVOICE SYSTEM______________");
            System.out.println("1.Add Customer");
            System.out.println("2.Add Invoice");
            System.out.println("3.Add Item to Exiting Invoice Numbers");
            System.out.println("4.List all Customers");
            System.out.println("5.List all Invoices");
            System.out.println("6.List all invoices of a Customer");
            System.out.println("7.Display an invoice");
            System.out.println("8.Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            if (choice < 1 || choice > 8)
                System.out.println("Please enter correct option!!!");
            else if(choice == 1){
                System.out.println("Enter your Name : ");
                String name = scan.nextLine();
                System.out.println("Enter your City: ");
                String city = scan.nextLine();
                System.out.println("Enter your Mobile Number : ");
                long mobileNo =  scan.nextLong();
                LogicLayer.INSTANCE.addCustomer(name,city,mobileNo,customerId);
                System.out.println("Your Customer Id is :"+customerId);
                customerId++;
            }
            else if(choice==2)
            {
                System.out.println("Enter Customer id:");
                int cus_Id = scan.nextInt();
                int k = 1;
                while(k == 1) {
                    if (LogicLayer.INSTANCE.customerCheck(cus_Id)) {
                        System.out.println("1.HeadSet = Rs.900");
                        System.out.println("2.SmartPhone = Rs.7000");
                        System.out.println("3.Watch = Rs.750");
                        System.out.println("4.TeleVision = Rs.8000");
                        System.out.println("Enter your Choice :");
                        int choice3 = scan.nextInt();
                        LogicLayer.INSTANCE.addItem(choice3, cus_Id, invoiceNo);
                        System.out.println("Your Invoice Number is : " + invoiceNo);
                       // invoiceNo++;
                    }
                    else{
                        System.out.println("Your Customer Id is not Exits");
                    }
                    System.out.println("Are you want to add one more item yes 1 or no 0");
                    k=scan.nextInt();
                }
                invoiceNo++;
            }
            else if(choice == 3) {
                System.out.println("Enter Invoice Number :");
                int invoice_no = scan.nextInt();
                int m = 1;
                while (m == 1) {
                    if (LogicLayer.INSTANCE.invoiceNoCheck(invoice_no)) {
                        System.out.println("1.HeadSet = Rs.900");
                        System.out.println("2.SmartPhone = Rs.7000");
                        System.out.println("3.Watch = Rs.750");
                        System.out.println("4.TeleVision = Rs.8000");
                        System.out.println("Enter your Choice :");
                        int choice4 = scan.nextInt();
                        LogicLayer.INSTANCE.addItem(choice4, invoice_no);
                    }
                    else{
                        System.out.println("Your Invoice Number is not Exits");
                    }
                    System.out.println("Are you want to add one more item yes 1 or no 0");
                    m=scan.nextInt();
                }
            }
            else if(choice == 4){
                HashMap<Integer,Customer> customerMap= LogicLayer.INSTANCE.viewAllCustomer();
                for (Integer s : customerMap.keySet())
                    System.out.println("Customer ID : "+s+"\t"+"Name : "+customerMap.get(s).getName()+"\t"+"City : "+customerMap.get(s).getAddress()+"\t"+"Mobile Number : "+customerMap.get(s).getMobileNumber());
            }
            else if(choice == 5){
                long amount = 0;
                HashMap<Integer, ArrayList<Item>> invoiceMap= LogicLayer.INSTANCE.viewAllInvoices();
                for (Integer s : invoiceMap.keySet()){
                    ArrayList<Item> list = invoiceMap.get(s);
                    int len = list.size();
                    System.out.println("Invoice Number : " + s + " ");
                    for(int l =0;l<len;l++) {
                        System.out.println((l+1)+". "+"Item Name : " + invoiceMap.get(s).get(l).getItemName() + "\t\t" + "Cost : " + invoiceMap.get(s).get(l).getItemCost());
                        amount = amount +  invoiceMap.get(s).get(l).getItemCost();
                    }
                    System.out.println("Total Amount : "+amount);
                    amount = 0;
                }
            }
            else if(choice == 6){
                System.out.println("Enter Customer ID : ");
                int cus_Id = scan.nextInt();
                String s = LogicLayer.INSTANCE.customerInvoices(cus_Id);
                System.out.println(s);
            }
            else if(choice == 7){
                long amount = 0;
                System.out.println("Enter Invoice Number :");
                int invoice_no = scan.nextInt();
                ArrayList<Item> list= LogicLayer.INSTANCE.getInvoice(invoice_no);
                for(int i =0;i< list.size();i++) {
                    System.out.println((i+1)+". "+"Item Name : "+list.get(i).getItemName()+"\t"+"Cost : "+list.get(i).getItemCost());
                    amount = amount +  list.get(i).getItemCost();
                }
                System.out.println("Total Amount : "+amount);
                amount = 0;
            }
            else if(choice == 8){
                System.out.println("Thank you");
                repeat = false;
            }
        }
    }
}