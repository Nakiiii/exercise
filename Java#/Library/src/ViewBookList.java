import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.util.ArrayList;


public class ViewBookList {

    private ArrayList<JLabel> books = new ArrayList<JLabel>();
    // Storage, Author, Description
    private ArrayList<String[]> SADs = new ArrayList<String[]>();

    private JFrame frame;
    private Container mainContainer;
    private ArrayList<JTextArea> sad_labels;
    private JPanel topPanel;
    private JPanel grid;
    private JLabel title;


    public ViewBookList() throws ParserConfigurationException {

        all_books();
        GUIs();

    }

    public void all_books(){
        books = new ArrayList<JLabel>();
        SADs = new ArrayList<String[]>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("books.xml");
            NodeList book_names = doc.getElementsByTagName("book");
            for (int i = 0; i < book_names.getLength(); i++) {
                Node book_name = book_names.item(i);
                if (book_name.getNodeType() == Node.ELEMENT_NODE){
                    Element book = (Element) book_name;
                    String id = book.getAttribute("id");
                    books.add(new JLabel(id));
                    NodeList children = book.getChildNodes();
                    String[] sad = new String[3];
                    for (int j = 0; j < children.getLength(); j++){
                        Node child = children.item(j);
                        if (child.getNodeType() == Node.ELEMENT_NODE){
                            Element c = (Element) child;
                            if(c.getTagName() == "author") {
                                sad[0] = c.getTextContent();
                            } else if (c.getTagName() == "storage"){
                                sad[1] = c.getTextContent();
                            } else if (c.getTagName() == "description"){
                                sad[2] = c.getTextContent();
                            }
                        }
                    }
                    SADs.add(sad);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GUIs(){
        frame = new JFrame();
        mainContainer = frame.getContentPane();
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mainContainer.setLayout(new BorderLayout(8,5));

        title = new JLabel("Book List");
        sad_labels = new ArrayList<JTextArea>();
        topPanel = new JPanel();
        grid = new JPanel();

        topPanel.setLayout(new FlowLayout(1));
        topPanel.setBorder(new LineBorder(Color.BLACK,3));
        topPanel.add(title);
        grid.setLayout(new GridLayout(SADs.size(),2));
        grid.setBorder(new LineBorder(Color.BLACK,3));
        grid.setPreferredSize(new Dimension(400,200));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (String[] sad : SADs) {
            String all ="Author: " + sad[0]+ "\n\nStock: " + sad[1] + "\n\nDescription: " + sad[2];
            sad_labels.add(new JTextArea(all));
        }

        for (int i = 0; i < books.size(); i++) {
            grid.add(books.get(i));
            sad_labels.get(i).setBorder(new LineBorder(Color.BLACK, 1));
            sad_labels.get(i).setLineWrap(true);
            sad_labels.get(i).setWrapStyleWord(true);
            sad_labels.get(i).setEditable(false);
            JScrollPane text_scroll = new JScrollPane();
            text_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            text_scroll.setViewportView(sad_labels.get(i));
            grid.add(text_scroll);
        }

        scrollPane.setViewportView(grid);

        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Book List");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
