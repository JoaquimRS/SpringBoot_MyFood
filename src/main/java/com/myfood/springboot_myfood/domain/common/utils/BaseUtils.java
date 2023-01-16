package com.myfood.springboot_myfood.domain.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseUtils {

    <T> List<String> inspect() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> fieldValue = new ArrayList<>();

        for (Field f : fields) {
            fieldValue.add(f.getName());
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        Gson gson = new Gson();

        for(String name : inspect()) {
            String method = "get" + Utils.capitalize(name);
            try {
                json.addProperty(
                        name,
                        String.valueOf(
                                this.getClass()
                                        .getMethod(method)
                                        .invoke(this)
                        ));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return gson.toJson(json);
    }

}
