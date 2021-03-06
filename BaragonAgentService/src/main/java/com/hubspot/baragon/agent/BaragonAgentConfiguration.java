package com.hubspot.baragon.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubspot.baragon.config.LoadBalancerConfiguration;
import com.hubspot.baragon.config.TemplateConfiguration;
import com.hubspot.baragon.config.ZooKeeperConfiguration;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaragonAgentConfiguration extends Configuration {
  @JsonProperty("zookeeper")
  @NotNull
  private ZooKeeperConfiguration zooKeeperConfiguration;

  @JsonProperty("loadBalancerConfig")
  @NotNull
  private LoadBalancerConfiguration loadBalancerConfiguration;

  @JsonProperty("upstreamPollIntervalMs")
  private int upstreamPollIntervalMs = 10000;

  @JsonProperty("templates")
  @NotNull
  private List<TemplateConfiguration> templates = Collections.emptyList();

  public ZooKeeperConfiguration getZooKeeperConfiguration() {
    return zooKeeperConfiguration;
  }

  public void setZooKeeperConfiguration(ZooKeeperConfiguration zooKeeperConfiguration) {
    this.zooKeeperConfiguration = zooKeeperConfiguration;
  }

  public LoadBalancerConfiguration getLoadBalancerConfiguration() {
    return loadBalancerConfiguration;
  }

  public void setLoadBalancerConfiguration(LoadBalancerConfiguration loadBalancerConfiguration) {
    this.loadBalancerConfiguration = loadBalancerConfiguration;
  }

  public int getUpstreamPollIntervalMs() {
    return upstreamPollIntervalMs;
  }

  public void setUpstreamPollIntervalMs(int upstreamPollIntervalMs) {
    this.upstreamPollIntervalMs = upstreamPollIntervalMs;
  }

  public List<TemplateConfiguration> getTemplates() {
    return templates;
  }

  public void setTemplates(List<TemplateConfiguration> templates) {
    this.templates = templates;
  }
}
