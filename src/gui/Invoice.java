/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.IntelliJTheme;
import static gui.Checkin.jLabel11;
import static gui.Checkin.jLabel5;
import static gui.Checkin.jLabel7;
import static gui.Checkin.jLabel9;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MCTech
 */
public class Invoice extends javax.swing.JFrame {
    DecimalFormat df = new DecimalFormat("0.00");
    DecimalFormat df2 = new DecimalFormat("");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String Checkin_ID_Apd;
    String roomNo_APD;
    boolean status = false;

    /**
     * Creates new form Invoice
     */
    public Invoice() {
        initComponents();
        loadCheckin();
        loadPaymentTypes();
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                int confirmed = JOptionPane.showConfirmDialog(((Supplier<JDialog>) () -> {
                    final JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    return dialog;
                }).get(), "Are you sure you want to exit the program?", "Log-Out", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dNow = sdf2.format(new Date());
                    MySQL.iud("UPDATE `log` SET `logout` = '" + dNow + "' WHERE `id`='" + SignIn.logId + "'");
                    System.exit(0);
                }
            }
        });
    }

    public void loadCheckin() {

        try {
            ResultSet rs = MySQL.search("SELECT `checkin`.`id`, `customer`.`id`, `customer`.`fname`, `customer`.`lname`,`customer`.`nic`, `checkin_item`.`checkin_date`, `checkin_item`.`checkout_date`, `room`.`id`, `room_type`.`name` FROM `checkin` INNER JOIN `customer` ON `checkin`.`customer_id`=`customer`.`id` INNER JOIN `checkin_item` ON `checkin`.`id` = `checkin_item`.`checkin_id` INNER JOIN `room` ON `checkin_item`.`room_id` = `room`.`id` INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` INNER JOIN `checkin_status` ON `checkin_item`.`checkin_status_id`= `checkin_status`.`id` WHERE `checkin_status`.`id` = '1' ORDER BY `checkin`.`id` DESC");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("checkin.id"));
                v.add(rs.getString("customer.id"));
                v.add(rs.getString("customer.fname"));
                v.add(rs.getString("customer.lname"));
                v.add(rs.getString("customer.nic"));
                v.add(rs.getString("checkin_item.checkin_date"));
                v.add(rs.getString("checkin_item.checkout_date"));
                v.add(rs.getString("room.id"));
                v.add(rs.getString("room_type.name"));

                dtm.addRow(v);

            }
            jTable2.setModel(dtm);
//            jButton5.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCheckin(String text) {

        try {
            ResultSet rs = MySQL.search("SELECT `checkin`.`id`, `customer`.`id`, `customer`.`fname`, `customer`.`lname`,`customer`.`nic`, `checkin_item`.`checkin_date`, `checkin_item`.`checkout_date`, `room`.`id`, `room_type`.`name` FROM `checkin` INNER JOIN `customer` ON `checkin`.`customer_id`=`customer`.`id` INNER JOIN `checkin_item` ON `checkin`.`id` = `checkin_item`.`checkin_id` INNER JOIN `room` ON `checkin_item`.`room_id` = `room`.`id` INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` INNER JOIN `checkin_status` ON `checkin_item`.`checkin_status_id`= `checkin_status`.`id` WHERE (`customer`.`fname` LIKE '" + text + "%' OR `customer`.`lname` LIKE '" + text + "%' OR `checkin_item`.`checkout_date` LIKE '" + text + "%') AND `checkin_status`.`id` = '1' ORDER BY `checkin`.`id` DESC");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("checkin.id"));
                v.add(rs.getString("customer.id"));
                v.add(rs.getString("customer.fname"));
                v.add(rs.getString("customer.lname"));
                v.add(rs.getString("customer.nic"));
                v.add(rs.getString("checkin_item.checkin_date"));
                v.add(rs.getString("checkin_item.checkout_date"));
                v.add(rs.getString("room.id"));
                v.add(rs.getString("room_type.name"));

                dtm.addRow(v);

            }
            jTable2.setModel(dtm);
//            jButton5.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTotal() {
        double total = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String t = jTable1.getValueAt(i, 9).toString();
            total = total + Double.parseDouble(t);
        }

        jLabel44.setText(df.format(total));
    }

    public void loadPaymentTypes() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `payment_type`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }
            DefaultComboBoxModel dcbm = new DefaultComboBoxModel(v);
            jComboBox5.setModel(dcbm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFields() {
        jTable1.clearSelection();
        jLabel13.setText("None");
        jLabel5.setText("None");
        jLabel7.setText("None");
        jLabel9.setText("None");
        jLabel11.setText("None");
        jLabel12.setText("None");
        jLabel13.setText("None");
        jLabel32.setText("None");
        jLabel49.setText("0.00");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTable2.clearSelection();
    }

    public void printInvoice(String invoiceId) {
        try {
            InputStream filePath = getClass().getResourceAsStream("/report/Invoice.jrxml");
            
            JasperReport jr = JasperCompileManager.compileReport(filePath);
            
            ResultSet rs = MySQL.search("SELECT `invoice`.`date_time` AS `date`,`invoice_payment`.`payment` AS `total`, "
                    + "`invoice_payment`.`balance` AS `balance`, `customer`.`fname` AS `fname`, `customer`.`lname` AS `lname`, "
                    + "`address`.`line1` AS `adl1`,`address`.`line2` AS `adl2`, `city`.`name` AS `city`, `invoice`.`unique_id` AS `uid` "
                    + "FROM `invoice` INNER JOIN `invoice_payment` ON `invoice`.`id` = `invoice_payment`.`invoice_id` INNER JOIN `invoice_item` "
                    + "ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `checkin` ON `invoice_item`.`checkin_id` = `checkin`.`id` "
                    + "INNER JOIN `customer` ON `checkin`.`customer_id` = `customer`.`id` INNER JOIN `address` "
                    + "ON `customer`.`address_id` = `address`.`id` INNER JOIN `city` ON `address`.`city_id` = `city`.`id` "
                    + "WHERE `invoice`.`id` = '" + invoiceId + "'");
            
            rs.next();
            
            String date = rs.getString("date");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String name = fname + " " + lname;
            String line1 = rs.getString("adl1");
            String line2 = rs.getString("adl2");
            String city = rs.getString("city");
            String uid = rs.getString("uid");
            String total = jLabel44.getText().trim();
            String balance = jLabel49.getText().trim();
            String payment = jTextField8.getText().trim();
            
            HashMap parameters = new HashMap();
            
            parameters.put("invoice", invoiceId);
            parameters.put("date", date);
            parameters.put("name", name);
            parameters.put("line1", line1);
            parameters.put("line2", line2);
            parameters.put("city", city);
            parameters.put("uid", uid);
            parameters.put("total", total);
            parameters.put("balance", balance);
            parameters.put("payment", payment);
            
            Connection dataSource = MySQL.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check-Out & INVOICE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel19.setText("Search Check-In (Name/Checkin ID)");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Home.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Check-In ID", "Customer ID", "First Name", "Last Name", "NIC", "Check-In ", "Check-Out ", "Room No", "Room Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Customer ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("None");

        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("None");

        jLabel6.setText("Last Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("None");

        jLabel8.setText("NIC");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("None");

        jLabel10.setText("Checkin ID");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("None");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("None");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("None");

        jLabel16.setText("Room Type");

        jLabel17.setText("Room No");

        jLabel14.setText("Checkin Date");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setEnabled(false);

        jLabel15.setText("Checkout Date");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Price");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("None");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17))
                        .addGap(209, 209, 209)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel11)))))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel13))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel18)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Checkin ID", "Customer ID", "First Name", "Room No", "Room Type", "Price", "Checkin Date", "Checkout Date", "Days", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("0.00");

        jLabel45.setText("Total Payment");

        jLabel46.setText("Payment Method");

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        jLabel47.setText("Payment");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel48.setText("Balance");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel49.setText("0.00");

        jButton8.setText("Print Invoice");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jLabel44)
                            .addComponent(jLabel47)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:

        String text = jTextField3.getText();
        loadCheckin(text);

    }//GEN-LAST:event_jTextField3KeyReleased

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:

        String text = jComboBox5.getSelectedItem().toString();

        if (text.equals("Select")) {
            jTextField8.setEditable(false);
            jTextField8.setText("");
            jLabel49.setText("0.00");
            jLabel49.setForeground(Color.BLACK);
        } else {
            jTextField8.setEditable(true);
        }
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TODO add your handling code here:
        String price = jTextField8.getText();
        String text = price + evt.getKeyChar();

        //
        if (!Pattern.compile("0|0[.]|0.[1-9]|0.[1-9][0-9]|[1-9][0-9]*[.]?[0-9]{0,2}").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String payment = jTextField8.getText().trim();
        String paymentType = jComboBox5.getSelectedItem().toString().trim();
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Checkin.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (paymentType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Payment Method.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("(0)|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]-[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(payment).matches()) {            //regex for land line no :- 0((11)|(2(1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}
            JOptionPane.showMessageDialog(this, "Invalid Payment.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            //Invoice INSERT
            long mTime = System.currentTimeMillis();

            String uniqueId = "In" + mTime;

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dNow = sdf2.format(new Date());

            String CheckinID = jTable1.getValueAt(0, 0).toString();

            MySQL.iud("INSERT INTO `invoice` (`date_time`,`user_id`,`checkin_id`,`unique_id`) VALUES ('" + dNow + "','" 
                    + SignIn.userId + "','" + CheckinID + "','" + uniqueId + "')");
            //Invoice INSERT

            try {
                //Invoice payment INSERT
                ResultSet rs = MySQL.search("SELECT * FROM `invoice` WHERE `unique_id`='" + uniqueId + "'");
                rs.next();
                String Invid = rs.getString("id");

                ResultSet rs2 = MySQL.search("SELECT * FROM `payment_type` WHERE `name`='" + paymentType + "'");
                rs2.next();
                String paymentTypeId = rs2.getString("id");
                String balance = jLabel49.getText();

                MySQL.iud("INSERT INTO `invoice_payment` (`invoice_id`,`payment_type_id`,`payment`,`balance`) VALUES ('" 
                        + Invid + "','" + paymentTypeId + "','" + payment + "','" + balance + "')");
                //Invoice payment INSERT

                //Invoice item INSERT
                for (int i = 0; i < jTable1.getRowCount(); i++) {

                    String checkin_ID = jTable1.getValueAt(i, 0).toString();
                    String days = jTable1.getValueAt(i, 8).toString();
                    String roomID = jTable1.getValueAt(i, 3).toString();
                    String checkOut = jTable1.getValueAt(i, 7).toString();

                    MySQL.iud("UPDATE `checkin_item` SET `checkout_date`='" + checkOut + "', `checkin_status_id`='2' "
                            + "WHERE (`checkin_id`='" + checkin_ID + "' AND `room_id`='" + roomID + "')");
                    MySQL.iud("UPDATE `room` SET `clean_status_id`='2' WHERE `id` = '" + roomID + "'");
                    MySQL.iud("INSERT INTO `invoice_item` (`day`,`invoice_id`,`checkin_id`,`room_id`) VALUES('" 
                            + days + "','" + Invid + "','" + CheckinID + "','" + roomID + "')");
                }
                
                printInvoice(Invid);

                //Supplier
                resetFields();
                //Suplier

                //Payment
                jLabel44.setText("0.00");
                jTextField8.setText("");
                jTextField8.setEditable(false);
                jLabel49.setText("0.00");
                jComboBox5.setSelectedIndex(0);
                //Payment

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                loadCheckin();

                JOptionPane.showMessageDialog(this, "New Invoice Created.", "Success", JOptionPane.INFORMATION_MESSAGE);

                //Invoice item INSERT & Stock INSERT or UPDATE
                //                printGrn(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        try {
            if (evt.getClickCount() == 1) {
                int r = jTable2.getSelectedRow();
                if (r == -1) {
                    JOptionPane.showMessageDialog(this, "Please Select Checkin.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    status = true;
                    Checkin_ID_Apd = jTable2.getValueAt(r, 0).toString();
                    String cus_iD = jTable2.getValueAt(r, 1).toString();
                    String cus_fname = jTable2.getValueAt(r, 2).toString();
                    String cus_lname = jTable2.getValueAt(r, 3).toString();
                    String cus_nic = jTable2.getValueAt(r, 4).toString();
                    roomNo_APD = jTable2.getValueAt(r, 7).toString();
                    String roomType = jTable2.getValueAt(r, 8).toString();
                    String checkin = jTable2.getValueAt(r, 5).toString();
                    String checkout = jTable2.getValueAt(r, 6).toString();
                    Date Checkidate = sdf.parse(checkin);
                    Date checkoutdate = sdf.parse(checkout);
                    jLabel11.setText(Checkin_ID_Apd);
                    jLabel3.setText(cus_iD);
                    jLabel5.setText(cus_fname);
                    jLabel7.setText(cus_lname);
                    jLabel9.setText(cus_nic);
                    
                    ResultSet rs = MySQL.search("SELECT * FROM `room_type` INNER JOIN `room` "
                            + "ON `room_type`.`id` = `room`.`room_type_id` WHERE `room`.`id`='" + roomNo_APD + "'");
                    rs.next();
                    String price = rs.getString("price");
                    jLabel32.setText(price);
                    jLabel12.setText(roomNo_APD);
                    jLabel13.setText(roomType);
                    jDateChooser1.setDate(Checkidate);
                    jDateChooser2.setDate(checkoutdate);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String checkinID = jLabel11.getText().trim();
            String cusID = jLabel3.getText().trim();
            String cusFname = jLabel5.getText().trim();
            String roomNo = jLabel12.getText().trim();
            String roomType = jLabel13.getText().trim();
            String roomPrice = jLabel32.getText().trim();
            String checkInDate = null;
            String checkOutDate = null;
            if (jDateChooser1.getDate() != null) {
                checkInDate = sdf.format(jDateChooser1.getDate());
            }
            if (jDateChooser2.getDate() != null) {
                checkOutDate = sdf.format(jDateChooser2.getDate());
            }
            Date d1 = jDateChooser1.getDate();
            Date d2 = jDateChooser2.getDate();
            double day1 = sdf.parse(sdf.format(d1)).getTime();
            double day2 = sdf.parse(sdf.format(d2)).getTime();
            double diff = day2 - day1;
            double datediif = diff / (1000 * 60 * 60 * 24);
            double dayTot = datediif;
            double tot = Double.parseDouble(roomPrice) * dayTot;
            String total = df.format(tot);
            if (cusID.equals("None")) {
                JOptionPane.showMessageDialog(this, "Please select a Checkin.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                boolean isFound = false;
                int x = -1;
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String addedRoomNo = jTable1.getValueAt(i, 3).toString().trim();
                    String addedCheckinID = jTable1.getValueAt(i, 0).toString().trim();
                    if (!checkinID.equals(addedCheckinID) || (addedRoomNo.equals(roomNo))) {
                        isFound = true;
                        x = i;
                        break;
                    }
                }
                if (isFound) {
                    JOptionPane.showMessageDialog(this, "This Room Already Added or Checkin Ids does not match.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    Vector v = new Vector();
                    v.add(checkinID);
                    v.add(cusID);
                    v.add(cusFname);
                    v.add(roomNo);
                    v.add(roomType);
                    v.add(roomPrice);
                    v.add(checkInDate);
                    v.add(checkOutDate);
                    v.add(df2.format(dayTot));
                    v.add(total);
                    dtm.addRow(v);
                    resetFields();
                    updateTotal();
                    JOptionPane.showMessageDialog(this, "Chckin Item added to the invoice successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:

        if (jTextField8.getText().isEmpty()) {
            jLabel49.setText("0.00");
            jLabel49.setForeground(Color.BLACK);
        } else {
            String total = jLabel44.getText();
            String payment = jTextField8.getText();

            double balance = Double.parseDouble(payment) - Double.parseDouble(total);

            if (balance < 0) {
                jLabel49.setForeground(Color.RED);
            } else {
                jLabel49.setForeground(Color.GREEN);
            }

            jLabel49.setText(df.format(balance));
        }

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please Select a Invoice Item.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "Do You Want to Remove this Item?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(r);
                    resetFields();
                    updateTotal();
                    jTextField8.setText("");
                    jTextField8.setEditable(false);
                    jLabel49.setText("0.00");
                    jComboBox5.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(this, "Invoice item Removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        Home hm = new Home();
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        resetFields();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:

        try {
                long mTime = System.currentTimeMillis();

                Date d1 = jDateChooser2.getDate();
                long day1 = d1.getTime();
                String date = sdf.format(d1);
                String today = sdf.format(mTime);

                if (day1 < mTime) {
                    if (!date.equals(today)) {
                        JOptionPane.showMessageDialog(this, "Please Select Another Date.", "Warning", JOptionPane.WARNING_MESSAGE);
                        jDateChooser2.setDate(null);
                    }
                }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:

        String t = jTextField3.getText();
        String text = t + evt.getKeyChar();

        if (!Pattern.compile("^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField3KeyTyped

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

            InputStream is = Splash.class.getResourceAsStream("/resources/arc_theme_dark.theme.json");
            IntelliJTheme.setup(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

}
