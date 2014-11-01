/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.viajante.gui;

import com.utn.ag.viajante.impl.Exhaustivo;
import com.utn.ag.viajante.model.Constants;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author fanky
 */
public class FrmViajanteStaFe extends javax.swing.JFrame {

    private ImagePanel pnlMapa;

    /**
     * Creates new form FrmViajante2
     */
    public FrmViajanteStaFe() {
        initComponents();
        init();
    }

    private void init() {
        setTitle("AG - Viajante Sta Fe");
        pnlMapa = new ImagePanel(ImagePanel.IMG_STA_FE, Constants.COORENADAS_CIUDADES_STA_FE);
        scrollPane.setViewportView(pnlMapa);
    }

    private void ejecutaAlgoritmoExhaustivo() {
        pnlMapa.clearMap();
        lblEstado.setText("Cargando mapa...");
        new Thread() {
            @Override
            public void run() {
                Exhaustivo e = new Exhaustivo(Constants.DISTANCIAS_CIUDADES_SANTA_FE, Constants.NOMBRES_CIUDADES_SANTA_FE, 0);

                e.recorrer();
                List<Integer> mejorRecorrido = e.getMejorRecorrido();
                int distancia = e.getMejorDistancia();
                Integer[] arrMejorRecorrido = mejorRecorrido.toArray(new Integer[mejorRecorrido.size()]);

                pnlMapa.dibujarRecorrido(ArrayUtils.toPrimitive(arrMejorRecorrido));
                lblEstado.setText(
                        "Ciudad Inicial: " + Constants.NOMBRES_CIUDADES_SANTA_FE[mejorRecorrido.get(0)] + " recorrido de " + distancia + "km");
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAExhaustivo = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        lblEstado = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAExhaustivo.setText("AlgoritmoExhaustivo");
        btnAExhaustivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAExhaustivoActionPerformed(evt);
            }
        });
        jPanel3.add(btnAExhaustivo);

        btnClean.setText("Limpiar Mapa");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });
        jPanel3.add(btnClean);

        lblEstado.setText("Estado:");
        lblEstado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstado))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAExhaustivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAExhaustivoActionPerformed
        ejecutaAlgoritmoExhaustivo();
    }//GEN-LAST:event_btnAExhaustivoActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        pnlMapa.clearMap();
    }//GEN-LAST:event_btnCleanActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmViajanteArgentina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmViajanteArgentina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmViajanteArgentina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmViajanteArgentina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmViajanteStaFe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAExhaustivo;
    private javax.swing.JButton btnClean;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
