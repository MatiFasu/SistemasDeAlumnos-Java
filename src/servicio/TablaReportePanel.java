package servicio;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import datos.AlumnoDAO;
import datos.AlumnoDAOH2Impl;
import datos.CursoDAOH2Impl;
import datos.DAOException;
import datos.DBManager;
import datos.InscripcionDAOH2Impl;
import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import entidades.Reporte;

public class TablaReportePanel extends JPanel implements ActionListener {

	private JTable tablaReporte;
    private ReporteTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    
    private JButton botonReporte;
    
    private InscripcionDAOH2Impl inscripcionDao;
    private CursoDAOH2Impl cursoDao;
    
    private List<Reporte> listaR;

    public TablaReportePanel() throws DAOException {
        super();
        armarPanelReporte();
    }
    
    public void armarPanelReporte() throws DAOException {
    	this.setLayout(new FlowLayout());

        modelo = new ReporteTableModel();
        tablaReporte = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaReporte);
        this.add(scrollPaneParaTabla);
        
        botonReporte = new JButton("Recaudacion total");
        botonReporte.addActionListener(this);
        this.add(botonReporte);
        
        cursoDao = new CursoDAOH2Impl();
        List<Curso> listaC = cursoDao.listaTodosLosCursos();
        
        inscripcionDao = new InscripcionDAOH2Impl();
        List<Inscripcion> listaI = inscripcionDao.listaTodasLasInscripciones();
        
        listaR = subirReportes(listaC, listaI);
        
        modelo.setContenido(listaR);
        
        modelo.fireTableDataChanged();
    }
    
    public List<Reporte> subirReportes(List<Curso> listaC, List<Inscripcion> listaI) {
    	List<Reporte> lista = new ArrayList<>();
    	
    	for(int i=0; i<listaC.size(); i++) {
    		Curso c = new Curso();
    		c = listaC.get(i);
    		
    		int idCurso = c.getId_Curso();
    		int anotados = 0;
    		for(int j=0; j<listaI.size(); j++) {
    			Inscripcion ins = new Inscripcion();
    			ins = listaI.get(j);
    			if( idCurso == ins.getId_curso() ) {
    				anotados++;
    			}
    		}
    		
    		float recaudacion = c.getPrecio() * anotados;
    		
    		Reporte r = new Reporte(idCurso,anotados,recaudacion);
    		
    		lista.add(r);
    	}
    	
    	return lista;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botonReporte) {
			
			float total = 0;
			
			List<Reporte> lista = listaR;
			for(int i=0; i<lista.size(); i++) {
				Reporte r = new Reporte();
				r = lista.get(i);
				total += r.getRecaudacion();
			}
			
			String s = Float.toString(total);
			
			JOptionPane.showConfirmDialog(this, "Recaudacion total es: " + s, "Recaudacion total", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	
	
}
