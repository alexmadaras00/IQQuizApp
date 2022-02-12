import android.content.Context
import com.example.iqquizapp.repository.database.User


class Room private constructor(private val context: Context) {
    val isLoggedIn: Boolean
        get() {
            val data = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return data.getInt("id", -1) != -1
        }
    val user: User
        get() {
            val data = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                data.getInt("id", -1),
                data.getString("username", null).toString(),
                data.getString("password", null).toString(),
                data.getString("email", null).toString(),
                data.getString("phone", null).toString(),
                data.getInt("test1", -1),
                data.getInt("test2", -1),
                data.getInt("test3", -1),
                data.getInt("test1_progress", -1),
                data.getInt("test2_progress", -1),
                data.getInt("test3_progress", -1),
                data.getBoolean("test1_done", false),
                data.getBoolean("test2_done", false),
                data.getBoolean("test3_done", false)
            )
        }

    fun saveUser(user: User) {
        val data = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = data.edit()

        editor.putInt("id", user.id)
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.putString("email", user.email)
        editor.putString("phone", user.phone)
        editor.putInt("test1", user.test1)
        editor.putInt("test2", user.test2)
        editor.putInt("test3", user.test3)
        editor.putInt("test1_progress", user.test1_progress)
        editor.putInt("test2_progress", user.test2_progress)
        editor.putInt("test3_progress", user.test3_progress)
        editor.putBoolean("test1_done", user.test1_done)
        editor.putBoolean("test2_done", user.test2_done)
        editor.putBoolean("test3_done", user.test3_done)


        editor.apply()
    }

    fun clear() {
        val data = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "user_pref"
        var mInstance: Room? = null

        @Synchronized
        fun getInstance(context: Context): Room {
            if (mInstance == null)
                mInstance = Room(context)
            return mInstance as Room
        }
    }
}