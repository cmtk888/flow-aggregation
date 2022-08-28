package com.colemantung.app;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        System.out.println( "Hello Javalin!" );
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/flows", ctx -> {
            ctx.result("Hello Flows:" + ctx.queryParam("hour"));
            AggregatedFlow aggregatedFlow = AggregatedFlow.getInstance();
            ctx.result(aggregatedFlow.printHour(Integer.valueOf(ctx.queryParam("hour"))));
        });

        app.post("/flows", ctx -> {
            FlowRequest[] flowRequests = ctx.bodyAsClass(FlowRequest[].class);
            FlowData flowData = FlowData.getInstance();
            String result = "POST request: \n";
            for (FlowRequest fr : flowRequests) {
                flowData.addRecord(fr);
                result += fr.toString() + "\n";
            }
            ctx.result(result);
        });
    }
}

