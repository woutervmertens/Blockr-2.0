package com.swop;

import com.swop.uiElements.BlockView;

import java.awt.*;

/**
 * The ui for the Main Window.
 */
public class MainView extends CanvasWindow {
    //Windows
    private View paletteView;
    private View programAreaView;
    private View gameWorldView;
    //ViewModel
    private MainViewModel viewModel;

    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     * @param gameController GameController
     */
    protected MainView(String title, GameController gameController) {
        super(title);
        viewModel = new MainViewModel(new Point(0, 0),width,height,new InputGameControllerFacade(gameController));

        //Sections
        paletteView = new ScrollableView(new PaletteView(new Point(0,0),this.width/4,this.height,new WindowGameControllerFacade(gameController)));
        programAreaView = new ScrollableView(new ProgramAreaView(new Point(paletteView.viewModel.getWidth(),0), paletteView.viewModel.getWidth() * 2, paletteView.viewModel.getHeight(), new WindowGameControllerFacade(gameController)));
        gameWorldView = new ScrollableView(new GameWorldView(new Point(paletteView.viewModel.getWidth() + programAreaView.viewModel.getWidth(),0), paletteView.viewModel.getWidth(), paletteView.viewModel.getHeight()));
        gameController.setPaletteVM((PaletteViewModel) paletteView.viewModel);
        gameController.setProgramAreaVM((ProgramAreaViewModel) programAreaView.viewModel);
        gameController.setGameWorldVM((GameWorldViewModel) gameWorldView.viewModel);
    }

    /**
     * Paints the CanvasWindow.
     *
     * @param g This object offers the methods that allow you to paint on the canvas.
     */
    @Override
    protected void paint(Graphics g) {
        adaptSectionSizes();

        paintSections(g);

        g.setClip(viewModel.getPosition().x,viewModel.getPosition().y, viewModel.getWidth(), viewModel.getHeight());
        BlockView b = new BlockView(viewModel.getDraggedBlock());
        b.draw(g);

        paintFeedback(g);
    }

    /**
     * Sets the sizes and positions of all the sections relative to the current window size.
     */
    private void adaptSectionSizes(){
        paletteView.changeProperties(new Point(0,0),getHeight(),getWidth()/4);
        programAreaView.changeProperties(new Point(paletteView.viewModel.getWidth(),0),getHeight(),getWidth()/2);
        gameWorldView.changeProperties(new Point(paletteView.viewModel.getWidth() + programAreaView.viewModel.getWidth(),0),getHeight(),getWidth()/4);
    }

    /**
     * Calls draw on all the sections.
     *
     * @param g Graphics Object
     */
    private void paintSections(Graphics g){
        paletteView.draw(g);
        programAreaView.draw(g);
        gameWorldView.draw(g);
    }


    /**
     * Paints the text informing the player how many blocks are still available to be placed.
     *
     * @param g Graphics Object
     */
    private void paintFeedback(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(viewModel.getFeedback(), getWidth() - 140, getHeight() - 10);
    }

    /**
     * Calls the respective handlers for each supported mouse input.
     *
     * @param id         The MouseEvent id.
     * @param x          The x position of the mouse action.
     * @param y          The y position of the mouse action.
     * @param clickCount The number of clicks associated with this event.
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
        viewModel.handleMouseInput(id,x,y);
        if (viewModel.shouldRepaint()) repaint();
    }

    /**
     * Calls the respective handlers for each supported key input.
     *
     * @param id      The KeyEvent id (Pressed or typed).
     * @param keyCode The numerical value of the key.
     * @param keyChar The char value of the key.
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar, boolean isHoldingCtrl, boolean isHoldingShift) {
        super.handleKeyEvent(id, keyCode, keyChar, isHoldingCtrl, isHoldingShift);
        viewModel.handleKeyInput(id,keyCode,isHoldingCtrl,isHoldingShift);
        if (viewModel.shouldRepaint()) repaint();
    }
}
