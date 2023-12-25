package main.java.raf.dsw.classycraft.app.gui.swing.view.frame;


import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.UpdateConnectionAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.UpdateClassAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.UpdateEnumAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.UpdateInterfaceAction;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.AggregationPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.CompositionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterfacePainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditContentPane extends JFrame {

    public EditContentPane(ElementPainter c) throws HeadlessException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(MainFrame.getInstance());
        setTitle("Edit content");

        Dimension defaultDimension = new Dimension(100, 40);
        if (c instanceof EnumPainter) {

            Enum e = (Enum) c.getDiagramElement();

            this.setSize(defaultDimension.width * 2 + 100, defaultDimension.height * (e.getContents().size() + 3) + 100);
            setLayout(new CardLayout(5, 5));

            JPanel mainGrid = new JPanel();
            GridLayout gridLayout = new GridLayout(e.getContents().size() + 3, 2, 5, 5);
            mainGrid.setLayout(gridLayout);

            JLabel lbName = new JLabel("Name: ");
            mainGrid.add(lbName);

            JTextField tfName = new JTextField(e.getName());
            mainGrid.add(tfName);

            ArrayList<JTextField> tfEnums = new ArrayList<>();
            for (String s : e.getContents()) {
                tfEnums.add(new JTextField(s));
            }

            ArrayList<JCheckBox> checkBox = new ArrayList<>();
            for (String ignored : e.getContents()) {
                checkBox.add(new JCheckBox());
            }

            JLabel lbEnums = new JLabel("Enums: ");
            mainGrid.add(lbEnums);

            JLabel lbRemove = new JLabel("Remove ?");
            mainGrid.add(lbRemove);

            for (int i = 0; i < tfEnums.size(); i++) {
                mainGrid.add(tfEnums.get(i));
                mainGrid.add(checkBox.get(i));
            }

            JButton btnCancel = new JButton("CANCEL");
            btnCancel.addActionListener(e1 -> this.dispose());
            mainGrid.add(btnCancel);

            JButton btOK = new JButton("OK");
            btOK.addActionListener(e2 -> {
                new UpdateEnumAction(e, tfName, tfEnums, checkBox);
                this.dispose();
            });
            mainGrid.add(btOK);

            add(mainGrid);

            for (Component component : this.getComponents()) {
                component.setPreferredSize(defaultDimension);
            }

        }
        if (c instanceof InterfacePainter) {
            Interface i = (Interface) c.getDiagramElement();

            this.setSize(defaultDimension.width * 4 + 100, defaultDimension.height * (i.getMethods().size() + 3) + 100);

            setLayout(new GridLayout(3, 1, 10, 10));

            JPanel top = new JPanel();
            top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

            top.add(new JLabel("Name: "));

            JTextField tfName = new JTextField(i.getName());
            top.add(tfName);

            JPanel mid = new JPanel();
            mid.setLayout(new GridLayout(i.getMethods().size() + 1, 4, 10, 10));

            mid.add(new JLabel("Visibility"));
            mid.add(new JLabel("Return value"));
            mid.add(new JLabel("Name"));
            mid.add(new JLabel("Remove ?"));

            ArrayList<JTextField> tfMethodNames = new ArrayList<>();
            ArrayList<JTextField> tfMethodValues = new ArrayList<>();
            ArrayList<JComboBox<Visibility>> cbMethodVisibility = new ArrayList<>();
            ArrayList<JCheckBox> checkBoxesMethods = new ArrayList<>();
            for (Method m : i.getMethods()) {
                tfMethodNames.add(new JTextField(m.getName()));
                tfMethodValues.add((new JTextField(m.getReturnValue())));
                JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
                comboBox.setSelectedItem(m.getVisibility());
                cbMethodVisibility.add(comboBox);
                checkBoxesMethods.add(new JCheckBox());
            }

            for (int j = 0; j < cbMethodVisibility.size(); j++) {
                mid.add(cbMethodVisibility.get(j));
                mid.add(tfMethodValues.get(j));
                mid.add(tfMethodNames.get(j));
                mid.add(checkBoxesMethods.get(j));
            }

            JPanel bot = new JPanel();
            bot.setLayout(new FlowLayout(FlowLayout.LEFT));

            JButton btnCancel = new JButton("CANCEL");
            btnCancel.addActionListener(e1 -> this.dispose());
            bot.add(btnCancel);

            JButton btOK = new JButton("OK");
            btOK.addActionListener(e2 -> {
                new UpdateInterfaceAction(i, tfName, cbMethodVisibility, tfMethodValues, tfMethodNames, checkBoxesMethods);
                this.dispose();
            });
            bot.add(btOK);

            add(top, 0);
            add(mid, 1);
            add(bot, 2);

            for (Component component : this.getComponents()) {
                component.setPreferredSize(defaultDimension);
            }
        }
        if (c instanceof ClassPainter) {
            Klasa k = (Klasa) c.getDiagramElement();

            this.setSize(defaultDimension.width * 4 + 100, defaultDimension.height * (k.getContents().size() + 4) + 100);

            setLayout(new GridLayout(4, 1, 5, 5));

            JPanel top = new JPanel();
            top.setLayout(new FlowLayout(FlowLayout.LEFT));
            top.add(new JLabel("Name: "));
            JTextField tfName = new JTextField(k.getName());
            top.add(tfName);

            ArrayList<JTextField> tfFieldNames = new ArrayList<>();
            ArrayList<JTextField> tfFieldType = new ArrayList<>();
            ArrayList<JComboBox<Visibility>> cbFieldVisibility = new ArrayList<>();
            ArrayList<JCheckBox> checkBoxesF = new ArrayList<>();

            ArrayList<JTextField> tfMethodNames = new ArrayList<>();
            ArrayList<JTextField> tfMethodValues = new ArrayList<>();
            ArrayList<JComboBox<Visibility>> cbMethodVisibility = new ArrayList<>();
            ArrayList<JCheckBox> checkBoxesM = new ArrayList<>();

            for (ClassContent content : k.getContents()) {
                if (content instanceof Field) {
                    tfFieldNames.add(new JTextField(content.getName()));
                    tfFieldType.add(new JTextField(((Field) content).getType()));
                    JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
                    comboBox.setSelectedItem(content.getVisibility());
                    cbFieldVisibility.add(comboBox);
                    checkBoxesF.add(new JCheckBox());
                } else {
                    tfMethodNames.add(new JTextField(content.getName()));
                    tfMethodValues.add(new JTextField(((Method) content).getReturnValue()));
                    JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
                    comboBox.setSelectedItem(content.getVisibility());
                    cbMethodVisibility.add(comboBox);
                    checkBoxesM.add(new JCheckBox());
                }
            }

            JPanel midF = new JPanel();
            JPanel midM = new JPanel();

            midF.setLayout(new GridLayout(tfFieldNames.size() + 1, 3, 10, 10));
            midM.setLayout(new GridLayout(tfMethodNames.size() + 1, 3, 10, 10));

            midF.add(new JLabel("Visibility"));
            midF.add(new JLabel("Type"));
            midF.add(new JLabel("Name"));
            midF.add(new JLabel("Remove ?"));

            for (int i = 0; i < tfFieldNames.size(); i++) {
                midF.add(cbFieldVisibility.get(i));
                midF.add(tfFieldType.get(i));
                midF.add(tfFieldNames.get(i));
                midF.add(checkBoxesF.get(i));
            }

            midM.add(new JLabel("Visibility"));
            midM.add(new JLabel("Return value"));
            midM.add(new JLabel("Name"));
            midM.add(new JLabel("Remove ?"));

            for (int i = 0; i < tfMethodNames.size(); i++) {
                midM.add(cbMethodVisibility.get(i));
                midM.add(tfMethodValues.get(i));
                midM.add(tfMethodNames.get(i));
                midM.add(checkBoxesM.get(i));
            }

            JPanel bot = new JPanel();
            bot.setLayout(new FlowLayout());

            JButton btnCancel = new JButton("CANCEL");
            btnCancel.addActionListener(e1 -> this.dispose());
            bot.add(btnCancel);

            JButton btOK = new JButton("OK");
            btOK.addActionListener(e2 -> {
                new UpdateClassAction(k, tfName,
                        cbFieldVisibility, tfFieldType, tfFieldNames, checkBoxesF,
                        cbMethodVisibility, tfMethodValues, tfMethodNames, checkBoxesM);
                this.dispose();
            });
            bot.add(btOK);

            add(top, 0);
            add(midF, 1);
            add(midM, 2);
            add(bot, 3);

            for (Component component : this.getComponents()) {
                component.setPreferredSize(defaultDimension);
            }
        }
        if (c instanceof AggregationPainter) {
            Aggregation k = (Aggregation) c.getDiagramElement();

            this.setSize(defaultDimension.width * 2 + 100, defaultDimension.height * 5 + 100);
            GridLayout gridLayout = new GridLayout(5, 2, 5, 5);
            setLayout(gridLayout);
            add(new JLabel("Connection: "), gridLayout);
            add(new JLabel(k.getName()), gridLayout);

            add(new JLabel("Name: "), gridLayout);
            JTextField tfName = new JTextField(k.getFieldName());
            add(tfName, gridLayout);

            add(new JLabel("Visibility: "), gridLayout);
            JComboBox<Visibility> cbVisibility = new JComboBox<>(Visibility.values());
            cbVisibility.setSelectedItem(k.getVisibility());
            cbVisibility.setSelectedItem(k.getVisibility());
            add(cbVisibility, gridLayout);

            add(new JLabel("Cardinality: "), gridLayout);
            JComboBox<Cardinality> cbCardinality = new JComboBox<>(Cardinality.values());
            cbCardinality.setSelectedItem(k.getCardinality());
            cbCardinality.setSelectedItem(k.getCardinality());
            add(cbCardinality, gridLayout);

            JButton btOK = new JButton("OK");
            btOK.addActionListener(e2 -> {
                new UpdateConnectionAction(k, tfName, cbVisibility, cbCardinality);
                this.dispose();
            });
            add(btOK, gridLayout);

            JButton btnCancel = new JButton("CANCEL");
            btnCancel.addActionListener(e1 -> this.dispose());
            add(btnCancel, gridLayout);
        }

        if (c instanceof CompositionPainter) {

            Composition k = (Composition) c.getDiagramElement();

            this.setSize(defaultDimension.width * 2 + 100, defaultDimension.height * 5 + 100);
            GridLayout gridLayout = new GridLayout(5, 2, 5, 5);
            setLayout(gridLayout);
            add(new JLabel("Connection: "), gridLayout);
            add(new JLabel(k.getName()), gridLayout);

            add(new JLabel("Name: "), gridLayout);
            JTextField tfName = new JTextField(k.getFieldName());
            add(tfName, gridLayout);

            add(new JLabel("Visibility: "), gridLayout);
            JComboBox<Visibility> cbVisibility = new JComboBox<>(Visibility.values());
            cbVisibility.setSelectedItem(k.getVisibility());
            cbVisibility.setSelectedItem(k.getVisibility());
            add(cbVisibility, gridLayout);

            add(new JLabel("Cardinality: "), gridLayout);
            JComboBox<Cardinality> cbCardinality = new JComboBox<>(Cardinality.values());
            cbCardinality.setSelectedItem(k.getCardinality());
            cbCardinality.setSelectedItem(k.getCardinality());
            add(cbCardinality, gridLayout);

            JButton btOK = new JButton("OK");
            btOK.addActionListener(e2 -> {
                new UpdateConnectionAction(k, tfName, cbVisibility, cbCardinality);
                this.dispose();
            });
            add(btOK, gridLayout);

            JButton btnCancel = new JButton("CANCEL");
            btnCancel.addActionListener(e1 -> this.dispose());
            add(btnCancel, gridLayout);
        }

    }
}
