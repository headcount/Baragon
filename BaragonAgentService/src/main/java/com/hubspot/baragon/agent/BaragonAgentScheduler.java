package com.hubspot.baragon.agent;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hubspot.baragon.agent.poller.PollerRunnable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class BaragonAgentScheduler {
  private static final Log LOG = LogFactory.getLog(BaragonAgentScheduler.class);

  private final ScheduledExecutorService scheduledExecutorService;
  private final int pollInterval;
  private final PollerRunnable pollerRunnable;

  @Inject
  public BaragonAgentScheduler(ScheduledExecutorService scheduledExecutorService,
                               @Named(BaragonAgentServiceModule.UPSTREAM_POLL_INTERVAL_PROPERTY) int pollInterval,
                               PollerRunnable pollerRunnable) {
    this.scheduledExecutorService = scheduledExecutorService;
    this.pollInterval = pollInterval;
    this.pollerRunnable = pollerRunnable;
  }

  public void start() {
    LOG.info(String.format("Starting upstream poller (%sms interval)...", pollInterval));
    scheduledExecutorService.scheduleAtFixedRate(pollerRunnable, pollInterval, pollInterval, TimeUnit.MILLISECONDS);
  }

  public void stop() {
    try {
      LOG.info("Stopping upstream poller...");
      scheduledExecutorService.shutdownNow();
      scheduledExecutorService.awaitTermination(1, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }
}
