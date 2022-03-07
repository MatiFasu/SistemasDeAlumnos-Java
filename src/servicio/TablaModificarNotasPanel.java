package servicio;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import datos.AlumnoDAO;
import datos.AlumnoDAOH2Impl;
import datos.CursoDAOH2Impl;
import datos.DAOException;
import datos.InscripcionDAOH2Impl;
import datos.NotasDao;
import datos.NotasDaoH2Impl;
import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import entidades.Notas;

public class TablaModificarNotasPanel extends JPanel implements ActionListener {

	private JTable tablaModificarNotas;
    private ModificarNotasTableModel modelo;

    private JScrollPane scrollPaneParaTabla;

    private JButton botonAgregar;
    private JButton botonActualizar;
    private JButton botonRegistrar;

    private JTextField notaText;
    
    private CursoDAOH2Impl cursoDao;
    private InscripcionDAOH2Impl inscripcionDao;
    private NotasDaoH2Impl notasDao;
    
    private List<Notas> listaN;
    
    private int contador;
    
    public TablaModificarNotasPanel() throws DAOException {
        super();
        armarPanelNotas();
    }
    
    public void armarPanelNotas() throws DAOException {
    	this.setLayout(new FlowLayout());

        modelo = new ModificarNotasTableModel();
        tablaModificarNotas = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaModificarNotas);
        this.add(scrollPaneParaTabla);
        
        botonAgregar = new JButton("CARGAR");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);
        
        cursoDao = new CursoDAOH2Impl();
        List<Curso> listaC = cursoDao.listaTodosLosCursos();
        
        inscripcionDao = new InscripcionDAOH2Impl();
        List<Inscripcion> listaI = inscripcionDao.listaTodasLasInscripciones();
        
        notasDao = new NotasDaoH2Impl();
        listaN = notasDao.listaTodasLasNotas();
        
        contador = notasDao.listaTodasLasNotas().size();
        System.out.println(contador);
        
        List<Notas> lista = subirDatos(listaC,listaI,listaN);
        
        modelo.setContenido(lista);
        
        modelo.fireTableDataChanged();
    }
	
    public List<Notas> subirDatos(List<Curso> listaC, List<Inscripcion> listaI, List<Notas> listaN) {
    	List<Notas> lista = new ArrayList<Notas>();
    	
    	contador = listaN.size();
    	
    	for(int i=0; i<listaI.size(); i++) {
    		
    		Inscripcion ins = new Inscripcion();
    		ins = listaI.get(i);    		
    		
    		int id_curso = ins.getId_curso();
    		int cod_mat = 0;
    		
			for(int j=0; j<listaC.size(); j++) {
			    Curso c = new Curso();
			    c = listaC.get(j);
			    if( id_curso == c.getId_Curso()) {
			    	cod_mat = c.getCod_materia(); 
			    }
			}
    		int leg_alum = ins.getLeg_alumno();
    		float nota = 0;
    		for(int k=0; k<listaN.size(); k++) {
    			Notas n = new Notas();
			    n = listaN.get(k);
			    if( leg_alum == n.getLeg_alum() && id_curso == n.getId_curso()) {
			    	nota = n.getNota(); 
			    }
			}
    		
    		Notas n = new Notas(contador,id_curso,cod_mat,leg_alum,nota);
    		
    		lista.add(n);
    	}
    	
    	return lista;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botonAgregar) {
            agregar();
        } else if(e.getSource() == botonActualizar) {
        	
        	float nota = Float.parseFloat(notaText.getText());
            
        	int filaSeleccionada = this.tablaModificarNotas.getSelectedRow();

            Notas n = this.modelo.getContenido().get(filaSeleccionada);
            n.setNota(nota);
            
            try {
            	notasDao.actualizarNota(n);
            } catch(DAOException e1) {
            	e1.printStackTrace();
            }
            
            
            this.modelo.getContenido().set(filaSeleccionada, n);

            modelo.fireTableDataChanged();
        	
        } else if(e.getSource() == botonRegistrar) {  
        	
        	contador++;
            System.out.println(contador);
        	
        	float nota = Float.parseFloat(notaText.getText());
        	
        	int filaSeleccionada = this.tablaModificarNotas.getSelectedRow();

            Notas n = this.modelo.getContenido().get(filaSeleccionada);
            n.setCod_nota(contador);
            n.setNota(nota);
            
            try {
				insertarBaseDatos(n);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}        
            
            this.modelo.getContenido().set(filaSeleccionada, n);

            modelo.fireTableDataChanged();
        	
        }
		
	}
	
	public void agregar() {
		JFrame frame = new JFrame("Registrar Notas");
        frame.setSize(300, 200);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelRegistro(panel);

        frame.setVisible(true);
	}
	
	public void armarPanelRegistro(JPanel panel) {
        panel.setLayout(null);

        JLabel notaLabel = new JLabel("Nota");
        notaLabel.setBounds(10, 10, 80, 25);
        panel.add(notaLabel);

        notaText = new JTextField(20);
        notaText.setBounds(100, 10, 160, 25);
        panel.add(notaText);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
        botonRegistrar.setBounds(50, 50, 100, 25);
        panel.add(botonRegistrar);
        
        botonActualizar = new JButton("Actualizar");
        botonActualizar.addActionListener(this);
        this.add(botonActualizar);
        botonActualizar.setBounds(160, 50, 100, 25);
        panel.add(botonActualizar);
        
    }
	
	public void insertarBaseDatos(Notas n) throws DAOException {
		NotasDao dao = new NotasDaoH2Impl();
        Notas aInsertar = new Notas();
        aInsertar.setCod_nota(n.getCod_nota());
        aInsertar.setId_curso(n.getId_curso());
        aInsertar.setCod_mat(n.getCod_mat());
        aInsertar.setLeg_alum(n.getLeg_alum());
        aInsertar.setNota(n.getNota());
        dao.crearNota(aInsertar);
	}
	

}
