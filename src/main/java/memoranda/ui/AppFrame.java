package main.java.memoranda.ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument;

import main.java.memoranda.models.CurrentProject;
import main.java.memoranda.models.History;
import main.java.interfaces.Note;
import main.java.interfaces.NoteList;
import main.java.interfaces.Project;
import main.java.interfaces.ProjectListener;
import main.java.interfaces.ResourcesList;
import main.java.interfaces.TaskList;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.ui.htmleditor.HTMLEditor;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.Context;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.ProjectExporter;
import main.java.memoranda.util.Util;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/* $Id: AppFrame.java,v 1.33 2005/07/05 08:17:24 alexeya Exp $ */

public class AppFrame extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu jMenuFile = new JMenu();
    private JMenuItem jMenuFileExit = new JMenuItem();

    private JButton jButton3 = new JButton();
    private ImageIcon image1, image2, image3;
    private  JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    private JSplitPane splitPane = new JSplitPane();

    JMenu jMenuEdit = new JMenu();
    JMenu jMenuFormat = new JMenu();
    JMenu jMenuInsert = new JMenu();

    private WorkPanel workPanel = new WorkPanel();
    HTMLEditor editor = workPanel.dailyItemsPanel.editorPanel.editor;

    private static Vector<ActionListener> exitListeners = new Vector<>();

    private JMenuItem jMenuFileNewPrj,jMenuFileNewNote,jMenuFileExportPrj,jMenuFileImportPrj,jMenuFileImportNote,jMenuFileExportNote,jMenuFileMin;

    JMenuItem jMenuItem1;
    JMenuItem jMenuEditUndo;
    JMenuItem jMenuEditRedo;
    JMenuItem jMenuEditCut;
    JMenuItem jMenuEditCopy;
    JMenuItem jMenuEditPaste;
    JMenuItem jMenuEditPasteSpec;
    JMenuItem jMenuEditSelectAll;
    JMenuItem jMenuEditFind;

    JMenu jMenuGo;
    JMenuItem jMenuInsertImage;
    JMenuItem jMenuInsertTable;
    JMenuItem jMenuInsertLink;
    JMenu jMenuInsertList;
    JMenuItem jMenuInsertListUL;
    JMenuItem jMenuInsertListOL;
    JMenuItem jMenuInsertBR;
    JMenuItem jMenuInsertHR;
    JMenuItem jMenuInsertChar;
    JMenuItem jMenuInsertDate;
    JMenuItem jMenuInsertTime;
    JMenuItem jMenuInsertFile;

    JMenu jMenuFormatPStyle;
    JMenuItem jMenuFormatP;
    JMenuItem jMenuFormatH1;
    JMenuItem jMenuFormatH2;
    JMenuItem jMenuFormatH3;
    JMenuItem jMenuFormatH4;
    JMenuItem jMenuFormatH5;
    JMenuItem jMenuFormatH6;
    JMenuItem jMenuFormatPRE;
    JMenuItem jMenuFormatBLCQ;

    JMenu jjMenuFormatChStyle;
    JMenuItem jMenuFormatChNorm;
    JMenuItem jMenuFormatChEM;
    JMenuItem jMenuFormatChSTRONG;
    JMenuItem jMenuFormatChCODE;
    JMenuItem jMenuFormatChCite;
    JMenuItem jMenuFormatChSUP;
    JMenuItem jMenuFormatChSUB;
    JMenuItem jMenuFormatChCustom;
    JMenuItem jMenuFormatChB;
    JMenuItem jMenuFormatChI;
    JMenuItem jMenuFormatChU;

    JMenu jMenuFormatAlign;
    JMenuItem jMenuFormatAlignL;
    JMenuItem jMenuFormatAlignC;
    JMenuItem jMenuFormatAlignR;

    JMenu jMenuFormatTable;
    JMenuItem jMenuFormatTableInsR;
    JMenuItem jMenuFormatTableInsC;
    JMenuItem jMenuFormatProperties;
    JMenuItem jMenuGoHBack;
    JMenuItem jMenuGoFwd;

    JMenuItem jMenuGoDayBack;
    JMenuItem jMenuGoDayFwd;
    JMenuItem jMenuGoToday;

    JMenuItem jMenuEditPref;

    JMenu jMenuInsertSpecial;

    JMenu jMenuHelp;

    JMenuItem jMenuHelpGuide = new JMenuItem();
    JMenuItem jMenuHelpWeb = new JMenuItem();
    JMenuItem jMenuHelpBug = new JMenuItem();
    JMenuItem jMenuHelpAbout = new JMenuItem();

    // Construct the frame
    public AppFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            new ExceptionDialog(e);
        }
    }

    // Component initialization //
    private void jbInit(){



        jMenuFileNewPrj = new JMenuItem();
        jMenuFileNewNote = new JMenuItem(workPanel.dailyItemsPanel.editorPanel.newAction);
        jMenuFileExportPrj = new JMenuItem(exportNotesAction);
        jMenuFileImportPrj = new JMenuItem(importNotesAction);
        jMenuFileImportNote = new JMenuItem(importOneNoteAction);
        jMenuFileExportNote = new JMenuItem(workPanel.dailyItemsPanel.editorPanel.exportAction);
        jMenuFileMin = new JMenuItem(minimizeAction);

        jMenuItem1 = new JMenuItem();
        jMenuEditUndo = new JMenuItem(editor.undoAction);
        jMenuEditRedo = new JMenuItem(editor.redoAction);
        jMenuEditCut = new JMenuItem(editor.cutAction);
        jMenuEditCopy = new JMenuItem(editor.copyAction);
        jMenuEditPaste = new JMenuItem(editor.pasteAction);
        jMenuEditPasteSpec = new JMenuItem(editor.stylePasteAction);
        jMenuEditSelectAll = new JMenuItem(editor.selectAllAction);
        jMenuEditFind = new JMenuItem(editor.findAction);

        jMenuGo = new JMenu();
        jMenuInsertImage = new JMenuItem(editor.imageAction);
        jMenuInsertTable = new JMenuItem(editor.tableAction);
        jMenuInsertLink = new JMenuItem(editor.linkAction);
        jMenuInsertList = new JMenu();
        jMenuInsertListUL = new JMenuItem(editor.ulAction);
        jMenuInsertListOL = new JMenuItem(editor.olAction);
        jMenuInsertBR = new JMenuItem(editor.breakAction);
        jMenuInsertHR = new JMenuItem(editor.insertHRAction);
        jMenuInsertChar = new JMenuItem(editor.insCharAction);
        jMenuInsertDate = new JMenuItem(workPanel.dailyItemsPanel.editorPanel.insertDateAction);
        jMenuInsertTime = new JMenuItem(workPanel.dailyItemsPanel.editorPanel.insertTimeAction);
        jMenuInsertFile = new JMenuItem(workPanel.dailyItemsPanel.editorPanel.importAction);

        jMenuFormatPStyle = new JMenu();
        jMenuFormatP = new JMenuItem(editor.new BlockAction(editor.T_P, ""));
        jMenuFormatH1 = new JMenuItem(editor.new BlockAction(editor.T_H1, ""));
        jMenuFormatH2 = new JMenuItem(editor.new BlockAction(editor.T_H2, ""));
        jMenuFormatH3 = new JMenuItem(editor.new BlockAction(editor.T_H3, ""));
        jMenuFormatH4 = new JMenuItem(editor.new BlockAction(editor.T_H4, ""));
        jMenuFormatH5 = new JMenuItem(editor.new BlockAction(editor.T_H5, ""));
        jMenuFormatH6 = new JMenuItem(editor.new BlockAction(editor.T_H6, ""));
        jMenuFormatPRE = new JMenuItem(editor.new BlockAction(editor.T_PRE, ""));
        jMenuFormatBLCQ = new JMenuItem(editor.new BlockAction(editor.T_BLOCKQ, ""));
        jjMenuFormatChStyle = new JMenu();
        jMenuFormatChNorm = new JMenuItem(editor.new InlineAction(editor.I_NORMAL, ""));
        jMenuFormatChEM = new JMenuItem(editor.new InlineAction(editor.I_EM, ""));
        jMenuFormatChSTRONG = new JMenuItem(editor.new InlineAction(editor.I_STRONG, ""));
        jMenuFormatChCODE = new JMenuItem(editor.new InlineAction(editor.I_CODE, ""));
        jMenuFormatChCite = new JMenuItem(editor.new InlineAction(editor.I_CITE, ""));
        jMenuFormatChSUP = new JMenuItem(editor.new InlineAction(editor.I_SUPERSCRIPT, ""));
        jMenuFormatChSUB = new JMenuItem(editor.new InlineAction(editor.I_SUBSCRIPT, ""));
        jMenuFormatChCustom = new JMenuItem(editor.new InlineAction(editor.I_CUSTOM, ""));
        jMenuFormatChB = new JMenuItem(editor.boldAction);
        jMenuFormatChI = new JMenuItem(editor.italicAction);
        jMenuFormatChU = new JMenuItem(editor.underAction);
        jMenuFormatAlign = new JMenu();
        jMenuFormatAlignL = new JMenuItem(editor.lAlignAction);
        jMenuFormatAlignC = new JMenuItem(editor.cAlignAction);
        jMenuFormatAlignR = new JMenuItem(editor.rAlignAction);
        jMenuFormatTable = new JMenu();
        jMenuFormatTableInsR = new JMenuItem(editor.insertTableRowAction);
        jMenuFormatTableInsC = new JMenuItem(editor.insertTableCellAction);
        jMenuFormatProperties = new JMenuItem(editor.propsAction);
        jMenuGoHBack = new JMenuItem(History.historyBackAction);
        jMenuGoFwd = new JMenuItem(History.historyForwardAction);

        jMenuGoDayBack = new JMenuItem(workPanel.dailyItemsPanel.calendar.dayBackAction);
        jMenuGoDayFwd = new JMenuItem(workPanel.dailyItemsPanel.calendar.dayForwardAction);
        jMenuGoToday = new JMenuItem(workPanel.dailyItemsPanel.calendar.todayAction);
        jMenuEditPref = new JMenuItem(preferencesAction);

        jMenuInsertSpecial = new JMenu();
        jMenuHelp = new JMenu();

        JMenuItem jMenuHelpGuide = new JMenuItem();
        JMenuItem jMenuHelpWeb = new JMenuItem();
        JMenuItem jMenuHelpBug = new JMenuItem();
        JMenuItem jMenuHelpAbout = new JMenuItem();
        this.setIconImage(new ImageIcon(AppFrame.class.getResource("/ui/icons/p2pIconFull.png")).getImage());
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        // this.setSize(new Dimension(800, 500));
        this.setTitle("Point2Point - " + CurrentProject.get().getTitle());
        // Added a space to App.VERSION_INFO to make it look some nicer
        statusBar.setText(" Version:" + App.VERSION_INFO + " (Build " + App.BUILD_INFO + " )");

        jMenuFile.setText(Local.getString("File"));
        jMenuFileExit.setText(Local.getString("Exit"));
        jMenuFileExit.addActionListener(e -> doExit());


        jMenuHelp.setText(Local.getString("Help"));

        jMenuHelpGuide.setText(Local.getString("Online user's guide"));
        jMenuHelpGuide.setIcon(new ImageIcon(AppFrame.class.getResource("/ui/icons/help.png")));
        jMenuHelpGuide.addActionListener(e -> jMenuHelpGuide_actionPerformed(e));

        jMenuHelpWeb.setText(Local.getString("Memoranda web site"));
        jMenuHelpWeb.setIcon(new ImageIcon(AppFrame.class.getResource("/ui/icons/web.png")));
        jMenuHelpWeb.addActionListener(e -> jMenuHelpWeb_actionPerformed(e));

        jMenuHelpBug.setText(Local.getString("Report a bug"));
        jMenuHelpBug.addActionListener(e -> jMenuHelpBug_actionPerformed(e));

        jMenuHelpAbout.setText(Local.getString("About Memoranda"));
        jMenuHelpAbout.addActionListener(e -> jMenuHelpAbout_actionPerformed(e));


        jButton3.setToolTipText(Local.getString("Help"));
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(3);
        splitPane.setDividerLocation(28);


        jMenuFileExportNote.setText(Local.getString("Export current note") + "...");
        jMenuFileImportNote.setText(Local.getString("Import one note") + "...");
        jMenuFileMin.setText(Local.getString("Close the window"));
        jMenuFileMin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.ALT_MASK));

        jMenuEdit.setText(Local.getString("Edit"));

        jMenuEditUndo.setText(Local.getString("Undo"));
        jMenuEditUndo.setToolTipText(Local.getString("Undo"));
        jMenuEditRedo.setText(Local.getString("Redo"));
        jMenuEditRedo.setToolTipText(Local.getString("Redo"));
        jMenuEditCut.setText(Local.getString("Cut"));
        jMenuEditCut.setToolTipText(Local.getString("Cut"));
        jMenuEditCopy.setText((String) Local.getString("Copy"));
        jMenuEditCopy.setToolTipText(Local.getString("Copy"));
        jMenuEditPaste.setText(Local.getString("Paste"));
        jMenuEditPaste.setToolTipText(Local.getString("Paste"));
        jMenuEditPasteSpec.setText(Local.getString("Paste special"));
        jMenuEditPasteSpec.setToolTipText(Local.getString("Paste special"));
        jMenuEditSelectAll.setText(Local.getString("Select all"));

        jMenuEditFind.setText(Local.getString("Find & replace") + "...");

        jMenuEditPref.setText(Local.getString("Preferences") + "...");

        jMenuInsert.setText(Local.getString("Insert"));

        jMenuInsertImage.setText(Local.getString("Image") + "...");
        jMenuInsertImage.setToolTipText(Local.getString("Insert Image"));
        jMenuInsertTable.setText(Local.getString("Table") + "...");
        jMenuInsertTable.setToolTipText(Local.getString("Insert Table"));
        jMenuInsertLink.setText(Local.getString("Hyperlink") + "...");
        jMenuInsertLink.setToolTipText(Local.getString("Insert Hyperlink"));
        jMenuInsertList.setText(Local.getString("List"));

        jMenuInsertListUL.setText(Local.getString("Unordered"));
        jMenuInsertListUL.setToolTipText(Local.getString("Insert Unordered"));
        jMenuInsertListOL.setText(Local.getString("Ordered"));

        jMenuInsertSpecial.setText(Local.getString("Special"));
        jMenuInsertBR.setText(Local.getString("Line break"));
        jMenuInsertHR.setText(Local.getString("Horizontal rule"));

        jMenuInsertListOL.setToolTipText(Local.getString("Insert Ordered"));

        jMenuInsertChar.setText(Local.getString("Special character") + "...");
        jMenuInsertChar.setToolTipText(Local.getString("Insert Special character"));
        jMenuInsertDate.setText(Local.getString("Current date"));
        jMenuInsertTime.setText(Local.getString("Current time"));
        jMenuInsertFile.setText(Local.getString("File") + "...");

        jMenuFormat.setText(Local.getString("Format"));
        jMenuFormatPStyle.setText(Local.getString("Paragraph style"));
        jMenuFormatP.setText(Local.getString("Paragraph"));
        jMenuFormatH1.setText(Local.getString("Header") + " 1");
        jMenuFormatH2.setText(Local.getString("Header") + " 2");
        jMenuFormatH3.setText(Local.getString("Header") + " 3");
        jMenuFormatH4.setText(Local.getString("Header") + " 4");
        jMenuFormatH5.setText(Local.getString("Header") + " 5");
        jMenuFormatH6.setText(Local.getString("Header") + " 6");
        jMenuFormatPRE.setText(Local.getString("Preformatted text"));
        jMenuFormatBLCQ.setText(Local.getString("Blockquote"));
        jjMenuFormatChStyle.setText(Local.getString("Character style"));
        jMenuFormatChNorm.setText(Local.getString("Normal"));
        jMenuFormatChEM.setText(Local.getString("Emphasis"));
        jMenuFormatChSTRONG.setText(Local.getString("Strong"));
        jMenuFormatChCODE.setText(Local.getString("Code"));
        jMenuFormatChCite.setText(Local.getString("Cite"));
        jMenuFormatChSUP.setText(Local.getString("Superscript"));
        jMenuFormatChSUB.setText(Local.getString("Subscript"));
        jMenuFormatChCustom.setText(Local.getString("Custom style") + "...");
        jMenuFormatChB.setText(Local.getString("Bold"));
        jMenuFormatChB.setToolTipText(Local.getString("Bold"));
        jMenuFormatChI.setText(Local.getString("Italic"));
        jMenuFormatChI.setToolTipText(Local.getString("Italic"));
        jMenuFormatChU.setText(Local.getString("Underline"));
        jMenuFormatChU.setToolTipText(Local.getString("Underline"));
        jMenuFormatAlign.setText(Local.getString("Alignment"));
        jMenuFormatAlignL.setText(Local.getString("Left"));
        jMenuFormatAlignL.setToolTipText(Local.getString("Left"));
        jMenuFormatAlignC.setText(Local.getString("Center"));
        jMenuFormatAlignC.setToolTipText(Local.getString("Center"));
        jMenuFormatAlignR.setText(Local.getString("Right"));
        jMenuFormatAlignR.setToolTipText(Local.getString("Right"));
        jMenuFormatTable.setText(Local.getString("Table"));
        jMenuFormatTableInsR.setText(Local.getString("Insert row"));
        jMenuFormatTableInsC.setText(Local.getString("Insert cell"));
        jMenuFormatProperties.setText(Local.getString("Object properties") + "...");
        jMenuFormatProperties.setToolTipText(Local.getString("Object properties"));

        jMenuGo.setText(Local.getString("Go"));
        jMenuGoHBack.setText(Local.getString("History back"));
        jMenuGoHBack.setToolTipText(Local.getString("History back"));
        jMenuGoFwd.setText(Local.getString("History forward"));
        jMenuGoFwd.setToolTipText(Local.getString("History forward"));
        jMenuGoDayBack.setText(Local.getString("One day back"));
        jMenuGoDayFwd.setText(Local.getString("One day forward"));
        jMenuGoToday.setText(Local.getString("To today"));

        jMenuInsertSpecial.setText(Local.getString("Special"));
        jMenuInsertBR.setText(Local.getString("Line break"));
        jMenuInsertBR.setToolTipText(Local.getString("Insert break"));
        jMenuInsertHR.setText(Local.getString("Horizontal rule"));
        jMenuInsertHR.setToolTipText(Local.getString("Insert Horizontal rule"));

        jMenuFile.add(jMenuFileNewPrj);
        jMenuFile.add(jMenuFileNewNote);
        jMenuFile.addSeparator();
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileExportPrj);
        jMenuFile.add(jMenuFileExportNote);
        jMenuFile.add(jMenuFileImportNote);
        jMenuFile.add(jMenuFileImportPrj);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuEditPref);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileMin);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileExit);

        jMenuHelp.add(jMenuHelpGuide);
        jMenuHelp.add(jMenuHelpWeb);
        jMenuHelp.add(jMenuHelpBug);
        jMenuHelp.addSeparator();
        jMenuHelp.add(jMenuHelpAbout);

        menuBar.add(jMenuFile);
        menuBar.add(jMenuEdit);
        menuBar.add(jMenuInsert);
        menuBar.add(jMenuFormat);
        menuBar.add(jMenuGo);
        menuBar.add(jMenuHelp);
        this.setJMenuBar(menuBar);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        contentPane.add(splitPane, BorderLayout.CENTER);

        splitPane.add(workPanel, JSplitPane.BOTTOM);
        jMenuEdit.add(jMenuEditUndo);
        jMenuEdit.add(jMenuEditRedo);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuEditCut);
        jMenuEdit.add(jMenuEditCopy);
        jMenuEdit.add(jMenuEditPaste);
        jMenuEdit.add(jMenuEditPasteSpec);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuEditSelectAll);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuEditFind);

        jMenuInsert.add(jMenuInsertImage);
        jMenuInsert.add(jMenuInsertTable);
        jMenuInsert.add(jMenuInsertLink);
        jMenuInsert.add(jMenuInsertList);
        // jMenuInsert.add(jMenuInsertSpecial);
        jMenuInsertList.add(jMenuInsertListUL);
        jMenuInsertList.add(jMenuInsertListOL);
        jMenuInsert.addSeparator();
        jMenuInsert.add(jMenuInsertBR);
        jMenuInsert.add(jMenuInsertHR);
        jMenuInsert.add(jMenuInsertChar);
        jMenuInsert.addSeparator();
        jMenuInsert.add(jMenuInsertDate);
        jMenuInsert.add(jMenuInsertTime);
        jMenuInsert.addSeparator();
        jMenuInsert.add(jMenuInsertFile);

        jMenuFormat.add(jMenuFormatPStyle);
        jMenuFormat.add(jjMenuFormatChStyle);
        jMenuFormat.add(jMenuFormatAlign);
        jMenuFormat.addSeparator();
        jMenuFormat.add(jMenuFormatTable);
        jMenuFormat.addSeparator();
        jMenuFormat.add(jMenuFormatProperties);
        jMenuFormatPStyle.add(jMenuFormatP);
        jMenuFormatPStyle.addSeparator();
        jMenuFormatPStyle.add(jMenuFormatH1);
        jMenuFormatPStyle.add(jMenuFormatH2);
        jMenuFormatPStyle.add(jMenuFormatH3);
        jMenuFormatPStyle.add(jMenuFormatH4);
        jMenuFormatPStyle.add(jMenuFormatH5);
        jMenuFormatPStyle.add(jMenuFormatH6);
        jMenuFormatPStyle.addSeparator();
        jMenuFormatPStyle.add(jMenuFormatPRE);
        jMenuFormatPStyle.add(jMenuFormatBLCQ);
        jjMenuFormatChStyle.add(jMenuFormatChNorm);
        jjMenuFormatChStyle.addSeparator();
        jjMenuFormatChStyle.add(jMenuFormatChB);
        jjMenuFormatChStyle.add(jMenuFormatChI);
        jjMenuFormatChStyle.add(jMenuFormatChU);
        jjMenuFormatChStyle.addSeparator();
        jjMenuFormatChStyle.add(jMenuFormatChEM);
        jjMenuFormatChStyle.add(jMenuFormatChSTRONG);
        jjMenuFormatChStyle.add(jMenuFormatChCODE);
        jjMenuFormatChStyle.add(jMenuFormatChCite);
        jjMenuFormatChStyle.addSeparator();
        jjMenuFormatChStyle.add(jMenuFormatChSUP);
        jjMenuFormatChStyle.add(jMenuFormatChSUB);
        jjMenuFormatChStyle.addSeparator();
        jjMenuFormatChStyle.add(jMenuFormatChCustom);
        jMenuFormatAlign.add(jMenuFormatAlignL);
        jMenuFormatAlign.add(jMenuFormatAlignC);
        jMenuFormatAlign.add(jMenuFormatAlignR);
        jMenuFormatTable.add(jMenuFormatTableInsR);
        jMenuFormatTable.add(jMenuFormatTableInsC);
        jMenuGo.add(jMenuGoHBack);
        jMenuGo.add(jMenuGoFwd);
        jMenuGo.addSeparator();
        jMenuGo.add(jMenuGoDayBack);
        jMenuGo.add(jMenuGoDayFwd);
        jMenuGo.add(jMenuGoToday);

        splitPane.setBorder(null);
        workPanel.setBorder(null);

        setEnabledEditorMenus(false);



        this.workPanel.dailyItemsPanel.taskB.addActionListener(e -> setEnabledEditorMenus(false));
        this.workPanel.dailyItemsPanel.alarmB.addActionListener(e -> setEnabledEditorMenus(false));
        this.workPanel.notesB.addActionListener(e -> setEnabledEditorMenus(true));

        Object fwo = Context.get("FRAME_WIDTH");
        Object fho = Context.get("FRAME_HEIGHT");
        if ((fwo != null) && (fho != null)) {
            int w = Integer.valueOf((String)fwo);
            int h = Integer.valueOf((String)fho);
            this.setSize(w, h);
        } else
            this.setExtendedState(Frame.MAXIMIZED_BOTH);

        Object xo = Context.get("FRAME_XPOS");
        Object yo = Context.get("FRAME_YPOS");
        if ((xo != null) && (yo != null)) {
            int x = Integer.valueOf((String) xo);
            int y = Integer.valueOf((String)yo);
            this.setLocation(x, y);
        }

        String pan = (String) Context.get("CURRENT_PANEL");
        if (pan != null) {
            workPanel.selectPanel(pan);
            setEnabledEditorMenus(pan.equalsIgnoreCase("NOTES"));
        }

        CurrentProject.addProjectListener(new ProjectListener() {

            public void projectChange(Project prj, NoteList nl, TaskList tl, ResourcesList rl) {
            }

            public void projectWasChanged() {
                setTitle("Memoranda - " + CurrentProject.get().getTitle());
            }
        });

    }

    public Action minimizeAction = new AbstractAction("Close the window") {
        public void actionPerformed(ActionEvent e) {
            doMinimize();
        }
    };

    public Action preferencesAction = new AbstractAction("Preferences") {
        public void actionPerformed(ActionEvent e) {
            showPreferences();
        }
    };

    public Action exportNotesAction = new AbstractAction(Local.getString("Export notes") + "...") {

        public void actionPerformed(ActionEvent e) {
            ppExport_actionPerformed(e);
        }
    };

    public Action importNotesAction = new AbstractAction(Local.getString("Import multiple notes")) {

        public void actionPerformed(ActionEvent e) {
            ppImport_actionPerformed(e);
        }
    };
    public Action importOneNoteAction = new AbstractAction(Local.getString("Import one note")) {

        public void actionPerformed(ActionEvent e) {
            p1Import_actionPerformed(e);
        }
    };

    private void jMenuHelpBug_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.BUGS_TRACKER_URL);
    }

    private void jMenuHelpWeb_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.WEBSITE_URL);
    }

    private void jMenuHelpGuide_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.GUIDE_URL);
    }

    // File | Exit action performed
    private void doExit() {
        if (Configuration.get("ASK_ON_EXIT").equals("yes")) {
            Dimension frmSize = this.getSize();
            Point loc = this.getLocation();

            ExitConfirmationDialog dlg = new ExitConfirmationDialog(this, Local.getString("Exit"));
            dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x,
                    (frmSize.height - dlg.getSize().height) / 2 + loc.y);
            dlg.setVisible(true);
            if (dlg.CANCELLED)
                return;
        }

        Context.put("FRAME_WIDTH", this.getWidth());
        Context.put("FRAME_HEIGHT", this.getHeight());
        Context.put("FRAME_XPOS", this.getLocation().x);
        Context.put("FRAME_YPOS", this.getLocation().y);
        exitNotify();
        System.exit(0);
    }

    // Found minimize error:
    // WINDOW_ICONIFIED event now calls App.minimizeWindow() method
    // Also updated WINDOW_CLOSING else clause on line 689
    // Jesse Sabbath (jsabbath) 29-January-2018
    private void doMinimize() {
        App.minimizeWindow();
    }

    // Help | About action performed
    private void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        AppFrame_AboutBox dlg = new AppFrame_AboutBox(this);
        Dimension dlgSize = dlg.getSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setVisible(true);
    }

    // Found location of missing close dialog
    // Dialog will now ask if you want to exit after
    // pressing X button (if setting is enabled)
    // It will also minimize instead of exit if
    // ON_CLOSE is set to minimize.
    // James Ortner (jortner1) 14-February-2018
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (Configuration.get("ON_CLOSE").equals("exit"))
                doExit();
            else
                doMinimize();
        } else if ((e.getID() == WindowEvent.WINDOW_ICONIFIED)) {
            super.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_ICONIFIED));
            doMinimize();
        } else
            super.processWindowEvent(e);
    }

    public static void addExitListener(ActionListener al) {
        exitListeners.add(al);
    }

    public WorkPanel getWorkPanel(){
        return this.workPanel;
    }

    private static void exitNotify() {

        for(ActionListener listener : exitListeners){
            listener.actionPerformed(null);
        }


    }

    private void setEnabledEditorMenus(boolean enabled) {
        this.jMenuEdit.setEnabled(enabled);
        this.jMenuFormat.setEnabled(enabled);
        this.jMenuInsert.setEnabled(enabled);
        this.jMenuFileNewNote.setEnabled(enabled);
        this.jMenuFileExportNote.setEnabled(enabled);
    }

    private void showPreferences() {
        PreferencesDialog dlg = new PreferencesDialog(this);
        dlg.pack();
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    private void ppExport_actionPerformed(ActionEvent e) {
        // Fix until Sun's JVM supports more locales...
        UIManager.put("FileChooser.lookInLabelText", Local.getString("Save in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString("Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString("Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local.getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local.getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString("File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString("Files of Type:"));
        UIManager.put("FileChooser.saveButtonText", Local.getString("Save"));
        UIManager.put("FileChooser.saveButtonToolTipText", Local.getString("Save selected file"));
        UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString("Cancel"));

        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Export notes"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.XHTML));
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));

        String lastSel = (String) Context.get("LAST_SELECTED_EXPORT_FILE");
        if (lastSel != null)
            chooser.setCurrentDirectory(new File(lastSel));

        ProjectExportDialog dlg = new ProjectExportDialog(App.getFrame(), Local.getString("Export notes"), chooser);
        String enc = (String) Context.get("EXPORT_FILE_ENCODING");
        if (enc != null)
            dlg.encCB.setSelectedItem(enc);
        String spl = (String) Context.get("EXPORT_SPLIT_NOTES");
        if (spl != null)
            dlg.splitChB.setSelected(spl.equalsIgnoreCase("true"));
        String ti = (String) Context.get("EXPORT_TITLES_AS_HEADERS");
        if (ti != null)
            dlg.titlesAsHeadersChB.setSelected(ti.equalsIgnoreCase("true"));
        Dimension dlgSize = new Dimension(550, 500);
        dlg.setSize(dlgSize);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;

        Context.put("LAST_SELECTED_EXPORT_FILE", chooser.getSelectedFile().getPath());
        Context.put("EXPORT_SPLIT_NOTES", new Boolean(dlg.splitChB.isSelected()).toString());
        Context.put("EXPORT_TITLES_AS_HEADERS", new Boolean(dlg.titlesAsHeadersChB.isSelected()).toString());

        int ei = dlg.encCB.getSelectedIndex();
        enc = null;
        if (ei == 1)
            enc = "UTF-8";
        boolean nument = (ei == 2);
        File f = chooser.getSelectedFile();
        boolean xhtml = chooser.getFileFilter().getDescription().indexOf("XHTML") > -1;
        CurrentProject.save();
        ProjectExporter.export(CurrentProject.get(), chooser.getSelectedFile(), enc, xhtml, dlg.splitChB.isSelected(),
                true, nument, dlg.titlesAsHeadersChB.isSelected(), false);
    }

    private void ppImport_actionPerformed(ActionEvent e) {

        UIManager.put("FileChooser.lookInLabelText", Local.getString("Look in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString("Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString("Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local.getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local.getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString("File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString("Files of Type:"));
        UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
        UIManager.put("FileChooser.openButtonToolTipText", Local.getString("Open selected file"));
        UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString("Cancel"));

        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Import notes"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
        chooser.setPreferredSize(new Dimension(550, 375));

        File lastSel = null;

        try {
            lastSel = (java.io.File) Context.get("LAST_SELECTED_NOTE_FILE");
        } catch (ClassCastException cce) {
            lastSel = new File(System.getProperty("user.dir") + File.separator);
        }
        // ---------------------------------------------------------------------

        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
            return;
        Context.put("LAST_SELECTED_NOTE_FILE", chooser.getSelectedFile());
        java.io.File f = chooser.getSelectedFile();
        HashMap<String, String> notesName = new HashMap<String, String>();
        HashMap<String, String> notesContent = new HashMap<String, String>();
        Builder parser = new Builder();
        String id = "", name = "", content = "";
        try {
            Document document = parser.build(f);
            Element body = document.getRootElement().getFirstChildElement("body");
            Element names = body.getFirstChildElement("div").getFirstChildElement("ul");
            Elements namelist = names.getChildElements("li");
            Element item;

            for (int i = 0; i < namelist.size(); i++) {
                item = namelist.get(i);
                id = item.getFirstChildElement("a").getAttributeValue("href").replace("\"", "").replace("#", "");
                name = item.getValue();
                notesName.put(id, name);
            }
            System.out.println("id: " + id + " name: " + name);

            Elements contlist = body.getChildElements("a");
            for (int i = 0; i < (contlist.size() - 1); i++) {
                item = contlist.get(i);
                id = item.getAttributeValue("name").replace("\"", "");
                content = item.getFirstChildElement("div").getValue();
                notesContent.put(id, content);
            }

            JEditorPane p = new JEditorPane();
            p.setContentType("text/html");
            for (Map.Entry<String, String> entry : notesName.entrySet()) {
                id = entry.getKey();
                name = entry.getValue().substring(11);
                content = notesContent.get(id);
                p.setText(content);
                HTMLDocument doc = (HTMLDocument) p.getDocument();
                Note note = CurrentProject.getNoteList().createNoteForDate(CurrentDate.get());
                note.setTitle(name);
                note.setId(Util.generateId());
                CurrentStorage.get().storeNote(note, doc);
            }
            workPanel.dailyItemsPanel.notesControlPane.refresh();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void p1Import_actionPerformed(ActionEvent e) {

        UIManager.put("FileChooser.lookInLabelText", Local.getString("Look in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString("Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString("Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local.getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local.getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString("File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString("Files of Type:"));
        UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
        UIManager.put("FileChooser.openButtonToolTipText", Local.getString("Open selected file"));
        UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString("Cancel"));

        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);

        chooser.setDialogTitle(Local.getString("Import notes"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
        chooser.setPreferredSize(new Dimension(550, 375));

        File lastSel = null;

        try {
            lastSel = (java.io.File) Context.get("LAST_SELECTED_NOTE_FILE");
        } catch (ClassCastException cce) {
            lastSel = new File(System.getProperty("user.dir") + File.separator);
        }
        // ---------------------------------------------------------------------

        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
            return;
        Context.put("LAST_SELECTED_NOTE_FILE", chooser.getSelectedFile());
        java.io.File f = chooser.getSelectedFile();
        HashMap<String, String> notesName = new HashMap<String, String>();
        HashMap<String, String> notesContent = new HashMap<String, String>();
        Builder parser = new Builder();
        String id = "", name = "", content = "";
        try {
            Document document = parser.build(f);
            content = document.getRootElement().getFirstChildElement("body").getValue();
            content = content.substring(content.indexOf("\n", content.indexOf("-")));
            content = content.replace("<p>", "").replace("</p>", "\n");
            name = f.getName().substring(0, f.getName().lastIndexOf("."));
            Element item;
            id = Util.generateId();
            System.out.println(id + " " + name + " " + content);
            notesName.put(id, name);
            notesContent.put(id, content);
            JEditorPane p = new JEditorPane();
            p.setContentType("text/html");

            for (Map.Entry<String, String> entry : notesName.entrySet()) {
                id = entry.getKey();
                System.out.println(id + " " + name + " " + content);
                p.setText(content);
                HTMLDocument doc = (HTMLDocument) p.getDocument();
                Note note = CurrentProject.getNoteList().createNoteForDate(CurrentDate.get());
                note.setTitle(name);
                note.setId(Util.generateId());
                CurrentStorage.get().storeNote(note, doc);
            }
            workPanel.dailyItemsPanel.notesControlPane.refresh();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
