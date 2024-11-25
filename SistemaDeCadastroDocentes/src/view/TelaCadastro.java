package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscricaoController;
import controller.LoginController;
import controller.ProfessorController;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfInscricaoCpfProfessor;
	private JTextField tfCursoNome;
	private JTextField tfCursoCodigo;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaHorario;
	private JTextField tfDisciplinaCurso;
	private JTextField tfDisciplinaCarga;
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorPontuacao;
	private JTextField tfProfessorArea;
	private JTextField tfCursoArea;
	private JTextField tfInscricaoProcesso;
	private JTextField tfInscricaoDisciplina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setTitle("Sistema de Chamadas Públicas - Cadastro");
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
				tabInscricao.setBackground(new Color(179, 16, 9));
				tabbedPane.addTab("Inscrição", null, tabInscricao, null);
				tabInscricao.setLayout(null);
				
				JPanel containerInscricao = new JPanel();
				containerInscricao.setLayout(null);
				containerInscricao.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
				containerInscricao.setBounds(44, 11, 513, 369);
				tabInscricao.add(containerInscricao);
				
				JTextArea taInscricaoListaDisciplinasOuInscricoes = new JTextArea();
				taInscricaoListaDisciplinasOuInscricoes.setBounds(26, 184, 462, 134);
				containerInscricao.add(taInscricaoListaDisciplinasOuInscricoes);
				
				JButton btnInscricaoExcluir = new JButton("Excluir");
				btnInscricaoExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnInscricaoExcluir.setBackground(UIManager.getColor("Button.background"));
				btnInscricaoExcluir.setBounds(299, 335, 89, 23);
				containerInscricao.add(btnInscricaoExcluir);
				
				JButton btnInscricaoSalvar = new JButton("Salvar");
				btnInscricaoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnInscricaoSalvar.setBackground(Color.WHITE);
				btnInscricaoSalvar.setBounds(399, 335, 89, 23);
				containerInscricao.add(btnInscricaoSalvar);
				
				JLabel lblInscricaoCpfProfessor = new JLabel("CPF:");
				lblInscricaoCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInscricaoCpfProfessor.setBounds(26, 81, 51, 30);
				containerInscricao.add(lblInscricaoCpfProfessor);
				
				tfInscricaoCpfProfessor = new JTextField();
				tfInscricaoCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfInscricaoCpfProfessor.setColumns(10);
				tfInscricaoCpfProfessor.setBounds(74, 81, 150, 30);
				containerInscricao.add(tfInscricaoCpfProfessor);
				
				JLabel lblInscricaoTitulo = new JLabel("INSCRIÇÃO");
				lblInscricaoTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblInscricaoTitulo.setBounds(25, 21, 463, 41);
				containerInscricao.add(lblInscricaoTitulo);
				
				JLabel lblInscricaoProcesso = new JLabel("Processo:");
				lblInscricaoProcesso.setToolTipText("Código do Curso");
				lblInscricaoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInscricaoProcesso.setBounds(251, 81, 89, 30);
				containerInscricao.add(lblInscricaoProcesso);
				
				tfInscricaoProcesso = new JTextField();
				tfInscricaoProcesso.setToolTipText("Código do Curso");
				tfInscricaoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfInscricaoProcesso.setColumns(10);
				tfInscricaoProcesso.setBounds(338, 81, 150, 30);
				containerInscricao.add(tfInscricaoProcesso);
				
				JLabel lblInscricaoDisciplina = new JLabel("Disciplina:");
				lblInscricaoDisciplina.setToolTipText("Código do Curso");
				lblInscricaoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInscricaoDisciplina.setBounds(251, 132, 89, 30);
				containerInscricao.add(lblInscricaoDisciplina);
				
				tfInscricaoDisciplina = new JTextField();
				tfInscricaoDisciplina.setToolTipText("Código do Curso");
				tfInscricaoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfInscricaoDisciplina.setColumns(10);
				tfInscricaoDisciplina.setBounds(338, 132, 110, 30);
				containerInscricao.add(tfInscricaoDisciplina);
				
				JButton btnInscricaoProcurarDisciplinas = new JButton("?");
				btnInscricaoProcurarDisciplinas.setBounds(451, 132, 37, 30);
				containerInscricao.add(btnInscricaoProcurarDisciplinas);
				
				JPanel tabCurso = new JPanel();
				tabCurso.setBackground(new Color(179, 16, 9));
				tabbedPane.addTab("Curso", null, tabCurso, null);
				tabCurso.setLayout(null);
				
				JPanel containerCurso = new JPanel();
				containerCurso.setLayout(null);
				containerCurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
				containerCurso.setBounds(45, 11, 507, 369);
				tabCurso.add(containerCurso);
				
				JButton btnCursoExcluir = new JButton("Excluir");
				btnCursoExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCursoExcluir.setBackground(UIManager.getColor("Button.background"));
				btnCursoExcluir.setBounds(293, 335, 89, 23);
				containerCurso.add(btnCursoExcluir);
				
				JButton btnCursoSalvar = new JButton("Salvar");
				btnCursoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCursoSalvar.setBackground(Color.WHITE);
				btnCursoSalvar.setBounds(393, 335, 89, 23);
				containerCurso.add(btnCursoSalvar);
				
				JLabel lblCursoNome = new JLabel("Nome:");
				lblCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCursoNome.setBounds(30, 82, 64, 30);
				containerCurso.add(lblCursoNome);
				
				tfCursoNome = new JTextField();
				tfCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfCursoNome.setColumns(10);
				tfCursoNome.setBounds(94, 82, 136, 30);
				containerCurso.add(tfCursoNome);
				
				JLabel lblCursoCodigo = new JLabel("Código:");
				lblCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCursoCodigo.setBounds(264, 82, 64, 29);
				containerCurso.add(lblCursoCodigo);
				
				tfCursoCodigo = new JTextField();
				tfCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfCursoCodigo.setColumns(10);
				tfCursoCodigo.setBounds(328, 82, 154, 30);
				containerCurso.add(tfCursoCodigo);
				
				JTextArea taCursoListaCursos = new JTextArea();
				taCursoListaCursos.setBounds(30, 188, 453, 125);
				containerCurso.add(taCursoListaCursos);
				
				JLabel lblCursoArea = new JLabel("Área:");
				lblCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCursoArea.setBounds(30, 135, 64, 29);
				containerCurso.add(lblCursoArea);
				
				tfCursoArea = new JTextField();
				tfCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfCursoArea.setColumns(10);
				tfCursoArea.setBounds(94, 134, 136, 30);
				containerCurso.add(tfCursoArea);
				
				JLabel lblCursoTitulo = new JLabel("CURSO");
				lblCursoTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblCursoTitulo.setBounds(30, 22, 453, 41);
				containerCurso.add(lblCursoTitulo);
				
				JPanel tabDisciplina = new JPanel();
				tabDisciplina.setBackground(new Color(179, 16, 9));
				tabbedPane.addTab("Disciplina", null, tabDisciplina, null);
				tabDisciplina.setLayout(null);
				
				JPanel containerDisciplina = new JPanel();
				containerDisciplina.setLayout(null);
				containerDisciplina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
				containerDisciplina.setBounds(45, 11, 507, 369);
				tabDisciplina.add(containerDisciplina);
				
				JLabel lblDisciplinaCodigo = new JLabel("Código:");
				lblDisciplinaCodigo.setToolTipText("Código da Disciplina");
				lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaCodigo.setBounds(20, 73, 66, 30);
				containerDisciplina.add(lblDisciplinaCodigo);
				
				tfDisciplinaCodigo = new JTextField();
				tfDisciplinaCodigo.setToolTipText("Código da Disciplina");
				tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDisciplinaCodigo.setColumns(10);
				tfDisciplinaCodigo.setBounds(84, 73, 150, 30);
				containerDisciplina.add(tfDisciplinaCodigo);
				
				JLabel lblDisciplinaCurso = new JLabel("Curso:");
				lblDisciplinaCurso.setToolTipText("Código do Curso");
				lblDisciplinaCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaCurso.setBounds(20, 125, 66, 30);
				containerDisciplina.add(lblDisciplinaCurso);
				
				JLabel lblDisciplinaNome = new JLabel("Nome:");
				lblDisciplinaNome.setToolTipText("Nome da Disciplina");
				lblDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaNome.setBounds(269, 74, 66, 30);
				containerDisciplina.add(lblDisciplinaNome);
				
				tfDisciplinaNome = new JTextField();
				tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDisciplinaNome.setColumns(10);
				tfDisciplinaNome.setBounds(333, 73, 150, 30);
				containerDisciplina.add(tfDisciplinaNome);
				
				JButton btnDisciplinaSalvar = new JButton("Salvar");
				btnDisciplinaSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnDisciplinaSalvar.setBackground(Color.WHITE);
				btnDisciplinaSalvar.setBounds(394, 335, 89, 23);
				containerDisciplina.add(btnDisciplinaSalvar);
				
				JButton btnDisciplinaExcluir = new JButton("Excluir");
				btnDisciplinaExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnDisciplinaExcluir.setBackground(UIManager.getColor("Button.background"));
				btnDisciplinaExcluir.setBounds(294, 335, 89, 23);
				containerDisciplina.add(btnDisciplinaExcluir);
				
				JLabel lblDisciplinaDia = new JLabel("Dia:");
				lblDisciplinaDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaDia.setBounds(20, 176, 66, 30);
				containerDisciplina.add(lblDisciplinaDia);
				
				JComboBox cbDisciplinaDias = new JComboBox();
				cbDisciplinaDias.setModel(new DefaultComboBoxModel(new String[] {"Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"}));
				cbDisciplinaDias.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbDisciplinaDias.setEditable(true);
				cbDisciplinaDias.setBounds(83, 176, 150, 30);
				containerDisciplina.add(cbDisciplinaDias);
				
				JLabel lblDisciplinaHorario = new JLabel("Horário:");
				lblDisciplinaHorario.setToolTipText("Horário da Disciplina");
				lblDisciplinaHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaHorario.setBounds(268, 177, 66, 30);
				containerDisciplina.add(lblDisciplinaHorario);
				
				tfDisciplinaHorario = new JTextField();
				tfDisciplinaHorario.setToolTipText("Horário da Disciplina");
				tfDisciplinaHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDisciplinaHorario.setColumns(10);
				tfDisciplinaHorario.setBounds(332, 176, 150, 30);
				containerDisciplina.add(tfDisciplinaHorario);
				
				JLabel lblDisciplinaCarga = new JLabel("Carga:");
				lblDisciplinaCarga.setToolTipText("Carga horária");
				lblDisciplinaCarga.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDisciplinaCarga.setBounds(269, 125, 66, 30);
				containerDisciplina.add(lblDisciplinaCarga);
				
				tfDisciplinaCurso = new JTextField();
				tfDisciplinaCurso.setToolTipText("Código do Curso");
				tfDisciplinaCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDisciplinaCurso.setColumns(10);
				tfDisciplinaCurso.setBounds(84, 125, 110, 30);
				containerDisciplina.add(tfDisciplinaCurso);
				
				JButton btnDisciplinaProcurarCursos = new JButton("?");
				btnDisciplinaProcurarCursos.setBounds(197, 125, 37, 30);
				containerDisciplina.add(btnDisciplinaProcurarCursos);
				
				tfDisciplinaCarga = new JTextField();
				tfDisciplinaCarga.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDisciplinaCarga.setColumns(10);
				tfDisciplinaCarga.setBounds(333, 125, 150, 30);
				containerDisciplina.add(tfDisciplinaCarga);
				
				JLabel lblDisciplinaTitulo = new JLabel("DISCIPLINA");
				lblDisciplinaTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblDisciplinaTitulo.setBounds(20, 21, 463, 41);
				containerDisciplina.add(lblDisciplinaTitulo);
				
				JTextArea taDisciplinaListaCursosOuDisciplinas = new JTextArea();
				taDisciplinaListaCursosOuDisciplinas.setBounds(20, 230, 462, 95);
				containerDisciplina.add(taDisciplinaListaCursosOuDisciplinas);
				
				JPanel tabProfessor = new JPanel();
				tabProfessor.setBackground(new Color(179, 16, 9));
				tabbedPane.addTab("Professor", null, tabProfessor, null);
				tabProfessor.setLayout(null);
				
				JPanel containerProfessor = new JPanel();
				containerProfessor.setLayout(null);
				containerProfessor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
				containerProfessor.setBackground(UIManager.getColor("Button.background"));
				containerProfessor.setBounds(44, 11, 507, 369);
				tabProfessor.add(containerProfessor);
				
				JLabel lblProfessorCpf = new JLabel("CPF:");
				lblProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblProfessorCpf.setBounds(296, 73, 55, 30);
				containerProfessor.add(lblProfessorCpf);
				
				tfProfessorCpf = new JTextField();
				tfProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfProfessorCpf.setColumns(10);
				tfProfessorCpf.setBounds(350, 73, 132, 30);
				containerProfessor.add(tfProfessorCpf);
				
				JLabel lblProfessorNome = new JLabel("Nome:");
				lblProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblProfessorNome.setBounds(30, 73, 55, 30);
				containerProfessor.add(lblProfessorNome);
				
				tfProfessorNome = new JTextField();
				tfProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfProfessorNome.setColumns(10);
				tfProfessorNome.setBounds(85, 73, 135, 30);
				containerProfessor.add(tfProfessorNome);
				
				JLabel lblProfessorArea = new JLabel("Área:");
				lblProfessorArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblProfessorArea.setBounds(30, 129, 55, 30);
				containerProfessor.add(lblProfessorArea);
				
				JLabel lblProfessorPontuacao = new JLabel("Pontuação:");
				lblProfessorPontuacao.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblProfessorPontuacao.setBounds(255, 129, 96, 30);
				containerProfessor.add(lblProfessorPontuacao);
				
				tfProfessorPontuacao = new JTextField();
				tfProfessorPontuacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfProfessorPontuacao.setColumns(10);
				tfProfessorPontuacao.setBounds(350, 129, 132, 30);
				containerProfessor.add(tfProfessorPontuacao);
				
				JButton btnProfessorExcluir = new JButton("Excluir");
				btnProfessorExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnProfessorExcluir.setBackground(UIManager.getColor("Button.background"));
				btnProfessorExcluir.setBounds(288, 335, 89, 23);
				containerProfessor.add(btnProfessorExcluir);
				
				JButton btnProfessorSalvar = new JButton("Salvar");
				btnProfessorSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnProfessorSalvar.setBackground(Color.WHITE);
				btnProfessorSalvar.setBounds(388, 335, 89, 23);
				containerProfessor.add(btnProfessorSalvar);
				
				JTextArea taProfessorListaProfessores = new JTextArea();
				taProfessorListaProfessores.setBounds(26, 188, 456, 128);
				containerProfessor.add(taProfessorListaProfessores);
				
				tfProfessorArea = new JTextField();
				tfProfessorArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfProfessorArea.setColumns(10);
				tfProfessorArea.setBounds(85, 129, 135, 30);
				containerProfessor.add(tfProfessorArea);
				
				JLabel lblProfessorTitulo = new JLabel("PROFESSOR");
				lblProfessorTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblProfessorTitulo.setBounds(27, 21, 456, 41);
				containerProfessor.add(lblProfessorTitulo);
				
	
				// professor botoes
				ProfessorController pCont = new ProfessorController(tfProfessorCpf, tfProfessorNome, tfProfessorArea, tfProfessorPontuacao, taProfessorListaProfessores);
				btnProfessorSalvar.addActionListener(pCont);
				btnProfessorExcluir.addActionListener(pCont);
				
				// inscricao botoes
				InscricaoController iCont = new InscricaoController(tfInscricaoCpfProfessor, tfDisciplinaNome, tfCursoArea, taInscricaoListaDisciplinasOuInscricoes);
				btnInscricaoSalvar.addActionListener(iCont);
				btnInscricaoProcurarDisciplinas.addActionListener(iCont);
				btnInscricaoExcluir.addActionListener(iCont);
				
				// Disciplina botoes
				DisciplinaController dCont= new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, tfDisciplinaCarga, tfDisciplinaHorario, tfDisciplinaNome, tfDisciplinaCurso, taDisciplinaListaCursosOuDisciplinas);
				btnDisciplinaSalvar.addActionListener(dCont);
				btnDisciplinaExcluir.addActionListener(dCont);
				btnDisciplinaProcurarCursos.addActionListener(dCont);
				
				//cursos botoes
				CursoController cCont = new CursoController(tfCursoCodigo, tfCursoNome, tfCursoArea, taCursoListaCursos);
				btnCursoExcluir.addActionListener(cCont);
				btnCursoSalvar.addActionListener(cCont);
		
				
				
				
				
				
	}
}
