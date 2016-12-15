package com.github.adrienpessu.quickies.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.io.BaseEncoding;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.UnsupportedEncodingException;

public class QuickieConfiguration extends Configuration {

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @NotEmpty
    private String jwtTokenSecret;

    @NotEmpty
    private String jwtAudience;

    @NotEmpty
    private String mongoHost;

    @NotEmpty
    private String mongoPort;

    @NotEmpty
    private String mongoDB;

    @JsonProperty
    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return BaseEncoding.base64Url().omitPadding().decode(jwtTokenSecret);
    }

    @JsonProperty
    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    @JsonProperty
    public String getJwtAudience() {
        return jwtAudience;
    }

    @JsonProperty
    public void setJwtAudience(String authZeroAudience) {
        this.jwtAudience = authZeroAudience;
    }

    public String getMongoHost() {
        return mongoHost;
    }

    @JsonProperty
    public void setMongoHost(final String mongoHost) {
        this.mongoHost = mongoHost;
    }

    @JsonProperty
    public String getMongoPort() {
        return mongoPort;
    }
    @JsonProperty
    public Integer getMongoPortAsInt() {
        return Integer.valueOf(mongoPort);
    }

    @JsonProperty
    public void setMongoPort(final String mongoPort) {
        this.mongoPort = mongoPort;
    }

    @JsonProperty
    public String getMongoDB() {
        return mongoDB;
    }

    @JsonProperty
    public void setMongoDB(final String mongoDB) {
        this.mongoDB = mongoDB;
    }

}
