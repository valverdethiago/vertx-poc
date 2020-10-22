package br.com.vsconsulting.laa.service;

import io.vertx.ext.healthchecks.HealthCheckHandler;

public interface HealthCheckService {

  HealthCheckHandler createHealthCheckHandler();

}
