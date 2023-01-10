package ro.sapientia.furniture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.service.UserBodyService;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserBodyService userBodyService;

    @GetMapping("/all")
    public ResponseEntity<List<UserBody>> getAllFurnitureBodies(){
        final List<UserBody> furnitureBodies = userBodyService.findAllUserBodies();
        return new ResponseEntity<>(furnitureBodies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserBody> getFurnitureBodyById(@PathVariable("id") Long id){
        final UserBody furnitureBody = userBodyService.findUserBodyById(id);
        return new ResponseEntity<>(furnitureBody,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserBody> addFurnitureBody(@RequestBody UserBody userBody){
        final UserBody persistenFurnitureBody = userBodyService.create(userBody);
        return new ResponseEntity<>(persistenFurnitureBody,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserBody> updateUserBody(@RequestBody UserBody userBody){
        final UserBody persistenUserBody = userBodyService.update(userBody);
        return new ResponseEntity<>(persistenUserBody,HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteUserBodyById(@PathVariable("id") Long id){
        userBodyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

