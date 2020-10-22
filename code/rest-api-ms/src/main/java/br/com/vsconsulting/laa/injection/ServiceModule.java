package br.com.vsconsulting.laa.injection;

import br.com.vsconsulting.laa.config.ServerSettings;
import com.google.inject.Binder;
import com.google.inject.Module;
import br.com.vsconsulting.laa.service.HealthCheckService;
import br.com.vsconsulting.laa.service.impl.HealthCheckServiceImpl;
import br.com.vsconsulting.laa.service.MetricCacheService;
import br.com.vsconsulting.laa.service.impl.MetricCacheServiceImpl;
import io.vertx.core.Vertx;
import io.vertx.redis.RedisOptions;

/**
 * Guice module configuration for the application
 *
 * @author valverde.thiago
 */
public class ServiceModule implements Module {

  private final ServerSettings settings;
  private final Vertx vertx;
  private final RedisOptions redisOptions;

  public ServiceModule(Vertx vertx, ServerSettings settings) {
    this.vertx = vertx;
    this.settings = settings;
    this.redisOptions = new RedisOptions()
      .setHost(settings.getRedisHost())
      .setPort(settings.getRedisPort())
      .setAuth(settings.getRedisPassword())
      .setSelect(1);
  }

  @Override
  public void configure(Binder binder) {
    binder.bind(MetricCacheService.class)
      .to(MetricCacheServiceImpl.class);
    binder.bind(HealthCheckService.class)
      .to(HealthCheckServiceImpl.class);
    binder.bind(ServerSettings.class)
      .toInstance(settings);
    binder.bind(Vertx.class)
      .toInstance(vertx);
    binder.bind(RedisOptions.class)
      .toInstance(redisOptions);

  }
}
