package cn.lelight.replace.plane;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.lelight.replace.bean.RePlaceItem;
import cn.lelight.replace.utils.RePlaceUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version 创建时间：2016年12月30日 下午3:18:29 类说明
 */
public class ImagePlane extends JPanel implements ActionListener {
	private java.util.List<RePlaceItem> rePlaceItems;
	// private Container containPane;
	private JTextField inputSrcFile;
	private JButton srcFileButton;
	private JTextField inputDrcFile;
	private JButton drcFileButton;
	private JButton startButton;
	private JLabel hintLable;

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// showJFrame obj = new showJFrame();
	// obj.testJFrame();
	// }

	public ImagePlane(int width, int height) {
		setSize(width, height);

		rePlaceItems = new ArrayList<>();
		rePlaceItems.add(new RePlaceItem("App Logo", "ic_launcher.png"));
		rePlaceItems.add(new RePlaceItem("闪屏页", "ic_splash_bg.png"));
		rePlaceItems.add(new RePlaceItem("引导页1", "ic_1.png"));
		rePlaceItems.add(new RePlaceItem("引导页2", "ic_2.png"));
		rePlaceItems.add(new RePlaceItem("引导页3", "ic_3.png"));
		rePlaceItems.add(new RePlaceItem("登录页 logo", "ic_icon.png"));
		rePlaceItems.add(new RePlaceItem("设备,轮播图", "ic_devcies_header.png"));

		// JFrame frame = new subJFrame("App 3.0+ 版本替换");
		// containPane = frame.getContentPane();
		setLayout(null);
		initScrDrcFilePathPane();
		initSelectImgPane();
		initBottomButton();

		setVisible(true);
	}

	private void initScrDrcFilePathPane() {
		JPanel panel = new JPanel();
		panel.setSize(800, 40);
		panel.setLocation(0, 0);
		panel.setBackground(new Color(208,208,208));
		JLabel srcText = new JLabel("图片源文件夹:");
		srcText.setPreferredSize(new Dimension(100, 20));
		panel.add(srcText);
		inputSrcFile = new JTextField(40);
		inputSrcFile.setToolTipText("输入源文件夹");
		srcFileButton = new JButton("选择文件夹");
		srcFileButton.addActionListener(this);
		panel.add(inputSrcFile);
		panel.add(srcFileButton);
		add(panel);

		JPanel panel2 = new JPanel();
		panel2.setSize(800, 40);
		panel2.setLocation(0, 40);
		panel2.setBackground(new Color(208,208,208));
		JLabel drcText = new JLabel("图片目标文件夹:");
		drcText.setPreferredSize(new Dimension(100, 20));
		panel2.add(drcText);
		inputDrcFile = new JTextField(40);
		drcFileButton = new JButton("选择文件夹");
		drcFileButton.addActionListener(this);
		panel2.add(inputDrcFile);
		panel2.add(drcFileButton);
		add(panel2);
	}

	private void initSelectImgPane() {
		int ds = 80;
		for (int i = 0; i < rePlaceItems.size(); i++) {
			ds = 80 + i * 54;
			add(initFileSelectPanel(rePlaceItems.get(i), ds));
		}
	}

	private JPanel initFileSelectPanel(RePlaceItem rePlaceItem, int ds) {
		JPanel fileSelectPanel = new JPanel();
		fileSelectPanel.setSize(800, 54);
		fileSelectPanel.setLocation(0, ds);
		fileSelectPanel.setBackground(Color.white);
		// ---------------------------------
		JLabel srcText = new JLabel(rePlaceItem.name);
		srcText.setPreferredSize(new Dimension(100, 20));
		// ---------------------------------
		rePlaceItem.actionText = new JTextField(40);
		rePlaceItem.actionButton = new JButton("选 择 图 片");
		rePlaceItem.actionButton.addActionListener(this);
		// ---------------------------------
		fileSelectPanel.add(srcText);
		fileSelectPanel.add(rePlaceItem.actionText);
		fileSelectPanel.add(rePlaceItem.actionButton);
		return fileSelectPanel;
	}

	private void showFileChooserAndSetTextFild(JTextField textField) {
		String srcFileDir = inputSrcFile.getText().toString().trim();
		JFileChooser jFileChooser;
		if (!"".equals(srcFileDir)) {
			// 源文件夹不为空
			File file = new File(srcFileDir);
			if (file.exists() && file.isDirectory()) {
				jFileChooser = new JFileChooser(srcFileDir);
			} else {
				jFileChooser = new JFileChooser();
			}
		} else {
			jFileChooser = new JFileChooser();
		}

		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jFileChooser.showDialog(new JLabel(), "选择");
		File file = jFileChooser.getSelectedFile();
		if (file.isDirectory()) {
			textField.setText(file.getAbsolutePath());
		} else if (file.isFile()) {
			textField.setText(file.getAbsolutePath());
		}
	}

	private void initBottomButton() {

		JPanel panel2 = new JPanel();
		panel2.setSize(800, 40);
		panel2.setLocation(0, 80 + 54 * 7);
		panel2.setBackground(new Color(208,208,208));

		hintLable = new JLabel("提示信息在此显示");
		hintLable.setPreferredSize(new Dimension(800, 40));
		hintLable.setBackground(Color.gray);
		hintLable.setForeground(Color.red);
		hintLable.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(hintLable);
		add(panel2);

		JPanel panel = new JPanel();
		panel.setSize(800, 40);
		panel.setLocation(0, 80 + 54 * 7 + 40);

		startButton = new JButton("开  始  替  换");
		startButton.setPreferredSize(new Dimension(600, 30));
		startButton.addActionListener(this);
		panel.add(startButton);
		add(panel);
	}

	/**
	 * 监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == srcFileButton) {
			showFileChooserAndSetTextFild(inputSrcFile);
		} else if (e.getSource() == drcFileButton) {
			showFileChooserAndSetTextFild(inputDrcFile);
		} else if (e.getSource() == startButton) {
			// 开始替换
			// 检查是否全部信息填写完毕
			String srcFilePath = inputSrcFile.getText().trim();
			String drcFilePath = inputDrcFile.getText().trim();

			if (srcFilePath.equals("")) {
				hintLable.setText("源文件夹路径不能为空");
				return;
			}
			if (drcFilePath.equals("")) {
				hintLable.setText("目标文件夹路径不能为空");
				return;
			}

			// for (int i = 0; i < rePlaceItems.size(); i++) {
			// if (rePlaceItems.get(i).actionText.getText().trim().equals("")) {
			// hintLable.setText(rePlaceItems.get(i).name + "路径不能为空");
			// return;
			// }
			// }

			String filePath = rePlaceItems.get(0).actionText.getText();
			RePlaceUtils.reNameFile(filePath, rePlaceItems.get(0).fileName);

			// 开始重命名资源
			// for (int i = 0; i < rePlaceItems.size(); i++) {
			// String filePath = rePlaceItems.get(i).actionText.getText();
			// RePlaceUtils.reNameFile(filePath, rePlaceItems.get(i).fileName);
			// }
			// 开始替换文件

			RePlaceUtils.startRePlace(srcFilePath, drcFilePath);

			// 完成?
			hintLable.setText(
					"                                                                                                                                      替换完成                                                                                                                                                                                                         ");

		} else {
			for (int i = 0; i < rePlaceItems.size(); i++) {
				if (e.getSource() == rePlaceItems.get(i).actionButton) {
					System.out.println("你点击了第" + i + "个");
					showFileChooserAndSetTextFild(rePlaceItems.get(i).actionText);
				}
			}
		}
	}

}
