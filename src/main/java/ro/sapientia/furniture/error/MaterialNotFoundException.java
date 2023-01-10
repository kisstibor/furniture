package ro.sapientia.furniture.error;


import javassist.NotFoundException;

public class MaterialNotFoundException extends NotFoundException {
    public MaterialNotFoundException(String msg) {
        super(msg);
    }
}
