package IS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorApp {

	public static void main(String[] args) throws InterruptedException {
		Calculator calc = new Calculator(); 
		calc.setVisible(true); 

	}

}


class Calculator extends JFrame  {
	 JLabel label1, label2, label3, label4; 
	 JButton bDean, bCathedra, bExecute ; 
	 JTextField textField1, textField2, textField3, textField4; 
	 JPanel panel; 
	 
	public Calculator() throws InterruptedException {
		super("Calculator"); 
		setBounds(370, 300, 250, 100); 
		
		label1 = new JLabel("Выберите роль пользователя");
		bCathedra = new JButton("Зав. кафедрой");
		bDean = new JButton("Декан");
		
		panel = new JPanel(new FlowLayout());  
		panel.add(label1);
		panel.add(bCathedra);
		panel.add(bDean);
		
		add(panel);
		
		bCathedra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				
				
				setBounds(630, 390, 630, 200); 
				
				label1 = new JLabel("Введите количество учеников"); 
				label2 = new JLabel("Введите количество учеников на руководителя (от 1 до 5)"); 
				label3 = new JLabel("Введите количество свободных руководителей с кафедры"); 
				label4 = new JLabel("Результат");
				
				textField1 = new JTextField("", 2); 
				textField2 = new JTextField("", 2); 
				textField3 = new JTextField("", 2); 
				textField4 = new JTextField("", 55); 
				
				bExecute = new JButton("Провести расчёт");
				bExecute.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						doCount();
						
					}
					
				});
				
				
				panel.add(label1); 
				panel.add(textField1); 
				panel.add(label2); 
				panel.add(textField2); 
				panel.add(label3); 
				panel.add(textField3); 
				panel.add(label4); 
				panel.add(textField4); 
				panel.add(bExecute);
				
			}
			
		});
		
		bDean.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				setBounds(630, 390, 250, 120); 
				
				label1 = new JLabel("Введите название кафедры"); 
				textField1 = new JTextField("", 20); 
				bExecute = new JButton("Далее");
				
				panel.add(label1);
				panel.add(textField1);
				panel.add(bExecute);
				
				bExecute.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						panel.removeAll();
						
						
						setBounds(630, 390, 630, 200); 
						
						label1 = new JLabel("Введите количество учеников"); 
						label2 = new JLabel("Введите количество учеников на руководителя (от 1 до 5)"); 
						label3 = new JLabel("Введите количество свободных руководителей с кафедры"); 
						label4 = new JLabel("Результат");
						
						textField1 = new JTextField("", 2); 
						textField2 = new JTextField("", 2); 
						textField3 = new JTextField("", 2);
						textField4 = new JTextField("", 55); 
						
						bExecute = new JButton("Провести расчёт");
						bExecute.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								doCount();
								
							}
							
						});
						
						panel.add(label1); 
						panel.add(textField1); 
						panel.add(label2); 
						panel.add(textField2); 
						panel.add(label3); 
						panel.add(textField3); 
						panel.add(label4);
						panel.add(textField4); 
						panel.add(bExecute);
						
					}
					
				});
			}
			
		});
	}

	
	public void doCount(){
		int totalStudents = 0, studentsPerTeacher = 0, freeCathedraTeachers = 0;
		int requiredTotalTeachers = 0, requiredCathedraTeachers = 0, requiredNonCathedraTeachers = 0;
		
		try {
			totalStudents = Integer.parseInt(textField1.getText());
			if(totalStudents < 0)
				throw new Exception();
			
			studentsPerTeacher = Integer.parseInt(textField2.getText());
			if(!(studentsPerTeacher >= 1 && studentsPerTeacher <= 5))
				throw new Exception();
			
			freeCathedraTeachers = Integer.parseInt(textField3.getText());
			if(freeCathedraTeachers < 0)
				throw new Exception();
		}
		catch(Exception e) {
			totalStudents = 0;
			textField1.setText("");
			
			studentsPerTeacher = 0;
			textField2.setText("");
			
			freeCathedraTeachers = 0;
			textField3.setText("");
			
			textField4.setText("Ошибка ввода");
			return;
		}
		
		requiredTotalTeachers = totalStudents / studentsPerTeacher;
		if(totalStudents % studentsPerTeacher != 0)
			requiredTotalTeachers++;
		
		if(requiredTotalTeachers <= freeCathedraTeachers)
			requiredCathedraTeachers = requiredTotalTeachers;
		else {
			requiredCathedraTeachers = freeCathedraTeachers;
			requiredNonCathedraTeachers = requiredTotalTeachers - freeCathedraTeachers;
		}
		textField4.setText("Необходимы дипломные руководители в количестве " + requiredTotalTeachers + " чел.: " + requiredCathedraTeachers + " чел. с кафедры и " + requiredNonCathedraTeachers + " чел. не с кафедры."); 
	}
	
}
