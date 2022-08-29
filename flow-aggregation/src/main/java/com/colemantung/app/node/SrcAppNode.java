package com.colemantung.app.node;

import com.colemantung.app.FlowRequest;
import com.colemantung.app.node.DestAppNode;

import java.util.HashMap;
import java.util.Map;

public class SrcAppNode {
    String name;
    Map<String, DestAppNode> destApps;

    public SrcAppNode(FlowRequest request) {
        this.name = request.getSrcApp();
        destApps = new HashMap<>();
        destApps.put(request.getDestApp(), new DestAppNode(request, this));
    }

    public void update(FlowRequest request) {
        DestAppNode node = destApps.get(request.getDestApp());
        if (node == null) {
            destApps.put(request.getDestApp(), new DestAppNode(request, this));
        } else {
            node.update(request);
        }
    }

    public String getName() {
        return name;
    }
}
