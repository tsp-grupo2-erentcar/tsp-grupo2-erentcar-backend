package com.api.rentcar.users.api;

import com.api.rentcar.users.domain.service.ClientService;
import com.api.rentcar.users.mapping.ClientMapper;
import com.api.rentcar.users.resource.ClientResource;
import com.api.rentcar.users.resource.CreateClientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper mapper;

    public ClientController(ClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ClientResource> getAllClients(Pageable pageable){
        return mapper.modelListToPage(clientService.getAll(),pageable);
    }

    @GetMapping("{clientId}")
    public ClientResource getClientById(@PathVariable Long clientId){
        return  mapper.toResource(clientService.getById(clientId));
    }

    @PostMapping
    public ClientResource createClient(@Valid @RequestBody CreateClientResource request){
        return mapper.toResource(clientService.create(mapper.toModel(request)));
    }

    @GetMapping("email/{email}/password/{password}")
    public ClientResource getClientById(@PathVariable("email")String email,
                                        @PathVariable("password")String password){
        return  mapper.toResource(clientService.AuthClient(email,password));
    }
}
