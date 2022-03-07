package servicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import datos.DAOException;


public class TablaInicio extends JPanel implements ActionListener {

    private JButton botonSalir;
    private JButton botonRegistrarCurso;
    private JButton botonRegistrarAlumno;
    private JButton botonInscribirse;
    private JButton botonModificarNotas;
    private JButton botonReporte;
	
    public TablaInicio() {
        super();
        armarPanelInicio();
    }
    
    public void armarPanelInicio() {
    	JFrame frame = new JFrame("Inicio");
        frame.setSize(300, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelIn(panel);

        frame.setVisible(true);
    }
    
    public void mostrarInicioPanel() {
    	JFrame frame = new JFrame();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new TablaInicio());
		frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
		frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

	}
    
    public void armarPanelIn(JPanel panel) {
    	panel.setLayout(null);

        JLabel admin = new JLabel("Administrador:");
        admin.setBounds(10, 10, 100, 25);
        panel.add(admin);
        
        botonRegistrarAlumno = new JButton("Registrar Alumnos");
        botonRegistrarAlumno.addActionListener(this);
        this.add(botonRegistrarAlumno);
        botonRegistrarAlumno.setBounds(100, 10, 160, 25);
        panel.add(botonRegistrarAlumno);
		
        botonRegistrarCurso = new JButton("Registrar Cursos");
        botonRegistrarCurso.addActionListener(this);
        this.add(botonRegistrarCurso);
        botonRegistrarCurso.setBounds(100, 40, 160, 25);
        panel.add(botonRegistrarCurso);
        
        JLabel prof = new JLabel("Profesor:");
        prof.setBounds(10, 90, 100, 25);
        panel.add(prof);
        
        botonModificarNotas = new JButton("Modificar Notas");
        botonModificarNotas.addActionListener(this);
        this.add(botonModificarNotas);
        botonModificarNotas.setBounds(100, 90, 160, 25);
        panel.add(botonModificarNotas);
        
        JLabel ins = new JLabel("Alumnos:");
        ins.setBounds(10, 120, 100, 25);
        panel.add(ins);
        
        botonInscribirse = new JButton("Inscribirse");
        botonInscribirse.addActionListener(this);
        this.add(botonInscribirse);
        botonInscribirse.setBounds(100, 120, 160, 25);
        panel.add(botonInscribirse);
        
        JLabel reporte = new JLabel("Reporte");
        reporte.setBounds(10, 150, 100, 25);
        panel.add(reporte);
        
        JLabel reporte2 = new JLabel("de Cursos:");
        reporte2.setBounds(10, 160, 100, 25);
        panel.add(reporte2);
        
        botonReporte = new JButton("Reporte");
        botonReporte.addActionListener(this);
        this.add(botonReporte);
        botonReporte.setBounds(100, 160, 160, 25);
        panel.add(botonReporte);
        
        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        this.add(botonSalir);
        botonSalir.setBounds(10, 220, 60, 25);
        panel.add(botonSalir);
    }
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == botonSalir) {
			System.exit(WIDTH);
		}
		if( e.getSource() == botonRegistrarAlumno) {
			JFrame j = new JFrame("Tabla Alumnos");
	        try {
				j.getContentPane().add(new TablaAlumnosPanel());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
	        j.pack();
	        j.setVisible(true);
		}
		if( e.getSource() == botonRegistrarCurso) {
			JFrame j = new JFrame("Registrar Cursos");
	        try {
				j.getContentPane().add(new TablaCursosPanel());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
	        j.pack();
	        j.setVisible(true);
		}
		if( e.getSource() == botonModificarNotas) {
			JFrame j = new JFrame("Modificar Notas");
	        try {
				j.getContentPane().add(new TablaModificarNotasPanel());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
	        j.pack();
	        j.setVisible(true);
		}
		if( e.getSource() == botonInscribirse) {
			JFrame j = new JFrame("Inscribirse");
	        try {
				j.getContentPane().add(new TablaInscribirsePanel());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
	        j.pack();
	        j.setVisible(true);
		}
		if( e.getSource() == botonReporte) {
			JFrame j = new JFrame("Reporte Cursos");
	        try {
				j.getContentPane().add(new TablaReportePanel());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
	        j.pack();
	        j.setVisible(true);
		}
		
		
	}
	
	
}
