package testTools;

import java.lang.reflect.Field;

import static org.junit.Assert.fail;

public class Reflection {

    /**
     * Set value of a private attribute.
     * If any error on type, field, etc, it will crash tests
     * @param object the object you want to change a field value
     * @param fieldName its field name you want to change
     * @param fieldValue the desired value
     */
    public static void setFieldByReflection(Object object, String fieldName, Object fieldValue){
        Class<?> _class = object.getClass();
        try{
            Field field = _class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    // TODO tmp, find why objectInstance.getClass doesn't work sometimes
    // TODO     => never mind the copy/past, works exactly as setFieldByReflection do.
    /**
     * If any error on type, field, etc, it will crash tests
     * Set value of a private attribute.
     *
     * @param _class the class of the object you want to change a field value
     * @param object the object you want to change a field value
     * @param fieldName its field name you want to change
     * @param fieldValue the desired value
     */
    public static void setFieldByReflection2(Class<?> _class, Object object, String fieldName, Object fieldValue){
        try{
            Field field = _class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     *
     * @param object object you want to get a field from
     * @param fieldName the name of the field you want to get
     * @return the field instance
     */
    public static Object getFieldByReflection(Object object, String fieldName){
        try {
            Field field;
            field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    public static Object getFieldByReflection2(Class<?> _class, Object object, String fieldName){
        try {
            Field field;
            field = _class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    // TODO accessMethodByReflection
}
