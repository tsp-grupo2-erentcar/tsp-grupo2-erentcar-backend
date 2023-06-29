package com.acme.webserviceserentcar.notification.api;

import com.acme.webserviceserentcar.client.domain.service.ClientService;
import com.acme.webserviceserentcar.notification.domain.service.NotificationService;
import com.acme.webserviceserentcar.notification.mapping.NotificationMapper;
import com.acme.webserviceserentcar.notification.resource.CreateNotificationResource.CreateNotificationResource;
import com.acme.webserviceserentcar.notification.resource.NotificationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin
public class NotificationController {
    private final NotificationService notificationService;
    private final ClientService clientService;
    private final NotificationMapper mapper;

    public NotificationController(ClientService clientService, NotificationService notificationService, NotificationMapper mapper) {
        this.notificationService = notificationService;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Notification", description = "Get All Notification", tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Notification returned",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NotificationResource.class))
                    ))
    })
    @GetMapping
    public Page<NotificationResource> getAllFavourite(Pageable pageable) {
        return mapper.modelListToPage(notificationService.getAll(), pageable);
    }

    @Operation(summary = "Get Notification by Id", description = "Get Notification by Id", tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotificationResource.class)
                    ))
    })
    @GetMapping("{notificationId}")
    public NotificationResource getNotficaciontById(@PathVariable Long notificationId) {
        return mapper.toResource(notificationService.getById(notificationId));
    }

    @Operation(summary = "Get All Notification by Client id", description = "Get All Notification by Client id", tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Notification of Client returned",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NotificationResource.class))
                    ))
    })
    @GetMapping("client/{clientId}")
    public Page<NotificationResource> getAllNotificationByClientId(@PathVariable Long clientId, Pageable pageable) {
        return notificationService.getAllNotificationByClientId(clientId, pageable).map(mapper::toResource);
    }

    @Operation(summary = "Create Notification", description = "Create Notification", tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotificationResource.class)
                    ))
    })
    @PostMapping()
    public NotificationResource createNotification(@RequestParam(name = "clientId") Long clientId,
                                             @RequestParam(name = "carId") Long carId,
                                             @Valid @RequestBody CreateNotificationResource request) {
        return mapper.toResource(notificationService.create(clientId, carId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Notification", description = "Delete Notification", tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favourite deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotificationResource.class)
                    ))
    })
    @DeleteMapping("{favouriteId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        return notificationService.delete(notificationId);
    }
}
