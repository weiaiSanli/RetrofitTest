package netework;


import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
