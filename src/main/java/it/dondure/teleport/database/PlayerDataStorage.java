package it.dondure.teleport.database;

import it.dondure.teleport.homes.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataStorage extends SQLlite {
    public PlayerDataStorage(final String path) throws ClassNotFoundException {
        super(path);
    }

    public void createNewTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS home (name varchar(25) PRIMARY KEY,\thome varchar(50) NOT NULL);";
        try (final PreparedStatement stmt = this.getConnection().prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insert(final String player, final String home) {
        try (final PreparedStatement insert = this.getConnection().prepareStatement("INSERT INTO home VALUES(?,?)")) {
            insert.setString(1, player);
            insert.setString(2, home);
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete() {
        try (final PreparedStatement insert = this.getConnection().prepareStatement("DELETE FROM home;")) {
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(final String player, final String home) {
        try (final PreparedStatement insert = this.getConnection().prepareStatement("UPDATE home SET home=? WHERE name=?")) {
            insert.setString(1, player);
            insert.setString(2, home);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean hasHome(final String player) {
        try (final PreparedStatement select = this.getConnection().prepareStatement("SELECT * FROM home WHERE name=?")) {
            select.setString(1, player);
            final ResultSet result = select.executeQuery();
            final boolean exists = result.next();
            result.close();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveHome(final List<Home> home) {
        this.delete();
        for (final Home home2 : home) {
            this.insert(home2.getOwner(), home2.toString());
        }
    }

    public List<Home> allHomes() {
        final List<Home> homes = new ArrayList<Home>();
        try (final PreparedStatement select = this.getConnection().prepareStatement("SELECT * FROM home")) {
            final ResultSet resultSet = select.executeQuery();
            while (resultSet.next()) {
                final String[] args = resultSet.getString("home").split(",");
                homes.add(new Home(args[0], new Location(Bukkit.getWorld(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]))));
            }
            resultSet.close();
            return homes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
