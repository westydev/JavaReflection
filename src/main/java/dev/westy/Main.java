package dev.westy;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main {
    public static String getValueByFieldName(Field f, Object obj) {
        String result = "";

        try {
            f.setAccessible(true);
            Object fieldValue = f.get(obj);

            if (fieldValue instanceof String) {
                result = (String) fieldValue;
            } else {
                result = String.valueOf(fieldValue);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return result;
    };

    public static Object invokeMethod(Method method, Object obj, Object[] parameters)
            throws IllegalAccessException, InvocationTargetException {
        try {
            method.setAccessible(true);
            Object result = method.invoke(obj, parameters);
            return result;

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        Entity Mirac = new Entity("Mirac", 16);

        // Get Visible Methods

        try {
            System.out.println("Visible Methods: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Method m : entityClass.getMethods()) {
                String paramsString = "(";
                int index = 0;

                for(Parameter param : m.getParameters()) {
                    index += 1;
                    if(m.getParameters().length == index) {
                        paramsString += param.getType().getName() + " " + param.getName();
                    } else {
                        paramsString += param.getType().getName() + " " + param.getName() + ", ";
                    }

                }

                paramsString += ");";

                System.out.println(m.getReturnType().getName() + " " + m.getName() + paramsString);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // All Methods

        try {
            System.out.println("\nAll Methods: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Method m : entityClass.getDeclaredMethods()) {
                String paramsString = "(";
                int index = 0;

                for(Parameter param : m.getParameters()) {
                    index += 1;
                    if(m.getParameters().length == index) {
                        paramsString += param.getType().getName() + " " + param.getName();
                    } else {
                        paramsString += param.getType().getName() + " " + param.getName() + ", ";
                    }

                }

                paramsString += ");";

                System.out.println(m.getReturnType().getName() + " " + m.getName() + paramsString);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


       //Visible Fields

        try {
            System.out.println("\nVisible Fields: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Field f : entityClass.getFields()) {
                String result = "";

                result += f.getType().getName() + " " + f.getName() + ";";

                System.out.println(result);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //All Fields

        try {
            System.out.println("\nAll Fields: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Field f : entityClass.getDeclaredFields()) {
                String result = "";

                result += f.getType().getName() + " " + f.getName() + ";";

                System.out.println(result);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //Visible Fields Values

        try {
            System.out.println("\nVisible Fields Values: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Field f : entityClass.getFields()) {
                String result = "";

                result += f.getType().getName() + " " + f.getName() + ";";

                System.out.println(result + " Value: " + getValueByFieldName(f, Mirac));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //All Fields Values

        try {
            System.out.println("\nAll Fields Values: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Field f : entityClass.getDeclaredFields()) {
                String result = "";

                result += f.getType().getName() + " " + f.getName() + ";";

                System.out.println(result + " Value: " + getValueByFieldName(f, Mirac));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("\nInvoke Methods: ");

            Class<Entity> entityClass = (Class<Entity>) Class.forName("dev.westy.Entity");

            for(Method m : entityClass.getDeclaredMethods()) {
                String paramsString = "(";
                int index = 0;

                for(Parameter param : m.getParameters()) {
                    index += 1;
                    if(m.getParameters().length == index) {
                        paramsString += param.getType().getName() + " " + param.getName();
                    } else {
                        paramsString += param.getType().getName() + " " + param.getName() + ", ";
                    }

                }

                paramsString += ");";

                try {
                    String invokedValue = "";

                    if(m.getName().equals("getName")) {
                        invokedValue += invokeMethod(m, Mirac, new Object[]{});
                    } else if(m.getName().equals("setName")) {
                        invokedValue += invokeMethod(m, Mirac, new Object[]{ "Yigit" });
                    } else if(m.getName().equals("setProtection")) {
                        invokedValue += invokeMethod(m, Mirac, new Object[]{ "Diamond", 30 }).toString();
                    } else if(m.getName().equals("getOld")) {
                        invokedValue += invokeMethod(m, Mirac, new Object[]{ }).toString();
                    } else if(m.getName().equals("setOld")) {
                        invokedValue += invokeMethod(m, Mirac, new Object[]{ 20 }).toString();
                    }

                    System.out.println(m.getReturnType().getName() + " " + m.getName() + paramsString + " Invoked: " + "'" + invokedValue + "'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}