package readinglist.config;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
//import org.springframework.boot.actuate.endpoint.PublicMetrics;
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.boot.actuate.metrics.CounterService;
//import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
//import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
//import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import readinglist.repository.ReadingListRepository;

@Slf4j
@Configuration
public class ActuatorConfig { 

	@Autowired
	ReadingListRepository readingListRepository;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	MeterRegistry mesterRegistry;
	
	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
	
	@Bean
	public HealthIndicator dBHealthCheck(){
		return new HealthIndicator() {
			
			@Override
			public Health health() {
				try {
					int size = readingListRepository.findAll().size();
					log.info("DBHealthCheck Config: {}", size);
					return Health.up().build();
				} catch (Exception e) {
					e.printStackTrace();
					return Health.down().withDetail("Error code can not access DB instance", 500).build();
				}
			}
		};
	}
	
//	@Bean
	public MeterRegistry meterRegistry() {
//		this.mesterRegistry.
		return this.mesterRegistry;
//		return new PublicMetrics() {
//			@Override
//			public Collection<Metric<?>> metrics() {
//				List<Metric<?>> metrics = new ArrayList<Metric<?>>();
//				
//				metrics.add(new Metric<Long>("ApplicationContext.startup.time", context.getStartupDate()));
//				metrics.add(new Metric<Integer>("ApplicationContext.bean.count", context.getBeanDefinitionCount()));
//				metrics.add(new Metric<Integer>("ApplicationContext.bean.names", context.getBeanDefinitionNames().length));
//				metrics.add(new Metric<Integer>("ApplicationContext.Controller", context.getBeanNamesForType(Controller.class).length));
//				metrics.add(new Metric<Integer>("ApplicationContext.beans", context.getBeanNamesForType(Object.class).length));
//				
//				return metrics;
//			}
//		};
	}
}
