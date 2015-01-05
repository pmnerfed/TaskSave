
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author puneet
 */
public class TaskSave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        // TODO 
        while(true)
        {
            System.out.println("Track Your Tasks :-)");
            System.out.println("1. List All Tasks");
            System.out.println("2. Add A Task");
            System.out.println("3. Exit");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            switch(num)
            {
                case 1: ListTasks();
                    break;
                
                case 2: addTask();
                    break;
                case 3: System.exit(0);
            }
        }
    }
    
    static public void ListTasks() throws IOException, ClassNotFoundException
    {
        
        List<Task> tasks = Task.readAllTasks();
        int N= tasks.size();
        System.out.println("[INFO]  Total Tasks : "+N);
        System.out.println("[INFO]  Listing all Tasks");
        for(int i=0;i<N;i++)
        {
            
            System.out.println("\nTask No. : "+(i+1));
            System.out.println("Title : "+tasks.get(i).getTitle());
            System.out.println("Desc : "+tasks.get(i).getDesc());
            System.out.println("Creation Time : "+tasks.get(i).getCTime());
            System.out.println("Expiration Time : "+tasks.get(i).getETime());
            System.out.println("Priority Level : "+tasks.get(i).getPriority());
        }
        System.out.println("\n\nChoose a Task By pressing its No...");
        System.out.println("OR you can go back by pressing #");
        
        
    }
    public static void addTask() throws ParseException, IOException, FileNotFoundException, ClassNotFoundException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Creating a new task :-) ");
        
        System.out.println("Enter the Title :: ");
        String s = in.nextLine();
        System.out.println("Enter the Description :: ");
        String d = in.nextLine();
        System.out.println("Enter expiry date  Example: 09/12/2013 11:00:00 PM [Default : NONE] :: ");
        String datetime = in.nextLine();
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        Date d1;
        if(!"".equals(datetime)){
             d1= format.parse(datetime);
        }
        else{
            d1=null;
        }
        System.out.println("Enter priority [Default : 1] :: ");
        int pr = in.nextInt();
        Task tsk;
        if("".equals(datetime)){
            tsk= new Task(s, d, pr);
        }else{
            tsk = new Task(s, d, d1, pr);
        }
        
        Task.writeTask(tsk);
    }
    
}
