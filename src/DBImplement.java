
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMINUL
 */
public class DBImplement {

    String flag;

    public DBImplement(String flag) {
        this.flag = flag;
    }

    public List<Link> selectAll() {

        List<Link> historyList = new ArrayList<Link>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfigure.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM browser." + flag);

            while (resultSet.next()) {
                Link link = new Link();
                link.setLink(resultSet.getString("link"));

                historyList.add(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return historyList;
    }

    public void insert(Link link) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO " + flag + "(link)" + "VALUES (?)");
            preparedStatement.setString(1, link.getLink());

            preparedStatement.executeUpdate();
            System.out.println("complete");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void delete() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM " + flag);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } //To change body of generated methods, choose Tools | Templates.
    }
}
