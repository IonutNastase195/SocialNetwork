package com.example.controller.web;

import com.example.model.connection.ConnectionRequest;
import com.example.model.connection.ConnectionResponse;
import com.example.model.connection.ConnectionUpdate;
import com.example.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/6")
public class ConnectionWebController {

    private final ConnectionService connectionService;

    @GetMapping("/all")
    public String getAllConnections(Model model) {
        model.addAttribute("connections", connectionService.getAllConnections());
        return "allConnectionsPage";
    }

    @GetMapping("/create")
    public String goToCreateConnectionPage() {
        return "createConnectionPage";
    }

    @PostMapping("/create")
    public String createConnection(@ModelAttribute(value = "connectionRequest") ConnectionRequest connectionRequest, Model model) {
        ConnectionResponse connection = connectionService.createConnection(connectionRequest);
        model.addAttribute("connection", connection);
        return "connectionCreatedPage";
    }

    @GetMapping("/update")
    public String goToUpdateConnectionPage(@ModelAttribute(value = "connectionId") int connectionId, Model model) {
        model.addAttribute("connectionId", connectionId);
        return "updateConnectionPage";
    }

    @PostMapping("/update")
    public String updateConnection(@ModelAttribute(value = "connectionUpdate") ConnectionUpdate connectionUpdate, Model model) {
        ConnectionResponse connection = connectionService.updateConnectionById(connectionUpdate.getId(), connectionUpdate);
        model.addAttribute("connection", connection);
        return "connectionUpdatedPage";
    }

    @PostMapping("/delete")
    public String deleteConnection(@ModelAttribute(value = "connectionId") int connectionId, Model model) {
        connectionService.deleteConnection(connectionId);
        model.addAttribute("connections", connectionService.getAllConnections());
        return "allConnectionsPage";
    }
}
