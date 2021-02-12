package MiSoftwarePruebas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiSoftwarePruebas implements WindowListener, ActionListener
{// Ventana Principal
	Frame ventana = new Frame("Mi Software");

	// Ventana Alta de Cliente
	Frame frmAltaCliente = new Frame("Alta de Cliente");
	Label lblNombreCliente = new Label("Nombre:");
	TextField txtNombreCliente = new TextField(20);
	Label lblCifCliente = new Label("Cif:");
	TextField txtCifCliente = new TextField(20);
	Button btnAltaCliente = new Button("Alta");
	Button btnCancelarAltaCliente = new Button("Cancelar");

	Dialog dlgConfirmarAltaCliente = new Dialog(frmAltaCliente, "Alta Cliente", true);
	Label lblMensajeAltaCliente = new Label("Alta de Cliente Correcta");

	// Ventana Consulta de Clientes
	Frame frmConsultaClientes = new Frame("Consulta Clientes");
	TextArea listadoClientes = new TextArea(4, 30);
	Button btnPdfClientes = new Button("PDF");

	// Ventana de Borrado de Cliente
	Frame frmBajaCliente = new Frame("Baja de Cliente");
	Label lblMensajeBajaCliente = new Label("Seleccionar el cliente:");
	Choice choClientes = new Choice();
	Button btnBorrarCliente = new Button("Borrar");
	Dialog dlgSeguroCliente = new Dialog(frmBajaCliente, "¿Seguro?", true);
	Label lblSeguroCliente = new Label("¿Está seguro de borrar?");
	Button btnSiSeguroCliente = new Button("Sí");
	Button btnNoSeguroCliente = new Button("No");
	Dialog dlgConfirmacionBajaCliente = new Dialog(frmBajaCliente, "Baja Cliente", true);
	Label lblConfirmacionBajaCliente = new Label("Baja de cliente correcta");

	MenuBar mnBar = new MenuBar();

	Menu mnuClientes = new Menu("Clientes");
	MenuItem mniAltaCliente = new MenuItem("Alta");
	MenuItem mniBajaCliente = new MenuItem("Baja");
	MenuItem mniModificacionCliente = new MenuItem("Modificación");
	MenuItem mniConsultaCliente = new MenuItem("Consulta");

	Menu mnuEmpleados = new Menu("Empleados");
	MenuItem mniAltaEmpleado = new MenuItem("Alta");
	MenuItem mniBajaEmpleado = new MenuItem("Baja");
	MenuItem mniModificacionEmpleado = new MenuItem("Modificación");
	MenuItem mniConsultaEmpleado = new MenuItem("Consulta");

	Menu mnuProyectos = new Menu("Proyectos");
	MenuItem mniAltaProyecto = new MenuItem("Alta");
	MenuItem mniBajaProyecto = new MenuItem("Baja");
	MenuItem mniModificacionProyecto = new MenuItem("Modificación");
	MenuItem mniConsultaProyecto= new MenuItem("Consulta");

	Menu mnuAsignaciones = new Menu("Asignaciones");
	MenuItem mniAltaAsignacion = new MenuItem("Alta");
	MenuItem mniBajaAsignacion = new MenuItem("Baja");
	MenuItem mniModificacionAsignacion = new MenuItem("Modificación");
	MenuItem mniConsultaAsignacion = new MenuItem("Consulta");

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/misoftware?serverTimezone=UTC";
	String login = "root";
	String password = "Studium2020;";
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public MiSoftwarePruebas()
	{
	ventana.setLayout(new FlowLayout());
	mniAltaCliente.addActionListener(this);
	mnuClientes.add(mniAltaCliente);
	mniBajaCliente.addActionListener(this);
	mnuClientes.add(mniBajaCliente);
	mnuClientes.add(mniModificacionCliente);
	mniConsultaCliente.addActionListener(this);
	mnuClientes.add(mniConsultaCliente);
	mnBar.add(mnuClientes);

	mnuEmpleados.add(mniAltaEmpleado);
	mnuEmpleados.add(mniBajaEmpleado);
	mnuEmpleados.add(mniModificacionEmpleado);
	mnuEmpleados.add(mniConsultaEmpleado);
	mnBar.add(mnuEmpleados);

	mnuProyectos.add(mniAltaProyecto);
	mnuProyectos.add(mniBajaProyecto);
	mnuProyectos.add(mniModificacionProyecto);
	mnuProyectos.add(mniConsultaProyecto);
	mnBar.add(mnuProyectos);

	mnuAsignaciones.add(mniAltaAsignacion);
	mnuAsignaciones.add(mniBajaAsignacion);
	mnuAsignaciones.add(mniModificacionAsignacion);
	mnuAsignaciones.add(mniConsultaAsignacion);
	mnBar.add(mnuAsignaciones);

	ventana.setMenuBar(mnBar);

	ventana.setSize(320,160);
	ventana.setResizable(false);
	ventana.setLocationRelativeTo(null);
	ventana.addWindowListener(this);
	ventana.setVisible(true);
	}

	public static void main(String[] args)
	{
	new MiSoftwarePruebas();
	}

	public void windowActivated(WindowEvent we) {}
	public void windowClosed(WindowEvent we) {}
	public void windowClosing(WindowEvent we)
	{
	if(frmAltaCliente.isActive())
	{
	frmAltaCliente.setVisible(false);
	}
	else if(dlgConfirmarAltaCliente.isActive())
	{
	txtNombreCliente.setText("");
	txtCifCliente.setText("");
	txtNombreCliente.requestFocus();
	dlgConfirmarAltaCliente.setVisible(false);
	}
	else if(frmConsultaClientes.isActive())
	{
	frmConsultaClientes.setVisible(false);
	}
	else if(frmBajaCliente.isActive())
	{
	frmBajaCliente.setVisible(false);
	}
	else if(dlgSeguroCliente.isActive())
	{
	dlgSeguroCliente.setVisible(false);
	}
	else if(dlgConfirmacionBajaCliente.isActive())
	{
	dlgConfirmacionBajaCliente.setVisible(false);
	dlgSeguroCliente.setVisible(false);
	frmBajaCliente.setVisible(false);
	}
	else
	{
	System.exit(0);
	}
	}
	public void windowDeactivated(WindowEvent we) {}
	public void windowDeiconified(WindowEvent we) {}
	public void windowIconified(WindowEvent we) {}
	public void windowOpened(WindowEvent we) {}
	public void actionPerformed(ActionEvent evento)
	{
	if(evento.getSource().equals(mniAltaCliente))
	{
	ventanaAltaCliente();
	}
	else if(evento.getSource().equals(btnAltaCliente))
	{
	connection = conectar();
	try
	{
	//Crear una sentencia
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY);
	//Crear un objeto ResultSet para guardar lo obtenido
	//y ejecutar la sentencia SQL
	if(((txtNombreCliente.getText().length())!=0)
	&&((txtCifCliente.getText().length())!=0))
	{
	sentencia = "INSERT INTO clientes VALUES (null, '"
	+ txtNombreCliente.getText()
	+ "', '" +txtCifCliente.getText() + "')";
	statement.executeUpdate(sentencia);
	lblMensajeAltaCliente.setText("Alta de Cliente Correcta");
	}
	else
	{
	lblMensajeAltaCliente.setText("Faltan datos");
	}
	}
	catch (SQLException sqle)
	{
	lblMensajeAltaCliente.setText("Error en ALTA");
	}
	finally
	{
	ventanaConfirmarAltaCliente();
	}
	}
	else if(evento.getSource().equals(mniConsultaCliente))
	{
	frmConsultaClientes.setLayout(new FlowLayout());
	// Conectar
	connection = conectar();
	// Hacer un SELECT * FROM clientes
	sentencia = "SELECT * FROM clientes";
	// La información está en ResultSet
	// Recorrer el RS y por cada registro,
	// meter una línea en el TextArea
	try
	{
	//Crear una sentencia
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY);
	//Crear un objeto ResultSet para guardar lo obtenido
	//y ejecutar la sentencia SQL
	rs = statement.executeQuery(sentencia);
	listadoClientes.selectAll();
	listadoClientes.setText("");
	listadoClientes.append("id\tNombre\tCif\n");
	while(rs.next())
	{
	listadoClientes.append(rs.getInt("idCliente")
	+"\t"+rs.getString("nombreCliente")
	+"\t"+rs.getString("cifCliente")
	+"\n");
	}
	}
	catch (SQLException sqle)
	{
	lblMensajeAltaCliente.setText("Error en ALTA");
	}
	finally
	{

	}
	listadoClientes.setEditable(false);
	ventanaConsultaCliente();
	}
	else if(evento.getSource().equals(mniBajaCliente))
	{
	frmBajaCliente.setLayout(new FlowLayout());
	frmBajaCliente.add(lblMensajeBajaCliente);
	// Rellenar el Choice
	// Conectar
	connection = conectar();
	// Hacer un SELECT * FROM clientes
	sentencia = "SELECT * FROM clientes";
	try
	{
	//Crear una sentencia
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY);
	//Crear un objeto ResultSet para guardar lo obtenido
	//y ejecutar la sentencia SQL
	rs = statement.executeQuery(sentencia);
	choClientes.removeAll();
	while(rs.next())
	{
	choClientes.add(rs.getInt("idCliente")
	+"-"+rs.getString("nombreCliente")
	+"-"+rs.getString("cifCliente"));
	}
	}
	catch (SQLException sqle)
	{
	lblMensajeAltaCliente.setText("Error en ALTA");
	}
	ventanaBajaCliente();
	}
	else if(evento.getSource().equals(btnBorrarCliente))
	{
	dialogoSeguroCliente();
	}
	else if(evento.getSource().equals(btnNoSeguroCliente))
	{
	dlgSeguroCliente.setVisible(false);
	}
	else if(evento.getSource().equals(btnSiSeguroCliente))
	{
	// Conectar
	connection = conectar();
	// Hacer un DELETE FROM clientes WHERE idCliente = X
	String[] elegido = choClientes.getSelectedItem().split("-");
	sentencia = "DELETE FROM clientes WHERE idCliente = "+elegido[0];
	try
	{
	//Crear una sentencia
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY);
	statement.executeUpdate(sentencia);
	lblConfirmacionBajaCliente.setText("Baja de Cliente Correcta");
	}
	catch (SQLException sqle)
	{
	lblConfirmacionBajaCliente.setText("Error en Baja");
	}
	finally
	{
	dialogoConfirmacionBajaCliente();
	}
	}
	}
	//Aquí aparecen los métodos extraidos
	private void ventanaConsultaCliente()
	{
		frmConsultaClientes.add(listadoClientes);
		frmConsultaClientes.add(btnPdfClientes);

		frmConsultaClientes.setSize(250,140);
		frmConsultaClientes.setResizable(false);
		frmConsultaClientes.setLocationRelativeTo(null);
		frmConsultaClientes.addWindowListener(this);
		frmConsultaClientes.setVisible(true);
	}
	//extrcción del método dialogoConfirmacionBajaCliente()
	private void dialogoConfirmacionBajaCliente()
	{
		dlgConfirmacionBajaCliente.setLayout(new FlowLayout());
		dlgConfirmacionBajaCliente.setBackground(Color.RED);
		dlgConfirmacionBajaCliente.addWindowListener(this);
		dlgConfirmacionBajaCliente.setSize(150,110);
		dlgConfirmacionBajaCliente.setResizable(false);
		dlgConfirmacionBajaCliente.setLocationRelativeTo(null);
		dlgConfirmacionBajaCliente.add(lblConfirmacionBajaCliente);
		dlgConfirmacionBajaCliente.setVisible(true);
	}
	//extrcción del método dialogoSeguroCliente()
	private void dialogoSeguroCliente()
	{
		dlgSeguroCliente.setLayout(new FlowLayout());
		dlgSeguroCliente.addWindowListener(this);
		dlgSeguroCliente.setSize(150,100);
		dlgSeguroCliente.setResizable(false);
		dlgSeguroCliente.setLocationRelativeTo(null);
		dlgSeguroCliente.add(lblSeguroCliente);
		btnSiSeguroCliente.addActionListener(this);
		dlgSeguroCliente.add(btnSiSeguroCliente);
		btnNoSeguroCliente.addActionListener(this);
		dlgSeguroCliente.add(btnNoSeguroCliente);
		dlgSeguroCliente.setVisible(true);
	}
	//extrcción del método ventanaConfirmarAltaCliente()
	private void ventanaConfirmarAltaCliente()
	{
		dlgConfirmarAltaCliente.setLayout(new FlowLayout());
		dlgConfirmarAltaCliente.setBackground(Color.getHSBColor(73,210,77));
		dlgConfirmarAltaCliente.addWindowListener(this);
		dlgConfirmarAltaCliente.setSize(160,100);
		dlgConfirmarAltaCliente.setResizable(false);
		dlgConfirmarAltaCliente.setLocationRelativeTo(null);
		dlgConfirmarAltaCliente.add(lblMensajeAltaCliente);
		dlgConfirmarAltaCliente.setVisible(true);
	}
	//extrcción del método ventanaAltaCliente()
	private void ventanaAltaCliente()
	{
		frmAltaCliente.setLayout(new FlowLayout());
		frmAltaCliente.add(lblNombreCliente);
		txtNombreCliente.setText("");
		frmAltaCliente.add(txtNombreCliente);
		frmAltaCliente.add(lblCifCliente);
		txtCifCliente.setText("");
		frmAltaCliente.add(txtCifCliente);
		btnAltaCliente.addActionListener(this);
		frmAltaCliente.add(btnAltaCliente);
		btnCancelarAltaCliente.addActionListener(this);
		frmAltaCliente.add(btnCancelarAltaCliente);

		frmAltaCliente.setSize(260,130);
		frmAltaCliente.setResizable(false);
		frmAltaCliente.setLocationRelativeTo(null);
		frmAltaCliente.addWindowListener(this);
		txtNombreCliente.requestFocus();
		frmAltaCliente.setVisible(true);
	}
	//método ventanaBajaCliente()
	private void ventanaBajaCliente()
	{
		frmBajaCliente.add(choClientes);
		btnBorrarCliente.addActionListener(this);
		frmBajaCliente.add(btnBorrarCliente);

		frmBajaCliente.setSize(250,140);
		frmBajaCliente.setResizable(false);
		frmBajaCliente.setLocationRelativeTo(null);
		frmBajaCliente.addWindowListener(this);
		frmBajaCliente.setVisible(true);
	}
	//Hasta aquí la extracción de los métodos
	public Connection conectar()
	{
	try
	{
	//Cargar los controladores para el acceso a la BD
	Class.forName(driver);
	//Establecer la conexión con la BD Empresa
	connection = DriverManager.getConnection(url, login, password);
	}
	catch (ClassNotFoundException cnfe)
	{
	System.out.println("Error 1-"+cnfe.getMessage());
	}
	catch (SQLException sqle)
	{
	System.out.println("Error 2-"+sqle.getMessage());
	}
	return connection;
	}
	}