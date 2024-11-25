package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JList;

public class TelaListagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinasCodigo;
	private JTextField tfProfessoresCpf;
	private JTextField tfCursosCodigo;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagem frame = new TelaListagem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public TelaListagem() throws PropertyVetoException {
		setTitle("Sistema de Chamadas Públicas - Visualização");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 604, 419);
		contentPane.add(tabbedPane);
		
		// Painel - Listagem de Cursos
		JPanel tabListaCursos = new JPanel();
		tabListaCursos.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Cursos", null, tabListaCursos, null);
		tabListaCursos.setLayout(null);
		
		JPanel containerCursos = new JPanel();
		containerCursos.setBounds(39, 11, 523, 369);
		containerCursos.setLayout(null);
		containerCursos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		tabListaCursos.add(containerCursos);
		
		JPanel containerCursosBusca = new JPanel();
		containerCursosBusca.setLayout(null);
		containerCursosBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerCursosBusca.setBounds(0, 0, 523, 48);
		containerCursos.add(containerCursosBusca);
		
		JLabel lblCursosCodigo = new JLabel("Digite o código:");
		lblCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursosCodigo.setBounds(10, 11, 112, 30);
		containerCursosBusca.add(lblCursosCodigo);
		
		tfCursosCodigo = new JTextField();
		tfCursosCodigo.setToolTipText("");
		tfCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursosCodigo.setColumns(10);
		tfCursosCodigo.setBounds(122, 11, 301, 30);
		containerCursosBusca.add(tfCursosCodigo);
		
		JButton btnCursosBuscar = new JButton("Buscar");
		btnCursosBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursosBuscar.setBounds(420, 11, 93, 30);
		containerCursosBusca.add(btnCursosBuscar);
		
		JTextArea taCursosListaCursos = new JTextArea();
		taCursosListaCursos.setBounds(10, 57, 503, 301);
		containerCursos.add(taCursosListaCursos);
		
		// Painel - Listagem de  Disciplinas
		JPanel tabListaDisciplinas = new JPanel();
		tabListaDisciplinas.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Disciplinas", null, tabListaDisciplinas, null);
		tabListaDisciplinas.setLayout(null);
		
		JPanel containerDisciplinas = new JPanel();
		containerDisciplinas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerDisciplinas.setBounds(39, 11, 523, 369);
		tabListaDisciplinas.add(containerDisciplinas);
		containerDisciplinas.setLayout(null);
		
		JPanel containerDisciplinasBusca = new JPanel();
		containerDisciplinasBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerDisciplinasBusca.setLayout(null);
		containerDisciplinasBusca.setBounds(0, 0, 523, 48);
		containerDisciplinas.add(containerDisciplinasBusca);
		
		JLabel lblDisciplinasCodigo = new JLabel("Digite o código:");
		lblDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinasCodigo.setBounds(10, 11, 112, 30);
		containerDisciplinasBusca.add(lblDisciplinasCodigo);
		
		tfDisciplinasCodigo = new JTextField();
		tfDisciplinasCodigo.setToolTipText("");
		tfDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinasCodigo.setColumns(10);
		tfDisciplinasCodigo.setBounds(122, 11, 301, 30);
		containerDisciplinasBusca.add(tfDisciplinasCodigo);
		
		JButton btnDisciplinasBuscar = new JButton("Buscar");
		btnDisciplinasBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinasBuscar.setBounds(420, 11, 93, 30);
		containerDisciplinasBusca.add(btnDisciplinasBuscar);
		
		JTextArea taDisciplinasListaDisciplinas = new JTextArea();
		taDisciplinasListaDisciplinas.setBounds(10, 57, 503, 301);
		containerDisciplinas.add(taDisciplinasListaDisciplinas);
		
		
		// Painel - Listagem de Professores
		JPanel tabListaProfessores = new JPanel();
		tabListaProfessores.setLayout(null);
		tabListaProfessores.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Professores", null, tabListaProfessores, null);
		JPanel containerProfessores = new JPanel();
		containerProfessores.setLayout(null);
		containerProfessores.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerProfessores.setBounds(39, 11, 523, 369);
		tabListaProfessores.add(containerProfessores);
		JPanel containerProfessoresBusca = new JPanel();
		containerProfessoresBusca.setLayout(null);
		containerProfessoresBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerProfessoresBusca.setBounds(0, 0, 523, 48);
		containerProfessores.add(containerProfessoresBusca);
		JLabel lblProfessoresCpf = new JLabel("Digite o CPF:");
		lblProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessoresCpf.setBounds(10, 11, 112, 30);
		containerProfessoresBusca.add(lblProfessoresCpf);
		tfProfessoresCpf = new JTextField();
		tfProfessoresCpf.setToolTipText("");
		tfProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessoresCpf.setColumns(10);
		tfProfessoresCpf.setBounds(122, 11, 301, 30);
		containerProfessoresBusca.add(tfProfessoresCpf);
		JButton btnProfessoresBuscar = new JButton("Buscar");
		btnProfessoresBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessoresBuscar.setBounds(420, 11, 93, 30);
		containerProfessoresBusca.add(btnProfessoresBuscar);
		JTextArea taProfessoresListaProfessores = new JTextArea();
		taProfessoresListaProfessores.setBounds(10, 57, 503, 301);
		containerProfessores.add(taProfessoresListaProfessores);
		
	}
}
