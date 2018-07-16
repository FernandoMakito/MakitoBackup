/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.dats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Log {

    String caminhoArquivoLog;
    static File arquivoLog;
    static File pastaLog;
    static int diasLog = 15;
    File[] todosArquivos;

    Log() throws IOException {
        //cria pasta
        pastaLog = new File("MakitoBackupLogs");
        if (!pastaLog.exists() && !pastaLog.isDirectory()) {
            boolean success = pastaLog.mkdirs();
        }
        //cria Arquivo
        caminhoArquivoLog = "MakitoBackup" + getData() + ".log";
        arquivoLog = new File(pastaLog.getAbsolutePath() + "/" + caminhoArquivoLog);
        if (!arquivoLog.exists()) {
            boolean success = arquivoLog.createNewFile();
        }
        //apaga Antigos
        todosArquivos = pastaLog.listFiles();
        //if (todosArquivos.length > diasLog) {
            apagaArquivos();
        //}

    }

    public void info(String info) {
        try {
            //logger.info(info);
            gravarLog(horaLog() + " INFO: " + info);
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void erro(String erro) {
        try {
            gravarLog(horaLog() + " ERRO: " + erro);
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getData() {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    private String horaLog() {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static void gravarLog(String line) throws IOException {
        FileOutputStream fos = new FileOutputStream(arquivoLog, true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    private void apagaArquivos() {
        for (File arquivo : todosArquivos) {
            long diff = new Date().getTime() - arquivo.lastModified();
            if (diff > diasLog * 24 * 60 * 60 * 1000) {
                this.info("Apagando Log antigo:"+ arquivo.getName());
                arquivo.delete();
            }
        }
    }

}
