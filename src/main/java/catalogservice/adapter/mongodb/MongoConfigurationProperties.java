package catalogservice.adapter.mongodb;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("mongodb")
class MongoConfigurationProperties {
  @NotBlank
  String database;
  @NotBlank
  String collection;
}
