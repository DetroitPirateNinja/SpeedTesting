// Database related
import java.sql.*;
import org.gjt.mm.mysql.Driver;

public class SpeedTestLoader
{
    /**
     * Class: SpeedTestLoader
     *
     * @author Kevin Hein
     *
     * 2021-10-14   Kevin Hein  Created
     *
     * Audit Log
     * ---------
     *
     * Description
     * -----------
     * The purpose of the SpeedTestLoader is to load the JSON results from the SpeedTest into the
     * MySQL database
     */

    /**
     * Main method
     *
     * First argument should be the name of the file with the results
     */
    public static void main(String args[])
    {
        // Read the name of the file that has the results
        // If there is no file - stop processing
        if ( args.length != 1 )
        {
            System.err.println("Usage is:  java SpeedTestLoader results_file_name");
            System.exit(1);
        }

        // Get the information from the file
        try
        {
            SpeedTest instance = new SpeedTest(args[0]);

            boolean loaded = instance.parseFile();

            if ( loaded != true )
            {
                System.out.println("Failure loading the file - exiting");
                System.exit(1);
            }

            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.63:3306/statistics",
                                                    "kevin",
                                                 "MyBoxerPuppy#25");

            Statement stmt = conn.createStatement();

            // Generate an insert statement
            String insStatement = new String();

            insStatement  = "INSERT INTO statistics.bandwidthTest ";
            insStatement += " (runTimestamp,";
            insStatement += "  pingJitter,";
            insStatement += "  pingLatency,";
            insStatement += "  downloadTotal,";
            insStatement += "  downloadBytes,";
            insStatement += "  downloadElapsedTime,";
            insStatement += "  uploadTotal,";
            insStatement += "  uploadBytes,";
            insStatement += "  uploadElapsedTime,";
            insStatement += "  ispName,";
            insStatement += "  interfaceLocalIP,";
            insStatement += "  interfaceName,";
            insStatement += "  interfaceMacAddress,";
            insStatement += "  interfaceIsVPN,";
            insStatement += "  interfaceExternalIP,";
            insStatement += "  serverID,";
            insStatement += "  serverName,";
            insStatement += "  serverLocation,";
            insStatement += "  serverCountry,";
            insStatement += "  serverHost,";
            insStatement += "  serverPort,";
            insStatement += "  serverIP,";
            insStatement += "  resultID,";
            insStatement += "  resultURL)";
            insStatement += " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(insStatement);

            pstmt.setString(1,instance.getTimestamp());
            pstmt.setDouble(2,instance.getJitter());
            pstmt.setDouble(3,instance.getLatency());
            pstmt.setLong(4,instance.getDownloadBandwidth());
            pstmt.setLong(5,instance.getDownloadBytes());
            pstmt.setLong(6,instance.getDownloadElapsed());
            pstmt.setLong(7,instance.getUploadBandwidth());
            pstmt.setLong(8,instance.getUploadBytes());
            pstmt.setLong(9,instance.getUploadElapsed());
            pstmt.setString(10,instance.getIspName());
            pstmt.setString(11,instance.getInternalIP());
            pstmt.setString(12,instance.getInterfaceName());
            pstmt.setString(13,instance.getMacAddr());
            pstmt.setBoolean(14,instance.getIsVPN());
            pstmt.setString(15,instance.getExternalIP());
            pstmt.setLong(16,instance.getServerID());
            pstmt.setString(17,instance.getServerName());
            pstmt.setString(18,instance.getServerLocation());
            pstmt.setString(19,instance.getServerCountry());
            pstmt.setString(20,instance.getServerHost());
            pstmt.setLong(21,instance.getServerPort());
            pstmt.setString(22,instance.getServerIP());
            pstmt.setString(23,instance.getResultID());
            pstmt.setString(24,instance.getResultURL());

            // Do the insert
            int rowcount = pstmt.executeUpdate();

            System.out.println("Number of rows inserted: " + rowcount);

            // Close the database connection
            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
