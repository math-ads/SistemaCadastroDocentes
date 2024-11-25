/*package view;

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

import controller.CursoController;
import controller.LoginController;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaHorario;
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorPontuacao;
	private JTextField tfCursoNome;
	private JTextField tfCursoCodigo;
	private JTextField tfListaDisciplinasCodigo;
	private JTextField tfInscricaoCodigoDisciplina;
	private JTextField tfListaProfessoresCpf;
	private JTextField tfListaCursosCodigo;
	private JTextField tfInscricaoCodigoProcesso;
	private JTextField tfInscricaoCpfProfessor;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public TelaPrincipal() throws PropertyVetoException {
		setTitle("Sistema de Chamadas Públicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 604, 419);
		contentPane.add(tabbedPane);
		
		// Painel - Inscrição
		JPanel tabInscricao = new JPanel();
		tabInscricao.setToolTipText("inscrição em processo seletivo");
		tabInscricao.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Inscrição", null, tabInscricao, null);
		tabInscricao.setLayout(null);
		
		JPanel containerInscricao = new JPanel();
		containerInscricao.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerInscricao.setBounds(43, 11, 513, 369);
		tabInscricao.add(containerInscricao);
		containerInscricao.setLayout(null);
		
		JLabel lblInscricaoCodigoDisciplina = new JLabel("Código da disciplina:");
		lblInscricaoCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricaoCodigoDisciplina.setBounds(26, 82, 150, 30);
		containerInscricao.add(lblInscricaoCodigoDisciplina);
		
		tfInscricaoCodigoDisciplina = new JTextField();
		tfInscricaoCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfInscricaoCodigoDisciplina.setColumns(10);
		tfInscricaoCodigoDisciplina.setBounds(176, 83, 197, 30);
		containerInscricao.add(tfInscricaoCodigoDisciplina);
		
		JLabel lblInscricaoCodigoProcesso = new JLabel("Código do processo:");
		lblInscricaoCodigoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricaoCodigoProcesso.setBounds(26, 136, 150, 30);
		containerInscricao.add(lblInscricaoCodigoProcesso);
		
		tfInscricaoCodigoProcesso = new JTextField();
		tfInscricaoCodigoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfInscricaoCodigoProcesso.setColumns(10);
		tfInscricaoCodigoProcesso.setBounds(176, 137, 197, 30);
		containerInscricao.add(tfInscricaoCodigoProcesso);
		
		JTextArea taInscricaoListaDisciplinasOuProcessos = new JTextArea();
		taInscricaoListaDisciplinasOuProcessos.setBounds(26, 195, 462, 123);
		containerInscricao.add(taInscricaoListaDisciplinasOuProcessos);
		
		JButton btnInscricaoCancelar = new JButton("Cancelar");
		btnInscricaoCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoCancelar.setBackground(UIManager.getColor("Button.background"));
		btnInscricaoCancelar.setBounds(299, 335, 89, 23);
		containerInscricao.add(btnInscricaoCancelar);
		
		JButton btnInscricaoCadastrar = new JButton("Salvar");
		btnInscricaoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoCadastrar.setBackground(Color.WHITE);
		btnInscricaoCadastrar.setBounds(399, 335, 89, 23);
		containerInscricao.add(btnInscricaoCadastrar);
		
		JButton btnInscricaoListarDisciplinas = new JButton("Procurar");
		btnInscricaoListarDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoListarDisciplinas.setBounds(396, 82, 93, 30);
		containerInscricao.add(btnInscricaoListarDisciplinas);
		
		JButton btnInscricaoListarProcessos = new JButton("Procurar");
		btnInscricaoListarProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoListarProcessos.setBounds(396, 136, 93, 30);
		containerInscricao.add(btnInscricaoListarProcessos);
		
		JLabel lblInscricaoCpfProfessor = new JLabel("CPF:");
		lblInscricaoCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricaoCpfProfessor.setBounds(26, 27, 66, 30);
		containerInscricao.add(lblInscricaoCpfProfessor);
		
		tfInscricaoCpfProfessor = new JTextField();
		tfInscricaoCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfInscricaoCpfProfessor.setColumns(10);
		tfInscricaoCpfProfessor.setBounds(90, 27, 283, 30);
		containerInscricao.add(tfInscricaoCpfProfessor);
		
		JPanel tabListaCursos = new JPanel();
		tabListaCursos.setLayout(null);
		tabListaCursos.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Cursos", null, tabListaCursos, null);
		
		JPanel containerListagem_2 = new JPanel();
		containerListagem_2.setLayout(null);
		containerListagem_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerListagem_2.setBounds(39, 11, 523, 369);
		tabListaCursos.add(containerListagem_2);
		
		JPanel containerBusca_2 = new JPanel();
		containerBusca_2.setLayout(null);
		containerBusca_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerBusca_2.setBounds(0, 0, 523, 48);
		containerListagem_2.add(containerBusca_2);
		
		JLabel lblListaCursosCodigo = new JLabel("Digite o código:");
		lblListaCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaCursosCodigo.setBounds(10, 11, 112, 30);
		containerBusca_2.add(lblListaCursosCodigo);
		
		tfListaCursosCodigo = new JTextField();
		tfListaCursosCodigo.setToolTipText("");
		tfListaCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfListaCursosCodigo.setColumns(10);
		tfListaCursosCodigo.setBounds(122, 11, 301, 30);
		containerBusca_2.add(tfListaCursosCodigo);
		
		JButton btnListaCursosBuscar = new JButton("Buscar");
		btnListaCursosBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaCursosBuscar.setBounds(420, 11, 93, 30);
		containerBusca_2.add(btnListaCursosBuscar);
		
		JTextArea taListaCursos = new JTextArea();
		taListaCursos.setBounds(10, 57, 503, 301);
		containerListagem_2.add(taListaCursos);
		
		JPanel tabCurso = new JPanel();
		tabCurso.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Cadastrar Curso", null, tabCurso, null);
		tabCurso.setLayout(null);
		
		JPanel containerCurso = new JPanel();
		containerCurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerCurso.setBounds(44, 11, 507, 369);
		tabCurso.add(containerCurso);
		containerCurso.setLayout(null);
		
		JButton btnCursoCancelar = new JButton("Cancelar");
		btnCursoCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCursoCancelar.setBounds(293, 335, 89, 23);
		containerCurso.add(btnCursoCancelar);
		
		JButton btnCursoCadastrar = new JButton("Salvar");
		btnCursoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastrar.setBackground(Color.WHITE);
		btnCursoCadastrar.setBounds(393, 335, 89, 23);
		containerCurso.add(btnCursoCadastrar);
		
		JLabel lblDisciplinaNome1_1 = new JLabel("Nome:");
		lblDisciplinaNome1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaNome1_1.setBounds(20, 29, 66, 30);
		containerCurso.add(lblDisciplinaNome1_1);
		tfCursoNome = new JTextField();
		tfCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoNome.setColumns(10);
		tfCursoNome.setBounds(84, 29, 398, 30);
		containerCurso.add(tfCursoNome);
		JLabel lblDisciplinaCodigo_1 = new JLabel("Código:");
		lblDisciplinaCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodigo_1.setBounds(20, 89, 66, 30);
		containerCurso.add(lblDisciplinaCodigo_1);
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoCodigo.setColumns(10);
		tfCursoCodigo.setBounds(84, 88, 150, 30);
		containerCurso.add(tfCursoCodigo);
		JLabel lblrea_1 = new JLabel("Área:");
		lblrea_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblrea_1.setBounds(269, 89, 66, 30);
		containerCurso.add(lblrea_1);
		JComboBox cbCursoArea = new JComboBox();
		cbCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCursoArea.setEditable(true);
		cbCursoArea.setBounds(332, 89, 150, 30);
		containerCurso.add(cbCursoArea);
		
		// Painel - Lista Disciplinas
		JPanel tabListaDisciplinas = new JPanel();
		tabListaDisciplinas.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Disciplinas", null, tabListaDisciplinas, null);
		tabListaDisciplinas.setLayout(null);
		
		JPanel containerListagem = new JPanel();
		containerListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerListagem.setBounds(39, 11, 523, 369);
		tabListaDisciplinas.add(containerListagem);
		containerListagem.setLayout(null);
		
		JPanel containerBusca = new JPanel();
		containerBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerBusca.setLayout(null);
		containerBusca.setBounds(0, 0, 523, 48);
		containerListagem.add(containerBusca);
		
		JLabel lblListaDisciplinasCodigo = new JLabel("Digite o código:");
		lblListaDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaDisciplinasCodigo.setBounds(10, 11, 112, 30);
		containerBusca.add(lblListaDisciplinasCodigo);
		
		tfListaDisciplinasCodigo = new JTextField();
		tfListaDisciplinasCodigo.setToolTipText("");
		tfListaDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfListaDisciplinasCodigo.setColumns(10);
		tfListaDisciplinasCodigo.setBounds(122, 11, 301, 30);
		containerBusca.add(tfListaDisciplinasCodigo);
		
		JButton btnListaDisciplinasBuscar = new JButton("Buscar");
		btnListaDisciplinasBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaDisciplinasBuscar.setBounds(420, 11, 93, 30);
		containerBusca.add(btnListaDisciplinasBuscar);
		
		JTextArea taListaDisciplinas = new JTextArea();
		taListaDisciplinas.setBounds(10, 57, 503, 301);
		containerListagem.add(taListaDisciplinas);
		
		// Painel - Disciplina
		JPanel tabDisciplina = new JPanel();
		tabDisciplina.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Cadastrar Disciplina", null, tabDisciplina, "Cadastro de Disciplina");
		tabbedPane.setBackgroundAt(4, new Color(179, 16, 9));
		tabDisciplina.setLayout(null);
		JPanel containerDisciplina = new JPanel();
		containerDisciplina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerDisciplina.setBounds(44, 11, 507, 369);
		tabDisciplina.add(containerDisciplina);
		containerDisciplina.setLayout(null);
		JLabel lblDisciplinaNome1 = new JLabel("Nome:");
		lblDisciplinaNome1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaNome1.setBounds(21, 25, 66, 30);
		containerDisciplina.add(lblDisciplinaNome1);
		tfDisciplinaNome = new JTextField();
		tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaNome.setColumns(10);
		tfDisciplinaNome.setBounds(85, 25, 398, 30);
		containerDisciplina.add(tfDisciplinaNome);
		JLabel lblDisciplinaCurso = new JLabel("Curso:");
		lblDisciplinaCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCurso.setBounds(270, 85, 66, 30);
		containerDisciplina.add(lblDisciplinaCurso);
		JComboBox cbDisciplinaCursos = new JComboBox();
		cbDisciplinaCursos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDisciplinaCursos.setEditable(true);
		cbDisciplinaCursos.setBounds(333, 85, 150, 30);
		containerDisciplina.add(cbDisciplinaCursos);
		JLabel lblDisciplinaCodigo = new JLabel("Código:");
		lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodigo.setBounds(21, 85, 66, 30);
		containerDisciplina.add(lblDisciplinaCodigo);
		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCodigo.setColumns(10);
		tfDisciplinaCodigo.setBounds(85, 84, 150, 30);
		containerDisciplina.add(tfDisciplinaCodigo);
		JButton btnDisciplinaCadastrar = new JButton("Salvar");
		btnDisciplinaCadastrar.setBounds(394, 335, 89, 23);
		containerDisciplina.add(btnDisciplinaCadastrar);
		btnDisciplinaCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaCadastrar.setBackground(new Color(255, 255, 255));
		JButton btnDisciplinaCancelar = new JButton("Cancelar");
		btnDisciplinaCancelar.setBounds(294, 335, 89, 23);
		containerDisciplina.add(btnDisciplinaCancelar);
		btnDisciplinaCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaCancelar.setBackground(new Color(240, 240, 240));
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(21, 141, 66, 30);
		containerDisciplina.add(lblDia);
		
		JComboBox cbDisciplinaDias = new JComboBox();
		cbDisciplinaDias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDisciplinaDias.setModel(new DefaultComboBoxModel(new String[] {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"}));
		cbDisciplinaDias.setEditable(true);
		cbDisciplinaDias.setBounds(84, 141, 150, 30);
		containerDisciplina.add(cbDisciplinaDias);
		
		JLabel lblHorrio = new JLabel("Horário:");
		lblHorrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorrio.setBounds(269, 142, 66, 30);
		containerDisciplina.add(lblHorrio);
		tfDisciplinaHorario = new JTextField();
		tfDisciplinaHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaHorario.setColumns(10);
		tfDisciplinaHorario.setBounds(333, 141, 150, 30);
		containerDisciplina.add(tfDisciplinaHorario);
		
		JLabel lblDia_1 = new JLabel("Carga:");
		lblDia_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia_1.setBounds(21, 198, 66, 30);
		containerDisciplina.add(lblDia_1);
		
		JComboBox cbDisciplinaQuantHoras = new JComboBox();
		cbDisciplinaQuantHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDisciplinaQuantHoras.setModel(new DefaultComboBoxModel(new String[] {"1 hora", "2 horas", "3 horas", "4 horas", "5 horas", "6 horas"}));
		cbDisciplinaQuantHoras.setEditable(true);
		cbDisciplinaQuantHoras.setBounds(84, 198, 150, 30);
		containerDisciplina.add(cbDisciplinaQuantHoras);
		
		JPanel tabListaProfessores = new JPanel();
		tabListaProfessores.setLayout(null);
		tabListaProfessores.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Professores", null, tabListaProfessores, null);
		
		JPanel containerListagem_1 = new JPanel();
		containerListagem_1.setLayout(null);
		containerListagem_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerListagem_1.setBounds(39, 11, 523, 369);
		tabListaProfessores.add(containerListagem_1);
		
		JPanel containerBusca_1 = new JPanel();
		containerBusca_1.setLayout(null);
		containerBusca_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerBusca_1.setBounds(0, 0, 523, 48);
		containerListagem_1.add(containerBusca_1);
		
		JLabel lblListaProfessoresCpf = new JLabel("Digite o CPF:");
		lblListaProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaProfessoresCpf.setBounds(10, 11, 112, 30);
		containerBusca_1.add(lblListaProfessoresCpf);
		
		tfListaProfessoresCpf = new JTextField();
		tfListaProfessoresCpf.setToolTipText("");
		tfListaProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfListaProfessoresCpf.setColumns(10);
		tfListaProfessoresCpf.setBounds(122, 11, 301, 30);
		containerBusca_1.add(tfListaProfessoresCpf);
		
		JButton btnListaProfessoresBuscar = new JButton("Buscar");
		btnListaProfessoresBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaProfessoresBuscar.setBounds(420, 11, 93, 30);
		containerBusca_1.add(btnListaProfessoresBuscar);
		
		JTextArea taListaProfessores = new JTextArea();
		taListaProfessores.setBounds(10, 57, 503, 301);
		containerListagem_1.add(taListaProfessores);
		
		// Painel - Professor
		JPanel tabProfessor = new JPanel();
		tabProfessor.setBackground(new Color(179, 16, 9));
		tabbedPane.addTab("Cadastrar Professor", null, tabProfessor, "Cadastro de Professor");
		tabbedPane.setBackgroundAt(6, new Color(179, 16, 9));
		tabProfessor.setLayout(null);
		
		JPanel containerProfessor = new JPanel();
		containerProfessor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerProfessor.setBackground(new Color(240, 240, 240));
		containerProfessor.setBounds(44, 11, 507, 369);
		tabProfessor.add(containerProfessor);
		containerProfessor.setLayout(null);
		
		JLabel lblProfessorCpf = new JLabel("CPF:");
		lblProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorCpf.setBounds(20, 26, 55, 30);
		containerProfessor.add(lblProfessorCpf);
		tfProfessorCpf = new JTextField();
		tfProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorCpf.setColumns(10);
		tfProfessorCpf.setBounds(75, 26, 400, 30);
		containerProfessor.add(tfProfessorCpf);
		
		JLabel lblProfessorNome = new JLabel("Nome:");
		lblProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorNome.setBounds(20, 86, 55, 30);
		containerProfessor.add(lblProfessorNome);
		tfProfessorNome = new JTextField();
		tfProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorNome.setColumns(10);
		tfProfessorNome.setBounds(75, 86, 400, 30);
		containerProfessor.add(tfProfessorNome);
		
		JLabel lblrea = new JLabel("Área:");
		lblrea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblrea.setBounds(20, 146, 55, 30);
		containerProfessor.add(lblrea);
		
		JComboBox cbProfessorAreas = new JComboBox();
		cbProfessorAreas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbProfessorAreas.setEditable(true);
		cbProfessorAreas.setBounds(75, 146, 145, 30);
		containerProfessor.add(cbProfessorAreas);
		
		JLabel lblProfessorPontuacao = new JLabel("Pontuação:");
		lblProfessorPontuacao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorPontuacao.setBounds(255, 146, 96, 30);
		containerProfessor.add(lblProfessorPontuacao);
		tfProfessorPontuacao = new JTextField();
		tfProfessorPontuacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorPontuacao.setColumns(10);
		tfProfessorPontuacao.setBounds(350, 146, 125, 30);
		containerProfessor.add(tfProfessorPontuacao);
		
		JButton btnProfessorCancelar = new JButton("Cancelar");
		btnProfessorCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCancelar.setBackground(UIManager.getColor("Button.background"));
		btnProfessorCancelar.setBounds(288, 335, 89, 23);
		containerProfessor.add(btnProfessorCancelar);
		
		JButton btnProfessorCadastrar = new JButton("Salvar");
		btnProfessorCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastrar.setBackground(Color.WHITE);
		btnProfessorCadastrar.setBounds(388, 335, 89, 23);
		containerProfessor.add(btnProfessorCadastrar);
		
		
		
	}
}*/
