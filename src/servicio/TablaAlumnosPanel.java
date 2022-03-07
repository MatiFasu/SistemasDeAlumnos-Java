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

import datos.AlumnoDAO;
import datos.AlumnoDAOH2Impl;
import datos.DAOException;
import entidades.Alumno;
import servicio.AlumnoTableModel;


public class TablaAlumnosPanel extends JPanel implements ActionListener {
	
	private JTable tablaAlumno;
    private AlumnoTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonRegistrar;

    private JTextField nombreText, apellidoText, legText, dniText;
    
    private AlumnoDAOH2Impl alumnoDao;

    public TablaAlumnosPanel() throws DAOException {
        super();
        armarPanelAlumnos();
    }
    
    public void armarPanelAlumnos() throws DAOException {
    	this.setLayout(new FlowLayout());

        modelo = new AlumnoTableModel();
        tablaAlumno = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaAlumno);
        this.add(scrollPaneParaTabla);
        
        botonBorrar = new JButton("BORRAR");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

        botonAgregar = new JButton("CARGAR");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);
        
        alumnoDao = new AlumnoDAOH2Impl();
        List<Alumno> lista = alumnoDao.listaTodosLosAlumnos();

        modelo.setContenido(lista);
        
        modelo.fireTableDataChanged();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botonAgregar) {
            agregar();
        } else if(e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaAlumno.getSelectedRow();

            Alumno a = this.modelo.getContenido().get(filaSeleccionada);

            System.out.println(a);

            this.modelo.getContenido().remove(filaSeleccionada);
            
            try {
				borrarBaseDatos(a.getLegAlum());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}

            modelo.fireTableDataChanged();
        } else if(e.getSource() == botonRegistrar) {
            
        	int l = Integer.parseInt(legText.getText());
            int d = Integer.parseInt(dniText.getText());
            String nom = nombreText.getText();
            String ape = apellidoText.getText();
            
            Alumno a = new Alumno(l,nom,ape,d);
            
            try {
				insertarBaseDatos(a);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
            
            legText.setText(null);
            nombreText.setText(null);
            apellidoText.setText(null);
            dniText.setText(null);

            modelo.getContenido().add(a);

            modelo.fireTableDataChanged();
        	
        }
		
	}
	
	public void agregar() {
		JFrame frame = new JFrame("Registrar Alumno");
        frame.setSize(300, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        armarPanelRegistro(panel);

        frame.setVisible(true);
	}
	
	public void armarPanelRegistro(JPanel panel) {
        panel.setLayout(null);

        JLabel legLabel = new JLabel("Legajo");
        legLabel.setBounds(10, 10, 80, 25);
        panel.add(legLabel);

        legText = new JTextField(20);
        legText.setBounds(100, 10, 160, 25);
        panel.add(legText);

        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(10, 40, 80, 25);
        panel.add(nombreLabel);

        nombreText = new JTextField(20);
        nombreText.setBounds(100, 40, 160, 25);
        panel.add(nombreText);

        JLabel apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setBounds(10, 80, 80, 25);
        panel.add(apellidoLabel);

        apellidoText = new JTextField(20);
        apellidoText.setBounds(100, 80, 160, 25);
        panel.add(apellidoText);

        JLabel dniLabel = new JLabel("Dni");
        dniLabel.setBounds(10, 120, 80, 25);
        panel.add(dniLabel);

        dniText = new JTextField(20);
        dniText.setBounds(100, 120, 160, 25);
        panel.add(dniText);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
        botonRegistrar.setBounds(100, 160, 100, 25);
        panel.add(botonRegistrar);

    }
	
	public void insertarBaseDatos(Alumno a) throws DAOException {
		AlumnoDAO dao = new AlumnoDAOH2Impl();
        Alumno aInsertar = new Alumno();
        aInsertar.setLegAlum(a.getLegAlum());
        aInsertar.setNombre(a.getNombre());
        aInsertar.setApellido(a.getApellido());
        aInsertar.setDni(a.getDni());
        dao.crearAlumno(aInsertar);
	}
	
	public void borrarBaseDatos(int leg) throws DAOException {
		AlumnoDAO dao = new AlumnoDAOH2Impl();
		dao.borrarAlumno(leg);
	}

}
