/**
 * TestIt.java
 *
 * This program creates the threads that are used to demonstrate
 * the difference between Java's notify() and condition variables.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */

public class TestIt
{
   public static final int NUMBER_OF_THREADS = 5;

   public static void main(String args[]) {
      //DoWork pile = new DoWorkNotify();

      // Change to this line to see it work correctly
      DoWorkCV pile = new DoWorkCV();

      Thread[] bees = new Thread[NUMBER_OF_THREADS];

      for (int i = 0; i < NUMBER_OF_THREADS; i++)
         bees[i] = new Thread(new Worker(pile, "Worker " + (new Integer(i)).toString(), i) );

      for (int i = 0; i < NUMBER_OF_THREADS; i++)
         bees[i].start();
   }
}

