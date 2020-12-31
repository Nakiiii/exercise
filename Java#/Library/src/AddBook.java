import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook implements ActionListener {

    private JFrame frame;
    private JPanel topPanel;
    private JPanel[] gridFields;
    private JPanel gridGrid;
    private JButton button;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JTextArea area;
    private JScrollPane scroll;


    public AddBook() {

        frame = new JFrame();
        topPanel = new JPanel();
        gridGrid = new JPanel();
        button = new JButton();
        textFields = new JTextField[]{
                new JTextField(),
                new JTextField(),
                new JTextField()
        };
        area = new JTextArea();
        labels = new JLabel[]{
                new JLabel("Enter new Book"),
                new JLabel("Title: "),
                new JLabel("Author:"),
                new JLabel("Stock: "),
                new JLabel("Description: ")
        };
        gridFields = new JPanel[] {
                new JPanel(),
                new JPanel(),
                new JPanel(),
                new JPanel()
        };
        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(100,20));
        }
        button = new JButton("Enter Book");
        area.setLineWrap(true);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        //mainContainer.setBackground()
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GRAY));


        topPanel.setLayout(new FlowLayout(1));
        topPanel.setBorder(new LineBorder(Color.BLACK, 3));
        //topPanel.setBackground()
        topPanel.add(labels[0]);

        gridFields[0].setLayout(new GridLayout(1, 2, 40,40));
        gridFields[0].setBackground(Color.LIGHT_GRAY);
        gridFields[1].setLayout(new GridLayout(1, 2, 40,40));
        gridFields[1].setBackground(Color.LIGHT_GRAY);
        gridFields[2].setLayout(new GridLayout(1, 2, 40,40));
        gridFields[2].setBackground(Color.LIGHT_GRAY);
        gridFields[3].setLayout(new GridLayout(1, 2, 40,40));
        gridFields[3].setBackground(Color.LIGHT_GRAY);

        gridGrid.setLayout(new GridLayout(4,1));
        gridGrid.setBorder(new LineBorder(Color.BLACK,3));
        gridGrid.setBackground(Color.LIGHT_GRAY);

        gridFields[0].add(labels[1]);
        gridFields[1].add(labels[2]);
        gridFields[2].add(labels[3]);
        gridFields[3].add(labels[4]);

        gridFields[0].add(textFields[0]);
        gridFields[1].add(textFields[1]);
        gridFields[2].add(textFields[2]);
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(100,50));
        gridFields[3].add(scroll);

        for (JPanel gridField : gridFields) {
            gridGrid.add(gridField);
        }

        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new FlowLayout());
        buttonLayout.add(button);
        mainContainer.add(gridGrid);
        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(buttonLayout, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Add new Book");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
