
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        // TODO 
        int num;
        PriorityQueue<Task> tasks = new PriorityQueue<Task>();
        tasks = Task.readAllTasks();
        while(true) 
        {
            if(args.length>0)
            {
                num = Integer.parseInt(args[0]);
            }else{
                System.out.println("Track Your Tasks :-)");
                System.out.println("1. List All Tasks");
                System.out.println("2. Add A Task");
                System.out.println("3. Exit");
                Scanner in = new Scanner(System.in);
                num = in.nextInt();
            }
            switch(num)
            {
                case 1: ListTasks(tasks);
                    break;
                
                case 2: tasks.add(addTask());
                    break;
                case 3: 
                    Task.saveTasks(tasks);
                    System.exit(0);
            }
        }
    }
    
    static public void ListTasks(PriorityQueue<Task> tasks) throws IOException, ClassNotFoundException
    {
        
        PriorityQueue<Task> Tasks = tasks;
        if(Tasks!=null)
        {
        int N= Tasks.size();
        System.out.println("[INFO]  Total Tasks : "+N);
        System.out.println("[INFO]  Listing all Tasks");
        for(int i=0;i<N;i++)
        {
            Task t = Tasks.poll();
            System.out.println("\nTask No. : "+(i+1));
            System.out.println("Title : "+t.getTitle());
            System.out.println("Desc : "+t.getDesc());
            System.out.println("Creation Time : "+t.getCTime());
            System.out.println("Expiration Time : "+t.getETime());
            System.out.println("Priority Level : "+t.getPriority());
        }
        System.out.println("\n\nChoose a Task By pressing its Number...");
        System.out.println("OR you can go back by pressing #");
        }
        
    }
    public static Task addTask() throws ParseException, IOException, FileNotFoundException, ClassNotFoundException
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
        
        return tsk;
    }

}
