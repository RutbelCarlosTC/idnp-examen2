package com.erns.canvaspre.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.erns.canvaspre.model.dao.CommentDao;
import com.erns.canvaspre.model.dao.DoorDao;
import com.erns.canvaspre.model.dao.PictureDao;
import com.erns.canvaspre.model.dao.RoomDao;
import com.erns.canvaspre.model.dao.VertexDao;
import com.erns.canvaspre.model.ent.CommentEntity;
import com.erns.canvaspre.model.ent.DoorEntity;
import com.erns.canvaspre.model.ent.PictureEntity;
import com.erns.canvaspre.model.ent.RoomEntity;
import com.erns.canvaspre.model.ent.VertexEntity;

@Database(version = 12,
        entities = {
                DoorEntity.class,
                PictureEntity.class,
                RoomEntity.class,
                VertexEntity.class,
                CommentEntity.class // Agregado para incluir CommentEntity
        }
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DoorDao doorDao();
    public abstract PictureDao pictureDao();
    public abstract RoomDao roomVertexDao();
    public abstract VertexDao vertexDao();
    public abstract CommentDao commentDao(); // DAO para manejar los comentarios

    private static AppDatabase INSTANCE = null;

    // Migración de la versión 10 a la 11 para crear la tabla de comentarios
    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Crear la tabla comments si no existe
            database.execSQL("CREATE TABLE IF NOT EXISTS `comments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `content` TEXT)");
        }
    };

    public static AppDatabase getInstance(Context context) {
        synchronized (context) {
            AppDatabase instance = INSTANCE;

            if (instance == null) {
                instance = Room.databaseBuilder(
                                context,
                                AppDatabase.class,
                                "database-name"
                        )
                        .addMigrations(MIGRATION_10_11)  // Agrega la migración para la versión 11
                        .fallbackToDestructiveMigration()  // Destruye y recrea la base de datos en caso de error
                        .build();

                INSTANCE = instance;
            }

            return instance;
        }
    }
}
