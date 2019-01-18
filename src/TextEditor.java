import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.undo.*;

public class TextEditor extends JFrame implements ActionListener{

	JFrame frame;
	JMenu file,edit,search;
	JMenuBar menuBar;
	JMenuItem newFile, open, save, saveAs, close, exit;
	JMenuItem copy, paste, delete, selectAll;
	JMenuItem undo, redo;
	JMenuItem find, findRep;
	JTextArea TextArea;
	FileDialog fileDia;
	String name,path,n;
	boolean flag;
	JTabbedPane sekme = new JTabbedPane();
	int sekmeSayisi = 0;
	String mouse;
	int iterator = 0;
	JTextArea textArea = new JTextArea();
	UndoManager undoManager = new UndoManager();
	boolean und;



	public TextEditor() {

		super("TextEditor");
		setSize(400,400);

		menuBar = new JMenuBar();

		TextArea = new JTextArea();

		add(TextArea);

		setJMenuBar(menuBar);

		file = new JMenu("File");
		edit = new JMenu("Edit");
		search = new JMenu("Search");

		newFile = new JMenuItem("New File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save as...");
		close = new JMenuItem("Close File");
		exit = new JMenuItem("Exit");

		fileDia=new FileDialog(frame,"Save As",FileDialog.SAVE);

		file.add(newFile);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(close);
		file.add(exit);

		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		delete = new JMenuItem("Delete");
		selectAll = new JMenuItem("Select All");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");

		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.add(selectAll);
		edit.add(undo);
		edit.add(redo);

		find = new JMenuItem("Find");
		findRep = new JMenuItem("Find and Replace");

		search.add(find);
		search.add(findRep);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(search);

		newFile.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveAs.addActionListener(this);
		close.addActionListener(this);
		exit.addActionListener(this);

		copy.addActionListener(this);
		paste.addActionListener(this);
		delete.addActionListener(this);
		selectAll.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);

		find.addActionListener(this);
		findRep.addActionListener(this);

		TextArea.getDocument().addUndoableEditListener(
		        new UndoableEditListener() {
		          public void undoableEditHappened(UndoableEditEvent e) {
		            undoManager.addEdit(e.getEdit());
		            butonYenile();
		          }
		        });

		setVisible(true);

	}


	public void actionPerformed(ActionEvent e) {
		JMenuItem choice =(JMenuItem)e.getSource();

		if (choice == newFile ) {
			//open new tab
		}
		else if (choice == open) {
		        JFileChooser input = new JFileChooser();
		        int sec = input.showOpenDialog(this);
		        if (sec == JFileChooser.APPROVE_OPTION) {
		            this.TextArea.setText("");
		        try {
					Scanner scanner = new Scanner(new FileReader(input.getSelectedFile().getPath()));
					while (scanner.hasNext())
						this.TextArea.append(scanner.nextLine() + "\n");
					scanner.close();
		        } catch (Exception a) {
		                System.out.println(a.getMessage());
		            }
		        }
		}
		else if (choice == save) {
			  fileDia=new FileDialog(frame,"Save",FileDialog.SAVE);
		      if(flag==false)
		      {
		    	  fileDia.setVisible(true);
		           name=fileDia.getFile();
		           path=fileDia.getDirectory();
		           String strr=TextArea.getText();
		           File f=new File(path,name);
		        FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(f);
					 char arr[];
				        arr=strr.toCharArray();
				        for(int i=0;i<arr.length;i++)
				        {
				          fos.write(arr[i]);
				        }
				        fos.close();
				} catch (Exception e1) {
					System.out.println("err");
				}
		      }
		         else
		         {
		          System.out.println(n);
		            String strr1=TextArea.getText();
		            File f=new File(n);
		            FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(f);
						char arr[];
				        arr=strr1.toCharArray();
				        for(int i=0;i<arr.length;i++)
				           fos.write(arr[i]);
				        fos.close();
					} catch (Exception e1) {
						System.out.println("err");
					}

		         }
		}
		else if(choice == saveAs) {
			  fileDia=new FileDialog(frame,"Save As",FileDialog.SAVE);
			  fileDia.setVisible(true);
		      name=fileDia.getFile();
		      path=fileDia.getDirectory();
		      File f=new File(path,name);
		      String str1=TextArea.getText();
		      FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f);
				char arr[];
			    arr=str1.toCharArray();
			    for(int i=0;i<arr.length;i++)
			    	fos.write(arr[i]);
			    fos.close();
			} catch (Exception e1) {
				System.out.println("err");
			}


		}
		else if (choice == close) {
			//close the tab
		}
		else if( choice == exit ) {
			System.exit(1);
		}
		else if (choice == copy) {
			mouse = TextArea.getSelectedText();
		}
		else if (choice == paste) {
			TextArea.insert(mouse, TextArea.getCaretPosition());
		}
		else if(choice == delete) {
			TextArea.replaceRange("", TextArea.getSelectionStart(), TextArea.getSelectionEnd());
		}
		else if (choice == selectAll) {
			TextArea.selectAll();
		}
		else if (choice == undo){


	        try {
	          undoManager.undo();
	        } catch (Exception ee) {
	          System.out.println(ee.getMessage());
	        }
	        butonYenile();

	    }
	    else if (choice == redo){

	    	try {
	            undoManager.redo();
	          } catch (Exception eee) {
		          System.out.println("err");
		        }
	    	butonYenile();

	    }
		else if (choice == find) {

			JFrame f=new JFrame("Find");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        JPanel arama = new JPanel();
	        arama.setLayout(new FlowLayout());
	        JButton b = new JButton("Find");
	        b.setBounds(240, 20, 200, 30);
	        TextField textBul=new TextField();
	        textBul.setBounds(15, 20, 200, 30);
	        f.add(textBul);

	        b.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
		        	String arancak = textBul.getText();
		            String arancakYer = TextArea.getText();
		            if(arancakYer.indexOf(arancak) != -1) {
		            	TextArea.select(arancakYer.indexOf(arancak), (arancakYer.indexOf(arancak) + arancak.length()));
		            }
	            }
	        });

	        f.add(b);
	        f.setSize(500,75);
	        f.setLayout(null);
	        f.setVisible(true);
	        f.setLocationRelativeTo(null);


		}
		else if(choice == findRep) {


			JFrame f=new JFrame("Find and Replace");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        JPanel arama = new JPanel();
	        arama.setLayout(new FlowLayout());
	        JButton b = new JButton("Replace All");
	        b.setBounds(240, 20, 200, 30);
	        TextField textBul=new TextField();
	        textBul.setBounds(15, 20, 200, 20);
	        f.add(textBul);
	        TextField replaceYap = new TextField();
	        replaceYap.setBounds(15, 40, 200, 20);
	        f.add(replaceYap);

	        b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
	        	String arancak = textBul.getText();
	        	String degis = replaceYap.getText();
	            String arancakYer = TextArea.getText();

	                if(arancakYer.indexOf(arancak) >= 0){
	                	while(arancakYer.indexOf(arancak) != -1)
	                     arancakYer = arancakYer.substring(0, arancakYer.indexOf(arancak)) + degis + arancakYer.substring(arancakYer.indexOf(arancak)+arancak.length());
	                	TextArea.setText(arancakYer);
	                }
	                else {

					}
	            }
	        });

	        f.add(b);
	        f.setSize(500,75);
	        f.setLayout(null);
	        f.setVisible(true);
	        f.setLocationRelativeTo(null);

		}
		else {

		}

	}

	public static void main(String[] args) {

		TextEditor t = new TextEditor();
		t.setVisible(true);
	}


	public void butonYenile() {
	    undo.setEnabled(undoManager.canUndo());
	    redo.setEnabled(undoManager.canRedo());
	  }



}
