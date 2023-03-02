package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.GestorBBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClienteJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDireccion;
	private JTextField textLocalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateClienteJDialog dialog = new UpdateClienteJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateClienteJDialog() {
		setBounds(100, 100, 533, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblIntroduceDni = new JLabel("INTRODUCE DNI:");
		lblIntroduceDni.setBounds(60, 39, 103, 23);
		contentPanel.add(lblIntroduceDni);

		textDni = new JTextField();
		textDni.setBounds(188, 40, 86, 20);
		contentPanel.add(textDni);
		textDni.setColumns(10);

		JLabel lblNombre = new JLabel("NOMBRE :");
		lblNombre.setBounds(60, 73, 64, 14);
		contentPanel.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(188, 70, 86, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("APELLIDOS :");
		lblApellidos.setBounds(60, 104, 64, 14);
		contentPanel.add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setBounds(188, 101, 86, 20);
		contentPanel.add(textApellidos);
		textApellidos.setColumns(10);

		JLabel lblDireccion = new JLabel("DIRECCIÓN :");
		lblDireccion.setBounds(60, 135, 64, 14);
		contentPanel.add(lblDireccion);

		textDireccion = new JTextField();
		textDireccion.setBounds(188, 132, 86, 20);
		contentPanel.add(textDireccion);
		textDireccion.setColumns(10);

		JLabel lblLocalidad = new JLabel("LOCALIDAD :");
		lblLocalidad.setBounds(60, 166, 64, 14);
		contentPanel.add(lblLocalidad);

		textLocalidad = new JTextField();
		textLocalidad.setBounds(188, 163, 86, 20);
		contentPanel.add(textLocalidad);
		textLocalidad.setColumns(10);

		JButton btnCargar = new JButton("BUSCAR");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorBBDD gb = new GestorBBDD();
				gb.conectar();
				try {
					Cliente cliente = gb.getCliente(textDni.getText());
					textNombre.setText(cliente.getNombre());
					textApellidos.setText(cliente.getApellidos());
					textDireccion.setText(cliente.getDireccion());
					textLocalidad.setText(cliente.getLocalidad());

					gb.cerrar();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnCargar.setBounds(339, 39, 103, 23);
		contentPanel.add(btnCargar);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorBBDD gb = new GestorBBDD();
				Cliente cliente = new Cliente();
				cliente.setNombre(textNombre.getText());
				cliente.setApellidos(textApellidos.getText());
				cliente.setDireccion(textDireccion.getText());
				cliente.setLocalidad(textLocalidad.getText());
				gb.conectar();
				try {
					gb.modificarCliente(cliente, textDni.getText());
					JOptionPane.showMessageDialog(null, "Cliente modificado");
					
					gb.cerrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnModificar.setBounds(339, 100, 103, 23);
		contentPanel.add(btnModificar);

		JButton btnELIMINAR = new JButton("ELIMINAR");
		btnELIMINAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorBBDD gb = new GestorBBDD();
				gb.conectar();
				try {
					gb.eliminarCliente(textDni.getText());
					JOptionPane.showMessageDialog(null,"Cliente eliminado!");
					gb.cerrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnELIMINAR.setBounds(339, 162, 103, 23);
		contentPanel.add(btnELIMINAR);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnReset.setBounds(339, 214, 103, 23);
		contentPanel.add(btnReset);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
