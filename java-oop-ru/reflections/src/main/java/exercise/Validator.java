package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Object obj) {

        List<String> result = new ArrayList<>();

        Class<?> aClass = obj.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    var fieldValue = field.get(obj);
                    if (fieldValue == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {

        Map<String, List<String>> result = new HashMap<>();

        Class<?> aClass = obj.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            NotNull annotation1 = field.getAnnotation(NotNull.class);
            if (annotation1 != null) {
                try {
                    field.setAccessible(true);
                    var fieldValue1 = field.get(obj);
                    if (fieldValue1 == null) {
                        var value = result.getOrDefault(field.getName(), new ArrayList<String>());
                        value.add("can not be null");
                        result.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            MinLength annotation2 = field.getAnnotation(MinLength.class);
            if (annotation2 != null) {
                try {
                    field.setAccessible(true);
                    var fieldValue2 = field.get(obj);
                    var minLength = annotation2.minLength();
                    if (fieldValue2 != null && fieldValue2.toString().length() < minLength) {
                        var value = result.getOrDefault(field.getName(), new ArrayList<String>());
                        value.add("length less than " + minLength);
                        result.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
// END
