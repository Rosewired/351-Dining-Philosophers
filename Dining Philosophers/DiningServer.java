/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

public interface DiningServer 
{  
   // called by a philosopher when they wish to eat 
   public void takeForks(int philNumber);
  
   // called by a philosopher when they are finished eating 
   public void returnForks(int philNumber);
}
