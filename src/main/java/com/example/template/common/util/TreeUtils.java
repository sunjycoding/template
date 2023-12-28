package com.example.template.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author created by sunjy on 12/27/23
 */
public class TreeUtils {

    public static <T> List<T> buildTree(List<T> list,
                                        String idFieldName,
                                        String parentIdFieldName,
                                        String childrenFieldName) {
        try {
            Map<Object, T> itemMap = new HashMap<>();
            for (T data : list) {
                Object id = getField(data, idFieldName);
                itemMap.put(id, data);
                setField(data, childrenFieldName, new ArrayList<>());
            }

            List<T> tree = new ArrayList<>();
            for (T item : list) {
                Object parentId = getField(item, parentIdFieldName);
                if (parentId == null) {
                    tree.add(item);
                } else {
                    T parentItem = itemMap.get(parentId);
                    if (parentItem != null) {
                        List<T> children = (List<T>) getField(parentItem, childrenFieldName);
                        children.add(item);
                    }
                }
            }
            return tree;
        } catch (Exception e) {
            throw new RuntimeException("获取数据树时出现错误", e);
        }
    }

    private static Object getField(Object data, String fieldName) throws ReflectiveOperationException {
        Field field = findField(data.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(data);
    }

    private static void setField(Object data, String fieldName, Object value) throws ReflectiveOperationException {
        Field field = findField(data.getClass(), fieldName);
        field.setAccessible(true);
        field.set(data, value);
    }

    private static Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> current = clazz;
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found");
    }

}
