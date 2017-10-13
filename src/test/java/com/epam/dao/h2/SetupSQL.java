package com.epam.dao.h2;

import com.epam.dao.interfaces.AbstractDaoFactory;
import lombok.extern.log4j.Log4j;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j
public class SetupSQL {
    private  static Path sqlPath = Paths.get("src\\test\\resources\\sql");
    private static Pattern pattern = Pattern.compile(".*\\.sql");

    public static void initConnection(DataSource dataSource) {
        log.info(sqlPath);

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(sqlPath);
            for (Path filePath : paths) {
                if (pattern.matcher(filePath.toFile().getName()).find()) {
                    statement.addBatch(
                            Files.lines(filePath)
                                    .collect(Collectors.joining())
                    );
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error("SQLException during database initialisation: " + e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IOException during accessing sql script file: " + e);
            e.printStackTrace();
        }
    }
}
