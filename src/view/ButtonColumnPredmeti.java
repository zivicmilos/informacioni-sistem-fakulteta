package view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import model.BazaPredmeta;
import model.Predmet;

public class ButtonColumnPredmeti implements TableCellRenderer {

	private JTable table = null;
	private MouseEventReposter reporter = null;
	private JComponent editor;

	public ButtonColumnPredmeti(JComponent editor) {
		this.editor = editor;
		this.editor.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		if (table != null && this.table != table) {
			this.table = table;
			final JTableHeader header = table.getTableHeader();
			if (header != null) {
				this.editor.setForeground(header.getForeground());
				this.editor.setBackground(header.getBackground());
				this.editor.setFont(header.getFont());
				reporter = new MouseEventReposter(header, col, this.editor);
				header.addMouseListener((MouseListener) reporter);
			}
		}

		if (reporter != null)
			reporter.setColumn(col);

		return this.editor;
	}

	static public class MouseEventReposter extends MouseAdapter {

		private Component dispatchComponent;
		private JTableHeader header;
		private int column = -1;
		private Component editor;

		public MouseEventReposter(JTableHeader header, int column, Component editor) {
			this.header = header;
			this.column = column;
			this.editor = editor;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		private void setDispatchComponent(MouseEvent e) {
			int col = header.getTable().columnAtPoint(e.getPoint());
			if (col != column || col == -1)
				return;

			Point p = e.getPoint();
			Point p2 = SwingUtilities.convertPoint(header, p, editor);
			dispatchComponent = SwingUtilities.getDeepestComponentAt(editor, p2.x, p2.y);
		}

		private boolean repostEvent(MouseEvent e) {
			if (dispatchComponent == null) {
				return false;
			}
			MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e, dispatchComponent);
			dispatchComponent.dispatchEvent(e2);
			return true;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (header.getResizingColumn() == null) {
				Point p = e.getPoint();

				int col = header.getTable().columnAtPoint(p);
				if (col != column || col == -1)
					return;

				int index = header.getColumnModel().getColumnIndexAtX(p.x);
				if (index == -1)
					return;

				editor.setBounds(header.getHeaderRect(index));
				header.add(editor);
				editor.validate();
				setDispatchComponent(e);
				repostEvent(e);
				
				switch(column) {
				case 0:
					if (!BazaPredmeta.getInstance().isSifraAsc()) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o1.getSifra().compareTo(o2.getSifra());
							}
						});
						BazaPredmeta.getInstance().setSifraAsc(true);
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o2.getSifra().compareTo(o1.getSifra());
							}

						});
						BazaPredmeta.getInstance().setSifraAsc(false);
					}
					break;
				case 1:
					if (!BazaPredmeta.getInstance().isNazivAsc()) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o1.getNaziv().compareTo(o2.getNaziv());
							}

						});
						BazaPredmeta.getInstance().setNazivAsc(true);
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o2.getNaziv().compareTo(o1.getNaziv());
							}

						});
						BazaPredmeta.getInstance().setNazivAsc(false);
					}
					break;
				case 2:
					if (!BazaPredmeta.getInstance().isEspbAsc()) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return Integer.compare(o1.getEspb(), o2.getEspb());
							}

						});
						BazaPredmeta.getInstance().setEspbAsc(true);
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return Integer.compare(o2.getEspb(), o1.getEspb());
							}

						});
						BazaPredmeta.getInstance().setEspbAsc(false);
					}
					break;
				case 3:
					if (!BazaPredmeta.getInstance().isGodinaAsc()) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return Integer.compare(o1.getGodStudija(), o2.getGodStudija());
							}

						});
						BazaPredmeta.getInstance().setGodinaAsc(true);
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return Integer.compare(o2.getGodStudija(), o1.getGodStudija());
							}

						});
						BazaPredmeta.getInstance().setGodinaAsc(false);
					}
					break;
				case 4:
					if (!BazaPredmeta.getInstance().isSemestarAsc()) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o1.getSemestar().compareTo(o2.getSemestar());
							}

						});
						BazaPredmeta.getInstance().setSemestarAsc(true);
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet o1, Predmet o2) {
								return o2.getSemestar().compareTo(o1.getSemestar());
							}

						});
						BazaPredmeta.getInstance().setSemestarAsc(false);
					}
					break;
				}

				

				BazaPredmeta.getInstance().setFiltriraniPredmeti(new ArrayList<Predmet>());
				try {
					MainWindow.getInstance().azurirajPrikaz("SORT", -1);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			repostEvent(e);
			dispatchComponent = null;
			header.remove(editor);
		}
	}

}