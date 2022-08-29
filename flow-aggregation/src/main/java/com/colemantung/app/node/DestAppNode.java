package com.colemantung.app.node;

import com.colemantung.app.FlowRequest;

import java.util.HashMap;
import java.util.Map;

public class DestAppNode {
    String name;
    Map<String, VpcIdNode> vpcIds;
    // Parent Node
    SrcAppNode parentNode;

    public DestAppNode(FlowRequest request, SrcAppNode parentNode) {
        this.name = request.getDestApp();
        this.parentNode = parentNode;
        vpcIds = new HashMap<>();
        vpcIds.put(request.getVpcId(), new VpcIdNode(request, this));
    }

    public void update(FlowRequest request) {
        VpcIdNode node = vpcIds.get(request.getVpcId());
        if (node == null) {
            vpcIds.put(request.getVpcId(), new VpcIdNode(request, this));
        } else {
            node.update(request);
        }
    }

    public SrcAppNode getParentNode() {
        return parentNode;
    }

    public String getName() {
        return name;
    }
}
