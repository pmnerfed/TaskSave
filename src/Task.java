
import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*TODO:
- Can add a return value to indicate error or success
- 
*/

/**
 *
 * @author puneet
 */
public class Task implements Serializable {
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
    
    public static List<Task> readAllTasks() throws IOException, ClassNotFoundException
    {
        List<Task> Tasks = new ArrayList<>();
        System.out.println("[INFO]  Reading all Tasks");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tasks.txt"));
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
    }
    
    public void writeTask( Task t) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        List<Task> Tasks = readAllTasks();
        Tasks.add(t);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks.txt"));
        int N = Tasks.size();
        outputStream.writeInt(N);
        for(int i=0;i<N;i++)
        {
            outputStream.writeObject(Tasks.get(i));
        }
        outputStream.flush();
        outputStream.close();
        
    }
}
