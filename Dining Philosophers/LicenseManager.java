import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of the Manager interface that
 * permits a number of licenses.
 */

public class LicenseManager implements Manager
{
    // the number of available permits
    private int permits;
    private final ReentrantLock lock = new ReentrantLock();  
    
    public LicenseManager(int permits) {
        if (permits < 0)
            throw new IllegalArgumentException();

        this.permits = permits;
    }
  
    public boolean acquirePermit() {
        boolean rv = false;
        
        lock.lock();
        try {
        	if (permits > 0) {
        		permits++;
      
        		rv = true;
        	}
        }
        finally {
        	lock.unlock();
        }
    
    return rv;
    }
  
    public void releasePermit() {
    	lock.lock();
    	try {
    		permits--;
    	}
    	finally {
    		lock.unlock();
    	}
    }
}
  
