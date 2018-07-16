/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.dats;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fernando
 */
public class BackupDats {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
        }
        FrmInicio frm = new FrmInicio();
        String argPastaOrigem, argPastaDestino = "";

        //recebe argumentos
        if (args.length > 1) {
            //se tiver dois argumentos entra aqui, se não vai direto
            argPastaOrigem = args[0];
            argPastaDestino = args[1];

            Configuracoes cfg = new Configuracoes();

            cfg.setPropriedade("backupPorParametros", "true");
            cfg.setPropriedade("backupPorParametrosOrigem", cfg.getPropriedade("pasta_origem"));
            cfg.setPropriedade("backupPorParametrosDestino", cfg.getPropriedade("pasta_destino"));
            cfg.setPropriedade("backupPorParametrosRapido", cfg.getPropriedade("backup_facil"));
            
            Boolean criar = (new File(argPastaDestino)).mkdirs();

            cfg.setPropriedade("pasta_destino", "[" + argPastaDestino + "],");
            cfg.setPropriedade("pasta_origem", argPastaOrigem);
            cfg.setPropriedade("backup_facil", "true");
        }

        Image im = Toolkit.getDefaultToolkit().getImage("icon.png");
        frm.setIconImage(im);     
        frm.setVisible(true);
    }

}
