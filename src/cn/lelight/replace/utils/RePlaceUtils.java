package cn.lelight.replace.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version ����ʱ�䣺2016��12��30�� ����2:10:24 ��˵��
 */
public class RePlaceUtils {
	// ԴĿ¼�������ļ����Ѿ�·��
	private static List<FileBean> srcFiles;
	// Ŀ��Ŀ¼�������ļ����Ѿ�·��
	private static List<FileBean> drcFiles;

	public static void startRePlace(String srcPath, String drsPath) {
		srcFiles = getFilesNameAndPath(srcPath);
		drcFiles = getFilesNameAndPath(drsPath);
		// ���߶Ա�,���滻Դ�ļ���Ŀ���ļ�
		for (int i = 0; i < srcFiles.size(); i++) {
			for (int j = 0; j < drcFiles.size(); j++) {
				System.out.println("�Ƚ���������:"+srcFiles.get(i).name+":"+drcFiles.get(j).name);
				if (srcFiles.get(i).name.equals(drcFiles.get(j).name)) {
					System.out.println("�������-----------------------");
					try {
						// ��������һ��,�滻
						File srcfile = new File(srcFiles.get(i).path);
						File drcfile = new File(drcFiles.get(j).path);
						System.out.println("��ʼ�滻:\n" + srcFiles.get(i).path +
								"\n" + drcFiles.get(j).path);
						copyFile(srcfile, drcfile);
						System.out.println("�滻�ɹ�: " + srcFiles.get(i).name);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("�����쳣:" + srcFiles.get(i).name + "\n" + e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * ��ȡĿ¼�������ļ������Լ�·��
	 */
	private static List<FileBean> getFilesNameAndPath(String path) {
		List<FileBean> list = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isFile()) {
					FileBean fileBean = new FileBean();
					fileBean.name = listFiles[i].getName();
					fileBean.path = listFiles[i].getAbsolutePath();
					list.add(fileBean);
				} else if (listFiles[i].isDirectory()) {
					System.out.println("�ļ���:" + listFiles[i].getAbsolutePath());
					list.addAll(getFilesNameAndPath(listFiles[i].getAbsolutePath()));
				}
			}
		} else {
			System.out.println("ԴĿ¼������");
		}
		return list;
	}

	public static void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream ins = new FileInputStream(fromFile);
		FileOutputStream out = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, b.length);
		}
		ins.close();
		out.close();
	}

	public static void reNameFile(String srcPath, String newname) {
		String oldname;
		File file = new File(srcPath);
		oldname = file.getName();

		srcPath = srcPath.replace(oldname, "");
		System.out.println(srcPath + "======" + oldname + " ������:" + newname);

		if (!oldname.equals(newname)) {// �µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ����������
			File oldfile = new File(srcPath + "/" + oldname);
			File newfile = new File(srcPath + "/" + newname);
			if (!oldfile.exists()) {
				return;// �������ļ�������
			}
			if (newfile.exists()) // ���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ��������������
				System.out.println(newname + "�Ѿ����ڣ�");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("���ļ����;��ļ�����ͬ...");
		}

	}

}
