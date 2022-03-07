package servicio;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import datos.CursoDAOH2Impl;
import datos.DAOException;
import datos.DBManager;
import datos.InscripcionDAO;
import datos.InscripcionDAOH2Impl;
import entidades.Curso;
import entidades.Inscripcion;

public class TablaInscribirsePanel extends JPanel implements ActionListener {
	
	private JTable tablaInscripcion;
    private CursoTableModel modelo;

    private JScrollPane scrollPaneParaTabla;

    private JButton botonInscribir;
    private JButton botonDescenscribirme;
    private JButton botonDejarCurso;
    private JButton botonRegistrar;

    private JTextField codInsText, idCursoText, legAlumText;
    private JTextField idCursoText2, legAlumText2;
    
    private InscripcionDAOH2Impl inscripcionDao;
    private CursoDAOH2Impl cursoDao;

    public TablaInscribirsePanel() throws DAOException {
        super();
        armarPanelInscripcion();
    }
    
    public void armarPanelInscripcion() throws DAOException {
    	this.setLayout(new FlowLayout());

        modelo = new CursoTableModel();
        tablaInscripcion = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaInscripcion);
        this.add(scrollPaneParaTabla);

        botonInscribir = new JButton("Inscribirme");
        botonInscribir.addActionListener(this);
        this.add(botonInscribir);
        
        botonDescenscribirme = new JButton("Descenscribirme");
        botonDescenscribirme.addActionListener(this);
        this.add(botonDescenscribirme);

        cursoDao = new CursoDAOH2Impl();
        List<Curso> listaCurso = cursoDao.listaTodosLosCursos();
        
        modelo.setContenido(listaCurso);
        
        modelo.fireTableDataChanged();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botonInscribir) {
            agregar();
            //---------
        } else if(e.getSource() == botonRegistrar) {
            
        	int codIns = Integer.parseInt(codInsText.getText());
        	int idCurso = Integer.parseInt(idCursoText.getText());
        	int legAl = Integer.parseInt(legAlumText.getText());
        	
        	Inscripcion i = new Inscripcion(codIns, idCurso, legAl);        	
        	
        	try {
        		inscripcionDao = new InscripcionDAOH2Impl();
            	List<Inscripcion> listaI = inscripcionDao.listaTodasLasInscripciones();
            	int cupo = cantidadCupo(listaI,legAl);     
            	
            	cursoDao = new CursoDAOH2Impl();
        		Curso c = cursoDao.muestraCurso(idCurso);
            	
            	if( cupo < 3 && c.getCupo()>0 ) {
            		insertarBaseDatos(i);
            		disminuirCupos(i);
            	}
            	else {
            		JOptionPane.showMessageDialog(this, "Cupo superado o no hay mas disponibilidad en el curso", "Error", JOptionPane.ERROR_MESSAGE);
            	}
        	} catch(DAOException e1) {
        		e1.printStackTrace();
        	}
        	     		
        	codInsText.setText(null);
            idCursoText.setText(null);
            legAlumText.setText(null);

            modelo.fireTableDataChanged();
        } else if(e.getSource() == botonDescenscribirme) {	        	
        	agregarPanelDescenscripcion();
        	//---------------------------
        } else if(e.getSource() == botonDejarCurso) {
        	
        	int idCurso = Integer.parseInt(idCursoText2.getText());
        	int legAl = Integer.parseInt(legAlumText2.getText());
        	
        	try {
        		inscripcionDao = new InscripcionDAOH2Impl();
            	List<Inscripcion> listaI = inscripcionDao.listaTodasLasInscripciones();
            	
            	Boolean cursoEncontrado = false;
            	
            	for(int i=0; i<listaI.size(); i++) {
            		Inscripcion ins = new Inscripcion();
            		ins = listaI.get(i);
            		
            		if( idCurso == ins.getId_curso() && legAl == ins.getLeg_alumno() ) {
            			darDeBajaInscripcion(ins);
            			aumentarCupo(ins);
            			cursoEncontrado = true;
            		}  		
            	} 
            	
            	if( cursoEncontrado ) {
            		JOptionPane.showMessageDialog(this, "Dado de baja", "Dar de baja", JOptionPane.INFORMATION_MESSAGE);
            	} else {
            		JOptionPane.showMessageDialog(this, "No se encontro el curso o alumno", "Advertencia!", JOptionPane.INFORMATION_MESSAGE);
            	}
        	} catch(DAOException e1) {
        		e1.printStackTrace();
        	}
        	
        		
            idCursoText2.setText(null);
            legAlumText2.setText(null);

            modelo.fireTableDataChanged();
        }
		
	}
	
	public void aumentarCupo(Inscripcion ins) throws DAOException {
		cursoDao = new CursoDAOH2Impl();
		List<Curso> listaCurso = cursoDao.listaTodosLosCursos();
		
		for(int i=0; i<listaCurso.size(); i++) {
			Curso c = new Curso();
			c = listaCurso.get(i);
			
			int cupo = c.getCupo() + 1;
			
			if( c.getId_Curso() == ins.getId_curso()) {
				c.setCupo(cupo);
			}
			
			cursoDao.actualizarCurso(c);
			modelo.getContenido().set(i, c);
		}
	}
	
	public void disminuirCupos(Inscripcion ins) throws DAOException {
		cursoDao = new CursoDAOH2Impl();
		List<Curso> listaCurso = cursoDao.listaTodosLosCursos();
		
		for(int i=0; i<listaCurso.size(); i++) {
			Curso c = new Curso();
			c = listaCurso.get(i);
			
			int cupo = c.getCupo() - 1;
			
			if( c.getId_Curso() == ins.getId_curso()) {
				c.setCupo(cupo);
			}
			
			cursoDao.actualizarCurso(c);
			modelo.getContenido().set(i, c);
		}                  
	}
	
	public int cantidadCupo(List<Inscripcion> listaI, int legAl) {
		int cupo = 0;
		for(int i=0; i<listaI.size(); i++) {
			Inscripcion ins = listaI.get(i);
			if(legAl == ins.getLeg_alumno()) {
				cupo++;
			}
		}
		
		return cupo;
	}
	
	public void agregar() {
		JFrame frame = new JFrame("inscribirme");
        frame.setSize(300, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelRegistro(panel);

        frame.setVisible(true);
	}
	
	public void agregarPanelDescenscripcion() {
		JFrame frame = new JFrame("descenscribirme");
        frame.setSize(300, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelDescenscripcion(panel);

        frame.setVisible(true);	
	}
	
	public void armarPanelRegistro(JPanel panel) {
        panel.setLayout(null);

        JLabel codInsLabel = new JLabel("Cod Inscripcion");
        codInsLabel.setBounds(10, 10, 80, 25);
        panel.add(codInsLabel);

        codInsText = new JTextField(20);
        codInsText.setBounds(100, 10, 160, 25);
        panel.add(codInsText);

        JLabel idCursoLabel = new JLabel("IdCurso");
        idCursoLabel.setBounds(10, 40, 80, 25);
        panel.add(idCursoLabel);

        idCursoText = new JTextField(20);
        idCursoText.setBounds(100, 40, 160, 25);
        panel.add(idCursoText);

        JLabel legAlLabel = new JLabel("LegAlum");
        legAlLabel.setBounds(10, 80, 80, 25);
        panel.add(legAlLabel);

        legAlumText = new JTextField(20);
        legAlumText.setBounds(100, 80, 160, 25);
        panel.add(legAlumText);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
        botonRegistrar.setBounds(100, 160, 100, 25);
        panel.add(botonRegistrar);

    }
	
	public void armarPanelDescenscripcion(JPanel panel) {
		panel.setLayout(null);
		
		JLabel idCursoLabel = new JLabel("Id Curso");
		idCursoLabel.setBounds(10, 10, 80, 25);
        panel.add(idCursoLabel);

        idCursoText2 = new JTextField(20);
        idCursoText2.setBounds(100, 10, 160, 25);
        panel.add(idCursoText2);

        JLabel legAlumLabel = new JLabel("LegAlum");
        legAlumLabel.setBounds(10, 40, 80, 25);
        panel.add(legAlumLabel);

        legAlumText2 = new JTextField(20);
        legAlumText2.setBounds(100, 40, 160, 25);
        panel.add(legAlumText2);
        
        botonDejarCurso = new JButton("Dejar Curso");
        botonDejarCurso.addActionListener(this);
        this.add(botonDejarCurso);
        botonDejarCurso.setBounds(100, 80, 140, 25);
        panel.add(botonDejarCurso);
	}
	
	public void insertarBaseDatos(Inscripcion i) throws DAOException {
		InscripcionDAO dao = new InscripcionDAOH2Impl();
		Inscripcion aInsertar = new Inscripcion();
        aInsertar.setCod_inscripcion(i.getCod_inscripcion());
        aInsertar.setId_curso(i.getId_curso());
        aInsertar.setLeg_alumno(i.getLeg_alumno());
        dao.crearInscripcion(aInsertar);
	}
	
	public void darDeBajaInscripcion(Inscripcion ins) throws DAOException {
		InscripcionDAO dao = new InscripcionDAOH2Impl();
		dao.borrarInscripcion(ins.getCod_inscripcion());
	}

}
