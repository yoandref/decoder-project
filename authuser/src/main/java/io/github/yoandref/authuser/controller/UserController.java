package io.github.yoandref.authuser.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.yoandref.authuser.dto.UserDTO;
import io.github.yoandref.authuser.models.UserModel;
import io.github.yoandref.authuser.service.UserService;
import io.github.yoandref.authuser.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserSpec spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                       Pageable pageable) {
        Page<UserModel> userModelPage = userService.findAll(pageable, spec);
        if(!userModelPage.isEmpty()) {
            for(UserModel user : userModelPage.toList()) {
                user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOneUser(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            return ResponseEntity.ok().body(userModelOptional.get());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteOneUser(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            userService.deleteById(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateOneUser(@PathVariable(value = "userId") UUID userId,
                                           @RequestBody @Validated(UserDTO.UserView.UserPut.class)
                                           @JsonView(UserDTO.UserView.UserPut.class) UserDTO userDTO) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            var userModel = userModelOptional.get();
            userModel.setFullName(userDTO.getFullName());
            userModel.setPhoneNumber(userDTO.getPhoneNumber());
            userModel.setCpf(userDTO.getCpf());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return ResponseEntity.ok().body(userModel);
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<?> updatePasswordUser(@PathVariable(value = "userId") UUID userId,
                                                @RequestBody @Validated(UserDTO.UserView.PasswordPut.class)
                                                @JsonView(UserDTO.UserView.PasswordPut.class) UserDTO userDTO) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } if(!userModelOptional.get().getPassword().equals(userDTO.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
        } else {
            var userModel = userModelOptional.get();
            userModel.setPassword(userDTO.getPassword());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return ResponseEntity.ok().body("User password updated successfully!");
        }
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<?> updateImageUser(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody @Validated(UserDTO.UserView.ImagePut.class)
                                             @JsonView(UserDTO.UserView.ImagePut.class) UserDTO userDTO) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            var userModel = userModelOptional.get();
            userModel.setImageUrl(userDTO.getImageUrl());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return ResponseEntity.ok().body(userModel);
        }
    }

}
