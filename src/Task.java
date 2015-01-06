
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
/*TODO:
- Can add a return value to indicate error or success
- 
*/

/**
 *
 * @author puneet
 */
public class Task implements Serializable, Comparable<Task> {
    private String title;
    private String desc;
    int priority;
    Date creationTime;
    Date expirationTime;
    boolean completed = false;
    
    public String getTitle()
    {
        return title;
    }
    public String getDesc()
    {
        return desc;
    }
    public int getPriority()
    {
        return priority;
    }
    public Date getCTime()
    {
        return creationTime;
    }
    public Date getETime()
    {
        return expirationTime;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setDesc(String desc)
    {
        this.desc=desc;
    }
    public void setPriority(int priority)
    {
        this.priority=priority;
    }
    public void setETime(Date eTime)
    {
        this.expirationTime= eTime;
    }
    
    
    Task(String Title, String Desc, Date eTime, int Priority)
    {
        this.title = Title;
        this.desc = Desc;
        this.priority = Priority;
        creationTime = new Date();
        expirationTime = eTime;
    }
    
    
    Task(String Title, String Desc, Date eTime)
    {
        this.title = Title;
        this.desc = Desc;
        creationTime = new Date();
        expirationTime = eTime;
    }
    
    Task(String Title, String Desc)
    {
        this.title = Title;
        this.desc = Desc;
        creationTime = new Date();
        expirationTime = null;
    }
    
    Task(String Title, String Desc, int Priority)
    {
        this.title = Title;
        this.desc = Desc;
        this.priority = Priority;
        creationTime = new Date();
        expirationTime = null;
    }
    
    public static PriorityQueue<Task> readAllTasks() throws IOException, ClassNotFoundException
    {
        PriorityQueue<Task> Tasks = new PriorityQueue<Task>();
        System.out.println("[INFO]  Reading all Tasks");
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tasks"));
            int N = inputStream.readInt();
            System.out.println("[INFO]  Total "+N+"Tasks");

            for(int i=0;i<N;i++)
            {
                Task t = (Task) inputStream.readObject();
                Tasks.add(t);
            }
            inputStream.close();
            System.out.println("[INFO]  Successfully read all Tasks");
            return Tasks;
        }catch(FileNotFoundException e){
            System.out.println("File not found.. Creating a new empty file ");
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks"));
            outputStream.writeInt(0);
            outputStream.flush();
            outputStream.close();
            PriorityQueue<Task> t = new PriorityQueue<Task>();
            return t;
        }
    }
    
    public static void writeTask( Task t) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        PriorityQueue<Task> Tasks;
        try{
        Tasks = readAllTasks();
        }catch(FileNotFoundException e){
            System.out.println("[INFO]  file not found ... creating a new one");
            Tasks = new PriorityQueue<Task>();
        }
        Tasks.add(t);
        int N = Tasks.size();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks"));
        outputStream.writeInt(N);
        for(int i=0;i<N;i++)
        {
            outputStream.writeObject(Tasks.poll());
        }
        outputStream.flush();
        outputStream.close();
        
    }

    @Override
    public int compareTo(Task t2) {
        return priority - t2.getPriority();
    }
}
