package com.yello.receiver.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yello.receiver.model.Address
import com.yello.receiver.model.Company
import com.yello.receiver.model.Location
import com.yello.receiver.model.User

/**
 * Created by µðšţãƒâ ™ on 28/05/2021.
 * ->
 */


@Database(
    entities = [User::class, Company::class, Address::class, Location::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            RoomDB::class.java,
                            "receiver.db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}