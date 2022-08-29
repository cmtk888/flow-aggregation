package com.colemantung.app.node;

import com.colemantung.app.FlowRequest;

import java.util.HashMap;
import java.util.Map;

public class VpcIdNode {
    String id;
    Map<Integer, HourNode> hours;
    // Parent Node
    DestAppNode parentNode;

    public VpcIdNode(FlowRequest request, DestAppNode parentNode) {
        this.id = request.getVpcId();
        this.parentNode = parentNode;
        hours = new HashMap<>();
        hours.put(request.getHour(), new HourNode(request, this));
    }

    public void update(FlowRequest request) {
        HourNode node = hours.get(request.getHour());
        if (node == null) {
            hours.put(request.getHour(), new HourNode(request, this));
        } else {
            node.update(request);
        }
    }

    public String getId() {
        return id;
    }

    public DestAppNode getParentNode() {
        return parentNode;
    }
}
