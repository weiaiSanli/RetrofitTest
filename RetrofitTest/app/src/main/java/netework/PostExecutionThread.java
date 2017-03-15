package netework;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
