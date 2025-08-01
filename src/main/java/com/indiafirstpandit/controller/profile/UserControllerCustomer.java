package com.indiafirstpandit.controller.profile;


import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.requests.UserProfileEditRequest;
import com.indiafirstpandit.response.UserProfileResponse;
import com.indiafirstpandit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/customer/profile")
public class UserControllerCustomer {

    @Autowired
    private UserService userService;


    //

    @GetMapping
    public ResponseEntity<UserProfileResponse> userProfileResponse(@AuthenticationPrincipal UserPrincipal userPrincipal){
        System.out.println();
        UserProfileResponse response = userService.getUserProfile(userPrincipal.getUser());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody String reason)
    {
        userService.deleteUser(userPrincipal.getUser(),reason);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserProfileResponse> editProfile(@RequestBody UserProfileEditRequest userProfileEditRequest, @AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        UserProfileResponse response = userService.editUserProfile(userProfileEditRequest,userPrincipal.getUser());
        return ResponseEntity.ok(response);
    }

}
