import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

        createGUI();
        button.addActionListener(this::actionPerformed);


    }

    private void createGUI() {
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
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse("books.xml");

            addNode(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,result);

            frame.dispose();

        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        } catch (TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        } catch (TransformerException transformerException) {
            transformerException.printStackTrace();
        }
    }

    private void addNode(Document doc) {

        Node books = doc.getDocumentElement();

        Element newBook = doc.createElement("book");
        newBook.setAttribute("id", textFields[0].getText());
        Element author = doc.createElement("author");
        Element storage = doc.createElement("storage");
        Element description = doc.createElement("description");
        Element students = doc.createElement("students");

        author.appendChild(doc.createTextNode(textFields[1].getText()));
        storage.appendChild(doc.createTextNode(textFields[2].getText()));
        description.appendChild(doc.createTextNode(area.getText()));
        newBook.appendChild(author);
        newBook.appendChild(storage);
        newBook.appendChild(description);
        newBook.appendChild(students);
        books.appendChild(newBook);

    }
}
