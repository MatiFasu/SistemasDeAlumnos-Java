package presentacion;

import servicio.TablaInicio;

import javax.swing.*;

import datos.TableManager;

public class Main {


	
	public static void main(String[] args) {
		
		JFrame j = new JFrame("Inicio");
        j.getContentPane().add(new TablaInicio());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        //j.setVisible(true);
		
		//TableManager tm = new TableManager();
        //tm.createAlumnoTable();
        //tm.createCursoTable();
        //tm.createInscripcionTable();
        //tm.createNotasTable();
		//tm.dropNotasTable();
		
		
        /*
        select * from alumnos;
		select * from cursos;
		select * from inscripcion;
		select * from notas;
        */

	}
	
	
}
