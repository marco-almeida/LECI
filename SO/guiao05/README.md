# Designing and implementing a simple client-server application

Basicamente o guiao anterior mas com mais guidelines e usa-se o `cond_signal` em vez do cond_wait numa das funçoes mas nsei pq.

Pode-se usar o wait na mesma, desde que se dê o lock do mutex antes e unlock depois.

**`pthread_cond_signal`** restarts **one** of the threads that are waiting on the condition variable cond. If no threads are waiting on cond, nothing happens. If several threads are waiting on cond, exactly one is restarted, but it is not specified which.

**`pthread_cond_broadcast`** **restarts all the threads** that are waiting on the condition variable cond. Nothing happens if no threads are waiting on cond.

**`pthread_cond_wait`** atomically unlocks the mutex (as per pthread_unlock_mutex) and waits for the condition variable cond to be signaled. The thread execution is suspended and does not consume any CPU time until the condition variable is signaled. The mutex must be locked by the calling thread on entrance to pthread_cond_wait. Before returning to the calling thread, pthread_cond_wait re-acquires mutex (as per pthread_lock_mutex).
