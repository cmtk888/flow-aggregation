package com.colemantung.app;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        System.out.println( "Starting the server at port 7070 ....." );
        Javalin app = Javalin.create().start(7070);

        app.get("/", ctx -> ctx.result("Hello World"));

        app.get("/flows", ctx -> {
            AggregatedFlow aggregatedFlow = AggregatedFlow.getInstance();
            ctx.result(aggregatedFlow.printHour(Integer.valueOf(ctx.queryParam("hour"))));
        });

        app.post("/flows", ctx -> {
            FlowRequest[] flowRequests = ctx.bodyAsClass(FlowRequest[].class);
            FlowDataRoot flowDataRoot = FlowDataRoot.getInstance();
            String result = "";
            for (FlowRequest fr : flowRequests) {
                flowDataRoot.addRecord(fr);
                result += fr.toString();
            }
            ctx.result(result);
        });
    }
}

