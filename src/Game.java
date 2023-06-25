import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Game extends JFrame implements ActionListener{
	private static final int DEFAULT_FORMWIDTH = 500;
	private static int DEFAULT_NUMBERS = 21;
	private static int riddledNumber = 0;
	
	JMenuBar menuBar;
	JMenu newGameMenu;
	JMenu levelMenu;
	JMenuItem easyLevel;
	JMenuItem middleLevel;
	JMenuItem hardLevel;
	
	ImageIcon ai;
	ImageIcon firework;
	JPanel topPanel;
	JPanel bodyPanel;
	JPanel fireworkPanel;
	JLabel aiLabel;
	JLabel fireworkLabel;
	JButton[] numbers;
	Random random;
	Game(){
//		
		menuBar = new JMenuBar();
		newGameMenu = new JMenu("Нова гра");
		newGameMenu.addMenuListener(new SampleMenuListener());
		
		levelMenu = new JMenu("Рівень гри");
		easyLevel = new JMenuItem("Легкий");
		middleLevel = new JMenuItem("Середній");
		hardLevel = new JMenuItem("Складний");
		
		easyLevel.addActionListener(this);
		middleLevel.addActionListener(this);
		hardLevel.addActionListener(this);
		
		levelMenu.add(easyLevel);
		levelMenu.add(middleLevel);
		levelMenu.add(hardLevel);
		
		menuBar.add(newGameMenu);
		menuBar.add(levelMenu);
		
		firework = new ImageIcon("firework.gif");
		fireworkLabel = new JLabel();
		fireworkLabel.setIcon(firework);	
		fireworkLabel.setHorizontalTextPosition(JLabel.LEFT);
		fireworkLabel.setHorizontalAlignment(JLabel.LEFT);
		
		fireworkPanel = new JPanel();
		fireworkPanel.add(fireworkLabel);
		fireworkPanel.setBounds(0, 0, DEFAULT_FORMWIDTH, 155);
		fireworkPanel.setBackground(new Color(0,0,0));
		fireworkPanel.setLayout(new FlowLayout(FlowLayout.LEFT));		
		fireworkPanel.setVisible(false);
		
		
		ai = new ImageIcon("ai.png");		
		aiLabel = new JLabel();
		aiLabel.setIcon(ai);
		aiLabel.setText("<html>Привіт, я загадала число" + "<br>" + "від 0 до " + (DEFAULT_NUMBERS-1) + " спробуй відгадати!</html>");
		aiLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		aiLabel.setForeground(Color.magenta);
		aiLabel.setIconTextGap(5);
		
		topPanel = new JPanel();
		topPanel.add(aiLabel);
		topPanel.setBounds(0, 0, DEFAULT_FORMWIDTH, 155);
		topPanel.setBackground(new Color(128,255,204));

		bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(128,255,204));
		bodyPanel.setBounds(0, 155, DEFAULT_FORMWIDTH, 200);
		bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		bodyPanel.setBorder(BorderFactory.createMatteBorder(
                20, 20, 20, 20, new Color(128,255,224)));
		
		numbers = new JButton[DEFAULT_NUMBERS];			
		
		for(int i=0; i<numbers.length;i++) {
			numbers[i] = new JButton();
			numbers[i].setText(""+ i);
			numbers[i].setFocusable(false);
			numbers[i].setBackground(Color.WHITE);	
			numbers[i].addActionListener(this);
			bodyPanel.add(numbers[i]);
			
		}
		

		
		this.setTitle("Відгадайка від Аї");
		this.setJMenuBar(menuBar);		
		this.add(topPanel);
		this.add(bodyPanel);	
		this.add(fireworkPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.darkGray);
		this.setLayout(null);
		this.setSize(DEFAULT_FORMWIDTH+15,620);
		this.setVisible(true);
		
		random = new Random();
		riddledNumber = random.nextInt(DEFAULT_NUMBERS);
//		riddledNumber = 1;
		
	}

	public void win(int pos) {
		numbers[pos].setBackground(Color.green);
		aiLabel.setText("Вітаю друже, ти переміг!");
		for(int i=0; i<numbers.length;i++) {
			numbers[i].setEnabled(false);
		}
		aiLabel.revalidate();
		aiLabel.repaint();
		topPanel.revalidate();
		topPanel.repaint();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		fireworkPanel.setVisible(true);
		topPanel.setBackground(new Color(0,0,0));
				
	}
	
	public void nextTry(int i) {
		numbers[i].setBackground(Color.pink);
	}
	
	public void restartGame() {
		riddledNumber = random.nextInt(DEFAULT_NUMBERS);
		aiLabel.setText("<html>Привіт, я загадала число" + "<br>" + "від 0 до " + (DEFAULT_NUMBERS-1) + " спробуй відгадати!</html>");
		for(int i=0; i<numbers.length;i++) {			
			numbers[i].setBackground(Color.WHITE);			
			numbers[i].setEnabled(true);			
		}
		fireworkPanel.setVisible(false);
		topPanel.setBackground(new Color(128,255,204));
	}
	
	public void setButton(int num, int height) {
		
		for(int i=0; i<numbers.length;i++) {
			bodyPanel.remove(numbers[i]);			
		}
		bodyPanel.revalidate();
		bodyPanel.repaint();
		
		numbers = new JButton[num];
		for(int i=0; i<numbers.length;i++) {
			numbers[i] = new JButton();
			numbers[i].setText(""+ i);
			numbers[i].setFocusable(false);
			numbers[i].setBackground(Color.WHITE);	
			numbers[i].addActionListener(this);
			bodyPanel.add(numbers[i]);
			
		}
		bodyPanel.setBounds(0, 155, DEFAULT_FORMWIDTH, height);
		bodyPanel.revalidate();
		bodyPanel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newGameMenu) {
			System.out.print("new");
		}
		if(e.getSource()==easyLevel) {
			DEFAULT_NUMBERS = 21;
			setButton(DEFAULT_NUMBERS, 200);
		}
		if(e.getSource()==middleLevel) {
			DEFAULT_NUMBERS = 51;
			setButton(DEFAULT_NUMBERS, 300);
		}
		if(e.getSource()==hardLevel) {
			DEFAULT_NUMBERS = 101;
			setButton(DEFAULT_NUMBERS, 500);
			this.setSize(DEFAULT_FORMWIDTH+15,720);
		}
		
		
		
		for(int i=0; i<numbers.length;i++) {			 
			if(e.getSource()==numbers[i]) {
				if(riddledNumber == Integer.parseInt(numbers[i].getText())) {
					win(i);
				} else {
					nextTry(i);
					
				}
			}
		}
		
	}
	
	
	class SampleMenuListener implements MenuListener {

	    @Override
	    public void menuSelected(MenuEvent e) {
	        restartGame();
	    }


		@Override
		public void menuDeselected(MenuEvent e) {
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			
		}
	}
	
	
}
