package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveDiagramAsScreenshotAction extends AbstractClassyAction {
    public SaveDiagramAsScreenshotAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/screenshot.png"));
        putValue(NAME, "Save diagram as screenshot");
        putValue(SHORT_DESCRIPTION, "Save diagram as screenshot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null || ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NO_OPENED_DIAGRAM);
            return;
        }
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();

        BufferedImage image = new BufferedImage(diagramView.getWidth(), diagramView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        diagramView.paint(g);

        String path = ApplicationFramework.getInstance().SCREENSHOTS_PATH + diagramView.getDiagram().getName();
        int i = 1;
        if (Files.exists(Paths.get(path))) {
            path += " (" + i + ")";
        }
        while (Files.exists(Paths.get(path))) {
            int x = i + 1;
            path = path.replace("(" + i + ")", "(" + x + ")");
            i++;
        }

        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }
}
