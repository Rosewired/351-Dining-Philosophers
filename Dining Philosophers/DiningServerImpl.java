/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	
	private Lock lock;
	private Condition[] cond;

	// different philosopher states
	enum State {THINKING, HUNGRY, EATING};
	
	// number of philosophers
	public static final int NUMBER_OF_PHILOSOPHERS = 5;
	
	// array to record each philosopher's state
	private State[] state;
	
	public DiningServerImpl()
	{
		// array of philosopher's state
		state = new State[NUMBER_OF_PHILOSOPHERS];
		
		// originally all philosopher's are thinking
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++)
			state[i] = State.THINKING;
		
		lock = new ReentrantLock();
		cond = new Condition[NUMBER_OF_PHILOSOPHERS];

		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++)
			cond[i] = lock.newCondition();
	}
	
	// called by a philosopher when they wish to eat 
	public void takeForks(int pnum)  {
		lock.lock();
		state[pnum] = State.HUNGRY;
		test(pnum);
		try {
		if (state[pnum] != State.EATING)
				cond[pnum].await();
			} catch (InterruptedException e) { }
		finally {
			lock.unlock();

		}
	}
	
	// called by a philosopher when they are finished eating 
	public void returnForks(int pnum) {
		lock.lock();
		try {
			state[pnum] = State.THINKING;
			// test left and right neighbors
			test(leftNeighbor(pnum));
			test(rightNeighbor(pnum));
		} finally {
			lock.unlock();
		}
	}
	
	private void test(int i)
	{
		// in other words ... if I'm hungry and my left and
		// right neighbors aren't eating, then let me eat!
		
		if ((state[leftNeighbor(i)] != State.EATING) && (state[i] == State.HUNGRY) && (state[rightNeighbor(i)] != State.EATING)) {
			state[i] = State.EATING;
			cond[i].signal();
		}
	}
	
	
	// return the index of the left neighbor of philosopher i
	private int leftNeighbor(int i)
	{
        return ((i + 1) % NUMBER_OF_PHILOSOPHERS);
    }
	
	// return the index of the right neighbor of philosopher i
	private int rightNeighbor(int i)
	{
        return ((i + 4) % NUMBER_OF_PHILOSOPHERS);
	}
}
