package backup.dats;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import net.lingala.zip4j.progress.ProgressMonitor;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

/**
 *
 * @author fernando
 */
public class FrmInicio extends javax.swing.JFrame {

    int qtdFiles;
    long tamanhoArquivos;
    List<String> extSelecionadas;
    List<String> addIgnorados = new ArrayList<>();
    List<String> arquivosIgnorados;
    List<String> pastasIgnoradas;
    List<String> destinosBackup = new ArrayList<>();
    List<File> arquivosAdicionais = new ArrayList<>();
    Timer timer = null;
    Boolean backupRapidoVerificado = false;
    Boolean rapido = false;
    Boolean postgresMakito = false;
    Boolean postgresEdoc = false;
    Boolean erroPostgres = false;
    Boolean backupFtp = false;
    Boolean migrarWeb = false;
    Boolean desligaPC = false;
    Boolean tamanhoOk = false;
    String pastaTemp = "";
    Log logger;
    String nomeArquivoBackup;

    public FrmInicio() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaArquivos = new javax.swing.JTable();
        txtQtdArquivos = new javax.swing.JLabel();
        salvaBackup = new javax.swing.JButton();
        txtDestino = new javax.swing.JTextField();
        progresso = new javax.swing.JProgressBar();
        statusSistema = new javax.swing.JLabel();
        txtExtSelecionandas = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btSelecionar = new javax.swing.JButton();
        txtOrigem = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btSelecionaDestino = new javax.swing.JButton();
        btExtensoes = new javax.swing.JButton();
        btIgnoraPasta = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Makito Backup 2.3");
        setLocationByPlatform(true);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 490));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tabelaArquivos.setAutoCreateRowSorter(true);
        tabelaArquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selec", "Arquivo", "Tamanho (KB)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaArquivos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tabelaArquivos.setMaximumSize(new java.awt.Dimension(80, 0));
        tabelaArquivos.setRowHeight(15);
        tabelaArquivos.getTableHeader().setReorderingAllowed(false);
        tabelaArquivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaArquivosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaArquivos);
        if (tabelaArquivos.getColumnModel().getColumnCount() > 0) {
            tabelaArquivos.getColumnModel().getColumn(0).setResizable(false);
            tabelaArquivos.getColumnModel().getColumn(0).setPreferredWidth(1);
            tabelaArquivos.getColumnModel().getColumn(1).setPreferredWidth(190);
        }

        txtQtdArquivos.setText("0 arquivos");

        salvaBackup.setBackground(new java.awt.Color(1, 109, 187));
        salvaBackup.setForeground(new java.awt.Color(255, 255, 255));
        salvaBackup.setText("Iniciar backup");
        salvaBackup.setEnabled(false);
        salvaBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaBackupActionPerformed(evt);
            }
        });

        txtDestino.setText("jTextField1");
        txtDestino.setUI(null);
        txtDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDestinoActionPerformed(evt);
            }
        });

        progresso.setToolTipText("");
        progresso.setBorderPainted(false);
        progresso.setFocusable(false);

        statusSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusSistema.setText("Aguarde, buscando arquivos");
        statusSistema.setMaximumSize(new java.awt.Dimension(380, 14));
        statusSistema.setMinimumSize(new java.awt.Dimension(380, 14));

        txtExtSelecionandas.setText("Não há extensões selecionadas");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Pasta Origem (Sistema)"));
        jPanel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel5KeyReleased(evt);
            }
        });

        btSelecionar.setBackground(new java.awt.Color(1, 109, 187));
        btSelecionar.setForeground(new java.awt.Color(255, 255, 255));
        btSelecionar.setText("...");
        btSelecionar.setToolTipText("Selecione a pasta onde o sistema está instalado");
        btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarActionPerformed(evt);
            }
        });

        txtOrigem.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtOrigem.setText("Pasta do Sistema");
        txtOrigem.setAutoscrolls(false);
        txtOrigem.setMaximumSize(new java.awt.Dimension(19, 20));
        txtOrigem.setName("txtCaminhoOrigem"); // NOI18N
        txtOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrigemActionPerformed(evt);
            }
        });
        txtOrigem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrigemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btSelecionar)
                .addComponent(txtOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurar"));

        btSelecionaDestino.setBackground(new java.awt.Color(1, 109, 187));
        btSelecionaDestino.setForeground(new java.awt.Color(255, 255, 255));
        btSelecionaDestino.setText("Pastas destino");
        btSelecionaDestino.setToolTipText("Definir onde o backup seja salvo");
        btSelecionaDestino.setName("btSelecionaDestino"); // NOI18N
        btSelecionaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionaDestinoActionPerformed(evt);
            }
        });

        btExtensoes.setText("Extensões");
        btExtensoes.setToolTipText("Escolha as extensões que deseja incluir no backup");
        btExtensoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExtensoesActionPerformed(evt);
            }
        });

        btIgnoraPasta.setText("Ignorar SubPastas");
        btIgnoraPasta.setToolTipText("Escolha as pastas que NÂO deseja incluir no backup");
        btIgnoraPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIgnoraPastaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btExtensoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btIgnoraPasta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSelecionaDestino))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btSelecionaDestino)
                .addComponent(btExtensoes)
                .addComponent(btIgnoraPasta))
        );

        jMenu1.setText("Avançado");
        jMenu1.setToolTipText("Clique para configurar opções do backup");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(progresso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtExtSelecionandas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 4, Short.MAX_VALUE)
                                .addComponent(statusSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(txtDestino)
                                        .addGap(34, 34, 34))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtQtdArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(salvaBackup)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(txtExtSelecionandas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQtdArquivos)
                        .addComponent(salvaBackup)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progresso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        salvaBackup.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        String caminho = selecionaPasta();
        if (!"".equals(caminho)) {
            try {
                txtOrigem.setText(caminho);
                Configuracoes cfg = new Configuracoes();
                cfg.setPropriedade("pasta_origem", caminho);
                conferirSlBase(caminho);
                try {
                    listaArquivos();
                } catch (IOException ex) {
                    logger.erro(ex.getMessage());
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btSelecionarActionPerformed
    private void conferirSlBase(String caminho) {
        try {
            //verificar SLBASE
            Configuracoes cfg = new Configuracoes();
            String arquivo = caminho + "\\SLBASE.DAT";
            if (new File(arquivo).exists() && cfg.getPropriedade("makitoPost_backup").equals("false") && cfg.getPropriedade("backup_facil").equals("false") && cfg.getPropriedade("backupPorParametros").equals("false")) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "<html>Ambiente <b>PostgreSQL</b> detectado.<br> Deseja configurar a conexão agora?</html>", "Configurar PostgreSQL", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    //configurar
                    FrmPost frm = new FrmPost();
                    Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
                    frm.setIconImage(im);
                    frm.setVisible(true);
                    frm.addComponentListener(new ComponentAdapter() {
                        public void componentHidden(ComponentEvent e) {
                            try {
                                ePostgres();
                                getExtSelecionadas();
                            } catch (IOException ex) {
                                logger.erro(ex.getMessage());
                            }
                        }
                    });
                }
            } else if (new File(arquivo).exists() && cfg.getPropriedade("makitoPost_backup").equals("false") && cfg.getPropriedade("backupPorParametros").equals("true")) {
                //entra aqui se for backup pelo parametros e seta a configuração do postgres pelo slbase
                gravaSlBase(arquivo);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gravaSlBase(String arquivo) {
        try {
            Configuracoes cfg = new Configuracoes();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                int i = 0;
                for (String line; (line = br.readLine()) != null;) {
                    switch (i) {
                        case 0:
                            cfg.setPropriedade("servidor_post", line);
                            break;
                        case 1:
                            cfg.setPropriedade("usuario_post", line);
                            break;
                        case 2:
                            cfg.setPropriedade("senha_post", line);
                            break;
                        case 3:
                            cfg.setPropriedade("banco_post", line);
                            break;
                        case 4:
                            cfg.setPropriedade("porta_post", line);
                            break;
                    }
                    i++;
                }
                cfg.setPropriedade("makitoPost_backup", "true");
                cfg.setPropriedade("caminho_post", caminhoPastaBinPostgres());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String caminhoPastaBinPostgres() {
        String[] caminhosPossiveis = new String[]{
            "C:\\Program Files (x86)\\PostgreSQL\\",
            "C:\\Program Files\\PostgreSQL\\",
            "C:\\PostgreSQL\\",
            "D:\\Program Files (x86)\\PostgreSQL\\",
            "D:\\Program Files\\PostgreSQL\\",
            "D:\\PostgreSQL\\"
        };
        String retorno = "C:\\Program Files (x86)\\PostgreSQL\\9.3\\bin";
        for (int i = 0; i < caminhosPossiveis.length; i++) {
            if (new File(caminhosPossiveis[i]).exists()) {
                File[] subPastas = new File(caminhosPossiveis[i]).listFiles();
                for (File pasta : subPastas) {
                    if (pasta.getName().contains(".")) {
                        retorno = caminhosPossiveis[i] + pasta.getName() + "\\bin";
                    }
                }
            }
        }
        return retorno;
    }

    private String getSiglaCliente() throws FileNotFoundException, FileNotFoundException, IOException {
        String arquivo = "MakitoFtp.txt";
        String sigla = "";
        if (new File(arquivo).exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                int i = 0;
                for (String line; (line = br.readLine()) != null;) {
                    if (i == 1) {
                        sigla = line;
                    }
                    i++;
                }
            }
            return "Makito(" + sigla + ")";
        } else {
            return "BackupMakito";
        }

    }
    private void tabelaArquivosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaArquivosMouseReleased
        atualizaQtdFiles();
    }//GEN-LAST:event_tabelaArquivosMouseReleased

    private void salvaBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaBackupActionPerformed
        try {
            selecionaCaminhoDestino();
        } catch (IOException | InterruptedException ex) {
            logger.erro(ex.getMessage());
        }


    }//GEN-LAST:event_salvaBackupActionPerformed

    private void txtOrigemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrigemKeyReleased
        try {
            listaArquivos();
        } catch (IOException ex) {
            logger.erro(ex.getMessage());
        }
    }//GEN-LAST:event_txtOrigemKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setTitle("Makito Backup 2.4");
        EnviarParaMakitoWeb();
        try {
            logger = new Log();
            Configuracoes cfg = new Configuracoes();
            //altera nome do arquivo se for BackupMakito
            if (cfg.getPropriedade("nome_arquivo").equals("BackupMakito")) {
                cfg.setPropriedade("nome_arquivo", getSiglaCliente());
            }
            nomeArquivoBackup = cfg.getPropriedade("nome_arquivo") + dataBackup() + ".zip";
            String origem = cfg.getPropriedade("pasta_origem");
            if (new File(origem).isDirectory()) {
                txtOrigem.setText(origem);
                conferirSlBase(origem);
            }
            String destino = cfg.getPropriedade("pasta_destino");
            if (new File(destino).getParent() != null) {
                txtDestino.setText(destino);
            }
            ePostgres();
            if (origem.equals("")) {
                String caminhoAtual = getAtualPath();
                if (caminhoAtual.toLowerCase().contains("sis_lj") || caminhoAtual.toLowerCase().contains("makito")) {
                    txtOrigem.setText(getAtualPath());
                    conferirSlBase(caminhoAtual);
                } else {
                    txtOrigem.setText("Pasta do sistema");
                    txtOrigem.selectAll();
                }
            }
            getExtSelecionadas();
            backupRapidoVerificado = false;
            listaArquivos();

        } catch (IOException ex) {
            logger.erro(ex.getMessage());
        }

    }//GEN-LAST:event_formWindowOpened

    private void verificarBkRapido() {
        try {
            Configuracoes cfg = new Configuracoes();
            defineCaminhoBackup();
            if (cfg.getPropriedade("backup_facil").equals("true") && !txtDestino.getText().equals("")) {
                File pastaOrigem = new File(cfg.getPropriedade("pasta_origem"));
                String pastaDestino = new File(txtDestino.getText()).getParent();
                String exts = cfg.getPropriedade("extensoes_ativas");
                File pasta = new File(pastaDestino);
                if (pastaOrigem.isDirectory() && pasta.isDirectory() && (!exts.equals("") || (postgresEdoc || postgresMakito))) {
                    rapido = true;
                    iniciaBackupRapido();
                } else {
                    if (!pastaOrigem.isDirectory()) {
                        JOptionPane.showMessageDialog(null, "Não foi possivel iniciar o backup automaticamente\n Não foi possível encontrar a pasta do sistema");
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possivel iniciar o backup automaticamente\n realize um backup manual primeiramente");
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            logger.erro(ex.getMessage());
        } catch (IOException ex) {
            logger.erro(ex.getMessage());
        }
    }

    private void iniciaBackupRapido() {
        Object[] options1 = {"Interromper"};
        final JPanel panel = new JPanel();
        final JLabel lbl = new JLabel("<html>Iniciando backup rápido em <b>5 segundos..</b> <br><br> O arquivo será salvo em: <u><b><br>" + destinosBackup.toString().replace(",", ",<br>") + "</b></u></html>");
        panel.add(lbl);
        timer = new Timer(1000, new ActionListener() {
            int segundos = 5;
            String pontos = "..";

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 0) {
                    switch (segundos) {
                        case 4:
                            pontos = ".";
                            break;
                        case 3:
                            pontos = "...";
                            break;
                        case 2:
                            pontos = "..";
                            break;
                        case 1:
                            pontos = ".";
                            break;
                    }

                    lbl.setText("<html>Iniciando backup rápido em <b>" + String.valueOf(segundos) + " segundos" + pontos + "</b> <br><br> O arquivo será salvo em: <u><b><br>" + destinosBackup.toString().replace(",", ",<br>") + "</b></u></html>");
                    segundos--;
                } else {
                    timer.stop();
                    JOptionPane.getRootFrame().dispose();
                    try {
                        iniciaBackupAuto();
                    } catch (InterruptedException ex) {
                        logger.erro(ex.getMessage());
                    }
                }
            }
        });
        timer.start();
        int result = JOptionPane.showOptionDialog(null, panel, "Backup Rápido",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options1, null);

        if (result == JOptionPane.YES_OPTION) {
            timer.stop();
            rapido = false;
        }
    }

    private void iniciaBackupAuto() throws InterruptedException {
        try {
            logger.info("------------------------------------------------------------------------------------------------------");
            logger.info("Backup iniciado automaticamente por " + System.getProperty("user.name"));
            if (!ePostgres()) {
                copiarArquivos();
            } else {
                while (!conferePostgres()) {
                    conferePostgres();
                }
                backupPostgres();
            }
        } catch (IOException ex) {
            logger.erro(ex.getMessage());
        }
    }
    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        FrmConfig frm = new FrmConfig();
        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);
        frm.setVisible(true);
        frm.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                try {
                    ePostgres();
                    getExtSelecionadas();
                } catch (IOException ex) {
                    logger.erro(ex.getMessage());
                }
            }
        });
    }//GEN-LAST:event_jMenu1MouseClicked

    private void txtDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDestinoActionPerformed

    }//GEN-LAST:event_txtDestinoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btExtensoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExtensoesActionPerformed
        FrmExtensoes frm = new FrmExtensoes();
        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);
        frm.setVisible(true);
        frm.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                try {
                    getExtSelecionadas();
                    listaArquivos();
                } catch (IOException ex) {
                    logger.erro(ex.getMessage());
                }
            }
        });
    }//GEN-LAST:event_btExtensoesActionPerformed

    private void btIgnoraPastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIgnoraPastaActionPerformed
        FrmSubPastas frm = new FrmSubPastas();
        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);
        frm.setVisible(true);
        frm.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                try {
                    getExtSelecionadas();
                    listaArquivos();
                } catch (IOException ex) {
                    logger.erro(ex.getMessage());
                }
            }
        });
    }//GEN-LAST:event_btIgnoraPastaActionPerformed

    private void txtOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrigemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrigemActionPerformed

    private void btSelecionaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionaDestinoActionPerformed
        FrmDestinos frm = new FrmDestinos();
        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);
        frm.setVisible(true);
    }//GEN-LAST:event_btSelecionaDestinoActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void jPanel5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyReleased

    }//GEN-LAST:event_jPanel5KeyReleased
    private boolean verificaFtp() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        boolean tudoOk = true;
        if (cfg.getPropriedade("ftp_servidor").equals("")) {
            cfg.setPropriedade("ftp_servidor", JOptionPane.showInputDialog(this, "Qual o endereço do servidor FTP?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("ftp_usuario").equals("")) {
            cfg.setPropriedade("ftp_usuario", JOptionPane.showInputDialog(this, "Qual o usuário do servidor FTP?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("ftp_senha").equals("")) {
            cfg.setPropriedade("ftp_senha", JOptionPane.showInputDialog(this, "Qual a senha do servidor FTP?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("ftp_caminho").equals("")) {
            cfg.setPropriedade("ftp_caminho", JOptionPane.showInputDialog(this, "Qual a pasta do servidor FTP?"));
            tudoOk = false;
        }
        return tudoOk;
    }

    private void enviaFTP() throws IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FileNotFoundException, FTPDataTransferException, FTPAbortedException, FTPListParseException {
        progresso.setStringPainted(true);
        statusSistema.setText("Conectando no servidor de FTP");
        while (!verificaFtp()) {
            verificaFtp();
        }
        final FTPClient client = new FTPClient();
        try {
            Configuracoes cfg = new Configuracoes();
            String host = cfg.getPropriedade("ftp_servidor");
            String porta = cfg.getPropriedade("ftp_porta");
            String user = cfg.getPropriedade("ftp_usuario");
            String pass = cfg.getPropriedade("ftp_senha");
            String nomeArquivo = cfg.getPropriedade("nome_arquivo");
            File arquivo = new File(txtDestino.getText());

            String uploadPath = cfg.getPropriedade("ftp_caminho");
            Boolean apagarLocal = Boolean.valueOf(cfg.getPropriedade("apagar_arquivo_local"));
            int diasManter = Integer.valueOf(cfg.getPropriedade("manter_arquivos_ftp"));
            logger.info("Enviando arquivo ao FTP, host: " + host + ":" + porta + " /usuário: " + user + " /pasta: " + uploadPath);
            client.setPassive(true);
            if (porta.equals("")) {
                client.connect(host);
            } else {
                client.connect(host, Integer.valueOf(porta));
            }
            client.login(user, pass);
            client.changeDirectory(uploadPath);

            List<File> arquivosEnviar = new ArrayList<>();
            arquivosEnviar.add(arquivo);
            final String tamanhoTotal = humanReadableByteCount(arquivo.length(), true);
            final int tamanhoTotalBytes = (int) arquivo.length();
            final Boolean divide = cfg.getPropriedade("dividirArquivo").equals("true") && ((tamanhoTotalBytes / 1048576) > Integer.valueOf(cfg.getPropriedade("tamanhoPartes")));
            progresso.setValue(0);
            progresso.setMaximum((int) arquivo.length());
            if (divide) {
                try {
                    //dividir arquivo em partes
                    compactarEmPartes(arquivo.toPath().toString(), cfg.getPropriedade("tamanhoPartes"));
                    arquivosEnviar.clear();
                    while (arquivosDivididos.isEmpty()) {
                        //statusSistema.setText("Dividindo arquivo em partes");
                        System.out.println("Aguardando");
                    }
                    for (String parteArquivo : arquivosDivididos) {
                        arquivosEnviar.add(new File(parteArquivo));
                    }
                    //arquivosEnviar.addAll(arquivosDivididos);
                } catch (UnsupportedEncodingException | InterruptedException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            for (final File arquivoEnvio : arquivosEnviar) {

                client.upload(arquivoEnvio, new FTPDataTransferListener() {
                    int transfBytes = 0;

                    @Override
                    public void started() {
                        //System.out.println("Iniciou");
                        logger.info("Começou a enviar o arquivo para o FTP");
                    }

                    @Override
                    public void transferred(int i) {
                        transfBytes += i;
                        setTotalEnviadoFtp(i);
                        progresso.setValue(getTotalEnviadoFtp());
                        progresso.setMaximum(tamanhoTotalBytes);
                        //statusSistema.setText("Enviando arquivo ao FTP ( " + humanReadableByteCount(transfBytes, true) + " de " + humanReadableByteCount(arquivoEnvio.length(), true) + " )");
                        if (!divide) {
                            statusSistema.setText("Enviando arquivo ao FTP ( " + humanReadableByteCount(getTotalEnviadoFtp(), true) + " de " + tamanhoTotal + " )");
                        } else {
                            statusSistema.setText("Enviando parte ao FTP (" + humanReadableByteCount(transfBytes, true) + "/" + humanReadableByteCount(arquivoEnvio.length(), true) + ") Total:(" + humanReadableByteCount(getTotalEnviadoFtp(), true) + "/" + tamanhoTotal + " )");
                        }
                    }

                    @Override
                    public void completed() {
                        logger.info("Envio ao FTP concluido, verificando integridade do arquivo...");
                    }

                    @Override
                    public void aborted() {
                        logger.erro("O envio ao FTP foi cancelado");
                    }

                    @Override
                    public void failed() {
                        logger.erro("O envio ao FTP falhou");
                    }

                });
            }

            //verificar upload
            FTPFile[] arquivos = client.list("*.zip");
            Boolean encontrouArquivo = false;

            for (FTPFile arquivoFtp : arquivos) {
                //conferencia para apagar
                if (diasManter != 999) {
                    long diff = new Date().getTime() - arquivoFtp.getModifiedDate().getTime();
                    if (diff > diasManter * 24 * 60 * 60 * 1000) {
                        if (arquivoFtp.getName().contains(nomeArquivo)) {
                            logger.info("Apagando arquivo do servidor de FTP, pois tem mais de " + String.valueOf(diasManter) + " dias. -->" + arquivoFtp.getName());
                            client.deleteFile(arquivoFtp.getName());
                        }
                    }
                }
                //conferencia se arquivo está
                if (arquivo.getName().equals(arquivoFtp.getName().replace("(FTP)", ""))) {
                    encontrouArquivo = true;
                }
            }
            if (divide) {
                //apaga arquivos divididos
                for (File arquivoApagar : arquivosEnviar) {
                    statusSistema.setText("Apagando arquivos temporários");
                    logger.info("Apagando arquivo particionado enviado ao FTP -->" + arquivoApagar.getAbsolutePath());
                    arquivoApagar.delete();
                }
            }
            if (apagarLocal && encontrouArquivo) {
                logger.info("Apagando arquivo local depois do backup no FTP -->" + arquivo.getAbsolutePath());
                arquivo.delete();
            }

            client.disconnect(true);
            if (encontrouArquivo) {
                statusSistema.setText("Arquivo enviado ao FTP com sucesso");
                logger.info("Arquivo enviado ao FTP com sucesso");
            } else {
                statusSistema.setText("Operação com FTP concluída com falhas");
                logger.info("O upload no FTP terminou, porém não foi possivel confirmar a existencia no servidor");
            }
        } catch (IOException | IllegalStateException | FTPIllegalReplyException | FTPException | NumberFormatException e) {
            if (!rapido) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao enviar o arquivo ao FTP \n" + e.getMessage(), "Erro ao enviar arquivo", JOptionPane.ERROR_MESSAGE);
            } else {
                mensagemTemporaria("Ocorreu um erro ao enviar o arquivo ao FTP \n" + e.getMessage(), "Erro ao enviar arquivo", 10, JOptionPane.ERROR_MESSAGE);
            }
            logger.erro("Erro ao enviar o arquivo ao FTP: " + e.getMessage());
        } finally {
            barraProgresso(false);
            progresso.setStringPainted(true);
        }
    }
    int totalEnviado;

    private void setTotalEnviadoFtp(int bytes) {
        totalEnviado += bytes;
    }

    private int getTotalEnviadoFtp() {
        return totalEnviado;
    }

    private String getAtualPath() throws IOException {
        String path = new File(".").getCanonicalPath();
        return path;
    }

    private void executaComandos(String arquivo) throws IOException, InterruptedException {
        final Process p = Runtime.getRuntime().exec("cmd /c start /wait " + arquivo);
        p.waitFor();
        statusSistema.setText("");
    }

    private void executaInicio() throws UnsupportedEncodingException, IOException, InterruptedException {
        Configuracoes cfg = new Configuracoes();
        String comando = cfg.getPropriedade("executa_antes");
        if (new File(comando).exists()) {
            statusSistema.setText("Executando script antes do backup");
            logger.info("Executando script antes do backup");
            executaComandos(comando);
        }
    }

    private void executaDepois() throws UnsupportedEncodingException, IOException, InterruptedException {
        Configuracoes cfg = new Configuracoes();
        String comando = cfg.getPropriedade("executa_depois");
        if (new File(comando).exists()) {
            statusSistema.setText("Executando script depois do backup");
            logger.info("Executando script depois do backup");
            executaComandos(comando);
        }
    }

    private String dataBackup() {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date date = new Date();
        String data = sdf.format(date);
        return "_" + data;
    }

    private String getDataBackup() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String data = sdf.format(date);
        return data;
    }

    private String getHoraBackup() {
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String hora = sdf.format(date);
        return hora;
    }

    private void copiarArquivos() {
        try {
            //execute no inicio
            executaInicio();
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro \n" + e.getMessage(), "Erro ao executar no inicio", JOptionPane.ERROR_MESSAGE);
        }
        //verifica espaço livre, somando mais 15% de espaço para o arquivo compactado;
        String destino = txtDestino.getText();
        if (destino.equals("")) {
            return;
        }
        String local;
        if (!pastaTemp.equals("propria")) {
            destino = pastaTemp;
            local = destino;
        } else {
            local = new File(destino).getParent();
        }

        long espacoLivre = new File(local).getFreeSpace();
        long espacoNecessario = (long) (tamanhoArquivos + (tamanhoArquivos * 0.15));
        if (espacoLivre <= espacoNecessario) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "O espaço disponível é insuficiente, é necessário aproximadamente " + humanReadableByteCount(espacoNecessario, true) + ", porém \n o espaço disponível em disco é de " + humanReadableByteCount(espacoLivre, true) + " \n Deseja continuar mesmo assim?", "Espaço em disco insuficiente", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
        new Thread(new Runnable() {
            public void run() {
                //desabilitar botoes
                habilitaComandos(false);
                //copiar arquivos
                String pastaOrigem = txtOrigem.getText();
                String pastaDestino;
                if (pastaTemp.equals("propria")) {
                    pastaDestino = new File(pastaOrigem).getParent();
                } else {
                    pastaDestino = pastaTemp;
                }
                DefaultTableModel model = (DefaultTableModel) tabelaArquivos.getModel();
                Boolean selec;
                qtdFiles = model.getRowCount();
                int filesCopiadas = 0;
                progresso.setMaximum(qtdFiles);
                progresso.setStringPainted(true);
                logger.info("Iniciando cópia de arquivos de -->" + pastaOrigem + " para a pasta temporária -->" + pastaDestino + "\\backup_makito | arquivo final -->" + txtDestino.getText());
                //cria pasta Temp
                Boolean criar = (new File(pastaDestino + "\\" + "backupMakito")).mkdirs();
                for (int i = 0; i < qtdFiles; i++) {
                    try {
                        selec = (Boolean) model.getValueAt(i, 0);
                        if (selec) {
                            String file = (String) model.getValueAt(i, 1);
                            File source, dest;
                            if (file.contains(" / ")) {
                                criaSubPastas(pastaDestino + "\\" + "backupMakito\\", file);
                            }
                            source = new File(pastaOrigem + "\\" + file.replace(" / ", "\\"));
                            dest = new File(pastaDestino + "\\" + "backupMakito" + "\\" + file.replace(" / ", "\\"));
                            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            statusSistema.setText("Copiando " + file + "  " + filesCopiadas + " de " + qtdFiles);
                            filesCopiadas++;
                        } else {
                            addIgnorados.add((String) model.getValueAt(i, 1));
                        }
                        progresso.setValue(i + 1);
                    } catch (IOException e) {
                        statusSistema.setText("Ops, ocorreu algum erro ao copiar");
                        if (rapido) {
                            mensagemTemporaria("Ocorreu um erro \n" + e.getMessage(), "Erro ao copiar os arquivos", 10, JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + e.getMessage(), "Erro ao copiar os arquivos", JOptionPane.ERROR_MESSAGE);
                        }
                        logger.erro("Erro ao copiar os arquivos" + e.getMessage());
                    }
                }
                //verificar arquivos adicionais, continua o processo na função abaixo.
                copiarArquivosAdicionais(pastaDestino + "\\" + "backupMakito");
            }
        }).start();
    }

    private File[] getArquivosPastasAdicionais(File pasta, final String ext) {
        File[] files;
        final String[] suffixes = ext.split(",");
        files = pasta.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Boolean retorno = false;
                for (String suf : suffixes) {
                    if (name.toLowerCase().endsWith(suf)) {
                        retorno = true;
                        //return true;
                    }
                }
                return retorno;
            }
        });

        return files;
    }

    private void copiarArquivosAdicionais(final String pastaDestino) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    //loop
                    Configuracoes cfg = new Configuracoes();
                    String pastasAdicionais = cfg.getPropriedade("pastas_adicionais");
                    Boolean backupAdicional = Boolean.valueOf(cfg.getPropriedade("backup_adicional"));

                    if (backupAdicional && !pastasAdicionais.equals("")) {
                        Boolean criar = (new File(pastaDestino + "\\PastasAdicionais")).mkdirs();
                        arquivosAdicionais.clear();
                        String[] pastas = pastasAdicionais.split("&");
                        String[] quebra;
                        String ext;
                        File pasta;
                        for (int j = 0; j < pastas.length; j++) {
                            //loop pelas pastas
                            quebra = pastas[j].replace("[", "").replace("]", "").split("\\{");
                            pasta = new File(quebra[0]);
                            if (!pasta.exists()) {
                                if (rapido) {
                                    mensagemTemporaria("A pasta adicional " + pasta.getPath() + " não existe.", "Erro no backup de pastas adicionais", 10, JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "A pasta adicional " + pasta.getPath() + " não existe.", "Erro no backup de pastas adicionais", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                ext = quebra[1].replace("}", "");
                                File[] files;
                                if (!ext.equals("*")) {
                                    files = getArquivosPastasAdicionais(pasta, ext);
                                    arquivosAdicionais.addAll(Arrays.asList(files));
                                } else {
                                    arquivosAdicionais.add(pasta);
                                }
                            }
                        }

                        if (arquivosAdicionais.size() > 0) {
                            qtdFiles = arquivosAdicionais.size();
                            int filesCopiadas = 0;
                            progresso.setMaximum(qtdFiles);
                            logger.info("Iniciando cópia de arquivos adicionais -->" + pastasAdicionais + " para a pasta temporária -->" + pastaDestino + "\\PastasAdicionais\\");

                            for (File arquivoCopiar : arquivosAdicionais) {
                                //loop pelos arquivos
                                try {
                                    File dest;
                                    if (!arquivoCopiar.isDirectory()) {
                                        //se for arquivo copia assim
                                        String pastaAnterior = arquivoCopiar.getParentFile().getParentFile().getName() + "\\" + arquivoCopiar.getParentFile().getName();
                                        String caminhoPasta = pastaDestino + "\\PastasAdicionais\\" + pastaAnterior;
                                        if (!new File(caminhoPasta).exists()) {
                                            new File(caminhoPasta).mkdirs();
                                        }
                                        dest = new File(caminhoPasta + "\\" + arquivoCopiar.getName());
                                        Files.copy(arquivoCopiar.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    } else {
                                        // se for a pasta inteira assim
                                        dest = new File(pastaDestino + "\\PastasAdicionais\\" + arquivoCopiar.getName());
                                        FileUtils.copyDirectory(arquivoCopiar, dest);
                                    }
                                    statusSistema.setText("Copiando " + arquivoCopiar.toPath() + "  " + filesCopiadas + 1 + " de " + qtdFiles);
                                    filesCopiadas++;
                                    progresso.setValue(filesCopiadas++);
                                } catch (IOException e) {
                                    statusSistema.setText("Ops, ocorreu algum erro ao copiar arquivos adicionais");
                                    if (rapido) {
                                        mensagemTemporaria("Ocorreu um erro \n" + e.getMessage(), "Erro ao copiar os arquivos adicionais", 10, JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + e.getMessage(), "Erro ao copiar os arquivos adicionais", JOptionPane.ERROR_MESSAGE);
                                    }
                                    logger.erro("Erro ao copiar os arquivos adicionais" + e.getMessage());
                                }
                            }
                        }

                    }
                    //fim
                    statusSistema.setText("Cópia de arquivos finalizada");
                    logger.info("Cópia de arquivos finalizada");
                    progresso.setStringPainted(false);
                    try {
                        compactar(txtDestino.getText(), pastaDestino);
                    } catch (IOException | InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + ex.getMessage(), "Erro ao compactar os arquivos", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void criaSubPastas(String destino, String arquivo) {
        String[] quebra = arquivo.split("/");
        File novaPasta = new File(destino + quebra[0].trim());
        if (!novaPasta.exists()) {
            Boolean criar = novaPasta.mkdirs();
        }
    }

    private int tipoCompactacao() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        String compac = cfg.getPropriedade("compactacao");
        int valorCompac = 5;
        if (!compac.equals("")) {
            valorCompac = Integer.parseInt(compac);
        }
        //aproveitando para verificar algumas variaveis necessárias
        setaVariaveisUteis();
        //fim verificação
        return valorCompac;
    }

    private void setaVariaveisUteis() {
        try {
            Configuracoes cfg = new Configuracoes();

            backupFtp = cfg.getPropriedade("ftp_backup").equals("true");
            desligaPC = cfg.getPropriedade("desliga_pc").equals("true");
            migrarWeb = cfg.getPropriedade("migrar_web").equals("true");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void compactar(final String destination, final String source) throws IOException, UnsupportedEncodingException, InterruptedException {
        statusSistema.setText("Criando arquivo compactado");
        logger.info("Criando arquivo compactado");
        progresso.setMaximum(100);
        progresso.setStringPainted(true);
        new Thread() {
            @Override
            public void run() {
                try {
                    // Initiate the ZipFile
                    ZipFile zipFile = new ZipFile(destination);

                    zipFile.setRunInThread(true);
                    ZipParameters parameters = new ZipParameters();
                    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
                    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                    parameters.setCompressionLevel(tipoCompactacao());
                    parameters.setIncludeRootFolder(false);

                    File folder = new File(source);
                    zipFile.addFolder(folder, parameters);

                    ProgressMonitor progressMonitor = zipFile.getProgressMonitor();

                    while (progressMonitor.getState() == ProgressMonitor.STATE_BUSY) {
                        try {
                            progresso.setValue(progressMonitor.getPercentDone());
                            File arquivoAtual = new File(progressMonitor.getFileName());
                            statusSistema.setText("Compactando " + arquivoAtual.getName());
                        } catch (Exception e) {
                            System.out.println("Errooooo");
                        }
                    }
                    if (progressMonitor.getResult() == ProgressMonitor.RESULT_ERROR) {
                        // Any exception can be retrieved as below:
                        if (progressMonitor.getException() != null) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao compactar:\n" + progressMonitor.getException().toString(), "Erro ao compactar", JOptionPane.ERROR_MESSAGE);
                            logger.erro("Ocorreu um erro ao compactar" + progressMonitor.getException().toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro desconhecido ao compactar", "Erro ao compactar", JOptionPane.ERROR_MESSAGE);
                            logger.erro("Ocorreu um erro desconhecido ao compactar");
                        }
                        apagaPasta(new File(source));
                    } else {
                        statusSistema.setText("Arquivo compactado criado");
                        logger.info("Arquivo compactado criado");
                        apagaPasta(new File(source));
                    }
                } catch (ZipException e) {
                } catch (IOException ex) {
                    mensagemTemporaria("Ocorreu um erro desconhecido ao compactar", "Erro ao compactar", 30, JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ex) {
                    logger.erro(ex.getMessage());
                } catch (FTPListParseException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    ArrayList<String> arquivosDivididos = new ArrayList<>();

    private void compactarEmPartes(final String source, final String tamPartes) throws IOException, UnsupportedEncodingException, InterruptedException {
        statusSistema.setText("Dividindo backup em partes");
        logger.info("Dividindo backup em partes");
        arquivosDivididos.clear();
        progresso.setMaximum(100);
        progresso.setStringPainted(true);
        new Thread() {
            @Override
            public void run() {
                try {
                    // Initiate the ZipFile
                    ZipFile zipFile = new ZipFile(source.replace(".zip", "(FTP).zip"));

                    zipFile.setRunInThread(true);
                    ZipParameters parameters = new ZipParameters();
                    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
                    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                    parameters.setCompressionLevel(tipoCompactacao());

                    File bk = new File(source);

                    zipFile.createZipFile(bk, parameters, true, ((Integer.valueOf(tamPartes) * 1024) * 1024));

                    ProgressMonitor progressMonitor = zipFile.getProgressMonitor();

                    while (progressMonitor.getState() == ProgressMonitor.STATE_BUSY) {
                        try {
                            progresso.setValue(progressMonitor.getPercentDone());
                            File arquivoAtual = new File(progressMonitor.getFileName());
                            statusSistema.setText("Divindo " + arquivoAtual.getName());
                        } catch (Exception e) {
                            System.out.println("Errooooo");
                        }
                    }
                    if (progressMonitor.getResult() == ProgressMonitor.RESULT_ERROR) {
                        // Any exception can be retrieved as below:
                        if (progressMonitor.getException() != null) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao dividir em partes:\n" + progressMonitor.getException().toString(), "Erro ao Dividir", JOptionPane.ERROR_MESSAGE);
                            logger.erro("Ocorreu um erro ao dividir" + progressMonitor.getException().toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro desconhecido ao dividir em partes", "Erro ao dividir", JOptionPane.ERROR_MESSAGE);
                            logger.erro("Ocorreu um erro desconhecido ao dividir");
                        }
                    } else if (progressMonitor.getResult() == ProgressMonitor.RESULT_SUCCESS) {
                        ArrayList retList = zipFile.getSplitZipFiles();
                        arquivosDivididos.addAll(retList);
                        statusSistema.setText("Arquivo dividido com sucesso");
                        logger.info("Arquivo dividido com sucesso");
                    }
                } catch (ZipException e) {
                } catch (IOException ex) {
                    mensagemTemporaria("Ocorreu um erro desconhecido ao dividir arquivo", "Erro ao dividir", 30, JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();

    }

    private void apagaPasta(File folder) throws IOException, UnsupportedEncodingException, InterruptedException, FTPListParseException {
        statusSistema.setText("Apagando arquivos temporários");
        logger.info("Apagando arquivos temporários");
        barraProgresso(true);
        //apaga backup postgres antigo
        if (postgresMakito || postgresEdoc) {
            File[] postgresBk = new File(txtOrigem.getText()).listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    Boolean retorno = false;

                    if (name.toLowerCase().endsWith(".backup")
                            && (name.toLowerCase().contains("postgresmakito")
                            || name.toLowerCase().contains("postgresedoc"))) {
                        retorno = true;
                    }
                    return retorno;
                }
            });
            for (File bkOld : postgresBk) {
                bkOld.delete();
            }
        }

        //apaga pasta temporária
        FileUtils.deleteDirectory(folder);

        statusSistema.setText("Arquivos temporários apagados");
        logger.info("Arquivos temporários apagados");
        barraProgresso(false);

        //verifica arquivos de backup antigos para apagar
        apagaAntigos();

        //Copiar para todos os destinos
        copiaParaDestinos();

    }

    private void copiaParaDestinos() {
        barraProgresso(true);
        new Thread(new Runnable() {
            public void run() {
                try {
                    File arquivoBackup = new File(txtDestino.getText());
                    verificaTamanho(arquivoBackup);
                    for (String arquivo : destinosBackup) {
                        File destinoAtual = new File(arquivo.replace("[", "").replace("]", "") + "\\" + nomeArquivoBackup);
                        if (!destinoAtual.exists()) {
                            logger.info("Copiando arquivo de " + arquivoBackup.toPath() + " para " + destinoAtual.toPath());
                            statusSistema.setText("Copiando para " + destinoAtual.getParent());
                            Files.copy(arquivoBackup.toPath(), destinoAtual.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            logger.info("Cópia concluída");
                        }
                    }
                    barraProgresso(false);
                    copiaSalvaArj();
                    finalizaBackup();
                } catch (UnsupportedEncodingException ex) {
                    logger.erro("Erro ao copiar arquivo: " + ex.getMessage());
                } catch (IOException ex) {
                    logger.erro("Erro ao copiar arquivo: " + ex.getMessage());
                }
            }
        }).start();
    }

    private void copiaSalvaArj() throws IOException {
        File arquivoBackup = new File(txtDestino.getText());
        logger.info("Copiando arquivo de " + txtDestino.getText() + " para " + txtOrigem.getText() + "\\SALVA.ARJ");
        statusSistema.setText("Copiando para arquivo SALVA.ARJ");
        Files.copy(arquivoBackup.toPath(), new File(txtOrigem.getText() + "\\SALVA.ARJ").toPath(), StandardCopyOption.REPLACE_EXISTING);
        logger.info("Cópia concluída");
    }
    long tamanhoAtualBackup;
    long ultimoTamanhoBackup;

    private void verificaTamanho(File arquivo) {
        try {
            Configuracoes cfg = new Configuracoes();
            tamanhoAtualBackup = arquivo.length();
            ultimoTamanhoBackup = Long.parseLong(cfg.getPropriedade("tamanho_arquivo"));

            logger.info("Verificando tamanho do arquivo de backup");
            if (tamanhoAtualBackup >= (ultimoTamanhoBackup - (ultimoTamanhoBackup * 0.05))) {
                //ok, tamanho aceitável
                cfg.setPropriedade("tamanho_arquivo", String.valueOf(tamanhoAtualBackup));
                logger.info("Tamanho do arquivo está ok, anterior " + (ultimoTamanhoBackup / 1024) + "KB, tamanho atual " + (tamanhoAtualBackup / 1024) + "KB");
                tamanhoOk = true;
            } else {
                tamanhoOk = false;
                logger.erro("Tamanho do arquivo está menor, anterior " + (ultimoTamanhoBackup / 1024) + "KB, tamanho atual " + (tamanhoAtualBackup / 1024) + "KB");
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EnviarParaMakitoWeb() {
        try {
            Configuracoes cfg = new Configuracoes();
            String siglaCliente = cfg.getPropriedade("sigla_migrar_web");
            String caminhoServidor = "https://sistema.makito.com.br/makitoservidorlinux/conversao/status/";
            RestAPI api = new RestAPI();
            
            JSONObject ret;
            try {
                ret = api.enviaGet(caminhoServidor+"ALISSON");
            } catch (UnirestException ex) {
                Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //String status = ret.getString("status");  
            
            //if(status.equals("CONVERTIDO")){
                //se 
            //}

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void finalizaBackup() {
        try {
            //faz backup em ftp
            if (backupFtp) {
                try {
                    enviaFTP();
                } catch (IllegalStateException | FTPIllegalReplyException | FTPException | FileNotFoundException | FTPDataTransferException | FTPAbortedException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao enviar o arquivo ao FTP \n" + ex.getMessage(), "Erro ao enviar arquivo", JOptionPane.ERROR_MESSAGE);
                } catch (FTPListParseException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (migrarWeb) {
                EnviarParaMakitoWeb();
            }

            habilitaComandos(true);
            try {
                executaDepois();
            } catch (IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + e.getMessage(), "Erro ao executar depois do backup", JOptionPane.ERROR_MESSAGE);
            }

            statusSistema.setText("Backup concluído!");
            salvaConfig();
            logger.info("Backup concluído!");
            progresso.setMaximum(1);
            progresso.setValue(1);
            if (!tamanhoOk) {
                mensagemTamanhoBackup();
            }
            String mensagem = "<html>O Backup foi finalizado! Salvo em:<u><b><br>" + destinosBackup.toString().replace(",", ",<br>") + "</b></u></html>";
            if (!desligaPC) {
                if (!rapido) {
                    JOptionPane.showMessageDialog(this, mensagem);
                }
                if (rapido) {
                    mensagemTemporaria(mensagem, "Backup Finalizado", 10, 1);
                    System.exit(0);
                }
            } else {
                mensagemTemporaria(mensagem, "Backup Finalizado", 10, 1);
                desligaPC();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mensagemTamanhoBackup() {
        String tabelaTamanhos = "<br><table border='1'><tr><td>Ultimo Backup</td><td>"
                + "Backup Atual</td></tr><tr><td><b>"
                + humanReadableByteCount(ultimoTamanhoBackup, true) + "</b></td><td><b>"
                + humanReadableByteCount(tamanhoAtualBackup, true) + "</b></td></tr></table>";
        String mensagemTamanhoErrado = "<html><div align='center'>Possivelmente o backup realizado está corrompido, <br>pois o tamanho do arquivo é incompatível com os backups anteriores.<br><b>Considere fechar o sistema e realizar novamente o backup.</br><br>" + tabelaTamanhos + "</div></html>";
        String tituloTamanhoErrado = "Tamanho do backup incompátivel";

        final JPanel panel = new JPanel();
        final JLabel lbl = new JLabel(mensagemTamanhoErrado);
        final JButton bt = new JButton("Confirmo que está correto (Não recomendado)");
        final JButton bt2 = new JButton("Fechar");
        final Object[] options1 = {bt, bt2};
        panel.add(lbl);

        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    timer.stop();
                    JOptionPane.getRootFrame().dispose();
                    Configuracoes cfg = new Configuracoes();
                    cfg.setPropriedade("tamanho_arquivo", String.valueOf(tamanhoAtualBackup));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                JOptionPane.getRootFrame().dispose();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            int segundos = 21;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 1) {
                    segundos--;
                    bt2.setText("Fechar (" + String.valueOf(segundos) + ")s");
                } else {
                    timer.stop();
                    JOptionPane.getRootFrame().dispose();
                }
            }
        });
        if (!tamanhoOk && rapido) {
            timer.start();
        }

        int result = JOptionPane.showOptionDialog(null, panel, tituloTamanhoErrado,
                JOptionPane.YES_NO_CANCEL_OPTION, 2,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION) {
            timer.stop();
        }

    }

    private void mensagemTemporaria(String texto, String titulo, final int tempo, int tipoMsg) {
        final JPanel panel = new JPanel();
        final JLabel lbl = new JLabel(texto);
        final JButton bt = new JButton("Fechar (" + String.valueOf(tempo) + ")s");
        final Object[] options1 = {bt};
        panel.add(lbl);

        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                JOptionPane.getRootFrame().dispose();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            int segundos = tempo;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 1) {
                    segundos--;
                    bt.setText("Fechar (" + String.valueOf(segundos) + ")s");
                } else {
                    timer.stop();
                    JOptionPane.getRootFrame().dispose();
                }
            }
        });
        timer.start();
        int result = JOptionPane.showOptionDialog(null, panel, titulo,
                JOptionPane.YES_NO_CANCEL_OPTION, tipoMsg,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION) {
            timer.stop();
        }
    }

    private void apagaAntigos() {
        try {
            Configuracoes cfg = new Configuracoes();
            String nomeArquivo = cfg.getPropriedade("nome_arquivo");
            int diasManter = Integer.valueOf(cfg.getPropriedade("manter_arquivos"));
            for (String destino : destinosBackup) {
                File[] todosArquivos = new File(destino.replace("[", "").replace("]", "")).listFiles();
                for (File arquivo : todosArquivos) {
                    long diff = new Date().getTime() - arquivo.lastModified();
                    if (diff > diasManter * 24 * 60 * 60 * 1000) {
                        if (getFileExtension(arquivo.getName()).toLowerCase().equals("zip") && arquivo.getName().contains(nomeArquivo)) {
                            logger.info("Apagando backup antigo:" + arquivo.getName());
                            arquivo.delete();
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void desligaPC() throws IOException {
        Object[] options1 = {"Cancelar"};
        final JPanel panel = new JPanel();
        final JLabel lbl = new JLabel("O computador será desligado em 10 segundos");
        panel.add(lbl);
        timer = new Timer(1000, new ActionListener() {
            int segundos = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 0) {
                    lbl.setText("O computador será desligado em " + String.valueOf(segundos) + " segundos");
                    segundos--;
                } else {
                    try {
                        timer.stop();
                        logger.info("Desligando o computador");
                        JOptionPane.getRootFrame().dispose();
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec("shutdown -s -t 0");
                        System.exit(0);
                    } catch (IOException ex) {
                        logger.erro(ex.getMessage());
                    }
                }
            }
        });
        timer.start();
        int result = JOptionPane.showOptionDialog(null, panel, "Desligar computador",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options1, null);

        if (result == JOptionPane.YES_OPTION) {
            timer.stop();
            desligaPC = false;
        }
    }

    private void salvaConfig() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        cfg.setPropriedade("pasta_origem", txtOrigem.getText());
        String ignorados = "";
        for (String arquivo : addIgnorados) {
            ignorados = arquivo + "<" + ignorados;
        }
        cfg.setPropriedade("arquivos_ignorados", ignorados);
        cfg.setPropriedade("dt_ultimo_backup", getDataBackup());
        cfg.setPropriedade("hr_ultimo_backup", getHoraBackup());

        if (cfg.getPropriedade("backupPorParametros").equals("true")) {
            cfg.setPropriedade("pasta_origem", cfg.getPropriedade("backupPorParametrosOrigem"));
            cfg.setPropriedade("pasta_destino", cfg.getPropriedade("backupPorParametrosDestino"));
            cfg.setPropriedade("backup_facil", cfg.getPropriedade("backupPorParametrosRapido"));
            cfg.setPropriedade("backupPorParametrosOrigem", "");
            cfg.setPropriedade("backupPorParametrosDestino", "");
            cfg.setPropriedade("backupPorParametrosRapido", "");
            cfg.setPropriedade("backupPorParametros", "false");
        }
    }

    private void habilitaComandos(Boolean ativa) {
        txtOrigem.setEnabled(ativa);
        salvaBackup.setEnabled(ativa);
        btSelecionar.setEnabled(ativa);
        tabelaArquivos.setEnabled(ativa);
        btExtensoes.setEnabled(ativa);
        btIgnoraPasta.setEnabled(ativa);
        jMenu1.setEnabled(ativa);
        tabelaArquivos.setEnabled(ativa);
        btSelecionaDestino.setEnabled(ativa);
    }

    private String selecionaPasta() {
        String pastaInicial;
        if (!new File(txtOrigem.getText()).isDirectory()) {
            pastaInicial = ".";
        } else {
            pastaInicial = txtOrigem.getText();
        }
        JFileChooser j = new JFileChooser(new File(pastaInicial));
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setDialogTitle("Selecione a pasta do sistema");
        j.setApproveButtonText("Selecionar Pasta");
        j.setAcceptAllFileFilterUsed(false);
        Integer opt = j.showOpenDialog(this);
        if (opt == 0) {
            return j.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }

    private void selecionarDestino() {
        FrmDestinos frm = new FrmDestinos();
        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);
        frm.setVisible(true);
        frm.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                try {
                    selecionaCaminhoDestino();
                } catch (IOException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void defineCaminhoBackup() {
        try {
            Configuracoes cfg = new Configuracoes();
            String destinos = cfg.getPropriedade("pasta_destino");
            String bkRapido = cfg.getPropriedade("backup_facil");
            String nomeArquivo = cfg.getPropriedade("nome_arquivo") + dataBackup() + ".zip";

            if (!destinos.contains("[") && !destinos.equals("")) {
                destinos = "[" + new File(destinos).getParent() + "],";
            }
            if (destinos.equals("")) {
                if (new File(txtOrigem.getText()).exists()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "<html>Não há nenhum destino de backup selecionado<br> Deseja informar agora?</html>", "Destino do backup", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        selecionarDestino();
                    }
                } else if (!txtOrigem.getText().equals("Pasta do sistema")) {
                    mensagemTemporaria("Informe uma pasta do sistema válida", "Pasta do sistema inválida", 5, JOptionPane.ERROR_MESSAGE);
                }
            } else {

                String[] quebra = destinos.split(",");
                List<String> caminhos = new ArrayList<>();

                caminhos.addAll(Arrays.asList(quebra));
                String inicioNome = "";

                //verifica se todos os caminhos existem
                boolean mudouCaminho = false;
                for (String destino : caminhos) {
                    destino = destino.replace("[", "").replace("]", "");
                    if (!new File(destino).exists()) {
                        //procura pasta em outras letras, as vezes pendrive muda a letra
                        char[] letrasPossiveis = "defghijklmnopqrstuvw".toUpperCase().toCharArray();
                        for (int i = 0; letrasPossiveis.length > i; i++) {
                            String novoCaminho = destino.replace(destino.substring(0, 2), String.valueOf(letrasPossiveis[i]) + ":");
                            File novoTeste = new File(novoCaminho);
                            if (novoTeste.exists()) {
                                //sugerir mudar para o caminho
                                int dialogResult = JOptionPane.showConfirmDialog(null, "<html>O caminho <b>" + destino + "</b> não foi encontrado, porém o caminho <b>" + novoCaminho + "</b> é válido, deseja alterar?</html>", "Alteração de destino", JOptionPane.YES_NO_OPTION);
                                if (dialogResult == JOptionPane.YES_OPTION) {
                                    cfg.setPropriedade("pasta_destino", cfg.getPropriedade("pasta_destino").replace(destino, novoCaminho));
                                    mudouCaminho = true;
                                }
                            }
                        }
                    }
                }
                //se mudou caminho, le novamente as configurações de destino
                if (mudouCaminho) {
                    quebra = cfg.getPropriedade("pasta_destino").split(",");
                    caminhos.clear();
                    caminhos.addAll(Arrays.asList(quebra));
                }

                //procura por disco local, para fazer mais rápido
                for (String destino : caminhos) {
                    destino = destino.replace("[", "").replace("]", "");
                    if ((destino.contains("C:") || destino.contains("D:")) && new File(destino).exists()) {
                        inicioNome = destino;
                        break;
                    }
                }
                //se não encontrou disco local faz onde der
                if (inicioNome.equals("")) {
                    for (String destino : caminhos) {
                        destino = destino.replace("[", "").replace("]", "");
                        if (new File(destino).exists()) {
                            inicioNome = destino;
                            break;
                        }
                    }
                }
                //se não encontrou nenhum lugar válido faz na pasta do sistema.
                boolean backupPastaSistema = false;

                if (inicioNome.equals("") && bkRapido.equals("true")) {
                    backupPastaSistema = true;
                    caminhos.add(txtOrigem.getText());
                    inicioNome = txtOrigem.getText();
                }

                //loop para verificar se todos os destinos existem
                destinosBackup.clear();
                File pastaDestino;
                for (String destino : caminhos) {
                    pastaDestino = new File(destino.replace("[", "").replace("]", ""));
                    if (!pastaDestino.exists()) {
                        mensagemTemporaria("<html>O caminho <b>" + pastaDestino.toPath() + "</b> não foi encontrado", "Caminho não encontrado", 10, JOptionPane.ERROR_MESSAGE);
                        logger.erro("O caminho " + pastaDestino.toPath() + " não existe, por isso não foi incluido no backup");
                    } else if (pastaDestino.exists()) {
                        destinosBackup.add(pastaDestino.toString());
                    }
                }

                if (backupPastaSistema) {
                    mensagemTemporaria("<html>Nenhum destino válido foi encontrado para realizar o backup,<br> Portanto será realizado na pasta do sistema</html>", "Erro ao iniciar o backup", 10, JOptionPane.INFORMATION_MESSAGE);
                    logger.erro("Nenhum destino válido foi encontrado, então o backup será realizado na pasta do sistema.");
                }
                if (!inicioNome.equals("")) {
                    File novoArquivo = new File(inicioNome + "\\" + nomeArquivo);
                    String nome = nomeArquivo;
                    int i = 2;
                    while (novoArquivo.exists()) {
                        nome = nomeArquivo.replace(".zip", "(" + String.valueOf(i) + ").zip");
                        novoArquivo = new File(inicioNome + "\\" + nomeArquivo.replace(".zip", "(" + String.valueOf(i) + ").zip"));
                        i++;
                    }
                    nomeArquivoBackup = nome;
                    txtDestino.setText(novoArquivo.getAbsolutePath());
                } else {
                    mensagemTemporaria("<html>Nenhum destino válido, clique em <B>Pastas Destino</B> para definir um destino do backup.</html>", "Erro ao iniciar o backup", 6, JOptionPane.INFORMATION_MESSAGE);
                    nomeArquivoBackup = "";
                    txtDestino.setText("");
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void selecionaCaminhoDestino() throws IOException, InterruptedException {

        defineCaminhoBackup();
        logger.info("------------------------------------------------------------------------------------------------------");
        logger.info("Backup iniciado manualmente por " + System.getProperty("user.name"));
        if (!ePostgres()) {
            copiarArquivos();
        } else {
            while (!conferePostgres()) {
                conferePostgres();
            }
            backupPostgres();
        }
    }

    private boolean ePostgres() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        Boolean makito = cfg.getPropriedade("makitoPost_backup").equals("true");
        postgresMakito = makito;
        Boolean edoc = cfg.getPropriedade("edoc_backup").equals("true");
        postgresEdoc = edoc;
        //aproveita para verificar a pasta para copiar os arquivos temporários
        pastaTemp = cfg.getPropriedade("pasta_temp");
        return makito || edoc;
    }

    private boolean conferePostgres() throws IOException {
        Configuracoes cfg = new Configuracoes();
        boolean tudoOk = true;
        if (cfg.getPropriedade("caminho_post").equals("")) {
            cfg.setPropriedade("caminho_post", JOptionPane.showInputDialog(this, "Qual o endereço do pasta Bin do PostgreSQL?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("servidor_post").equals("")) {
            cfg.setPropriedade("servidor_post", JOptionPane.showInputDialog(this, "Qual o do servidor PostgreSQL?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("porta_post").equals("")) {
            cfg.setPropriedade("porta_post", JOptionPane.showInputDialog(this, "Qual a porta do servidor PostgreSQL?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("usuario_post").equals("")) {
            cfg.setPropriedade("usuario_post", JOptionPane.showInputDialog(this, "Qual o nome do usuário PostgreSQL?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("senha_post").equals("")) {
            cfg.setPropriedade("senha_post", JOptionPane.showInputDialog(this, "Qual a senha do PostgreSQL?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("makitoPost_backup").equals("true") && cfg.getPropriedade("banco_post").equals("")) {
            cfg.setPropriedade("banco_post", JOptionPane.showInputDialog(this, "Qual o nome do banco de dados do Makito?"));
            tudoOk = false;
        }
        if (cfg.getPropriedade("edoc_backup").equals("true") && cfg.getPropriedade("banco_edoc").equals("")) {
            cfg.setPropriedade("banco_edoc", JOptionPane.showInputDialog(this, "Qual o nome do banco de dados do E-doc?"));
            tudoOk = false;
        }
        return tudoOk;

    }

    private void listaArquivos() throws IOException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                String caminho = txtOrigem.getText();
                statusSistema.setText("Buscando arquivos...");
                barraProgresso(true);
                File folder = new File(caminho);
                String nomeArquivo;
                tamanhoArquivos = 0;
                try {
                    ePostgres();
                } catch (IOException ex) {
                    logger.erro(ex.getMessage());
                }
                if (folder.isDirectory()) {
                    File[] todosArquivos = folder.listFiles();
                    DefaultTableModel model = (DefaultTableModel) tabelaArquivos.getModel();
                    model.setRowCount(0);
                    for (File arquivo : todosArquivos) {
                        if (arquivo.isFile()) {
                            if (filtraArquivos(arquivo.getName())) {
                                model.addRow(new Object[]{selecionaArquivos(arquivo.getName()), arquivo.getName(), (arquivo.length() / 1024)});
                            }
                        } else if (arquivo.isDirectory() && !pastasIgnoradas.contains(arquivo.getName().toLowerCase())) {
                            File[] novaPasta = arquivo.listFiles();
                            for (File arquivoInterno : novaPasta) {
                                if (filtraArquivos(arquivoInterno.getName())) {
                                    nomeArquivo = arquivo.getName() + " / " + arquivoInterno.getName();
                                    model.addRow(new Object[]{selecionaArquivos(nomeArquivo), nomeArquivo, (arquivoInterno.length() / 1024)});
                                }
                            }
                        }
                    }
                }
                atualizaQtdFiles();
                barraProgresso(false);
                statusSistema.setText("Pronto para iniciar o backup");
                if (!backupRapidoVerificado) {
                    verificarBkRapido();
                }
                backupRapidoVerificado = true;
            }
        });
        t.start();
    }

    private void barraProgresso(Boolean ativa) {
        progresso.setMaximum(1);
        progresso.setIndeterminate(ativa);
        if (ativa) {
            progresso.setValue(1);
        } else {
            progresso.setValue(0);
        }
    }

    private void atualizaQtdFiles() {
        DefaultTableModel model = (DefaultTableModel) tabelaArquivos.getModel();
        qtdFiles = model.getRowCount();
        tamanhoArquivos = 0;
        for (int i = 0; i < qtdFiles; i++) {
            Boolean s = (Boolean) model.getValueAt(i, 0);
            if (!s) {
                qtdFiles--;
            } else {
                tamanhoArquivos += ((long) model.getValueAt(i, 2) * 1024);
            }
        }
        txtQtdArquivos.setText(String.valueOf(qtdFiles) + " arquivos, cerca de " + humanReadableByteCount(tamanhoArquivos, true));
        File pasta = new File(txtOrigem.getText());
        if (qtdFiles == 0 && !postgresMakito && !postgresEdoc) {
            if (pasta.isDirectory()) {
                JOptionPane.showMessageDialog(this, "Nenhum arquivo encontrado nessa pasta, \nSelecione a pasta do sistema e as extensões desejadas");
            } else if (txtOrigem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione a pasta do sistema primeiramente");
            }
            salvaBackup.setEnabled(false);
        } else if ((qtdFiles > 0 || (postgresMakito || postgresEdoc)) && pasta.isDirectory()) {
            salvaBackup.setEnabled(true);
        }

    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1024 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private void getExtSelecionadas() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        String exts = cfg.getPropriedade("extensoes_ativas");
        String pastaSistema = cfg.getPropriedade("pasta_origem");
        if (new File(pastaSistema).isDirectory()) {
            if (!new File(txtOrigem.getText()).isDirectory()) {
                txtOrigem.setText(pastaSistema);
                listaArquivos();
            }
        }
        String pastaDestino = cfg.getPropriedade("pasta_destino");
        if (!txtDestino.getText().equals(pastaDestino)) {
            txtDestino.setText(pastaDestino);
        }
        String frase = "";
        extSelecionadas = Arrays.asList(exts.split("/"));
        if (!exts.equals("")) {
            frase = "<html>Extensões: <b>" + extSelecionadas.toString() + "</b>";
            if (postgresMakito && postgresEdoc) {
                frase += "<a color='blue'> /PostgreSQL (Makito e E-doc)</a>";
            } else if (postgresMakito && !postgresEdoc) {
                frase += "<a color='blue'> /PostgreSQL (Makito)</a>";
            } else if (!postgresMakito && postgresEdoc) {
                frase += "<a color='blue'> /PostgreSQL (E-doc)</a>";
            }
            frase += "</html>";
            txtExtSelecionandas.setText(frase);
        } else if (!postgresEdoc && !postgresMakito) {
            txtExtSelecionandas.setText("Nenhuma extensão selecionada");
        } else if (postgresEdoc && postgresMakito) {
            txtExtSelecionandas.setText("<html><a color='blue'> PostgreSQL (Makito e E-doc)</a><html>");
        } else if (postgresEdoc) {
            txtExtSelecionandas.setText("<html><a color='blue'> PostgreSQL (E-doc)</a><html>");
        } else {
            txtExtSelecionandas.setText("<html><a color='blue'> PostgreSQL (Makito)</a></html>");
        }
        getPastasIgnoradas();
    }

    private void getPastasIgnoradas() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        String exts = cfg.getPropriedade("pastas_ignoradas");
        pastasIgnoradas = Arrays.asList(exts.toLowerCase().split(","));
        getArquivosIgnorados();

    }

    private void getArquivosIgnorados() throws UnsupportedEncodingException, IOException {
        Configuracoes cfg = new Configuracoes();
        String files = cfg.getPropriedade("arquivos_ignorados");
        arquivosIgnorados = Arrays.asList(files.split("<"));
    }

    private boolean filtraArquivos(String nome) {
        if (!nome.toLowerCase().contains("slccep") && !nome.toLowerCase().contains("slibpt")) {
            String ext = getFileExtension(nome).toLowerCase();
            return extSelecionadas.contains(ext);
        } else {
            return false;
        }
    }

    private boolean selecionaArquivos(String nome) {
        return !arquivosIgnorados.contains(nome);
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    boolean finalizadoMakito = false, finalizadoEdoc = false, iniciouCopia = false;

    private synchronized void backupPostgres() throws IOException, InterruptedException {
        if (!txtDestino.getText().equals("")) {
            if (postgresMakito) {
                Runnable r = new Runnable() {
                    public void run() {
                        try {
                            logger.info("Fazendo backup do PostgreSQL - Makito");
                            fazBackupPostgres("Makito");
                            finalizadoMakito = true;
                            chamaCopiaPostgres();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                new Thread(r).start();
            } else {
                finalizadoMakito = true;
            }
            if (postgresEdoc) {
                Runnable r = new Runnable() {
                    public void run() {
                        try {
                            logger.info("Fazendo backup do PostgreSQL - Edoc");
                            fazBackupPostgres("Edoc");
                            finalizadoEdoc = true;
                            chamaCopiaPostgres();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FrmInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                new Thread(r).start();
            } else {
                finalizadoEdoc = true;
            }
        }
    }

    private void chamaCopiaPostgres() {
        //System.out.println("Testando");
        if (finalizadoEdoc && finalizadoMakito && !iniciouCopia) {
            //System.out.println("copiando");
            copiarArquivos();
            barraProgresso(false);
        }
    }

    public synchronized void fazBackupPostgres(final String tipo) throws InterruptedException {
        try {
            habilitaComandos(false);
            statusSistema.setText("Fazendo backup do PostgreSQL - " + tipo);
            barraProgresso(true);
            Configuracoes cfg = new Configuracoes();
            List<String> comandos = new ArrayList<>();
            String caminho_post = cfg.getPropriedade("caminho_post");
            String servidor_post = cfg.getPropriedade("servidor_post");
            String porta_post = cfg.getPropriedade("porta_post");
            String usuario_post = cfg.getPropriedade("usuario_post");
            String banco;
            if (tipo.equals("Makito")) {
                banco = cfg.getPropriedade("banco_post");
            } else {
                banco = cfg.getPropriedade("banco_edoc");
            }
            logger.info("Iniciando backup do PostgreSQL - " + tipo + ", pasta_bin:" + caminho_post + " servidor: " + servidor_post + ":" + porta_post + " usuario: " + usuario_post);
            comandos.add(caminho_post + "\\pg_dump.exe");
            //comandos.add("-i");Não funciona postgres 10
            comandos.add("-h");
            comandos.add(servidor_post);
            comandos.add("-p");
            comandos.add(porta_post);
            comandos.add("-U");
            comandos.add(usuario_post);
            comandos.add("-F");
            comandos.add("c");
            comandos.add("-f");
            comandos.add(txtOrigem.getText() + "\\Postgres" + tipo + dataBackup() + ".backup");
            comandos.add(banco);
            ProcessBuilder builder = new ProcessBuilder(comandos);
            builder.environment().put("PGPASSWORD", cfg.getPropriedade("senha_post"));
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                int dialogResult = JOptionPane.showConfirmDialog(null, "Ocorreu o seguinte erro: \n" + line + "\nDeseja continuar o backup dos arquivos?", "Erro no Backup do PostgreSQL", JOptionPane.YES_NO_OPTION);
                logger.erro("Erro no backup Postgres - " + tipo + ": " + line);
                if (dialogResult == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    erroPostgres = true;
                }
            }
            if (!erroPostgres) {
                File postgresBk = new File(txtOrigem.getText() + "\\Postgres" + tipo + dataBackup() + ".backup");
                DefaultTableModel model = (DefaultTableModel) tabelaArquivos.getModel();
                model.addRow(new Object[]{true, postgresBk.getName(), (postgresBk.length() / 1024)});
                atualizaQtdFiles();
                statusSistema.setText("Backup Postgres " + tipo + " finalizado");
                logger.info("Backup Postgres " + tipo + " finalizado");
            }

        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + e.getMessage(), "Erro no backup do PostgreSQL - " + tipo, JOptionPane.ERROR_MESSAGE);
            logger.erro("Erro no backup Postgres " + tipo + ": " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExtensoes;
    private javax.swing.JButton btIgnoraPasta;
    private javax.swing.JButton btSelecionaDestino;
    private javax.swing.JButton btSelecionar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar progresso;
    private javax.swing.JButton salvaBackup;
    private javax.swing.JLabel statusSistema;
    private javax.swing.JTable tabelaArquivos;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JLabel txtExtSelecionandas;
    private javax.swing.JTextField txtOrigem;
    private javax.swing.JLabel txtQtdArquivos;
    // End of variables declaration//GEN-END:variables
}
