
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Internal frame to show kitchen frame.
 */
public class KitchenFrame extends JInternalFrame implements ChangeListener
{
    private JPanel orderPanel;
    private Kitchen kitchen;
    private PickUpList pickUpList;
    
    /**
     * Creates an internal kitchen frame with specified settings.
     * @param xSize width of frame
     * @param ySize height of frame
     * @param xPos x position of frame
     * @param yPos y position of frame
     * @param rest restaurant to order from
     */
    public KitchenFrame(int xSize, int ySize, int xPos, int yPos, Restaurant rest)
    {
        super("Kitchen", false, false, true, false);
        setSize(xSize, ySize);
        setLocation(xPos, yPos);
        setVisible(true);   
        setLayout(new BorderLayout());
        
        kitchen = rest.getKitchen();
        pickUpList = rest.getPickUpList();
        createComponents();
    }
    
    /**
     * Creates components in the frame.
     */
    private void createComponents()
    {
        // remove button
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(event ->
        {
            Order order = kitchen.removeOrder();
            
            if (order != null)
                pickUpList.addOrder(order);
        });
        
        // queue of orders
        orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.PAGE_AXIS));
        orderPanel.add(new JLabel("No orders!"));
        JScrollPane orderPane = new JScrollPane(orderPanel);
        orderPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        
        add(removeButton, BorderLayout.NORTH);
        add(orderPane, BorderLayout.CENTER);
    }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        orderPanel.removeAll();
        
        if (!kitchen.isEmpty())
        {
            for (Order o : kitchen)
            {
            	
            	StringBuilder orderStr = new StringBuilder();
        	    
        	    ArrayList<String> order = new ArrayList<String>();
        	    
        	    //test
        	    /*
        	    order.add("Burger");
        	    order.add("Fries");
        	    order.add("Coke");
        	    */
        	    
        	    for (int i = 0; i < order.size(); i++) {
        	    	if (i != (order.size() - 1))
        	    		orderStr.append(order.get(i) + ", ");
        	    	if (i == (order.size() - 1))
        	    		orderStr.append(order.get(i));
        	    	
        	    }
        	    
        	    JLabel newOrder = new JLabel();
        	    newOrder.setText("<html><body>" + o.getName() + "<br>" + o.getPhoneNumber() + "<br>" +
            			o.getBill() + "<br>" + orderStr.toString() + "</body></html>");
        	    
        	    newOrder.setBorder(new LineBorder(new Color(0,0,0)));
        	    orderPanel.add(newOrder);
        
            }
        }
        else
        {
            orderPanel.add(new JLabel("No orders!"));
        }
        
        orderPanel.revalidate();
        orderPanel.repaint();
    }
}
