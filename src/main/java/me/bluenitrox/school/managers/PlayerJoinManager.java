package me.bluenitrox.school.managers;

import me.bluenitrox.school.SchoolMode;
import me.bluenitrox.school.mine.manager.MinenManager;
import me.bluenitrox.school.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerJoinManager {

    public static int language = 1;


    public static void cachPlayerData(UUID uuid) {
        if(!isUserExists(uuid)) {
            configuratePlayer(uuid);
        }
        float money = MoneyManager.getMoneyDatabase(uuid);
        float exp = ExpManager.getExpDatabase(uuid);
        int mine = MinenManager.getMineDatabase(uuid);
        int blocks = PlayerBreakBlockManager.getBlocksDatabase(uuid);
        SchoolMode.setPlayerMoney(uuid, money);
        SchoolMode.setPlayerExp(uuid, exp);
        SchoolMode.setPlayerMine(uuid, mine);
        SchoolMode.setPlayerBlocks(uuid, blocks);
        SchoolMode.playerlevel.put(uuid,ExpManager.getLevelDatabase(uuid));

    }

    public static void configuratePlayer(UUID uuid) {
        if(!isUserExists(uuid)) {
            try(PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO spielerdaten (spieleruuid, money, dungeon, exp, mine, prestige, kills, deaths, cases, bloecke, mob, chests, level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setString(1, uuid.toString());
                ps.setFloat(2, 1000);
                ps.setInt(3, 1);
                ps.setFloat(4, 0);
                ps.setInt(5, 1);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.setInt(8, 0);
                ps.setInt(9, 0);
                ps.setInt(10, 0);
                ps.setInt(11, 0);
                ps.setInt(12, 0);
                ps.setInt(13, 1);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isUserExists(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT spieleruuid FROM spielerdaten WHERE spieleruuid = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateBelowName(Player p){
        org.bukkit.scoreboard.ScoreboardManager manager = (org.bukkit.scoreboard.ScoreboardManager) Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showkill", "player_kills");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName(" §6§lLevel");
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
            final Score score = objective.getScore(online);
            score.setScore(ExpManager.getLevel(online.getUniqueId()));
        }
    }
}
