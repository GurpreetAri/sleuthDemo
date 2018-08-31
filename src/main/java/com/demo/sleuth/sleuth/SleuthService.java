package com.demo.sleuth.sleuth;

import brave.Span;
import brave.Tracer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SleuthService {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    //TODO - Manual span
    @Autowired
    @Lazy
    Tracer tracer;


    public String existingSpan() {
        LOGGER.info("Using the same span");
        return "existingSpan - Success";
    }

    public String newSpan() {
        String responseVal = null;
        LOGGER.info("New Span generation");

//        this.tracer.currentSpan().context().parentId();
//        this.tracer.currentSpan().context().traceId();
//        this.tracer.currentSpan().context().spanId();
//        this.tracer.currentSpan().context().sampled();

        // Start a span. If there was a span present in this thread it will become the `newSpan`'s parent.
        Span newSpan = this.tracer.nextSpan().name("newSpan");

        try (Tracer.SpanInScope ws = this.tracer.withSpanInScope(newSpan.start())) {
            // ...
            // You can tag a span
            // newSpan.tag("newSpan", taxValue);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ...
            // You can log an event on a span
            newSpan.annotate("event - new span created..");
        } finally {
            // Once done remember to finish the span. This will allow collecting
            // the span to send it to Zipkin
            newSpan.finish();
        }
        LOGGER.info("New span generated...");

        return responseVal;
    }

}
