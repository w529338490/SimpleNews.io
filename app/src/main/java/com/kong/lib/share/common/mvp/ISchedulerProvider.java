package com.kong.lib.share.common.mvp;

import rx.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface ISchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();

    Scheduler newThread();
}
