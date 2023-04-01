import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Main {
    public static void main(String[] args){

    }

    // body of this method is copied from https://www.geeksforgeeks.org/java-swing-jfilechooser/
    public static String getProfilePhoto(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            String pathOfProfilePhoto = j.getSelectedFile().getAbsolutePath();
            return pathOfProfilePhoto;
        } else { return null; }
    }
}
