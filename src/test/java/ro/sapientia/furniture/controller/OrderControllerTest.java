package ro.sapientia.furniture.controller;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = FurnitureController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class OrderControllerTest {

}
