package Actividad3;

/**
 *
 * @author Elsy Joselyn Godinez Juarez
 * GTID141
 * Unidad 3 Ejercico 3
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Ventana principal de la aplicación que muestra la interfaz gráfica
 * Permite realizar operaciones de conjuntos con datos de estudiantes
 */
public class ConjuntosFrame extends JFrame {
    
    // Conjuntos para almacenar los datos
    private Set<String> conjuntoA = new HashSet<>();
    private Set<String> conjuntoB = new HashSet<>();
    private Set<String> conjuntoResultado = new HashSet<>();
    
    // Componentes de la interfaz
    private JTextArea txtConjuntoA, txtConjuntoB, txtResultado;
    private JTextField txtNuevoElemento;
    private JLabel lblEstado;
    
    /**
     * Constructor que inicializa la interfaz gráfica
     */
    public ConjuntosFrame() {
        setTitle("Aplicación de Conjuntos - Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Configurar colores globales
        configurarColoresGlobales();
        
        inicializarComponentes();
        inicializarDatos();
        actualizarVistas();
    }
    
    /**
     * Configura colores globales para todos los componentes
     */
    private void configurarColoresGlobales() {
        // Forzar texto negro en todos los componentes
        UIManager.put("Label.foreground", Color.BLACK);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextArea.foreground", Color.BLACK);
        UIManager.put("TitledBorder.titleColor", Color.BLACK);
    }
    
    /**
     * Inicializa todos los componentes de la interfaz gráfica
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Título
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central - Conjuntos
        JPanel panelCentral = crearPanelCentral();
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior - Controles
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel del título
     */
    private JPanel crearPanelTitulo() {
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelTitulo.setBackground(new Color(70, 130, 180));
        
        JLabel titulo = new JLabel("GESTIÓN DE CONJUNTOS DE ESTUDIANTES");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);
        
        return panelTitulo;
    }
    
    /**
     * Crea el panel central con los conjuntos
     */
    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 15, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelCentral.setBackground(new Color(240, 240, 240));
        
        // Conjunto A
        JPanel panelA = crearPanelConjunto("CONJUNTO A - Grupo 1", new Color(173, 216, 230));
        txtConjuntoA = crearAreaTexto();
        panelA.add(new JScrollPane(txtConjuntoA), BorderLayout.CENTER);
        
        // Conjunto B
        JPanel panelB = crearPanelConjunto("CONJUNTO B - Grupo 2", new Color(255, 218, 185));
        txtConjuntoB = crearAreaTexto();
        panelB.add(new JScrollPane(txtConjuntoB), BorderLayout.CENTER);
        
        // Resultado
        JPanel panelResultado = crearPanelConjunto("RESULTADO", new Color(144, 238, 144));
        txtResultado = crearAreaTexto();
        panelResultado.add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        
        panelCentral.add(panelA);
        panelCentral.add(panelB);
        panelCentral.add(panelResultado);
        
        return panelCentral;
    }
    
    /**
     * Crea un área de texto configurada
     */
    private JTextArea crearAreaTexto() {
        JTextArea textArea = new JTextArea(15, 20);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
    
    /**
     * Crea el panel inferior con controles
     */
    private JPanel crearPanelInferior() {
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        // Panel de entrada de datos
        JPanel panelEntrada = crearPanelEntrada();
        panelInferior.add(panelEntrada, BorderLayout.NORTH);
        
        // Panel de operaciones
        JPanel panelOperaciones = crearPanelOperaciones();
        panelInferior.add(panelOperaciones, BorderLayout.CENTER);
        
        // Barra de estado
        lblEstado = new JLabel(" Listo - Ingrese datos y realice operaciones");
        lblEstado.setBorder(BorderFactory.createLoweredBevelBorder());
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEstado.setForeground(Color.BLACK);
        lblEstado.setBackground(new Color(220, 220, 220));
        lblEstado.setOpaque(true);
        panelInferior.add(lblEstado, BorderLayout.SOUTH);
        
        return panelInferior;
    }
    
    /**
     * Crea el panel de entrada de datos
     */
    private JPanel crearPanelEntrada() {
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 2), 
            "GESTIÓN DE ESTUDIANTES"
        ));
        panelEntrada.setBackground(Color.WHITE);
        
        // Etiqueta "Nuevo estudiante:"
        JLabel lblNuevoEstudiante = new JLabel("Nuevo estudiante:");
        lblNuevoEstudiante.setFont(new Font("Arial", Font.BOLD, 13));
        lblNuevoEstudiante.setForeground(Color.BLACK);
        panelEntrada.add(lblNuevoEstudiante);
        
        // Campo de texto
        txtNuevoElemento = new JTextField(25);
        txtNuevoElemento.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNuevoElemento.setForeground(Color.BLACK);
        txtNuevoElemento.setCaretColor(Color.BLACK);
        txtNuevoElemento.setBackground(Color.WHITE);
        txtNuevoElemento.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panelEntrada.add(txtNuevoElemento);
        
        // Botones
        panelEntrada.add(crearBotonAgregar("Agregar a A", 'A'));
        panelEntrada.add(crearBotonAgregar("Agregar a B", 'B'));
        panelEntrada.add(crearBotonLimpiar());
        
        return panelEntrada;
    }
    
    /**
     * Crea el panel de operaciones
     */
    private JPanel crearPanelOperaciones() {
        JPanel panelOperaciones = new JPanel(new GridLayout(2, 3, 10, 10));
        panelOperaciones.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 2), 
            "OPERACIONES DE CONJUNTOS"
        ));
        panelOperaciones.setBackground(Color.WHITE);
        panelOperaciones.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Fila 1 de operaciones
        panelOperaciones.add(crearBotonOperacion("UNIÓN (A ∪ B)", 
            new Color(135, 206, 235), e -> union()));
        panelOperaciones.add(crearBotonOperacion("INTERSECCIÓN (A ∩ B)", 
            new Color(255, 182, 193), e -> interseccion()));
        panelOperaciones.add(crearBotonOperacion("DIFERENCIA (A - B)", 
            new Color(152, 251, 152), e -> diferenciaAB()));
        
        // Fila 2 de operaciones
        panelOperaciones.add(crearBotonOperacion("DIFERENCIA (B - A)", 
            new Color(221, 160, 221), e -> diferenciaBA()));
        panelOperaciones.add(crearBotonOperacion("COMPLEMENTO (A')", 
            new Color(255, 228, 196), e -> complementoA()));
        panelOperaciones.add(crearBotonOperacion("VERIFICAR ⊆", 
            new Color(176, 224, 230), e -> verificarSubconjunto()));
        
        return panelOperaciones;
    }
    
    /**
     * Crea un botón de agregar con estilo
     */
    private JButton crearBotonAgregar(String texto, char conjunto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Colores según el conjunto
        if (conjunto == 'A') {
            boton.setBackground(new Color(173, 216, 230));  // Azul claro
        } else {
            boton.setBackground(new Color(255, 218, 185));  // Naranja claro
        }
        
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        
        // Tooltip
        boton.setToolTipText("Agrega el estudiante al Conjunto " + conjunto);
        
        // Action listener
        if (conjunto == 'A') {
            boton.addActionListener(e -> agregarElemento(conjuntoA, 'A'));
        } else {
            boton.addActionListener(e -> agregarElemento(conjuntoB, 'B'));
        }
        
        return boton;
    }
    
    /**
     * Crea el botón Limpiar Todo
     */
    private JButton crearBotonLimpiar() {
        JButton btnLimpiar = new JButton("Limpiar Todo");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(240, 128, 128));  // Rojo claro
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btnLimpiar.addActionListener(e -> limpiarTodo());
        btnLimpiar.setToolTipText("Elimina todos los elementos y reinicia con datos de ejemplo");
        return btnLimpiar;
    }
    
    /**
     * Crea un panel para mostrar un conjunto
     */
    private JPanel crearPanelConjunto(String titulo, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Crear borde con título
        BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(color.darker(), 3), 
            titulo
        );
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color.darker(), 3), 
                titulo
            ),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        panel.setBackground(color.brighter());
        
        return panel;
    }
    
    /**
     * Crea un botón de operación
     */
    private JButton crearBotonOperacion(String texto, Color colorFondo, ActionListener listener) {
        JButton boton = new JButton("<html><center>" + texto.replace(" ", "<br>") + "</center></html>");
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(10, 5, 10, 5)
        ));
        boton.addActionListener(listener);
        return boton;
    }
    
    /**
     * Inicializa los conjuntos con datos de ejemplo
     */
    private void inicializarDatos() {
        // Conjunto A - Grupo 1 de estudiantes
        conjuntoA.add("Ana García");
        conjuntoA.add("Carlos López");
        conjuntoA.add("María Rodríguez");
        conjuntoA.add("Pedro Sánchez");
        conjuntoA.add("Laura Martínez");
        
        // Conjunto B - Grupo 2 de estudiantes
        conjuntoB.add("María Rodríguez");
        conjuntoB.add("Pedro Sánchez");
        conjuntoB.add("Juan Pérez");
        conjuntoB.add("Sofía Fernández");
        conjuntoB.add("David González");
    }
    
    /**
     * Actualiza las vistas de texto
     */
    private void actualizarVistas() {
        actualizarTextoConjunto(txtConjuntoA, conjuntoA, "Conjunto A");
        actualizarTextoConjunto(txtConjuntoB, conjuntoB, "Conjunto B");
        actualizarTextoConjunto(txtResultado, conjuntoResultado, "Resultado");
    }
    
    /**
     * Actualiza un área de texto con el contenido de un conjunto
     */
    private void actualizarTextoConjunto(JTextArea textArea, Set<String> conjunto, String nombre) {
        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════════════════\n");
        sb.append(nombre.toUpperCase()).append("\n");
        sb.append("══════════════════════════════════════════\n\n");
        sb.append("Total de elementos: ").append(conjunto.size()).append("\n\n");
        
        if (conjunto.isEmpty()) {
            sb.append("(conjunto vacío)\n");
        } else {
            int i = 1;
            for (String elemento : conjunto) {
                sb.append(String.format("%2d. %s\n", i++, elemento));
            }
        }
        
        sb.append("\n══════════════════════════════════════════");
        
        textArea.setText(sb.toString());
    }
    
    // ============================================
    // OPERACIONES DE CONJUNTOS - IMPLEMENTACIÓN
    // ============================================
    
    /**
     * OPERACIÓN 1: UNIÓN de conjuntos
     * Combina todos los elementos de A y B sin duplicados
     */
    private void union() {
        conjuntoResultado.clear();
        conjuntoResultado.addAll(conjuntoA);  // Agrega todos los elementos de A
        conjuntoResultado.addAll(conjuntoB);  // Agrega todos los elementos de B
        
        actualizarVistas();
        lblEstado.setText(" Unión realizada: " + conjuntoResultado.size() + 
                         " elementos únicos (A: " + conjuntoA.size() + 
                         " + B: " + conjuntoB.size() + " - duplicados)");
    }
    
    /**
     * OPERACIÓN 2: INTERSECCIÓN de conjuntos
     * Encuentra elementos comunes entre A y B
     */
    private void interseccion() {
        conjuntoResultado.clear();
        conjuntoResultado.addAll(conjuntoA);  // Copia A al resultado
        conjuntoResultado.retainAll(conjuntoB);  // Mantiene solo los que están en B
        
        actualizarVistas();
        lblEstado.setText(" Intersección: " + conjuntoResultado.size() + 
                         " elementos comunes entre A y B");
    }
    
    /**
     * OPERACIÓN 3: DIFERENCIA A - B
     * Elementos que están en A pero no en B
     */
    private void diferenciaAB() {
        conjuntoResultado.clear();
        conjuntoResultado.addAll(conjuntoA);  // Copia A al resultado
        conjuntoResultado.removeAll(conjuntoB);  // Elimina los que están en B
        
        actualizarVistas();
        lblEstado.setText(" Diferencia A-B: " + conjuntoResultado.size() + 
                         " elementos únicos de A (no en B)");
    }
    
    /**
     * OPERACIÓN 4: DIFERENCIA B - A
     * Elementos que están en B pero no en A
     */
    private void diferenciaBA() {
        conjuntoResultado.clear();
        conjuntoResultado.addAll(conjuntoB);  // Copia B al resultado
        conjuntoResultado.removeAll(conjuntoA);  // Elimina los que están en A
        
        actualizarVistas();
        lblEstado.setText(" Diferencia B-A: " + conjuntoResultado.size() + 
                         " elementos únicos de B (no en A)");
    }
    
    /**
     * OPERACIÓN 5: COMPLEMENTO de A
     * Suponiendo universo = A ∪ B, complemento = universo - A
     */
    private void complementoA() {
        Set<String> universo = new HashSet<>();
        universo.addAll(conjuntoA);
        universo.addAll(conjuntoB);
        
        conjuntoResultado.clear();
        conjuntoResultado.addAll(universo);  // Copia universo al resultado
        conjuntoResultado.removeAll(conjuntoA);  // Elimina los que están en A
        
        actualizarVistas();
        lblEstado.setText(" Complemento de A: " + conjuntoResultado.size() + 
                         " elementos del universo que no están en A");
    }
    
    /**
     * OPERACIÓN 6: VERIFICAR SI A ES SUBCONJUNTO DE B
     * Comprueba si todos los elementos de A están contenidos en B
     */
    private void verificarSubconjunto() {
        boolean esSubconjunto = conjuntoB.containsAll(conjuntoA);
        
        if (esSubconjunto) {
            lblEstado.setText(" ✓ VERDADERO: A ⊆ B (todos los elementos de A están en B)");
        } else {
            // Encontrar qué elementos faltan
            Set<String> faltantes = new HashSet<>(conjuntoA);
            faltantes.removeAll(conjuntoB);
            
            lblEstado.setText(" ✗ FALSO: A ⊈ B. Elementos de A que faltan en B: " + 
                             String.join(", ", faltantes));
        }
        
        // Mostrar el análisis en el área de resultado
        StringBuilder analisis = new StringBuilder();
        analisis.append("══════════════════════════════════════════\n");
        analisis.append("ANÁLISIS DE SUBCONJUNTO\n");
        analisis.append("══════════════════════════════════════════\n\n");
        analisis.append("¿A es subconjunto de B?\n");
        analisis.append("Respuesta: ").append(esSubconjunto ? "SÍ (A ⊆ B)" : "NO (A ⊈ B)").append("\n\n");
        
        analisis.append("Conjunto A: ").append(conjuntoA.size()).append(" elementos\n");
        analisis.append("Conjunto B: ").append(conjuntoB.size()).append(" elementos\n\n");
        
        if (!esSubconjunto) {
            Set<String> faltantes = new HashSet<>(conjuntoA);
            faltantes.removeAll(conjuntoB);
            analisis.append("Elementos de A que NO están en B:\n");
            for (String elem : faltantes) {
                analisis.append("  • ").append(elem).append("\n");
            }
        }
        
        txtResultado.setText(analisis.toString());
    }
    
    /**
     * Agrega un nuevo elemento a un conjunto
     */
    private void agregarElemento(Set<String> conjunto, char nombreConjunto) {
        String elemento = txtNuevoElemento.getText().trim();
        
        if (elemento.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un nombre de estudiante", 
                "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (conjunto.contains(elemento)) {
            JOptionPane.showMessageDialog(this, 
                "El estudiante '" + elemento + "' ya existe en el conjunto " + nombreConjunto, 
                "Elemento duplicado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            conjunto.add(elemento);
            actualizarVistas();
            lblEstado.setText(" ✓ Estudiante '" + elemento + "' agregado al conjunto " + nombreConjunto);
        }
        
        txtNuevoElemento.setText("");
        txtNuevoElemento.requestFocus();
    }
    
    /**
     * Limpia todos los conjuntos
     */
    private void limpiarTodo() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de limpiar todos los conjuntos?\n\n" +
            "Se perderán los datos actuales y se restaurarán los valores iniciales.",
            "Confirmar limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            conjuntoA.clear();
            conjuntoB.clear();
            conjuntoResultado.clear();
            inicializarDatos(); // Restaura los datos de ejemplo
            actualizarVistas();
            lblEstado.setText(" ✓ Todos los conjuntos han sido limpiados y reinicializados con datos de ejemplo");
        }
    }
    
    /**
 * Método para obtener una descripción de las operaciones disponibles
 */
public String getDescripcionOperaciones() {
    StringBuilder sb = new StringBuilder();
    sb.append("OPERACIONES DISPONIBLES:\n\n");
    sb.append("1. UNIÓN (A ∪ B): Combina todos los elementos de ambos conjuntos\n");
    sb.append("2. INTERSECCIÓN (A ∩ B): Elementos que están en ambos conjuntos\n");
    sb.append("3. DIFERENCIA (A - B): Elementos de A que no están en B\n");
    sb.append("4. DIFERENCIA (B - A): Elementos de B que no están en A\n");
    sb.append("5. COMPLEMENTO (A'): Elementos que no están en A (del universo A∪B)\n");
    sb.append("6. VERIFICAR ⊆: Comprueba si A es subconjunto de B\n\n");
    sb.append("USO:\n");
    sb.append("1. Ingrese nombres en el campo 'Nuevo estudiante:'\n");
    sb.append("2. Use 'Agregar a A' o 'Agregar a B' para añadir elementos\n");
    sb.append("3. Haga clic en cualquier operación para ver el resultado\n");
    sb.append("4. Use 'Limpiar Todo' para reiniciar");
    
    return sb.toString();
}
}

