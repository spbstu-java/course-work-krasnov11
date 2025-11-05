package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Caller {

    private final ValueProvider valueProvider;

    public Caller(ValueProvider valueProvider){
        if (valueProvider == null)
            throw new IllegalArgumentException("valueProvider is null");
        this.valueProvider = valueProvider;
    }

    public String executeCalls(Object obj)
            throws ValueProviderException, InvocationTargetException, IllegalAccessException {

        var sb = new StringBuilder();

        if (obj == null)
            throw new IllegalArgumentException("Argument obj is null");

        var objClass = obj.getClass();

        for (var m : objClass.getDeclaredMethods()){

            sb.append(String.format("%n%nProcess method: %s%n", m));

            var modifiers = m.getModifiers();
            if (Modifier.isPublic(modifiers))
            {
                sb.append("Method is public, call will not be executed");
                continue;
            }

            for (var a : m.getAnnotations()){

                if (a.annotationType().isAssignableFrom(CallCount.class)){

                    sb.append(a);
                    sb.append('\n');

                    var callCount = (CallCount)a;
                    if (callCount.value() > 0){
                        var prmClasses = m.getParameterTypes();
                        var args = new Object[prmClasses.length];

                        // arguments
                        for (int i = 0; i < prmClasses.length; i++) {
                            args[i] = this.valueProvider.getValue(prmClasses[i]);
                        }

                        // call
                        m.setAccessible(true);
                        for (int i = 0; i<callCount.value(); ++i){
                            sb.append(String.format("Invoke %d%n", i+1));
                            m.invoke(obj, args);
                        }
                    }

                    break;
                }
            }
        }

        return sb.toString();
    }
}
