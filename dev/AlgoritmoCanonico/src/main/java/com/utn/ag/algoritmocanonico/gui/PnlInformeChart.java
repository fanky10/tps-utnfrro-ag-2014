/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * pnlEntradaEscalon.java
 *
 * Created on 13-abr-2011, 20:32:57
 */
package com.utn.ag.algoritmocanonico.gui;

import com.utn.ag.algoritmocanonico.App;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author facundo
 */
public class PnlInformeChart extends JPanel {

    private CustomChartPanel cPanel;
    
    /** Creates new form pnlEscalon */
    public PnlInformeChart() {
        initComponents();
        cPanel = new CustomChartPanel(new DefaultChartModel("rechart", "Tiempo", "Y(t)"));
        pnlGrafico.add(cPanel, BorderLayout.CENTER);
    }

    private void refreshDatos() {
        cPanel.setModel(new DefaultChartModel(App.getInformeChart().getChart()));
    }



    @Override
    public String toString() {
        return "Entrada Escalon";
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGrafico = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        pnlGrafico.setBackground(new java.awt.Color(255, 255, 255));
        pnlGrafico.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlGrafico.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Ingresar Datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGrafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlGrafico;
    // End of variables declaration//GEN-END:variables

    public static void showApp() {
        JFrame frmMain = new JFrame("Algoritmos Geneticos");
        frmMain.getContentPane().add(new PnlInformeChart());
        frmMain.setLocationRelativeTo(null);
        frmMain.pack();
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setVisible(true);
    }
}
