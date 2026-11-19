import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Persona.class;

            System.out.println("=== Constructores declarados ===");
            Constructor<?>[] constructores = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructores) {
                System.out.println(c + " | Parámetros: " + Arrays.toString(c.getParameterTypes()));
            }

            Constructor<Persona> constructorConArgs = Persona.class.getConstructor(String.class, int.class);
            Persona persona = constructorConArgs.newInstance("Lucía", 24);
            System.out.println("\n=== Instancia creada con Reflection ===");
            persona.mostrarInformacion();

            Field campoNombre = clazz.getDeclaredField("nombre");
            campoNombre.setAccessible(true);
            campoNombre.set(persona, "Martín");
            System.out.println("\n=== Campo 'nombre' modificado en tiempo de ejecución ===");

            Method metodoMostrar = clazz.getDeclaredMethod("mostrarInformacion");
            System.out.println("\n=== Invocación de mostrarInformacion() ===");
            metodoMostrar.invoke(persona);

            Method metodoSaludar = clazz.getDeclaredMethod("Saludar");
            System.out.println("\n=== Invocación normal de Saludar() ===");
            System.out.println(metodoSaludar.invoke(persona));

            metodoSaludar.setAccessible(false);
            System.out.println("Modificadores reales de Saludar(): " + metodoSaludar.getModifiers());
            System.out.println("Saludar() sigue devolviendo: " + metodoSaludar.invoke(persona));

        } catch (ReflectiveOperationException e) {
            System.err.println("Error durante la operación reflexiva: " + e.getMessage());
            e.printStackTrace();
        }
    }
}