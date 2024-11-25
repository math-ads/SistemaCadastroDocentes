package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscricaoController;
import controller.ProfessorController;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class TelaCrud extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JTextField tfCursosCodigo;
	private JTextField tfCursoNome;
	private JTextField tfCursoArea;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorArea;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfProfessoresCpf;
	private JTextField tfCursoCodigo;
	private JTextField tfInscricaoCpf;
	private JTextField tfProfessorCpf;
	private JTextField tfInscricoesCodigo;
	private JComboBox<String> cbDisciplinaCurso;
	private JComboBox<String> cbInscricaoDisciplina;
	private JPanel containerDisciplinaCadastro;
	private JPanel containerInscricaoCadastro;
	private JButton btnDisciplinaAdicionarCurso;
	private JButton btnInscricaoAdicionarDisciplina;
	private JTextArea taDisciplinasListagem;
	private JTextArea taCursosListagem;
	private JTextArea taProfessoresListagem;
	private JTextArea taInscricoesListagem;
	private JMenuItem smCursosListagem;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCrud frame = new TelaCrud();
					frame.setVisible(true);
					frame.setBounds(100, 100, 640, 480);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public TelaCrud() throws PropertyVetoException, FileNotFoundException, IOException {
		
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Sistema de Chamadas Públicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CursoController cursoController = new CursoController();
		DisciplinaController disciplinaController = new DisciplinaController();
		InscricaoController inscricaoController = new InscricaoController();
		ProfessorController professorController = new ProfessorController();
		
        JMenuBar menubar = new JMenuBar();
        
        // Início do Menu - CURSOS
        JMenu menuCursos = new JMenu("  Cursos  ");
        smCursosListagem = new JMenuItem("Listagem");
        smCursosListagem.setActionCommand("Card Curso Listagem");
        smCursosListagem.addActionListener(new ChangeCardlayoutListener());
        JMenuItem smCursosCadastro = new JMenuItem("Cadastro");
        smCursosCadastro.setActionCommand("Card Curso Cadastro");
        smCursosCadastro.addActionListener(new ChangeCardlayoutListener());
        menuCursos.add(smCursosListagem);
        menuCursos.add(smCursosCadastro);
        menubar.add(menuCursos);
        setJMenuBar(menubar);
        // Fim do Menu - CURSOS
        
        // Início do Menu - DISCIPLINAS
        JMenu menuDisciplinas = new JMenu("  Disciplinas  ");
        menubar.add(menuDisciplinas);
        JMenuItem smDisciplinasListagem = new JMenuItem("Listagem");
        smDisciplinasListagem.setActionCommand("Card Disciplina Listagem");
        smDisciplinasListagem.addActionListener(new ChangeCardlayoutListener());
        menuDisciplinas.add(smDisciplinasListagem);
        JMenuItem smDisciplinasCadastro = new JMenuItem("Cadastro");
        smDisciplinasCadastro.setActionCommand("Card Disciplina Cadastro");
        smDisciplinasCadastro.addActionListener(new ChangeCardlayoutListener());
        menuDisciplinas.add(smDisciplinasCadastro);
        // Fim do Menu - DISCIPLINAS
        
        // Início do Menu - INSCRIÇÕES
        JMenu menuInscricao = new JMenu("  Inscrições  ");
        menubar.add(menuInscricao);
        JMenuItem smInscricaoListagem = new JMenuItem("Listagem");
        smInscricaoListagem.setActionCommand("Card Inscrição Listagem");
        smInscricaoListagem.addActionListener(new ChangeCardlayoutListener());
        menuInscricao.add(smInscricaoListagem);
        JMenuItem smInscricaoCadastro = new JMenuItem("Cadastro");
        smInscricaoCadastro.setActionCommand("Card Inscrição Cadastro");
        smInscricaoCadastro.addActionListener(new ChangeCardlayoutListener());
        menuInscricao.add(smInscricaoCadastro);
        // Fim do Menu - INSCRIÇÕES
        
        // Início do Menu - PROFESSORES
        JMenu menuProfessores = new JMenu("  Professores  ");
        menubar.add(menuProfessores);
        JMenuItem smProfessoresListagem = new JMenuItem("Listagem");
        smProfessoresListagem.setActionCommand("Card Professor Listagem");
        smProfessoresListagem.addActionListener(new ChangeCardlayoutListener());
        menuProfessores.add(smProfessoresListagem);
        JMenuItem smProfessoresCadastro = new JMenuItem("Cadastro");
        smProfessoresCadastro.setActionCommand("Card Professor Cadastro");
        smProfessoresCadastro.addActionListener(new ChangeCardlayoutListener());
        menuProfessores.add(smProfessoresCadastro);
        // Fim do Menu - PROFESSORES
        
        // Início do Menu - PROCESSOS
        JMenu menuProcessos = new JMenu("Processos");
        menubar.add(menuProcessos);
        JMenuItem smProcessosAtivos = new JMenuItem("Ativos");
        smProcessosAtivos.setActionCommand("Card Processos Ativos Listagem");
        smProcessosAtivos.addActionListener(new ChangeCardlayoutListener());
        menuProcessos.add(smProcessosAtivos);
        // Fim do Menu - PROCESSOS
        
        
        // Início do Card de Listagem - CURSOS
        JLabel lblCursosTitulo = new JLabel("LISTA DE CURSOS");
		lblCursosTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCursosTitulo.setBounds(24, 21, 503, 41);
        taCursosListagem = new JTextArea();
		taCursosListagem.setLineWrap(true);
        JScrollPane spCursosListagem = new JScrollPane();
		spCursosListagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spCursosListagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCursosListagem.setBounds(24, 73, 472, 165);
		spCursosListagem.setViewportView(taCursosListagem);
        JLabel lblCursosCodigo = new JLabel("Digite o código:");
		lblCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursosCodigo.setBounds(24, 22, 112, 30);
		tfCursosCodigo = new JTextField();
		tfCursosCodigo.setToolTipText("");
		tfCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursosCodigo.setColumns(10);
		tfCursosCodigo.setBounds(138, 22, 150, 30);
		JButton btnCursosEditar = new JButton("Editar");
		btnCursosEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursosEditar.setBounds(399, 22, 95, 30);
		
		JButton btnCursosExcluir = new JButton("Excluir");
        btnCursosExcluir.addActionListener(cursoController);
		btnCursosExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursosExcluir.setBounds(296, 22, 94, 30);
		JButton btnCursosAdicionar = new JButton("+ Adicionar Curso");
		btnCursosAdicionar.setActionCommand("Card Curso Cadastro");
        btnCursosAdicionar.addActionListener(new ChangeCardlayoutListener());
		btnCursosAdicionar.setBounds(296, 70, 198, 30);
		btnCursosAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JPanel containerCursosBusca = new JPanel();
		containerCursosBusca.setLayout(null);
		containerCursosBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerCursosBusca.setBounds(0, 261, 523, 118);
		containerCursosBusca.add(lblCursosCodigo);
		containerCursosBusca.add(tfCursosCodigo);
		containerCursosBusca.add(btnCursosEditar);
		containerCursosBusca.add(btnCursosExcluir);
		containerCursosBusca.add(btnCursosAdicionar);
        JPanel containerCursoListagem = new JPanel();
        containerCursoListagem.setBounds(39, 11, 523, 379);
        containerCursoListagem.setLayout(null);
        containerCursoListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerCursoListagem.add(lblCursosTitulo);
        containerCursoListagem.add(spCursosListagem);
        containerCursoListagem.add(containerCursosBusca);
        JPanel cardCursoListagem = new JPanel();
        cardCursoListagem.setBackground(new Color(179, 16, 9));
        cardCursoListagem.setLayout(null);
        cardCursoListagem.add(containerCursoListagem);
        btnCursosEditar.setActionCommand(tfCursosCodigo.getText() + "," + "Card Curso Cadastro");
        btnCursosEditar.addActionListener(new ChangeCardlayoutListener());
        // Fim do Card de Listagem - CURSOS
        
        
        // Início do Card de Cadastro - CURSOS
        JPanel cardCursoCadastro = new JPanel();
        cardCursoCadastro.setBackground(new Color(179, 16, 9));
        cardCursoCadastro.setLayout(null);
        JPanel containerCursoCadastro = new JPanel();
        containerCursoCadastro.setLayout(null);
        containerCursoCadastro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerCursoCadastro.setBounds(39, 11, 523, 379);
        cardCursoCadastro.add(containerCursoCadastro);
        JButton btnCursoVoltar = new JButton("Voltar");
        btnCursoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCursoVoltar.setBackground(UIManager.getColor("Button.background"));
        btnCursoVoltar.setBounds(300, 335, 89, 23);
        btnCursoVoltar.setActionCommand("Card Curso Listagem");
        btnCursoVoltar.addActionListener(new ChangeCardlayoutListener());
        containerCursoCadastro.add(btnCursoVoltar);
        JButton btnCursoSalvar = new JButton("Salvar");
        btnCursoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCursoSalvar.setBackground(UIManager.getColor("Button.background"));
        btnCursoSalvar.setBounds(406, 335, 89, 23);
        containerCursoCadastro.add(btnCursoSalvar);
        JLabel lblCursoNome = new JLabel("Nome: *");
        lblCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCursoNome.setBounds(24, 82, 70, 30);
        containerCursoCadastro.add(lblCursoNome);
        tfCursoNome = new JTextField();
        tfCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfCursoNome.setColumns(10);
        tfCursoNome.setBounds(94, 82, 402, 30);
        containerCursoCadastro.add(tfCursoNome);
        JLabel lblCursoArea = new JLabel("Área:");
        lblCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCursoArea.setBounds(24, 187, 64, 29);
        containerCursoCadastro.add(lblCursoArea);
        tfCursoArea = new JTextField();
        tfCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfCursoArea.setColumns(10);
        tfCursoArea.setBounds(94, 186, 188, 30);
        containerCursoCadastro.add(tfCursoArea);
        JLabel lblCursoCodigo = new JLabel("Código: *");
        lblCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCursoCodigo.setBounds(24, 135, 70, 29);
        containerCursoCadastro.add(lblCursoCodigo);
        tfCursoCodigo = new JTextField();
        tfCursoCodigo.setText("");
        tfCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfCursoCodigo.setColumns(10);
        tfCursoCodigo.setBounds(94, 134, 188, 30);
        containerCursoCadastro.add(tfCursoCodigo);
        JLabel lblCursoTitulo = new JLabel("CADASTRO DE CURSO");
        lblCursoTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCursoTitulo.setBounds(24, 22, 453, 41);
        containerCursoCadastro.add(lblCursoTitulo);
        JTextArea taCursoAvisos = new JTextArea();
        taCursoAvisos.setFont(new Font("Monospaced", Font.BOLD, 12));
        taCursoAvisos.setBackground(UIManager.getColor("Panel.background"));
        taCursoAvisos.setForeground(new Color(0, 0, 0));
        taCursoAvisos.setEditable(false);
        taCursoAvisos.setWrapStyleWord(true);
        taCursoAvisos.setLineWrap(true);
        taCursoAvisos.setBounds(30, 335, 259, 53);
        containerCursoCadastro.add(taCursoAvisos);
        btnCursoSalvar.addActionListener(new CursoController(tfCursoCodigo, tfCursoNome, tfCursoArea, taCursoAvisos));
        // Fim do Card de Cadastro - CURSOS
        
        
        // Início do Card de Listagem - DISCIPLINAS
        JPanel cardDisciplinaListagem = new JPanel();
        cardDisciplinaListagem.setLayout(null);
        cardDisciplinaListagem.setBackground(new Color(179, 16, 9));
        JPanel containerDisciplinaListagem = new JPanel();
        containerDisciplinaListagem.setLayout(null);
        containerDisciplinaListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerDisciplinaListagem.setBounds(39, 11, 523, 379);
        cardDisciplinaListagem.add(containerDisciplinaListagem);
        JLabel lblDisciplinasTitulo = new JLabel("LISTA DE DISCIPLINAS");
        lblDisciplinasTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDisciplinasTitulo.setBounds(24, 21, 503, 41);
        containerDisciplinaListagem.add(lblDisciplinasTitulo);
        taDisciplinasListagem = new JTextArea();
        taDisciplinasListagem.setLineWrap(true);
        JScrollPane spDisciplinasListagem = new JScrollPane();
        spDisciplinasListagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spDisciplinasListagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spDisciplinasListagem.setBounds(24, 73, 472, 165);
        spDisciplinasListagem.setViewportView(taDisciplinasListagem);
        containerDisciplinaListagem.add(spDisciplinasListagem);
        JPanel containerDisciplinasBusca = new JPanel();
        containerDisciplinasBusca.setLayout(null);
        containerDisciplinasBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerDisciplinasBusca.setBounds(0, 261, 523, 118);
        containerDisciplinaListagem.add(containerDisciplinasBusca);
        JLabel lblDisciplinasCodigo = new JLabel("Digite o código:");
        lblDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinasCodigo.setBounds(24, 22, 112, 30);
        containerDisciplinasBusca.add(lblDisciplinasCodigo);
        JTextField tfDisciplinasCodigo = new JTextField();
        tfDisciplinasCodigo.setToolTipText("");
        tfDisciplinasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfDisciplinasCodigo.setColumns(10);
        tfDisciplinasCodigo.setBounds(138, 22, 150, 30);
        containerDisciplinasBusca.add(tfDisciplinasCodigo);
        JButton btnDisciplinasEditar = new JButton("Editar");
        btnDisciplinasEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinasEditar.setBounds(399, 22, 95, 30);
        
        containerDisciplinasBusca.add(btnDisciplinasEditar);
        JButton btnDisciplinasExcluir = new JButton("Excluir");
        btnDisciplinasExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinasExcluir.setBounds(296, 22, 94, 30);
        btnDisciplinasExcluir.addActionListener(disciplinaController);
        containerDisciplinasBusca.add(btnDisciplinasExcluir);
        JButton btnDisciplinasAdicionar = new JButton("+ Adicionar Disciplina");
        btnDisciplinasAdicionar.setActionCommand("Card Disciplina Cadastro");
        btnDisciplinasAdicionar.addActionListener(new ChangeCardlayoutListener());
        btnDisciplinasAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinasAdicionar.setBounds(296, 70, 198, 30);
        containerDisciplinasBusca.add(btnDisciplinasAdicionar);
        btnDisciplinasEditar.setActionCommand(tfDisciplinasCodigo.getText() + "," + "Card Disciplina Cadastro");
        btnDisciplinasEditar.addActionListener(new ChangeCardlayoutListener());
		// Início do Card de Listagem - DISCIPLINAS
        

        
		// Início do Card de Cadastro - DISCIPLINAS
		JPanel cardDisciplinaCadastro = new JPanel();
        cardDisciplinaCadastro.setBackground(new Color(179, 16, 9));
        cardDisciplinaCadastro.setLayout(null);
        containerDisciplinaCadastro = new JPanel();
        containerDisciplinaCadastro.setLayout(null);
        containerDisciplinaCadastro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerDisciplinaCadastro.setBounds(39, 11, 523, 379);
        cardDisciplinaCadastro.add(containerDisciplinaCadastro);
        JButton btnDisciplinaVoltar = new JButton("Voltar");
        btnDisciplinaVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinaVoltar.setBackground(UIManager.getColor("Button.background"));
        btnDisciplinaVoltar.setBounds(300, 335, 89, 23);
        btnDisciplinaVoltar.setActionCommand("Card Disciplina Listagem");
        btnDisciplinaVoltar.addActionListener(new ChangeCardlayoutListener());
        containerDisciplinaCadastro.add(btnDisciplinaVoltar);
        JButton btnDisciplinaSalvar = new JButton("Salvar");
        btnDisciplinaSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinaSalvar.setBackground(UIManager.getColor("Button.background"));
        btnDisciplinaSalvar.setBounds(406, 335, 89, 23);
        containerDisciplinaCadastro.add(btnDisciplinaSalvar);
        JLabel lblDisciplinaNome = new JLabel("Nome: *");
        lblDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinaNome.setBounds(24, 82, 70, 30);
        containerDisciplinaCadastro.add(lblDisciplinaNome);
        tfDisciplinaNome = new JTextField();
        tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfDisciplinaNome.setColumns(10);
        tfDisciplinaNome.setBounds(94, 82, 402, 30);
        containerDisciplinaCadastro.add(tfDisciplinaNome);
        JLabel lblDisciplinaCurso = new JLabel("Curso: *");
        lblDisciplinaCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinaCurso.setBounds(24, 187, 64, 30);
        containerDisciplinaCadastro.add(lblDisciplinaCurso);
        btnDisciplinaAdicionarCurso = new JButton("+ Adicionar Curso");
        btnDisciplinaAdicionarCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDisciplinaAdicionarCurso.setBounds(94, 186, 188, 30);
        btnDisciplinaAdicionarCurso.setActionCommand("Card Curso Cadastro");
        btnDisciplinaAdicionarCurso.addActionListener(new ChangeCardlayoutListener());
        containerDisciplinaCadastro.add(btnDisciplinaAdicionarCurso);
        cbDisciplinaCurso = new JComboBox<String>();
        cbDisciplinaCurso.setBackground(new Color(255, 255, 255));
        cbDisciplinaCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbDisciplinaCurso.setBounds(94, 186, 188, 30);
        containerDisciplinaCadastro.add(cbDisciplinaCurso);
        JLabel lblDisciplinaCarga = new JLabel("Carga:");
		lblDisciplinaCarga.setToolTipText("Carga horária");
		lblDisciplinaCarga.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCarga.setBounds(310, 135, 66, 30);
		containerDisciplinaCadastro.add(lblDisciplinaCarga);
		JTextField tfDisciplinaCarga = new JTextField();
		tfDisciplinaCarga.setText("0");
		tfDisciplinaCarga.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCarga.setColumns(10);
		tfDisciplinaCarga.setBounds(373, 134, 123, 30);
		containerDisciplinaCadastro.add(tfDisciplinaCarga);
        JLabel lblDisciplinaCodigo = new JLabel("Código: *");
        lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinaCodigo.setBounds(24, 135, 70, 29);
        containerDisciplinaCadastro.add(lblDisciplinaCodigo);
        tfDisciplinaCodigo = new JTextField();
        tfDisciplinaCodigo.setText("");
        tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfDisciplinaCodigo.setColumns(10);
        tfDisciplinaCodigo.setBounds(94, 134, 188, 30);
        containerDisciplinaCadastro.add(tfDisciplinaCodigo);
        JLabel lblDisciplinaDia = new JLabel("Dia:");
        lblDisciplinaDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinaDia.setBounds(310, 187, 70, 30);
        containerDisciplinaCadastro.add(lblDisciplinaDia);
        JComboBox<String> cbDisciplinaDia = new JComboBox<String>();
        cbDisciplinaDia.setBackground(new Color(255, 255, 255));
        cbDisciplinaDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbDisciplinaDia.setBounds(373, 186, 123, 30);
        cbDisciplinaDia.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"}));
        containerDisciplinaCadastro.add(cbDisciplinaDia);
        JLabel lblDisciplinaHorario = new JLabel("Horário:");
        lblDisciplinaHorario.setToolTipText("Carga horária");
        lblDisciplinaHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDisciplinaHorario.setBounds(310, 239, 66, 30);
		containerDisciplinaCadastro.add(lblDisciplinaHorario);
		JTextField tfDisciplinaHorario = new JTextField();
		tfDisciplinaHorario.setText("00:00");
		tfDisciplinaHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaHorario.setColumns(10);
		tfDisciplinaHorario.setBounds(373, 238, 123, 30);
		containerDisciplinaCadastro.add(tfDisciplinaHorario);
        JLabel lblDisciplinaTitulo = new JLabel("CADASTRO DE DISCIPLINA");
        lblDisciplinaTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDisciplinaTitulo.setBounds(24, 22, 453, 41);
        JTextArea taDisciplinaAvisos = new JTextArea();
        taDisciplinaAvisos.setFont(new Font("Monospaced", Font.BOLD, 12));
        taDisciplinaAvisos.setBackground(UIManager.getColor("Panel.background"));
        taDisciplinaAvisos.setForeground(new Color(0, 0, 0));
        taDisciplinaAvisos.setEditable(false);
        taDisciplinaAvisos.setWrapStyleWord(true);
        taDisciplinaAvisos.setLineWrap(true);
        taDisciplinaAvisos.setBounds(30, 335, 259, 53);
        containerDisciplinaCadastro.add(taDisciplinaAvisos);
        containerDisciplinaCadastro.add(lblDisciplinaTitulo);
        btnDisciplinaSalvar.addActionListener(new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, tfDisciplinaCarga, 
				tfDisciplinaHorario, tfDisciplinaCarga, cbDisciplinaCurso, taDisciplinaAvisos, cbDisciplinaDia));
        // Fim do Card de Cadastro - DISCIPLINAS
        
        
// Início do Card de Listagem - PROFESSORES
        
        JPanel cardProfessorListagem = new JPanel();
        cardProfessorListagem.setLayout(null);
        cardProfessorListagem.setBackground(new Color(179, 16, 9));
        JPanel containerProfessorListagem = new JPanel();
        containerProfessorListagem.setLayout(null);
        containerProfessorListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerProfessorListagem.setBounds(20, 11, 542, 379);
        cardProfessorListagem.add(containerProfessorListagem);
        JLabel lblProfessoresTitulo = new JLabel("LISTA DE PROFESSORES");
        lblProfessoresTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblProfessoresTitulo.setBounds(24, 21, 503, 41);
        containerProfessorListagem.add(lblProfessoresTitulo);
        JTextArea taProfessoresListagem = new JTextArea();
        taProfessoresListagem.setLineWrap(true);
        JScrollPane spProfessoresListagem = new JScrollPane();
        spProfessoresListagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spProfessoresListagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spProfessoresListagem.setBounds(24, 73, 472, 165);
        spProfessoresListagem.setViewportView(taProfessoresListagem);
        containerProfessorListagem.add(spProfessoresListagem);
        JPanel containerProfessoresBusca = new JPanel();
        containerProfessoresBusca.setLayout(null);
        containerProfessoresBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerProfessoresBusca.setBounds(0, 261, 523, 118);
        containerProfessorListagem.add(containerProfessoresBusca);
        JLabel lblProfessoresCpf = new JLabel("Digite o CPF:");
        lblProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfessoresCpf.setBounds(24, 22, 112, 30);
        containerProfessoresBusca.add(lblProfessoresCpf);
        tfProfessoresCpf = new JTextField();
        tfProfessoresCpf.setToolTipText("");
        tfProfessoresCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfProfessoresCpf.setColumns(10);
        tfProfessoresCpf.setBounds(138, 22, 150, 30);
        containerProfessoresBusca.add(tfProfessoresCpf);
        JButton btnProfessoresEditar = new JButton("Editar");
        btnProfessoresEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnProfessoresEditar.setBounds(399, 22, 95, 30);
        
        containerProfessoresBusca.add(btnProfessoresEditar);
        JButton btnProfessoresExcluir = new JButton("Excluir");
        btnProfessoresExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnProfessoresExcluir.setBounds(296, 22, 94, 30);
        
     
        containerProfessoresBusca.add(btnProfessoresExcluir);
        JButton btnProfessoresAdicionar = new JButton("+ Adicionar Professor");
        btnProfessoresAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnProfessoresAdicionar.setBounds(296, 70, 198, 30);
        btnProfessoresAdicionar.setActionCommand("Card Professor Cadastro");
        btnProfessoresAdicionar.addActionListener(new ChangeCardlayoutListener());
        containerProfessoresBusca.add(btnProfessoresAdicionar);
        btnProfessoresEditar.setActionCommand(tfProfessoresCpf.getText() + "," + "Card Professor Cadastro");
        btnProfessoresEditar.addActionListener(new ChangeCardlayoutListener());
        // Fim do Card de Listagem - PROFESSORES
        
        
        // Início do Card de Cadastro - PROFESSORES
        JPanel cardProfessorCadastro = new JPanel();
        cardProfessorCadastro.setBackground(new Color(179, 16, 9));
        cardProfessorCadastro.setLayout(null);
        JPanel containerProfessorCadastro = new JPanel();
        containerProfessorCadastro.setLayout(null);
        containerProfessorCadastro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerProfessorCadastro.setBounds(39, 11, 523, 379);
        cardProfessorCadastro.add(containerProfessorCadastro);
        JButton btnProfessorVoltar = new JButton("Voltar");
        btnProfessorVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnProfessorVoltar.setBackground(UIManager.getColor("Button.background"));
        btnProfessorVoltar.setBounds(300, 335, 89, 23);
        btnProfessorVoltar.setActionCommand("Card Professor Listagem");
        btnProfessorVoltar.addActionListener(new ChangeCardlayoutListener());
        containerProfessorCadastro.add(btnProfessorVoltar);
        JButton btnProfessorSalvar = new JButton("Salvar");
        btnProfessorSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnProfessorSalvar.setBackground(UIManager.getColor("Button.background"));
        btnProfessorSalvar.setBounds(406, 335, 89, 23);
        containerProfessorCadastro.add(btnProfessorSalvar);
        JLabel lblProfessorNome = new JLabel("Nome:");
        lblProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfessorNome.setBounds(24, 82, 70, 30);
        containerProfessorCadastro.add(lblProfessorNome);
        tfProfessorNome = new JTextField();
        tfProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfProfessorNome.setColumns(10);
        tfProfessorNome.setBounds(94, 82, 402, 30);
        containerProfessorCadastro.add(tfProfessorNome);
        JLabel lblProfessorPontuacao = new JLabel("Pontuação:");
        lblProfessorPontuacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfessorPontuacao.setBounds(310, 187, 104, 29);
        containerProfessorCadastro.add(lblProfessorPontuacao);
        JTextField tfProfessorPontuacao = new JTextField();
        tfProfessorPontuacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfProfessorPontuacao.setColumns(10);
        tfProfessorPontuacao.setBounds(423, 186, 73, 30);
        containerProfessorCadastro.add(tfProfessorPontuacao);
        JLabel lblProfessorArea = new JLabel("Área:");
        lblProfessorArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfessorArea.setBounds(24, 135, 70, 29);
        containerProfessorCadastro.add(lblProfessorArea);
        tfProfessorArea = new JTextField();
        tfProfessorArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfProfessorArea.setColumns(10);
        tfProfessorArea.setBounds(94, 134, 188, 30);
        containerProfessorCadastro.add(tfProfessorArea);
        JLabel lblProfessorCpf = new JLabel("CPF:");
        lblProfessorCpf.setToolTipText("Carga horária");
        lblProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfessorCpf.setBounds(310, 135, 66, 30);
		containerProfessorCadastro.add(lblProfessorCpf);
		tfProfessorCpf = new JTextField();
		tfProfessorCpf.setText("");
		tfProfessorCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorCpf.setColumns(10);
		tfProfessorCpf.setBounds(373, 134, 123, 30);
		containerProfessorCadastro.add(tfProfessorCpf);
        JLabel lblProfessorTitulo = new JLabel("CADASTRO DE PROFESSOR");
        lblProfessorTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblProfessorTitulo.setBounds(24, 22, 453, 41);
        containerProfessorCadastro.add(lblProfessorTitulo);
        JTextArea taProfessorAvisos = new JTextArea();
        taProfessorAvisos.setFont(new Font("Monospaced", Font.BOLD, 12));
        taProfessorAvisos.setBackground(UIManager.getColor("Panel.background"));
        taProfessorAvisos.setForeground(new Color(0, 0, 0));
        taProfessorAvisos.setEditable(false);
        taProfessorAvisos.setWrapStyleWord(true);
        taProfessorAvisos.setLineWrap(true);
        taProfessorAvisos.setBounds(30, 335, 259, 53);
        containerProfessorCadastro.add(taProfessorAvisos);
        btnProfessorSalvar.addActionListener(new ProfessorController(tfProfessoresCpf, tfProfessorCpf, tfProfessorNome, tfProfessorArea, tfProfessorPontuacao, taProfessorAvisos, taProfessoresListagem));
        btnProfessoresExcluir.addActionListener(new ProfessorController(tfProfessoresCpf,tfProfessorCpf, tfProfessorNome, tfProfessorArea, tfProfessorPontuacao, taProfessorAvisos, taProfessoresListagem));
        // Fim do Card de Cadastro - PROFESSORES
        
        
        // Início do Card de Listagem - INSCRIÇÕES
        JPanel cardInscricaoListagem = new JPanel();
        cardInscricaoListagem.setLayout(null);
        cardInscricaoListagem.setBackground(new Color(179, 16, 9));
        JPanel containerInscricaoListagem = new JPanel();
        containerInscricaoListagem.setLayout(null);
        containerInscricaoListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerInscricaoListagem.setBounds(39, 11, 523, 379);
        cardInscricaoListagem.add(containerInscricaoListagem);
        JLabel lblInscricoesTitulo = new JLabel("LISTA DE INSCRITOS");
        lblInscricoesTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblInscricoesTitulo.setBounds(24, 21, 503, 41);
        containerInscricaoListagem.add(lblInscricoesTitulo);
        taInscricoesListagem = new JTextArea();
        taInscricoesListagem.setLineWrap(true);
        JScrollPane spInscricoesListagem = new JScrollPane();
        spInscricoesListagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spInscricoesListagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spInscricoesListagem.setBounds(24, 73, 472, 165);
        spInscricoesListagem.setViewportView(taInscricoesListagem);
        containerInscricaoListagem.add(spInscricoesListagem);
        JPanel containerInscricoesBusca = new JPanel();
        containerInscricoesBusca.setLayout(null);
        containerInscricoesBusca.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerInscricoesBusca.setBounds(0, 261, 523, 118);
        containerInscricaoListagem.add(containerInscricoesBusca);
        JLabel lblInscricoesCpf = new JLabel("Digite o CPF:");
        lblInscricoesCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInscricoesCpf.setBounds(24, 22, 112, 30);
        containerInscricoesBusca.add(lblInscricoesCpf);
        tfInscricoesCodigo = new JTextField();
        tfInscricoesCodigo.setToolTipText("");
        tfInscricoesCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfInscricoesCodigo.setColumns(10);
        tfInscricoesCodigo.setBounds(138, 22, 150, 30);
        containerInscricoesBusca.add(tfInscricoesCodigo);
        JButton btnInscricoesEditar = new JButton("Editar");
        btnInscricoesEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricoesEditar.setBounds(399, 22, 95, 30);
        
        containerInscricoesBusca.add(btnInscricoesEditar);
        JButton btnInscricoesExcluir = new JButton("Excluir");
        btnInscricoesExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricoesExcluir.setBounds(296, 22, 94, 30);
        btnInscricoesExcluir.addActionListener(inscricaoController);
        containerInscricoesBusca.add(btnInscricoesExcluir);
        JButton btnInscricoesAdicionar = new JButton("+ Adicionar Inscrição");
        btnInscricoesAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricoesAdicionar.setBounds(296, 70, 198, 30);
        btnInscricoesAdicionar.setActionCommand("Card Inscrição Cadastro");
        btnInscricoesAdicionar.addActionListener(new ChangeCardlayoutListener());
        containerInscricoesBusca.add(btnInscricoesAdicionar);
        btnInscricoesEditar.setActionCommand(tfInscricoesCodigo.getText() + "," + "Card Inscrição Cadastro");
        btnInscricoesEditar.addActionListener(new ChangeCardlayoutListener());
        // Fim do Card de Listagem - INSCRIÇÕES
        
        // Início do Card de Cadastro - INSCRIÇÕES
        JPanel cardInscricaoCadastro = new JPanel();
        cardInscricaoCadastro.setBackground(new Color(179, 16, 9));
        cardInscricaoCadastro.setLayout(null);
        containerInscricaoCadastro = new JPanel();
        containerInscricaoCadastro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerInscricaoCadastro.setBounds(39, 11, 523, 379);
        cardInscricaoCadastro.add(containerInscricaoCadastro);
        containerInscricaoCadastro.setLayout(null);
        JButton btnInscricaoVoltar = new JButton("Voltar");
        btnInscricaoVoltar.setBounds(300, 335, 89, 23);
        btnInscricaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricaoVoltar.setBackground(UIManager.getColor("Button.background"));
        btnInscricaoVoltar.setActionCommand("Card Inscrição Listagem");
        btnInscricaoVoltar.addActionListener(new ChangeCardlayoutListener());
        containerInscricaoCadastro.add(btnInscricaoVoltar);
        JButton btnInscricaoSalvar = new JButton("Salvar");
        btnInscricaoSalvar.setBounds(406, 335, 89, 23);
        btnInscricaoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricaoSalvar.setBackground(UIManager.getColor("Button.background"));
        containerInscricaoCadastro.add(btnInscricaoSalvar);
        JLabel lblInscricaoProcesso = new JLabel("Processo:");
        lblInscricaoProcesso.setBounds(24, 82, 70, 30);
        lblInscricaoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        containerInscricaoCadastro.add(lblInscricaoProcesso);
        JTextField tfInscricaoProcesso = new JTextField();
        tfInscricaoProcesso.setBounds(94, 82, 188, 30);
        tfInscricaoProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfInscricaoProcesso.setColumns(10);
        containerInscricaoCadastro.add(tfInscricaoProcesso);
        JLabel lblInscricaoCpf = new JLabel("CPF:");
        lblInscricaoCpf.setBounds(310, 82, 66, 30);
        lblInscricaoCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        containerInscricaoCadastro.add(lblInscricaoCpf);
        tfInscricaoCpf = new JTextField();
        tfInscricaoCpf.setText("");
        tfInscricaoCpf.setBounds(373, 82, 123, 30);
        tfInscricaoCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfInscricaoCpf.setColumns(10);
        containerInscricaoCadastro.add(tfInscricaoCpf);
        JLabel lblInscricaoDisciplina = new JLabel("Disciplina:");
        lblInscricaoDisciplina.setBounds(24, 135, 70, 29);
        lblInscricaoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
        containerInscricaoCadastro.add(lblInscricaoDisciplina);
        btnInscricaoAdicionarDisciplina = new JButton("+ Adicionar Disciplina");
        btnInscricaoAdicionarDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInscricaoAdicionarDisciplina.setBounds(94, 134, 188, 30);
        btnInscricaoAdicionarDisciplina.setActionCommand("Card Disciplina Cadastro");
        btnInscricaoAdicionarDisciplina.addActionListener(new ChangeCardlayoutListener());
        containerInscricaoCadastro.add(btnInscricaoAdicionarDisciplina);
        cbInscricaoDisciplina = new JComboBox<String>();
        cbInscricaoDisciplina.setBackground(new Color(255, 255, 255));
        cbInscricaoDisciplina.setBounds(94, 134, 188, 30);
        cbInscricaoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
        containerInscricaoCadastro.add(cbInscricaoDisciplina);
        JLabel lblInscricaoTitulo = new JLabel("CADASTRO DE INSCRIÇÃO");
        lblInscricaoTitulo.setBounds(24, 22, 453, 41);
        lblInscricaoTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        containerInscricaoCadastro.add(lblInscricaoTitulo);
        JTextArea taInscricaoAvisos = new JTextArea();
        taInscricaoAvisos.setFont(new Font("Monospaced", Font.BOLD, 12));
        taInscricaoAvisos.setBackground(UIManager.getColor("Panel.background"));
        taInscricaoAvisos.setForeground(new Color(0, 0, 0));
        taInscricaoAvisos.setEditable(false);
        taInscricaoAvisos.setWrapStyleWord(true);
        taInscricaoAvisos.setLineWrap(true);
        taInscricaoAvisos.setBounds(30, 335, 259, 53);
        containerProfessorCadastro.add(taInscricaoAvisos);
        btnInscricaoSalvar.addActionListener(new InscricaoController(tfInscricaoCpf, tfInscricaoProcesso, cbInscricaoDisciplina, taInscricaoAvisos));
        // Fim do Card de Cadastro - INSCRIÇÕES
        
        
        
        // Início do Card de Listagem - PROCESSOS ATIVOS
        JPanel cardProcessosAtivosListagem = new JPanel();
        cardProcessosAtivosListagem.setLayout(null);
        cardProcessosAtivosListagem.setBackground(new Color(179, 16, 9));
        JPanel containerProcessosAtivosListagem = new JPanel();
        containerProcessosAtivosListagem.setLayout(null);
        containerProcessosAtivosListagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
        containerProcessosAtivosListagem.setBounds(39, 11, 523, 379);
        cardProcessosAtivosListagem.add(containerProcessosAtivosListagem);
        JLabel lblProcessosAtivosTitulo = new JLabel("LISTA DE PROCESSOS ATIVOS");
        lblProcessosAtivosTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblProcessosAtivosTitulo.setBounds(24, 21, 503, 41);
        containerProcessosAtivosListagem.add(lblProcessosAtivosTitulo);
        JTextArea taProcessosAtivosListagem = new JTextArea();
        taProcessosAtivosListagem.setLineWrap(true);
        JScrollPane spProcessosAtivosListagem = new JScrollPane();
        spProcessosAtivosListagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spProcessosAtivosListagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spProcessosAtivosListagem.setBounds(24, 73, 472, 255);
        spProcessosAtivosListagem.setViewportView(taProcessosAtivosListagem);
        containerProcessosAtivosListagem.add(spProcessosAtivosListagem);
        // Fim do Card de Listagem - PROCESSOS ATIVOS

        
        // Aqui é onde são definidas as telas (cards)
        // Cada add() desse cria a camada setando o card (1º parâmetro) com base no actionCommand do submenu do menu (2º parâmetro)
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 640, 480);
		layeredPane.setLayout(new CardLayout(0, 0));
        layeredPane.add(cardCursoListagem, "Card Curso Listagem");
        layeredPane.add(cardCursoCadastro, "Card Curso Cadastro");
        layeredPane.add(cardDisciplinaListagem, "Card Disciplina Listagem");
        layeredPane.add(cardDisciplinaCadastro, "Card Disciplina Cadastro");
        layeredPane.add(cardProfessorListagem, "Card Professor Listagem");
        layeredPane.add(cardProfessorCadastro, "Card Professor Cadastro");
        layeredPane.add(cardInscricaoListagem, "Card Inscrição Listagem");
        layeredPane.add(cardInscricaoCadastro, "Card Inscrição Cadastro");
        layeredPane.add(cardProcessosAtivosListagem, "Card ProcessosAtivos Listagem");
        
        getContentPane().add(layeredPane, BorderLayout.CENTER);
        pack();
        setVisible(true);
	}
	
	private class ChangeCardlayoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
        	String[] vet = evt.getActionCommand().split(",");
        	String cmd = vet.length > 1 ? vet[1] : evt.getActionCommand();
            CardLayout card = (CardLayout) (layeredPane.getLayout());
            card.show(layeredPane, String.valueOf(cmd));
            
            //edit
            if (evt.getActionCommand().contains("Cadastro")) {
            	
            	if (evt.getActionCommand().contains("Curso")) {
            		if (vet.length > 1) {
            			tfCursoCodigo.setText(vet[0]);
            		}
            		
            	}
            	
            	if (evt.getActionCommand().contains("Disciplina")) {
            		if (vet.length > 1) {
            			tfDisciplinaCodigo.setText(vet[0]);
            		}
            		
            	}
            	
            	if (evt.getActionCommand().contains("Profesor")) {
            		if (vet.length > 1) {
            			tfProfessorCpf.setText(vet[0]);
            		}
            		
            	}

				if (evt.getActionCommand().contains("Inscrição")) {
					if (vet.length > 1) {
						tfInscricaoCpf.setText(vet[0]);
					}
					
				}
				
            }
            
            // define o texto com os dados do .csv
            if (evt.getActionCommand().contains("Listagem")) {
            	String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
            	File arq = new File(path);
            	
            	if (evt.getActionCommand().contains("Curso")) {
            		arq = new File(path, "curso.csv");
            		System.out.println("Listagem de Curso");
            	}
            	
            	if (evt.getActionCommand().contains("Disciplina")) {
            		arq = new File(path, "disciplina.csv");
            		System.out.println("Listagem de Disciplina");
            	}
            	
            	if (evt.getActionCommand().contains("Profesor")) {
            		arq = new File(path, "professor.csv");
            		System.out.println("Listagem de Professor");
            	}

				if (evt.getActionCommand().contains("Inscrição")) {
					arq = new File(path, "inscricao.csv");
            		System.out.println("Listagem de Inscriçaõ");
				}
				
        	    
        	    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
        	    	String[] codigos = new String[50];
        	    	String[] nomes = new String[50];
        	    	
        	    	String linha;
        	    	int tamanho = 0;
        	    	while ((linha = buffer.readLine()) != null) {
        	    		String[] vetLinha = linha.split(";");
        	    		
        	    		codigos[tamanho] = vetLinha[0];
        	    		nomes[tamanho] = vetLinha[1];
        	    		
        	    		tamanho++;
        	    	}
        	    	
        	    	if (tamanho > 0) {
        	    		String[] disciplinaCod = new String[tamanho];
        	    		String[] disciplinaNom = new String[tamanho];
            	    	
        	    		String disciplinas = "";
            	    	for (int i = 0; i < tamanho; i++) {
            	    		disciplinaCod[i] = codigos[i];
            	    		disciplinaNom[i] = nomes[i];
            	    	}
            	    	
            	    	
            	    	for (int i = 0; i < tamanho; i++) {
            	    		
            	    		disciplinas += " Código: " + String.valueOf(disciplinaCod[i] + "\n Nome: " + disciplinaNom[i] + "\n\n");
            	    		
            	    	}
            	    	
            	    	taDisciplinasListagem.setText(disciplinas);
            	    	
            	    	if (evt.getActionCommand().contains("Curso")) {
            	    		taCursosListagem.setText(disciplinas);
                    	}
                    	
                    	if (evt.getActionCommand().contains("Disciplina")) {
                    		taDisciplinasListagem.setText(disciplinas);
                    	}
                    	
                    	if (evt.getActionCommand().contains("Profesor")) {
                    		taProfessoresListagem.setText(disciplinas);
                    	}

        				if (evt.getActionCommand().contains("Inscrição")) {
        					taInscricoesListagem.setText(disciplinas);
        				}
        	    	}
                	
                } catch (FileNotFoundException e) {
                	System.err.println(e.getMessage());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
            }
            
            // popula o combo de Cursos na tela
            if (evt.getActionCommand().equals("Card Disciplina Cadastro")) {
            	String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        	    File arq = new File(path, "curso.csv");
        	    
        	    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
        	    	String[] lista = new String[50];
        	    	
        	    	String linha;
        	    	int tamanho = 1;
        	    	while ((linha = buffer.readLine()) != null) {
        	    		String[] vetLinha = linha.split(";");
        	    		
        	    		lista[tamanho] = vetLinha[1];
        	    		tamanho++;
        	    	}
        	    	
        	    	if (tamanho > 1) {
        	    		String[] cursos = new String[tamanho];
            	    	
            	    	for (int i = 0; i < tamanho; i++) {
            	    		cursos[i] = lista[i];
            	    	}
            	    	
            	    	// Se após a opção em branco a próxima opção também for vazia, aí mostra o botão
            	    	if (cursos[1] != null) {
            	    		containerDisciplinaCadastro.remove(btnDisciplinaAdicionarCurso);
            	        	cbDisciplinaCurso.setModel(new DefaultComboBoxModel<String>(cursos));
            	        } else {
            	        	containerDisciplinaCadastro.remove(cbDisciplinaCurso);
            	        }
        	    	}
                	
                } catch (FileNotFoundException e) {
                	System.err.println(e.getMessage());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
            }
            
            // popula o combo Disciplinas na tela
            if (evt.getActionCommand().equals("Card Inscrição Cadastro")) {
            	
            	String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        	    File arq = new File(path, "disciplina.csv");
        	    
        	    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
        	    	String[] lista = new String[50];
        	    	
        	    	String linha;
        	    	int tamanho = 1;
        	    	while ((linha = buffer.readLine()) != null) {
        	    		String[] vetLinha = linha.split(";");
        	    		
        	    		lista[tamanho] = vetLinha[1];
        	    		tamanho++;
        	    	}
        	    	
        	    	if (tamanho > 1) {
        	    		String[] disciplinas = new String[tamanho];
            	    	
            	    	for (int i = 0; i < tamanho; i++) {
            	    		disciplinas[i] = lista[i];
            	    	}
            	    	
            	    	// Se após a opção em branco a próxima opção também for vazia, aí mostra o botão
            	    	if (disciplinas[1] != null) {
            	    		containerInscricaoCadastro.remove(btnInscricaoAdicionarDisciplina);
            	        	cbInscricaoDisciplina.setModel(new DefaultComboBoxModel<String>(disciplinas));
            	        } else {
            	        	containerInscricaoCadastro.remove(cbInscricaoDisciplina);
            	        }
        	    	}
                	
                } catch (FileNotFoundException e) {
                	System.err.println(e.getMessage());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
            }
        }
    }
}
