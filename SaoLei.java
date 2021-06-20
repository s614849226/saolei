import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SaoLei implements ActionListener {
	JFrame frame = new JFrame();

	ImageIcon bannerIcon = new ImageIcon("BannerJ.png");
	ImageIcon guessIcon = new ImageIcon("guess.png");
	ImageIcon bombIcon = new ImageIcon("bomb.png");
	ImageIcon failIcon = new ImageIcon("fail.png");
	ImageIcon winIcon = new ImageIcon("win.png");
	ImageIcon winFlagIcon = new ImageIcon("win_flag.png");

	JButton bannerBtn = new JButton(bannerIcon);

	
	//���ݽṹ
	int ROW = 20;
	int COL = 20; //��������
	int[][] data = new int[ROW][COL];
	JButton[][] btns = new JButton[ROW][COL];
	int LEICOUNT = 1;//�׵�����
	int LEICODE = -1;//�׵Ĵ���
	int unopened = ROW*COL;
	int opened = 0;
	int seconds = 0;//ʱ�Ӽ���
	JLabel label1 = new JLabel("�ѿ���"+opened);
	JLabel label2 = new JLabel("������"+unopened);
	JLabel label3 = new JLabel("��ʱ��"+seconds+"s");
	Timer timer = new Timer(1000,this);
	public SaoLei() {
		frame.setSize(600,700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		setHeader();
		addLei();

		setButtons();
		timer.start();
		frame.setVisible(true);
	}
	private void addLei() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		for(int i = 0;i<LEICOUNT;) {
			//��ֹ�׳�����ͬһ���ط���ʹ�ײ���Leicount
			int r = rand.nextInt(ROW);//�����������
			int c = rand.nextInt(COL);
			if(data[r][c]!=LEICODE) {
				data[r][c]=LEICODE;
				i++;
			//	System.out.print(r+c);

				//System.out.print(data[r][c]);
			}
		}
		//�����ܱߵ��׵�����
		for(int i = 0;i<ROW;i++) {
			for(int j = 0;j<COL;j++) {
				if(data[i][j] == LEICODE) continue;
				int tempCount = 0;
				if(i>0&&j>0&&data[i-1][j-1] == LEICODE) tempCount++;
				if(i>0&&data[i-1][j] == LEICODE) tempCount++;
				if(j>0&&data[i][j-1] == LEICODE) tempCount++;
				if(i<19&&j<19&&data[i+1][j+1] == LEICODE) tempCount++;
				if(i<19&&data[i+1][j] == LEICODE) tempCount++;
				if(j<19&&data[i][j+1] == LEICODE) tempCount++;
				if(i>0&&j<19&&data[i-1][j+1] == LEICODE) tempCount++;
				if(j>0&&i<19&&data[i+1][j-1] == LEICODE) tempCount++;
				data[i][j] = tempCount;

			}
		}
		
	}
	private void setButtons() {
		// TODO Auto-generated method stub
		Container con = new Container();
		con.setLayout(new GridLayout(ROW,COL));//д�ʺŸ���
		for(int i = 0;i<ROW;i++) {
			for(int j = 0;j<COL;j++) {
				JButton btn = new JButton(guessIcon);
				btn.setOpaque(true);
				btn.setBackground(new Color(244,183,113));
				btn.addActionListener(this);
				//JButton btn = new JButton(data[i][j]+"");
				btn.setMargin(new Insets(0,0,0,0));
			//	System.out.print(data[i][j]);
				con.add(btn);
				btns[i][j] = btn;
			}
		}
		
		frame.add(con,BorderLayout.CENTER);

		
	}
	private void setHeader()
	{
		JPanel panel = new JPanel(new GridBagLayout());//����һ���������������ڹ涨���Ĳ���
		GridBagConstraints c1 = new GridBagConstraints(0,0,3,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
		//����button�ĸ�ʽ������ֱ�����������label�ĸ�ʽ 
		panel.add(bannerBtn, c1);
		bannerBtn.addActionListener(this);
		label1.setOpaque(true);
		label1.setBackground(Color.white);
		label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label2.setOpaque(true);
		label2.setBackground(Color.white);
		label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label3.setOpaque(true);
		label3.setBackground(Color.white);
		label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		bannerBtn.setOpaque(true);
		bannerBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		bannerBtn.setBackground(Color.white);
		
		GridBagConstraints c2 = new GridBagConstraints(0,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
		panel.add(label1, c2);
		GridBagConstraints c3 = new GridBagConstraints(1,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
		panel.add(label2, c3);
		GridBagConstraints c4 = new GridBagConstraints(2,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
		panel.add(label3, c4);	
		
		
		frame.add(panel,BorderLayout.NORTH);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SaoLei();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof Timer) {
			seconds++;
			label3.setText("��ʱ��"+seconds+"s");
			timer.start();
			return;
		}
		JButton btn = (JButton)e.getSource();
		if(btn.equals(bannerBtn)) {
			restart();
			return;
		}
		for(int i = 0;i<ROW;i++) {
			for(int j = 0;j<COL;j++) {
				if(btn.equals(btns[i][j])){//�������а�ť
					if(data[i][j] == LEICODE) {
						lose();
					}
					else {
					openCell(i,j);
					checkWin();
				}
					return;

			}
		}
		
	}}
	
	//�������㣬��ť�ָ�����������ʱ��
	private void restart() {
		// TODO Auto-generated method stub
		
		for(int i = 0;i<ROW;i++) {
			for(int j = 0;j<COL;j++) {
				data[i][j]=0;
				btns[i][j].setBackground(new Color(244,183,113));
				btns[i][j].setEnabled(true);
				btns[i][j].setText("");
				btns[i][j].setIcon(guessIcon);
				
			}

		}
		int unopened = ROW*COL;
		int opened = 0;
		int seconds = 0;//ʱ�Ӽ���
		 label1.setText("�ѿ���"+opened);
	     label2.setText("������"+unopened);
		 label3.setText("��ʱ��"+seconds+"s");
		addLei();
		//setButtons();
		timer.start();

		
	}
	private void checkWin() {
		// TODO Auto-generated method stub
		int count=0;
		for(int i = 0;i<ROW;i++) {
			for(int j = 0;j<COL;j++) {
				if(btns[i][j].isEnabled()) count++;			
			}
		}
		if(count == LEICOUNT) {
			for(int i = 0;i<ROW;i++) {
				for(int j = 0;j<COL;j++) {
					if(btns[i][j].isEnabled()) {
						btns[i][j].setIcon(winFlagIcon);
					}
				}
				
			}
			bannerBtn.setIcon(winIcon);
			JOptionPane.showMessageDialog(frame,"��ϲ��ȡ��ʤ����\n����Ե���������¿�ʼ��","Ӯ�ˣ�",JOptionPane.PLAIN_MESSAGE);
			timer.stop();
		}
		
	}
	private void lose() {
		// TODO Auto-generated method stub
		bannerBtn.setIcon(failIcon);
		for(int i = 0;i<ROW;i++) {
					for(int j = 0;j<COL;j++) {
				if(btns[i][j].isEnabled()) {
					JButton btn1 = btns[i][j];

					if(data[i][j] ==LEICODE) {
						btn1.setIcon(bombIcon);
						btn1.setEnabled(false);
						btn1.setDisabledIcon(bombIcon);
			
					}
					else {
						btn1.setIcon(null);
						btn1.setEnabled(false);
						btn1.setOpaque(true);
						//btn1.setBackground(Color.GREEN);
						btn1.setText(data[i][j]+"");
						
					}
					}
					}
			}
		timer.stop();
		JOptionPane.showMessageDialog(frame,"�ܿ�ϧ��ʧ���ˣ�\n����Ե���������¿�ʼ��","��������",JOptionPane.PLAIN_MESSAGE);
		}
	
	private void openCell(int i, int j) {
		JButton btn = btns[i][j];
		if(!btn.isEnabled()) return;
		
		btn.setIcon(null);
		btn.setEnabled(false);
		btn.setOpaque(true);
		btn.setBackground(Color.GREEN);
		btn.setText(data[i][j]+"");
		addopenCount();
		
		if(data[i][j] == 0) {
			if(i>0&&j>0&&data[i-1][j-1] == 0) openCell(i-1,j-1);
			if(i>0&&data[i-1][j] == 0 ) openCell(i-1,j);
			if(j>0&&data[i][j-1] == 0) openCell(i,j-1);
			if(i<19&&j<19&&data[i+1][j+1] == 0) openCell(i+1,j+1);
			if(i<19&&data[i+1][j] == 0) openCell(i+1,j);
			if(j<19&&data[i][j+1] == 0) openCell(i,j+1);
			if(i>0&&j<19&&data[i-1][j+1] == 0) openCell(i-1,j+1);
			if(j>0&&i<19&&data[i+1][j-1] == 0) openCell(i+1,j-1);
			
		}
		
		
	}
	private void addopenCount() {
		// TODO Auto-generated method stub
		opened++;
		unopened--;
		label1.setText("�ѿ���" + opened);
		label2.setText("������" + unopened);
		
	}
}
