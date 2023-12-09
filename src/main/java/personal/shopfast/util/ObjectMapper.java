package personal.shopfast.util;

import java.lang.reflect.Field;

public class ObjectMapper {

    public static <TSource, TTarget> TTarget map(TSource source, Class<TTarget> targetClass) {
        try {
            List<Field> sourceFields = new ArrayList<>();
            for (Class<?> c = source.getClass(); c != null; c = c.getSuperclass()) {
                sourceFields.addAll(Arrays.asList(c.getDeclaredFields()));
            }
            TTarget target = targetClass.newInstance();

            for (Field sourceField : sourceFields) {
                try {
                    Field targetField = targetClass.getDeclaredField(sourceField.getName());

                    if (targetField.getType().equals(sourceField.getType())) {
                        sourceField.setAccessible(true);
                        targetField.setAccessible(true);
                        targetField.set(target, sourceField.get(source));
                    }
                } catch (NoSuchFieldException ignored) {
                }
            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map objects", e);
        }
    }
}
