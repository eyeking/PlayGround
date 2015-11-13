package com.cosmicdust.dwkan.resources;

import com.codahale.metrics.annotation.Timed;
import com.cosmicdust.dwkan.api.Saying;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by nshah on 10/22/2015.
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    public static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("id/{id}")
    @Timed
    public Saying sayHello(@PathParam("id") UUID id) {
        LOG.debug(" !@#$%^&*() DEBUG: " + id);
        LOG.debug("UUID string representation: "+id.toString());
        return new Saying(counter.incrementAndGet(), "nayan");
    }

    @GET
    @Timed
    public Saying sayHi(@QueryParam("name") Optional<String> name) {
        LOG.debug(" !@#$%^&*() DEBUG: " + name);
        LOG.error(" !@#$%^&*() ERROR: " + name);
        LOG.info(" !@#$%^&*() INFO: " + name);
        LOG.warn(" !@#$%^&*() WARN: " + name);
        LOG.trace(" !@#$%^&*() TRACE: " + name);
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}

