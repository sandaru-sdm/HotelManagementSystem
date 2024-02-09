/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author MCTech
 */
public class Checkin extends javax.swing.JFrame {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    boolean checkin_update = false;
    boolean btn_add = false;
    String Checkin_ID_Apd;
    String roomNo_APD;
    boolean status = false;

    /**
     * Creates new form Check-in
     */
    public Checkin() {
        initComponents();
        loadRooms();
        loadCheckin();
        jButton1.setText("Select Customer");
        if (checkin_update && btn_add) {
            jButton5.setEnabled(true);
        } else {
            jButton5.setEnabled(false);
        }
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

    public void loadRooms() {

        try {
            ResultSet rs = MySQL.search("SELECT `room`.`id`, `room_type`.`name`,`room_type`.`price`, "
                    + "`available`.`name` FROM `room` INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` "
                    + "INNER JOIN `available` ON `room`.`available_id` = `available`.`id` ORDER BY `room`.`id` ASC");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("room.id"));
                v.add(rs.getString("room_type.name"));
                v.add(rs.getString("room_type.price"));
                v.add(rs.getString("available.name"));

                dtm.addRow(v);

            }
            jTable1.setModel(dtm);
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRooms(String text) {

        try {
            ResultSet rs = MySQL.search("SELECT `room`.`id`, `room_type`.`name`,`room_type`.`price`, "
                    + "`available`.`name` FROM `room` INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` "
                    + "INNER JOIN `available` ON `room`.`available_id` = `available`.`id` WHERE `room_type`.`name` LIKE '" + text + "%' "
                            + "OR `room_type`.`price` LIKE '" + text + "%' OR `available`.`name` LIKE '" + text + "%' ORDER BY `room`.`id` ASC");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("room.id"));
                v.add(rs.getString("room_type.name"));
                v.add(rs.getString("room_type.price"));
                v.add(rs.getString("available.name"));

                dtm.addRow(v);

            }
            jTable1.setModel(dtm);
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFields() {

        jTable3.clearSelection();
        jTable1.clearSelection();
        jLabel13.setText("None");
        jLabel15.setText("None");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jLabel5.setText("None");
        jLabel7.setText("None");
        jLabel9.setText("None");
        jLabel11.setText("None");
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();

    }

    public void loadCheckin() {
        try {
            ResultSet rs = MySQL.search("SELECT `checkin`.`id`, `customer`.`id`, `customer`.`fname`, `customer`.`lname`,`customer`.`nic`, "
                    + "`checkin_item`.`checkin_date`, `checkin_item`.`checkout_date`, `room`.`id`, `room_type`.`name` FROM `checkin` "
                    + "INNER JOIN `customer` ON `checkin`.`customer_id`=`customer`.`id` INNER JOIN `checkin_item` "
                    + "ON `checkin`.`id` = `checkin_item`.`checkin_id` INNER JOIN `room` ON `checkin_item`.`room_id` = `room`.`id` "
                    + "INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` INNER JOIN `checkin_status` "
                    + "ON `checkin_item`.`checkin_status_id`= `checkin_status`.`id` WHERE `checkin_status`.`id` = '1' "
                    + "ORDER BY `checkin`.`id` DESC");
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
            jButton5.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCheckin(String text) {
        try {
            ResultSet rs = MySQL.search("SELECT `checkin`.`id`, `customer`.`id`, `customer`.`fname`, `customer`.`lname`,"
                    + "`customer`.`nic`, `checkin_item`.`checkin_date`, `checkin_item`.`checkout_date`, `room`.`id`, `room_type`.`name` "
                    + "FROM `checkin` INNER JOIN `customer` ON `checkin`.`customer_id`=`customer`.`id` "
                    + "INNER JOIN `checkin_item` ON `checkin`.`id` = `checkin_item`.`checkin_id` "
                    + "INNER JOIN `room` ON `checkin_item`.`room_id` = `room`.`id` "
                    + "INNER JOIN `room_type` ON `room`.`room_type_id` = `room_type`.`id` "
                    + "INNER JOIN `checkin_status` ON `checkin_item`.`checkin_status_id`= `checkin_status`.`id` "
                    + "WHERE `checkin_status`.`id` = '1' AND `customer`.`fname` LIKE '" + text + "%' OR `customer`.`lname` LIKE '" + text + "%' "
                            + "OR `checkin_item`.`checkout_date` LIKE '" + text + "%'  ORDER BY `checkin`.`id` DESC");
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
            jButton5.setEnabled(false);
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

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        jButton4.setText("Check-In");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check-In");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Home.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setText("Select Customer");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Customer ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("None");

        jLabel6.setText("First Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("None");

        jLabel8.setText("Last Name");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("None");

        jLabel10.setText("NIC");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("None");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Available Rooms");

        jLabel3.setText("Search");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No", "Room Type", "Price", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel12.setText("Room No");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("None");

        jLabel14.setText("Room Type");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("None");

        jLabel16.setText("Check-In Date");

        jLabel17.setText("Check-Out Date");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(131, 131, 131)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(jLabel16))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jLabel17))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addGap(21, 21, 21))
        );

        jLabel18.setText("Search Check-In");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jButton5.setText("Update Check-In");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setText("Check-In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "NIC", "Check-In ", "Check-Out ", "Room No", "Room Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        CustomerRegistration cr = new CustomerRegistration();
        cr.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:

        String text = jTextField1.getText();
        loadRooms(text);

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        int r = jTable1.getSelectedRow();

        if (evt.getClickCount() == 1) {
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Room.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                String RoomNo = jTable1.getValueAt(r, 0).toString();
                String RoomType = jTable1.getValueAt(r, 1).toString();
                String availability = jTable1.getValueAt(r, 3).toString();

                DefaultTableModel dtm3 = (DefaultTableModel) jTable3.getModel();

                boolean isFound = false;
                int x = -1;
                for (int i = 0; i < dtm3.getRowCount(); i++) {
                    String s = jTable3.getValueAt(i, 6).toString();
                    if (s.equals(RoomNo)) {
                        isFound = true;
                        x = i;
                        break;
                    }
                }
                if (isFound) {
                    JOptionPane.showMessageDialog(this, "This Room is Already Added. Please Select Another One.", "Warning", JOptionPane.WARNING_MESSAGE);

                }

                if (availability.equals("Unavailable")) {
                    JOptionPane.showMessageDialog(this, "This Room is Currently Unavailable. Please Select Available One.", "Warning", JOptionPane.WARNING_MESSAGE);

                    jTable1.clearSelection();
                    jLabel13.setText("None");
                    jLabel15.setText("None");

                } else {
                    jLabel13.setText(RoomNo);
                    jLabel15.setText(RoomType);

                }
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String cusID = jLabel5.getText().trim();
            String cusFName = jLabel7.getText().trim();
            String cusLName = jLabel9.getText().trim();
            String cusNic = jLabel11.getText().trim();
            String RoomNo = jLabel13.getText().trim();
            String RoomType = jLabel15.getText().trim();
            String checkInDate = null;
            String checkOutDate = null;
            if (jDateChooser1.getDate() != null) {
                checkInDate = sdf.format(jDateChooser1.getDate());
            }
            if (jDateChooser2.getDate() != null) {
                checkOutDate = sdf.format(jDateChooser2.getDate());
            }
            if (cusID.equals("None")) {
                JOptionPane.showMessageDialog(this, "Please Select Customer.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (RoomNo.equals("None")) {
                JOptionPane.showMessageDialog(this, "Please Select Room.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (checkInDate == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Check-In Date.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (checkOutDate == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Check-Out Date.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                boolean isFound = false;
                int x = -1;
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String addedRoomNo = jTable3.getValueAt(i, 6).toString();
                    if (RoomNo.equals(addedRoomNo)) {
                        isFound = true;
                        x = i;
                        break;
                    }
                }
                if (isFound) {
                    JOptionPane.showMessageDialog(this, "This Room Already Added.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    Vector v = new Vector();
                    v.add(cusID);
                    v.add(cusFName);
                    v.add(cusLName);
                    v.add(cusNic);
                    v.add(checkInDate);
                    v.add(checkOutDate);
                    v.add(RoomNo);
                    v.add(RoomType);
                    dtm.addRow(v);
                    jTable3.clearSelection();
                    jTable1.clearSelection();
                    jLabel13.setText("None");
                    jLabel15.setText("None");
                    jDateChooser1.setDate(null);
                    jDateChooser2.setDate(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (jTable3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Please Add Rooms First.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                long mTime = System.currentTimeMillis();
                String uniqueId = "Ch" + mTime;
                String CurrentDate = sdf.format(new Date());
                String customerID = jLabel5.getText().trim();
                MySQL.iud("INSERT INTO `checkin` (`customer_id`,`user_id`,`date_time`,`unique_id`) VALUES('" + customerID 
                        + "','" + SignIn.userId + "','" + CurrentDate + "','" + uniqueId + "') ");
                ResultSet rs = MySQL.search("SELECT * FROM `checkin` WHERE `unique_id`='" + uniqueId + "'");
                rs.next();
                String checkinID = rs.getString("id");
                for (int i = 0; i < jTable3.getRowCount(); i++) {
                    String CusID = jTable3.getValueAt(i, 0).toString();
                    String CheckinDate = jTable3.getValueAt(i, 4).toString();
                    String ChecOutDate = jTable3.getValueAt(i, 5).toString();
                    String RoomNo = jTable3.getValueAt(i, 6).toString();
                    MySQL.iud("INSERT INTO `checkin_item` (`checkin_id`,`room_id`,`checkin_date`,`checkout_date`,`checkin_status_id`) VALUES ('" 
                            + checkinID + "','" + RoomNo + "','" + CheckinDate + "','" + ChecOutDate + "','1') ");
                    MySQL.iud("UPDATE `room` SET `available_id`='2' WHERE `id`='" + RoomNo + "'");
                }
                resetFields();
                loadCheckin();
                loadRooms();
                DefaultTableModel dtm3 = (DefaultTableModel) jTable3.getModel();
                dtm3.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Checkin Success.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:

        String text = jTextField2.getText();

        if (text.isEmpty()) {
            loadCheckin();
        } else {
            loadCheckin(text);
        }

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        try {
            if (evt.getClickCount() == 1) {
                checkin_update = true;
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
                    jButton2.setEnabled(false);
                    jButton5.setEnabled(true);
                    jLabel5.setText(cus_iD);
                    jLabel7.setText(cus_fname);
                    jLabel9.setText(cus_lname);
                    jLabel11.setText(cus_nic);
                    jLabel13.setText(roomNo_APD);
                    jLabel15.setText(roomType);
                    jDateChooser1.setDate(Checkidate);
                    jDateChooser2.setDate(checkoutdate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            jButton1.setEnabled(true);
            jButton1.setText("Select Supplier");

            jLabel5.setText("None");
            jLabel7.setText("None");
            jLabel9.setText("None");
            jLabel11.setText("None");
            jButton1.setText("Select Customer");
        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            if (jTable2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Please Add Rooms First.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                String customerID = jLabel5.getText().trim();
                String CurrentDate = sdf.format(new Date());
                MySQL.iud("UPDATE `checkin` SET `customer_id`='" + customerID + "', `date_time`='" 
                        + CurrentDate + "' WHERE `id`='" + Checkin_ID_Apd + "'");
                for (int i = 0; i < jTable3.getRowCount(); i++) {
                    String CusID = jTable3.getValueAt(i, 0).toString();
                    String CheckinDate = jTable3.getValueAt(i, 4).toString();
                    String ChecOutDate = jTable3.getValueAt(i, 5).toString();
                    String RoomNo = jTable3.getValueAt(i, 6).toString();
                    MySQL.iud("UPDATE `checkin_item` SET `room_id`='" + RoomNo + "', `checkin_date`='" 
                            + CheckinDate + "', `checkout_date`='" + ChecOutDate + "' WHERE `checkin_id`='" + Checkin_ID_Apd + "'");
                    MySQL.iud("UPDATE `room` SET `available_id`='1' WHERE `id`='" + roomNo_APD + "'");
                    MySQL.iud("UPDATE `room` SET `available_id`='2' WHERE `id`='" + RoomNo + "'");
                }
                resetFields();
                loadCheckin();
                loadRooms();
                DefaultTableModel dtm3 = (DefaultTableModel) jTable3.getModel();
                dtm3.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Checkin Success.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        Home hm = new Home();
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        resetFields();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:

        String t = jTextField2.getText();
        String text = t + evt.getKeyChar();

        if (!Pattern.compile("^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:

        try {

            if (status == false) {

                long mTime = System.currentTimeMillis();

                Date d1 = jDateChooser1.getDate();
                long day1 = d1.getTime();
                String date = sdf.format(d1);
                String today = sdf.format(mTime);

//            System.out.println(date);
//            System.out.println(today);
                if (day1 < mTime) {
                    if (!date.equals(today)) {
                        JOptionPane.showMessageDialog(this, "Please Select Another Date.", "Warning", JOptionPane.WARNING_MESSAGE);
                        jDateChooser1.setDate(null);
                    }
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:

        try {

            if (status == false) {

                long mTime = System.currentTimeMillis();

                Date d1 = jDateChooser2.getDate();
                long day1 = d1.getTime();
                String date = sdf.format(d1);
                String today = sdf.format(mTime);
//            System.out.println(date);
//            System.out.println(today);

                if (day1 < mTime) {

                    JOptionPane.showMessageDialog(this, "Please Select Another Date.", "Warning", JOptionPane.WARNING_MESSAGE);
                    jDateChooser2.setDate(null);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:

        String t = jTextField1.getText();
        String text = t + evt.getKeyChar();

        if (!Pattern.compile("^[a-zA-Z]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Checkin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
