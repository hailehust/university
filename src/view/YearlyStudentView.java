package view;

import control.DAO;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author hai
 */
public class YearlyStudentView extends javax.swing.JFrame {

    /*======================= ATTRIBUTEs ==============================*/
    private University university;
    private String mssv;

    //cac model de hien thi thong tin
    DefaultTableModel modelXemHocPhanPhaiHoc;//hien thi table admin
    DefaultTableModel modelXemLopMoPhaiHoc;//hien thi table admin

    //nghia
    private DefaultTableModel modelKetQuaHocTap;


    /*======================= CONSTRUCTORs ==============================*/
    /**
     * Creates new form S
     *
     * @param university
     * @param university.findStudent(mssv)
     */
    public YearlyStudentView(University university, String mssv) {
        /* Set the GTK+ look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If GTK+ (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(YearlyStudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YearlyStudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YearlyStudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YearlyStudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        initComponents();
        this.setLocationRelativeTo(null);

        modelXemHocPhanPhaiHoc = (DefaultTableModel) jTableHocPhanCanHoc.getModel();
        modelXemLopMoPhaiHoc = (DefaultTableModel) jTableLopMoCanHoc.getModel();

        this.mssv = mssv;
        this.university = university;
        this.getSystemSemesterID();

        jLabelMSSV.setText(mssv);
        jLabelToday.setText("Hôm nay: " + String.valueOf(university.getToday()));
        jLabelSystemSemester.setText("Học kỳ: " + university.getSystemSemesterList().get(university.getSystemSemesterList().size() - 1).getSystemSemesterID());

        //nghia
        LabelName.setText(university.findStudent(mssv).getHoTen());
        LabelMSSV.setText(university.findStudent(mssv).getMssv());
        LabelTrangThai1.setText(university.findStudent(mssv).getTrangThai());
        LabelNganhHoc.setText(university.findSchool(university.findStudent(mssv).getMaNganhHoc()).getTenNganhHoc());
        LabelChuongTrinhHoc.setText(university.findProgram(university.findStudent(mssv).getMaChuongTrinhHoc()).getTenChuongTrinhHoc());
        LabelLopSV.setText(university.findStudent(mssv).getMaLopSinhVien());

        modelKetQuaHocTap = (DefaultTableModel) jTableKetQuaHocTap.getModel();

        this.displayKetQuaHocTap();

    }

    /*=================== OTHER METHODs ===============================*/
    public void getSystemSemesterID() {//get cac ma hoc ky he thong cua sinh vien dang lam viec
        //cho vao list item cua jComboBoxMaHocKyHeThong
        jComboBoxMaHocKyHeThong1.removeAllItems();
//        jComboBoxMaHocKyHeThong1.addItem("");
        jComboBoxMaHocKyHeThong2.removeAllItems();
//        jComboBoxMaHocKyHeThong2.addItem("");

        Collections.reverse(university.findStudent(mssv).getHocKySinhVien());//dao thu tu trong 

        for (StudentSemester a : university.findStudent(university.findStudent(mssv).getMssv()).getHocKySinhVien()) {
            jComboBoxMaHocKyHeThong1.addItem(a.getSystemSemesterID());
            jComboBoxMaHocKyHeThong2.addItem(a.getSystemSemesterID());

        }
    }

    public void displayXemHocPhanPhaiHoc() {//hien thi cac hoc phan ma 1 sinh vien dang ky // trong 1 ky he thong
        modelXemHocPhanPhaiHoc.setRowCount(0);//set model ve 0

        Course h;
        //duyet listMaHocPhanDangKy de hien thi tung hoc phan

        for (String maHocPhan : university.findStudentSemester(university.findStudent(mssv).getMssv(), jComboBoxMaHocKyHeThong1.getSelectedItem().toString()).getMaHocPhanDangKy()) {
            h = university.findCourse(maHocPhan);//tim ra hoc phan tuong ung ma s
            modelXemHocPhanPhaiHoc.addRow(new Object[]{
                h.getMaHocPhan(), h.getTenHocPhan()
            });
        }
    }

    public void displayXemLopMoPhaiHoc() {//hien thi cac hoc phan ma 1 sinh vien dang ky // trong 1 ky he thong

        modelXemLopMoPhaiHoc.setRowCount(0);//set model ve 0

        CourseClass h;
        //duyet listMaHocPhanDangKy de hien thi tung hoc phan

        for (String maLopMo : university.findStudentSemester(university.findStudent(mssv).getMssv(), jComboBoxMaHocKyHeThong2.getSelectedItem().toString()).getMaLopMoDangKy()) {
            h = university.findCourseClass(maLopMo);
            modelXemLopMoPhaiHoc.addRow(new Object[]{
                h.getMaLopMo(), h.getMaHocPhan(), university.findCourse(h.getMaHocPhan()).getTenHocPhan()
            });
        }
    }

    //nghia
    public void displayKetQuaHocTap() {
        for (CourseResult k : university.findStudent(mssv).getBangDiem()) {
            modelKetQuaHocTap.addRow(new Object[]{
                k.getMaHocPhan(), university.findCourse(k.getMaHocPhan()).getTenHocPhan(), k.getDiemGiuaKy(), k.getDiemCuoiKy(),
                k.getDiemHocPhan(), k.getKetQua()
            });
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelHoSoSinhvien = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        LabelName = new javax.swing.JLabel();
        LabelMSSV = new javax.swing.JLabel();
        LabelTrangThai1 = new javax.swing.JLabel();
        LabelLopSV = new javax.swing.JLabel();
        LabelChuongTrinhHoc = new javax.swing.JLabel();
        LabelNganhHoc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKetQuaHocTap = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanelTracuu = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHocPhanCanHoc = new javax.swing.JTable();
        jComboBoxMaHocKyHeThong1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLopMoCanHoc = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxMaHocKyHeThong2 = new javax.swing.JComboBox<>();
        jButtonDangXuat = new javax.swing.JButton();
        jLabelMSSV = new javax.swing.JLabel();
        jLabelSystemSemester = new javax.swing.JLabel();
        jLabelToday = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yearly Student Session");
        setFont(new java.awt.Font("Liberation Sans", 0, 10)); // NOI18N

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanelHoSoSinhvien.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel8.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N

        jLabel2.setText("Full name");

        jLabel3.setText("Student ID");

        jLabel5.setText("School");

        jLabel6.setText("Student class");

        jLabel7.setText("Program");

        jButton2.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-password-reset-25.png")); // NOI18N
        jButton2.setText("Change password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LabelName.setText("jLabel11");

        LabelMSSV.setText("jLabel11");

        LabelTrangThai1.setText("jLabel11");

        LabelLopSV.setText("jLabel11");

        LabelChuongTrinhHoc.setText("jLabel11");

        LabelNganhHoc.setText("jLabel11");

        jLabel4.setText("Status");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelNganhHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelChuongTrinhHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelLopSV)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jButton2)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(LabelName)
                    .addComponent(LabelNganhHoc))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(LabelMSSV)
                    .addComponent(LabelChuongTrinhHoc))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LabelTrangThai1)
                    .addComponent(LabelLopSV)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(41, 41, 41))
        );

        jTabbedPane1.addTab("Information", jPanel8);

        jTableKetQuaHocTap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course name", "Mid-term score", "End-term score", "Course score", "Result"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableKetQuaHocTap);
        if (jTableKetQuaHocTap.getColumnModel().getColumnCount() > 0) {
            jTableKetQuaHocTap.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableKetQuaHocTap.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableKetQuaHocTap.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        jLabel8.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-rating-25.png")); // NOI18N
        jLabel8.setText("CPA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8))
        );

        jTabbedPane1.addTab("Study result", jPanel1);

        javax.swing.GroupLayout jPanelHoSoSinhvienLayout = new javax.swing.GroupLayout(jPanelHoSoSinhvien);
        jPanelHoSoSinhvien.setLayout(jPanelHoSoSinhvienLayout);
        jPanelHoSoSinhvienLayout.setHorizontalGroup(
            jPanelHoSoSinhvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanelHoSoSinhvienLayout.setVerticalGroup(
            jPanelHoSoSinhvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane2.addTab("PROFILE", jPanelHoSoSinhvien);

        jPanelTracuu.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jTabbedPane3.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jTableHocPhanCanHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableHocPhanCanHoc);

        jComboBoxMaHocKyHeThong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMaHocKyHeThong1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Semester");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jComboBoxMaHocKyHeThong1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxMaHocKyHeThong1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane3.addTab("Yearly courses", jPanel10);

        jTableLopMoCanHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class ID", "Course ID", "Course name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableLopMoCanHoc);
        if (jTableLopMoCanHoc.getColumnModel().getColumnCount() > 0) {
            jTableLopMoCanHoc.getColumnModel().getColumn(0).setPreferredWidth(1);
            jTableLopMoCanHoc.getColumnModel().getColumn(1).setPreferredWidth(1);
            jTableLopMoCanHoc.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jLabel10.setText("Semester");

        jComboBoxMaHocKyHeThong2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMaHocKyHeThong2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addComponent(jComboBoxMaHocKyHeThong2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxMaHocKyHeThong2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane3.addTab("Yearly classes", jPanel11);

        javax.swing.GroupLayout jPanelTracuuLayout = new javax.swing.GroupLayout(jPanelTracuu);
        jPanelTracuu.setLayout(jPanelTracuuLayout);
        jPanelTracuuLayout.setHorizontalGroup(
            jPanelTracuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanelTracuuLayout.setVerticalGroup(
            jPanelTracuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane2.addTab("SEARCHING", jPanelTracuu);

        jButtonDangXuat.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-shutdown-25.png")); // NOI18N
        jButtonDangXuat.setText("Logout");
        jButtonDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangXuatActionPerformed(evt);
            }
        });

        jLabelMSSV.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabelMSSV.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-graduate-25.png")); // NOI18N
        jLabelMSSV.setText("studentID");

        jLabelSystemSemester.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-calendar-25.png")); // NOI18N
        jLabelSystemSemester.setText("systemSemester");

        jLabelToday.setIcon(new javax.swing.ImageIcon("/home/haile/Downloads/icons8-clock-25.png")); // NOI18N
        jLabelToday.setText("today");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabelSystemSemester)
                .addGap(38, 38, 38)
                .addComponent(jLabelToday)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDangXuat)
                .addGap(61, 61, 61))
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangXuat)
                    .addComponent(jLabelSystemSemester)
                    .addComponent(jLabelToday)
                    .addComponent(jLabelMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangXuatActionPerformed
        // TODO add your handling code here:
        DAO dao = new DAO(university);

        university.initLoginSession(dao);
        this.dispose();

    }//GEN-LAST:event_jButtonDangXuatActionPerformed

    private void jComboBoxMaHocKyHeThong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMaHocKyHeThong1ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMaHocKyHeThong1.getSelectedItem().toString().equals("")) {
            modelXemHocPhanPhaiHoc.setRowCount(0);
        } else {
            displayXemHocPhanPhaiHoc();
        }
    }//GEN-LAST:event_jComboBoxMaHocKyHeThong1ActionPerformed

    private void jComboBoxMaHocKyHeThong2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMaHocKyHeThong2ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMaHocKyHeThong2.getSelectedItem().toString().equals("")) {
            modelXemLopMoPhaiHoc.setRowCount(0);
        } else {
            displayXemLopMoPhaiHoc();
        }
    }//GEN-LAST:event_jComboBoxMaHocKyHeThong2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        university.initPassSession(mssv);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelChuongTrinhHoc;
    private javax.swing.JLabel LabelLopSV;
    private javax.swing.JLabel LabelMSSV;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelNganhHoc;
    private javax.swing.JLabel LabelTrangThai1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonDangXuat;
    private javax.swing.JComboBox<String> jComboBoxMaHocKyHeThong1;
    private javax.swing.JComboBox<String> jComboBoxMaHocKyHeThong2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMSSV;
    private javax.swing.JLabel jLabelSystemSemester;
    private javax.swing.JLabel jLabelToday;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelHoSoSinhvien;
    private javax.swing.JPanel jPanelTracuu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTableHocPhanCanHoc;
    private javax.swing.JTable jTableKetQuaHocTap;
    private javax.swing.JTable jTableLopMoCanHoc;
    // End of variables declaration//GEN-END:variables
}
