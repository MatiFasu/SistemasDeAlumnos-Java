package servicio;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import datos.CursoDAOH2Impl;
import datos.DAOException;
import datos.AlumnoDAO;
import datos.AlumnoDAOH2Impl;
import datos.CursoDAO;
import entidades.Alumno;
import entidades.Curso;

public class TablaCursosPanel extends JPanel implements ActionListener {

	private JTable tablaCurso;
    private CursoTableModel modelo;

    private JScrollPane scrollPaneParaTabla;

    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonRegistrar;
    
    private CursoDAOH2Impl cursoDao;
    
    private JTextField idCursoText, codMatText, legProfText, materiaText, precioText, cupoText;

    public TablaCursosPanel() throws DAOException {
        super();
        armarPanelCursos();
    }
    
    public void armarPanelCursos() throws DAOException {
    	this.setLayout(new FlowLayout());

        modelo = new CursoTableModel();
        tablaCurso = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaCurso);
        this.add(scrollPaneParaTabla);

        botonBorrar = new JButton("BORRAR");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

        botonAgregar = new JButton("CARGAR");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);
        
        cursoDao = new CursoDAOH2Impl();
        List<Curso> lista = cursoDao.listaTodosLosCursos();

        modelo.setContenido(lista);
        
        modelo.fireTableDataChanged();
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botonAgregar) {
            agregar();
        } else if(e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaCurso.getSelectedRow();

            Curso c = this.modelo.getContenido().get(filaSeleccionada);

            System.out.println(c);

            this.modelo.getContenido().remove(filaSeleccionada);
            
            try {
				borrarBaseDatos(c.getId_Curso());
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            modelo.fireTableDataChanged();
        } else if(e.getSource() == botonRegistrar) {
            
        	int id = Integer.parseInt(idCursoText.getText());
            int cod_mat = Integer.parseInt(codMatText.getText());
            int leg_Prof = Integer.parseInt(legProfText.getText());
            String m = materiaText.getText();
            float precio = Float.parseFloat(precioText.getText());
            int cupo = Integer.parseInt(cupoText.getText());
            
            Curso c = new Curso(id, cod_mat, leg_Prof, m, precio, cupo);
            
            try {
				insertarBaseDatos(c);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            idCursoText.setText(null);
            codMatText.setText(null);
            legProfText.setText(null);
            materiaText.setText(null);
            precioText.setText(null);
            cupoText.setText(null);

            modelo.getContenido().add(c);

            modelo.fireTableDataChanged();
        	
        }
		
	}
	
	public void agregar() {
		JFrame frame = new JFrame("Registrar Curso");
        frame.setSize(350, 350);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelRegistro(panel);

        frame.setVisible(true);
	}
	
	public void armarPanelRegistro(JPanel panel) {
        panel.setLayout(null);

        JLabel idCursoLabel = new JLabel("Id_Curso");
        idCursoLabel.setBounds(10, 10, 80, 25);
        panel.add(idCursoLabel);

        idCursoText = new JTextField(20);
        idCursoText.setBounds(100, 10, 160, 25);
        panel.add(idCursoText);

        JLabel codMateriaLabel = new JLabel("Cod_Materia");
        codMateriaLabel.setBounds(10, 40, 80, 25);
        panel.add(codMateriaLabel);

        codMatText = new JTextField(20);
        codMatText.setBounds(100, 40, 160, 25);
        panel.add(codMatText);

        JLabel legProfLabel = new JLabel("Leg_Profesor");
        legProfLabel.setBounds(10, 80, 80, 25);
        panel.add(legProfLabel);

        legProfText = new JTextField(20);
        legProfText.setBounds(100, 80, 160, 25);
        panel.add(legProfText);
        
        JLabel materiaLabel = new JLabel("Materia");
        materiaLabel.setBounds(10, 120, 80, 25);
        panel.add(materiaLabel);
        
        materiaText = new JTextField(20);
        materiaText.setBounds(100, 120, 160, 25);
        panel.add(materiaText);
        
        JLabel precioLabel = new JLabel("Precio");
        precioLabel.setBounds(10, 160, 80, 25);
        panel.add(precioLabel);

        precioText = new JTextField(20);
        precioText.setBounds(100, 160, 160, 25);
        panel.add(precioText);
        
        JLabel cupoLabel = new JLabel("Cupo");
        cupoLabel.setBounds(10, 200, 80, 25);
        panel.add(cupoLabel);

        cupoText = new JTextField(20);
        cupoText.setBounds(100, 200, 160, 25);
        panel.add(cupoText);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
        botonRegistrar.setBounds(100, 240, 100, 25);
        panel.add(botonRegistrar);

    }
	
	public void insertarBaseDatos(Curso c) throws DAOException {
		CursoDAO dao = new CursoDAOH2Impl();
        Curso aInsertar = new Curso();
        aInsertar.setId_Curso(c.getId_Curso());;
        aInsertar.setCod_materia(c.getCod_materia());
        aInsertar.setLeg_profe(c.getLeg_profe());
        aInsertar.setNombreMateria(c.getNombreMateria());
        aInsertar.setPrecio(c.getPrecio());
        aInsertar.setCupo(c.getCupo());
        dao.crearCurso(aInsertar);
	}
	
	public void borrarBaseDatos(int idCurso) throws DAOException {
		CursoDAO dao = new CursoDAOH2Impl();
		dao.borrarCurso(idCurso);
	}
	

}
