package main.java.raf.dsw.classycraft.app.gui.swing.view.frame;


import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.UpdateClassAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.UpdateEnumAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.UpdateInterfaceAction;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterfacePainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
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
	
	private final Font myFont = new Font("Calibri", Font.PLAIN, 14);
	
	//TODO popraviti
	public EditContentPane(ElementPainter c) throws HeadlessException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setSize(screenWidth * 4 / 10, screenHeight * 6 / 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Edit content");
		
		
		if(c instanceof EnumPainter) {
			Enum e = (Enum) c.getDiagramElement();
			
			setLayout(new GridLayout(2, 1, 10, 10));
			JPanel top = new JPanel();
			BoxLayout boxLayout = new BoxLayout(top, BoxLayout.Y_AXIS);
			top.setLayout(boxLayout);
			
			top.add(new JLabel("Name"));
			
			JTextField tfName = new JTextField(e.getName());
			top.add(tfName);
			
			ArrayList<JTextField> tfEnums = new ArrayList<>();
			for(String s : e.getContents()) {
				tfEnums.add(new JTextField(s));
			}
			
			top.add(new JLabel("Enums"));
			for(JTextField tf : tfEnums) {
				top.add(tf);
			}
			
			JPanel bot = new JPanel();
			FlowLayout flowLayout = new FlowLayout();
			bot.setLayout(flowLayout);
			
			JButton btnCancel = new JButton("CANCEL");
			btnCancel.addActionListener(e1->{
				this.dispose();
			});
			
			JButton btOK = new JButton("OK");
			btOK.addActionListener(e2->{
				new UpdateEnumAction(e, tfName, tfEnums);
				this.dispose();
			});
			bot.add(btOK);
			bot.add(btnCancel);
			add(top, 0);
			add(bot, 1);
			
		}
		if(c instanceof InterfacePainter) {
			Interface i = (Interface) c.getDiagramElement();
			
			setLayout(new GridLayout(3, 1, 10, 10));
			
			JPanel top = new JPanel();
			JTextField tfName = new JTextField(i.getName());
			top.add(tfName, BOTTOM_ALIGNMENT);
			
			JPanel mid = new JPanel();
			GridLayout gridLayout = new GridLayout(i.getMethods().size() + 1, 3, 10, 10);
			mid.setLayout(gridLayout);
			
			mid.add(new JLabel("VISIBILITY"));
			mid.add(new JLabel("RETURN VAL"));
			mid.add(new JLabel("NAME"));
			
			
			ArrayList<JTextField> tfMethodNames = new ArrayList<>();
			ArrayList<JTextField> tfMethodValues = new ArrayList<>();
			ArrayList<JComboBox<Visibility>> cbMethodVisibility = new ArrayList<>();
			for(Method m : i.getMethods()) {
				tfMethodNames.add(new JTextField(m.getName()));
				tfMethodValues.add((new JTextField(m.getReturnValue())));
				JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
				comboBox.setSelectedItem(m.getVisibility());
				cbMethodVisibility.add(comboBox);
			}
			
			for(int j = 0; j < cbMethodVisibility.size(); j++) {
				
				mid.add(cbMethodVisibility.get(j), gridLayout);
				mid.add(tfMethodValues.get(j), gridLayout);
				mid.add(tfMethodNames.get(j), gridLayout);
			}
			
			JPanel bot = new JPanel();
			FlowLayout flowLayout = new FlowLayout();
			bot.setLayout(flowLayout);
			
			JButton btnCancel = new JButton("CANCEL");
			btnCancel.addActionListener(e1->{
				this.dispose();
			});
			
			JButton btOK = new JButton("OK");
			btOK.addActionListener(e2->{
				new UpdateInterfaceAction(i, tfName, cbMethodVisibility, tfMethodValues, tfMethodNames);
				this.dispose();
			});
			bot.add(btOK);
			bot.add(btnCancel);
			add(top, 0);
			add(mid, 1);
			add(bot, 2);
		}
		if(c instanceof ClassPainter) {
			Klasa k = (Klasa) c.getDiagramElement();
			
			setLayout(new GridLayout(4, 1, 10, 10));
			
			JPanel top = new JPanel();
			JTextField tfName = new JTextField(k.getName());
			top.add(tfName);
			
			ArrayList<JTextField> tfFieldNames = new ArrayList<>();
			ArrayList<JTextField> tfFieldType = new ArrayList<>();
			ArrayList<JComboBox<Visibility>> cbFieldVisibility = new ArrayList<>();
			
			ArrayList<JTextField> tfMethodNames = new ArrayList<>();
			ArrayList<JTextField> tfMethodValues = new ArrayList<>();
			ArrayList<JComboBox<Visibility>> cbMethodVisibility = new ArrayList<>();
			
			for(ClassContent content : k.getContents()) {
				if(content instanceof Field) {
					tfFieldNames.add(new JTextField(content.getName()));
					tfFieldType.add(new JTextField(((Field) content).getType()));
					JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
					comboBox.setSelectedItem(k.getVisibility());
					cbFieldVisibility.add(comboBox);
				}else {
					tfMethodNames.add(new JTextField(content.getName()));
					tfMethodValues.add(new JTextField(((Method) content).getReturnValue()));
					JComboBox<Visibility> comboBox = new JComboBox<>(Visibility.values());
					comboBox.setSelectedItem(k.getVisibility());
					cbMethodVisibility.add(comboBox);
				}
			}
			
			JPanel midF = new JPanel();
			JPanel midM = new JPanel();
			
			GridLayout gridLayoutF = new GridLayout(tfFieldNames.size() + 1, 3, 10, 10);
			GridLayout gridLayoutM = new GridLayout(tfMethodNames.size() + 1, 3, 10, 10);
			
			midF.setLayout(gridLayoutF);
			midM.setLayout(gridLayoutM);
			
			midF.add(new JLabel("VISIBILITY"));
			midF.add(new JLabel("TYPE"));
			midF.add(new JLabel("NAME"));
			
			midM.add(new JLabel("VISIBILITY"));
			midM.add(new JLabel("RETURN VAL"));
			midM.add(new JLabel("NAME"));
			
			for(int i = 0; i < tfFieldNames.size(); i++) {
				midF.add(cbFieldVisibility.get(i));
				midF.add(tfFieldType.get(i));
				midF.add(tfFieldNames.get(i));
			}
			for(int i = 0; i < tfMethodNames.size(); i++) {
				midM.add(cbMethodVisibility.get(i));
				midM.add(tfMethodValues.get(i));
				midM.add(tfMethodNames.get(i));
			}
			JPanel bot = new JPanel();
			FlowLayout flowLayout = new FlowLayout();
			bot.setLayout(flowLayout);
			
			JButton btnCancel = new JButton("CANCEL");
			btnCancel.addActionListener(e1->{
				this.dispose();
			});
			
			JButton btOK = new JButton("OK");
			btOK.addActionListener(e2->{
				new UpdateClassAction(k, tfName, cbFieldVisibility, tfFieldType, tfFieldNames, cbMethodVisibility, tfMethodValues, tfMethodNames);
				this.dispose();
			});
			bot.add(btOK);
			bot.add(btnCancel);
			add(top, 0);
			add(midF, 1);
			add(midM, 2);
			add(bot, 3);
		}
	}
}
