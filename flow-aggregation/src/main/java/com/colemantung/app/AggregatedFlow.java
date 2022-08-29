package com.colemantung.app;

import com.colemantung.app.node.DestAppNode;
import com.colemantung.app.node.HourNode;
import com.colemantung.app.node.SrcAppNode;
import com.colemantung.app.node.VpcIdNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

// A singleton class with a HourlyMap for fast lookup
public class AggregatedFlow {
    private static AggregatedFlow instance = null;

    public Map<Integer, List<HourNode>> hourlyMap;

    private AggregatedFlow() {
        hourlyMap = new HashMap<>();
    }

    public static AggregatedFlow getInstance() {
        if (instance == null)
            instance = new AggregatedFlow();
        return instance;
    }

    public String printHour(int hour) {
        List<HourNode> nodes = hourlyMap.get(hour);

        if (nodes == null) return "[]";

        JsonArrayBuilder aggregatedFlowArrBuilder = Json.createArrayBuilder();
        for (HourNode n : nodes) {
            VpcIdNode vpcIdNode = n.getParentNode();
            DestAppNode destAppNode = vpcIdNode.getParentNode();
            SrcAppNode srcAppNode = destAppNode.getParentNode();

            aggregatedFlowArrBuilder.add(
                    Json.createObjectBuilder()
                            .add("src_app", srcAppNode.getName())
                            .add("dest_app", destAppNode.getName())
                            .add("vpc_id", vpcIdNode.getId())
                            .add("bytes_tx", n.getBytesTx())
                            .add("bytes_rx", n.getBytesRx())
                            .add("hour", n.getHour())
            );
        }
        return aggregatedFlowArrBuilder.build().toString();
    }
}