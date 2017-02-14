import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLabel extends JLabel {

	Card card;
	Icon backIcon;
	Icon frontIcon;
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;
	boolean faceUp;
	CardLabel thisCard = this;
	public CardLabel(Card card) {
		this.card = card;
		if (GUICard.iconsLoaded == false) {
			GUICard.loadCardIcons();
		}
		backIcon = GUICard.getBackCardIcon();
		frontIcon = GUICard.getIcon(card);
		setIcon(backIcon);
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				//thisCard.set
				myX = getX();
				myY = getY();
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
				if (!faceUp) {
					flip();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;

				setLocation(myX + deltaX, myY + deltaY);

			}
		});

	}

	public boolean flip() {
		if (faceUp) {
			setIcon(backIcon);
			faceUp = false;
		} else {
			setIcon(frontIcon);
			faceUp = true;
		}
		return faceUp;
	}

	public Card getCard() {
		return card;
	}

	public void setMousePos(int x, int y, int screenX, int screenY){
		myX = x;
		myY = x;
		this.screenX = screenX;
		this.screenY = screenY;
	}
}
