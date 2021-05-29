package com.yello.receiver.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.yello.receiver.model.User

/**
 * Created by µðšţãƒâ ™ on 28/05/2021.
 * ->
 */
@Dao
interface UserDao {
    @Query(
        """SELECT user.* , company.* , address.*  , location.* FROM company
        INNER JOIN user ON companyUserId = userId 
        INNER JOIN address ON userId = addressUserId
        INNER JOIN location ON addressUserId = addressId
        WHERE userId = :id"""
    )
    fun getUser(id: Int?): User?

    @Update
    fun updateUser(user: User)

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User): Long

    @Delete
    fun deleteUser(user: User)
}