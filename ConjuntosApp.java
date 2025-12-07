package Actividad3;

/**
 *
 * @author Elsy Joselyn Godinez Juarez
 * GTID141
 * UNIDAD 3
 * EJERCICIO 3
 */


import javax.swing.*;

/**
 * Aplicación principal para demostrar el uso de conjuntos en Java con interfaz gráfica Swing
 * Esta aplicación permite realizar operaciones de conjuntos con datos de estudiantes
 */
public class ConjuntosApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer el look and feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("No se pudo establecer el look and feel: " + e.getMessage());
            }
            
            // Crear y mostrar la ventana principal
            new ConjuntosFrame().setVisible(true);
        });
    }
}
